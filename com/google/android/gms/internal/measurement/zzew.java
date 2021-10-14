package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzey;

final class zzew implements zzgf {
    private static final zzew zzahu = new zzew();

    private zzew() {
    }

    public static zzew zzua() {
        return zzahu;
    }

    @Override // com.google.android.gms.internal.measurement.zzgf
    public final boolean zza(Class<?> cls) {
        return zzey.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.measurement.zzgf
    public final zzgg zzb(Class<?> cls) {
        if (!zzey.class.isAssignableFrom(cls)) {
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Unsupported message type: ".concat(valueOf) : new String("Unsupported message type: "));
        }
        try {
            return (zzgg) zzey.zzd(cls.asSubclass(zzey.class)).zza(zzey.zzd.zzaif, (Object) null, (Object) null);
        } catch (Exception e) {
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? "Unable to get message info for ".concat(valueOf2) : new String("Unable to get message info for "), e);
        }
    }
}
