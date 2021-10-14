package com.google.android.gms.internal.measurement;

public final class zzlj implements zzdb<zzlm> {
    private static zzlj zzasv = new zzlj();
    private final zzdb<zzlm> zzapj;

    public static boolean zzzw() {
        return ((zzlm) zzasv.get()).zzzw();
    }

    private zzlj(zzdb<zzlm> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzlj() {
        this(zzda.zzg(new zzll()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzlm get() {
        return this.zzapj.get();
    }
}
