package com.google.android.gms.internal.measurement;

public final class zzjh implements zzdb<zzjk> {
    private static zzjh zzapo = new zzjh();
    private final zzdb<zzjk> zzapj;

    public static boolean zzxj() {
        return ((zzjk) zzapo.get()).zzxj();
    }

    public static boolean zzxk() {
        return ((zzjk) zzapo.get()).zzxk();
    }

    public static boolean zzxl() {
        return ((zzjk) zzapo.get()).zzxl();
    }

    private zzjh(zzdb<zzjk> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzjh() {
        this(zzda.zzg(new zzjj()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzjk get() {
        return this.zzapj.get();
    }
}
