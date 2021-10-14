package com.pushwoosh.inapp.j;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.UserIdUpdatedEvent;
import com.pushwoosh.internal.network.LoggableRequest;
import com.pushwoosh.internal.network.PushRequest;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class i extends PushRequest<Map<String, Object>> implements LoggableRequest {
    private String a;

    i(String str) {
        this.a = str;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        jSONObject.put("userId", this.a);
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "registerUser";
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    @Nullable
    public Map<String, Object> parseResponse(@NonNull JSONObject jSONObject) throws JSONException {
        EventBus.sendEvent(new UserIdUpdatedEvent());
        return (Map) super.parseResponse(jSONObject);
    }
}
