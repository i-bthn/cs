package com.google.android.gms.internal.measurement;

public final class zzld implements zzdb<zzlg> {
    private static zzld zzasn = new zzld();
    private final zzdb<zzlg> zzapj;

    public static boolean zzzq() {
        return ((zzlg) zzasn.get()).zzzq();
    }

    public static double zzzr() {
        return ((zzlg) zzasn.get()).zzzr();
    }

    public static long zzzs() {
        return ((zzlg) zzasn.get()).zzzs();
    }

    public static long zzzt() {
        return ((zzlg) zzasn.get()).zzzt();
    }

    public static String zzzu() {
        return ((zzlg) zzasn.get()).zzzu();
    }

    private zzld(zzdb<zzlg> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzld() {
        this(zzda.zzg(new zzlf()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzlg get() {
        return this.zzapj.get();
    }
}
