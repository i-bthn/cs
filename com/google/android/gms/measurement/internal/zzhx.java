package com.google.android.gms.measurement.internal;

/* access modifiers changed from: package-private */
public final class zzhx implements Runnable {
    private final /* synthetic */ zzn zzpg;
    private final /* synthetic */ zzjn zzpi;
    private final /* synthetic */ zzhv zzrd;
    private final /* synthetic */ boolean zzrm;

    zzhx(zzhv zzhv, boolean z, zzjn zzjn, zzn zzn) {
        this.zzrd = zzhv;
        this.zzrm = z;
        this.zzpi = zzjn;
        this.zzpg = zzn;
    }

    public final void run() {
        zzdx zzdx = this.zzrd.zzrf;
        if (zzdx == null) {
            this.zzrd.zzab().zzgk().zzao("Discarding data. Failed to set user attribute");
            return;
        }
        this.zzrd.zza(zzdx, this.zzrm ? null : this.zzpi, this.zzpg);
        this.zzrd.zzir();
    }
}
