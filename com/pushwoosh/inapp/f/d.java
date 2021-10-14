package com.pushwoosh.inapp.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.core.app.NotificationManagerCompat;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.inapp.f.a;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.c.a;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.b;
import com.pushwoosh.internal.utils.h;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class d {
    public static final String d = "d";
    private Map<String, a> a;
    private SharedPreferences b;
    private HandlerThread c = new HandlerThread("BusinessCasesThread");

    public d(PrefsProvider prefsProvider, a aVar, h hVar, b bVar) {
        float a2 = a(aVar);
        this.c.start();
        this.b = prefsProvider.providePrefs("PWBusinessCasesState");
        if (this.b != null) {
            this.a = new HashMap();
            Map<String, a> map = this.a;
            SharedPreferences sharedPreferences = this.b;
            bVar.getClass();
            map.put("welcome-inapp", new a("welcome-inapp", 0.0f, sharedPreferences, new a.d() {
                /* class com.pushwoosh.inapp.f.$$Lambda$GfYqcF4jIzvAL5BRlNPHyj1TsQ */

                @Override // com.pushwoosh.inapp.f.a.d
                public final boolean check() {
                    return b.this.c();
                }
            }, hVar));
            Map<String, a> map2 = this.a;
            SharedPreferences sharedPreferences2 = this.b;
            bVar.getClass();
            map2.put("app-update-message", new a("app-update-message", 0.0f, sharedPreferences2, new a.d() {
                /* class com.pushwoosh.inapp.f.$$Lambda$yVJ0eOx_18dW2O4JVpiP_phbtcM */

                @Override // com.pushwoosh.inapp.f.a.d
                public final boolean check() {
                    return b.this.b();
                }
            }, hVar));
            this.a.put("push-unregister", new a("push-unregister", a2, this.b, new a.d() {
                /* class com.pushwoosh.inapp.f.$$Lambda$d$SNzRbpwazTqIpVvTFAgl7mbM1Yk */

                @Override // com.pushwoosh.inapp.f.a.d
                public final boolean check() {
                    return d.this.b();
                }
            }, hVar));
        }
    }

    private float a(com.pushwoosh.internal.platform.c.a aVar) {
        Object obj;
        float f;
        Bundle bundle = aVar.h().metaData;
        if (bundle == null || (obj = bundle.get("com.pushwoosh.in_app_business_solutions_capping")) == null) {
            return 1.0f;
        }
        if (obj instanceof Integer) {
            f = ((Integer) obj).floatValue();
        } else if (obj instanceof Float) {
            f = ((Float) obj).floatValue();
        } else {
            PWLog.error(d, "wrong format capping, capping must be positive number");
            f = 1.0f;
        }
        if (f < 0.0f) {
            PWLog.error(d, "wrong format capping, capping must be positive number");
            f = 1.0f;
        }
        String str = d;
        PWLog.noise(str, "set Up capping:" + f);
        return f;
    }

    public static void a(List<com.pushwoosh.inapp.j.l.b> list) {
        try {
            HashMap hashMap = new HashMap();
            for (com.pushwoosh.inapp.j.l.b bVar : list) {
                if (bVar.b() != null && !bVar.b().isEmpty()) {
                    hashMap.put(bVar.b(), b.a(bVar));
                }
            }
            PushwooshPlatform.getInstance().b().a((Map<String, b>) hashMap, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                HashMap hashMap = new HashMap();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, b.a(jSONObject.optJSONObject(next)));
                }
                PushwooshPlatform.getInstance().b().a((Map<String, b>) hashMap, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean b() {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        return (applicationContext == null || NotificationManagerCompat.from(applicationContext).areNotificationsEnabled() || PushwooshPlatform.getInstance().notificationManager().c() == null) ? false : true;
    }

    public void a() {
        this.b.edit().clear().apply();
    }

    public void a(String str, a.b bVar) {
        a aVar = this.a.get(str);
        if (aVar != null) {
            new Handler(this.c.getLooper()).post(new Runnable(bVar) {
                /* class com.pushwoosh.inapp.f.$$Lambda$d$qXeNujlxg9AY8gOkFn55eizhp0E */
                private final /* synthetic */ a.b f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    a.this.a((a) this.f$1);
                }
            });
        }
    }

    public void a(Map<String, b> map, boolean z) {
        com.pushwoosh.inapp.k.d d2;
        com.pushwoosh.inapp.j.l.b a2;
        for (a aVar : this.a.values()) {
            b bVar = map.get(aVar.a());
            if (bVar != null && (z || !((d2 = com.pushwoosh.inapp.b.d()) == null || (a2 = d2.a(bVar.a())) == null || a2.i() != bVar.b()))) {
                aVar.a(bVar.a());
            }
        }
    }
}
