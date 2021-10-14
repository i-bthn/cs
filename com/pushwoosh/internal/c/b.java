package com.pushwoosh.internal.c;

public final class b {
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0043 A[RETURN] */
    public static String a(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != -1724799148) {
            if (hashCode == 489623371 && str.equals("PKCS1Padding")) {
                c = 0;
                if (c == 0) {
                    return "RSA/ECB/PKCS1Padding";
                }
                if (c == 1) {
                    return "RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING";
                }
                throw new IllegalArgumentException("Incorrect padding: " + str);
            }
        } else if (str.equals("OAEPPadding")) {
            c = 1;
            if (c == 0) {
            }
        }
        c = 65535;
        if (c == 0) {
        }
    }
}
