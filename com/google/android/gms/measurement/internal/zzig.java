package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;

/* access modifiers changed from: package-private */
public final class zzig implements Runnable {
    private final /* synthetic */ zzn zzpg;
    private final /* synthetic */ zzhv zzrd;
    private final /* synthetic */ boolean zzrm;
    private final /* synthetic */ boolean zzro;
    private final /* synthetic */ zzq zzrp;
    private final /* synthetic */ zzq zzrq;

    zzig(zzhv zzhv, boolean z, boolean z2, zzq zzq, zzn zzn, zzq zzq2) {
        this.zzrd = zzhv;
        this.zzro = z;
        this.zzrm = z2;
        this.zzrp = zzq;
        this.zzpg = zzn;
        this.zzrq = zzq2;
    }

    public final void run() {
        zzdx zzdx = this.zzrd.zzrf;
        if (zzdx == null) {
            this.zzrd.zzab().zzgk().zzao("Discarding data. Failed to send conditional user property to service");
            return;
        }
        if (this.zzro) {
            this.zzrd.zza(zzdx, this.zzrm ? null : this.zzrp, this.zzpg);
        } else {
            try {
                if (TextUtils.isEmpty(this.zzrq.packageName)) {
                    zzdx.zza(this.zzrp, this.zzpg);
                } else {
                    zzdx.zzb(this.zzrp);
                }
            } catch (RemoteException e) {
                this.zzrd.zzab().zzgk().zza("Failed to send conditional user property to the service", e);
            }
        }
        this.zzrd.zzir();
    }
}
