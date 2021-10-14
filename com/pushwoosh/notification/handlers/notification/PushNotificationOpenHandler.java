package com.pushwoosh.notification.handlers.notification;

import android.os.Bundle;

@FunctionalInterface
public interface PushNotificationOpenHandler {
    void postHandleNotification(Bundle bundle);
}
