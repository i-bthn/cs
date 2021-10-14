package com.pushwoosh.plugin.pushnotifications;

import android.content.pm.ApplicationInfo;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.NotificationServiceExtension;
import com.pushwoosh.notification.PushMessage;

public class PushwooshNotificationServiceExtension extends NotificationServiceExtension {
    private boolean showForegroundPush;

    public PushwooshNotificationServiceExtension() {
        try {
            ApplicationInfo applicationInfo = getApplicationContext().getPackageManager().getApplicationInfo(getApplicationContext().getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                boolean z = false;
                this.showForegroundPush = (applicationInfo.metaData.getBoolean("PW_BROADCAST_PUSH", false) || applicationInfo.metaData.getBoolean("com.pushwoosh.foreground_push", false)) ? true : z;
            }
        } catch (Exception e) {
            PWLog.error(PushNotifications.TAG, "Failed to read AndroidManifest metaData", e);
        }
        PWLog.debug(PushNotifications.TAG, "showForegroundPush = " + this.showForegroundPush);
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.notification.NotificationServiceExtension
    public boolean onMessageReceived(PushMessage pushMessage) {
        PushNotifications.messageReceived(pushMessage.toJson().toString());
        return (!this.showForegroundPush && isAppOnForeground()) || super.onMessageReceived(pushMessage);
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.notification.NotificationServiceExtension
    public void onMessageOpened(PushMessage pushMessage) {
        PushNotifications.openPush(pushMessage.toJson().toString());
    }
}
