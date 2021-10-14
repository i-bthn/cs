package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;

/* access modifiers changed from: package-private */
public final class zziz extends zzaa {
    private final /* synthetic */ zziw zzsi;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zziz(zziw zziw, zzgh zzgh) {
        super(zzgh);
        this.zzsi = zziw;
    }

    @Override // com.google.android.gms.measurement.internal.zzaa
    @WorkerThread
    public final void run() {
        this.zzsi.zzja();
    }
}
