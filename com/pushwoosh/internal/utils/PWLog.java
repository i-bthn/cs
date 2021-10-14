package com.pushwoosh.internal.utils;

import android.util.Log;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.secure.crypt.manager.RsaDecryptorManager;

public final class PWLog {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static boolean a = false;
    private static Level b = Level.DEBUG;
    private static LogsUpdateListener c;

    public enum Level {
        NONE,
        FATAL,
        ERROR,
        WARN,
        INFO,
        DEBUG,
        NOISE,
        INTERNAL
    }

    public interface LogsUpdateListener {
        void logUpdated(Level level, String str);
    }

    private static String a(String str, String str2) {
        if (str == null) {
            return str2;
        }
        return "[" + str + "] " + str2;
    }

    private static void a(Level level, String str) {
        LogsUpdateListener logsUpdateListener = c;
        if (logsUpdateListener != null) {
            logsUpdateListener.logUpdated(level, str);
        }
    }

    private static boolean a() {
        return a(Level.DEBUG);
    }

    private static boolean a(Level level) {
        return b.compareTo(level) >= 0;
    }

    private static boolean b() {
        return a(Level.ERROR);
    }

    private static boolean c() {
        return a(Level.FATAL);
    }

    private static boolean d() {
        return a(Level.INFO);
    }

    public static void debug(String str) {
        debug((String) null, str);
    }

    public static void debug(String str, String str2) {
        if (a()) {
            Log.d(RsaDecryptorManager.ALIAS, a(str, str2));
        }
        a(Level.DEBUG, a(str, str2));
    }

    public static void debug(String str, String str2, Throwable th) {
        if (a()) {
            Log.d(RsaDecryptorManager.ALIAS, a(str, str2), th);
        }
    }

    public static void debug(String str, Throwable th) {
        debug(null, str, th);
    }

    private static boolean e() {
        return a(Level.INTERNAL);
    }

    public static void error(String str) {
        error((String) null, str);
    }

    public static void error(String str, String str2) {
        if (b()) {
            Log.e(RsaDecryptorManager.ALIAS, a(str, str2));
        }
        a(Level.ERROR, a(str, str2));
    }

    public static void error(String str, String str2, Throwable th) {
        if (b()) {
            Log.e(RsaDecryptorManager.ALIAS, a(str, str2), th);
        }
    }

    public static void error(String str, Throwable th) {
        error(null, str, th);
    }

    public static void exception(Throwable th) {
        error(null, "Exception occurred", th);
    }

    private static boolean f() {
        return a(Level.NOISE);
    }

    public static void fatal(String str) {
        fatal((String) null, str);
    }

    public static void fatal(String str, String str2) {
        if (c()) {
            Log.wtf(RsaDecryptorManager.ALIAS, a(str, str2));
        }
        a(Level.FATAL, a(str, str2));
    }

    public static void fatal(String str, String str2, Throwable th) {
        if (c()) {
            Log.wtf(RsaDecryptorManager.ALIAS, a(str, str2), th);
        }
    }

    public static void fatal(String str, Throwable th) {
        fatal(null, str, th);
    }

    private static boolean g() {
        return a(Level.WARN);
    }

    public static void info(String str) {
        info((String) null, str);
    }

    public static void info(String str, String str2) {
        if (d()) {
            Log.i(RsaDecryptorManager.ALIAS, a(str, str2));
        }
        a(Level.INFO, a(str, str2));
    }

    public static void info(String str, String str2, Throwable th) {
        if (d()) {
            Log.i(RsaDecryptorManager.ALIAS, a(str, str2), th);
        }
    }

    public static void info(String str, Throwable th) {
        info(null, str, th);
    }

    public static void init() {
        if (!a) {
            String str = RepositoryModule.getRegistrationPreferences().logLevel().get();
            if (str != null) {
                try {
                    b = Level.valueOf(str);
                    if (b == Level.INTERNAL) {
                        b = Level.DEBUG;
                    }
                } catch (IllegalArgumentException unused) {
                    error("Unrecognized log level: " + str);
                }
            }
            info("Log level: " + b.name());
            a = true;
        }
    }

    public static void internal(String str) {
        internal((String) null, str);
    }

    public static void internal(String str, String str2) {
        if (e()) {
            Log.v(RsaDecryptorManager.ALIAS, a(str, str2));
        }
        a(Level.INTERNAL, a(str, str2));
    }

    public static void internal(String str, String str2, Throwable th) {
        if (e()) {
            Log.v(RsaDecryptorManager.ALIAS, a(str, str2), th);
        }
    }

    public static void internal(String str, Throwable th) {
        internal(null, str, th);
    }

    public static boolean isLoggable(String str, int i) {
        switch (i) {
            case 2:
                return f();
            case 3:
                return a();
            case 4:
                return d();
            case 5:
                return g();
            case 6:
                return b();
            case 7:
                return c();
            default:
                return false;
        }
    }

    public static void noise(String str) {
        noise((String) null, str);
    }

    public static void noise(String str, String str2) {
        if (f()) {
            Log.v(RsaDecryptorManager.ALIAS, a(str, str2));
        }
        a(Level.NONE, a(str, str2));
    }

    public static void noise(String str, String str2, Throwable th) {
        if (f()) {
            Log.v(RsaDecryptorManager.ALIAS, a(str, str2), th);
        }
    }

    public static void noise(String str, Throwable th) {
        noise(null, str, th);
    }

    public static void setLogsUpdateListener(LogsUpdateListener logsUpdateListener) {
        c = logsUpdateListener;
    }

    public static void warn(String str) {
        warn((String) null, str);
    }

    public static void warn(String str, String str2) {
        if (g()) {
            Log.w(RsaDecryptorManager.ALIAS, a(str, str2));
        }
        a(Level.WARN, a(str, str2));
    }

    public static void warn(String str, String str2, Throwable th) {
        if (g()) {
            Log.w(RsaDecryptorManager.ALIAS, a(str, str2), th);
        }
    }

    public static void warn(String str, Throwable th) {
        warn(null, str, th);
    }
}
