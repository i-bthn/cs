package com.pushwoosh.inapp.j;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.UserIdUpdatedEvent;
import com.pushwoosh.internal.network.LoggableRequest;
import com.pushwoosh.internal.network.PushRequest;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class h extends PushRequest<Map<String, Object>> implements LoggableRequest {
    private String a;
    private String b;

    h(String str, String str2) {
        this.a = str2;
        this.b = str;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        jSONObject.put("email", this.a);
        jSONObject.put("tz_offset", TimeUnit.SECONDS.convert((long) TimeZone.getDefault().getOffset(new Date().getTime()), TimeUnit.MILLISECONDS));
        jSONObject.put("userId", this.b);
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "registerEmailUser";
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    @Nullable
    public Map<String, Object> parseResponse(@NonNull JSONObject jSONObject) throws JSONException {
        EventBus.sendEvent(new UserIdUpdatedEvent());
        return (Map) super.parseResponse(jSONObject);
    }
}
