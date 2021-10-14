package com.pushwoosh.e.a.a.a.o.b;

public final class g {
    public int a() {
        return 5;
    }

    public int a(byte[] bArr) {
        return a(bArr, 0);
    }

    public int a(byte[] bArr, int i) {
        return (bArr[i + 4] & 255) + ((bArr[i + 3] & 255) << 8) + ((bArr[i + 2] & 255) << 16) + (bArr[i + 1] << 24);
    }

    public boolean a(byte b) {
        return b == -3;
    }

    public byte[] a(int i) {
        return new byte[]{-3, (byte) ((i >>> 24) & 255), (byte) ((i >>> 16) & 255), (byte) ((i >>> 8) & 255), (byte) (i & 255)};
    }
}
