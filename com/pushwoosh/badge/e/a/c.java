package com.pushwoosh.badge.e.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;
import com.pushwoosh.badge.e.a.d.a;
import com.pushwoosh.badge.e.a.d.b;
import com.pushwoosh.badge.e.a.d.d;
import com.pushwoosh.badge.e.a.d.e;
import com.pushwoosh.badge.e.a.d.f;
import com.pushwoosh.badge.e.a.d.g;
import com.pushwoosh.badge.e.a.d.h;
import com.pushwoosh.badge.e.a.d.i;
import com.pushwoosh.badge.e.a.d.j;
import com.pushwoosh.badge.e.a.d.k;
import com.pushwoosh.badge.e.a.d.l;
import com.pushwoosh.badge.e.a.d.m;
import com.pushwoosh.badge.e.a.d.n;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class c {
    private static final List<Class<? extends a>> a = new LinkedList();
    private static a b;
    private static ComponentName c;

    static {
        a.add(a.class);
        a.add(b.class);
        a.add(g.class);
        a.add(h.class);
        a.add(k.class);
        a.add(com.pushwoosh.badge.e.a.d.c.class);
        a.add(f.class);
        a.add(i.class);
        a.add(j.class);
        a.add(n.class);
        a.add(l.class);
        a.add(m.class);
        a.add(e.class);
    }

    private static boolean a(Context context) {
        a aVar;
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null) {
            Log.e("ShortcutBadger", "Unable to find launch intent for package " + context.getPackageName());
            return false;
        }
        c = launchIntentForPackage.getComponent();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveActivity == null || resolveActivity.activityInfo.name.toLowerCase().contains("resolver")) {
            return false;
        }
        String str = resolveActivity.activityInfo.packageName;
        Iterator<Class<? extends a>> it = a.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            try {
                aVar = (a) it.next().newInstance();
            } catch (Exception unused) {
                aVar = null;
            }
            if (aVar != null && aVar.a().contains(str)) {
                b = aVar;
                break;
            }
        }
        if (b != null) {
            return true;
        }
        b = Build.MANUFACTURER.equalsIgnoreCase("ZUK") ? new n() : Build.MANUFACTURER.equalsIgnoreCase("OPPO") ? new i() : Build.MANUFACTURER.equalsIgnoreCase("VIVO") ? new l() : Build.MANUFACTURER.equalsIgnoreCase("ZTE") ? new m() : new d();
        return true;
    }

    public static boolean a(Context context, int i) {
        try {
            b(context, i);
            return true;
        } catch (b e) {
            if (!Log.isLoggable("ShortcutBadger", 3)) {
                return false;
            }
            Log.d("ShortcutBadger", "Unable to execute badge", e);
            return false;
        }
    }

    public static void b(Context context, int i) throws b {
        if (b != null || a(context)) {
            try {
                b.a(context, c, i);
            } catch (Exception e) {
                throw new b("Unable to execute badge", e);
            }
        } else {
            throw new b("No default launcher available");
        }
    }
}
