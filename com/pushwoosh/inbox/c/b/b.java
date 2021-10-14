package com.pushwoosh.inbox.c.b;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.network.LoggableRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends a<c> implements LoggableRequest {
    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "getInboxMessages";
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public c parseResponse(@NonNull JSONObject jSONObject) throws JSONException {
        return new c(jSONObject);
    }
}
