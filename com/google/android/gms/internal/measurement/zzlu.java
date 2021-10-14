package com.google.android.gms.internal.measurement;

public final class zzlu implements zzdb<zzlt> {
    private static zzlu zzatc = new zzlu();
    private final zzdb<zzlt> zzapj;

    public static boolean zzzz() {
        return ((zzlt) zzatc.get()).zzzz();
    }

    public static boolean zzaaa() {
        return ((zzlt) zzatc.get()).zzaaa();
    }

    public static boolean zzaab() {
        return ((zzlt) zzatc.get()).zzaab();
    }

    public static boolean zzaac() {
        return ((zzlt) zzatc.get()).zzaac();
    }

    private zzlu(zzdb<zzlt> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzlu() {
        this(zzda.zzg(new zzlw()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzlt get() {
        return this.zzapj.get();
    }
}
