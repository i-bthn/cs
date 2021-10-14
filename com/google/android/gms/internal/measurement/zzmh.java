package com.google.android.gms.internal.measurement;

public final class zzmh implements zzdb<zzmk> {
    private static zzmh zzatv = new zzmh();
    private final zzdb<zzmk> zzapj;

    public static boolean zzaan() {
        return ((zzmk) zzatv.get()).zzaan();
    }

    private zzmh(zzdb<zzmk> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzmh() {
        this(zzda.zzg(new zzmj()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzmk get() {
        return this.zzapj.get();
    }
}
