package com.google.android.gms.internal.measurement;

import java.io.IOException;

abstract class zzhp<T, B> {
    zzhp() {
    }

    /* access modifiers changed from: package-private */
    public abstract void zza(B b, int i, long j);

    /* access modifiers changed from: package-private */
    public abstract void zza(B b, int i, zzdp zzdp);

    /* access modifiers changed from: package-private */
    public abstract void zza(B b, int i, T t);

    /* access modifiers changed from: package-private */
    public abstract void zza(T t, zzim zzim) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract boolean zza(zzgy zzgy);

    /* access modifiers changed from: package-private */
    public abstract void zzb(B b, int i, long j);

    /* access modifiers changed from: package-private */
    public abstract void zzc(B b, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void zzc(T t, zzim zzim) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zze(Object obj, T t);

    /* access modifiers changed from: package-private */
    public abstract void zzf(Object obj, B b);

    /* access modifiers changed from: package-private */
    public abstract T zzg(T t, T t2);

    /* access modifiers changed from: package-private */
    public abstract void zzj(Object obj);

    /* access modifiers changed from: package-private */
    public abstract T zzp(B b);

    /* access modifiers changed from: package-private */
    public abstract int zzt(T t);

    /* access modifiers changed from: package-private */
    public abstract B zzwp();

    /* access modifiers changed from: package-private */
    public abstract T zzx(Object obj);

    /* access modifiers changed from: package-private */
    public abstract B zzy(Object obj);

    /* access modifiers changed from: package-private */
    public abstract int zzz(T t);

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002c  */
    public final boolean zza(B b, zzgy zzgy) throws IOException {
        int tag = zzgy.getTag();
        int i = tag >>> 3;
        switch (tag & 7) {
            case 0:
                zza(b, i, zzgy.zzsi());
                return true;
            case 1:
                zzb(b, i, zzgy.zzsk());
                return true;
            case 2:
                zza((Object) b, i, zzgy.zzso());
                return true;
            case 3:
                B zzwp = zzwp();
                int i2 = (i << 3) | 4;
                while (zzgy.zzsy() != Integer.MAX_VALUE && zza(zzwp, zzgy)) {
                    while (zzgy.zzsy() != Integer.MAX_VALUE) {
                        while (zzgy.zzsy() != Integer.MAX_VALUE) {
                        }
                        break;
                    }
                }
                if (i2 == zzgy.getTag()) {
                    zza((Object) b, i, (Object) zzp(zzwp));
                    return true;
                }
                throw zzfi.zzux();
            case 4:
                return false;
            case 5:
                zzc(b, i, zzgy.zzsl());
                return true;
            default:
                throw zzfi.zzuy();
        }
    }
}
