package com.pushwoosh;

import android.content.Context;
import android.content.IntentFilter;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;
import com.pushwoosh.internal.utils.LockScreenReceiver;
import com.pushwoosh.internal.utils.PWLog;
import java.lang.reflect.InvocationTargetException;

public class PushwooshInitializer {
    private static final String a = "PushwooshInitializer";

    /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:4:? A[ExcHandler: ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:1:0x0007] */
    private static void a(Context context) {
        Class.forName("com.pushwoosh.xamarin.internal.XamarinPluginProvider");
        try {
            Class.forName("com.pushwoosh.firebase.FirebaseInitializer").getMethod("init", Context.class).invoke(null, context);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
    }

    private static boolean a() {
        return (!DeviceSpecificProvider.isInited() || PushwooshPlatform.getInstance() == null || AndroidPlatformModule.getApplicationContext() == null) ? false : true;
    }

    public static void init(Context context) {
        if (a()) {
            PWLog.noise(a, "already init");
            return;
        }
        DeviceSpecificProvider.isInited();
        AndroidPlatformModule.init(context);
        a(context);
        if (DeviceSpecificProvider.getInstance() == null) {
            PWLog.error(a, "No attached push notifications providers have been found.\nThis error can be seen when you use 'pushwoosh-huawei' module\nnot on Huawei device or you have not added any module attaching\npush notifications provider.\nPushwoosh supports Firebase, Amazon, Huawei, Baidu push notification providers.\nSee the integration guide https://docs.pushwoosh.com/platform-docs/pushwoosh-sdk/android-push-notifications");
            return;
        }
        PushwooshPlatform a2 = new PushwooshPlatform.b().a(new a()).a(DeviceSpecificProvider.getInstance().pushRegistrar()).a();
        a2.l();
        AndroidPlatformModule.getApplicationOpenDetector().a(a2.a().e());
        LockScreenReceiver lockScreenReceiver = new LockScreenReceiver();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.ANSWER");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        context.registerReceiver(lockScreenReceiver, intentFilter);
        PWLog.noise(a, "Pushwoosh init finished");
    }
}
