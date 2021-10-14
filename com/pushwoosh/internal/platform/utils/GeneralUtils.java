package com.pushwoosh.internal.platform.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.TypedValue;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RepositoryModule;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Locale;

public class GeneralUtils {
    public static final String SDK_VERSION = "6.2.8";
    private static final String[] a = {".mp3", ".3gp", ".mp4", ".m4a", ".aac", ".flac", ".ogg", ".wav"};

    private static boolean a(String str) {
        for (String str2 : a) {
            if (str.toLowerCase(Locale.US).endsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    public static void checkNotNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(String.format("Please set the %1$s constant and recompile the app.", str));
        }
    }

    public static void checkNotNullOrEmpty(String str, String str2) {
        checkNotNull(str, str2);
        if (str.length() == 0) {
            throw new IllegalArgumentException(String.format("Please set the %1$s constant and recompile the app.", str2));
        }
    }

    public static boolean checkStickyBroadcastPermissions(Context context) {
        try {
            return context.getPackageManager().checkPermission("android.permission.BROADCAST_STICKY", context.getPackageName()) == 0;
        } catch (Exception e) {
            PWLog.error("error in checking broadcast_sticky permission", e);
            return false;
        }
    }

    public static int getAppVersion() {
        return AndroidPlatformModule.getAppInfoProvider().f();
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x005a */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0064 A[Catch:{ IOException -> 0x0095, Exception -> 0x0099 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0065 A[Catch:{ IOException -> 0x0095, Exception -> 0x0099 }] */
    public static ArrayList<String> getRawResourses() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Field field : Class.forName(AndroidPlatformModule.getAppInfoProvider().a() + ".R$raw").getFields()) {
            String name = field.getName();
            int a2 = AndroidPlatformModule.getResourceProvider().a(name, "raw");
            TypedValue typedValue = new TypedValue();
            AndroidPlatformModule.getResourceProvider().a(a2, typedValue, true);
            if (a(typedValue.string.toString())) {
                arrayList.add(name);
            }
        }
        try {
            AssetManager assets = AndroidPlatformModule.getManagerProvider().getAssets();
            if (assets != null) {
                return arrayList;
            }
            String[] list = assets.list("www/res");
            for (String str : list) {
                if (assets.list("www/res/" + str).length == 0) {
                    if (a(str)) {
                        arrayList.add(str);
                    }
                }
            }
            return arrayList;
        } catch (IOException e) {
            PWLog.exception(e);
        } catch (Exception unused) {
        }
    }

    public static String getSenderId() {
        String o = PushwooshPlatform.getInstance().c().o();
        return (o == null || o.isEmpty()) ? RepositoryModule.getRegistrationPreferences().projectId().get() : o;
    }

    public static boolean isMainActivity(Activity activity) {
        ComponentName component;
        String str;
        PackageManager packageManager = activity.getPackageManager();
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(activity.getPackageName());
        if (launchIntentForPackage == null || (component = launchIntentForPackage.getComponent()) == null) {
            return false;
        }
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(component, 0);
            str = activityInfo.targetActivity != null ? activityInfo.targetActivity : component.getClassName();
        } catch (PackageManager.NameNotFoundException unused) {
            str = component.getClassName();
        }
        return TextUtils.equals(str, activity.getClass().getName());
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = AndroidPlatformModule.getManagerProvider().getConnectivityManager();
        NetworkInfo activeNetworkInfo = connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
    }

    public static boolean isStoreApp() {
        return !TextUtils.isEmpty(AndroidPlatformModule.getAppInfoProvider().g());
    }

    public static String md5(String str) {
        if (str == null) {
            return "";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x", Byte.valueOf(digest[i])));
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static int parseColor(String str) {
        StringBuilder sb;
        try {
            if (!str.startsWith("#") || !(str.length() == 7 || str.length() == 9)) {
                if (str.startsWith("#") && str.length() == 4) {
                    char[] charArray = str.toCharArray();
                    sb = new StringBuilder();
                    sb.append("#");
                    sb.append(charArray[1]);
                    sb.append(charArray[1]);
                    sb.append(charArray[2]);
                    sb.append(charArray[2]);
                    sb.append(charArray[3]);
                    sb.append(charArray[3]);
                } else if (!str.startsWith("#") || str.length() != 5) {
                    String[] split = str.split(",");
                    return Color.argb(Integer.parseInt(split[3]), Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                } else {
                    char[] charArray2 = str.toCharArray();
                    sb = new StringBuilder();
                    sb.append("#");
                    sb.append(charArray2[1]);
                    sb.append(charArray2[1]);
                    sb.append(charArray2[2]);
                    sb.append(charArray2[2]);
                    sb.append(charArray2[3]);
                    sb.append(charArray2[3]);
                    sb.append(charArray2[4]);
                    sb.append(charArray2[4]);
                }
                str = sb.toString();
            }
            return Color.parseColor(str);
        } catch (Exception e) {
            PWLog.exception(e);
            return -1;
        }
    }
}
