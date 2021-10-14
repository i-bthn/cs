package com.pushwoosh.badge.e.a.d;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.pushwoosh.badge.e.a.a;
import com.pushwoosh.badge.e.a.e.b;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import org.apache.cordova.globalization.Globalization;

public class i implements a {
    private static int a = -1;

    private Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    private Object a(Class cls, String str, Class[] clsArr, Object[] objArr) {
        Method a2;
        if (cls == null || a((Object) str) || (a2 = a(cls, str, clsArr)) == null) {
            return null;
        }
        a2.setAccessible(true);
        try {
            return a2.invoke(null, objArr);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:4:0x000a */
    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Class */
    /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: java.lang.Class */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.reflect.Method] */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:7|8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        if (r3.getSuperclass() != null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return a(r3.getSuperclass(), r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
        return r3.getMethod(r4, r5);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0015 */
    private Method a(Class cls, String str, Class[] clsArr) {
        if (cls == 0 || a((Object) str)) {
            return null;
        }
        cls.getMethods();
        cls.getDeclaredMethods();
        cls = cls.getDeclaredMethod(str, clsArr);
        return cls;
    }

    private boolean a(Object obj) {
        return obj == null || obj.toString().equals("") || obj.toString().trim().equals("null");
    }

    private int b() {
        int i;
        int i2 = a;
        if (i2 >= 0) {
            return i2;
        }
        try {
            i = ((Integer) a(a("com.color.os.ColorBuild"), "getColorOSVERSION", null, null)).intValue();
        } catch (Exception unused) {
            i = 0;
        }
        if (i == 0) {
            try {
                String b = b("ro.build.version.opporom");
                if (b.startsWith("V1.4")) {
                    return 3;
                }
                if (b.startsWith("V2.0")) {
                    return 4;
                }
                if (b.startsWith("V2.1")) {
                    return 5;
                }
            } catch (Exception unused2) {
            }
        }
        a = i;
        return a;
    }

    private String b(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                b.a(bufferedReader);
                return readLine;
            } catch (IOException unused) {
                b.a(bufferedReader);
                return null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
                b.a(bufferedReader2);
                throw th;
            }
        } catch (IOException unused2) {
            bufferedReader = null;
            b.a(bufferedReader);
            return null;
        } catch (Throwable th3) {
            th = th3;
            b.a(bufferedReader2);
            throw th;
        }
    }

    @Override // com.pushwoosh.badge.e.a.a
    public List<String> a() {
        return Collections.singletonList("com.oppo.launcher");
    }

    @Override // com.pushwoosh.badge.e.a.a
    @TargetApi(11)
    public void a(Context context, ComponentName componentName, int i) throws com.pushwoosh.badge.e.a.b {
        if (i == 0) {
            i = -1;
        }
        Intent intent = new Intent("com.oppo.unsettledevent");
        intent.putExtra("pakeageName", componentName.getPackageName());
        intent.putExtra(Globalization.NUMBER, i);
        intent.putExtra("upgradeNumber", i);
        if (com.pushwoosh.badge.e.a.e.a.a(context, intent)) {
            context.sendBroadcast(intent);
        } else if (b() == 6) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("app_badge_count", i);
                context.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", (String) null, bundle);
            } catch (Throwable unused) {
                throw new com.pushwoosh.badge.e.a.b("unable to resolve intent: " + intent.toString());
            }
        }
    }
}
