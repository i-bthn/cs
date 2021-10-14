package com.pushwoosh.secure.c.a;

import android.os.Bundle;
import com.pushwoosh.internal.utils.PWLog;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    private Bundle a(Bundle bundle, JSONObject jSONObject) throws JSONException {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            bundle.putString(next, jSONObject.getString(next));
        }
        return bundle;
    }

    private JSONObject a(String str) throws JSONException {
        return new JSONObject(str);
    }

    public Bundle a(Bundle bundle, String str) {
        try {
            return a(bundle, a(str));
        } catch (JSONException e) {
            PWLog.exception(e);
            return bundle;
        }
    }
}
