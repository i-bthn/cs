package com.google.android.gms.internal.measurement;

final class zzfx implements zzgf {
    private zzgf[] zzaka;

    zzfx(zzgf... zzgfArr) {
        this.zzaka = zzgfArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzgf
    public final boolean zza(Class<?> cls) {
        for (zzgf zzgf : this.zzaka) {
            if (zzgf.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzgf
    public final zzgg zzb(Class<?> cls) {
        zzgf[] zzgfArr = this.zzaka;
        for (zzgf zzgf : zzgfArr) {
            if (zzgf.zza(cls)) {
                return zzgf.zzb(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
