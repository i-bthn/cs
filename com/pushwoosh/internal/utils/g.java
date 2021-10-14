package com.pushwoosh.internal.utils;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashSet;
import java.util.Map;

public class g {
    public static Bundle a(SharedPreferences sharedPreferences, String str) {
        Bundle bundle = new Bundle();
        Map<String, ?> all = sharedPreferences.getAll();
        String str2 = str + "$##$";
        HashSet<String> hashSet = new HashSet();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith(str2)) {
                String a = a(key, str2);
                if (!a.contains("$##$")) {
                    Object value = entry.getValue();
                    if (value != null) {
                        if (value instanceof Integer) {
                            bundle.putInt(a, ((Integer) value).intValue());
                        } else if (value instanceof Long) {
                            bundle.putLong(a, ((Long) value).longValue());
                        } else if (value instanceof Boolean) {
                            bundle.putBoolean(a, ((Boolean) value).booleanValue());
                        } else if (value instanceof CharSequence) {
                            bundle.putString(a, value.toString());
                        }
                    }
                } else {
                    hashSet.add(b(a, "$##$"));
                }
            }
        }
        for (String str3 : hashSet) {
            bundle.putBundle(str3, a(sharedPreferences, str2 + str3));
        }
        return bundle;
    }

    private static String a(String str, String str2) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str.startsWith(str2)) ? str : str.substring(str2.length());
    }

    private static String b(String str, String str2) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            return str;
        }
        if (str2.length() == 0) {
            return "";
        }
        int indexOf = str.indexOf(str2);
        return indexOf == -1 ? str : str.substring(0, indexOf);
    }
}
