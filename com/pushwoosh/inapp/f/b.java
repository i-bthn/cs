package com.pushwoosh.inapp.f;

import org.json.JSONObject;

public class b {
    private String a;
    private long b;

    public static b a(com.pushwoosh.inapp.j.l.b bVar) {
        b bVar2 = new b();
        bVar2.a = bVar.c();
        bVar2.b = bVar.i();
        return bVar2;
    }

    public static b a(JSONObject jSONObject) {
        b bVar = new b();
        bVar.a = jSONObject.optString("code");
        bVar.b = jSONObject.optLong("updated");
        return bVar;
    }

    public String a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }
}
