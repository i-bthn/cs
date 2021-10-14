package com.pushwoosh.internal.network;

import org.json.JSONObject;

public class b extends PushRequest<Void> implements LoggableRequest {
    private String a;
    private String b;
    private JSONObject c;

    b(String str, String str2, JSONObject jSONObject) {
        this.a = str;
        this.b = str2;
        this.c = jSONObject;
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public JSONObject a() {
        return this.c;
    }

    public String b() {
        return this.a;
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return this.b;
    }
}
