package com.pushwoosh.internal.network;

import com.pushwoosh.exception.PushwooshException;

public class NetworkException extends PushwooshException {
    public NetworkException(String str) {
        super(str);
    }
}
