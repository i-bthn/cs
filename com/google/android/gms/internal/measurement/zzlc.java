package com.google.android.gms.internal.measurement;

public final class zzlc implements zzdb<zzlb> {
    private static zzlc zzasm = new zzlc();
    private final zzdb<zzlb> zzapj;

    public static boolean zzzp() {
        return ((zzlb) zzasm.get()).zzzp();
    }

    private zzlc(zzdb<zzlb> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzlc() {
        this(zzda.zzg(new zzle()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzlb get() {
        return this.zzapj.get();
    }
}
