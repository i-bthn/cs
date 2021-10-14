package com.pushwoosh.e.a.a.a.o.b;

public final class f {
    public float a(byte[] bArr) {
        return a(bArr, 0);
    }

    public float a(byte[] bArr, int i) {
        return Float.intBitsToFloat((bArr[i + 4] & 255) + ((bArr[i + 3] & 255) << 8) + ((bArr[i + 2] & 255) << 16) + (bArr[i + 1] << 24));
    }

    public boolean a(byte b) {
        return b == -6;
    }

    public byte[] a(float f) {
        int floatToIntBits = Float.floatToIntBits(f);
        return new byte[]{-6, (byte) (floatToIntBits >>> 24), (byte) (floatToIntBits >>> 16), (byte) (floatToIntBits >>> 8), (byte) floatToIntBits};
    }
}
