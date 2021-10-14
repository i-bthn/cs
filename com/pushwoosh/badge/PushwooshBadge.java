package com.pushwoosh.badge;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.pushwoosh.badge.e.a.c;
import com.pushwoosh.internal.platform.AndroidPlatformModule;

public class PushwooshBadge {
    /* access modifiers changed from: private */
    public static /* synthetic */ void a(int i) {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext != null) {
            c.a(applicationContext, i);
        }
    }

    public static void addBadgeNumber(int i) {
        setBadgeNumber(getBadgeNumber() + i);
    }

    public static int getBadgeNumber() {
        return a.a().a().get();
    }

    public static void setBadgeNumber(int i) {
        a.b().a(i);
        new Handler(Looper.getMainLooper()).post(new Runnable(i) {
            /* class com.pushwoosh.badge.$$Lambda$PushwooshBadge$IslIgDL_j157TDtmFcJ_V63VuWg */
            private final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                PushwooshBadge.lambda$IslIgDL_j157TDtmFcJ_V63VuWg(this.f$0);
            }
        });
    }
}
