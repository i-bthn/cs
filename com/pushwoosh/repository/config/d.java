package com.pushwoosh.repository.config;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.network.LoggableRequest;
import com.pushwoosh.internal.network.PushRequest;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d extends PushRequest<a> implements LoggableRequest {
    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("channels");
        jSONArray.put("events");
        jSONArray.put("public_key");
        jSONObject.put("features", jSONArray);
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "getConfig";
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    @NonNull
    public a parseResponse(@NonNull JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("features");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String str = "";
        int i = 1;
        if (optJSONObject != null) {
            JSONArray optJSONArray = optJSONObject.optJSONArray("channels");
            JSONArray optJSONArray2 = optJSONObject.optJSONArray("events");
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    arrayList.add(new Channel(optJSONArray.getJSONObject(i2)));
                }
            }
            if (optJSONArray2 != null) {
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    String optString = optJSONArray2.optString(i3);
                    if (!optString.isEmpty()) {
                        arrayList2.add(new c(optString));
                    }
                }
            }
            str = optJSONObject.getString("public_key");
            try {
                i = optJSONObject.getInt("logger");
            } catch (JSONException unused) {
            }
        }
        return new a(arrayList, arrayList2, str, i);
    }
}
