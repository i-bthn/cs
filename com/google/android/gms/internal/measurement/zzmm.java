package com.google.android.gms.internal.measurement;

public final class zzmm implements zzdb<zzml> {
    private static zzmm zzaty = new zzmm();
    private final zzdb<zzml> zzapj;

    public static boolean zzaao() {
        return ((zzml) zzaty.get()).zzaao();
    }

    private zzmm(zzdb<zzml> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzmm() {
        this(zzda.zzg(new zzmn()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzml get() {
        return this.zzapj.get();
    }
}
