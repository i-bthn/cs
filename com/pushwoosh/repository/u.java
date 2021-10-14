package com.pushwoosh.repository;

import com.pushwoosh.internal.network.LoggableRequest;
import com.pushwoosh.internal.network.PushRequest;
import com.pushwoosh.tags.Tags;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

class u extends PushRequest<Void> implements LoggableRequest {
    private JSONObject a;

    public u(JSONObject jSONObject) {
        this.a = jSONObject;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        Iterator<String> keys = this.a.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String optString = this.a.optString(next);
            if (optString != null && optString.startsWith("#pwinc#")) {
                this.a.put(next, Tags.incrementInt(next, Integer.valueOf(Integer.parseInt(optString.substring(7))).intValue()));
            }
            JSONObject jSONObject2 = this.a;
            jSONObject2.put(next, jSONObject2.opt(next));
        }
        jSONObject.put("tags", this.a);
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "setTags";
    }
}
