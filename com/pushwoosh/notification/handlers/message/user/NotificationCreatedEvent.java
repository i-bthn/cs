package com.pushwoosh.notification.handlers.message.user;

import com.pushwoosh.internal.event.Event;
import com.pushwoosh.notification.PushMessage;

public class NotificationCreatedEvent implements Event {
    private final int a;
    private final String b;
    private final PushMessage c;

    NotificationCreatedEvent(int i, String str, PushMessage pushMessage) {
        this.a = i;
        this.b = str;
        this.c = pushMessage;
    }

    public PushMessage getMessage() {
        return this.c;
    }

    public int getMessageId() {
        return this.a;
    }

    public String getMessageTag() {
        return this.b;
    }
}
