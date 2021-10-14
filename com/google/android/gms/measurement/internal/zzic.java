package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzp;

/* access modifiers changed from: package-private */
public final class zzic implements Runnable {
    private final /* synthetic */ zzp zzdi;
    private final /* synthetic */ zzai zzdm;
    private final /* synthetic */ String zzdn;
    private final /* synthetic */ zzhv zzrd;

    zzic(zzhv zzhv, zzai zzai, String str, zzp zzp) {
        this.zzrd = zzhv;
        this.zzdm = zzai;
        this.zzdn = str;
        this.zzdi = zzp;
    }

    public final void run() {
        try {
            zzdx zzdx = this.zzrd.zzrf;
            if (zzdx == null) {
                this.zzrd.zzab().zzgk().zzao("Discarding data. Failed to send event to service to bundle");
                return;
            }
            byte[] zza = zzdx.zza(this.zzdm, this.zzdn);
            this.zzrd.zzir();
            this.zzrd.zzz().zza(this.zzdi, zza);
        } catch (RemoteException e) {
            this.zzrd.zzab().zzgk().zza("Failed to send event to the service to bundle", e);
        } finally {
            this.zzrd.zzz().zza(this.zzdi, (byte[]) null);
        }
    }
}
