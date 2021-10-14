package com.pushwoosh.notification.g;

import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.notification.b;
import com.pushwoosh.repository.RepositoryModule;

public final class a {
    private static final long[] a = {0, 150, 50, 150};

    static int a(PushMessage pushMessage) {
        if (Build.VERSION.SDK_INT < 24) {
            return pushMessage.getPriority();
        }
        switch (pushMessage.getPriority()) {
            case -2:
            case -1:
                return 2;
            case 0:
                return 3;
            case 1:
            case 2:
                return 4;
            default:
                return NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
        }
    }

    static String a(String str) {
        if (!str.contains(" ") && str.startsWith("pushwoosh_")) {
            return str;
        }
        return "pushwoosh_" + str.trim().replaceAll("\\s+", "_").toLowerCase();
    }

    static long[] a() {
        return a;
    }

    public static String b(PushMessage pushMessage) {
        String r = b.r(pushMessage.toBundle());
        return r == null ? RepositoryModule.getNotificationPreferences().a().get() : r;
    }
}
