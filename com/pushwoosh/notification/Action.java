package com.pushwoosh.notification;

import com.google.android.gms.common.internal.ImagesContract;
import com.pushwoosh.internal.utils.PWLog;
import org.json.JSONException;
import org.json.JSONObject;

public class Action {
    private a a;
    private String b;
    private String c;
    private String d;
    private String e;
    private Class f;
    private JSONObject g;

    public enum a {
        ACTIVITY,
        SERVICE,
        BROADCAST
    }

    public Action(JSONObject jSONObject) throws JSONException {
        try {
            this.a = a.valueOf(jSONObject.getString("type"));
            this.c = jSONObject.getString("title");
            this.d = jSONObject.optString("icon");
            this.b = jSONObject.optString("action");
            this.e = jSONObject.optString(ImagesContract.URL);
            String optString = jSONObject.optString("class");
            if (optString != null) {
                try {
                    this.f = Class.forName(optString);
                } catch (ClassNotFoundException e2) {
                    PWLog.exception(e2);
                }
            }
            try {
                this.g = jSONObject.getJSONObject("extras");
            } catch (JSONException unused) {
            }
        } catch (Exception e3) {
            throw new JSONException(e3.getMessage());
        }
    }

    public Class getActionClass() {
        return this.f;
    }

    public JSONObject getExtras() {
        return this.g;
    }

    public String getIcon() {
        return this.d;
    }

    public String getIntentAction() {
        return this.b;
    }

    public String getTitle() {
        return this.c;
    }

    public a getType() {
        return this.a;
    }

    public String getUrl() {
        return this.e;
    }
}
