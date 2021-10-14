package com.google.android.gms.internal.measurement;

public final class zzjm implements zzdb<zzjl> {
    private static zzjm zzapt = new zzjm();
    private final zzdb<zzjl> zzapj;

    public static boolean zzxm() {
        return ((zzjl) zzapt.get()).zzxm();
    }

    private zzjm(zzdb<zzjl> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzjm() {
        this(zzda.zzg(new zzjo()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzjl get() {
        return this.zzapj.get();
    }
}
