package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzey;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

final class zzgo<T> implements zzgx<T> {
    private final zzgi zzakn;
    private final boolean zzako;
    private final zzhp<?, ?> zzakx;
    private final zzen<?> zzaky;

    private zzgo(zzhp<?, ?> zzhp, zzen<?> zzen, zzgi zzgi) {
        this.zzakx = zzhp;
        this.zzako = zzen.zze(zzgi);
        this.zzaky = zzen;
        this.zzakn = zzgi;
    }

    static <T> zzgo<T> zza(zzhp<?, ?> zzhp, zzen<?> zzen, zzgi zzgi) {
        return new zzgo<>(zzhp, zzen, zzgi);
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final T newInstance() {
        return (T) this.zzakn.zzup().zzuf();
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final boolean equals(T t, T t2) {
        if (!this.zzakx.zzx(t).equals(this.zzakx.zzx(t2))) {
            return false;
        }
        if (this.zzako) {
            return this.zzaky.zzh(t).equals(this.zzaky.zzh(t2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final int hashCode(T t) {
        int hashCode = this.zzakx.zzx(t).hashCode();
        return this.zzako ? (hashCode * 53) + this.zzaky.zzh(t).hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final void zzc(T t, T t2) {
        zzgz.zza(this.zzakx, t, t2);
        if (this.zzako) {
            zzgz.zza(this.zzaky, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final void zza(T t, zzim zzim) throws IOException {
        Iterator<Map.Entry<?, Object>> it = this.zzaky.zzh(t).iterator();
        while (it.hasNext()) {
            Map.Entry<?, Object> next = it.next();
            zzeq zzeq = (zzeq) next.getKey();
            if (zzeq.zztx() != zzij.MESSAGE || zzeq.zzty() || zzeq.zztz()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zzfl) {
                zzim.zza(zzeq.zzlg(), (Object) ((zzfl) next).zzve().zzrs());
            } else {
                zzim.zza(zzeq.zzlg(), next.getValue());
            }
        }
        zzhp<?, ?> zzhp = this.zzakx;
        zzhp.zzc(zzhp.zzx(t), zzim);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0097 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.measurement.zzgx
    public final void zza(T t, byte[] bArr, int i, int i2, zzdk zzdk) throws IOException {
        T t2 = t;
        zzhs zzhs = t2.zzahz;
        if (zzhs == zzhs.zzwq()) {
            zzhs = zzhs.zzwr();
            t2.zzahz = zzhs;
        }
        t.zzuq();
        zzey.zze zze = null;
        while (i < i2) {
            int zza = zzdl.zza(bArr, i, zzdk);
            int i3 = zzdk.zzada;
            if (i3 == 11) {
                int i4 = 0;
                zzdp zzdp = null;
                while (zza < i2) {
                    zza = zzdl.zza(bArr, zza, zzdk);
                    int i5 = zzdk.zzada;
                    int i6 = i5 & 7;
                    switch (i5 >>> 3) {
                        case 2:
                            if (i6 == 0) {
                                zza = zzdl.zza(bArr, zza, zzdk);
                                i4 = zzdk.zzada;
                                zze = (zzey.zze) this.zzaky.zza(zzdk.zzadd, this.zzakn, i4);
                            }
                            if (i5 == 12) {
                                break;
                            } else {
                                zza = zzdl.zza(i5, bArr, zza, i2, zzdk);
                            }
                        case 3:
                            if (zze == null) {
                                if (i6 == 2) {
                                    zza = zzdl.zze(bArr, zza, zzdk);
                                    zzdp = (zzdp) zzdk.zzadc;
                                }
                                if (i5 == 12) {
                                }
                            } else {
                                zzgt.zzvy();
                                throw new NoSuchMethodError();
                            }
                            break;
                        default:
                            if (i5 == 12) {
                            }
                            break;
                    }
                    if (zzdp != null) {
                        zzhs.zzb((i4 << 3) | 2, zzdp);
                    }
                    i = zza;
                }
                if (zzdp != null) {
                }
                i = zza;
            } else if ((i3 & 7) == 2) {
                zze = (zzey.zze) this.zzaky.zza(zzdk.zzadd, this.zzakn, i3 >>> 3);
                if (zze == null) {
                    i = zzdl.zza(i3, bArr, zza, i2, zzhs, zzdk);
                } else {
                    zzgt.zzvy();
                    throw new NoSuchMethodError();
                }
            } else {
                i = zzdl.zza(i3, bArr, zza, i2, zzdk);
            }
        }
        if (i != i2) {
            throw zzfi.zzva();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final void zza(T t, zzgy zzgy, zzel zzel) throws IOException {
        boolean z;
        zzhp<?, ?> zzhp = this.zzakx;
        zzen<?> zzen = this.zzaky;
        Object zzy = zzhp.zzy(t);
        zzeo<?> zzi = zzen.zzi(t);
        do {
            try {
                if (zzgy.zzsy() == Integer.MAX_VALUE) {
                    zzhp.zzf(t, zzy);
                    return;
                }
                int tag = zzgy.getTag();
                if (tag == 11) {
                    int i = 0;
                    Object obj = null;
                    zzdp zzdp = null;
                    while (zzgy.zzsy() != Integer.MAX_VALUE) {
                        int tag2 = zzgy.getTag();
                        if (tag2 == 16) {
                            i = zzgy.zzsp();
                            obj = zzen.zza(zzel, this.zzakn, i);
                        } else if (tag2 == 26) {
                            if (obj != null) {
                                zzen.zza(zzgy, obj, zzel, zzi);
                            } else {
                                zzdp = zzgy.zzso();
                            }
                        } else if (!zzgy.zzsz()) {
                            break;
                        }
                    }
                    if (zzgy.getTag() != 12) {
                        throw zzfi.zzux();
                    } else if (zzdp != null) {
                        if (obj != null) {
                            zzen.zza(zzdp, obj, zzel, zzi);
                        } else {
                            zzhp.zza(zzy, i, zzdp);
                        }
                    }
                } else if ((tag & 7) == 2) {
                    Object zza = zzen.zza(zzel, this.zzakn, tag >>> 3);
                    if (zza != null) {
                        zzen.zza(zzgy, zza, zzel, zzi);
                    } else {
                        z = zzhp.zza(zzy, zzgy);
                        continue;
                    }
                } else {
                    z = zzgy.zzsz();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzhp.zzf(t, zzy);
            }
        } while (z);
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final void zzj(T t) {
        this.zzakx.zzj(t);
        this.zzaky.zzj(t);
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final boolean zzv(T t) {
        return this.zzaky.zzh(t).isInitialized();
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final int zzt(T t) {
        zzhp<?, ?> zzhp = this.zzakx;
        int zzz = zzhp.zzz(zzhp.zzx(t)) + 0;
        return this.zzako ? zzz + this.zzaky.zzh(t).zzts() : zzz;
    }
}
