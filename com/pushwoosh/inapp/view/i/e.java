package com.pushwoosh.inapp.view.i;

import android.content.Context;
import androidx.annotation.Nullable;
import com.pushwoosh.inapp.view.i.h.b;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;

public class e {

    /* access modifiers changed from: package-private */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a = new int[com.pushwoosh.inapp.view.i.h.a.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            a[com.pushwoosh.inapp.view.i.h.a.IN_APP.ordinal()] = 1;
            a[com.pushwoosh.inapp.view.i.h.a.RICH_MEDIA.ordinal()] = 2;
            try {
                a[com.pushwoosh.inapp.view.i.h.a.REMOTE_URL.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @Nullable
    private Context a() {
        return AndroidPlatformModule.getApplicationContext();
    }

    @Nullable
    public d a(b bVar) {
        if (a() == null) {
            PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            return null;
        }
        int i = a.a[bVar.c().ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? new a(a(), com.pushwoosh.inapp.b.b()) : new c(a()) : bVar.e() ? new f(a(), bVar.d()) : new g(a(), bVar.a()) : (bVar.b() == null || !bVar.b().m()) ? new a(a(), com.pushwoosh.inapp.b.b()) : new b(a());
    }

    public void b(b bVar) {
        try {
            d a2 = a(bVar);
            if (a2 != null) {
                a2.a(bVar.b());
            }
        } catch (Throwable th) {
            PWLog.error(th.getMessage());
        }
    }
}
