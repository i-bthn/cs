package com.google.android.gms.internal.measurement;

public final class zzmg implements zzdb<zzmf> {
    private static zzmg zzatu = new zzmg();
    private final zzdb<zzmf> zzapj;

    public static boolean zzaam() {
        return ((zzmf) zzatu.get()).zzaam();
    }

    private zzmg(zzdb<zzmf> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzmg() {
        this(zzda.zzg(new zzmi()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzmf get() {
        return this.zzapj.get();
    }
}
