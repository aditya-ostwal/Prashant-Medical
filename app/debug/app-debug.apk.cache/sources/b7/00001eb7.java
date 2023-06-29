package okhttp3;

import java.io.IOException;

/* loaded from: classes12.dex */
public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    QUIC("quic");
    
    private final String protocol;

    Protocol(String protocol) {
        this.protocol = protocol;
    }

    public static Protocol get(String protocol) throws IOException {
        Protocol protocol2 = HTTP_1_0;
        if (protocol.equals(protocol2.protocol)) {
            return protocol2;
        }
        Protocol protocol3 = HTTP_1_1;
        if (protocol.equals(protocol3.protocol)) {
            return protocol3;
        }
        Protocol protocol4 = HTTP_2;
        if (protocol.equals(protocol4.protocol)) {
            return protocol4;
        }
        Protocol protocol5 = SPDY_3;
        if (protocol.equals(protocol5.protocol)) {
            return protocol5;
        }
        Protocol protocol6 = QUIC;
        if (protocol.equals(protocol6.protocol)) {
            return protocol6;
        }
        throw new IOException("Unexpected protocol: " + protocol);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.protocol;
    }
}