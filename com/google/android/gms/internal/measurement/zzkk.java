package com.google.android.gms.internal.measurement;

public final class zzkk implements zzdb<zzkj> {
    private static zzkk zzarw = new zzkk();
    private final zzdb<zzkj> zzapj;

    public static boolean zzzf() {
        return ((zzkj) zzarw.get()).zzzf();
    }

    public static boolean zzzg() {
        return ((zzkj) zzarw.get()).zzzg();
    }

    private zzkk(zzdb<zzkj> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzkk() {
        this(zzda.zzg(new zzkm()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzkj get() {
        return this.zzapj.get();
    }
}
