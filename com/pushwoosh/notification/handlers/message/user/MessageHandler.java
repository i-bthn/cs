package com.pushwoosh.notification.handlers.message.user;

import androidx.annotation.WorkerThread;
import com.pushwoosh.notification.PushMessage;

@FunctionalInterface
public interface MessageHandler {
    @WorkerThread
    void handlePushMessage(PushMessage pushMessage);
}
