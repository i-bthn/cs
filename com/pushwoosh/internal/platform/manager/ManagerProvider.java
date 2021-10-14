package com.pushwoosh.internal.platform.manager;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import androidx.annotation.Nullable;

public interface ManagerProvider {
    @Nullable
    ActivityManager getActivityManager();

    @Nullable
    AlarmManager getAlarmManager();

    @Nullable
    AssetManager getAssets();

    @Nullable
    AudioManager getAudioManager();

    @Nullable
    ConnectivityManager getConnectivityManager();

    @Nullable
    KeyguardManager getKeyguardManager();

    @Nullable
    NotificationManager getNotificationManager();

    @Nullable
    PackageManager getPackageManager();

    @Nullable
    PowerManager getPowerManager();

    @Nullable
    TelephonyManager getTelephonyManager();

    @Nullable
    WindowManager getWindowManager();
}
