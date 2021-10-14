package com.pushwoosh.notification.g;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import com.pushwoosh.repository.RepositoryModule;

public class c {
    @RequiresApi(api = 26)
    private static int a(NotificationChannel notificationChannel) {
        if (b(notificationChannel)) {
            return 0;
        }
        return c(notificationChannel) ? 4 : 6;
    }

    @RequiresApi(api = 26)
    public static int a(NotificationManager notificationManager) {
        NotificationChannel notificationChannel;
        String a = a.a(RepositoryModule.getNotificationPreferences().a().get());
        if (TextUtils.isEmpty(a) || (notificationChannel = notificationManager.getNotificationChannel(a)) == null) {
            return 6;
        }
        return a(notificationChannel);
    }

    @RequiresApi(api = 26)
    private static boolean b(NotificationChannel notificationChannel) {
        return notificationChannel.getImportance() == 0;
    }

    @RequiresApi(api = 26)
    private static boolean c(NotificationChannel notificationChannel) {
        return notificationChannel.getImportance() <= 2 || notificationChannel.getSound() == null;
    }
}
