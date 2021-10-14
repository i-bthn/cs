package com.pushwoosh.internal.event;

public class DataEvent<T> implements Event {
    private final T a;

    public DataEvent(T t) {
        this.a = t;
    }

    public T getData() {
        return this.a;
    }
}
