package com.pushwoosh.notification;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import com.pushwoosh.internal.utils.JsonUtils;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.e;
import com.pushwoosh.repository.RepositoryModule;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class b {
    private static final int a = 0;
    private static final int b = 1;

    static {
        int i = Build.VERSION.SDK_INT;
        int i2 = Build.VERSION.SDK_INT;
    }

    public static boolean A(Bundle bundle) {
        String string = bundle.getString("vib");
        return !TextUtils.isEmpty(string) && string.equals("1");
    }

    public static int B(Bundle bundle) {
        int a2 = a(bundle, "visibility", b);
        if (Math.abs(a2) <= 1) {
            return a2;
        }
        PWLog.warn("Unsupported visibility: " + a2 + ", setting to default: 1");
        return b;
    }

    public static boolean C(Bundle bundle) {
        return bundle.getBoolean(ImagesContract.LOCAL, false);
    }

    public static boolean D(Bundle bundle) {
        return a(bundle, "pw_lockscreen");
    }

    public static boolean E(Bundle bundle) {
        return a(bundle, NotificationCompat.GROUP_KEY_SILENT) || a(bundle, "pw_silent");
    }

    public static boolean F(Bundle bundle) {
        return a(bundle, 2);
    }

    public static boolean G(Bundle bundle) {
        return a(bundle, 1);
    }

    private static int a(Bundle bundle, String str, int i) {
        String string = bundle.getString(str);
        if (TextUtils.isEmpty(string)) {
            return (string == null || !string.isEmpty()) ? bundle.getInt(str, i) : i;
        }
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException unused) {
            PWLog.error("ERROR! Incorrect format for key [ " + str + " ]: " + string);
            return i;
        }
    }

    @NonNull
    public static JSONObject a(Bundle bundle) {
        return JsonUtils.bundleToJsonWithUserData(bundle);
    }

    public static void a(@NonNull Bundle bundle, boolean z) {
        bundle.putBoolean("foreground", z);
        bundle.putBoolean("onStart", !z);
    }

    private static boolean a(Bundle bundle, int i) {
        return a(bundle, "pw_msg", 0) == i;
    }

    private static boolean a(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (TextUtils.equals(string, "true")) {
            return true;
        }
        try {
            return Integer.parseInt(string) > 0;
        } catch (Exception unused) {
            return false;
        }
    }

    @NonNull
    public static Collection<Action> b(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        try {
            String string = bundle.getString("pw_actions");
            if (string != null) {
                JSONArray jSONArray = new JSONArray(string);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(new Action(jSONArray.getJSONObject(i)));
                }
            }
        } catch (JSONException e) {
            PWLog.exception(e);
        }
        return arrayList;
    }

    public static void b(Bundle bundle, String str) {
        bundle.putString("b", str);
    }

    public static void b(@NonNull Bundle bundle, boolean z) {
        bundle.putString("pw_msg", "1");
        bundle.putBoolean(ImagesContract.LOCAL, z);
    }

    @Nullable
    public static String c(Bundle bundle) {
        return bundle.getString("packs");
    }

    public static void c(Bundle bundle, String str) {
        bundle.putString("ci", str);
    }

    public static int d(Bundle bundle) {
        return a(bundle, "pw_badges", -1);
    }

    public static void d(Bundle bundle, String str) {
        bundle.putString("l", str);
    }

    @Nullable
    public static String e(Bundle bundle) {
        return bundle.getString("b");
    }

    public static void e(Bundle bundle, String str) {
        bundle.putString("title", str);
    }

    @Nullable
    public static String f(Bundle bundle) {
        return bundle.getString("u");
    }

    public static void f(Bundle bundle, String str) {
        bundle.putString("i", str);
    }

    public static String g(Bundle bundle) {
        String str = (String) bundle.get("header");
        if (str != null) {
            return str;
        }
        CharSequence e = AndroidPlatformModule.getAppInfoProvider().e();
        if (e == null) {
            e = "";
        }
        return e.toString();
    }

    public static void g(Bundle bundle, String str) {
        bundle.putString("pw_msg_tag", str);
    }

    @Nullable
    public static String h(Bundle bundle) {
        return bundle.getString("h");
    }

    @NonNull
    public static Integer i(Bundle bundle) {
        String string = bundle.getString("ibc");
        return Integer.valueOf(string != null ? Color.parseColor(string) : RepositoryModule.getNotificationPreferences().c().get());
    }

    @Nullable
    public static String j(Bundle bundle) {
        return bundle.getString("pw_command");
    }

    @Nullable
    public static String k(Bundle bundle) {
        return bundle.getString("ci");
    }

    @Nullable
    public static Integer l(Bundle bundle) {
        String string = bundle.getString("led");
        if (string != null) {
            return Integer.valueOf(GeneralUtils.parseColor(string));
        }
        return null;
    }

    public static int m(Bundle bundle) {
        return a(bundle, "led_off_ms", 1000);
    }

    public static int n(Bundle bundle) {
        return a(bundle, "led_on_ms", 100);
    }

    @Nullable
    public static String o(Bundle bundle) {
        return bundle.getString("l");
    }

    @Nullable
    public static String p(Bundle bundle) {
        return bundle.getString("title");
    }

    @Nullable
    public static String q(Bundle bundle) {
        return bundle.getString("pw_msg_tag");
    }

    @Nullable
    public static String r(Bundle bundle) {
        return bundle.getString("pw_channel");
    }

    public static int s(Bundle bundle) {
        int a2 = a(bundle, "pri", a);
        if (Math.abs(a2) <= 2) {
            return a2;
        }
        PWLog.warn("Unsupported priority: " + a2 + ", setting to default: 0");
        return a;
    }

    @Nullable
    public static String t(Bundle bundle) {
        return bundle.getString("p");
    }

    @Nullable
    public static String u(Bundle bundle) {
        return bundle.getString("md");
    }

    @Nullable
    public static String v(Bundle bundle) {
        return bundle.getString("r");
    }

    @Nullable
    public static String w(Bundle bundle) {
        return bundle.getString("rm");
    }

    public static int x(Bundle bundle) {
        return e.d(bundle.getString("i"));
    }

    @Nullable
    public static String y(Bundle bundle) {
        return bundle.getString("s");
    }

    @Nullable
    public static String z(Bundle bundle) {
        return bundle.getString("value");
    }
}
