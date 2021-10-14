package com.pushwoosh.inapp.j;

import com.pushwoosh.internal.network.LoggableRequest;
import com.pushwoosh.internal.network.PushRequest;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class d extends PushRequest<Void> implements LoggableRequest {
    private String a;
    private String b;
    private boolean c;

    d(String str, String str2, boolean z) {
        this.a = str;
        this.b = str2;
        this.c = z;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        jSONObject.put("oldUserId", this.a);
        jSONObject.put("newUserId", this.b);
        jSONObject.put("merge", this.c);
        jSONObject.put("ts", (System.currentTimeMillis() / 1000) + ((long) (Calendar.getInstance().getTimeZone().getRawOffset() / 1000)));
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "mergeUser";
    }
}
