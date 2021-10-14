package com.pushwoosh.secure.crypt;

public final class a {
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0041 A[RETURN] */
    public static String a(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != -1724799148) {
            if (hashCode == 489623371 && str.equals("PKCS1Padding")) {
                c = 0;
                switch (c) {
                    case 0:
                        return "RSA/ECB/PKCS1Padding";
                    case 1:
                        return "RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING";
                    default:
                        throw new IllegalArgumentException("Incorrect padding: " + str);
                }
            }
        } else if (str.equals("OAEPPadding")) {
            c = 1;
            switch (c) {
            }
        }
        c = 65535;
        switch (c) {
        }
    }
}
