package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.MainThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzx;
import com.google.android.gms.measurement.internal.zzix;

public final class zzit<T extends Context & zzix> {
    private final T zzrz;

    public zzit(T t) {
        Preconditions.checkNotNull(t);
        this.zzrz = t;
    }

    @MainThread
    public final void onCreate() {
        zzfj zza = zzfj.zza(this.zzrz, (zzx) null);
        zzef zzab = zza.zzab();
        zza.zzae();
        zzab.zzgs().zzao("Local AppMeasurementService is starting up");
    }

    @MainThread
    public final void onDestroy() {
        zzfj zza = zzfj.zza(this.zzrz, (zzx) null);
        zzef zzab = zza.zzab();
        zza.zzae();
        zzab.zzgs().zzao("Local AppMeasurementService is shutting down");
    }

    @MainThread
    public final int onStartCommand(Intent intent, int i, int i2) {
        zzfj zza = zzfj.zza(this.zzrz, (zzx) null);
        zzef zzab = zza.zzab();
        if (intent == null) {
            zzab.zzgn().zzao("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zza.zzae();
        zzab.zzgs().zza("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zze(new zzis(this, i2, zzab, intent));
        }
        return 2;
    }

    private final void zze(Runnable runnable) {
        zzjg zzm = zzjg.zzm(this.zzrz);
        zzm.zzaa().zza(new zziu(this, zzm, runnable));
    }

    @MainThread
    public final IBinder onBind(Intent intent) {
        if (intent == null) {
            zzab().zzgk().zzao("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzfk(zzjg.zzm(this.zzrz));
        }
        zzab().zzgn().zza("onBind received unknown action", action);
        return null;
    }

    @MainThread
    public final boolean onUnbind(Intent intent) {
        if (intent == null) {
            zzab().zzgk().zzao("onUnbind called with null intent");
            return true;
        }
        zzab().zzgs().zza("onUnbind called for intent. action", intent.getAction());
        return true;
    }

    @TargetApi(24)
    @MainThread
    public final boolean onStartJob(JobParameters jobParameters) {
        zzfj zza = zzfj.zza(this.zzrz, (zzx) null);
        zzef zzab = zza.zzab();
        String string = jobParameters.getExtras().getString("action");
        zza.zzae();
        zzab.zzgs().zza("Local AppMeasurementJobService called. action", string);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(string)) {
            return true;
        }
        zze(new zziv(this, zzab, jobParameters));
        return true;
    }

    @MainThread
    public final void onRebind(Intent intent) {
        if (intent == null) {
            zzab().zzgk().zzao("onRebind called with null intent");
            return;
        }
        zzab().zzgs().zza("onRebind called. action", intent.getAction());
    }

    private final zzef zzab() {
        return zzfj.zza(this.zzrz, (zzx) null).zzab();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzef zzef, JobParameters jobParameters) {
        zzef.zzgs().zzao("AppMeasurementJobService processed last upload request.");
        this.zzrz.zza(jobParameters, false);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, zzef zzef, Intent intent) {
        if (this.zzrz.zza(i)) {
            zzef.zzgs().zza("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            zzab().zzgs().zzao("Completed wakeful intent.");
            this.zzrz.zza(intent);
        }
    }
}
