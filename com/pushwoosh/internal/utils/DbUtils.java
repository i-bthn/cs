package com.pushwoosh.internal.utils;

import android.text.TextUtils;

public class DbUtils {
    public static String[] getSelectionArgs(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr2[i] = TextUtils.isEmpty(strArr[i]) ? "'null'" : strArr[i];
        }
        return strArr2;
    }
}
