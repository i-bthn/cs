package com.scottyab.rootbeer.util;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class QLog {
    public static final int ALL = 5;
    public static final int ERRORS_ONLY = 1;
    public static final int ERRORS_WARNINGS = 2;
    public static final int ERRORS_WARNINGS_INFO = 3;
    public static final int ERRORS_WARNINGS_INFO_DEBUG = 4;
    public static int LOGGING_LEVEL = 5;
    public static final int NONE = 0;
    private static final String TAG = "RootBeer";
    private static final String TAG_GENERAL_OUTPUT = "QLog";

    public static void e(Object obj, Throwable th) {
        if (isELoggable()) {
            Log.e(TAG, getTrace() + String.valueOf(obj));
            Log.e(TAG, getThrowableTrace(th));
            Log.e(TAG_GENERAL_OUTPUT, getTrace() + String.valueOf(obj));
            Log.e(TAG_GENERAL_OUTPUT, getThrowableTrace(th));
        }
    }

    public static void e(Object obj) {
        if (isELoggable()) {
            Log.e(TAG, getTrace() + String.valueOf(obj));
            Log.e(TAG_GENERAL_OUTPUT, getTrace() + String.valueOf(obj));
        }
    }

    public static void w(Object obj, Throwable th) {
        if (isWLoggable()) {
            Log.w(TAG, getTrace() + String.valueOf(obj));
            Log.w(TAG, getThrowableTrace(th));
            Log.w(TAG_GENERAL_OUTPUT, getTrace() + String.valueOf(obj));
            Log.w(TAG_GENERAL_OUTPUT, getThrowableTrace(th));
        }
    }

    public static void w(Object obj) {
        if (isWLoggable()) {
            Log.w(TAG, getTrace() + String.valueOf(obj));
            Log.w(TAG_GENERAL_OUTPUT, getTrace() + String.valueOf(obj));
        }
    }

    public static void i(Object obj) {
        if (isILoggable()) {
            Log.i(TAG, getTrace() + String.valueOf(obj));
        }
    }

    public static void d(Object obj) {
        if (isDLoggable()) {
            Log.d(TAG, getTrace() + String.valueOf(obj));
        }
    }

    public static void v(Object obj) {
        if (isVLoggable()) {
            Log.v(TAG, getTrace() + String.valueOf(obj));
        }
    }

    public static boolean isVLoggable() {
        return LOGGING_LEVEL > 4;
    }

    public static boolean isDLoggable() {
        return LOGGING_LEVEL > 3;
    }

    public static boolean isILoggable() {
        return LOGGING_LEVEL > 2;
    }

    public static boolean isWLoggable() {
        return LOGGING_LEVEL > 1;
    }

    public static boolean isELoggable() {
        return LOGGING_LEVEL > 0;
    }

    private static String getThrowableTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    private static String getTrace() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String methodName = stackTrace[2].getMethodName();
        String className = stackTrace[2].getClassName();
        int lineNumber = stackTrace[2].getLineNumber();
        String substring = className.substring(className.lastIndexOf(46) + 1);
        return substring + ": " + methodName + "() [" + lineNumber + "] - ";
    }

    public static void handleException(Exception exc) {
        e(exc.toString());
        exc.printStackTrace();
    }

    private QLog() {
    }
}
