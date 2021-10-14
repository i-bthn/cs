package com.pushwoosh;

import android.content.Context;
import android.os.Bundle;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.NotificationRegistrarHelper;
import com.pushwoosh.notification.b;

public class PushwooshMessagingServiceHelper {
    private static void logMessageStatisticsError(String str) {
        if (NetworkModule.getRequestManager() != null) {
            NetworkModule.getRequestManager().logError(str);
        }
    }

    public static boolean onMessageReceived(Context context, Bundle bundle) {
        PushwooshInitializer.init(context);
        sendMessageDeliveryEvent(bundle);
        NotificationRegistrarHelper.handleMessage(bundle);
        return true;
    }

    public static void onTokenRefresh(String str) {
        PushwooshInitializer.init(AndroidPlatformModule.getApplicationContext());
        NotificationRegistrarHelper.onRegisteredForRemoteNotifications(str);
    }

    static void sendMessageDeliveryEvent(Bundle bundle) {
        try {
            PushwooshPlatform.getInstance().o().a(b.t(bundle));
        } catch (Throwable th) {
            logMessageStatisticsError("/messageDeliveryEvent was not sent. Exception occurred " + th.getClass().getCanonicalName() + ". " + th.getMessage());
        }
    }

    static void sendPushStat(Bundle bundle) {
        try {
            PushwooshPlatform.getInstance().o().a(b.t(bundle), b.u(bundle));
        } catch (Throwable th) {
            logMessageStatisticsError("/pushStat request was not sent. Exception occurred " + th.getClass().getCanonicalName() + ". " + th.getMessage());
        }
    }
}
