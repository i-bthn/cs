package com.pushwoosh;

import com.pushwoosh.exception.RegisterForPushNotificationsException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.internal.utils.NotificationRegistrarHelper;

final class PluginAPI {
    PluginAPI() {
    }

    public static void handleTokenRefresh() {
        NotificationRegistrarHelper.clearToken();
        if (PushwooshPlatform.getInstance() != null) {
            PushwooshPlatform.getInstance().notificationManager().a((Callback<String, RegisterForPushNotificationsException>) null);
        }
    }
}
