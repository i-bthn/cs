package com.pushwoosh.internal.crash;

import android.os.Looper;
import androidx.annotation.NonNull;
import com.pushwoosh.exception.PushwooshException;

public class LogSender {

    /* access modifiers changed from: package-private */
    public static class a extends e {
        a() {
        }

        @Override // com.pushwoosh.internal.crash.e
        public String a() {
            return "custom_log";
        }

        @Override // com.pushwoosh.internal.crash.e
        public boolean d() {
            return true;
        }

        @Override // com.pushwoosh.internal.crash.e
        public boolean e() {
            return true;
        }
    }

    public static class b extends PushwooshException {
        public b(String str) {
            super(str);
        }
    }

    public static void submitCustomError(@NonNull Throwable th) {
        f.a(th, Looper.getMainLooper().getThread(), new a());
    }

    public static void submitCustomLog(@NonNull String str) {
        submitCustomError(new b(str));
    }
}
