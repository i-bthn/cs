package com.pushwoosh.badge.b;

import com.pushwoosh.badge.PushwooshBadge;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.notification.handlers.message.user.NotificationMessageHandler;

public class a extends NotificationMessageHandler {
    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.notification.handlers.message.user.NotificationMessageHandler
    public void handleNotification(PushMessage pushMessage) {
        int badges = pushMessage.getBadges();
        if (badges >= 0) {
            PushwooshBadge.setBadgeNumber(badges);
        }
    }
}
