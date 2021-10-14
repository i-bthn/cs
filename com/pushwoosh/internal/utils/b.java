package com.pushwoosh.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.pushwoosh.internal.platform.AndroidPlatformModule;

public class b {
    private SharedPreferences a;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private final Object e = new Object();

    public b(SharedPreferences sharedPreferences) {
        this.a = sharedPreferences;
    }

    private Integer f() {
        try {
            if (g().contains("LastLaunchVersion")) {
                return Integer.valueOf(g().getInt("LastLaunchVersion", -1));
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private SharedPreferences g() {
        return this.a;
    }

    private void h() {
        try {
            g().edit().putInt("LastLaunchVersion", a()).apply();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public int a() {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext == null) {
            return 0;
        }
        try {
            return applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0).versionCode;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public boolean b() {
        d();
        boolean z = this.c;
        this.c = false;
        return z;
    }

    public boolean c() {
        d();
        boolean z = this.b;
        this.b = false;
        return z;
    }

    public void d() {
        synchronized (this.e) {
            if (!this.d) {
                Integer f = f();
                if (f == null) {
                    this.b = true;
                } else if (f.intValue() != a()) {
                    this.b = false;
                    this.c = true;
                    h();
                    this.d = true;
                } else {
                    this.b = false;
                }
                this.c = false;
                h();
                this.d = true;
            }
        }
    }

    public boolean e() {
        d();
        return this.b;
    }
}
