package com.google.android.gms.internal.measurement;

public final class zzlv implements zzdb<zzly> {
    private static zzlv zzatd = new zzlv();
    private final zzdb<zzly> zzapj;

    public static boolean zzaad() {
        return ((zzly) zzatd.get()).zzaad();
    }

    public static boolean zzaae() {
        return ((zzly) zzatd.get()).zzaae();
    }

    private zzlv(zzdb<zzly> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzlv() {
        this(zzda.zzg(new zzlx()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzly get() {
        return this.zzapj.get();
    }
}
