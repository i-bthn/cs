package com.pushwoosh.internal.event;

import com.pushwoosh.internal.event.Event;

public class Subscription<T extends Event> {
    private final Class<T> a;
    private final EventListener<T> b;

    Subscription(Class<T> cls, EventListener<T> eventListener) {
        this.a = cls;
        this.b = eventListener;
    }

    public void unsubscribe() {
        EventBus.unsubscribe(this.a, this.b);
    }
}
