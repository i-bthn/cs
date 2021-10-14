package com.google.android.gms.measurement.internal;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzdu<V> {
    private static final Object zzjo = new Object();
    private final String zzjj;
    private final zzdv<V> zzjk;
    private final V zzjl;
    private final V zzjm;
    private final Object zzjn;
    @GuardedBy("overrideLock")
    private volatile V zzjp;
    @GuardedBy("cachingLock")
    private volatile V zzjq;

    private zzdu(@NonNull String str, @NonNull V v, @NonNull V v2, @Nullable zzdv<V> zzdv) {
        this.zzjn = new Object();
        this.zzjp = null;
        this.zzjq = null;
        this.zzjj = str;
        this.zzjl = v;
        this.zzjm = v2;
        this.zzjk = zzdv;
    }

    public final String getKey() {
        return this.zzjj;
    }

    public final V get(@Nullable V v) {
        synchronized (this.zzjn) {
            V v2 = this.zzjp;
        }
        if (v != null) {
            return v;
        }
        if (zzak.zzfv == null) {
            return this.zzjl;
        }
        zzr zzr = zzak.zzfv;
        synchronized (zzjo) {
            if (zzr.isMainThread()) {
                return this.zzjq == null ? this.zzjl : this.zzjq;
            } else if (!zzr.isMainThread()) {
                zzr zzr2 = zzak.zzfv;
                try {
                    for (zzdu zzdu : zzak.zzfw) {
                        synchronized (zzjo) {
                            if (!zzr.isMainThread()) {
                                zzdu.zzjq = zzdu.zzjk != null ? zzdu.zzjk.get() : null;
                            } else {
                                throw new IllegalStateException("Refreshing flag cache must be done on a worker thread.");
                            }
                        }
                    }
                } catch (SecurityException e) {
                    zzak.zza(e);
                }
            } else {
                throw new IllegalStateException("Tried to refresh flag cache on main thread or on package side.");
            }
        }
        zzdv<V> zzdv = this.zzjk;
        if (zzdv == null) {
            zzr zzr3 = zzak.zzfv;
            return this.zzjl;
        }
        try {
            return zzdv.get();
        } catch (SecurityException e2) {
            zzak.zza(e2);
            zzr zzr4 = zzak.zzfv;
            return this.zzjl;
        }
    }
}
