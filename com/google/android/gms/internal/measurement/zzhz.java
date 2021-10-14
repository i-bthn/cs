package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;

/* access modifiers changed from: package-private */
public abstract class zzhz {
    zzhz() {
    }

    /* access modifiers changed from: package-private */
    public abstract int zzb(int i, byte[] bArr, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract int zzb(CharSequence charSequence, byte[] bArr, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void zzb(CharSequence charSequence, ByteBuffer byteBuffer);

    /* access modifiers changed from: package-private */
    public abstract String zzh(byte[] bArr, int i, int i2) throws zzfi;

    /* access modifiers changed from: package-private */
    public final boolean zzf(byte[] bArr, int i, int i2) {
        return zzb(0, bArr, i, i2) == 0;
    }

    static void zzc(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int length = charSequence.length();
        int position = byteBuffer.position();
        int i2 = 0;
        while (i2 < length) {
            try {
                char charAt = charSequence.charAt(i2);
                if (charAt >= 128) {
                    break;
                }
                byteBuffer.put(position + i2, (byte) charAt);
                i2++;
            } catch (IndexOutOfBoundsException unused) {
                char charAt2 = charSequence.charAt(i2);
                StringBuilder sb = new StringBuilder(37);
                sb.append("Failed writing ");
                sb.append(charAt2);
                sb.append(" at index ");
                sb.append(byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1));
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            }
        }
        if (i2 == length) {
            byteBuffer.position(position + i2);
            return;
        }
        position += i2;
        while (i2 < length) {
            char charAt3 = charSequence.charAt(i2);
            if (charAt3 < 128) {
                byteBuffer.put(position, (byte) charAt3);
            } else if (charAt3 < 2048) {
                int i3 = position + 1;
                try {
                    byteBuffer.put(position, (byte) ((charAt3 >>> 6) | 192));
                    byteBuffer.put(i3, (byte) ((charAt3 & '?') | 128));
                    position = i3;
                } catch (IndexOutOfBoundsException unused2) {
                    position = i3;
                    char charAt22 = charSequence.charAt(i2);
                    StringBuilder sb2 = new StringBuilder(37);
                    sb2.append("Failed writing ");
                    sb2.append(charAt22);
                    sb2.append(" at index ");
                    sb2.append(byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1));
                    throw new ArrayIndexOutOfBoundsException(sb2.toString());
                }
            } else if (charAt3 < 55296 || 57343 < charAt3) {
                int i4 = position + 1;
                try {
                    byteBuffer.put(position, (byte) ((charAt3 >>> '\f') | 224));
                    position = i4 + 1;
                    byteBuffer.put(i4, (byte) (((charAt3 >>> 6) & 63) | 128));
                    byteBuffer.put(position, (byte) ((charAt3 & '?') | 128));
                } catch (IndexOutOfBoundsException unused3) {
                    position = i4;
                    char charAt222 = charSequence.charAt(i2);
                    StringBuilder sb22 = new StringBuilder(37);
                    sb22.append("Failed writing ");
                    sb22.append(charAt222);
                    sb22.append(" at index ");
                    sb22.append(byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1));
                    throw new ArrayIndexOutOfBoundsException(sb22.toString());
                }
            } else {
                int i5 = i2 + 1;
                if (i5 != length) {
                    try {
                        char charAt4 = charSequence.charAt(i5);
                        if (Character.isSurrogatePair(charAt3, charAt4)) {
                            int codePoint = Character.toCodePoint(charAt3, charAt4);
                            int i6 = position + 1;
                            try {
                                byteBuffer.put(position, (byte) ((codePoint >>> 18) | 240));
                                position = i6 + 1;
                                try {
                                    byteBuffer.put(i6, (byte) (((codePoint >>> 12) & 63) | 128));
                                    i = position + 1;
                                } catch (IndexOutOfBoundsException unused4) {
                                    i2 = i5;
                                    char charAt2222 = charSequence.charAt(i2);
                                    StringBuilder sb222 = new StringBuilder(37);
                                    sb222.append("Failed writing ");
                                    sb222.append(charAt2222);
                                    sb222.append(" at index ");
                                    sb222.append(byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1));
                                    throw new ArrayIndexOutOfBoundsException(sb222.toString());
                                }
                                try {
                                    byteBuffer.put(position, (byte) (((codePoint >>> 6) & 63) | 128));
                                    byteBuffer.put(i, (byte) ((codePoint & 63) | 128));
                                    position = i;
                                    i2 = i5;
                                } catch (IndexOutOfBoundsException unused5) {
                                    position = i;
                                    i2 = i5;
                                    char charAt22222 = charSequence.charAt(i2);
                                    StringBuilder sb2222 = new StringBuilder(37);
                                    sb2222.append("Failed writing ");
                                    sb2222.append(charAt22222);
                                    sb2222.append(" at index ");
                                    sb2222.append(byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1));
                                    throw new ArrayIndexOutOfBoundsException(sb2222.toString());
                                }
                            } catch (IndexOutOfBoundsException unused6) {
                                position = i6;
                                i2 = i5;
                                char charAt222222 = charSequence.charAt(i2);
                                StringBuilder sb22222 = new StringBuilder(37);
                                sb22222.append("Failed writing ");
                                sb22222.append(charAt222222);
                                sb22222.append(" at index ");
                                sb22222.append(byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1));
                                throw new ArrayIndexOutOfBoundsException(sb22222.toString());
                            }
                        } else {
                            i2 = i5;
                        }
                    } catch (IndexOutOfBoundsException unused7) {
                        i2 = i5;
                        char charAt2222222 = charSequence.charAt(i2);
                        StringBuilder sb222222 = new StringBuilder(37);
                        sb222222.append("Failed writing ");
                        sb222222.append(charAt2222222);
                        sb222222.append(" at index ");
                        sb222222.append(byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1));
                        throw new ArrayIndexOutOfBoundsException(sb222222.toString());
                    }
                }
                throw new zzib(i2, length);
            }
            i2++;
            position++;
        }
        byteBuffer.position(position);
    }
}
