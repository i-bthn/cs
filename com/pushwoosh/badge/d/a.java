package com.pushwoosh.badge.d;

import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;

public class a {
    private final com.pushwoosh.badge.c.a a;
    private final RequestManager b = NetworkModule.getRequestManager();

    public a(com.pushwoosh.badge.c.a aVar) {
        this.a = aVar;
    }

    public void a(int i) {
        this.a.a().set(i);
        RequestManager requestManager = this.b;
        if (requestManager != null) {
            requestManager.sendRequest(new b(i));
        }
    }
}
