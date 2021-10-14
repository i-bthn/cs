package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.InstantApps;
import java.util.Iterator;
import java.util.List;

public final class zzdy extends zzg {
    private String zzce;
    private String zzcg;
    private String zzcm;
    private String zzco;
    private long zzcr;
    private String zzcu;
    private List<String> zzcw;
    private int zzds;
    private int zzjr;
    private String zzjs;
    private long zzjt;
    private long zzs;

    zzdy(zzfj zzfj, long j) {
        super(zzfj);
        this.zzs = j;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zzbk() {
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x019b A[Catch:{ IllegalStateException -> 0x01cc }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x019e A[Catch:{ IllegalStateException -> 0x01cc }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01a7 A[Catch:{ IllegalStateException -> 0x01cc }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01ba A[Catch:{ IllegalStateException -> 0x01cc }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01ef  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x023a  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x024a  */
    @Override // com.google.android.gms.measurement.internal.zzg
    public final void zzbl() {
        boolean z;
        String googleAppId;
        String str = "unknown";
        String str2 = "Unknown";
        String str3 = "Unknown";
        String packageName = getContext().getPackageName();
        PackageManager packageManager = getContext().getPackageManager();
        int i = Integer.MIN_VALUE;
        if (packageManager == null) {
            zzab().zzgk().zza("PackageManager is null, app identity information might be inaccurate. appId", zzef.zzam(packageName));
        } else {
            try {
                str = packageManager.getInstallerPackageName(packageName);
            } catch (IllegalArgumentException unused) {
                zzab().zzgk().zza("Error retrieving app installer package name. appId", zzef.zzam(packageName));
            }
            if (str == null) {
                str = "manual_install";
            } else if ("com.android.vending".equals(str)) {
                str = "";
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
                if (packageInfo != null) {
                    CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                    if (!TextUtils.isEmpty(applicationLabel)) {
                        str3 = applicationLabel.toString();
                    }
                    str2 = packageInfo.versionName;
                    i = packageInfo.versionCode;
                }
            } catch (PackageManager.NameNotFoundException unused2) {
                zzab().zzgk().zza("Error retrieving package info. appId, appName", zzef.zzam(packageName), str3);
            }
        }
        this.zzce = packageName;
        this.zzco = str;
        this.zzcm = str2;
        this.zzjr = i;
        this.zzjs = str3;
        this.zzjt = 0;
        zzae();
        Status initialize = GoogleServices.initialize(getContext());
        boolean z2 = true;
        boolean z3 = (initialize != null && initialize.isSuccess()) | (!TextUtils.isEmpty(this.zzj.zzhx()) && "am".equals(this.zzj.zzhy()));
        if (!z3) {
            if (initialize == null) {
                zzab().zzgk().zzao("GoogleService failed to initialize (no status)");
            } else {
                zzab().zzgk().zza("GoogleService failed to initialize, status", Integer.valueOf(initialize.getStatusCode()), initialize.getStatusMessage());
            }
        }
        if (z3) {
            Boolean zzbq = zzad().zzbq();
            if (zzad().zzbp()) {
                if (this.zzj.zzhw()) {
                    zzab().zzgq().zzao("Collection disabled with firebase_analytics_collection_deactivated=1");
                }
            } else if (zzbq == null || zzbq.booleanValue()) {
                if (zzbq != null || !GoogleServices.isMeasurementExplicitlyDisabled()) {
                    zzab().zzgs().zzao("Collection enabled");
                    z = true;
                    this.zzcg = "";
                    this.zzcu = "";
                    this.zzcr = 0;
                    zzae();
                    if (!TextUtils.isEmpty(this.zzj.zzhx()) && "am".equals(this.zzj.zzhy())) {
                        this.zzcu = this.zzj.zzhx();
                    }
                    googleAppId = GoogleServices.getGoogleAppId();
                    this.zzcg = !TextUtils.isEmpty(googleAppId) ? "" : googleAppId;
                    if (!TextUtils.isEmpty(googleAppId)) {
                        this.zzcu = new StringResourceValueReader(getContext()).getString("admob_app_id");
                    }
                    if (z) {
                        zzab().zzgs().zza("App package, google app id", this.zzce, this.zzcg);
                    }
                    this.zzcw = null;
                    if (zzad().zze(this.zzce, zzak.zzix)) {
                        zzae();
                        List<String> zzk = zzad().zzk("analytics.safelisted_events");
                        if (zzk != null) {
                            if (zzk.size() != 0) {
                                Iterator<String> it = zzk.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    if (!zzz().zzq("safelisted event", it.next())) {
                                        z2 = false;
                                        break;
                                    }
                                }
                            } else {
                                zzab().zzgn().zzao("Safelisted event list cannot be empty. Ignoring");
                                z2 = false;
                            }
                        }
                        if (z2) {
                            this.zzcw = zzk;
                        }
                    }
                    if (Build.VERSION.SDK_INT >= 16) {
                        this.zzds = 0;
                        return;
                    } else if (packageManager != null) {
                        this.zzds = InstantApps.isInstantApp(getContext()) ? 1 : 0;
                        return;
                    } else {
                        this.zzds = 0;
                        return;
                    }
                } else {
                    zzab().zzgq().zzao("Collection disabled with google_app_measurement_enable=0");
                }
            } else if (this.zzj.zzhw()) {
                zzab().zzgq().zzao("Collection disabled with firebase_analytics_collection_enabled=0");
            }
        }
        z = false;
        this.zzcg = "";
        this.zzcu = "";
        this.zzcr = 0;
        zzae();
        this.zzcu = this.zzj.zzhx();
        try {
            googleAppId = GoogleServices.getGoogleAppId();
            this.zzcg = !TextUtils.isEmpty(googleAppId) ? "" : googleAppId;
            if (!TextUtils.isEmpty(googleAppId)) {
            }
            if (z) {
            }
        } catch (IllegalStateException e) {
            zzab().zzgk().zza("getGoogleAppId or isMeasurementEnabled failed with exception. appId", zzef.zzam(packageName), e);
        }
        this.zzcw = null;
        if (zzad().zze(this.zzce, zzak.zzix)) {
        }
        if (Build.VERSION.SDK_INT >= 16) {
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final zzn zzai(String str) {
        String str2;
        Boolean zzj;
        zzo();
        zzm();
        String zzag = zzag();
        String gmpAppId = getGmpAppId();
        zzbi();
        String str3 = this.zzcm;
        long zzgf = (long) zzgf();
        zzbi();
        String str4 = this.zzco;
        long zzao = zzad().zzao();
        zzbi();
        zzo();
        if (this.zzjt == 0) {
            this.zzjt = this.zzj.zzz().zzc(getContext(), getContext().getPackageName());
        }
        long j = this.zzjt;
        boolean isEnabled = this.zzj.isEnabled();
        boolean z = !zzac().zzmc;
        zzo();
        zzm();
        if (!this.zzj.isEnabled()) {
            str2 = null;
        } else {
            str2 = zzge();
        }
        zzbi();
        long j2 = this.zzcr;
        long zzic = this.zzj.zzic();
        int zzgg = zzgg();
        boolean booleanValue = zzad().zzbr().booleanValue();
        zzs zzad = zzad();
        zzad.zzm();
        Boolean zzj2 = zzad.zzj("google_analytics_ssaid_collection_enabled");
        return new zzn(zzag, gmpAppId, str3, zzgf, str4, zzao, j, str, isEnabled, z, str2, j2, zzic, zzgg, booleanValue, Boolean.valueOf(zzj2 == null || zzj2.booleanValue()).booleanValue(), zzac().zzhi(), zzah(), (!zzad().zze(zzag(), zzak.zzij) || (zzj = zzad().zzj("google_analytics_default_allow_ad_personalization_signals")) == null) ? null : Boolean.valueOf(!zzj.booleanValue()), this.zzs, zzad().zze(zzag(), zzak.zzix) ? this.zzcw : null);
    }

    @VisibleForTesting
    @WorkerThread
    private final String zzge() {
        try {
            Class<?> loadClass = getContext().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
            if (loadClass == null) {
                return null;
            }
            try {
                Object invoke = loadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, getContext());
                if (invoke == null) {
                    return null;
                }
                try {
                    return (String) loadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(invoke, new Object[0]);
                } catch (Exception unused) {
                    zzab().zzgp().zzao("Failed to retrieve Firebase Instance Id");
                    return null;
                }
            } catch (Exception unused2) {
                zzab().zzgo().zzao("Failed to obtain Firebase Analytics instance");
                return null;
            }
        } catch (ClassNotFoundException unused3) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzag() {
        zzbi();
        return this.zzce;
    }

    /* access modifiers changed from: package-private */
    public final String getGmpAppId() {
        zzbi();
        return this.zzcg;
    }

    /* access modifiers changed from: package-private */
    public final String zzah() {
        zzbi();
        return this.zzcu;
    }

    /* access modifiers changed from: package-private */
    public final int zzgf() {
        zzbi();
        return this.zzjr;
    }

    /* access modifiers changed from: package-private */
    public final int zzgg() {
        zzbi();
        return this.zzds;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final List<String> zzbh() {
        return this.zzcw;
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
