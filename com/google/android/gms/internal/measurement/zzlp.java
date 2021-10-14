package com.google.android.gms.internal.measurement;

public final class zzlp implements zzdb<zzls> {
    private static zzlp zzasz = new zzlp();
    private final zzdb<zzls> zzapj;

    public static boolean zzzy() {
        return ((zzls) zzasz.get()).zzzy();
    }

    private zzlp(zzdb<zzls> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzlp() {
        this(zzda.zzg(new zzlr()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzls get() {
        return this.zzapj.get();
    }
}
