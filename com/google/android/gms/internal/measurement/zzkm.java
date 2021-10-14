package com.google.android.gms.internal.measurement;

public final class zzkm implements zzkj {
    private static final zzcm<Boolean> zzary;
    private static final zzcm<Boolean> zzarz;

    @Override // com.google.android.gms.internal.measurement.zzkj
    public final boolean zzzf() {
        return zzary.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzkj
    public final boolean zzzg() {
        return zzarz.get().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzcn.zzdh("com.google.android.gms.measurement"));
        zzary = zzct.zzb("measurement.collection.efficient_engagement_reporting_enabled", false);
        zzarz = zzct.zzb("measurement.collection.redundant_engagement_removal_enabled", false);
    }
}
