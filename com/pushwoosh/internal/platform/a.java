package com.pushwoosh.internal.platform;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import java.util.Date;

public class a {
    private Date a;
    private final Application b;

    /* access modifiers changed from: package-private */
    /* renamed from: com.pushwoosh.internal.platform.a$a  reason: collision with other inner class name */
    public class C0020a implements Application.ActivityLifecycleCallbacks {
        private int a;
        private int b;

        C0020a() {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (a.this.a != null) {
                if (new Date().getTime() - a.this.a.getTime() >= 60000) {
                    EventBus.sendEvent(new d());
                }
                a.this.a = null;
            } else if (this.a == 0) {
                EventBus.sendEvent(new d());
            }
            this.a++;
        }

        public void onActivityDestroyed(Activity activity) {
            this.a--;
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            if (this.b == 0) {
                EventBus.sendEvent(new c());
            }
            this.b++;
        }

        public void onActivityStopped(Activity activity) {
            this.b--;
            if (this.b == 0) {
                EventBus.sendEvent(new b());
            }
        }
    }

    public static class b implements Event {
    }

    public static class c implements Event {
    }

    public static class d implements Event {
        d() {
        }
    }

    a(Context context) {
        this.b = (Application) context.getApplicationContext();
    }

    public void a(boolean z) {
        if (z) {
            EventBus.sendEvent(new d());
            this.a = new Date();
        }
        this.b.registerActivityLifecycleCallbacks(new C0020a());
    }
}
