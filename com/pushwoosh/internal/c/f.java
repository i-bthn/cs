package com.pushwoosh.internal.c;

import android.util.Base64;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

public class f {
    private int a(PublicKey publicKey) {
        if (publicKey instanceof RSAKey) {
            return ((RSAKey) publicKey).getModulus().bitLength();
        }
        throw new IllegalArgumentException("PublicKey should be instance of RsaKey");
    }

    private byte[] a(int i, byte[] bArr, Cipher cipher, String str) throws IllegalBlockSizeException, BadPaddingException {
        int a = e.a(str, i, cipher);
        byte[] bArr2 = new byte[((i / 8) * ((bArr.length / a) + (bArr.length % a > 0 ? 1 : 0)))];
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr.length) {
            int i4 = i2 + a;
            byte[] doFinal = cipher.doFinal(Arrays.copyOfRange(bArr, i2, Math.min(i4, bArr.length)));
            System.arraycopy(doFinal, 0, bArr2, i3, doFinal.length);
            i3 += doFinal.length;
            i2 = i4;
        }
        return bArr2;
    }

    public String a(byte[] bArr, PublicKey publicKey, String str) throws Exception {
        Cipher instance = Cipher.getInstance(b.a(str));
        instance.init(1, publicKey);
        return new String(Base64.encode(a(a(publicKey), bArr, instance, str), 0)).replaceAll("(\\r|\\n)", "");
    }
}
