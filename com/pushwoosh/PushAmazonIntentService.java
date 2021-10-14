package com.pushwoosh;

import android.content.Intent;
import com.amazon.device.messaging.ADMMessageHandlerBase;
import com.pushwoosh.internal.utils.NotificationRegistrarHelper;
import com.pushwoosh.internal.utils.PWLog;

public class PushAmazonIntentService extends ADMMessageHandlerBase {
    public PushAmazonIntentService() {
        super(PushAmazonIntentService.class.getName());
    }

    public PushAmazonIntentService(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public void onMessage(Intent intent) {
        PWLog.info("AmazonIntentService", "Received message");
        NotificationRegistrarHelper.handleMessage(intent.getExtras());
    }

    /* access modifiers changed from: protected */
    public void onRegistered(String str) {
        PWLog.info("AmazonIntentService", "Device registered: regId = " + str);
        NotificationRegistrarHelper.onRegisteredForRemoteNotifications(str);
    }

    /* access modifiers changed from: protected */
    public void onRegistrationError(String str) {
        PWLog.error("AmazonIntentService", "Messaging registration error: " + str);
        NotificationRegistrarHelper.onFailedToRegisterForRemoteNotifications(str);
    }

    /* access modifiers changed from: protected */
    public void onUnregistered(String str) {
        PWLog.info("AmazonIntentService", "Device unregistered");
        NotificationRegistrarHelper.onUnregisteredFromRemoteNotifications(str);
    }
}
