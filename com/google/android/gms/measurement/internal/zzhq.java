package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.Map;

public final class zzhq extends zzg {
    @VisibleForTesting
    protected zzhr zzqo;
    private volatile zzhr zzqp;
    private zzhr zzqq;
    private final Map<Activity, zzhr> zzqr = new ArrayMap();
    private zzhr zzqs;
    private String zzqt;

    public zzhq(zzfj zzfj) {
        super(zzfj);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zzbk() {
        return false;
    }

    @WorkerThread
    public final zzhr zzin() {
        zzbi();
        zzo();
        return this.zzqo;
    }

    public final void setCurrentScreen(@NonNull Activity activity, @Nullable @Size(max = 36, min = 1) String str, @Nullable @Size(max = 36, min = 1) String str2) {
        if (this.zzqp == null) {
            zzab().zzgp().zzao("setCurrentScreen cannot be called while no activity active");
        } else if (this.zzqr.get(activity) == null) {
            zzab().zzgp().zzao("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = zzbh(activity.getClass().getCanonicalName());
            }
            boolean equals = this.zzqp.zzqv.equals(str2);
            boolean zzs = zzjs.zzs(this.zzqp.zzqu, str);
            if (equals && zzs) {
                zzab().zzgp().zzao("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() <= 0 || str.length() > 100)) {
                zzab().zzgp().zza("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() > 0 && str2.length() <= 100)) {
                zzab().zzgs().zza("Setting current screen to name, class", str == null ? "null" : str, str2);
                zzhr zzhr = new zzhr(str, str2, zzz().zzjv());
                this.zzqr.put(activity, zzhr);
                zza(activity, zzhr, true);
            } else {
                zzab().zzgp().zza("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    public final zzhr zzio() {
        zzm();
        return this.zzqp;
    }

    @MainThread
    private final void zza(Activity activity, zzhr zzhr, boolean z) {
        zzhr zzhr2 = this.zzqp == null ? this.zzqq : this.zzqp;
        if (zzhr.zzqv == null) {
            zzhr = new zzhr(zzhr.zzqu, zzbh(activity.getClass().getCanonicalName()), zzhr.zzqw);
        }
        this.zzqq = this.zzqp;
        this.zzqp = zzhr;
        zzaa().zza(new zzht(this, z, zzhr2, zzhr));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(@NonNull zzhr zzhr, boolean z) {
        zzp().zzc(zzx().elapsedRealtime());
        if (zzv().zza(zzhr.zzqx, z)) {
            zzhr.zzqx = false;
        }
    }

    public static void zza(zzhr zzhr, Bundle bundle, boolean z) {
        if (bundle != null && zzhr != null && (!bundle.containsKey("_sc") || z)) {
            if (zzhr.zzqu != null) {
                bundle.putString("_sn", zzhr.zzqu);
            } else {
                bundle.remove("_sn");
            }
            bundle.putString("_sc", zzhr.zzqv);
            bundle.putLong("_si", zzhr.zzqw);
        } else if (bundle != null && zzhr == null && z) {
            bundle.remove("_sn");
            bundle.remove("_sc");
            bundle.remove("_si");
        }
    }

    @WorkerThread
    public final void zza(String str, zzhr zzhr) {
        zzo();
        synchronized (this) {
            if (this.zzqt == null || this.zzqt.equals(str) || zzhr != null) {
                this.zzqt = str;
                this.zzqs = zzhr;
            }
        }
    }

    @VisibleForTesting
    private static String zzbh(String str) {
        String[] split = str.split("\\.");
        String str2 = split.length > 0 ? split[split.length - 1] : "";
        return str2.length() > 100 ? str2.substring(0, 100) : str2;
    }

    @MainThread
    private final zzhr zza(@NonNull Activity activity) {
        Preconditions.checkNotNull(activity);
        zzhr zzhr = this.zzqr.get(activity);
        if (zzhr != null) {
            return zzhr;
        }
        zzhr zzhr2 = new zzhr(null, zzbh(activity.getClass().getCanonicalName()), zzz().zzjv());
        this.zzqr.put(activity, zzhr2);
        return zzhr2;
    }

    @MainThread
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (bundle != null && (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) != null) {
            this.zzqr.put(activity, new zzhr(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), bundle2.getString("referrer_name"), bundle2.getLong("id")));
        }
    }

    @MainThread
    public final void onActivityResumed(Activity activity) {
        zza(activity, zza(activity), false);
        zza zzp = zzp();
        zzp.zzaa().zza(new zze(zzp, zzp.zzx().elapsedRealtime()));
    }

    @MainThread
    public final void onActivityPaused(Activity activity) {
        zzhr zza = zza(activity);
        this.zzqq = this.zzqp;
        this.zzqp = null;
        zzaa().zza(new zzhs(this, zza));
    }

    @MainThread
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzhr zzhr;
        if (bundle != null && (zzhr = this.zzqr.get(activity)) != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putLong("id", zzhr.zzqw);
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, zzhr.zzqu);
            bundle2.putString("referrer_name", zzhr.zzqv);
            bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
        }
    }

    @MainThread
    public final void onActivityDestroyed(Activity activity) {
        this.zzqr.remove(activity);
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzl() {
        super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzm() {
        super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzn() {
        super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzo() {
        super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zza zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzgp zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzdy zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhv zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhq zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzeb zzu() {
        return super.zzu();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zziw zzv() {
        return super.zzv();
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
