package com.google.android.gms.internal.measurement;

public final class zzjj implements zzjk {
    private static final zzcm<Boolean> zzapq;
    private static final zzcm<Boolean> zzapr;
    private static final zzcm<Boolean> zzaps;

    @Override // com.google.android.gms.internal.measurement.zzjk
    public final boolean zzxj() {
        return zzapq.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzjk
    public final boolean zzxk() {
        return zzapr.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzjk
    public final boolean zzxl() {
        return zzaps.get().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzcn.zzdh("com.google.android.gms.measurement"));
        zzapq = zzct.zzb("measurement.log_installs_enabled", false);
        zzapr = zzct.zzb("measurement.log_third_party_store_events_enabled", false);
        zzaps = zzct.zzb("measurement.log_upgrades_enabled", false);
    }
}
