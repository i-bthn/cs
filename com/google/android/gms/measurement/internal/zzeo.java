package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.work.WorkRequest;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

/* access modifiers changed from: package-private */
public final class zzeo extends zzge {
    @VisibleForTesting
    static final Pair<String, Long> zzlg = new Pair<>("", 0L);
    private SharedPreferences zzlh;
    public zzes zzli;
    public final zzet zzlj = new zzet(this, "last_upload", 0);
    public final zzet zzlk = new zzet(this, "last_upload_attempt", 0);
    public final zzet zzll = new zzet(this, "backoff", 0);
    public final zzet zzlm = new zzet(this, "last_delete_stale", 0);
    public final zzet zzln = new zzet(this, "midnight_offset", 0);
    public final zzet zzlo = new zzet(this, "first_open_time", 0);
    public final zzet zzlp = new zzet(this, "app_install_time", 0);
    public final zzev zzlq = new zzev(this, "app_instance_id", null);
    private String zzlr;
    private boolean zzls;
    private long zzlt;
    public final zzet zzlu = new zzet(this, "time_before_start", WorkRequest.MIN_BACKOFF_MILLIS);
    public final zzet zzlv = new zzet(this, "session_timeout", 1800000);
    public final zzeq zzlw = new zzeq(this, "start_new_session", true);
    public final zzev zzlx = new zzev(this, "non_personalized_ads", null);
    public final zzeq zzly = new zzeq(this, "use_dynamite_api", false);
    public final zzeq zzlz = new zzeq(this, "allow_remote_dynamite", false);
    public final zzet zzma = new zzet(this, "last_pause_time", 0);
    public final zzet zzmb = new zzet(this, "time_active", 0);
    public boolean zzmc;
    public zzeq zzmd = new zzeq(this, "app_backgrounded", false);
    public zzet zzme = new zzet(this, "deep_link_last_retrieved", -1);

    /* access modifiers changed from: package-private */
    @NonNull
    @WorkerThread
    public final Pair<String, Boolean> zzap(String str) {
        zzo();
        long elapsedRealtime = zzx().elapsedRealtime();
        String str2 = this.zzlr;
        if (str2 != null && elapsedRealtime < this.zzlt) {
            return new Pair<>(str2, Boolean.valueOf(this.zzls));
        }
        this.zzlt = elapsedRealtime + zzad().zza(str, zzak.zzgg);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
            if (advertisingIdInfo != null) {
                this.zzlr = advertisingIdInfo.getId();
                this.zzls = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzlr == null) {
                this.zzlr = "";
            }
        } catch (Exception e) {
            zzab().zzgr().zza("Unable to get advertising id", e);
            this.zzlr = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzlr, Boolean.valueOf(this.zzls));
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.measurement.internal.zzge
    public final boolean zzbk() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String zzaq(String str) {
        zzo();
        String str2 = (String) zzap(str).first;
        MessageDigest messageDigest = zzjs.getMessageDigest();
        if (messageDigest == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, messageDigest.digest(str2.getBytes())));
    }

    zzeo(zzfj zzfj) {
        super(zzfj);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.measurement.internal.zzge
    @WorkerThread
    public final void zzbl() {
        this.zzlh = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzmc = this.zzlh.getBoolean("has_been_opened", false);
        if (!this.zzmc) {
            SharedPreferences.Editor edit = this.zzlh.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.zzli = new zzes(this, "health_monitor", Math.max(0L, zzak.zzgh.get(null).longValue()));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final SharedPreferences zzhb() {
        zzo();
        zzbi();
        return this.zzlh;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzar(String str) {
        zzo();
        SharedPreferences.Editor edit = zzhb().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String zzhc() {
        zzo();
        return zzhb().getString("gmp_app_id", null);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzas(String str) {
        zzo();
        SharedPreferences.Editor edit = zzhb().edit();
        edit.putString("admob_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String zzhd() {
        zzo();
        return zzhb().getString("admob_app_id", null);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final Boolean zzhe() {
        zzo();
        if (!zzhb().contains("use_service")) {
            return null;
        }
        return Boolean.valueOf(zzhb().getBoolean("use_service", false));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzd(boolean z) {
        zzo();
        zzab().zzgs().zza("Setting useService", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzhb().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzhf() {
        zzo();
        zzab().zzgs().zzao("Clearing collection preferences.");
        if (zzad().zza(zzak.zzil)) {
            Boolean zzhg = zzhg();
            SharedPreferences.Editor edit = zzhb().edit();
            edit.clear();
            edit.apply();
            if (zzhg != null) {
                setMeasurementEnabled(zzhg.booleanValue());
                return;
            }
            return;
        }
        boolean contains = zzhb().contains("measurement_enabled");
        boolean z = true;
        if (contains) {
            z = zze(true);
        }
        SharedPreferences.Editor edit2 = zzhb().edit();
        edit2.clear();
        edit2.apply();
        if (contains) {
            setMeasurementEnabled(z);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void setMeasurementEnabled(boolean z) {
        zzo();
        zzab().zzgs().zza("Setting measurementEnabled", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzhb().edit();
        edit.putBoolean("measurement_enabled", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zze(boolean z) {
        zzo();
        return zzhb().getBoolean("measurement_enabled", z);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final Boolean zzhg() {
        zzo();
        if (zzhb().contains("measurement_enabled")) {
            return Boolean.valueOf(zzhb().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final String zzhh() {
        zzo();
        String string = zzhb().getString("previous_os_version", null);
        zzw().zzbi();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor edit = zzhb().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzf(boolean z) {
        zzo();
        zzab().zzgs().zza("Updating deferred analytics collection", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzhb().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzhi() {
        zzo();
        return zzhb().getBoolean("deferred_analytics_collection", false);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzhj() {
        return this.zzlh.contains("deferred_analytics_collection");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzx(long j) {
        return j - this.zzlv.get() > this.zzma.get();
    }
}
