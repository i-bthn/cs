package com.google.android.gms.measurement.internal;

/* access modifiers changed from: package-private */
public final class zzhs implements Runnable {
    private final /* synthetic */ zzhr zzqy;
    private final /* synthetic */ zzhq zzqz;

    zzhs(zzhq zzhq, zzhr zzhr) {
        this.zzqz = zzhq;
        this.zzqy = zzhr;
    }

    public final void run() {
        this.zzqz.zza((zzhq) this.zzqy, (zzhr) false);
        zzhq zzhq = this.zzqz;
        zzhq.zzqo = null;
        zzhq.zzs().zza((zzhr) null);
    }
}
