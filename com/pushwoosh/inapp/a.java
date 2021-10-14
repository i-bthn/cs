package com.pushwoosh.inapp;

import androidx.annotation.WorkerThread;
import com.pushwoosh.inapp.k.c;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.d;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private c a;

    public a(c cVar) {
        this.a = cVar;
    }

    private Map<String, String> a(JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        PWLog.debug("[InApp]InAppConfig", "Localization : {");
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String string = jSONObject.getString(next);
            hashMap.put(next, string);
            PWLog.debug("[InApp]InAppConfig", "  \"" + next + "\" : \"" + string + "\"");
        }
        PWLog.debug("[InApp]InAppConfig", "}");
        return hashMap;
    }

    @WorkerThread
    public Map<String, String> a(String str) throws IOException, JSONException {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject(d.d(this.a.a(str)));
        JSONObject jSONObject3 = jSONObject2.getJSONObject("localization");
        String string = jSONObject2.getString("default_language");
        PWLog.debug("[InApp]InAppConfig", "default language : " + string);
        try {
            jSONObject = jSONObject3.getJSONObject(Locale.getDefault().getLanguage());
        } catch (JSONException unused) {
            PWLog.warn("[InApp]InAppConfig", "Preferred language not found, fall back to default");
            jSONObject = jSONObject3.getJSONObject(string);
        }
        return a(jSONObject);
    }
}
