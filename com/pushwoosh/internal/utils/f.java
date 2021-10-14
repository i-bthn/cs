package com.pushwoosh.internal.utils;

import com.pushwoosh.internal.utils.j.c;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class f {
    public static <T> T a(String str, Class<?>... clsArr) throws IOException {
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            c cVar = new c(new ByteArrayInputStream(a(str)));
            cVar.a(clsArr);
            return (T) cVar.readObject();
        } catch (Exception e) {
            throw new IOException("Deserialization error: " + e.getMessage(), e);
        }
    }

    public static <T> String a(T t) throws IOException {
        if (t == null) {
            return "";
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(t);
            objectOutputStream.close();
            return a(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            throw new IOException("Serialization error: " + e.getMessage(), e);
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            sb.append((char) (((bArr[i] >> 4) & 15) + 97));
            sb.append((char) ((bArr[i] & 15) + 97));
        }
        return sb.toString();
    }

    private static byte[] a(String str) {
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length(); i += 2) {
            int i2 = i / 2;
            bArr[i2] = (byte) ((str.charAt(i) - 'a') << 4);
            bArr[i2] = (byte) (bArr[i2] + (str.charAt(i + 1) - 'a'));
        }
        return bArr;
    }
}
