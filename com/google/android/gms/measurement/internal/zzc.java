package com.google.android.gms.measurement.internal;

/* access modifiers changed from: package-private */
public final class zzc implements Runnable {
    private final /* synthetic */ String zzbb;
    private final /* synthetic */ long zzcb;
    private final /* synthetic */ zza zzcc;

    zzc(zza zza, String str, long j) {
        this.zzcc = zza;
        this.zzbb = str;
        this.zzcb = j;
    }

    public final void run() {
        this.zzcc.zza((zza) this.zzbb, (String) this.zzcb);
    }
}
