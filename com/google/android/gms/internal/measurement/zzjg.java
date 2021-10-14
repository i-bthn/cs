package com.google.android.gms.internal.measurement;

public final class zzjg implements zzdb<zzjf> {
    private static zzjg zzapn = new zzjg();
    private final zzdb<zzjf> zzapj;

    public static boolean zzxi() {
        return ((zzjf) zzapn.get()).zzxi();
    }

    private zzjg(zzdb<zzjf> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzjg() {
        this(zzda.zzg(new zzji()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzjf get() {
        return this.zzapj.get();
    }
}
