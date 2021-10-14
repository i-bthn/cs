package com.pushwoosh.e.a.a.a.o.b;

public final class k {
    public String a(byte[] bArr) {
        return a(bArr, 0, bArr.length - 1);
    }

    public String a(byte[] bArr, int i, int i2) {
        return new String(bArr, i + 1, i2);
    }

    public boolean a(byte b) {
        return b == -2;
    }

    public byte[] a(String str) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[(bytes.length + 1)];
        bArr[0] = -2;
        System.arraycopy(bytes, 0, bArr, 1, bytes.length);
        return bArr;
    }
}
