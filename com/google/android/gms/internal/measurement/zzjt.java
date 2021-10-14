package com.google.android.gms.internal.measurement;

public final class zzjt implements zzdb<zzjw> {
    private static zzjt zzari = new zzjt();
    private final zzdb<zzjw> zzapj;

    public static boolean zzyy() {
        return ((zzjw) zzari.get()).zzyy();
    }

    private zzjt(zzdb<zzjw> zzdb) {
        this.zzapj = zzda.zza(zzdb);
    }

    public zzjt() {
        this(zzda.zzg(new zzjv()));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzdb
    public final /* synthetic */ zzjw get() {
        return this.zzapj.get();
    }
}
