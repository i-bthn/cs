package com.google.android.gms.measurement.internal;

/* access modifiers changed from: package-private */
public final class zzjj implements Runnable {
    private final /* synthetic */ zzjg zztl;
    private final /* synthetic */ zzjm zztm;

    zzjj(zzjg zzjg, zzjm zzjm) {
        this.zztl = zzjg;
        this.zztm = zzjm;
    }

    public final void run() {
        this.zztl.zza((zzjg) this.zztm);
        this.zztl.start();
    }
}
