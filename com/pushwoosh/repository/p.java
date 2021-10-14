package com.pushwoosh.repository;

import com.pushwoosh.internal.network.LoggableRequest;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class p extends a implements LoggableRequest {
    private String a;

    p(String str) {
        this.a = str;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.repository.a, com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        super.buildParams(jSONObject);
        jSONObject.put("push_token", this.a);
        ArrayList<String> rawResourses = GeneralUtils.getRawResourses();
        if (rawResourses != null) {
            jSONObject.put("sounds", new JSONArray((Collection) rawResourses));
        }
    }

    @Override // com.pushwoosh.repository.a, com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "registerDevice";
    }
}
