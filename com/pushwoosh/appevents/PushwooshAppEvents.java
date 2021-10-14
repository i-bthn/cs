package com.pushwoosh.appevents;

import android.app.Application;
import com.pushwoosh.inapp.InAppManager;
import com.pushwoosh.internal.crash.LogSender;
import com.pushwoosh.internal.platform.AndroidPlatformModule;

public class PushwooshAppEvents {
    private static volatile Application.ActivityLifecycleCallbacks a;
    private static final Object b = new Object();

    private static Application.ActivityLifecycleCallbacks a() {
        synchronized (b) {
            if (a == null) {
                a = new a($$Lambda$PushwooshAppEvents$1OVEy5pHqMYi0KMnkDhDHRFqoiM.INSTANCE);
                Application application = (Application) AndroidPlatformModule.getApplicationContext();
                if (application == null) {
                    LogSender.submitCustomError(new Exception("AndroidPlatformModule.getApplicationContext() returned null in PushwooshAppEvents.registerActivityLifecycleCallbacks() method"));
                } else {
                    application.registerActivityLifecycleCallbacks(a);
                }
            }
        }
        return a;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004a  */
    public static /* synthetic */ void a(String str, String str2) {
        char c;
        String str3;
        InAppManager inAppManager;
        int hashCode = str.hashCode();
        if (hashCode != -1632974827) {
            if (hashCode != -1140992324) {
                if (hashCode == -794051143 && str.equals("ApplicationOpened")) {
                    c = 0;
                    if (c != 0) {
                        inAppManager = InAppManager.getInstance();
                        str3 = "_ApplicationOpened";
                    } else if (c == 1) {
                        inAppManager = InAppManager.getInstance();
                        str3 = "_ApplicationClosed";
                    } else if (c == 2) {
                        inAppManager = InAppManager.getInstance();
                        str3 = "_ScreenOpened";
                    } else {
                        return;
                    }
                    inAppManager.postEvent(str3);
                }
            } else if (str.equals("ApplicationClosed")) {
                c = 1;
                if (c != 0) {
                }
                inAppManager.postEvent(str3);
            }
        } else if (str.equals("ScreenOpened")) {
            c = 2;
            if (c != 0) {
            }
            inAppManager.postEvent(str3);
        }
        c = 65535;
        if (c != 0) {
        }
        inAppManager.postEvent(str3);
    }

    public static void init() {
        a();
    }
}
