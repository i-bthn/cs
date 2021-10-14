package com.pushwoosh.internal.specific;

import com.pushwoosh.internal.registrar.PushRegistrar;

public class DeviceSpecificProvider {
    public static final String FCM_ANDROID_TYPE = "Android FCM";
    public static final String HUAWEI_TYPE = "Huawei";
    private static DeviceSpecificProvider b;
    private final DeviceSpecific a;

    public static class Builder {
        private DeviceSpecific a;

        public DeviceSpecificProvider build(boolean z) {
            if (this.a != null) {
                if (DeviceSpecificProvider.b == null || z) {
                    DeviceSpecificProvider unused = DeviceSpecificProvider.b = new DeviceSpecificProvider(this.a);
                }
                return DeviceSpecificProvider.b;
            }
            throw new IllegalArgumentException("You must setup deviceSpecific");
        }

        public Builder setDeviceSpecific(DeviceSpecific deviceSpecific) {
            this.a = deviceSpecific;
            return this;
        }
    }

    private DeviceSpecificProvider(DeviceSpecific deviceSpecific) {
        this.a = deviceSpecific;
    }

    public static DeviceSpecificProvider getInstance() {
        return b;
    }

    public static boolean isInited() {
        return b != null;
    }

    public int deviceType() {
        return this.a.deviceType();
    }

    public boolean isFirebase() {
        return type().equals(FCM_ANDROID_TYPE);
    }

    public boolean isHuawei() {
        return type().equals(HUAWEI_TYPE);
    }

    public String permission(String str) {
        return this.a.permission(str);
    }

    public String projectId() {
        return this.a.projectId();
    }

    public PushRegistrar pushRegistrar() {
        return this.a.pushRegistrar();
    }

    public String type() {
        return this.a.type();
    }
}
