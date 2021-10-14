package com.google.android.gms.internal.measurement;

public final class zzll implements zzlm {
    private static final zzcm<Boolean> zzasx = new zzct(zzcn.zzdh("com.google.android.gms.measurement")).zzb("measurement.experiment.enable_experiment_reporting", true);

    @Override // com.google.android.gms.internal.measurement.zzlm
    public final boolean zzzw() {
        return zzasx.get().booleanValue();
    }
}
