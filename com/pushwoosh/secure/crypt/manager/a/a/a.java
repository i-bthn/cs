package com.pushwoosh.secure.crypt.manager.a.a;

import org.json.JSONException;
import org.json.JSONObject;

public class a implements c<Void> {
    private final String a;
    private final String b;

    public a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    /* renamed from: a */
    public Void b(JSONObject jSONObject) {
        return null;
    }

    @Override // com.pushwoosh.secure.crypt.manager.a.a.c
    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("hwid", this.a);
        jSONObject.put("appCode", this.b);
        jSONObject.put("deviceType", 3);
        return jSONObject;
    }

    @Override // com.pushwoosh.secure.crypt.manager.a.a.c
    public String b() {
        return "device/delete";
    }
}
