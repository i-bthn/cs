package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzcm;
import com.google.android.gms.internal.measurement.zzp;
import com.google.android.gms.internal.measurement.zzx;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

public class zzfj implements zzgh {
    private static volatile zzfj zzoa;
    private final Clock zzac;
    private boolean zzdh = false;
    private final long zzdr;
    private final zzr zzfv;
    private final Context zzob;
    private final String zzoc;
    private final String zzod;
    private final zzs zzoe;
    private final zzeo zzof;
    private final zzef zzog;
    private final zzfc zzoh;
    private final zziw zzoi;
    private final zzjs zzoj;
    private final zzed zzok;
    private final zzhq zzol;
    private final zzgp zzom;
    private final zza zzon;
    private final zzhl zzoo;
    private zzeb zzop;
    private zzhv zzoq;
    private zzac zzor;
    private zzdy zzos;
    private zzeu zzot;
    private Boolean zzou;
    private long zzov;
    private volatile Boolean zzow;
    @VisibleForTesting
    private Boolean zzox;
    @VisibleForTesting
    private Boolean zzoy;
    private int zzoz;
    private AtomicInteger zzpa = new AtomicInteger(0);
    private final boolean zzt;
    private final String zzv;

    private zzfj(zzgm zzgm) {
        boolean z = false;
        Preconditions.checkNotNull(zzgm);
        this.zzfv = new zzr(zzgm.zzob);
        zzak.zza(this.zzfv);
        this.zzob = zzgm.zzob;
        this.zzv = zzgm.zzv;
        this.zzoc = zzgm.zzoc;
        this.zzod = zzgm.zzod;
        this.zzt = zzgm.zzt;
        this.zzow = zzgm.zzow;
        zzx zzx = zzgm.zzpr;
        if (!(zzx == null || zzx.zzw == null)) {
            Object obj = zzx.zzw.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zzox = (Boolean) obj;
            }
            Object obj2 = zzx.zzw.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzoy = (Boolean) obj2;
            }
        }
        zzcm.zzr(this.zzob);
        this.zzac = DefaultClock.getInstance();
        this.zzdr = this.zzac.currentTimeMillis();
        this.zzoe = new zzs(this);
        zzeo zzeo = new zzeo(this);
        zzeo.initialize();
        this.zzof = zzeo;
        zzef zzef = new zzef(this);
        zzef.initialize();
        this.zzog = zzef;
        zzjs zzjs = new zzjs(this);
        zzjs.initialize();
        this.zzoj = zzjs;
        zzed zzed = new zzed(this);
        zzed.initialize();
        this.zzok = zzed;
        this.zzon = new zza(this);
        zzhq zzhq = new zzhq(this);
        zzhq.initialize();
        this.zzol = zzhq;
        zzgp zzgp = new zzgp(this);
        zzgp.initialize();
        this.zzom = zzgp;
        zziw zziw = new zziw(this);
        zziw.initialize();
        this.zzoi = zziw;
        zzhl zzhl = new zzhl(this);
        zzhl.initialize();
        this.zzoo = zzhl;
        zzfc zzfc = new zzfc(this);
        zzfc.initialize();
        this.zzoh = zzfc;
        if (!(zzgm.zzpr == null || zzgm.zzpr.zzs == 0)) {
            z = true;
        }
        boolean z2 = !z;
        zzr zzr = this.zzfv;
        if (this.zzob.getApplicationContext() instanceof Application) {
            zzgp zzq = zzq();
            if (zzq.getContext().getApplicationContext() instanceof Application) {
                Application application = (Application) zzq.getContext().getApplicationContext();
                if (zzq.zzpu == null) {
                    zzq.zzpu = new zzhj(zzq, null);
                }
                if (z2) {
                    application.unregisterActivityLifecycleCallbacks(zzq.zzpu);
                    application.registerActivityLifecycleCallbacks(zzq.zzpu);
                    zzq.zzab().zzgs().zzao("Registered activity lifecycle callback");
                }
            }
        } else {
            zzab().zzgn().zzao("Application context is not an Application");
        }
        this.zzoh.zza(new zzfl(this, zzgm));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzgm zzgm) {
        String str;
        zzeh zzeh;
        zzaa().zzo();
        zzs.zzbm();
        zzac zzac2 = new zzac(this);
        zzac2.initialize();
        this.zzor = zzac2;
        zzdy zzdy = new zzdy(this, zzgm.zzs);
        zzdy.initialize();
        this.zzos = zzdy;
        zzeb zzeb = new zzeb(this);
        zzeb.initialize();
        this.zzop = zzeb;
        zzhv zzhv = new zzhv(this);
        zzhv.initialize();
        this.zzoq = zzhv;
        this.zzoj.zzbj();
        this.zzof.zzbj();
        this.zzot = new zzeu(this);
        this.zzos.zzbj();
        zzab().zzgq().zza("App measurement is starting up, version", Long.valueOf(this.zzoe.zzao()));
        zzr zzr = this.zzfv;
        zzab().zzgq().zzao("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzr zzr2 = this.zzfv;
        String zzag = zzdy.zzag();
        if (TextUtils.isEmpty(this.zzv)) {
            if (zzz().zzbr(zzag)) {
                zzeh = zzab().zzgq();
                str = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
            } else {
                zzeh = zzab().zzgq();
                String valueOf = String.valueOf(zzag);
                str = valueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(valueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ");
            }
            zzeh.zzao(str);
        }
        zzab().zzgr().zzao("Debug-level message logging enabled");
        if (this.zzoz != this.zzpa.get()) {
            zzab().zzgk().zza("Not all components initialized", Integer.valueOf(this.zzoz), Integer.valueOf(this.zzpa.get()));
        }
        this.zzdh = true;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void start() {
        zzaa().zzo();
        if (zzac().zzlj.get() == 0) {
            zzac().zzlj.set(this.zzac.currentTimeMillis());
        }
        if (Long.valueOf(zzac().zzlo.get()).longValue() == 0) {
            zzab().zzgs().zza("Persisting first open", Long.valueOf(this.zzdr));
            zzac().zzlo.set(this.zzdr);
        }
        if (zzie()) {
            zzr zzr = this.zzfv;
            if (!TextUtils.isEmpty(zzr().getGmpAppId()) || !TextUtils.isEmpty(zzr().zzah())) {
                zzz();
                if (zzjs.zza(zzr().getGmpAppId(), zzac().zzhc(), zzr().zzah(), zzac().zzhd())) {
                    zzab().zzgq().zzao("Rechecking which service to use due to a GMP App Id change");
                    zzac().zzhf();
                    zzu().resetAnalyticsData();
                    this.zzoq.disconnect();
                    this.zzoq.zzis();
                    zzac().zzlo.set(this.zzdr);
                    zzac().zzlq.zzau(null);
                }
                zzac().zzar(zzr().getGmpAppId());
                zzac().zzas(zzr().zzah());
            }
            zzq().zzbg(zzac().zzlq.zzho());
            zzr zzr2 = this.zzfv;
            if (!TextUtils.isEmpty(zzr().getGmpAppId()) || !TextUtils.isEmpty(zzr().zzah())) {
                boolean isEnabled = isEnabled();
                if (!zzac().zzhj() && !this.zzoe.zzbp()) {
                    zzac().zzf(!isEnabled);
                }
                if (isEnabled) {
                    zzq().zzim();
                }
                zzs().zza(new AtomicReference<>());
            }
        } else if (isEnabled()) {
            if (!zzz().zzbp("android.permission.INTERNET")) {
                zzab().zzgk().zzao("App is missing INTERNET permission");
            }
            if (!zzz().zzbp("android.permission.ACCESS_NETWORK_STATE")) {
                zzab().zzgk().zzao("App is missing ACCESS_NETWORK_STATE permission");
            }
            zzr zzr3 = this.zzfv;
            if (!Wrappers.packageManager(this.zzob).isCallerInstantApp() && !this.zzoe.zzbw()) {
                if (!zzez.zzl(this.zzob)) {
                    zzab().zzgk().zzao("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzjs.zzb(this.zzob, false)) {
                    zzab().zzgk().zzao("AppMeasurementService not registered/enabled");
                }
            }
            zzab().zzgk().zzao("Uploading is not possible. App measurement disabled");
        }
        zzac().zzly.set(this.zzoe.zza(zzak.zziu));
        zzac().zzlz.set(this.zzoe.zza(zzak.zziv));
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public final zzr zzae() {
        return this.zzfv;
    }

    public final zzs zzad() {
        return this.zzoe;
    }

    public final zzeo zzac() {
        zza((zzgf) this.zzof);
        return this.zzof;
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public final zzef zzab() {
        zza((zzge) this.zzog);
        return this.zzog;
    }

    public final zzef zzhs() {
        zzef zzef = this.zzog;
        if (zzef == null || !zzef.isInitialized()) {
            return null;
        }
        return this.zzog;
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public final zzfc zzaa() {
        zza((zzge) this.zzoh);
        return this.zzoh;
    }

    public final zziw zzv() {
        zza((zzg) this.zzoi);
        return this.zzoi;
    }

    public final zzeu zzht() {
        return this.zzot;
    }

    /* access modifiers changed from: package-private */
    public final zzfc zzhu() {
        return this.zzoh;
    }

    public final zzgp zzq() {
        zza((zzg) this.zzom);
        return this.zzom;
    }

    public final zzjs zzz() {
        zza((zzgf) this.zzoj);
        return this.zzoj;
    }

    public final zzed zzy() {
        zza((zzgf) this.zzok);
        return this.zzok;
    }

    public final zzeb zzu() {
        zza((zzg) this.zzop);
        return this.zzop;
    }

    private final zzhl zzhv() {
        zza((zzge) this.zzoo);
        return this.zzoo;
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public final Context getContext() {
        return this.zzob;
    }

    public final boolean zzhw() {
        return TextUtils.isEmpty(this.zzv);
    }

    public final String zzhx() {
        return this.zzv;
    }

    public final String zzhy() {
        return this.zzoc;
    }

    public final String zzhz() {
        return this.zzod;
    }

    public final boolean zzia() {
        return this.zzt;
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public final Clock zzx() {
        return this.zzac;
    }

    public final zzhq zzt() {
        zza((zzg) this.zzol);
        return this.zzol;
    }

    public final zzhv zzs() {
        zza((zzg) this.zzoq);
        return this.zzoq;
    }

    public final zzac zzw() {
        zza((zzge) this.zzor);
        return this.zzor;
    }

    public final zzdy zzr() {
        zza((zzg) this.zzos);
        return this.zzos;
    }

    public final zza zzp() {
        zza zza = this.zzon;
        if (zza != null) {
            return zza;
        }
        throw new IllegalStateException("Component not created");
    }

    @VisibleForTesting
    public static zzfj zza(Context context, String str, String str2, Bundle bundle) {
        return zza(context, new zzx(0, 0, true, null, null, null, bundle));
    }

    public static zzfj zza(Context context, zzx zzx) {
        if (zzx != null && (zzx.origin == null || zzx.zzv == null)) {
            zzx = new zzx(zzx.zzr, zzx.zzs, zzx.zzt, zzx.zzu, null, null, zzx.zzw);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzoa == null) {
            synchronized (zzfj.class) {
                if (zzoa == null) {
                    zzoa = new zzfj(new zzgm(context, zzx));
                }
            }
        } else if (!(zzx == null || zzx.zzw == null || !zzx.zzw.containsKey("dataCollectionDefaultEnabled"))) {
            zzoa.zza(zzx.zzw.getBoolean("dataCollectionDefaultEnabled"));
        }
        return zzoa;
    }

    private final void zzbi() {
        if (!this.zzdh) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    private static void zza(zzge zzge) {
        if (zzge == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzge.isInitialized()) {
            String valueOf = String.valueOf(zzge.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static void zza(zzg zzg) {
        if (zzg == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzg.isInitialized()) {
            String valueOf = String.valueOf(zzg.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static void zza(zzgf zzgf) {
        if (zzgf == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(boolean z) {
        this.zzow = Boolean.valueOf(z);
    }

    @WorkerThread
    public final boolean zzib() {
        return this.zzow != null && this.zzow.booleanValue();
    }

    @WorkerThread
    public final boolean isEnabled() {
        boolean z;
        zzaa().zzo();
        zzbi();
        if (this.zzoe.zza(zzak.zzil)) {
            if (this.zzoe.zzbp()) {
                return false;
            }
            Boolean bool = this.zzoy;
            if (bool != null && bool.booleanValue()) {
                return false;
            }
            Boolean zzhg = zzac().zzhg();
            if (zzhg != null) {
                return zzhg.booleanValue();
            }
            Boolean zzbq = this.zzoe.zzbq();
            if (zzbq != null) {
                return zzbq.booleanValue();
            }
            Boolean bool2 = this.zzox;
            if (bool2 != null) {
                return bool2.booleanValue();
            }
            if (GoogleServices.isMeasurementExplicitlyDisabled()) {
                return false;
            }
            if (!this.zzoe.zza(zzak.zzig) || this.zzow == null) {
                return true;
            }
            return this.zzow.booleanValue();
        } else if (this.zzoe.zzbp()) {
            return false;
        } else {
            Boolean zzbq2 = this.zzoe.zzbq();
            if (zzbq2 != null) {
                z = zzbq2.booleanValue();
            } else {
                z = !GoogleServices.isMeasurementExplicitlyDisabled();
                if (z && this.zzow != null && zzak.zzig.get(null).booleanValue()) {
                    z = this.zzow.booleanValue();
                }
            }
            return zzac().zze(z);
        }
    }

    /* access modifiers changed from: package-private */
    public final long zzic() {
        Long valueOf = Long.valueOf(zzac().zzlo.get());
        if (valueOf.longValue() == 0) {
            return this.zzdr;
        }
        return Math.min(this.zzdr, valueOf.longValue());
    }

    /* access modifiers changed from: package-private */
    public final void zzm() {
        zzr zzr = this.zzfv;
    }

    /* access modifiers changed from: package-private */
    public final void zzl() {
        zzr zzr = this.zzfv;
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzge zzge) {
        this.zzoz++;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzg zzg) {
        this.zzoz++;
    }

    /* access modifiers changed from: package-private */
    public final void zzid() {
        this.zzpa.incrementAndGet();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final boolean zzie() {
        zzbi();
        zzaa().zzo();
        Boolean bool = this.zzou;
        if (bool == null || this.zzov == 0 || (bool != null && !bool.booleanValue() && Math.abs(this.zzac.elapsedRealtime() - this.zzov) > 1000)) {
            this.zzov = this.zzac.elapsedRealtime();
            zzr zzr = this.zzfv;
            boolean z = true;
            this.zzou = Boolean.valueOf(zzz().zzbp("android.permission.INTERNET") && zzz().zzbp("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzob).isCallerInstantApp() || this.zzoe.zzbw() || (zzez.zzl(this.zzob) && zzjs.zzb(this.zzob, false))));
            if (this.zzou.booleanValue()) {
                if (!zzz().zzr(zzr().getGmpAppId(), zzr().zzah()) && TextUtils.isEmpty(zzr().zzah())) {
                    z = false;
                }
                this.zzou = Boolean.valueOf(z);
            }
        }
        return this.zzou.booleanValue();
    }

    @WorkerThread
    public final void zza(@NonNull zzp zzp) {
        zzaa().zzo();
        zza((zzge) zzhv());
        String zzag = zzr().zzag();
        Pair<String, Boolean> zzap = zzac().zzap(zzag);
        if (!this.zzoe.zzbr().booleanValue() || ((Boolean) zzap.second).booleanValue()) {
            zzab().zzgr().zzao("ADID unavailable to retrieve Deferred Deep Link. Skipping");
            zzz().zzb(zzp, "");
        } else if (!zzhv().zzgv()) {
            zzab().zzgn().zzao("Network is not available for Deferred Deep Link request. Skipping");
            zzz().zzb(zzp, "");
        } else {
            URL zza = zzz().zza(zzr().zzad().zzao(), zzag, (String) zzap.first);
            zzhl zzhv = zzhv();
            zzfi zzfi = new zzfi(this, zzp);
            zzhv.zzo();
            zzhv.zzbi();
            Preconditions.checkNotNull(zza);
            Preconditions.checkNotNull(zzfi);
            zzhv.zzaa().zzb(new zzhn(zzhv, zzag, zza, null, null, zzfi));
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzp zzp, String str, int i, Throwable th, byte[] bArr, Map map) {
        List<ResolveInfo> queryIntentActivities;
        boolean z = true;
        if (!((i == 200 || i == 204 || i == 304) && th == null)) {
            zzab().zzgn().zza("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i), th);
            zzz().zzb(zzp, "");
        } else if (bArr.length == 0) {
            zzz().zzb(zzp, "");
        } else {
            try {
                JSONObject jSONObject = new JSONObject(new String(bArr));
                String optString = jSONObject.optString("deeplink", "");
                String optString2 = jSONObject.optString("gclid", "");
                zzjs zzz = zzz();
                zzz.zzm();
                if (TextUtils.isEmpty(optString) || (queryIntentActivities = zzz.getContext().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(optString)), 0)) == null || queryIntentActivities.isEmpty()) {
                    z = false;
                }
                if (!z) {
                    zzab().zzgn().zza("Deferred Deep Link validation failed. gclid, deep link", optString2, optString);
                    zzz().zzb(zzp, "");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("deeplink", optString);
                bundle.putString("gclid", optString2);
                this.zzom.logEvent("auto", "_cmp", bundle);
                zzz().zzb(zzp, optString);
            } catch (JSONException e) {
                zzab().zzgk().zza("Failed to parse the Deferred Deep Link response. exception", e);
                zzz().zzb(zzp, "");
            }
        }
    }
}
