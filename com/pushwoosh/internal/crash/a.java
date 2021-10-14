package com.pushwoosh.internal.crash;

import com.pushwoosh.internal.utils.PWLog;

/* access modifiers changed from: package-private */
public class a {
    public static void a(String str) {
        PWLog.debug("CrashAnalytics", str);
    }

    public static void a(String str, Throwable th) {
        PWLog.error("CrashAnalytics", str, th);
    }

    public static void b(String str) {
        PWLog.error("CrashAnalytics", str);
    }
}
