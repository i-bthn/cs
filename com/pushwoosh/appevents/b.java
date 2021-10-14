package com.pushwoosh.appevents;

import android.app.Application;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.appevents.a;
import com.pushwoosh.inapp.InAppManager;
import com.pushwoosh.internal.crash.LogSender;
import com.pushwoosh.internal.event.ConfigLoadedEvent;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.a;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;
import com.pushwoosh.repository.config.c;
import com.pushwoosh.tags.TagsBundle;
import java.util.List;

public class b {
    private static volatile Application.ActivityLifecycleCallbacks d;
    private static final Object e = new Object();
    private boolean a = false;
    private EventListener<a.d> b = new EventListener() {
        /* class com.pushwoosh.appevents.$$Lambda$b$LPAgY_UetKoLE9ttqor3AJIpzY */

        @Override // com.pushwoosh.internal.event.EventListener
        public final void onReceive(Event event) {
            b.this.a((b) ((a.d) event));
        }
    };
    private EventListener<ConfigLoadedEvent> c = new EventListener() {
        /* class com.pushwoosh.appevents.$$Lambda$b$NPQ3HgSFUH832th1YIIiFDdxyO8 */

        @Override // com.pushwoosh.internal.event.EventListener
        public final void onReceive(Event event) {
            b.this.a((b) ((ConfigLoadedEvent) event));
        }
    };

    static TagsBundle a(String str, String str2) {
        TagsBundle.Builder builder = new TagsBundle.Builder();
        builder.putInt("device_type", DeviceSpecificProvider.getInstance().deviceType());
        builder.putString("application_version", AndroidPlatformModule.getAppInfoProvider().d());
        if (str.equals("PW_ScreenOpen")) {
            builder.putString("screen_name", str2);
        }
        return builder.build();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(ConfigLoadedEvent configLoadedEvent) {
        EventBus.unsubscribe(ConfigLoadedEvent.class, this.c);
        this.a = true;
        a("PW_ApplicationOpen", a("PW_ApplicationOpen", ""));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(a.d dVar) {
        EventBus.unsubscribe(a.d.class, this.b);
        b();
    }

    private void b() {
        synchronized (e) {
            if (d == null) {
                Application application = (Application) AndroidPlatformModule.getApplicationContext();
                if (application == null) {
                    EventBus.subscribe(a.d.class, this.b);
                    LogSender.submitCustomError(new Exception("AndroidPlatformModule.getApplicationContext() returned null in PushwooshDefaultEvents.registerActivityLifecycleCallbacks() method"));
                } else {
                    d = new a(new a.c() {
                        /* class com.pushwoosh.appevents.$$Lambda$b$_tGiBgAJvi64dVQppx65wxdttcw */

                        @Override // com.pushwoosh.appevents.a.c
                        public final void a(String str, String str2) {
                            b.this.b(str, str2);
                        }
                    });
                    application.registerActivityLifecycleCallbacks(d);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004e  */
    public /* synthetic */ void b(String str, String str2) {
        char c2;
        String str3;
        TagsBundle tagsBundle;
        int hashCode = str.hashCode();
        if (hashCode != -1632974827) {
            if (hashCode != -1140992324) {
                if (hashCode == -794051143 && str.equals("ApplicationOpened")) {
                    c2 = 0;
                    if (c2 != 0) {
                        tagsBundle = a("PW_ApplicationOpen", str2);
                        str3 = "PW_ApplicationOpen";
                    } else if (c2 == 1) {
                        tagsBundle = a("PW_ApplicationMinimized", str2);
                        str3 = "PW_ApplicationMinimized";
                    } else if (c2 == 2) {
                        tagsBundle = a("PW_ScreenOpen", str2);
                        str3 = "PW_ScreenOpen";
                    } else {
                        return;
                    }
                    a(str3, tagsBundle);
                }
            } else if (str.equals("ApplicationClosed")) {
                c2 = 1;
                if (c2 != 0) {
                }
                a(str3, tagsBundle);
            }
        } else if (str.equals("ScreenOpened")) {
            c2 = 2;
            if (c2 != 0) {
            }
            a(str3, tagsBundle);
        }
        c2 = 65535;
        if (c2 != 0) {
        }
        a(str3, tagsBundle);
    }

    public void a() {
        EventBus.subscribe(ConfigLoadedEvent.class, this.c);
        b();
    }

    /* access modifiers changed from: package-private */
    public void a(String str, TagsBundle tagsBundle) {
        List<c> c2 = PushwooshPlatform.getInstance().o().c();
        if (c2 != null && this.a) {
            for (c cVar : c2) {
                if (cVar.a().equals(str)) {
                    InAppManager.getInstance().postEvent(str, tagsBundle);
                    return;
                }
            }
        }
    }
}
