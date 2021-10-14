package com.pushwoosh.exception;

public class PushwooshException extends Exception {
    public PushwooshException(String str) {
        super(str);
    }

    public PushwooshException(String str, Throwable th) {
        super(str, th);
    }

    public PushwooshException(Throwable th) {
        super(th);
    }
}
