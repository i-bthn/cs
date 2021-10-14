package com.pushwoosh.amazon.a.b;

import com.pushwoosh.amazon.internal.registrar.AdmRegistrar;
import com.pushwoosh.internal.registrar.PushRegistrar;
import com.pushwoosh.internal.specific.DeviceSpecific;

/* access modifiers changed from: package-private */
public final class a implements DeviceSpecific {
    private final PushRegistrar a = new AdmRegistrar();

    a() {
    }

    @Override // com.pushwoosh.internal.specific.DeviceSpecific
    public int deviceType() {
        return 9;
    }

    @Override // com.pushwoosh.internal.specific.DeviceSpecific
    public String permission(String str) {
        return str + ".permission.RECEIVE_ADM_MESSAGE";
    }

    @Override // com.pushwoosh.internal.specific.DeviceSpecific
    public String projectId() {
        return "AMAZON_DEVICE";
    }

    @Override // com.pushwoosh.internal.specific.DeviceSpecific
    public PushRegistrar pushRegistrar() {
        return this.a;
    }

    @Override // com.pushwoosh.internal.specific.DeviceSpecific
    public String type() {
        return "Amazon";
    }
}
