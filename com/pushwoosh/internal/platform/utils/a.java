package com.pushwoosh.internal.platform.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.WorkRequest;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.PushwooshSharedDataProvider;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.utils.a;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.RepositoryModule;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class a {
    private static g a = new d();
    private static g b = new b();
    private static g c = new f();
    private static g d = new c();
    private static g e = new e();

    /* access modifiers changed from: private */
    public static class b extends g {
        private b() {
        }

        /* access modifiers changed from: protected */
        @Override // com.pushwoosh.internal.platform.utils.a.g
        public String b() {
            return AndroidPlatformModule.getAppInfoProvider().b();
        }
    }

    /* access modifiers changed from: private */
    public static class c extends g {
        private c() {
        }

        /* access modifiers changed from: protected */
        @Override // com.pushwoosh.internal.platform.utils.a.g
        public String b() {
            RegistrationPrefs registrationPreferences = RepositoryModule.getRegistrationPreferences();
            String str = registrationPreferences.deviceId().get();
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            String uuid = UUID.randomUUID().toString();
            registrationPreferences.deviceId().set(uuid);
            return uuid;
        }
    }

    /* access modifiers changed from: private */
    public static class d extends g {
        private d() {
        }

        /* access modifiers changed from: protected */
        @Override // com.pushwoosh.internal.platform.utils.a.g
        public String b() {
            if (Build.VERSION.SDK_INT >= 28) {
                return "";
            }
            String str = Build.SERIAL;
            return TextUtils.equals("unknown", str) ? "" : str;
        }
    }

    /* access modifiers changed from: private */
    public static class e extends g {
        private CountDownLatch d;
        private b e;

        /* access modifiers changed from: private */
        /* renamed from: com.pushwoosh.internal.platform.utils.a$e$a  reason: collision with other inner class name */
        public interface AbstractC0022a {
            void a(String str);
        }

        /* access modifiers changed from: private */
        public static class b extends AsyncTask<Void, Void, String> {
            private final List<AbstractC0022a> a;

            private b() {
                this.a = new ArrayList();
            }

            private void b(String str) {
                ArrayList<AbstractC0022a> arrayList;
                if (TextUtils.isEmpty(str)) {
                    str = UUID.randomUUID().toString();
                }
                RepositoryModule.getRegistrationPreferences().deviceId().set(str);
                synchronized (this.a) {
                    arrayList = new ArrayList(this.a);
                }
                for (AbstractC0022a aVar : arrayList) {
                    aVar.a(str);
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public String doInBackground(Void... voidArr) {
                return e.d();
            }

            /* access modifiers changed from: package-private */
            public void a(AbstractC0022a aVar) {
                synchronized (this.a) {
                    this.a.add(aVar);
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(String str) {
                super.onPostExecute(str);
                b(str);
            }

            /* access modifiers changed from: protected */
            public void onCancelled() {
                super.onCancelled();
                b(null);
            }
        }

        private e() {
            this.d = new CountDownLatch(1);
        }

        private static List<ProviderInfo> a(List<ProviderInfo> list) {
            String e2 = PushwooshPlatform.getInstance().c().e();
            if (TextUtils.isEmpty(e2)) {
                return list;
            }
            ArrayList arrayList = new ArrayList();
            for (ProviderInfo providerInfo : list) {
                if (!TextUtils.isEmpty(providerInfo.packageName) && providerInfo.packageName.contains(e2)) {
                    arrayList.add(providerInfo);
                }
            }
            return arrayList;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(Handler handler, g.AbstractC0023a aVar, String str) {
            handler.removeCallbacksAndMessages(null);
            if (aVar != null) {
                aVar.a(str);
            }
            this.d.countDown();
            this.e = null;
        }

        /* access modifiers changed from: private */
        public static String d() {
            String str;
            Exception e2;
            List<ProviderInfo> a;
            try {
                Context applicationContext = AndroidPlatformModule.getApplicationContext();
                if (!(applicationContext == null || (a = a(applicationContext.getPackageManager().queryContentProviders(null, 0, 0))) == null)) {
                    if (a.size() != 0) {
                        String str2 = applicationContext.getPackageName() + "." + PushwooshSharedDataProvider.class.getSimpleName();
                        for (ProviderInfo providerInfo : a) {
                            if (providerInfo.authority.endsWith("." + PushwooshSharedDataProvider.class.getSimpleName()) && !providerInfo.authority.equals(str2)) {
                                Cursor query = applicationContext.getContentResolver().query(Uri.parse("content://" + providerInfo.authority + "/" + "hwid"), null, null, null, GeneralUtils.md5(applicationContext.getPackageName()));
                                if (query == null) {
                                    continue;
                                } else {
                                    if (query.getColumnCount() > 0 && query.getColumnName(0).equals("hwid") && query.moveToFirst()) {
                                        str = query.getString(0);
                                        if (!TextUtils.isEmpty(str)) {
                                            try {
                                                query.close();
                                                return str;
                                            } catch (Exception e3) {
                                                e2 = e3;
                                            }
                                        }
                                    }
                                    query.close();
                                }
                            }
                        }
                        return null;
                    }
                }
                return null;
            } catch (Exception e4) {
                str = null;
                e2 = e4;
                e2.printStackTrace();
                return str;
            }
        }

        private boolean e() {
            b bVar = this.e;
            return (bVar == null || bVar.getStatus() == AsyncTask.Status.FINISHED) ? false : true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void f() {
            b bVar = this.e;
            if (bVar != null && bVar.getStatus() != AsyncTask.Status.FINISHED) {
                this.e.cancel(true);
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.pushwoosh.internal.platform.utils.a.g
        public void a(g.AbstractC0023a aVar) {
            if (!e()) {
                Handler handler = new Handler();
                $$Lambda$a$e$6hzp6k1wnSPOuh3xzA139dwgRgU r1 = new AbstractC0022a(handler, aVar) {
                    /* class com.pushwoosh.internal.platform.utils.$$Lambda$a$e$6hzp6k1wnSPOuh3xzA139dwgRgU */
                    private final /* synthetic */ Handler f$1;
                    private final /* synthetic */ a.g.AbstractC0023a f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    @Override // com.pushwoosh.internal.platform.utils.a.e.AbstractC0022a
                    public final void a(String str) {
                        a.e.this.a(this.f$1, this.f$2, str);
                    }
                };
                try {
                    String str = RepositoryModule.getRegistrationPreferences().deviceId().get();
                    if (!TextUtils.isEmpty(str)) {
                        r1.a(str);
                        return;
                    }
                    this.e = new b();
                    this.e.a(r1);
                    handler.postDelayed(new Runnable() {
                        /* class com.pushwoosh.internal.platform.utils.$$Lambda$a$e$WqI1C9TNYl5jqOaztQiMkU_lLGk */

                        public final void run() {
                            a.e.this.f();
                        }
                    }, WorkRequest.MIN_BACKOFF_MILLIS);
                    this.e.execute(new Void[0]);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    r1.a(null);
                }
            } else if (aVar != null) {
                b bVar = this.e;
                aVar.getClass();
                bVar.a(new AbstractC0022a() {
                    /* class com.pushwoosh.internal.platform.utils.$$Lambda$C7CO_EamgmKM_1PKDIcRxcwN7hw */

                    @Override // com.pushwoosh.internal.platform.utils.a.e.AbstractC0022a
                    public final void a(String str) {
                        a.g.AbstractC0023a.this.a(str);
                    }
                });
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.pushwoosh.internal.platform.utils.a.g
        public String b() {
            RegistrationPrefs registrationPreferences = RepositoryModule.getRegistrationPreferences();
            if (!e()) {
                a((g.AbstractC0023a) null);
            }
            try {
                this.d.await();
                return registrationPreferences.deviceId().get();
            } catch (InterruptedException unused) {
                return null;
            }
        }
    }

    /* access modifiers changed from: private */
    public static class f extends g {
        private f() {
        }

        /* access modifiers changed from: protected */
        @Override // com.pushwoosh.internal.platform.utils.a.g
        @SuppressLint({"MissingPermission", "HardwareIds"})
        public String b() {
            try {
                TelephonyManager telephonyManager = AndroidPlatformModule.getManagerProvider().getTelephonyManager();
                return telephonyManager != null ? telephonyManager.getDeviceId() : "";
            } catch (RuntimeException e) {
                PWLog.error("DeviceTelephonyUUID", e);
                return "";
            }
        }
    }

    public static abstract class g {
        private static List<String> c = new ArrayList();
        String a = null;
        g b;

        /* access modifiers changed from: protected */
        /* renamed from: com.pushwoosh.internal.platform.utils.a$g$a  reason: collision with other inner class name */
        public interface AbstractC0023a {
            void a(String str);
        }

        g() {
            c.add("9774d56d682e549c");
            c.add("1234567");
            c.add("abcdef");
            c.add("dead00beef");
            c.add("unknown");
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(b bVar, String str) {
            String str2;
            g gVar;
            if (!a(str) || (gVar = this.b) == null) {
                this.a = str;
                str2 = this.a;
            } else {
                str2 = gVar.a();
            }
            bVar.a(str2);
        }

        private boolean a(String str) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str.replace('0', ' ').replace('-', ' ').trim())) {
                return true;
            }
            return c.contains(str.toLowerCase());
        }

        public String a() {
            synchronized (this) {
                if (this.a != null) {
                    return this.a;
                }
                String b2 = b();
                if (!a(b2) || this.b == null) {
                    this.a = b2;
                    return b2;
                }
                return this.b.a();
            }
        }

        /* access modifiers changed from: protected */
        public void a(AbstractC0023a aVar) {
            aVar.a(b());
        }

        /* access modifiers changed from: package-private */
        public void a(g gVar) {
            this.b = gVar;
        }

        public void a(b bVar) {
            synchronized (this) {
                if (this.a != null) {
                    bVar.a(this.a);
                } else {
                    a(new AbstractC0023a(bVar) {
                        /* class com.pushwoosh.internal.platform.utils.$$Lambda$a$g$jJbze_vZWHGJSrcnQJo_1EOhrBg */
                        private final /* synthetic */ b f$1;

                        {
                            this.f$1 = r2;
                        }

                        @Override // com.pushwoosh.internal.platform.utils.a.g.AbstractC0023a
                        public final void a(String str) {
                            a.g.this.a(this.f$1, str);
                        }
                    });
                }
            }
        }

        /* access modifiers changed from: protected */
        public abstract String b();
    }

    static {
        q();
    }

    private static String a(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char charAt = str.charAt(0);
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        return Character.toUpperCase(charAt) + str.substring(1);
    }

    public static void a(@NonNull b bVar) {
        e.a(bVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0030  */
    private static boolean a() {
        Process process;
        boolean z = false;
        try {
            process = new ProcessBuilder("/system/xbin/which", "su").start();
            try {
                if (new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null) {
                    z = true;
                }
                if (process != null) {
                    process.destroy();
                }
                return z;
            } catch (Throwable unused) {
                if (process != null) {
                }
                return false;
            }
        } catch (Throwable unused2) {
            process = null;
            if (process != null) {
                process.destroy();
            }
            return false;
        }
    }

    private static boolean b() {
        for (String str : System.getenv("PATH").split(":")) {
            if (new File(str, "su").exists()) {
                return true;
            }
        }
        return false;
    }

    public static long c() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static long d() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static float e() {
        try {
            Intent a2 = AndroidPlatformModule.getReceiverProvider().a((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (a2 == null) {
                return -1.0f;
            }
            int intExtra = a2.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1);
            int intExtra2 = a2.getIntExtra("scale", -1);
            if (intExtra == -1 || intExtra2 == -1) {
                return -1.0f;
            }
            return (((float) intExtra) / ((float) intExtra2)) * 100.0f;
        } catch (Exception unused) {
            return -1.0f;
        }
    }

    public static String f() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return a(str2);
        }
        return a(str) + " " + str2;
    }

    public static String g() {
        return e.a();
    }

    public static String h() {
        return b.a();
    }

    @Nullable
    public static String i() {
        return RepositoryModule.getRegistrationPreferences().deviceId().get();
    }

    public static String j() {
        return Build.DISPLAY;
    }

    public static String k() {
        try {
            return AndroidPlatformModule.getApplicationContext().getPackageManager().getPackageInfo("com.google.android.gms", 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "not_installed";
        } catch (NullPointerException unused2) {
            return "undefined";
        }
    }

    @Nullable
    public static String l() {
        return AndroidPlatformModule.getAppInfoProvider().g();
    }

    public static int m() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = AndroidPlatformModule.getManagerProvider().getWindowManager();
        if (windowManager == null) {
            return 0;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int n() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = AndroidPlatformModule.getManagerProvider().getWindowManager();
        if (windowManager == null) {
            return 0;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static long o() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static long p() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        } catch (Exception unused) {
            return -1;
        }
    }

    static void q() {
        a = new d();
        b = new b();
        c = new f();
        d = new c();
        e = new e();
        b.a(c);
        c.a(a);
        a.a(d);
        e.a(d);
    }

    public static boolean r() {
        PowerManager powerManager;
        KeyguardManager keyguardManager = AndroidPlatformModule.getManagerProvider().getKeyguardManager();
        if (keyguardManager == null || keyguardManager.inKeyguardRestrictedInputMode() || (powerManager = AndroidPlatformModule.getManagerProvider().getPowerManager()) == null) {
            return false;
        }
        if (!(Build.VERSION.SDK_INT < 20 ? powerManager.isScreenOn() : powerManager.isInteractive())) {
            return false;
        }
        ActivityManager activityManager = AndroidPlatformModule.getManagerProvider().getActivityManager();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager == null ? null : activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        String a2 = AndroidPlatformModule.getAppInfoProvider().a();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(a2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean s() {
        return a() || b();
    }

    @SuppressLint({"InlinedApi"})
    public static boolean t() {
        Configuration a2 = AndroidPlatformModule.getResourceProvider().a();
        return a2 != null && (a2.screenLayout & 4) == 4;
    }
}
