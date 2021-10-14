package com.google.android.gms.internal.measurement;

public final class zzmd implements zzme {
    private static final zzcm<Long> zzapw;
    private static final zzcm<Boolean> zzatp;
    private static final zzcm<Boolean> zzatq;
    private static final zzcm<Boolean> zzatr;
    private static final zzcm<Boolean> zzats;
    private static final zzcm<Boolean> zzatt;

    @Override // com.google.android.gms.internal.measurement.zzme
    public final boolean zzaai() {
        return zzatp.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzme
    public final boolean zzaaj() {
        return zzatq.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzme
    public final boolean zzaak() {
        return zzats.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzme
    public final boolean zzaal() {
        return zzatt.get().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzcn.zzdh("com.google.android.gms.measurement"));
        zzatp = zzct.zzb("measurement.client.sessions.background_sessions_enabled", true);
        zzatq = zzct.zzb("measurement.client.sessions.immediate_start_enabled_foreground", false);
        zzatr = zzct.zzb("measurement.client.sessions.immediate_start_enabled", false);
        zzats = zzct.zzb("measurement.client.sessions.remove_expired_session_properties_enabled", true);
        zzatt = zzct.zzb("measurement.client.sessions.session_id_enabled", true);
        zzapw = zzct.zze("measurement.id.sessionization_client", 0);
    }
}
