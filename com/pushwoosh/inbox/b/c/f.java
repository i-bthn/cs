package com.pushwoosh.inbox.b.c;

import com.pushwoosh.inbox.d.a;
import com.pushwoosh.internal.richmedia.ResourceAction;
import com.pushwoosh.internal.utils.PWLog;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class f implements b {
    f() {
    }

    @Override // com.pushwoosh.inbox.b.c.b
    public void a(JSONObject jSONObject) throws JSONException {
        JSONObject c = a.c(jSONObject);
        if (c == null) {
            PWLog.noise("Incorrect richMedia action");
        } else {
            ResourceAction.performRichMediaAction(c.toString());
        }
    }
}
