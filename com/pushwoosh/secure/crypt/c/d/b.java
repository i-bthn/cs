package com.pushwoosh.secure.crypt.c.d;

import android.content.Context;
import android.os.Build;
import com.pushwoosh.secure.crypt.c.d.a.a;
import com.pushwoosh.secure.crypt.c.d.a.d;
import com.pushwoosh.secure.crypt.d.c;

public abstract class b {
    private static a a(Context context, String str) {
        com.pushwoosh.secure.crypt.d.b a = c.a(context, str);
        a dVar = (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 23) ? Build.VERSION.SDK_INT >= 23 ? new d(str, a) : c(context, str, "RSA") : new com.pushwoosh.secure.crypt.c.d.a.c(context, str, a);
        return new c(dVar.b(), dVar.a());
    }

    public static a a(Context context, String str, String str2) {
        return ((str2.hashCode() == 81440 && str2.equals("RSA")) ? (char) 0 : 65535) != 0 ? b(context, str, str2) : a(context, str);
    }

    public static a b(Context context, String str, String str2) {
        a c = c(context, str, str2);
        return new c(c.b(), c.a());
    }

    private static a c(Context context, String str, String str2) {
        com.pushwoosh.secure.crypt.d.b a = c.a(context, str);
        if (((str2.hashCode() == 81440 && str2.equals("RSA")) ? (char) 0 : 65535) == 0) {
            return new com.pushwoosh.secure.crypt.c.d.a.b(a);
        }
        throw new IllegalArgumentException("Not supported default keys provider for algorithm " + str2);
    }
}
