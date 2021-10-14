package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;

/* access modifiers changed from: package-private */
public final class zzed extends zzeb {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzadx;
    private int zzady;
    private int zzadz;
    private int zzaea;
    private int zzaeb;

    private zzed(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzaeb = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzadz = this.pos;
        this.zzadx = z;
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final int zzsg() throws IOException {
        if (zzsw()) {
            this.zzaea = 0;
            return 0;
        }
        this.zzaea = zzta();
        int i = this.zzaea;
        if ((i >>> 3) != 0) {
            return i;
        }
        throw zzfi.zzuw();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final void zzat(int i) throws zzfi {
        if (this.zzaea != i) {
            throw zzfi.zzux();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final boolean zzau(int i) throws IOException {
        int zzsg;
        int i2 = 0;
        switch (i & 7) {
            case 0:
                if (this.limit - this.pos >= 10) {
                    while (i2 < 10) {
                        byte[] bArr = this.buffer;
                        int i3 = this.pos;
                        this.pos = i3 + 1;
                        if (bArr[i3] < 0) {
                            i2++;
                        }
                    }
                    throw zzfi.zzuv();
                }
                while (i2 < 10) {
                    if (zztf() < 0) {
                        i2++;
                    }
                }
                throw zzfi.zzuv();
                return true;
            case 1:
                zzay(8);
                return true;
            case 2:
                zzay(zzta());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzay(4);
                return true;
            default:
                throw zzfi.zzuy();
        }
        do {
            zzsg = zzsg();
            if (zzsg != 0) {
            }
            zzat(((i >>> 3) << 3) | 4);
            return true;
        } while (zzau(zzsg));
        zzat(((i >>> 3) << 3) | 4);
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zztd());
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zztc());
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final long zzsh() throws IOException {
        return zztb();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final long zzsi() throws IOException {
        return zztb();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final int zzsj() throws IOException {
        return zzta();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final long zzsk() throws IOException {
        return zztd();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final int zzsl() throws IOException {
        return zztc();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final boolean zzsm() throws IOException {
        return zztb() != 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final String readString() throws IOException {
        int zzta = zzta();
        if (zzta > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzta <= i - i2) {
                String str = new String(this.buffer, i2, zzta, zzez.UTF_8);
                this.pos += zzta;
                return str;
            }
        }
        if (zzta == 0) {
            return "";
        }
        if (zzta < 0) {
            throw zzfi.zzuu();
        }
        throw zzfi.zzut();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final String zzsn() throws IOException {
        int zzta = zzta();
        if (zzta > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzta <= i - i2) {
                String zzh = zzhy.zzh(this.buffer, i2, zzta);
                this.pos += zzta;
                return zzh;
            }
        }
        if (zzta == 0) {
            return "";
        }
        if (zzta <= 0) {
            throw zzfi.zzuu();
        }
        throw zzfi.zzut();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final <T extends zzgi> T zza(zzgr<T> zzgr, zzel zzel) throws IOException {
        int zzta = zzta();
        if (this.zzadp < this.zzadq) {
            int zzaw = zzaw(zzta);
            this.zzadp++;
            T zzc = zzgr.zzc(this, zzel);
            zzat(0);
            this.zzadp--;
            zzax(zzaw);
            return zzc;
        }
        throw zzfi.zzuz();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final zzdp zzso() throws IOException {
        byte[] bArr;
        int zzta = zzta();
        if (zzta > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzta <= i - i2) {
                zzdp zzb = zzdp.zzb(this.buffer, i2, zzta);
                this.pos += zzta;
                return zzb;
            }
        }
        if (zzta == 0) {
            return zzdp.zzadh;
        }
        if (zzta > 0) {
            int i3 = this.limit;
            int i4 = this.pos;
            if (zzta <= i3 - i4) {
                this.pos = zzta + i4;
                bArr = Arrays.copyOfRange(this.buffer, i4, this.pos);
                return zzdp.zze(bArr);
            }
        }
        if (zzta > 0) {
            throw zzfi.zzut();
        } else if (zzta == 0) {
            bArr = zzez.zzair;
            return zzdp.zze(bArr);
        } else {
            throw zzfi.zzuu();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final int zzsp() throws IOException {
        return zzta();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final int zzsq() throws IOException {
        return zzta();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final int zzsr() throws IOException {
        return zztc();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final long zzss() throws IOException {
        return zztd();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final int zzst() throws IOException {
        return zzaz(zzta());
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final long zzsu() throws IOException {
        return zzbm(zztb());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
        if (r2[r3] >= 0) goto L_0x006a;
     */
    private final int zzta() throws IOException {
        int i;
        int i2 = this.pos;
        int i3 = this.limit;
        if (i3 != i2) {
            byte[] bArr = this.buffer;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.pos = i4;
                return b;
            } else if (i3 - i4 >= 9) {
                int i5 = i4 + 1;
                int i6 = b ^ (bArr[i4] << 7);
                if (i6 < 0) {
                    i = i6 ^ -128;
                } else {
                    int i7 = i5 + 1;
                    int i8 = i6 ^ (bArr[i5] << 14);
                    if (i8 >= 0) {
                        i = i8 ^ 16256;
                        i5 = i7;
                    } else {
                        i5 = i7 + 1;
                        int i9 = i8 ^ (bArr[i7] << 21);
                        if (i9 < 0) {
                            i = i9 ^ -2080896;
                        } else {
                            int i10 = i5 + 1;
                            byte b2 = bArr[i5];
                            i = (i9 ^ (b2 << 28)) ^ 266354560;
                            if (b2 < 0) {
                                i5 = i10 + 1;
                                if (bArr[i10] < 0) {
                                    i10 = i5 + 1;
                                    if (bArr[i5] < 0) {
                                        i5 = i10 + 1;
                                        if (bArr[i10] < 0) {
                                            i10 = i5 + 1;
                                            if (bArr[i5] < 0) {
                                                i5 = i10 + 1;
                                            }
                                        }
                                    }
                                }
                            }
                            i5 = i10;
                        }
                    }
                }
                this.pos = i5;
                return i;
            }
        }
        return (int) zzsv();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b7, code lost:
        if (((long) r2[r0]) >= 0) goto L_0x00bb;
     */
    private final long zztb() throws IOException {
        long j;
        int i = this.pos;
        int i2 = this.limit;
        if (i2 != i) {
            byte[] bArr = this.buffer;
            int i3 = i + 1;
            byte b = bArr[i];
            if (b >= 0) {
                this.pos = i3;
                return (long) b;
            } else if (i2 - i3 >= 9) {
                int i4 = i3 + 1;
                int i5 = b ^ (bArr[i3] << 7);
                if (i5 < 0) {
                    j = (long) (i5 ^ -128);
                } else {
                    int i6 = i4 + 1;
                    int i7 = i5 ^ (bArr[i4] << 14);
                    if (i7 >= 0) {
                        i4 = i6;
                        j = (long) (i7 ^ 16256);
                    } else {
                        i4 = i6 + 1;
                        int i8 = i7 ^ (bArr[i6] << 21);
                        if (i8 < 0) {
                            j = (long) (i8 ^ -2080896);
                        } else {
                            long j2 = (long) i8;
                            int i9 = i4 + 1;
                            long j3 = j2 ^ (((long) bArr[i4]) << 28);
                            if (j3 >= 0) {
                                j = 266354560 ^ j3;
                                i4 = i9;
                            } else {
                                i4 = i9 + 1;
                                long j4 = j3 ^ (((long) bArr[i9]) << 35);
                                if (j4 < 0) {
                                    j = j4 ^ -34093383808L;
                                } else {
                                    int i10 = i4 + 1;
                                    long j5 = j4 ^ (((long) bArr[i4]) << 42);
                                    if (j5 >= 0) {
                                        j = 4363953127296L ^ j5;
                                        i4 = i10;
                                    } else {
                                        i4 = i10 + 1;
                                        long j6 = j5 ^ (((long) bArr[i10]) << 49);
                                        if (j6 < 0) {
                                            j = j6 ^ -558586000294016L;
                                        } else {
                                            int i11 = i4 + 1;
                                            j = (j6 ^ (((long) bArr[i4]) << 56)) ^ 71499008037633920L;
                                            i4 = j < 0 ? i11 + 1 : i11;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.pos = i4;
                return j;
            }
        }
        return zzsv();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzeb
    public final long zzsv() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zztf = zztf();
            j |= ((long) (zztf & ByteCompanionObject.MAX_VALUE)) << i;
            if ((zztf & ByteCompanionObject.MIN_VALUE) == 0) {
                return j;
            }
        }
        throw zzfi.zzuv();
    }

    private final int zztc() throws IOException {
        int i = this.pos;
        if (this.limit - i >= 4) {
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }
        throw zzfi.zzut();
    }

    private final long zztd() throws IOException {
        int i = this.pos;
        if (this.limit - i >= 8) {
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }
        throw zzfi.zzut();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final int zzaw(int i) throws zzfi {
        if (i >= 0) {
            int zzsx = i + zzsx();
            int i2 = this.zzaeb;
            if (zzsx <= i2) {
                this.zzaeb = zzsx;
                zzte();
                return i2;
            }
            throw zzfi.zzut();
        }
        throw zzfi.zzuu();
    }

    private final void zzte() {
        this.limit += this.zzady;
        int i = this.limit;
        int i2 = i - this.zzadz;
        int i3 = this.zzaeb;
        if (i2 > i3) {
            this.zzady = i2 - i3;
            this.limit = i - this.zzady;
            return;
        }
        this.zzady = 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final void zzax(int i) {
        this.zzaeb = i;
        zzte();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final boolean zzsw() throws IOException {
        return this.pos == this.limit;
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final int zzsx() {
        return this.pos - this.zzadz;
    }

    private final byte zztf() throws IOException {
        int i = this.pos;
        if (i != this.limit) {
            byte[] bArr = this.buffer;
            this.pos = i + 1;
            return bArr[i];
        }
        throw zzfi.zzut();
    }

    @Override // com.google.android.gms.internal.measurement.zzeb
    public final void zzay(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.limit;
            int i3 = this.pos;
            if (i <= i2 - i3) {
                this.pos = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzfi.zzuu();
        }
        throw zzfi.zzut();
    }
}
