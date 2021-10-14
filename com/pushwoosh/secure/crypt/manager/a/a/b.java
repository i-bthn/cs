package com.pushwoosh.secure.crypt.manager.a.a;

import com.pushwoosh.secure.a.c;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements c<c> {
    private final String a;
    private final String b;
    private final String c;

    public b(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    /* renamed from: a */
    public c b(JSONObject jSONObject) {
        String optString = jSONObject.optString("key", null);
        int optInt = jSONObject.optInt("version", 0);
        if (optString == null || optInt == 0) {
            return null;
        }
        return new c(optString, optInt);
    }

    @Override // com.pushwoosh.secure.crypt.manager.a.a.c
    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("hwid", this.a);
        jSONObject.put("publicKey", this.b);
        jSONObject.put("appCode", this.c);
        jSONObject.put("deviceType", 3);
        return jSONObject;
    }

    @Override // com.pushwoosh.secure.crypt.manager.a.a.c
    public String b() {
        return "device/create";
    }
}
