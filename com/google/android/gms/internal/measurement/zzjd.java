package com.google.android.gms.internal.measurement;

public final class zzjd implements zzje {
    private static final zzcm<Boolean> zzapm = new zzct(zzcn.zzdh("com.google.android.gms.measurement")).zzb("measurement.log_androidId_enabled", false);

    @Override // com.google.android.gms.internal.measurement.zzje
    public final boolean zzxh() {
        return zzapm.get().booleanValue();
    }
}
