package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* access modifiers changed from: package-private */
public final class zzht implements Runnable {
    private final /* synthetic */ zzhq zzqz;
    private final /* synthetic */ boolean zzra;
    private final /* synthetic */ zzhr zzrb;
    private final /* synthetic */ zzhr zzrc;

    zzht(zzhq zzhq, boolean z, zzhr zzhr, zzhr zzhr2) {
        this.zzqz = zzhq;
        this.zzra = z;
        this.zzrb = zzhr;
        this.zzrc = zzhr2;
    }

    public final void run() {
        boolean z;
        boolean z2 = false;
        if (this.zzqz.zzad().zzz(this.zzqz.zzr().zzag())) {
            z = this.zzra && this.zzqz.zzqo != null;
            if (z) {
                zzhq zzhq = this.zzqz;
                zzhq.zza((zzhq) zzhq.zzqo, (zzhr) true);
            }
        } else {
            if (this.zzra && this.zzqz.zzqo != null) {
                zzhq zzhq2 = this.zzqz;
                zzhq2.zza((zzhq) zzhq2.zzqo, (zzhr) true);
            }
            z = false;
        }
        zzhr zzhr = this.zzrb;
        if (zzhr == null || zzhr.zzqw != this.zzrc.zzqw || !zzjs.zzs(this.zzrb.zzqv, this.zzrc.zzqv) || !zzjs.zzs(this.zzrb.zzqu, this.zzrc.zzqu)) {
            z2 = true;
        }
        if (z2) {
            Bundle bundle = new Bundle();
            zzhq.zza(this.zzrc, bundle, true);
            zzhr zzhr2 = this.zzrb;
            if (zzhr2 != null) {
                if (zzhr2.zzqu != null) {
                    bundle.putString("_pn", this.zzrb.zzqu);
                }
                bundle.putString("_pc", this.zzrb.zzqv);
                bundle.putLong("_pi", this.zzrb.zzqw);
            }
            if (this.zzqz.zzad().zzz(this.zzqz.zzr().zzag()) && z) {
                long zzjb = this.zzqz.zzv().zzjb();
                if (zzjb > 0) {
                    this.zzqz.zzz().zzb(bundle, zzjb);
                }
            }
            this.zzqz.zzq().zza("auto", "_vs", bundle);
        }
        zzhq zzhq3 = this.zzqz;
        zzhq3.zzqo = this.zzrc;
        zzhq3.zzs().zza(this.zzrc);
    }
}
