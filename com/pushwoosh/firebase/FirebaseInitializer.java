package com.pushwoosh.firebase;

import android.content.Context;
import com.pushwoosh.firebase.a.c.b;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;

public class FirebaseInitializer {
    public static void init(Context context) {
        new DeviceSpecificProvider.Builder().setDeviceSpecific(b.a()).build(true);
    }
}
