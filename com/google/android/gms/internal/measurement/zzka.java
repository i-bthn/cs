package com.google.android.gms.internal.measurement;

public final class zzka implements zzjx {
    private static final zzcm<Boolean> zzarn = new zzct(zzcn.zzdh("com.google.android.gms.measurement")).zzb("measurement.audience.dynamic_filters", true);

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final boolean zzyz() {
        return zzarn.get().booleanValue();
    }
}
