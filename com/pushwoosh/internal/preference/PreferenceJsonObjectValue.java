package com.pushwoosh.internal.preference;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.JsonUtils;
import com.pushwoosh.internal.utils.PWLog;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PreferenceJsonObjectValue implements PreferenceValue {
    @Nullable
    private final SharedPreferences a;
    private final String b;
    @Nullable
    private JSONObject c;

    public PreferenceJsonObjectValue(@Nullable SharedPreferences sharedPreferences, String str) {
        this.b = str;
        this.a = sharedPreferences;
        String str2 = null;
        if (sharedPreferences != null) {
            try {
                str2 = sharedPreferences.getString(str, null);
            } catch (Exception e) {
                PWLog.exception(e);
                return;
            }
        }
        if (str2 != null) {
            this.c = new JSONObject(str2);
        }
    }

    private JSONObject a(JSONObject jSONObject) {
        try {
            JSONArray names = jSONObject.names();
            if (names == null) {
                return new JSONObject();
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < names.length(); i++) {
                arrayList.add(names.getString(i));
            }
            return new JSONObject(jSONObject, (String[]) arrayList.toArray(new String[0]));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public JSONObject get() {
        return this.c;
    }

    public void merge(@NonNull JSONObject jSONObject) {
        JSONObject jSONObject2 = this.c;
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        JsonUtils.mergeJson(jSONObject, jSONObject2);
        set(jSONObject2);
    }

    public void set(JSONObject jSONObject) {
        JSONObject jSONObject2;
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            PWLog.error("Incorrect state of the app preferences is null");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String str = null;
        if (jSONObject != null) {
            jSONObject2 = a(jSONObject);
            if (jSONObject2 != null) {
                str = jSONObject2.toString();
            }
        } else {
            jSONObject2 = null;
        }
        edit.putString(this.b, str);
        edit.apply();
        this.c = jSONObject2;
    }
}
