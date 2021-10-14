package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
public final class zzgt implements Runnable {
    private final /* synthetic */ boolean zzbi;
    private final /* synthetic */ AtomicReference zzps;
    private final /* synthetic */ zzgp zzpt;

    zzgt(zzgp zzgp, AtomicReference atomicReference, boolean z) {
        this.zzpt = zzgp;
        this.zzps = atomicReference;
        this.zzbi = z;
    }

    public final void run() {
        this.zzpt.zzs().zza(this.zzps, this.zzbi);
    }
}
