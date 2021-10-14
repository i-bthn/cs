package com.pushwoosh.internal.c;

import androidx.annotation.NonNull;
import java.util.Random;

public class d {
    private Random a;

    public d(Random random) {
        this.a = random;
    }

    @NonNull
    public byte[] a() {
        byte[] bArr = new byte[16];
        this.a.nextBytes(bArr);
        return bArr;
    }
}
