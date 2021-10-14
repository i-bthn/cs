package com.google.android.gms.internal.measurement;

public final class zzlf implements zzlg {
    private static final zzcm<Boolean> zzasp;
    private static final zzcm<Double> zzasq;
    private static final zzcm<Long> zzasr;
    private static final zzcm<Long> zzass;
    private static final zzcm<String> zzast;

    @Override // com.google.android.gms.internal.measurement.zzlg
    public final boolean zzzq() {
        return zzasp.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlg
    public final double zzzr() {
        return zzasq.get().doubleValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlg
    public final long zzzs() {
        return zzasr.get().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlg
    public final long zzzt() {
        return zzass.get().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlg
    public final String zzzu() {
        return zzast.get();
    }

    static {
        zzct zzct = new zzct(zzcn.zzdh("com.google.android.gms.measurement"));
        zzasp = zzct.zzb("measurement.test.boolean_flag", false);
        zzasq = zzct.zza("measurement.test.double_flag", -3.0d);
        zzasr = zzct.zze("measurement.test.int_flag", -2);
        zzass = zzct.zze("measurement.test.long_flag", -1);
        zzast = zzct.zzt("measurement.test.string_flag", "---");
    }
}
