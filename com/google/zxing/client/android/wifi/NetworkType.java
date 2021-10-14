package com.google.zxing.client.android.wifi;

/* access modifiers changed from: package-private */
public enum NetworkType {
    WEP,
    WPA,
    NO_PASSWORD;

    static NetworkType forIntentValue(String str) {
        if (str == null) {
            return NO_PASSWORD;
        }
        if ("WPA".equals(str) || "WPA2".equals(str)) {
            return WPA;
        }
        if ("WEP".equals(str)) {
            return WEP;
        }
        if ("nopass".equals(str)) {
            return NO_PASSWORD;
        }
        throw new IllegalArgumentException(str);
    }
}
