package com.pushwoosh.internal.crash;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* access modifiers changed from: package-private */
public class j {
    private static final Pattern a = Pattern.compile("[0-9a-f]+", 2);

    public static String a(String str) throws IllegalArgumentException {
        if (str != null) {
            String trim = str.trim();
            Matcher matcher = a.matcher(trim);
            if (trim.length() != 32) {
                throw new IllegalArgumentException("App ID length must be 32 characters.");
            } else if (matcher.matches()) {
                return trim;
            } else {
                throw new IllegalArgumentException("App ID must match regex pattern /[0-9a-f]+/i");
            }
        } else {
            throw new IllegalArgumentException("App ID must not be null.");
        }
    }
}
