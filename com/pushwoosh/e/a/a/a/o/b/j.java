package com.pushwoosh.e.a.a.a.o.b;

public final class j {
    public short a(byte[] bArr) {
        return a(bArr, 0);
    }

    public short a(byte[] bArr, int i) {
        return (short) ((bArr[i + 1] << 8) + (bArr[i + 2] & 255));
    }

    public boolean a(byte b) {
        return b == -9;
    }
}
