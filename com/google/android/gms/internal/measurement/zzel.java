package com.google.android.gms.internal.measurement;

import androidx.core.internal.view.SupportMenu;
import com.google.android.gms.internal.measurement.zzey;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzel {
    private static volatile boolean zzaer = false;
    private static final Class<?> zzaes = zzto();
    private static volatile zzel zzaet;
    private static volatile zzel zzaeu;
    static final zzel zzaev = new zzel(true);
    private final Map<zza, zzey.zze<?, ?>> zzaew;

    private static Class<?> zzto() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    static final class zza {
        private final int number;
        private final Object object;

        zza(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.object) * SupportMenu.USER_MASK) + this.number;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.object == zza.object && this.number == zza.number) {
                return true;
            }
            return false;
        }
    }

    public static zzel zztp() {
        zzel zzel = zzaet;
        if (zzel == null) {
            synchronized (zzel.class) {
                zzel = zzaet;
                if (zzel == null) {
                    zzel = zzej.zztm();
                    zzaet = zzel;
                }
            }
        }
        return zzel;
    }

    public static zzel zztq() {
        zzel zzel = zzaeu;
        if (zzel == null) {
            synchronized (zzel.class) {
                zzel = zzaeu;
                if (zzel == null) {
                    zzel = zzej.zztn();
                    zzaeu = zzel;
                }
            }
        }
        return zzel;
    }

    static zzel zztn() {
        return zzex.zzc(zzel.class);
    }

    public final <ContainingType extends zzgi> zzey.zze<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzey.zze<ContainingType, ?>) this.zzaew.get(new zza(containingtype, i));
    }

    zzel() {
        this.zzaew = new HashMap();
    }

    private zzel(boolean z) {
        this.zzaew = Collections.emptyMap();
    }
}
