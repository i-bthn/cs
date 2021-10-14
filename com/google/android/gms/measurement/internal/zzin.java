package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;

public final class zzin implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    final /* synthetic */ zzhv zzrd;
    private volatile boolean zzrt;
    private volatile zzec zzru;

    protected zzin(zzhv zzhv) {
        this.zzrd = zzhv;
    }

    public final void zzb(Intent intent) {
        this.zzrd.zzo();
        Context context = this.zzrd.getContext();
        ConnectionTracker instance = ConnectionTracker.getInstance();
        synchronized (this) {
            if (this.zzrt) {
                this.zzrd.zzab().zzgs().zzao("Connection attempt already in progress");
                return;
            }
            this.zzrd.zzab().zzgs().zzao("Using local app measurement service");
            this.zzrt = true;
            instance.bindService(context, intent, this.zzrd.zzre, 129);
        }
    }

    public final void zziw() {
        if (this.zzru != null && (this.zzru.isConnected() || this.zzru.isConnecting())) {
            this.zzru.disconnect();
        }
        this.zzru = null;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceConnected");
        synchronized (this) {
            if (iBinder == null) {
                this.zzrt = false;
                this.zzrd.zzab().zzgk().zzao("Service connected with null binder");
                return;
            }
            zzdz zzdz = null;
            try {
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                    if (iBinder != null) {
                        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                        if (queryLocalInterface instanceof zzdx) {
                            zzdz = (zzdx) queryLocalInterface;
                        } else {
                            zzdz = new zzdz(iBinder);
                        }
                    }
                    this.zzrd.zzab().zzgs().zzao("Bound to IMeasurementService interface");
                } else {
                    this.zzrd.zzab().zzgk().zza("Got binder with a wrong descriptor", interfaceDescriptor);
                }
            } catch (RemoteException unused) {
                this.zzrd.zzab().zzgk().zzao("Service connect failed to get IMeasurementService");
            }
            if (zzdz == null) {
                this.zzrt = false;
                try {
                    ConnectionTracker.getInstance().unbindService(this.zzrd.getContext(), this.zzrd.zzre);
                } catch (IllegalArgumentException unused2) {
                }
            } else {
                this.zzrd.zzaa().zza(new zzim(this, zzdz));
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.zzrd.zzab().zzgr().zzao("Service disconnected");
        this.zzrd.zzaa().zza(new zzip(this, componentName));
    }

    public final void zzix() {
        this.zzrd.zzo();
        Context context = this.zzrd.getContext();
        synchronized (this) {
            if (this.zzrt) {
                this.zzrd.zzab().zzgs().zzao("Connection attempt already in progress");
            } else if (this.zzru == null || (!this.zzru.isConnecting() && !this.zzru.isConnected())) {
                this.zzru = new zzec(context, Looper.getMainLooper(), this, this);
                this.zzrd.zzab().zzgs().zzao("Connecting to remote service");
                this.zzrt = true;
                this.zzru.checkAvailabilityAndConnect();
            } else {
                this.zzrd.zzab().zzgs().zzao("Already awaiting connection attempt");
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                this.zzrd.zzaa().zza(new zzio(this, (zzdx) this.zzru.getService()));
            } catch (DeadObjectException | IllegalStateException unused) {
                this.zzru = null;
                this.zzrt = false;
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.zzrd.zzab().zzgr().zzao("Service connection suspended");
        this.zzrd.zzaa().zza(new zzir(this));
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        zzef zzhs = this.zzrd.zzj.zzhs();
        if (zzhs != null) {
            zzhs.zzgn().zza("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzrt = false;
            this.zzru = null;
        }
        this.zzrd.zzaa().zza(new zziq(this));
    }

    static /* synthetic */ boolean zza(zzin zzin, boolean z) {
        zzin.zzrt = false;
        return false;
    }
}
