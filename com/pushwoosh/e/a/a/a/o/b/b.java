package com.pushwoosh.e.a.a.a.o.b;

public final class b {
    public boolean a(byte b) {
        return b == -12;
    }

    public byte[] a(byte[] bArr) {
        return a(bArr, 0, bArr.length - 1);
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i + 1, bArr2, 0, i2);
        return bArr2;
    }
}
