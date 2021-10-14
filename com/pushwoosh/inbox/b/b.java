package com.pushwoosh.inbox.b;

import android.content.SharedPreferences;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.inbox.f.a;
import com.pushwoosh.internal.command.CommandApplayer;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;

public class b {
    private static volatile com.pushwoosh.inbox.e.b a;
    private static final Object b = new Object();
    private static volatile a c;
    private static final Object d = new Object();
    private static com.pushwoosh.inbox.f.c.b e;
    private static RequestManager f;
    private static CommandApplayer g;

    public static com.pushwoosh.inbox.e.b a() {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    if (f == null) {
                        throw new IllegalArgumentException("Incorrect state.");
                    } else if (g != null) {
                        a = new com.pushwoosh.inbox.e.b(f, b(), g);
                        f = null;
                    } else {
                        throw new IllegalArgumentException("Incorrect state.");
                    }
                }
            }
        }
        return a;
    }

    public static void a(com.pushwoosh.inbox.f.c.b bVar, RequestManager requestManager, PrefsProvider prefsProvider) {
        e = bVar;
        f = requestManager;
        g = new CommandApplayer();
        SharedPreferences providePrefs = prefsProvider.providePrefs("pwInbox");
        String applicationCode = Pushwoosh.getInstance().getApplicationCode();
        String string = providePrefs == null ? applicationCode : providePrefs.getString("appId", applicationCode);
        if (providePrefs != null) {
            providePrefs.edit().putString("appId", applicationCode).apply();
        }
        if (!string.equals(applicationCode)) {
            e.a();
        }
    }

    private static a b() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    if (e != null) {
                        c = new com.pushwoosh.inbox.f.c.a(e);
                        e = null;
                    } else {
                        throw new IllegalArgumentException("Incorrect state.");
                    }
                }
            }
        }
        return c;
    }
}
