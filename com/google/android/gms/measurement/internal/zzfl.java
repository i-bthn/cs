package com.google.android.gms.measurement.internal;

/* access modifiers changed from: package-private */
public final class zzfl implements Runnable {
    private final /* synthetic */ zzgm zzpd;
    private final /* synthetic */ zzfj zzpe;

    zzfl(zzfj zzfj, zzgm zzgm) {
        this.zzpe = zzfj;
        this.zzpd = zzgm;
    }

    public final void run() {
        this.zzpe.zza((zzfj) this.zzpd);
        this.zzpe.start();
    }
}
