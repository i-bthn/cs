package com.pushwoosh.notification;

import androidx.annotation.ColorInt;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.internal.utils.e;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.l;

public class PushwooshNotificationSettings {
    private static final l a = RepositoryModule.getNotificationPreferences();

    private static boolean a() {
        if (a != null) {
            return true;
        }
        PushwooshPlatform.r();
        return false;
    }

    public static boolean areNotificationsEnabled() {
        return a() && e.a() && a.j().get();
    }

    public static void enableNotifications(boolean z) {
        if (a()) {
            a.j().set(z);
        }
    }

    public static void setColorLED(@ColorInt int i) {
        if (a()) {
            a.e().set(i);
        }
    }

    public static void setEnableLED(boolean z) {
        if (a()) {
            a.f().set(z);
        }
    }

    public static void setLightScreenOnNotification(boolean z) {
        if (a()) {
            a.g().set(z);
        }
    }

    public static void setMultiNotificationMode(boolean z) {
        if (a()) {
            a.i().set(z);
        }
    }

    public static void setNotificationChannelName(String str) {
        if (a()) {
            a.a().set(str);
        }
    }

    public static void setNotificationIconBackgroundColor(@ColorInt int i) {
        if (a()) {
            a.c().set(i);
        }
    }

    public static void setSoundNotificationType(SoundType soundType) {
        if (a()) {
            a.n().set(soundType);
        }
    }

    public static void setVibrateNotificationType(VibrateType vibrateType) {
        if (a()) {
            a.r().set(vibrateType);
        }
    }
}
