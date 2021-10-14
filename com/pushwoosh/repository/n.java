package com.pushwoosh.repository;

import com.pushwoosh.internal.network.LoggableRequest;
import com.pushwoosh.internal.network.PushRequest;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class n extends PushRequest<Void> implements LoggableRequest {
    private String a;
    private String b;

    public n(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        String str = this.a;
        if (str != null) {
            jSONObject.put("hash", str);
        }
        String str2 = this.b;
        if (str2 != null) {
            jSONObject.put("metaData", str2);
        }
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "pushStat";
    }
}
