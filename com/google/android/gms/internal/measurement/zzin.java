package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzin extends IOException {
    /* JADX WARNING: Illegal instructions before constructor call */
    zzin(int i, int i2) {
        super(r0.toString());
        StringBuilder sb = new StringBuilder(108);
        sb.append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ");
        sb.append(i);
        sb.append(" limit ");
        sb.append(i2);
        sb.append(").");
    }
}
