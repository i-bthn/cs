package com.google.android.gms.internal.measurement;

public final class zzkl implements zzdb<zzko> {
    private static zzkl zzarx = new zzkl();
    private final zzdb<zzko> zzapj;

    public static boolean zzzh() {
        return ((zzko) zzarx.get()).zzzh();
    }

    private zzkl(zzdb<zzko> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzkl() {
        this(zzda.zzg(new zzkn()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzko get() {
        return this.zzapj.get();
    }
}
