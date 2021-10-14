package com.pushwoosh.inapp.j.l;

import android.net.Uri;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.ImagesContract;
import com.pushwoosh.inapp.g.a;
import com.pushwoosh.internal.utils.JsonUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class c {
    @NonNull
    static b a(String str) throws a {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("hash");
            boolean optBoolean = jSONObject.optBoolean("required", false);
            int optInt = jSONObject.optInt("priority", 0);
            long j = jSONObject.getLong("ts");
            String string = jSONObject.getString(ImagesContract.URL);
            String optString2 = jSONObject.optString("gdpr");
            return new b(a(string, Uri.parse(string).getLastPathSegment()), string, optString, j, a.TOP, a(jSONObject), optBoolean, optInt, null, optString2);
        } catch (Exception e) {
            throw new a("Can't parse richMedia", e);
        }
    }

    @NonNull
    private static String a(String str, String str2) {
        if (str2 != null) {
            if (str2.contains(".")) {
                str2 = str2.substring(0, str2.lastIndexOf("."));
            }
            return "r-" + str2;
        }
        throw new IllegalArgumentException("Missing code in richMedia url: " + str);
    }

    static Map<String, String> a(Map<String, Object> map) {
        if (map == null) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                hashMap.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return hashMap;
    }

    @NonNull
    private static Map<String, Object> a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject("{}");
        if (jSONObject.has("tags")) {
            jSONObject2 = jSONObject.getJSONObject("tags");
        }
        return JsonUtils.jsonToMap(jSONObject2);
    }
}
