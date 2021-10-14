package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;

final class zzie extends zzhz {
    zzie() {
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0065, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00ba, code lost:
        return -1;
     */
    @Override // com.google.android.gms.internal.measurement.zzhz
    public final int zzb(int i, byte[] bArr, int i2, int i3) {
        int i4;
        if ((i2 | i3 | (bArr.length - i3)) >= 0) {
            long j = (long) i2;
            int i5 = (int) (((long) i3) - j);
            if (i5 >= 16) {
                long j2 = j;
                i4 = 0;
                while (true) {
                    if (i4 >= i5) {
                        i4 = i5;
                        break;
                    }
                    long j3 = j2 + 1;
                    if (zzhv.zza(bArr, j2) < 0) {
                        break;
                    }
                    i4++;
                    j2 = j3;
                }
            } else {
                i4 = 0;
            }
            int i6 = i5 - i4;
            long j4 = j + ((long) i4);
            while (true) {
                byte b = 0;
                while (true) {
                    if (i6 <= 0) {
                        break;
                    }
                    long j5 = j4 + 1;
                    byte zza = zzhv.zza(bArr, j4);
                    if (zza < 0) {
                        b = zza;
                        j4 = j5;
                        break;
                    }
                    i6--;
                    b = zza;
                    j4 = j5;
                }
                if (i6 != 0) {
                    int i7 = i6 - 1;
                    if (b >= -32) {
                        if (b >= -16) {
                            if (i7 >= 3) {
                                i6 = i7 - 3;
                                long j6 = j4 + 1;
                                byte zza2 = zzhv.zza(bArr, j4);
                                if (zza2 > -65 || (((b << 28) + (zza2 + 112)) >> 30) != 0) {
                                    break;
                                }
                                long j7 = j6 + 1;
                                if (zzhv.zza(bArr, j6) > -65) {
                                    break;
                                }
                                j4 = j7 + 1;
                                if (zzhv.zza(bArr, j7) > -65) {
                                    break;
                                }
                            } else {
                                return zza(bArr, b, j4, i7);
                            }
                        } else if (i7 >= 2) {
                            i6 = i7 - 2;
                            long j8 = j4 + 1;
                            byte zza3 = zzhv.zza(bArr, j4);
                            if (zza3 > -65 || ((b == -32 && zza3 < -96) || (b == -19 && zza3 >= -96))) {
                                break;
                            }
                            j4 = j8 + 1;
                            if (zzhv.zza(bArr, j8) > -65) {
                                break;
                            }
                        } else {
                            return zza(bArr, b, j4, i7);
                        }
                    } else if (i7 != 0) {
                        i6 = i7 - 1;
                        if (b < -62) {
                            break;
                        }
                        long j9 = j4 + 1;
                        if (zzhv.zza(bArr, j4) > -65) {
                            break;
                        }
                        j4 = j9;
                    } else {
                        return b;
                    }
                } else {
                    return 0;
                }
            }
            return -1;
        }
        throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhz
    public final String zzh(byte[] bArr, int i, int i2) throws zzfi {
        if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (i < i3) {
                byte zza = zzhv.zza(bArr, (long) i);
                if (!zzia.zzh(zza)) {
                    break;
                }
                i++;
                zzia.zzb(zza, cArr, i4);
                i4++;
            }
            int i5 = i4;
            while (i < i3) {
                int i6 = i + 1;
                byte zza2 = zzhv.zza(bArr, (long) i);
                if (zzia.zzh(zza2)) {
                    int i7 = i5 + 1;
                    zzia.zzb(zza2, cArr, i5);
                    while (i6 < i3) {
                        byte zza3 = zzhv.zza(bArr, (long) i6);
                        if (!zzia.zzh(zza3)) {
                            break;
                        }
                        i6++;
                        zzia.zzb(zza3, cArr, i7);
                        i7++;
                    }
                    i = i6;
                    i5 = i7;
                } else if (zzia.zzi(zza2)) {
                    if (i6 < i3) {
                        int i8 = i6 + 1;
                        zzia.zzb(zza2, zzhv.zza(bArr, (long) i6), cArr, i5);
                        i = i8;
                        i5++;
                    } else {
                        throw zzfi.zzvb();
                    }
                } else if (zzia.zzj(zza2)) {
                    if (i6 < i3 - 1) {
                        int i9 = i6 + 1;
                        int i10 = i9 + 1;
                        zzia.zzb(zza2, zzhv.zza(bArr, (long) i6), zzhv.zza(bArr, (long) i9), cArr, i5);
                        i = i10;
                        i5++;
                    } else {
                        throw zzfi.zzvb();
                    }
                } else if (i6 < i3 - 2) {
                    int i11 = i6 + 1;
                    int i12 = i11 + 1;
                    zzia.zzb(zza2, zzhv.zza(bArr, (long) i6), zzhv.zza(bArr, (long) i11), zzhv.zza(bArr, (long) i12), cArr, i5);
                    i = i12 + 1;
                    i5 = i5 + 1 + 1;
                } else {
                    throw zzfi.zzvb();
                }
            }
            return new String(cArr, 0, i5);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhz
    public final int zzb(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        char charAt;
        long j = (long) i;
        long j2 = ((long) i2) + j;
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            char charAt2 = charSequence.charAt(length - 1);
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(charAt2);
            sb.append(" at index ");
            sb.append(i + i2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i4 = 0;
        while (i4 < length && (charAt = charSequence.charAt(i4)) < 128) {
            zzhv.zza(bArr, j, (byte) charAt);
            i4++;
            j = 1 + j;
        }
        if (i4 == length) {
            return (int) j;
        }
        while (i4 < length) {
            char charAt3 = charSequence.charAt(i4);
            if (charAt3 < 128 && j < j2) {
                zzhv.zza(bArr, j, (byte) charAt3);
                j++;
            } else if (charAt3 < 2048 && j <= j2 - 2) {
                long j3 = j + 1;
                zzhv.zza(bArr, j, (byte) ((charAt3 >>> 6) | 960));
                j = j3 + 1;
                zzhv.zza(bArr, j3, (byte) ((charAt3 & '?') | 128));
            } else if ((charAt3 < 55296 || 57343 < charAt3) && j <= j2 - 3) {
                long j4 = j + 1;
                zzhv.zza(bArr, j, (byte) ((charAt3 >>> '\f') | 480));
                long j5 = j4 + 1;
                zzhv.zza(bArr, j4, (byte) (((charAt3 >>> 6) & 63) | 128));
                zzhv.zza(bArr, j5, (byte) ((charAt3 & '?') | 128));
                j = j5 + 1;
            } else if (j <= j2 - 4) {
                int i5 = i4 + 1;
                if (i5 != length) {
                    char charAt4 = charSequence.charAt(i5);
                    if (Character.isSurrogatePair(charAt3, charAt4)) {
                        int codePoint = Character.toCodePoint(charAt3, charAt4);
                        long j6 = j + 1;
                        zzhv.zza(bArr, j, (byte) ((codePoint >>> 18) | 240));
                        long j7 = j6 + 1;
                        zzhv.zza(bArr, j6, (byte) (((codePoint >>> 12) & 63) | 128));
                        long j8 = j7 + 1;
                        zzhv.zza(bArr, j7, (byte) (((codePoint >>> 6) & 63) | 128));
                        j = j8 + 1;
                        zzhv.zza(bArr, j8, (byte) ((codePoint & 63) | 128));
                        i4 = i5;
                    }
                } else {
                    i5 = i4;
                }
                throw new zzib(i5 - 1, length);
            } else if (55296 > charAt3 || charAt3 > 57343 || ((i3 = i4 + 1) != length && Character.isSurrogatePair(charAt3, charSequence.charAt(i3)))) {
                StringBuilder sb2 = new StringBuilder(46);
                sb2.append("Failed writing ");
                sb2.append(charAt3);
                sb2.append(" at index ");
                sb2.append(j);
                throw new ArrayIndexOutOfBoundsException(sb2.toString());
            } else {
                throw new zzib(i4, length);
            }
            i4++;
        }
        return (int) j;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x003d A[LOOP:1: B:11:0x003d->B:35:0x0104, LOOP_START, PHI: r4 r9 r10 r11 
      PHI: (r4v5 long) = (r4v4 long), (r4v8 long) binds: [B:8:0x0035, B:35:0x0104] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r9v3 int) = (r9v2 int), (r9v5 int) binds: [B:8:0x0035, B:35:0x0104] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r10v1 char) = (r10v0 char), (r10v2 char) binds: [B:8:0x0035, B:35:0x0104] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r11v2 long) = (r11v1 long), (r11v3 long) binds: [B:8:0x0035, B:35:0x0104] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0037  */
    @Override // com.google.android.gms.internal.measurement.zzhz
    public final void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        long j;
        long j2;
        int i;
        char charAt;
        long zzb = zzhv.zzb(byteBuffer);
        long position = ((long) byteBuffer.position()) + zzb;
        long limit = ((long) byteBuffer.limit()) + zzb;
        int length = charSequence.length();
        if (((long) length) <= limit - position) {
            int i2 = 0;
            while (true) {
                char c = 128;
                long j3 = 1;
                if (i2 < length && (charAt = charSequence.charAt(i2)) < 128) {
                    zzhv.zza(position, (byte) charAt);
                    i2++;
                    position = 1 + position;
                } else if (i2 != length) {
                    byteBuffer.position((int) (position - zzb));
                    return;
                } else {
                    while (i2 < length) {
                        char charAt2 = charSequence.charAt(i2);
                        if (charAt2 < c && position < limit) {
                            j = position + j3;
                            zzhv.zza(position, (byte) charAt2);
                            j2 = j3;
                        } else if (charAt2 < 2048 && position <= limit - 2) {
                            long j4 = position + j3;
                            zzhv.zza(position, (byte) ((charAt2 >>> 6) | 960));
                            zzhv.zza(j4, (byte) ((charAt2 & '?') | 128));
                            j = j4 + j3;
                            j2 = j3;
                        } else if ((charAt2 < 55296 || 57343 < charAt2) && position <= limit - 3) {
                            long j5 = position + j3;
                            zzhv.zza(position, (byte) ((charAt2 >>> '\f') | 480));
                            long j6 = j5 + j3;
                            zzhv.zza(j5, (byte) (((charAt2 >>> 6) & 63) | 128));
                            zzhv.zza(j6, (byte) ((charAt2 & '?') | 128));
                            j = j6 + 1;
                            j2 = 1;
                        } else if (position <= limit - 4) {
                            int i3 = i2 + 1;
                            if (i3 != length) {
                                char charAt3 = charSequence.charAt(i3);
                                if (Character.isSurrogatePair(charAt2, charAt3)) {
                                    int codePoint = Character.toCodePoint(charAt2, charAt3);
                                    long j7 = position + 1;
                                    zzhv.zza(position, (byte) ((codePoint >>> 18) | 240));
                                    long j8 = j7 + 1;
                                    zzhv.zza(j7, (byte) (((codePoint >>> 12) & 63) | 128));
                                    long j9 = j8 + 1;
                                    zzhv.zza(j8, (byte) (((codePoint >>> 6) & 63) | 128));
                                    j2 = 1;
                                    zzhv.zza(j9, (byte) ((codePoint & 63) | 128));
                                    i2 = i3;
                                    j = j9 + 1;
                                } else {
                                    i2 = i3;
                                }
                            }
                            throw new zzib(i2 - 1, length);
                        } else if (55296 > charAt2 || charAt2 > 57343 || ((i = i2 + 1) != length && Character.isSurrogatePair(charAt2, charSequence.charAt(i)))) {
                            StringBuilder sb = new StringBuilder(46);
                            sb.append("Failed writing ");
                            sb.append(charAt2);
                            sb.append(" at index ");
                            sb.append(position);
                            throw new ArrayIndexOutOfBoundsException(sb.toString());
                        } else {
                            throw new zzib(i2, length);
                        }
                        i2++;
                        j3 = j2;
                        position = j;
                        c = 128;
                    }
                    byteBuffer.position((int) (position - zzb));
                    return;
                }
            }
            if (i2 != length) {
            }
        } else {
            char charAt4 = charSequence.charAt(length - 1);
            int limit2 = byteBuffer.limit();
            StringBuilder sb2 = new StringBuilder(37);
            sb2.append("Failed writing ");
            sb2.append(charAt4);
            sb2.append(" at index ");
            sb2.append(limit2);
            throw new ArrayIndexOutOfBoundsException(sb2.toString());
        }
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        switch (i2) {
            case 0:
                return zzhy.zzch(i);
            case 1:
                return zzhy.zzr(i, zzhv.zza(bArr, j));
            case 2:
                return zzhy.zzc(i, zzhv.zza(bArr, j), zzhv.zza(bArr, j + 1));
            default:
                throw new AssertionError();
        }
    }
}
