package com.pushwoosh.repository;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.network.LoggableRequest;

public class f extends e implements LoggableRequest {
    private String a;

    public f(String str) {
        this.a = str;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    @NonNull
    public String getHwid() {
        return this.a;
    }
}
