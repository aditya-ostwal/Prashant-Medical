package okhttp3.internal.ws;

import com.google.android.gms.location.GeofenceStatusCodes;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.UByte;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* loaded from: classes12.dex */
final class WebSocketReader {
    boolean closed;
    final FrameCallback frameCallback;
    long frameLength;
    final boolean isClient;
    boolean isControlFrame;
    boolean isFinalFrame;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    int opcode;
    final BufferedSource source;
    private final Buffer controlFrameBuffer = new Buffer();
    private final Buffer messageFrameBuffer = new Buffer();

    /* loaded from: classes12.dex */
    public interface FrameCallback {
        void onReadClose(int i, String str);

        void onReadMessage(String str) throws IOException;

        void onReadMessage(ByteString byteString) throws IOException;

        void onReadPing(ByteString byteString);

        void onReadPong(ByteString byteString);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSocketReader(boolean isClient, BufferedSource source, FrameCallback frameCallback) {
        if (source == null) {
            throw new NullPointerException("source == null");
        }
        if (frameCallback == null) {
            throw new NullPointerException("frameCallback == null");
        }
        this.isClient = isClient;
        this.source = source;
        this.frameCallback = frameCallback;
        this.maskKey = isClient ? null : new byte[4];
        this.maskCursor = isClient ? null : new Buffer.UnsafeCursor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void processNextFrame() throws IOException {
        readHeader();
        if (this.isControlFrame) {
            readControlFrame();
        } else {
            readMessageFrame();
        }
    }

    /* JADX WARN: Finally extract failed */
    private void readHeader() throws IOException {
        String str;
        if (this.closed) {
            throw new IOException("closed");
        }
        long timeoutBefore = this.source.timeout().timeoutNanos();
        this.source.timeout().clearTimeout();
        try {
            int b0 = this.source.readByte() & UByte.MAX_VALUE;
            this.source.timeout().timeout(timeoutBefore, TimeUnit.NANOSECONDS);
            this.opcode = b0 & 15;
            boolean z = (b0 & 128) != 0;
            this.isFinalFrame = z;
            boolean z2 = (b0 & 8) != 0;
            this.isControlFrame = z2;
            if (z2 && !z) {
                throw new ProtocolException("Control frames must be final.");
            }
            boolean reservedFlag1 = (b0 & 64) != 0;
            boolean reservedFlag2 = (b0 & 32) != 0;
            boolean reservedFlag3 = (b0 & 16) != 0;
            if (reservedFlag1 || reservedFlag2 || reservedFlag3) {
                throw new ProtocolException("Reserved flags are unsupported.");
            }
            int b1 = this.source.readByte() & UByte.MAX_VALUE;
            boolean isMasked = (b1 & 128) != 0;
            if (isMasked == this.isClient) {
                if (this.isClient) {
                    str = "Server-sent frames must not be masked.";
                } else {
                    str = "Client-sent frames must be masked.";
                }
                throw new ProtocolException(str);
            }
            long j = b1 & WorkQueueKt.MASK;
            this.frameLength = j;
            if (j == 126) {
                this.frameLength = this.source.readShort() & 65535;
            } else if (j == 127) {
                long readLong = this.source.readLong();
                this.frameLength = readLong;
                if (readLong < 0) {
                    throw new ProtocolException("Frame length 0x" + Long.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF");
                }
            }
            if (this.isControlFrame && this.frameLength > 125) {
                throw new ProtocolException("Control frame must be less than 125B.");
            }
            if (isMasked) {
                this.source.readFully(this.maskKey);
            }
        } catch (Throwable th) {
            this.source.timeout().timeout(timeoutBefore, TimeUnit.NANOSECONDS);
            throw th;
        }
    }

    private void readControlFrame() throws IOException {
        long j = this.frameLength;
        if (j > 0) {
            this.source.readFully(this.controlFrameBuffer, j);
            if (!this.isClient) {
                this.controlFrameBuffer.readAndWriteUnsafe(this.maskCursor);
                this.maskCursor.seek(0L);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        }
        switch (this.opcode) {
            case 8:
                int code = GeofenceStatusCodes.GEOFENCE_REQUEST_TOO_FREQUENT;
                String reason = "";
                long bufferSize = this.controlFrameBuffer.size();
                if (bufferSize == 1) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if (bufferSize != 0) {
                    code = this.controlFrameBuffer.readShort();
                    reason = this.controlFrameBuffer.readUtf8();
                    String codeExceptionMessage = WebSocketProtocol.closeCodeExceptionMessage(code);
                    if (codeExceptionMessage != null) {
                        throw new ProtocolException(codeExceptionMessage);
                    }
                }
                this.frameCallback.onReadClose(code, reason);
                this.closed = true;
                return;
            case 9:
                this.frameCallback.onReadPing(this.controlFrameBuffer.readByteString());
                return;
            case 10:
                this.frameCallback.onReadPong(this.controlFrameBuffer.readByteString());
                return;
            default:
                throw new ProtocolException("Unknown control opcode: " + Integer.toHexString(this.opcode));
        }
    }

    private void readMessageFrame() throws IOException {
        int opcode = this.opcode;
        if (opcode != 1 && opcode != 2) {
            throw new ProtocolException("Unknown opcode: " + Integer.toHexString(opcode));
        }
        readMessage();
        if (opcode == 1) {
            this.frameCallback.onReadMessage(this.messageFrameBuffer.readUtf8());
        } else {
            this.frameCallback.onReadMessage(this.messageFrameBuffer.readByteString());
        }
    }

    private void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            readHeader();
            if (this.isControlFrame) {
                readControlFrame();
            } else {
                return;
            }
        }
    }

    private void readMessage() throws IOException {
        while (!this.closed) {
            long j = this.frameLength;
            if (j > 0) {
                this.source.readFully(this.messageFrameBuffer, j);
                if (!this.isClient) {
                    this.messageFrameBuffer.readAndWriteUnsafe(this.maskCursor);
                    this.maskCursor.seek(this.messageFrameBuffer.size() - this.frameLength);
                    WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            }
            if (!this.isFinalFrame) {
                readUntilNonControlFrame();
                if (this.opcode != 0) {
                    throw new ProtocolException("Expected continuation opcode. Got: " + Integer.toHexString(this.opcode));
                }
            } else {
                return;
            }
        }
        throw new IOException("closed");
    }
}