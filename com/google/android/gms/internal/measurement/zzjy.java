package com.google.android.gms.internal.measurement;

public final class zzjy implements zzdb<zzjx> {
    private static zzjy zzarl = new zzjy();
    private final zzdb<zzjx> zzapj;

    public static boolean zzyz() {
        return ((zzjx) zzarl.get()).zzyz();
    }

    private zzjy(zzdb<zzjx> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzjy() {
        this(zzda.zzg(new zzka()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzjx get() {
        return this.zzapj.get();
    }
}
