package com.pushwoosh.internal.command;

public class CommandParams<T> {
    private final T params;

    public CommandParams(T t) {
        this.params = t;
    }

    public T getParams() {
        return this.params;
    }
}
