package com.google.android.gms.internal.measurement;

public final class zzkb implements zzkc {
    private static final zzcm<Long> zzapw;
    private static final zzcm<Boolean> zzaro;
    private static final zzcm<Boolean> zzarp;
    private static final zzcm<Boolean> zzarq;
    private static final zzcm<Boolean> zzarr;

    @Override // com.google.android.gms.internal.measurement.zzkc
    public final boolean zzza() {
        return zzaro.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzkc
    public final boolean zzzb() {
        return zzarp.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzkc
    public final boolean zzzc() {
        return zzarq.get().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzcn.zzdh("com.google.android.gms.measurement"));
        zzaro = zzct.zzb("measurement.sdk.dynamite.allow_remote_dynamite", false);
        zzarp = zzct.zzb("measurement.collection.init_params_control_enabled", true);
        zzarq = zzct.zzb("measurement.sdk.dynamite.use_dynamite", false);
        zzarr = zzct.zzb("measurement.sdk.dynamite.use_dynamite2", false);
        zzapw = zzct.zze("measurement.id.sdk.dynamite.use_dynamite", 0);
    }
}
