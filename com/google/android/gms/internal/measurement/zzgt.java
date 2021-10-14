package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* access modifiers changed from: package-private */
public final class zzgt {
    private static final zzgt zzalc = new zzgt();
    private final zzha zzald = new zzfv();
    private final ConcurrentMap<Class<?>, zzgx<?>> zzale = new ConcurrentHashMap();

    public static zzgt zzvy() {
        return zzalc;
    }

    public final <T> zzgx<T> zzf(Class<T> cls) {
        zzez.zza((Object) cls, "messageType");
        zzgx<T> zzgx = (zzgx<T>) this.zzale.get(cls);
        if (zzgx != null) {
            return zzgx;
        }
        zzgx<T> zze = this.zzald.zze(cls);
        zzez.zza((Object) cls, "messageType");
        zzez.zza((Object) zze, "schema");
        zzgx<T> zzgx2 = (zzgx<T>) this.zzale.putIfAbsent(cls, zze);
        return zzgx2 != null ? zzgx2 : zze;
    }

    public final <T> zzgx<T> zzw(T t) {
        return zzf(t.getClass());
    }

    private zzgt() {
    }
}
