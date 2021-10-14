package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* access modifiers changed from: package-private */
public final class zzia implements Runnable {
    private final /* synthetic */ zzn zzpg;
    private final /* synthetic */ zzhv zzrd;
    private final /* synthetic */ boolean zzrn;

    zzia(zzhv zzhv, zzn zzn, boolean z) {
        this.zzrd = zzhv;
        this.zzpg = zzn;
        this.zzrn = z;
    }

    public final void run() {
        zzdx zzdx = this.zzrd.zzrf;
        if (zzdx == null) {
            this.zzrd.zzab().zzgk().zzao("Discarding data. Failed to send app launch");
            return;
        }
        try {
            zzdx.zza(this.zzpg);
            if (this.zzrn) {
                this.zzrd.zzu().zzgi();
            }
            this.zzrd.zza(zzdx, (AbstractSafeParcelable) null, this.zzpg);
            this.zzrd.zzir();
        } catch (RemoteException e) {
            this.zzrd.zzab().zzgk().zza("Failed to send app launch to the service", e);
        }
    }
}
