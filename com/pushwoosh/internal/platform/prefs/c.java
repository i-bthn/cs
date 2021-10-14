package com.pushwoosh.internal.platform.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.prefs.migration.a;
import com.pushwoosh.internal.platform.prefs.migration.b;
import com.pushwoosh.internal.utils.PWLog;

public class c {
    private static volatile c b;
    private int a;

    private c() {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        SharedPreferences sharedPreferences = applicationContext == null ? null : applicationContext.getSharedPreferences("com.pushwoosh.migration", 0);
        this.a = sharedPreferences == null ? 3 : sharedPreferences.getInt("lastVersion", 1);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt("lastVersion", 3).apply();
        }
        PWLog.noise("PrefsFactory created. LastVersion: " + this.a + "; CurrentVersion: " + 3);
    }

    private PrefsProvider a(int i) {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (i == 1) {
            return new b(applicationContext);
        }
        if (i == 2) {
            return new a(applicationContext);
        }
        if (i == 3) {
            return new b(applicationContext);
        }
        PWLog.noise("PrefsFactory", "Unknown version: " + i);
        return null;
    }

    @Nullable
    public static b a() {
        if (b == null) {
            d();
        }
        return b.f();
    }

    public static PrefsProvider b() {
        if (b == null) {
            d();
        }
        return b.a(3);
    }

    public static PrefsProvider c() {
        if (b == null) {
            return null;
        }
        return b.e();
    }

    private static void d() {
        synchronized (c.class) {
            if (b == null) {
                b = new c();
            }
        }
    }

    private PrefsProvider e() {
        return a(this.a);
    }

    private b f() {
        if (this.a == 2) {
            return new a(a(3));
        }
        return null;
    }
}
