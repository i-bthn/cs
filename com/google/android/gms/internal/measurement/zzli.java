package com.google.android.gms.internal.measurement;

public final class zzli implements zzdb<zzlh> {
    private static zzli zzasu = new zzli();
    private final zzdb<zzlh> zzapj;

    public static boolean zzzv() {
        return ((zzlh) zzasu.get()).zzzv();
    }

    private zzli(zzdb<zzlh> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzli() {
        this(zzda.zzg(new zzlk()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzlh get() {
        return this.zzapj.get();
    }
}
