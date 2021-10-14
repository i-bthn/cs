package com.pushwoosh.inapp.j;

import androidx.annotation.NonNull;
import com.pushwoosh.inapp.j.l.b;
import com.pushwoosh.internal.network.LoggableRequest;
import com.pushwoosh.internal.network.PushRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class a extends PushRequest<List<b>> implements LoggableRequest {
    a() {
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        jSONObject.put("language", Locale.getDefault().getLanguage());
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "getInApps";
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public List<b> parseResponse(@NonNull JSONObject jSONObject) throws JSONException {
        List<b> emptyList = Collections.emptyList();
        if (jSONObject.has("inApps")) {
            JSONArray jSONArray = jSONObject.getJSONArray("inApps");
            emptyList = new ArrayList<>(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                emptyList.add(new b(jSONArray.getJSONObject(i)));
            }
        }
        return emptyList;
    }
}
