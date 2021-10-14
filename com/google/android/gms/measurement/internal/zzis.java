package com.google.android.gms.measurement.internal;

import android.content.Intent;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzis implements Runnable {
    private final int zzqi;
    private final zzit zzrw;
    private final zzef zzrx;
    private final Intent zzry;

    zzis(zzit zzit, int i, zzef zzef, Intent intent) {
        this.zzrw = zzit;
        this.zzqi = i;
        this.zzrx = zzef;
        this.zzry = intent;
    }

    public final void run() {
        this.zzrw.zza(this.zzqi, this.zzrx, this.zzry);
    }
}
