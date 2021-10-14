package com.google.android.gms.internal.measurement;

public final class zzke implements zzdb<zzkd> {
    private static zzke zzars = new zzke();
    private final zzdb<zzkd> zzapj;

    public static boolean zzzd() {
        return ((zzkd) zzars.get()).zzzd();
    }

    private zzke(zzdb<zzkd> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzke() {
        this(zzda.zzg(new zzkg()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzkd get() {
        return this.zzapj.get();
    }
}
