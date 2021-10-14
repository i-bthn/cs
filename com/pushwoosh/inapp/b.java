package com.pushwoosh.inapp;

import androidx.annotation.Nullable;
import com.pushwoosh.inapp.k.a;
import com.pushwoosh.inapp.k.c;
import com.pushwoosh.inapp.k.d;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.repository.RepositoryModule;

public class b {
    private static final c a = new a(AndroidPlatformModule.getApplicationContext());
    private static final com.pushwoosh.inapp.j.k.c b = new com.pushwoosh.inapp.j.k.c(b());
    private static final com.pushwoosh.inapp.h.b c = new com.pushwoosh.inapp.h.b(b());
    private static volatile d d;
    private static final Object e = new Object();
    private static volatile com.pushwoosh.inapp.j.c f;
    private static final Object g = new Object();

    private static com.pushwoosh.inapp.j.k.c a() {
        return b;
    }

    public static c b() {
        return a;
    }

    @Nullable
    public static com.pushwoosh.inapp.j.c c() {
        synchronized (g) {
            if (f == null) {
                if (AndroidPlatformModule.getApplicationContext() == null) {
                    return null;
                }
                f = new com.pushwoosh.inapp.j.c(NetworkModule.getRequestManager(), d(), a(), e(), b(), RepositoryModule.getRegistrationPreferences());
            }
            return f;
        }
    }

    @Nullable
    public static d d() {
        synchronized (e) {
            if (d == null) {
                if (AndroidPlatformModule.getApplicationContext() == null) {
                    return null;
                }
                d = new com.pushwoosh.inapp.k.b(AndroidPlatformModule.getApplicationContext());
            }
            return d;
        }
    }

    private static com.pushwoosh.inapp.h.b e() {
        return c;
    }
}
