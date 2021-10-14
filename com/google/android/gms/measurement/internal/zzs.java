package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public final class zzs extends zzgf {
    private Boolean zzeb;
    @NonNull
    private zzu zzec = zzv.zzee;
    private Boolean zzed;

    zzs(zzfj zzfj) {
        super(zzfj);
        zzak.zza(zzfj);
    }

    /* access modifiers changed from: package-private */
    public final void zza(@NonNull zzu zzu) {
        this.zzec = zzu;
    }

    static String zzbm() {
        return zzak.zzgf.get(null);
    }

    @WorkerThread
    public final int zzi(@Size(min = 1) String str) {
        return zzb(str, zzak.zzgt);
    }

    public final long zzao() {
        zzae();
        return 16250;
    }

    public final boolean zzbn() {
        if (this.zzed == null) {
            synchronized (this) {
                if (this.zzed == null) {
                    ApplicationInfo applicationInfo = getContext().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzed = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if (this.zzed == null) {
                        this.zzed = Boolean.TRUE;
                        zzab().zzgk().zzao("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzed.booleanValue();
    }

    @WorkerThread
    public final long zza(String str, @NonNull zzdu<Long> zzdu) {
        if (str == null) {
            return zzdu.get(null).longValue();
        }
        String zzb = this.zzec.zzb(str, zzdu.getKey());
        if (TextUtils.isEmpty(zzb)) {
            return zzdu.get(null).longValue();
        }
        try {
            return zzdu.get(Long.valueOf(Long.parseLong(zzb))).longValue();
        } catch (NumberFormatException unused) {
            return zzdu.get(null).longValue();
        }
    }

    @WorkerThread
    public final int zzb(String str, @NonNull zzdu<Integer> zzdu) {
        if (str == null) {
            return zzdu.get(null).intValue();
        }
        String zzb = this.zzec.zzb(str, zzdu.getKey());
        if (TextUtils.isEmpty(zzb)) {
            return zzdu.get(null).intValue();
        }
        try {
            return zzdu.get(Integer.valueOf(Integer.parseInt(zzb))).intValue();
        } catch (NumberFormatException unused) {
            return zzdu.get(null).intValue();
        }
    }

    @WorkerThread
    public final double zzc(String str, @NonNull zzdu<Double> zzdu) {
        if (str == null) {
            return zzdu.get(null).doubleValue();
        }
        String zzb = this.zzec.zzb(str, zzdu.getKey());
        if (TextUtils.isEmpty(zzb)) {
            return zzdu.get(null).doubleValue();
        }
        try {
            return zzdu.get(Double.valueOf(Double.parseDouble(zzb))).doubleValue();
        } catch (NumberFormatException unused) {
            return zzdu.get(null).doubleValue();
        }
    }

    @WorkerThread
    public final boolean zzd(String str, @NonNull zzdu<Boolean> zzdu) {
        if (str == null) {
            return zzdu.get(null).booleanValue();
        }
        String zzb = this.zzec.zzb(str, zzdu.getKey());
        if (TextUtils.isEmpty(zzb)) {
            return zzdu.get(null).booleanValue();
        }
        return zzdu.get(Boolean.valueOf(Boolean.parseBoolean(zzb))).booleanValue();
    }

    public final boolean zze(String str, zzdu<Boolean> zzdu) {
        return zzd(str, zzdu);
    }

    public final boolean zza(zzdu<Boolean> zzdu) {
        return zzd(null, zzdu);
    }

    @Nullable
    @VisibleForTesting
    private final Bundle zzbo() {
        try {
            if (getContext().getPackageManager() == null) {
                zzab().zzgk().zzao("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(getContext()).getApplicationInfo(getContext().getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData;
            }
            zzab().zzgk().zzao("Failed to load metadata: ApplicationInfo is null");
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            zzab().zzgk().zza("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @VisibleForTesting
    public final Boolean zzj(@Size(min = 1) String str) {
        Preconditions.checkNotEmpty(str);
        Bundle zzbo = zzbo();
        if (zzbo == null) {
            zzab().zzgk().zzao("Failed to load metadata: Metadata bundle is null");
            return null;
        } else if (!zzbo.containsKey(str)) {
            return null;
        } else {
            return Boolean.valueOf(zzbo.getBoolean(str));
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @VisibleForTesting
    public final List<String> zzk(@Size(min = 1) String str) {
        Integer num;
        Preconditions.checkNotEmpty(str);
        Bundle zzbo = zzbo();
        if (zzbo == null) {
            zzab().zzgk().zzao("Failed to load metadata: Metadata bundle is null");
            num = null;
        } else if (!zzbo.containsKey(str)) {
            num = null;
        } else {
            num = Integer.valueOf(zzbo.getInt(str));
        }
        if (num == null) {
            return null;
        }
        try {
            String[] stringArray = getContext().getResources().getStringArray(num.intValue());
            if (stringArray == null) {
                return null;
            }
            return Arrays.asList(stringArray);
        } catch (Resources.NotFoundException e) {
            zzab().zzgk().zza("Failed to load string array from metadata: resource not found", e);
            return null;
        }
    }

    public final boolean zzbp() {
        zzae();
        Boolean zzj = zzj("firebase_analytics_collection_deactivated");
        return zzj != null && zzj.booleanValue();
    }

    public final Boolean zzbq() {
        zzae();
        return zzj("firebase_analytics_collection_enabled");
    }

    public final Boolean zzbr() {
        zzm();
        Boolean zzj = zzj("google_analytics_adid_collection_enabled");
        return Boolean.valueOf(zzj == null || zzj.booleanValue());
    }

    public static long zzbs() {
        return zzak.zzhi.get(null).longValue();
    }

    public static long zzbt() {
        return zzak.zzgi.get(null).longValue();
    }

    public final String zzbu() {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, "debug.firebase.analytics.app", "");
        } catch (ClassNotFoundException e) {
            zzab().zzgk().zza("Could not find SystemProperties class", e);
            return "";
        } catch (NoSuchMethodException e2) {
            zzab().zzgk().zza("Could not find SystemProperties.get() method", e2);
            return "";
        } catch (IllegalAccessException e3) {
            zzab().zzgk().zza("Could not access SystemProperties.get()", e3);
            return "";
        } catch (InvocationTargetException e4) {
            zzab().zzgk().zza("SystemProperties.get() threw an exception", e4);
            return "";
        }
    }

    public static boolean zzbv() {
        return zzak.zzge.get(null).booleanValue();
    }

    public final boolean zzl(String str) {
        return "1".equals(this.zzec.zzb(str, "gaia_collection_enabled"));
    }

    public final boolean zzm(String str) {
        return "1".equals(this.zzec.zzb(str, "measurement.event_sampling_enabled"));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzn(String str) {
        return zzd(str, zzak.zzhs);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzo(String str) {
        return zzd(str, zzak.zzhm);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String zzp(String str) {
        zzdu<String> zzdu = zzak.zzhn;
        if (str == null) {
            return zzdu.get(null);
        }
        return zzdu.get(this.zzec.zzb(str, zzdu.getKey()));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzq(String str) {
        return zzd(str, zzak.zzht);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzr(String str) {
        return zzd(str, zzak.zzhu);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzs(String str) {
        return zzd(str, zzak.zzhv);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzt(String str) {
        return zzd(str, zzak.zzhx);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzbw() {
        if (this.zzeb == null) {
            this.zzeb = zzj("app_measurement_lite");
            if (this.zzeb == null) {
                this.zzeb = false;
            }
        }
        if (this.zzeb.booleanValue() || !this.zzj.zzia()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzu(String str) {
        return zzd(str, zzak.zzhw);
    }

    @WorkerThread
    static boolean zzbx() {
        return zzak.zzhy.get(null).booleanValue();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzv(String str) {
        return zzd(str, zzak.zzhz);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzw(String str) {
        return zzd(str, zzak.zzia);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzx(String str) {
        return zzd(str, zzak.zzib);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzy(String str) {
        return zzd(str, zzak.zzic);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzz(String str) {
        return zzd(str, zzak.zzih);
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzl() {
        super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzm() {
        super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzn() {
        super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzo() {
        super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzac zzw() {
        return super.zzw();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ Clock zzx() {
        return super.zzx();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzed zzy() {
        return super.zzy();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzjs zzz() {
        return super.zzz();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzfc zzaa() {
        return super.zzaa();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzef zzab() {
        return super.zzab();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzeo zzac() {
        return super.zzac();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzs zzad() {
        return super.zzad();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzr zzae() {
        return super.zzae();
    }
}
