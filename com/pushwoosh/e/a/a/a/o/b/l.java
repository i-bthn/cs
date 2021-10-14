package com.pushwoosh.e.a.a.a.o.b;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class l {
    private byte[] a(int i) {
        return new byte[]{(byte) ((i >>> 24) & 255), (byte) ((i >>> 16) & 255), (byte) ((i >>> 8) & 255), (byte) (i & 255)};
    }

    private int b(byte[] bArr) {
        return (bArr[3] & 255) + ((bArr[2] & 255) << 8) + ((bArr[1] & 255) << 16) + (bArr[0] << 24);
    }

    public Set<String> a(byte[] bArr) {
        byte b = bArr[0];
        int i = 1;
        if (b == -1) {
            HashSet hashSet = new HashSet();
            while (i < bArr.length) {
                byte[] bArr2 = new byte[4];
                System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
                int b2 = b(bArr2);
                byte[] bArr3 = new byte[b2];
                for (int i2 = 0; i2 < bArr3.length; i2++) {
                    bArr3[i2] = bArr[i + i2 + 4];
                }
                hashSet.add(new String(bArr3));
                i += 4 + b2;
            }
            return Collections.unmodifiableSet(hashSet);
        }
        throw new ClassCastException(String.format("Set<String> cannot be deserialized in '%s' flag type", Byte.valueOf(b)));
    }

    public boolean a(byte b) {
        return b == -1;
    }

    public byte[] a(Set<String> set) {
        byte[][] bArr = new byte[set.size()][];
        int i = 1;
        int i2 = 0;
        for (String str : set) {
            byte[] bytes = str.getBytes();
            byte[] a = a(bytes.length);
            byte[] bArr2 = new byte[(bytes.length + a.length)];
            System.arraycopy(a, 0, bArr2, 0, a.length);
            System.arraycopy(bytes, 0, bArr2, a.length, bytes.length);
            bArr[i2] = bArr2;
            i += bArr2.length;
            i2++;
        }
        byte[] bArr3 = new byte[i];
        bArr3[0] = -1;
        int i3 = 1;
        for (byte[] bArr4 : bArr) {
            System.arraycopy(bArr4, 0, bArr3, i3, bArr4.length);
            i3 += bArr4.length;
        }
        return bArr3;
    }
}
