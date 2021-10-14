package com.google.android.gms.measurement.internal;

final class zzjf extends zzaa {
    private final /* synthetic */ zzjg zzsa;
    private final /* synthetic */ zzjc zzsm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjf(zzjc zzjc, zzgh zzgh, zzjg zzjg) {
        super(zzgh);
        this.zzsm = zzjc;
        this.zzsa = zzjg;
    }

    @Override // com.google.android.gms.measurement.internal.zzaa
    public final void run() {
        this.zzsm.cancel();
        this.zzsm.zzab().zzgs().zzao("Starting upload from DelayedRunnable");
        this.zzsa.zzjl();
    }
}
