package com.google.android.gms.internal.measurement;

public final class zzkf implements zzdb<zzki> {
    private static zzkf zzart = new zzkf();
    private final zzdb<zzki> zzapj;

    public static boolean zzze() {
        return ((zzki) zzart.get()).zzze();
    }

    private zzkf(zzdb<zzki> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzkf() {
        this(zzda.zzg(new zzkh()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzki get() {
        return this.zzapj.get();
    }
}
