package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzey;

/* access modifiers changed from: package-private */
public final class zzfv implements zzha {
    private static final zzgf zzajx = new zzfy();
    private final zzgf zzajw;

    public zzfv() {
        this(new zzfx(zzew.zzua(), zzvj()));
    }

    private zzfv(zzgf zzgf) {
        this.zzajw = (zzgf) zzez.zza((Object) zzgf, "messageInfoFactory");
    }

    @Override // com.google.android.gms.internal.measurement.zzha
    public final <T> zzgx<T> zze(Class<T> cls) {
        zzgz.zzg(cls);
        zzgg zzb = this.zzajw.zzb(cls);
        if (zzb.zzvs()) {
            if (zzey.class.isAssignableFrom(cls)) {
                return zzgo.zza(zzgz.zzwe(), zzep.zztu(), zzb.zzvt());
            }
            return zzgo.zza(zzgz.zzwc(), zzep.zztv(), zzb.zzvt());
        } else if (zzey.class.isAssignableFrom(cls)) {
            if (zza(zzb)) {
                return zzgm.zza(cls, zzb, zzgs.zzvw(), zzfs.zzvi(), zzgz.zzwe(), zzep.zztu(), zzgd.zzvp());
            }
            return zzgm.zza(cls, zzb, zzgs.zzvw(), zzfs.zzvi(), zzgz.zzwe(), (zzen<?>) null, zzgd.zzvp());
        } else if (zza(zzb)) {
            return zzgm.zza(cls, zzb, zzgs.zzvv(), zzfs.zzvh(), zzgz.zzwc(), zzep.zztv(), zzgd.zzvo());
        } else {
            return zzgm.zza(cls, zzb, zzgs.zzvv(), zzfs.zzvh(), zzgz.zzwd(), (zzen<?>) null, zzgd.zzvo());
        }
    }

    private static boolean zza(zzgg zzgg) {
        return zzgg.zzvr() == zzey.zzd.zzail;
    }

    private static zzgf zzvj() {
        try {
            return (zzgf) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return zzajx;
        }
    }
}
