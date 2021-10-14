package com.google.android.gms.internal.measurement;

public final class zzlo implements zzdb<zzln> {
    private static zzlo zzasy = new zzlo();
    private final zzdb<zzln> zzapj;

    public static boolean zzzx() {
        return ((zzln) zzasy.get()).zzzx();
    }

    private zzlo(zzdb<zzln> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzlo() {
        this(zzda.zzg(new zzlq()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzln get() {
        return this.zzapj.get();
    }
}
