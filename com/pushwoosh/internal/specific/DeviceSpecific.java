package com.pushwoosh.internal.specific;

import com.pushwoosh.internal.registrar.PushRegistrar;

public interface DeviceSpecific {
    int deviceType();

    String permission(String str);

    String projectId();

    PushRegistrar pushRegistrar();

    String type();
}
