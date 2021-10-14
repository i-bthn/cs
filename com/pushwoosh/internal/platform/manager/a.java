package com.pushwoosh.internal.platform.manager;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import java.lang.ref.WeakReference;

public class a implements ManagerProvider {
    private final WeakReference<Context> a;

    public a(@Nullable Context context) {
        this.a = new WeakReference<>(context);
    }

    @Nullable
    private Context a() {
        return this.a.get();
    }

    @Override // com.pushwoosh.internal.platform.manager.ManagerProvider
    public ActivityManager getActivityManager() {
        if (a() == null) {
            return null;
        }
        return (ActivityManager) a().getSystemService("activity");
    }

    @Override // com.pushwoosh.internal.platform.manager.ManagerProvider
    public AlarmManager getAlarmManager() {
        if (a() == null) {
            return null;
        }
        return (AlarmManager) a().getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    @Override // com.pushwoosh.internal.platform.manager.ManagerProvider
    public AssetManager getAssets() {
        if (a() == null) {
            return null;
        }
        return a().getAssets();
    }

    @Override // com.pushwoosh.internal.platform.manager.ManagerProvider
    public AudioManager getAudioManager() {
        if (a() == null) {
            return null;
        }
        return (AudioManager) a().getSystemService("audio");
    }

    @Override // com.pushwoosh.internal.platform.manager.ManagerProvider
    public ConnectivityManager getConnectivityManager() {
        if (a() == null) {
            return null;
        }
        return (ConnectivityManager) a().getSystemService("connectivity");
    }

    @Override // com.pushwoosh.internal.platform.manager.ManagerProvider
    public KeyguardManager getKeyguardManager() {
        if (a() == null) {
            return null;
        }
        return (KeyguardManager) a().getSystemService("keyguard");
    }

    @Override // com.pushwoosh.internal.platform.manager.ManagerProvider
    public NotificationManager getNotificationManager() {
        if (a() == null) {
            return null;
        }
        return (NotificationManager) a().getSystemService("notification");
    }

    @Override // com.pushwoosh.internal.platform.manager.ManagerProvider
    public PackageManager getPackageManager() {
        if (a() == null) {
            return null;
        }
        return a().getPackageManager();
    }

    @Override // com.pushwoosh.internal.platform.manager.ManagerProvider
    public PowerManager getPowerManager() {
        if (a() == null) {
            return null;
        }
        return (PowerManager) a().getSystemService("power");
    }

    @Override // com.pushwoosh.internal.platform.manager.ManagerProvider
    public TelephonyManager getTelephonyManager() {
        if (a() == null) {
            return null;
        }
        return (TelephonyManager) a().getSystemService("phone");
    }

    @Override // com.pushwoosh.internal.platform.manager.ManagerProvider
    public WindowManager getWindowManager() {
        if (a() == null) {
            return null;
        }
        return (WindowManager) a().getSystemService("window");
    }
}
