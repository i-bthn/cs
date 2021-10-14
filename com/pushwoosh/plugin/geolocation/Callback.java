package com.pushwoosh.plugin.geolocation;

public interface Callback<T> {
    void call(T t, Exception exc);
}
