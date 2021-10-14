package com.google.android.gms.internal.measurement;

public final class zzlx implements zzly {
    private static final zzcm<Long> zzapw;
    private static final zzcm<Boolean> zzati;
    private static final zzcm<Boolean> zzatj;

    @Override // com.google.android.gms.internal.measurement.zzly
    public final boolean zzaad() {
        return zzati.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzly
    public final boolean zzaae() {
        return zzatj.get().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzcn.zzdh("com.google.android.gms.measurement"));
        zzati = zzct.zzb("measurement.audience.sequence_filters", false);
        zzatj = zzct.zzb("measurement.audience.sequence_filters_bundle_timestamp", false);
        zzapw = zzct.zze("measurement.id.audience.sequence_filters", 0);
    }
}
