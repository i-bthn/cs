package com.pushwoosh.internal.c;

import javax.crypto.Cipher;

public class e {
    public static int a(String str, int i, Cipher cipher) {
        if (cipher.getBlockSize() != 0) {
            return cipher.getBlockSize();
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1724799148) {
            if (hashCode == 489623371 && str.equals("PKCS1Padding")) {
                c = 1;
            }
        } else if (str.equals("OAEPPadding")) {
            c = 0;
        }
        return c != 0 ? c != 1 ? i / 8 : (i / 8) - 11 : (i / 8) - 66;
    }
}
