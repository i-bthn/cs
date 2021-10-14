package com.google.android.gms.internal.measurement;

public final class zzma implements zzdb<zzlz> {
    private static zzma zzatk = new zzma();
    private final zzdb<zzlz> zzapj;

    public static boolean zzaaf() {
        return ((zzlz) zzatk.get()).zzaaf();
    }

    public static boolean zzaag() {
        return ((zzlz) zzatk.get()).zzaag();
    }

    public static boolean zzaah() {
        return ((zzlz) zzatk.get()).zzaah();
    }

    private zzma(zzdb<zzlz> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzma() {
        this(zzda.zzg(new zzmc()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzlz get() {
        return this.zzapj.get();
    }
}
