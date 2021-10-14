package com.google.android.gms.internal.measurement;

import java.io.IOException;
import kotlin.jvm.internal.ByteCompanionObject;

/* access modifiers changed from: package-private */
public final class zzdl {
    static int zza(byte[] bArr, int i, zzdk zzdk) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zza(b, bArr, i2, zzdk);
        }
        zzdk.zzada = b;
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, zzdk zzdk) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzdk.zzada = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & ByteCompanionObject.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzdk.zzada = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & ByteCompanionObject.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzdk.zzada = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & ByteCompanionObject.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzdk.zzada = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & ByteCompanionObject.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzdk.zzada = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzb(byte[] bArr, int i, zzdk zzdk) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzdk.zzadb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & ByteCompanionObject.MAX_VALUE)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & ByteCompanionObject.MAX_VALUE)) << i4;
            b = b2;
            i3 = i5;
        }
        zzdk.zzadb = j2;
        return i3;
    }

    static int zza(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    static long zzb(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static double zzc(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzb(bArr, i));
    }

    static float zzd(byte[] bArr, int i) {
        return Float.intBitsToFloat(zza(bArr, i));
    }

    static int zzc(byte[] bArr, int i, zzdk zzdk) throws zzfi {
        int zza = zza(bArr, i, zzdk);
        int i2 = zzdk.zzada;
        if (i2 < 0) {
            throw zzfi.zzuu();
        } else if (i2 == 0) {
            zzdk.zzadc = "";
            return zza;
        } else {
            zzdk.zzadc = new String(bArr, zza, i2, zzez.UTF_8);
            return zza + i2;
        }
    }

    static int zzd(byte[] bArr, int i, zzdk zzdk) throws zzfi {
        int zza = zza(bArr, i, zzdk);
        int i2 = zzdk.zzada;
        if (i2 < 0) {
            throw zzfi.zzuu();
        } else if (i2 == 0) {
            zzdk.zzadc = "";
            return zza;
        } else {
            zzdk.zzadc = zzhy.zzh(bArr, zza, i2);
            return zza + i2;
        }
    }

    static int zze(byte[] bArr, int i, zzdk zzdk) throws zzfi {
        int zza = zza(bArr, i, zzdk);
        int i2 = zzdk.zzada;
        if (i2 < 0) {
            throw zzfi.zzuu();
        } else if (i2 > bArr.length - zza) {
            throw zzfi.zzut();
        } else if (i2 == 0) {
            zzdk.zzadc = zzdp.zzadh;
            return zza;
        } else {
            zzdk.zzadc = zzdp.zzb(bArr, zza, i2);
            return zza + i2;
        }
    }

    static int zza(zzgx zzgx, byte[] bArr, int i, int i2, zzdk zzdk) throws IOException {
        byte b;
        int i3;
        int i4 = i + 1;
        byte b2 = bArr[i];
        if (b2 < 0) {
            i3 = zza(b2, bArr, i4, zzdk);
            b = zzdk.zzada;
        } else {
            i3 = i4;
            b = b2;
        }
        if (b < 0 || b > i2 - i3) {
            throw zzfi.zzut();
        }
        Object newInstance = zzgx.newInstance();
        int i5 = (b == 1 ? 1 : 0) + i3;
        zzgx.zza(newInstance, bArr, i3, i5, zzdk);
        zzgx.zzj(newInstance);
        zzdk.zzadc = newInstance;
        return i5;
    }

    static int zza(zzgx zzgx, byte[] bArr, int i, int i2, int i3, zzdk zzdk) throws IOException {
        zzgm zzgm = (zzgm) zzgx;
        Object newInstance = zzgm.newInstance();
        int zza = zzgm.zza(newInstance, bArr, i, i2, i3, zzdk);
        zzgm.zzj(newInstance);
        zzdk.zzadc = newInstance;
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzff<?> zzff, zzdk zzdk) {
        zzfa zzfa = (zzfa) zzff;
        int zza = zza(bArr, i2, zzdk);
        zzfa.zzbu(zzdk.zzada);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzdk);
            if (i != zzdk.zzada) {
                break;
            }
            zza = zza(bArr, zza2, zzdk);
            zzfa.zzbu(zzdk.zzada);
        }
        return zza;
    }

    static int zza(byte[] bArr, int i, zzff<?> zzff, zzdk zzdk) throws IOException {
        zzfa zzfa = (zzfa) zzff;
        int zza = zza(bArr, i, zzdk);
        int i2 = zzdk.zzada + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, zzdk);
            zzfa.zzbu(zzdk.zzada);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzfi.zzut();
    }

    static int zza(zzgx<?> zzgx, int i, byte[] bArr, int i2, int i3, zzff<?> zzff, zzdk zzdk) throws IOException {
        int zza = zza(zzgx, bArr, i2, i3, zzdk);
        zzff.add(zzdk.zzadc);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzdk);
            if (i != zzdk.zzada) {
                break;
            }
            zza = zza(zzgx, bArr, zza2, i3, zzdk);
            zzff.add(zzdk.zzadc);
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzhs zzhs, zzdk zzdk) throws zzfi {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 != 5) {
                switch (i4) {
                    case 0:
                        int zzb = zzb(bArr, i2, zzdk);
                        zzhs.zzb(i, Long.valueOf(zzdk.zzadb));
                        return zzb;
                    case 1:
                        zzhs.zzb(i, Long.valueOf(zzb(bArr, i2)));
                        return i2 + 8;
                    case 2:
                        int zza = zza(bArr, i2, zzdk);
                        int i5 = zzdk.zzada;
                        if (i5 < 0) {
                            throw zzfi.zzuu();
                        } else if (i5 <= bArr.length - zza) {
                            if (i5 == 0) {
                                zzhs.zzb(i, zzdp.zzadh);
                            } else {
                                zzhs.zzb(i, zzdp.zzb(bArr, zza, i5));
                            }
                            return zza + i5;
                        } else {
                            throw zzfi.zzut();
                        }
                    case 3:
                        zzhs zzwr = zzhs.zzwr();
                        int i6 = (i & -8) | 4;
                        int i7 = 0;
                        while (true) {
                            if (i2 < i3) {
                                int zza2 = zza(bArr, i2, zzdk);
                                int i8 = zzdk.zzada;
                                if (i8 != i6) {
                                    i7 = i8;
                                    i2 = zza(i8, bArr, zza2, i3, zzwr, zzdk);
                                } else {
                                    i7 = i8;
                                    i2 = zza2;
                                }
                            }
                        }
                        if (i2 > i3 || i7 != i6) {
                            throw zzfi.zzva();
                        }
                        zzhs.zzb(i, zzwr);
                        return i2;
                    default:
                        throw zzfi.zzuw();
                }
            } else {
                zzhs.zzb(i, Integer.valueOf(zza(bArr, i2)));
                return i2 + 4;
            }
        } else {
            throw zzfi.zzuw();
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzdk zzdk) throws zzfi {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 5) {
                return i2 + 4;
            }
            switch (i4) {
                case 0:
                    return zzb(bArr, i2, zzdk);
                case 1:
                    return i2 + 8;
                case 2:
                    return zza(bArr, i2, zzdk) + zzdk.zzada;
                case 3:
                    int i5 = (i & -8) | 4;
                    int i6 = 0;
                    while (i2 < i3) {
                        i2 = zza(bArr, i2, zzdk);
                        i6 = zzdk.zzada;
                        if (i6 != i5) {
                            i2 = zza(i6, bArr, i2, i3, zzdk);
                        } else if (i2 > i3 && i6 == i5) {
                            return i2;
                        } else {
                            throw zzfi.zzva();
                        }
                    }
                    if (i2 > i3) {
                    }
                    throw zzfi.zzva();
                default:
                    throw zzfi.zzuw();
            }
        } else {
            throw zzfi.zzuw();
        }
    }
}
