package kotlin.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/io/BufferedReader;", "invoke"}, k = 3, mv = {1, 1, 8})
/* compiled from: Console.kt */
final class ConsoleKt$stdin$2 extends Lambda implements Function0<BufferedReader> {
    public static final ConsoleKt$stdin$2 INSTANCE = new ConsoleKt$stdin$2();

    ConsoleKt$stdin$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final BufferedReader invoke() {
        return new BufferedReader(new InputStreamReader(new InputStream() {
            /* class kotlin.io.ConsoleKt$stdin$2.AnonymousClass1 */

            @Override // java.io.InputStream
            public int read() {
                return System.in.read();
            }

            @Override // java.io.InputStream
            public void reset() {
                System.in.reset();
            }

            @Override // java.io.InputStream
            public int read(@NotNull byte[] bArr) {
                Intrinsics.checkParameterIsNotNull(bArr, "b");
                return System.in.read(bArr);
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
            public void close() {
                System.in.close();
            }

            public void mark(int i) {
                System.in.mark(i);
            }

            @Override // java.io.InputStream
            public long skip(long j) {
                return System.in.skip(j);
            }

            @Override // java.io.InputStream
            public int available() {
                return System.in.available();
            }

            public boolean markSupported() {
                return System.in.markSupported();
            }

            @Override // java.io.InputStream
            public int read(@NotNull byte[] bArr, int i, int i2) {
                Intrinsics.checkParameterIsNotNull(bArr, "b");
                return System.in.read(bArr, i, i2);
            }
        }));
    }
}
