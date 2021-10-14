package com.pushwoosh.secure.a;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class a {
    public byte[] a(byte[] bArr, SecretKey secretKey) throws GeneralSecurityException {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, instance.getBlockSize());
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr, instance.getBlockSize(), bArr.length);
        instance.init(2, secretKey, new IvParameterSpec(copyOfRange));
        return instance.doFinal(copyOfRange2);
    }
}
