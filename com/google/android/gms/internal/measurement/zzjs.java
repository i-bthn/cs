package com.google.android.gms.internal.measurement;

public final class zzjs implements zzdb<zzjr> {
    private static zzjs zzarh = new zzjs();
    private final zzdb<zzjr> zzapj;

    public static boolean zzyx() {
        return ((zzjr) zzarh.get()).zzyx();
    }

    private zzjs(zzdb<zzjr> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzjs() {
        this(zzda.zzg(new zzju()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzjr get() {
        return this.zzapj.get();
    }
}
