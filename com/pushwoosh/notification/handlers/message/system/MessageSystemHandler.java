package com.pushwoosh.notification.handlers.message.system;

import android.os.Bundle;
import androidx.annotation.WorkerThread;

@FunctionalInterface
public interface MessageSystemHandler {
    @WorkerThread
    boolean preHandleMessage(Bundle bundle);
}
