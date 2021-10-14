package com.google.android.gms.measurement.internal;

/* access modifiers changed from: package-private */
public final class zzif extends zzaa {
    private final /* synthetic */ zzhv zzrd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzif(zzhv zzhv, zzgh zzgh) {
        super(zzgh);
        this.zzrd = zzhv;
    }

    @Override // com.google.android.gms.measurement.internal.zzaa
    public final void run() {
        this.zzrd.zzab().zzgn().zzao("Tasks have been queued for a long time");
    }
}
