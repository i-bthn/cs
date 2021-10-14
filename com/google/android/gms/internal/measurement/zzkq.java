package com.google.android.gms.internal.measurement;

public final class zzkq implements zzdb<zzkp> {
    private static zzkq zzasb = new zzkq();
    private final zzdb<zzkp> zzapj;

    public static boolean zzzi() {
        return ((zzkp) zzasb.get()).zzzi();
    }

    private zzkq(zzdb<zzkp> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzkq() {
        this(zzda.zzg(new zzks()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzkp get() {
        return this.zzapj.get();
    }
}
