package com.google.android.gms.internal.measurement;

public final class zzmj implements zzmk {
    private static final zzcm<Boolean> zzatx = new zzct(zzcn.zzdh("com.google.android.gms.measurement")).zzb("measurement.upload.file_lock_state_check", false);

    @Override // com.google.android.gms.internal.measurement.zzmk
    public final boolean zzaan() {
        return zzatx.get().booleanValue();
    }
}
