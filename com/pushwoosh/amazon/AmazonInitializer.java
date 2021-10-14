package com.pushwoosh.amazon;

import android.content.Context;
import com.pushwoosh.amazon.a.a;
import com.pushwoosh.amazon.a.b.b;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;
import com.pushwoosh.internal.utils.PWLog;

public class AmazonInitializer {
    public static void init(Context context) {
        if (a.a()) {
            new DeviceSpecificProvider.Builder().setDeviceSpecific(b.a()).build(a.a());
        } else {
            PWLog.error("This is not an Amazon device. The service is not available.");
        }
    }
}
