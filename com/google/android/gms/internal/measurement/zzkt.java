package com.google.android.gms.internal.measurement;

public final class zzkt implements zzku {
    private static final zzcm<Boolean> zzase;
    private static final zzcm<Boolean> zzasf;
    private static final zzcm<Boolean> zzasg;

    @Override // com.google.android.gms.internal.measurement.zzku
    public final boolean zzzj() {
        return zzase.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzku
    public final boolean zzzk() {
        return zzasf.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzku
    public final boolean zzzl() {
        return zzasg.get().booleanValue();
    }

    static {
        zzct zzct = new zzct(zzcn.zzdh("com.google.android.gms.measurement"));
        zzase = zzct.zzb("measurement.sdk.collection.last_deep_link_referrer", false);
        zzasf = zzct.zzb("measurement.sdk.collection.last_deep_link_referrer_campaign", false);
        zzasg = zzct.zzb("measurement.sdk.collection.last_gclid_from_referrer", false);
    }
}
