package com.pushwoosh.notification;

import android.app.NotificationManager;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.b;
import java.io.Serializable;

public class LocalNotificationRequest implements Serializable {
    private int a;

    public LocalNotificationRequest(int i) {
        this.a = i;
    }

    public void cancel() {
        unschedule();
        b a2 = RepositoryModule.getLocalNotificationStorage().a(this.a);
        if (a2 != null) {
            int b = a2.b();
            String c = a2.c();
            NotificationManager notificationManager = AndroidPlatformModule.getManagerProvider().getNotificationManager();
            if (notificationManager != null) {
                notificationManager.cancel(c, b);
            }
        }
    }

    public int getRequestId() {
        return this.a;
    }

    public void unschedule() {
        LocalNotificationReceiver.cancelNotification(this.a);
    }
}
