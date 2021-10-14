package com.google.android.gms.internal.measurement;

public final class zzkw implements zzdb<zzkv> {
    private static zzkw zzash = new zzkw();
    private final zzdb<zzkv> zzapj;

    public static boolean zzzm() {
        return ((zzkv) zzash.get()).zzzm();
    }

    public static boolean zzzn() {
        return ((zzkv) zzash.get()).zzzn();
    }

    private zzkw(zzdb<zzkv> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzkw() {
        this(zzda.zzg(new zzky()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzkv get() {
        return this.zzapj.get();
    }
}
