package com.pushwoosh.internal.event;

import androidx.annotation.MainThread;
import com.pushwoosh.internal.event.Event;

@FunctionalInterface
public interface EventListener<T extends Event> {
    @MainThread
    void onReceive(T t);
}
