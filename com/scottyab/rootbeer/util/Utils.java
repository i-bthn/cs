package com.scottyab.rootbeer.util;

public final class Utils {
    private Utils() throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }

    public static boolean isSelinuxFlagInEnabled() {
        String str;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod("get", String.class).invoke(cls, "ro.build.selinux");
        } catch (Exception unused) {
            str = null;
        }
        return "1".equals(str);
    }
}
