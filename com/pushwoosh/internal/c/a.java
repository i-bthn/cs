package com.pushwoosh.internal.c;

import android.util.Base64;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class a {
    public String a(byte[] bArr, byte[] bArr2, String str) throws GeneralSecurityException {
        byte[] bytes = str.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(bArr2));
        return new String(Base64.encode(a(bArr2, instance.doFinal(bytes)), 0));
    }

    /* access modifiers changed from: package-private */
    public byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
