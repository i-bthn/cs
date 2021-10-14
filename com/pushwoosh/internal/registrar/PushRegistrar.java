package com.pushwoosh.internal.registrar;

public interface PushRegistrar {
    void checkDevice(String str) throws Exception;

    void init();

    void registerPW();

    void unregisterPW();
}
