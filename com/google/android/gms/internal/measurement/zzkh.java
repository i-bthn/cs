package com.google.android.gms.internal.measurement;

public final class zzkh implements zzki {
    private static final zzcm<Long> zzapw;
    private static final zzcm<Boolean> zzarv;

    @Override // com.google.android.gms.internal.measurement.zzki
    public final boolean zzze() {
        return zzarv.get().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzcn.zzdh("com.google.android.gms.measurement"));
        zzarv = zzct.zzb("measurement.fetch_config_with_admob_app_id", true);
        zzapw = zzct.zze("measurement.id.fetch_config_with_admob_app_id", 0);
    }
}
