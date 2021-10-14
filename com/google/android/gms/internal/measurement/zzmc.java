package com.google.android.gms.internal.measurement;

public final class zzmc implements zzlz {
    private static final zzcm<Long> zzapw;
    private static final zzcm<Boolean> zzatm;
    private static final zzcm<Boolean> zzatn;
    private static final zzcm<Boolean> zzato;

    @Override // com.google.android.gms.internal.measurement.zzlz
    public final boolean zzaaf() {
        return zzatm.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlz
    public final boolean zzaag() {
        return zzatn.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlz
    public final boolean zzaah() {
        return zzato.get().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzcn.zzdh("com.google.android.gms.measurement"));
        zzatm = zzct.zzb("measurement.service.sessions.remove_disabled_session_number", false);
        zzatn = zzct.zzb("measurement.service.sessions.session_number_enabled", false);
        zzato = zzct.zzb("measurement.service.sessions.session_number_backfill_enabled", false);
        zzapw = zzct.zze("measurement.id.session_number", 0);
    }
}
