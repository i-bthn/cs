package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import androidx.work.WorkRequest;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzgk;
import com.google.android.gms.measurement.internal.zzgn;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class zzz {
    private static volatile zzz zzab = null;
    private static Boolean zzah = null;
    private static Boolean zzai = null;
    private static boolean zzaj = false;
    private static Boolean zzak = null;
    @VisibleForTesting
    private static String zzal = "use_dynamite_api";
    @VisibleForTesting
    private static String zzam = "allow_remote_dynamite";
    private static boolean zzan = false;
    private static boolean zzao = false;
    protected final Clock zzac;
    private final ExecutorService zzad;
    private final AppMeasurementSdk zzae;
    private List<Pair<zzgn, zzd>> zzaf;
    private int zzag;
    private boolean zzap;
    private String zzaq;
    private zzk zzar;
    private final String zzu;

    class zzc implements Application.ActivityLifecycleCallbacks {
        zzc() {
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
            zzz.this.zza((zzz) new zzbd(this, activity, bundle));
        }

        public final void onActivityStarted(Activity activity) {
            zzz.this.zza((zzz) new zzbc(this, activity));
        }

        public final void onActivityResumed(Activity activity) {
            zzz.this.zza((zzz) new zzbf(this, activity));
        }

        public final void onActivityPaused(Activity activity) {
            zzz.this.zza((zzz) new zzbe(this, activity));
        }

        public final void onActivityStopped(Activity activity) {
            zzz.this.zza((zzz) new zzbh(this, activity));
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            zzl zzl = new zzl();
            zzz.this.zza((zzz) new zzbg(this, activity, zzl));
            Bundle zzb = zzl.zzb(50);
            if (zzb != null) {
                bundle.putAll(zzb);
            }
        }

        public final void onActivityDestroyed(Activity activity) {
            zzz.this.zza((zzz) new zzbi(this, activity));
        }
    }

    public static zzz zza(@NonNull Context context) {
        return zza(context, (String) null, (String) null, (String) null, (Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public abstract class zzb implements Runnable {
        final long timestamp;
        final long zzbt;
        private final boolean zzbu;

        zzb(zzz zzz) {
            this(true);
        }

        /* access modifiers changed from: package-private */
        public abstract void zzf() throws RemoteException;

        /* access modifiers changed from: protected */
        public void zzk() {
        }

        zzb(boolean z) {
            this.timestamp = zzz.this.zzac.currentTimeMillis();
            this.zzbt = zzz.this.zzac.elapsedRealtime();
            this.zzbu = z;
        }

        public void run() {
            if (zzz.this.zzap) {
                zzk();
                return;
            }
            try {
                zzf();
            } catch (Exception e) {
                zzz.this.zza((zzz) e, (Exception) false, this.zzbu);
                zzk();
            }
        }
    }

    public static zzz zza(Context context, String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotNull(context);
        if (zzab == null) {
            synchronized (zzz.class) {
                if (zzab == null) {
                    zzab = new zzz(context, str, str2, str3, bundle);
                }
            }
        }
        return zzab;
    }

    static class zza extends zzt {
        private final zzgk zzbs;

        zza(zzgk zzgk) {
            this.zzbs = zzgk;
        }

        @Override // com.google.android.gms.internal.measurement.zzq
        public final void onEvent(String str, String str2, Bundle bundle, long j) {
            this.zzbs.interceptEvent(str, str2, bundle, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzq
        public final int id() {
            return System.identityHashCode(this.zzbs);
        }
    }

    /* access modifiers changed from: package-private */
    public static class zzd extends zzt {
        private final zzgn zzbv;

        zzd(zzgn zzgn) {
            this.zzbv = zzgn;
        }

        @Override // com.google.android.gms.internal.measurement.zzq
        public final void onEvent(String str, String str2, Bundle bundle, long j) {
            this.zzbv.onEvent(str, str2, bundle, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzq
        public final int id() {
            return System.identityHashCode(this.zzbv);
        }
    }

    public final AppMeasurementSdk zzg() {
        return this.zzae;
    }

    private zzz(Context context, String str, String str2, String str3, Bundle bundle) {
        if (str == null || !zza(str2, str3)) {
            this.zzu = "FA";
        } else {
            this.zzu = str;
        }
        this.zzac = DefaultClock.getInstance();
        this.zzad = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.zzae = new AppMeasurementSdk(this);
        boolean z = false;
        if (!(!zzb(context) || zzh())) {
            this.zzaq = null;
            this.zzap = true;
            Log.w(this.zzu, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
            return;
        }
        if (!zza(str2, str3)) {
            this.zzaq = "fa";
            if (str2 == null || str3 == null) {
                if ((str2 == null) ^ (str3 == null ? true : z)) {
                    Log.w(this.zzu, "Specified origin or custom app id is null. Both parameters will be ignored.");
                }
            } else {
                Log.v(this.zzu, "Deferring to Google Analytics for Firebase for event data collection. https://goo.gl/J1sWQy");
                this.zzap = true;
                return;
            }
        } else {
            this.zzaq = str2;
        }
        zza(new zzy(this, str2, str3, context, bundle));
        Application application = (Application) context.getApplicationContext();
        if (application == null) {
            Log.w(this.zzu, "Unable to register lifecycle notifications. Application null.");
        } else {
            application.registerActivityLifecycleCallbacks(new zzc());
        }
    }

    private static boolean zzb(Context context) {
        try {
            GoogleServices.initialize(context);
            if (GoogleServices.getGoogleAppId() != null) {
                return true;
            }
            return false;
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static boolean zza(String str, String str2) {
        return (str2 == null || str == null || zzh()) ? false : true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final void zza(zzb zzb2) {
        this.zzad.execute(zzb2);
    }

    /* access modifiers changed from: protected */
    public final zzk zza(Context context, boolean z) {
        DynamiteModule.VersionPolicy versionPolicy;
        if (z) {
            try {
                versionPolicy = DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION;
            } catch (DynamiteModule.LoadingException e) {
                zza((Exception) e, true, false);
                return null;
            }
        } else {
            versionPolicy = DynamiteModule.PREFER_LOCAL;
        }
        return zzn.asInterface(DynamiteModule.load(context, versionPolicy, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
    }

    /* access modifiers changed from: private */
    public static int zzc(Context context) {
        return DynamiteModule.getRemoteVersion(context, ModuleDescriptor.MODULE_ID);
    }

    /* access modifiers changed from: private */
    public static int zzd(Context context) {
        return DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final void zza(Exception exc, boolean z, boolean z2) {
        this.zzap |= z;
        if (z) {
            Log.w(this.zzu, "Data collection startup failed. No data will be collected.", exc);
            return;
        }
        if (z2) {
            zza(5, "Error with data collection. Data lost.", exc, (Object) null, (Object) null);
        }
        Log.w(this.zzu, "Error with data collection. Data lost.", exc);
    }

    private static boolean zzh() {
        try {
            Class.forName("com.google.firebase.analytics.FirebaseAnalytics");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public final void zza(zzgk zzgk) {
        zza(new zzal(this, zzgk));
    }

    public final void zza(zzgn zzgn) {
        Preconditions.checkNotNull(zzgn);
        zza(new zzau(this, zzgn));
    }

    public final void zzb(zzgn zzgn) {
        Preconditions.checkNotNull(zzgn);
        zza(new zzaz(this, zzgn));
    }

    public final void logEvent(@NonNull String str, Bundle bundle) {
        zza(null, str, bundle, false, true, null);
    }

    public final void logEventInternal(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, null);
    }

    public final void logEventInternalNoInterceptor(String str, String str2, Bundle bundle, long j) {
        zza(str, str2, bundle, true, false, Long.valueOf(j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, Long l) {
        zza(new zzay(this, l, str, str2, bundle, z, z2));
    }

    public final void setUserProperty(String str, String str2) {
        zza((String) null, str, (Object) str2, false);
    }

    public final void setUserPropertyInternal(String str, String str2, Object obj) {
        zza(str, str2, obj, true);
    }

    private final void zza(String str, String str2, Object obj, boolean z) {
        zza(new zzbb(this, str, str2, obj, z));
    }

    public final void setConditionalUserProperty(Bundle bundle) {
        zza(new zzba(this, bundle));
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zza(new zzab(this, str, str2, bundle));
    }

    public final List<Bundle> getConditionalUserProperties(String str, String str2) {
        zzl zzl = new zzl();
        zza(new zzaa(this, str, str2, zzl));
        List<Bundle> list = (List) zzl.zza(zzl.zzb(5000), List.class);
        return list == null ? Collections.emptyList() : list;
    }

    public final void setUserId(String str) {
        zza(new zzad(this, str));
    }

    public final void setCurrentScreen(Activity activity, String str, String str2) {
        zza(new zzac(this, activity, str, str2));
    }

    public final void setMeasurementEnabled(boolean z) {
        zza(new zzaf(this, z));
    }

    public final void resetAnalyticsData() {
        zza(new zzae(this));
    }

    public final void setMinimumSessionDuration(long j) {
        zza(new zzah(this, j));
    }

    public final void setSessionTimeoutDuration(long j) {
        zza(new zzag(this, j));
    }

    public final void beginAdUnitExposure(String str) {
        zza(new zzaj(this, str));
    }

    public final void endAdUnitExposure(String str) {
        zza(new zzai(this, str));
    }

    public final String getGmpAppId() {
        zzl zzl = new zzl();
        zza(new zzak(this, zzl));
        return zzl.zza(500);
    }

    public final String zzi() {
        zzl zzl = new zzl();
        zza(new zzan(this, zzl));
        return zzl.zza(50);
    }

    public final long generateEventId() {
        zzl zzl = new zzl();
        zza(new zzam(this, zzl));
        Long l = (Long) zzl.zza(zzl.zzb(500), Long.class);
        if (l != null) {
            return l.longValue();
        }
        long nextLong = new Random(System.nanoTime() ^ this.zzac.currentTimeMillis()).nextLong();
        int i = this.zzag + 1;
        this.zzag = i;
        return nextLong + ((long) i);
    }

    public final String getCurrentScreenName() {
        zzl zzl = new zzl();
        zza(new zzap(this, zzl));
        return zzl.zza(500);
    }

    public final String getCurrentScreenClass() {
        zzl zzl = new zzl();
        zza(new zzao(this, zzl));
        return zzl.zza(500);
    }

    public final Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        zzl zzl = new zzl();
        zza(new zzar(this, str, str2, z, zzl));
        Bundle zzb2 = zzl.zzb(5000);
        if (zzb2 == null || zzb2.size() == 0) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap(zzb2.size());
        for (String str3 : zzb2.keySet()) {
            Object obj = zzb2.get(str3);
            if ((obj instanceof Double) || (obj instanceof Long) || (obj instanceof String)) {
                hashMap.put(str3, obj);
            }
        }
        return hashMap;
    }

    public final void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        zza(new zzaq(this, false, 5, str, obj, null, null));
    }

    public final Bundle zza(Bundle bundle, boolean z) {
        zzl zzl = new zzl();
        zza(new zzat(this, bundle, zzl));
        if (z) {
            return zzl.zzb(5000);
        }
        return null;
    }

    public final int getMaxUserProperties(String str) {
        zzl zzl = new zzl();
        zza(new zzas(this, str, zzl));
        Integer num = (Integer) zzl.zza(zzl.zzb(WorkRequest.MIN_BACKOFF_MILLIS), Integer.class);
        if (num == null) {
            return 25;
        }
        return num.intValue();
    }

    @WorkerThread
    public final String getAppInstanceId() {
        zzl zzl = new zzl();
        zza(new zzav(this, zzl));
        return zzl.zza(120000);
    }

    public final String getAppIdOrigin() {
        return this.zzaq;
    }

    public final Object zzb(int i) {
        zzl zzl = new zzl();
        zza(new zzax(this, zzl, i));
        return zzl.zza(zzl.zzb(15000), Object.class);
    }

    public final void setDataCollectionEnabled(boolean z) {
        zza(new zzaw(this, z));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0057, code lost:
        android.util.Log.e("FA", "Exception reading flag from SharedPreferences.", r4);
        com.google.android.gms.internal.measurement.zzz.zzah = false;
        com.google.android.gms.internal.measurement.zzz.zzai = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0056 A[ExcHandler: ClassCastException | IllegalStateException | NullPointerException (r4v1 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:10:0x0010] */
    public static void zze(Context context) {
        synchronized (zzz.class) {
            if (zzah == null || zzai == null) {
                try {
                    if (zza(context, "app_measurement_internal_disable_startup_flags")) {
                        zzah = false;
                        zzai = false;
                        return;
                    }
                    SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
                    zzah = Boolean.valueOf(sharedPreferences.getBoolean(zzal, false));
                    zzai = Boolean.valueOf(sharedPreferences.getBoolean(zzam, false));
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.remove(zzal);
                    edit.remove(zzam);
                    edit.apply();
                } catch (ClassCastException | IllegalStateException | NullPointerException e) {
                }
            }
        }
    }

    public static boolean zzf(Context context) {
        zze(context);
        synchronized (zzz.class) {
            if (!zzaj) {
                try {
                    String str = (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, "measurement.dynamite.enabled", "");
                    if ("true".equals(str)) {
                        zzak = true;
                    } else if ("false".equals(str)) {
                        zzak = false;
                    } else {
                        zzak = null;
                    }
                } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    Log.e("FA", "Unable to call SystemProperties.get()", e);
                    zzak = null;
                } finally {
                    zzaj = true;
                }
            }
        }
        Boolean bool = zzak;
        if (bool == null) {
            bool = zzah;
        }
        return bool.booleanValue();
    }

    private static boolean zza(Context context, @Size(min = 1) String str) {
        Preconditions.checkNotEmpty(str);
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                if (applicationInfo.metaData != null) {
                    return applicationInfo.metaData.getBoolean(str);
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
