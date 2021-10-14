package com.pushwoosh.internal;

import android.content.Context;
import android.text.TextUtils;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.registrar.PushRegistrar;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.PushwooshNotificationManager;

public class b {
    private final PluginProvider a;
    private final PushwooshNotificationManager b;

    public b(PluginProvider pluginProvider, PushwooshNotificationManager pushwooshNotificationManager) {
        this.a = pluginProvider;
        this.b = pushwooshNotificationManager;
    }

    private void a(PushRegistrar pushRegistrar) {
        PushwooshNotificationManager pushwooshNotificationManager = this.b;
        if (pushwooshNotificationManager != null) {
            pushwooshNotificationManager.a(pushRegistrar);
        }
    }

    private boolean a(Context context) {
        return a("com.pushwoosh.amazon.AmazonInitializer", "com.pushwoosh.amazon.internal.registrar.AdmRegistrar", context);
    }

    private boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private boolean a(String str, String str2, Context context) {
        try {
            Class.forName(str).getMethod("init", Context.class).invoke(null, context);
            Class<?> cls = Class.forName(str2);
            PushRegistrar pushRegistrar = DeviceSpecificProvider.getInstance().pushRegistrar();
            if (cls.isInstance(pushRegistrar)) {
                a(pushRegistrar);
                return true;
            }
        } catch (Throwable th) {
            PWLog.error("PushRegistrarHelper", "Unexpected error occurred calling 'initRegistrarClass' method");
            PWLog.error("PushRegistrarHelper", th.getMessage());
        }
        return false;
    }

    private boolean b(Context context) {
        return a("com.pushwoosh.firebase.FirebaseInitializer", "com.pushwoosh.firebase.internal.registrar.FcmRegistrar", context);
    }

    private boolean b(String str) {
        return TextUtils.equals(str, "Native");
    }

    private boolean c() {
        return a("com.pushwoosh.amazon.AmazonInitializer");
    }

    private boolean c(Context context) {
        return a("com.pushwoosh.huawei.HuaweiInitializer", "com.pushwoosh.huawei.internal.registrar.HuaweiPushRegistrar", context);
    }

    private boolean d() {
        return a("com.pushwoosh.firebase.FirebaseInitializer");
    }

    private boolean e() {
        return a("com.pushwoosh.huawei.HuaweiInitializer");
    }

    public void a() {
        Context applicationContext;
        PluginProvider pluginProvider = this.a;
        if (pluginProvider != null && !b(pluginProvider.getPluginType()) && (applicationContext = AndroidPlatformModule.getApplicationContext()) != null && e()) {
            c(applicationContext);
        }
    }

    public boolean b() {
        PluginProvider pluginProvider = this.a;
        if (pluginProvider == null || b(pluginProvider.getPluginType())) {
            return false;
        }
        PWLog.debug("PushRegistrarHelper", "Initializing default PushRegistrar in a plugin");
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext == null) {
            return false;
        }
        if (!c() || !a(applicationContext)) {
            return d() && b(applicationContext);
        }
        return true;
    }
}
