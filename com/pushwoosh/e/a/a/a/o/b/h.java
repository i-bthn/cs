package com.pushwoosh.e.a.a.a.o.b;

public final class h {
    public long a(byte[] bArr) {
        return a(bArr, 0);
    }

    public long a(byte[] bArr, int i) {
        return (((long) bArr[i + 8]) & 255) + ((((long) bArr[i + 7]) & 255) << 8) + ((((long) bArr[i + 6]) & 255) << 16) + ((((long) bArr[i + 5]) & 255) << 24) + ((((long) bArr[i + 4]) & 255) << 32) + ((((long) bArr[i + 3]) & 255) << 40) + ((255 & ((long) bArr[i + 2])) << 48) + (((long) bArr[i + 1]) << 56);
    }

    public boolean a(byte b) {
        return b == -4;
    }

    public byte[] a(long j) {
        return new byte[]{-4, (byte) ((int) (j >>> 56)), (byte) ((int) (j >>> 48)), (byte) ((int) (j >>> 40)), (byte) ((int) (j >>> 32)), (byte) ((int) (j >>> 24)), (byte) ((int) (j >>> 16)), (byte) ((int) (j >>> 8)), (byte) ((int) j)};
    }
}
