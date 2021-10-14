package com.pushwoosh.e.a.a.a.o.b;

public final class e {
    public double a(byte[] bArr) {
        return a(bArr, 0);
    }

    public double a(byte[] bArr, int i) {
        return Double.longBitsToDouble(((long) ((bArr[i + 8] & 255) + ((bArr[i + 7] & 255) << 8) + ((bArr[i + 6] & 255) << 16))) + (((long) (bArr[i + 5] & 255)) << 24) + (((long) (bArr[i + 4] & 255)) << 32) + (((long) (bArr[i + 3] & 255)) << 40) + (((long) (bArr[i + 2] & 255)) << 48) + (((long) bArr[i + 1]) << 56));
    }

    public boolean a(byte b) {
        return b == -5;
    }
}
