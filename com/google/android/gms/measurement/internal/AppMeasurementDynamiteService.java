package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzn;
import com.google.android.gms.internal.measurement.zzp;
import com.google.android.gms.internal.measurement.zzq;
import com.google.android.gms.internal.measurement.zzv;
import com.google.android.gms.internal.measurement.zzx;
import java.util.Map;

@DynamiteApi
public class AppMeasurementDynamiteService extends zzn {
    private Map<Integer, zzgn> zzdk = new ArrayMap();
    @VisibleForTesting
    zzfj zzj = null;

    class zza implements zzgn {
        private zzq zzdo;

        zza(zzq zzq) {
            this.zzdo = zzq;
        }

        @Override // com.google.android.gms.measurement.internal.zzgn
        public final void onEvent(String str, String str2, Bundle bundle, long j) {
            try {
                this.zzdo.onEvent(str, str2, bundle, j);
            } catch (RemoteException e) {
                AppMeasurementDynamiteService.this.zzj.zzab().zzgn().zza("Event listener threw exception", e);
            }
        }
    }

    class zzb implements zzgk {
        private zzq zzdo;

        zzb(zzq zzq) {
            this.zzdo = zzq;
        }

        @Override // com.google.android.gms.measurement.internal.zzgk
        public final void interceptEvent(String str, String str2, Bundle bundle, long j) {
            try {
                this.zzdo.onEvent(str, str2, bundle, j);
            } catch (RemoteException e) {
                AppMeasurementDynamiteService.this.zzj.zzab().zzgn().zza("Event interceptor threw exception", e);
            }
        }
    }

    private final void zzbi() {
        if (this.zzj == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void initialize(IObjectWrapper iObjectWrapper, zzx zzx, long j) throws RemoteException {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzfj zzfj = this.zzj;
        if (zzfj == null) {
            this.zzj = zzfj.zza(context, zzx);
        } else {
            zzfj.zzab().zzgn().zzao("Attempting to initialize multiple times");
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) throws RemoteException {
        zzbi();
        this.zzj.zzq().logEvent(str, str2, bundle, z, z2, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j) throws RemoteException {
        zzbi();
        this.zzj.zzq().zza(str, str2, ObjectWrapper.unwrap(iObjectWrapper), z, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void setUserId(String str, long j) throws RemoteException {
        zzbi();
        this.zzj.zzq().zza(null, "_id", str, true, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j) throws RemoteException {
        zzbi();
        this.zzj.zzt().setCurrentScreen((Activity) ObjectWrapper.unwrap(iObjectWrapper), str, str2);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void setMeasurementEnabled(boolean z, long j) throws RemoteException {
        zzbi();
        this.zzj.zzq().setMeasurementEnabled(z);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void resetAnalyticsData(long j) throws RemoteException {
        zzbi();
        this.zzj.zzq().resetAnalyticsData(j);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void setMinimumSessionDuration(long j) throws RemoteException {
        zzbi();
        this.zzj.zzq().setMinimumSessionDuration(j);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void setSessionTimeoutDuration(long j) throws RemoteException {
        zzbi();
        this.zzj.zzq().setSessionTimeoutDuration(j);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void getMaxUserProperties(String str, zzp zzp) throws RemoteException {
        zzbi();
        this.zzj.zzq();
        Preconditions.checkNotEmpty(str);
        this.zzj.zzz().zza(zzp, 25);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void getCurrentScreenName(zzp zzp) throws RemoteException {
        zzbi();
        zza(zzp, this.zzj.zzq().getCurrentScreenName());
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void getCurrentScreenClass(zzp zzp) throws RemoteException {
        zzbi();
        zza(zzp, this.zzj.zzq().getCurrentScreenClass());
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void getCachedAppInstanceId(zzp zzp) throws RemoteException {
        zzbi();
        zza(zzp, this.zzj.zzq().zzi());
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void getAppInstanceId(zzp zzp) throws RemoteException {
        zzbi();
        this.zzj.zzaa().zza(new zzh(this, zzp));
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void getDeepLink(zzp zzp) throws RemoteException {
        zzbi();
        zzgp zzq = this.zzj.zzq();
        zzq.zzo();
        if (!zzq.zzad().zzd(null, zzak.zzjc)) {
            zzq.zzz().zzb(zzp, "");
        } else if (zzq.zzac().zzme.get() > 0) {
            zzq.zzz().zzb(zzp, "");
        } else {
            zzq.zzac().zzme.set(zzq.zzx().currentTimeMillis());
            zzq.zzj.zza(zzp);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void getGmpAppId(zzp zzp) throws RemoteException {
        zzbi();
        zza(zzp, this.zzj.zzq().getGmpAppId());
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void generateEventId(zzp zzp) throws RemoteException {
        zzbi();
        this.zzj.zzz().zza(zzp, this.zzj.zzz().zzjv());
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void beginAdUnitExposure(String str, long j) throws RemoteException {
        zzbi();
        this.zzj.zzp().beginAdUnitExposure(str, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void endAdUnitExposure(String str, long j) throws RemoteException {
        zzbi();
        this.zzj.zzp().endAdUnitExposure(str, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void initForTests(Map map) throws RemoteException {
        zzbi();
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void logEventAndBundle(String str, String str2, Bundle bundle, zzp zzp, long j) throws RemoteException {
        zzbi();
        Preconditions.checkNotEmpty(str2);
        (bundle != null ? new Bundle(bundle) : new Bundle()).putString("_o", "app");
        this.zzj.zzaa().zza(new zzj(this, zzp, new zzai(str2, new zzah(bundle), "app", j), str));
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void onActivityStarted(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzbi();
        zzhj zzhj = this.zzj.zzq().zzpu;
        if (zzhj != null) {
            this.zzj.zzq().zzif();
            zzhj.onActivityStarted((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void onActivityStopped(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzbi();
        zzhj zzhj = this.zzj.zzq().zzpu;
        if (zzhj != null) {
            this.zzj.zzq().zzif();
            zzhj.onActivityStopped((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j) throws RemoteException {
        zzbi();
        zzhj zzhj = this.zzj.zzq().zzpu;
        if (zzhj != null) {
            this.zzj.zzq().zzif();
            zzhj.onActivityCreated((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzbi();
        zzhj zzhj = this.zzj.zzq().zzpu;
        if (zzhj != null) {
            this.zzj.zzq().zzif();
            zzhj.onActivityDestroyed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void onActivityPaused(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzbi();
        zzhj zzhj = this.zzj.zzq().zzpu;
        if (zzhj != null) {
            this.zzj.zzq().zzif();
            zzhj.onActivityPaused((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void onActivityResumed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zzbi();
        zzhj zzhj = this.zzj.zzq().zzpu;
        if (zzhj != null) {
            this.zzj.zzq().zzif();
            zzhj.onActivityResumed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzp zzp, long j) throws RemoteException {
        zzbi();
        zzhj zzhj = this.zzj.zzq().zzpu;
        Bundle bundle = new Bundle();
        if (zzhj != null) {
            this.zzj.zzq().zzif();
            zzhj.onActivitySaveInstanceState((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
        try {
            zzp.zzb(bundle);
        } catch (RemoteException e) {
            this.zzj.zzab().zzgn().zza("Error returning bundle value to wrapper", e);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void performAction(Bundle bundle, zzp zzp, long j) throws RemoteException {
        zzbi();
        zzp.zzb(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void getUserProperties(String str, String str2, boolean z, zzp zzp) throws RemoteException {
        zzbi();
        this.zzj.zzaa().zza(new zzi(this, zzp, str, str2, z));
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void logHealthData(int i, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Object obj;
        Object obj2;
        zzbi();
        Object obj3 = null;
        if (iObjectWrapper == null) {
            obj = null;
        } else {
            obj = ObjectWrapper.unwrap(iObjectWrapper);
        }
        if (iObjectWrapper2 == null) {
            obj2 = null;
        } else {
            obj2 = ObjectWrapper.unwrap(iObjectWrapper2);
        }
        if (iObjectWrapper3 != null) {
            obj3 = ObjectWrapper.unwrap(iObjectWrapper3);
        }
        this.zzj.zzab().zza(i, true, false, str, obj, obj2, obj3);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void setEventInterceptor(zzq zzq) throws RemoteException {
        zzbi();
        zzgp zzq2 = this.zzj.zzq();
        zzb zzb2 = new zzb(zzq);
        zzq2.zzm();
        zzq2.zzbi();
        zzq2.zzaa().zza(new zzgu(zzq2, zzb2));
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void registerOnMeasurementEventListener(zzq zzq) throws RemoteException {
        zzbi();
        zzgn zzgn = this.zzdk.get(Integer.valueOf(zzq.id()));
        if (zzgn == null) {
            zzgn = new zza(zzq);
            this.zzdk.put(Integer.valueOf(zzq.id()), zzgn);
        }
        this.zzj.zzq().zza(zzgn);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void unregisterOnMeasurementEventListener(zzq zzq) throws RemoteException {
        zzbi();
        zzgn remove = this.zzdk.remove(Integer.valueOf(zzq.id()));
        if (remove == null) {
            remove = new zza(zzq);
        }
        this.zzj.zzq().zzb(remove);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void setInstanceIdProvider(zzv zzv) throws RemoteException {
        zzbi();
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void setConditionalUserProperty(Bundle bundle, long j) throws RemoteException {
        zzbi();
        if (bundle == null) {
            this.zzj.zzab().zzgk().zzao("Conditional user property must not be null");
        } else {
            this.zzj.zzq().setConditionalUserProperty(bundle, j);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        zzbi();
        this.zzj.zzq().clearConditionalUserProperty(str, str2, bundle);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void getConditionalUserProperties(String str, String str2, zzp zzp) throws RemoteException {
        zzbi();
        this.zzj.zzaa().zza(new zzl(this, zzp, str, str2));
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void getTestFlag(zzp zzp, int i) throws RemoteException {
        zzbi();
        switch (i) {
            case 0:
                this.zzj.zzz().zzb(zzp, this.zzj.zzq().zzih());
                return;
            case 1:
                this.zzj.zzz().zza(zzp, this.zzj.zzq().zzii().longValue());
                return;
            case 2:
                zzjs zzz = this.zzj.zzz();
                double doubleValue = this.zzj.zzq().zzik().doubleValue();
                Bundle bundle = new Bundle();
                bundle.putDouble("r", doubleValue);
                try {
                    zzp.zzb(bundle);
                    return;
                } catch (RemoteException e) {
                    zzz.zzj.zzab().zzgn().zza("Error returning double value to wrapper", e);
                    return;
                }
            case 3:
                this.zzj.zzz().zza(zzp, this.zzj.zzq().zzij().intValue());
                return;
            case 4:
                this.zzj.zzz().zza(zzp, this.zzj.zzq().zzig().booleanValue());
                return;
            default:
                return;
        }
    }

    private final void zza(zzp zzp, String str) {
        this.zzj.zzz().zzb(zzp, str);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void setDataCollectionEnabled(boolean z) throws RemoteException {
        zzbi();
        this.zzj.zzq().zza(z);
    }

    @Override // com.google.android.gms.internal.measurement.zzk
    public void isDataCollectionEnabled(zzp zzp) throws RemoteException {
        zzbi();
        this.zzj.zzaa().zza(new zzk(this, zzp));
    }
}
