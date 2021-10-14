package com.pushwoosh.internal.crash;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* access modifiers changed from: package-private */
public class d {
    private static String a;
    private static String b;
    static WeakReference<Context> c;
    private static long d;

    /* access modifiers changed from: package-private */
    public static class a extends AsyncTask<Void, Object, Integer> {
        final /* synthetic */ e a;

        a(e eVar) {
            this.a = eVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Integer doInBackground(Void... voidArr) {
            return Integer.valueOf(d.d());
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Integer num) {
            e eVar = this.a;
            boolean z = eVar != null && eVar.c();
            if (num.intValue() == 1) {
                e eVar2 = this.a;
                if (eVar2 != null) {
                    eVar2.i();
                }
            } else if (num.intValue() == 2) {
                e eVar3 = this.a;
                if (eVar3 != null) {
                    eVar3.f();
                }
            } else {
                d.c(this.a, z);
                return;
            }
            d.d(this.a, z);
        }
    }

    /* access modifiers changed from: package-private */
    public static class b extends AsyncTask<Void, Object, Object> {
        final /* synthetic */ boolean a;
        final /* synthetic */ e b;

        b(boolean z, e eVar) {
            this.a = z;
            this.b = eVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Object doInBackground(Void... voidArr) {
            String[] e = d.e();
            if (e == null) {
                return null;
            }
            d.b(e);
            if (!this.a) {
                return null;
            }
            for (String str : e) {
                d.b(str, this.b);
            }
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0054, code lost:
        if (r3 != null) goto L_0x0056;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0062 A[SYNTHETIC, Splitter:B:28:0x0062] */
    private static String a(String str) {
        File fileStreamPath;
        Throwable th;
        BufferedReader bufferedReader;
        IOException e;
        Context c2 = c();
        if (c2 == null || (fileStreamPath = c2.getFileStreamPath(str)) == null || !fileStreamPath.exists()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(c2.openFileInput(str)));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                        sb.append(System.getProperty("line.separator"));
                    }
                } catch (IOException e2) {
                    e = e2;
                    try {
                        a.a("Failed to read content of " + str, e);
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                        }
                        throw th;
                    }
                }
                try {
                    break;
                } catch (IOException unused) {
                }
            }
        } catch (IOException e3) {
            e = e3;
            bufferedReader = null;
            a.a("Failed to read content of " + str, e);
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
        bufferedReader.close();
        return sb.toString();
    }

    public static void a(Context context, String str, String str2) {
        a(context, str, str2, null);
    }

    public static void a(Context context, String str, String str2, @Nullable e eVar) {
        a(context, str, str2, eVar, false);
        a(eVar);
    }

    private static void a(Context context, String str, String str2, e eVar, boolean z) {
        if (context != null) {
            if (d == 0) {
                d = System.currentTimeMillis();
            }
            b = str;
            a = j.a(str2);
            c = new WeakReference<>(context);
            if (a == null) {
                a = "";
            }
            if (z) {
                c(eVar, eVar != null && eVar.c());
            }
        }
    }

    public static void a(e eVar) {
        new a(eVar).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static void a(String str, int i) {
        Context c2;
        if (i != -1 && (c2 = c()) != null) {
            SharedPreferences sharedPreferences = c2.getSharedPreferences("SdkCrashAnalytics", 0);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            int i2 = sharedPreferences.getInt("RETRY_COUNT: " + str, 0);
            if (i2 >= i) {
                c(str);
                b(str);
                return;
            }
            edit.putInt("RETRY_COUNT: " + str, i2 + 1);
            edit.apply();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean a(File file, String str) {
        return str.endsWith(g.a());
    }

    private static String b() {
        return b + "api/2/apps/" + a + "/crashes/";
    }

    private static void b(String str) {
        Context c2 = c();
        if (c2 != null) {
            SharedPreferences.Editor edit = c2.getSharedPreferences("SdkCrashAnalytics", 0).edit();
            edit.remove("RETRY_COUNT: " + str);
            edit.apply();
        }
    }

    /* access modifiers changed from: private */
    public static void b(String str, e eVar) {
        boolean z = false;
        Boolean bool = false;
        HttpURLConnection httpURLConnection = null;
        try {
            String a2 = a(str);
            if (a2.length() > 0) {
                a.a("Transmitting crash data: \n" + a2);
                String a3 = a(str.replace(g.a(), ".description"));
                HashMap hashMap = new HashMap();
                hashMap.put("raw", a2);
                hashMap.put("description", a3);
                hashMap.put("version", "6.2.8");
                httpURLConnection = new h(b()).b("POST").a(hashMap).a();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 202 || responseCode == 201) {
                    z = true;
                }
                bool = Boolean.valueOf(z);
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (bool.booleanValue()) {
                a.a("Transmission succeeded");
                c(str);
                if (eVar == null) {
                    return;
                }
                eVar.h();
                b(str);
                return;
            }
            a.a("Transmission failed, will retry on next register() call");
            if (eVar == null) {
                return;
            }
            eVar.g();
            a(str, eVar.b());
        } catch (Exception e) {
            a.a("Failed to transmit crash data", e);
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            if (bool.booleanValue()) {
                a.a("Transmission succeeded");
                c(str);
                if (eVar == null) {
                }
            } else {
                a.a("Transmission failed, will retry on next register() call");
                if (eVar == null) {
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            if (bool.booleanValue()) {
                a.a("Transmission succeeded");
                c(str);
                if (eVar != null) {
                    eVar.h();
                    b(str);
                }
            } else {
                a.a("Transmission failed, will retry on next register() call");
                if (eVar != null) {
                    eVar.g();
                    a(str, eVar.b());
                }
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public static void b(String[] strArr) {
        Context c2 = c();
        if (c2 != null) {
            try {
                SharedPreferences.Editor edit = c2.getSharedPreferences("SdkCrashAnalytics", 0).edit();
                edit.putString("ConfirmedFilenames", TextUtils.join(",", strArr));
                edit.apply();
            } catch (Exception unused) {
            }
        }
    }

    private static Context c() {
        WeakReference<Context> weakReference = c;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static void c(e eVar, boolean z) {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            a.a("Current handler class = " + defaultUncaughtExceptionHandler.getClass().getName());
        }
        if (defaultUncaughtExceptionHandler instanceof f) {
            ((f) defaultUncaughtExceptionHandler).a(eVar);
        } else {
            Thread.setDefaultUncaughtExceptionHandler(new f(defaultUncaughtExceptionHandler, eVar, z));
        }
    }

    private static void c(String str) {
        Context c2 = c();
        if (c2 != null) {
            c2.deleteFile(str);
            c2.deleteFile(str.replace(g.a(), ".user"));
            c2.deleteFile(str.replace(g.a(), ".contact"));
            c2.deleteFile(str.replace(g.a(), ".description"));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002c  */
    public static int d() {
        List list;
        String[] e = e();
        if (e == null || e.length <= 0) {
            return 0;
        }
        try {
            Context c2 = c();
            if (c2 != null) {
                list = Arrays.asList(c2.getSharedPreferences("SdkCrashAnalytics", 0).getString("ConfirmedFilenames", "").split("\\|"));
                if (list != null) {
                    for (String str : e) {
                        if (list.contains(str)) {
                        }
                    }
                    return 2;
                }
                return 1;
            }
        } catch (Exception unused) {
        }
        list = null;
        if (list != null) {
        }
        return 1;
    }

    /* access modifiers changed from: private */
    public static void d(e eVar, boolean z) {
        c(eVar, z);
        boolean isNetworkAvailable = GeneralUtils.isNetworkAvailable();
        if (!isNetworkAvailable && eVar != null) {
            eVar.g();
        }
        new b(isNetworkAvailable, eVar).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* access modifiers changed from: private */
    public static String[] e() {
        Context c2 = c();
        if (c2 == null) {
            return null;
        }
        File a2 = g.a(c2);
        if (a2 != null) {
            a.a("Looking for exceptions in: " + a2.getAbsolutePath());
            return (a2.exists() || a2.mkdir()) ? a2.list($$Lambda$d$ErA1tCNemOv749CacHG5Zs1es.INSTANCE) : new String[0];
        }
        a.a("Can't search for exception as file path is null.");
        return null;
    }
}
