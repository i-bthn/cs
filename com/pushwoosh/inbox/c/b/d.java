package com.pushwoosh.inbox.c.b;

import androidx.core.app.NotificationCompat;
import com.pushwoosh.inbox.internal.data.InboxMessageStatus;
import com.pushwoosh.internal.network.LoggableRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class d extends a<Void> implements LoggableRequest {
    private final long a;
    private final InboxMessageStatus b;
    private final String c;

    public d(long j, InboxMessageStatus inboxMessageStatus, String str) {
        this.a = j;
        this.b = inboxMessageStatus;
        this.c = str;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        super.buildParams(jSONObject);
        jSONObject.put("inbox_code", String.valueOf(this.a));
        jSONObject.put(NotificationCompat.CATEGORY_STATUS, this.b.getCode());
        jSONObject.put("hash", this.c);
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "inboxStatus";
    }
}
