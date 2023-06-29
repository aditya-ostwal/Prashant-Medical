package kotlinx.coroutines;

import androidx.exifinterface.media.ExifInterface;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;

/* compiled from: Await.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\u001a=\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u001e\u0010\u0003\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004\"\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a%\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0004\"\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u001b\u0010\u0007\u001a\u00020\b*\b\u0012\u0004\u0012\u00020\n0\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"awaitAll", "", ExifInterface.GPS_DIRECTION_TRUE, "deferreds", "", "Lkotlinx/coroutines/Deferred;", "([Lkotlinx/coroutines/Deferred;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinAll", "", "jobs", "Lkotlinx/coroutines/Job;", "([Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AwaitKt {
    public static final <T> Object awaitAll(Deferred<? extends T>[] deferredArr, Continuation<? super List<? extends T>> continuation) {
        return deferredArr.length == 0 ? CollectionsKt.emptyList() : new AwaitAll(deferredArr).await(continuation);
    }

    public static final <T> Object awaitAll(Collection<? extends Deferred<? extends T>> collection, Continuation<? super List<? extends T>> continuation) {
        if (collection.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        Object[] array = collection.toArray(new Deferred[0]);
        if (array != null) {
            return new AwaitAll((Deferred[]) array).await(continuation);
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object joinAll(kotlinx.coroutines.Job[] r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.AwaitKt$joinAll$1
            if (r0 == 0) goto L14
            r0 = r10
            kotlinx.coroutines.AwaitKt$joinAll$1 r0 = (kotlinx.coroutines.AwaitKt$joinAll$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            kotlinx.coroutines.AwaitKt$joinAll$1 r0 = new kotlinx.coroutines.AwaitKt$joinAll$1
            r0.<init>(r10)
        L19:
            r10 = r0
            java.lang.Object r0 = r10.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r10.label
            switch(r2) {
                case 0: goto L3e;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2d:
            r9 = 0
            r2 = 0
            int r3 = r10.I$1
            int r4 = r10.I$0
            java.lang.Object r5 = r10.L$0
            kotlinx.coroutines.Job[] r5 = (kotlinx.coroutines.Job[]) r5
            kotlin.ResultKt.throwOnFailure(r0)
            r8 = r4
            r4 = r3
            r3 = r8
            goto L46
        L3e:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = 0
            r3 = 0
            int r4 = r9.length
            r5 = r9
            r9 = r2
        L46:
            if (r3 >= r4) goto L5f
            r2 = r5[r3]
            int r3 = r3 + 1
            r6 = 0
            r10.L$0 = r5
            r10.I$0 = r3
            r10.I$1 = r4
            r7 = 1
            r10.label = r7
            java.lang.Object r2 = r2.join(r10)
            if (r2 != r1) goto L5d
            return r1
        L5d:
            r2 = r6
            goto L46
        L5f:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.AwaitKt.joinAll(kotlinx.coroutines.Job[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object joinAll(java.util.Collection<? extends kotlinx.coroutines.Job> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.AwaitKt$joinAll$3
            if (r0 == 0) goto L14
            r0 = r7
            kotlinx.coroutines.AwaitKt$joinAll$3 r0 = (kotlinx.coroutines.AwaitKt$joinAll$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            kotlinx.coroutines.AwaitKt$joinAll$3 r0 = new kotlinx.coroutines.AwaitKt$joinAll$3
            r0.<init>(r7)
        L19:
            r7 = r0
            java.lang.Object r0 = r7.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r7.label
            switch(r2) {
                case 0: goto L37;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2d:
            r6 = 0
            r2 = 0
            java.lang.Object r3 = r7.L$0
            java.util.Iterator r3 = (java.util.Iterator) r3
            kotlin.ResultKt.throwOnFailure(r0)
            goto L42
        L37:
            kotlin.ResultKt.throwOnFailure(r0)
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            r2 = 0
            java.util.Iterator r3 = r6.iterator()
            r6 = r2
        L42:
            boolean r2 = r3.hasNext()
            if (r2 == 0) goto L5d
            java.lang.Object r2 = r3.next()
            kotlinx.coroutines.Job r2 = (kotlinx.coroutines.Job) r2
            r4 = 0
            r7.L$0 = r3
            r5 = 1
            r7.label = r5
            java.lang.Object r2 = r2.join(r7)
            if (r2 != r1) goto L5b
            return r1
        L5b:
            r2 = r4
            goto L42
        L5d:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.AwaitKt.joinAll(java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }
}