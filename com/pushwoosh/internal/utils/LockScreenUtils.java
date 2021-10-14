package com.pushwoosh.internal.utils;

import android.app.KeyguardManager;
import android.os.Build;
import android.os.PowerManager;
import com.pushwoosh.internal.platform.AndroidPlatformModule;

public class LockScreenUtils {
    public static boolean isScreenLocked() {
        KeyguardManager keyguardManager = AndroidPlatformModule.getManagerProvider().getKeyguardManager();
        boolean z = keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
        PowerManager powerManager = AndroidPlatformModule.getManagerProvider().getPowerManager();
        return z || !(powerManager == null || (Build.VERSION.SDK_INT >= 20 ? powerManager.isInteractive() : powerManager.isScreenOn()));
    }
}
