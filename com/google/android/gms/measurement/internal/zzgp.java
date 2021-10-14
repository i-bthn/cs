package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public final class zzgp extends zzg {
    @VisibleForTesting
    protected zzhj zzpu;
    private zzgk zzpv;
    private final Set<zzgn> zzpw = new CopyOnWriteArraySet();
    private boolean zzpx;
    private final AtomicReference<String> zzpy = new AtomicReference<>();
    @VisibleForTesting
    protected boolean zzpz = true;

    protected zzgp(zzfj zzfj) {
        super(zzfj);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zzbk() {
        return false;
    }

    public final void zzif() {
        if (getContext().getApplicationContext() instanceof Application) {
            ((Application) getContext().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zzpu);
        }
    }

    public final Boolean zzig() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzaa().zza(atomicReference, 15000, "boolean test flag value", new zzgo(this, atomicReference));
    }

    public final String zzih() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzaa().zza(atomicReference, 15000, "String test flag value", new zzgy(this, atomicReference));
    }

    public final Long zzii() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzaa().zza(atomicReference, 15000, "long test flag value", new zzha(this, atomicReference));
    }

    public final Integer zzij() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzaa().zza(atomicReference, 15000, "int test flag value", new zzhd(this, atomicReference));
    }

    public final Double zzik() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzaa().zza(atomicReference, 15000, "double test flag value", new zzhc(this, atomicReference));
    }

    public final void setMeasurementEnabled(boolean z) {
        zzbi();
        zzm();
        zzaa().zza(new zzhf(this, z));
    }

    public final void zza(boolean z) {
        zzbi();
        zzm();
        zzaa().zza(new zzhe(this, z));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzg(boolean z) {
        zzo();
        zzm();
        zzbi();
        zzab().zzgr().zza("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzac().setMeasurementEnabled(z);
        zzil();
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzil() {
        if (zzad().zze(zzr().zzag(), zzak.zzik)) {
            zzo();
            String zzho = zzac().zzlx.zzho();
            if (zzho != null) {
                if ("unset".equals(zzho)) {
                    zza("app", "_npa", (Object) null, zzx().currentTimeMillis());
                } else {
                    zza("app", "_npa", Long.valueOf("true".equals(zzho) ? 1 : 0), zzx().currentTimeMillis());
                }
            }
        }
        if (!this.zzj.isEnabled() || !this.zzpz) {
            zzab().zzgr().zzao("Updating Scion state (FE)");
            zzs().zzip();
            return;
        }
        zzab().zzgr().zzao("Recording app launch after enabling measurement for the first time (FE)");
        zzim();
    }

    public final void setMinimumSessionDuration(long j) {
        zzm();
        zzaa().zza(new zzhh(this, j));
    }

    public final void setSessionTimeoutDuration(long j) {
        zzm();
        zzaa().zza(new zzhg(this, j));
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) {
        logEvent(str, str2, bundle, false, true, zzx().currentTimeMillis());
    }

    public final void logEvent(String str, String str2, Bundle bundle) {
        logEvent(str, str2, bundle, true, true, zzx().currentTimeMillis());
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(String str, String str2, Bundle bundle) {
        zzm();
        zzo();
        zza(str, str2, zzx().currentTimeMillis(), bundle);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(String str, String str2, long j, Bundle bundle) {
        zzm();
        zzo();
        zza(str, str2, j, bundle, true, this.zzpv == null || zzjs.zzbq(str2), false, null);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        String[] strArr;
        List<String> list;
        zzhr zzhr;
        int i;
        long j2;
        ArrayList arrayList;
        Bundle bundle2;
        Class<?> cls;
        List<String> zzbh;
        Preconditions.checkNotEmpty(str);
        if (!zzad().zze(str3, zzak.zzip)) {
            Preconditions.checkNotEmpty(str2);
        }
        Preconditions.checkNotNull(bundle);
        zzo();
        zzbi();
        if (!this.zzj.isEnabled()) {
            zzab().zzgr().zzao("Event not sent since app measurement is disabled");
        } else if (!zzad().zze(zzr().zzag(), zzak.zzix) || (zzbh = zzr().zzbh()) == null || zzbh.contains(str2)) {
            int i2 = 0;
            if (!this.zzpx) {
                this.zzpx = true;
                try {
                    if (!this.zzj.zzia()) {
                        cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, getContext().getClassLoader());
                    } else {
                        cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService");
                    }
                    try {
                        cls.getDeclaredMethod("initialize", Context.class).invoke(null, getContext());
                    } catch (Exception e) {
                        zzab().zzgn().zza("Failed to invoke Tag Manager's initialize() method", e);
                    }
                } catch (ClassNotFoundException unused) {
                    zzab().zzgq().zzao("Tag Manager is not found and thus will not be used");
                }
            }
            if (zzad().zze(zzr().zzag(), zzak.zzje) && "_cmp".equals(str2) && bundle.containsKey("gclid")) {
                zza("auto", "_lgclid", bundle.getString("gclid"), zzx().currentTimeMillis());
            }
            if (z3) {
                zzae();
                if (!"_iap".equals(str2)) {
                    zzjs zzz = this.zzj.zzz();
                    int i3 = 2;
                    if (zzz.zzp(NotificationCompat.CATEGORY_EVENT, str2)) {
                        if (!zzz.zza(NotificationCompat.CATEGORY_EVENT, zzgj.zzpn, str2)) {
                            i3 = 13;
                        } else if (zzz.zza(NotificationCompat.CATEGORY_EVENT, 40, str2)) {
                            i3 = 0;
                        }
                    }
                    if (i3 != 0) {
                        zzab().zzgm().zza("Invalid public event name. Event will not be logged (FE)", zzy().zzaj(str2));
                        this.zzj.zzz();
                        this.zzj.zzz().zza(i3, "_ev", zzjs.zza(str2, 40, true), str2 != null ? str2.length() : 0);
                        return;
                    }
                }
            }
            zzae();
            zzhr zzin = zzt().zzin();
            if (zzin != null && !bundle.containsKey("_sc")) {
                zzin.zzqx = true;
            }
            zzhq.zza(zzin, bundle, z && z3);
            boolean equals = "am".equals(str);
            boolean zzbq = zzjs.zzbq(str2);
            if (z && this.zzpv != null && !zzbq && !equals) {
                zzab().zzgr().zza("Passing event to registered event handler (FE)", zzy().zzaj(str2), zzy().zzc(bundle));
                this.zzpv.interceptEvent(str, str2, bundle, j);
            } else if (this.zzj.zzie()) {
                int zzbl = zzz().zzbl(str2);
                if (zzbl != 0) {
                    zzab().zzgm().zza("Invalid event name. Event will not be logged (FE)", zzy().zzaj(str2));
                    zzz();
                    String zza = zzjs.zza(str2, 40, true);
                    if (str2 != null) {
                        i2 = str2.length();
                    }
                    this.zzj.zzz().zza(str3, zzbl, "_ev", zza, i2);
                    return;
                }
                List<String> listOf = CollectionUtils.listOf((Object[]) new String[]{"_o", "_sn", "_sc", "_si"});
                String str4 = str2;
                Bundle zza2 = zzz().zza(str3, str2, bundle, listOf, z3, true);
                zzhr zzhr2 = (zza2 == null || !zza2.containsKey("_sc") || !zza2.containsKey("_si")) ? null : new zzhr(zza2.getString("_sn"), zza2.getString("_sc"), Long.valueOf(zza2.getLong("_si")).longValue());
                zzhr zzhr3 = zzhr2 == null ? zzin : zzhr2;
                if (zzad().zzz(str3)) {
                    zzae();
                    if (zzt().zzin() != null && "_ae".equals(str4)) {
                        long zzjb = zzv().zzjb();
                        if (zzjb > 0) {
                            zzz().zzb(zza2, zzjb);
                        }
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(zza2);
                long nextLong = zzz().zzjw().nextLong();
                if (zzad().zze(zzr().zzag(), zzak.zzid) && zzac().zzma.get() > 0 && zzac().zzx(j) && zzac().zzmd.get()) {
                    zzab().zzgs().zzao("Current session is expired, remove the session number and Id");
                    if (zzad().zze(zzr().zzag(), zzak.zzhz)) {
                        zza("auto", "_sid", (Object) null, zzx().currentTimeMillis());
                    }
                    if (zzad().zze(zzr().zzag(), zzak.zzia)) {
                        zza("auto", "_sno", (Object) null, zzx().currentTimeMillis());
                    }
                }
                if (zzad().zzy(zzr().zzag())) {
                    if (zza2.getLong(FirebaseAnalytics.Param.EXTEND_SESSION, 0) == 1) {
                        zzab().zzgs().zzao("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                        this.zzj.zzv().zza(j, true);
                    }
                }
                String[] strArr2 = (String[]) zza2.keySet().toArray(new String[bundle.size()]);
                Arrays.sort(strArr2);
                int length = strArr2.length;
                int i4 = 0;
                int i5 = 0;
                while (i4 < length) {
                    String str5 = strArr2[i4];
                    Object obj = zza2.get(str5);
                    zzz();
                    Bundle[] zzb = zzjs.zzb(obj);
                    if (zzb != null) {
                        strArr = strArr2;
                        zza2.putInt(str5, zzb.length);
                        i = length;
                        int i6 = 0;
                        while (i6 < zzb.length) {
                            Bundle bundle3 = zzb[i6];
                            zzhq.zza(zzhr3, bundle3, true);
                            Bundle zza3 = zzz().zza(str3, "_ep", bundle3, listOf, z3, false);
                            zza3.putString("_en", str4);
                            zza3.putLong("_eid", nextLong);
                            zza3.putString("_gn", str5);
                            zza3.putInt("_ll", zzb.length);
                            zza3.putInt("_i", i6);
                            arrayList2.add(zza3);
                            i6++;
                            zza2 = zza2;
                            arrayList2 = arrayList2;
                            str5 = str5;
                            zzhr3 = zzhr3;
                            listOf = listOf;
                            i5 = i5;
                            nextLong = nextLong;
                        }
                        list = listOf;
                        j2 = nextLong;
                        arrayList = arrayList2;
                        zzhr = zzhr3;
                        bundle2 = zza2;
                        i5 += zzb.length;
                    } else {
                        list = listOf;
                        strArr = strArr2;
                        i = length;
                        j2 = nextLong;
                        arrayList = arrayList2;
                        zzhr = zzhr3;
                        bundle2 = zza2;
                    }
                    i4++;
                    strArr2 = strArr;
                    zza2 = bundle2;
                    arrayList2 = arrayList;
                    nextLong = j2;
                    length = i;
                    zzhr3 = zzhr;
                    listOf = list;
                }
                if (i5 != 0) {
                    zza2.putLong("_eid", nextLong);
                    zza2.putInt("_epc", i5);
                }
                int i7 = 0;
                for (ArrayList arrayList3 = arrayList2; i7 < arrayList3.size(); arrayList3 = arrayList3) {
                    Bundle bundle4 = (Bundle) arrayList3.get(i7);
                    String str6 = i7 != 0 ? "_ep" : str4;
                    bundle4.putString("_o", str);
                    Bundle zzg = z2 ? zzz().zzg(bundle4) : bundle4;
                    zzab().zzgr().zza("Logging event (FE)", zzy().zzaj(str4), zzy().zzc(zzg));
                    zzs().zzc(new zzai(str6, new zzah(zzg), str, j), str3);
                    if (!equals) {
                        for (zzgn zzgn : this.zzpw) {
                            zzgn.onEvent(str, str2, new Bundle(zzg), j);
                        }
                    }
                    i7++;
                    str4 = str4;
                }
                zzae();
                if (zzt().zzin() != null && "_ae".equals(str4)) {
                    zzv().zza(true, true);
                }
            }
        } else {
            zzab().zzgr().zza("Dropping non-safelisted event. event name, origin", str2, str);
        }
    }

    public final void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        boolean z3;
        zzm();
        String str3 = str == null ? "app" : str;
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        if (z2) {
            if (this.zzpv != null && !zzjs.zzbq(str2)) {
                z3 = false;
                zzb(str3, str2, j, bundle2, z2, z3, !z, null);
            }
        }
        z3 = true;
        zzb(str3, str2, j, bundle2, z2, z3, !z, null);
    }

    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzaa().zza(new zzgr(this, str, str2, j, zzjs.zzh(bundle), z, z2, z3, str3));
    }

    public final void zzb(String str, String str2, Object obj, boolean z) {
        zza(str, str2, obj, z, zzx().currentTimeMillis());
    }

    public final void zza(String str, String str2, Object obj, boolean z, long j) {
        String str3 = str == null ? "app" : str;
        int i = 6;
        int i2 = 0;
        if (z) {
            i = zzz().zzbm(str2);
        } else {
            zzjs zzz = zzz();
            if (zzz.zzp("user property", str2)) {
                if (!zzz.zza("user property", zzgl.zzpp, str2)) {
                    i = 15;
                } else if (zzz.zza("user property", 24, str2)) {
                    i = 0;
                }
            }
        }
        if (i != 0) {
            zzz();
            String zza = zzjs.zza(str2, 24, true);
            if (str2 != null) {
                i2 = str2.length();
            }
            this.zzj.zzz().zza(i, "_ev", zza, i2);
        } else if (obj != null) {
            int zzc = zzz().zzc(str2, obj);
            if (zzc != 0) {
                zzz();
                String zza2 = zzjs.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i2 = String.valueOf(obj).length();
                }
                this.zzj.zzz().zza(zzc, "_ev", zza2, i2);
                return;
            }
            Object zzd = zzz().zzd(str2, obj);
            if (zzd != null) {
                zza(str3, str2, j, zzd);
            }
        } else {
            zza(str3, str2, j, (Object) null);
        }
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzaa().zza(new zzgq(this, str, str2, obj, j));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0098  */
    @WorkerThread
    public final void zza(String str, String str2, Object obj, long j) {
        Long l;
        String str3;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzo();
        zzm();
        zzbi();
        if (zzad().zze(zzr().zzag(), zzak.zzik) && FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS.equals(str2)) {
            if (obj instanceof String) {
                String str4 = (String) obj;
                if (!TextUtils.isEmpty(str4)) {
                    Long valueOf = Long.valueOf("false".equals(str4.toLowerCase(Locale.ENGLISH)) ? 1 : 0);
                    zzac().zzlx.zzau(valueOf.longValue() == 1 ? "true" : "false");
                    str3 = "_npa";
                    l = valueOf;
                    if (!this.zzj.isEnabled()) {
                        zzab().zzgr().zzao("User property not set since app measurement is disabled");
                        return;
                    } else if (this.zzj.zzie()) {
                        zzab().zzgr().zza("Setting user property (FE)", zzy().zzaj(str3), l);
                        zzs().zzb(new zzjn(str3, j, l, str));
                        return;
                    } else {
                        return;
                    }
                }
            }
            if (obj == null) {
                zzac().zzlx.zzau("unset");
                str3 = "_npa";
                l = obj;
                if (!this.zzj.isEnabled()) {
                }
            }
        }
        str3 = str2;
        l = obj;
        if (!this.zzj.isEnabled()) {
        }
    }

    public final List<zzjn> zzh(boolean z) {
        zzm();
        zzbi();
        zzab().zzgr().zzao("Fetching user attributes (FE)");
        if (zzaa().zzhp()) {
            zzab().zzgk().zzao("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        } else if (zzr.isMainThread()) {
            zzab().zzgk().zzao("Cannot get all user properties from main thread");
            return Collections.emptyList();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzj.zzaa().zza(new zzgt(this, atomicReference, z));
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzab().zzgn().zza("Interrupted waiting for get user properties", e);
                }
            }
            List<zzjn> list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            zzab().zzgn().zzao("Timed out waiting for get user properties");
            return Collections.emptyList();
        }
    }

    @Nullable
    public final String zzi() {
        zzm();
        return this.zzpy.get();
    }

    @Nullable
    public final String zzy(long j) {
        if (zzaa().zzhp()) {
            zzab().zzgk().zzao("Cannot retrieve app instance id from analytics worker thread");
            return null;
        } else if (zzr.isMainThread()) {
            zzab().zzgk().zzao("Cannot retrieve app instance id from main thread");
            return null;
        } else {
            long elapsedRealtime = zzx().elapsedRealtime();
            String zzz = zzz(120000);
            long elapsedRealtime2 = zzx().elapsedRealtime() - elapsedRealtime;
            return (zzz != null || elapsedRealtime2 >= 120000) ? zzz : zzz(120000 - elapsedRealtime2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzbg(@Nullable String str) {
        this.zzpy.set(str);
    }

    @Nullable
    private final String zzz(long j) {
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            zzaa().zza(new zzgs(this, atomicReference));
            try {
                atomicReference.wait(j);
            } catch (InterruptedException unused) {
                zzab().zzgn().zzao("Interrupted waiting for app instance id");
                return null;
            }
        }
        return (String) atomicReference.get();
    }

    public final void resetAnalyticsData(long j) {
        zzbg(null);
        zzaa().zza(new zzgv(this, j));
    }

    @WorkerThread
    public final void zzim() {
        zzo();
        zzm();
        zzbi();
        if (this.zzj.zzie()) {
            zzs().zzim();
            this.zzpz = false;
            String zzhh = zzac().zzhh();
            if (!TextUtils.isEmpty(zzhh)) {
                zzw().zzbi();
                if (!zzhh.equals(Build.VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", zzhh);
                    logEvent("auto", "_ou", bundle);
                }
            }
        }
    }

    @WorkerThread
    public final void zza(zzgk zzgk) {
        zzgk zzgk2;
        zzo();
        zzm();
        zzbi();
        if (!(zzgk == null || zzgk == (zzgk2 = this.zzpv))) {
            Preconditions.checkState(zzgk2 == null, "EventInterceptor already set.");
        }
        this.zzpv = zzgk;
    }

    public final void zza(zzgn zzgn) {
        zzm();
        zzbi();
        Preconditions.checkNotNull(zzgn);
        if (!this.zzpw.add(zzgn)) {
            zzab().zzgn().zzao("OnEventListener already registered");
        }
    }

    public final void zzb(zzgn zzgn) {
        zzm();
        zzbi();
        Preconditions.checkNotNull(zzgn);
        if (!this.zzpw.remove(zzgn)) {
            zzab().zzgn().zzao("OnEventListener had not been registered");
        }
    }

    public final void setConditionalUserProperty(Bundle bundle) {
        setConditionalUserProperty(bundle, zzx().currentTimeMillis());
    }

    public final void setConditionalUserProperty(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        zzm();
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            zzab().zzgn().zzao("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        zza(bundle2, j);
    }

    public final void zzd(Bundle bundle) {
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString("app_id"));
        zzl();
        zza(new Bundle(bundle), zzx().currentTimeMillis());
    }

    private final void zza(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        zzgg.zza(bundle, "app_id", String.class, null);
        zzgg.zza(bundle, "origin", String.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.NAME, String.class, null);
        zzgg.zza(bundle, "value", Object.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
        Preconditions.checkNotEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        Preconditions.checkNotEmpty(bundle.getString("origin"));
        Preconditions.checkNotNull(bundle.get("value"));
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j);
        String string = bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
        Object obj = bundle.get("value");
        if (zzz().zzbm(string) != 0) {
            zzab().zzgk().zza("Invalid conditional user property name", zzy().zzal(string));
        } else if (zzz().zzc(string, obj) != 0) {
            zzab().zzgk().zza("Invalid conditional user property value", zzy().zzal(string), obj);
        } else {
            Object zzd = zzz().zzd(string, obj);
            if (zzd == null) {
                zzab().zzgk().zza("Unable to normalize conditional user property value", zzy().zzal(string), obj);
                return;
            }
            zzgg.zza(bundle, zzd);
            long j2 = bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
            if (TextUtils.isEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME)) || (j2 <= 15552000000L && j2 >= 1)) {
                long j3 = bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
                if (j3 > 15552000000L || j3 < 1) {
                    zzab().zzgk().zza("Invalid conditional user property time to live", zzy().zzal(string), Long.valueOf(j3));
                } else {
                    zzaa().zza(new zzgx(this, bundle));
                }
            } else {
                zzab().zzgk().zza("Invalid conditional user property timeout", zzy().zzal(string), Long.valueOf(j2));
            }
        }
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zzm();
        zza((String) null, str, str2, bundle);
    }

    public final void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotEmpty(str);
        zzl();
        zza(str, str2, str3, bundle);
    }

    private final void zza(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zzx().currentTimeMillis();
        Preconditions.checkNotEmpty(str2);
        Bundle bundle2 = new Bundle();
        if (str != null) {
            bundle2.putString("app_id", str);
        }
        bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, str2);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, currentTimeMillis);
        if (str3 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str3);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        zzaa().zza(new zzgw(this, bundle2));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zze(Bundle bundle) {
        zzo();
        zzbi();
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        Preconditions.checkNotEmpty(bundle.getString("origin"));
        Preconditions.checkNotNull(bundle.get("value"));
        if (!this.zzj.isEnabled()) {
            zzab().zzgr().zzao("Conditional property not sent since collection is disabled");
            return;
        }
        zzjn zzjn = new zzjn(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP), bundle.get("value"), bundle.getString("origin"));
        try {
            zzai zza = zzz().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS), bundle.getString("origin"), 0, true, false);
            zzs().zzd(new zzq(bundle.getString("app_id"), bundle.getString("origin"), zzjn, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), false, bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), zzz().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS), bundle.getString("origin"), 0, true, false), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), zza, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzz().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), bundle.getString("origin"), 0, true, false)));
        } catch (IllegalArgumentException unused) {
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzf(Bundle bundle) {
        zzo();
        zzbi();
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        if (!this.zzj.isEnabled()) {
            zzab().zzgr().zzao("Conditional property not cleared since collection is disabled");
            return;
        }
        try {
            zzs().zzd(new zzq(bundle.getString("app_id"), bundle.getString("origin"), new zzjn(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), 0, null, null), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), bundle.getBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzz().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), bundle.getString("origin"), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), true, false)));
        } catch (IllegalArgumentException unused) {
        }
    }

    public final ArrayList<Bundle> zzn(String str, String str2) {
        zzm();
        return zze(null, str, str2);
    }

    public final ArrayList<Bundle> zzd(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzl();
        return zze(str, str2, str3);
    }

    @VisibleForTesting
    private final ArrayList<Bundle> zze(String str, String str2, String str3) {
        if (zzaa().zzhp()) {
            zzab().zzgk().zzao("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList<>(0);
        } else if (zzr.isMainThread()) {
            zzab().zzgk().zzao("Cannot get conditional user properties from main thread");
            return new ArrayList<>(0);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzj.zzaa().zza(new zzgz(this, atomicReference, str, str2, str3));
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzab().zzgn().zza("Interrupted waiting for get conditional user properties", str, e);
                }
            }
            List list = (List) atomicReference.get();
            if (list != null) {
                return zzjs.zzd(list);
            }
            zzab().zzgn().zza("Timed out waiting for get conditional user properties", str);
            return new ArrayList<>();
        }
    }

    public final Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        zzm();
        return zzb((String) null, str, str2, z);
    }

    public final Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        Preconditions.checkNotEmpty(str);
        zzl();
        return zzb(str, str2, str3, z);
    }

    @VisibleForTesting
    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        if (zzaa().zzhp()) {
            zzab().zzgk().zzao("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        } else if (zzr.isMainThread()) {
            zzab().zzgk().zzao("Cannot get user properties from main thread");
            return Collections.emptyMap();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzj.zzaa().zza(new zzhb(this, atomicReference, str, str2, str3, z));
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzab().zzgn().zza("Interrupted waiting for get user properties", e);
                }
            }
            List<zzjn> list = (List) atomicReference.get();
            if (list == null) {
                zzab().zzgn().zzao("Timed out waiting for get user properties");
                return Collections.emptyMap();
            }
            ArrayMap arrayMap = new ArrayMap(list.size());
            for (zzjn zzjn : list) {
                arrayMap.put(zzjn.name, zzjn.getValue());
            }
            return arrayMap;
        }
    }

    @Nullable
    public final String getCurrentScreenName() {
        zzhr zzio = this.zzj.zzt().zzio();
        if (zzio != null) {
            return zzio.zzqu;
        }
        return null;
    }

    @Nullable
    public final String getCurrentScreenClass() {
        zzhr zzio = this.zzj.zzt().zzio();
        if (zzio != null) {
            return zzio.zzqv;
        }
        return null;
    }

    @Nullable
    public final String getGmpAppId() {
        if (this.zzj.zzhx() != null) {
            return this.zzj.zzhx();
        }
        try {
            return GoogleServices.getGoogleAppId();
        } catch (IllegalStateException e) {
            this.zzj.zzab().zzgk().zza("getGoogleAppId failed with exception", e);
            return null;
        }
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
