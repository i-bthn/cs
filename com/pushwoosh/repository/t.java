package com.pushwoosh.repository;

import com.pushwoosh.internal.network.LoggableRequest;
import com.pushwoosh.internal.network.PushRequest;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class t extends PushRequest<Void> implements LoggableRequest {
    private JSONObject a;
    private String b;

    public t(JSONObject jSONObject, String str) {
        this.a = jSONObject;
        this.b = str;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        Iterator<String> keys = this.a.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject jSONObject2 = this.a;
            jSONObject2.put(next, jSONObject2.opt(next));
        }
        jSONObject.put("tags", this.a);
        jSONObject.put("email", this.b);
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "setEmailTags";
    }
}
