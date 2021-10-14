package com.pushwoosh.e.a.a.a.k.c;

public final class c {
    private static final byte[] d = new byte[0];
    private final int a;
    private final String b;
    private final byte[] c;

    private c(int i, String str, byte[] bArr) {
        this.a = i;
        this.b = str;
        this.c = bArr;
    }

    public static c a(String str) {
        return new c(3, str, d);
    }

    static c a(String str, byte[] bArr) {
        return new c(1, str, bArr);
    }

    public static c b(String str, byte[] bArr) {
        return new c(2, str, bArr);
    }

    public int a() {
        return this.a;
    }

    public byte[] b() {
        return this.c;
    }

    public String c() {
        return this.b;
    }
}
