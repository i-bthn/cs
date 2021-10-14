package com.pushwoosh.repository.config;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import org.json.JSONObject;

public class Channel {
    private String code;
    private String name;
    private int position;

    public Channel(JSONObject jSONObject) {
        this.code = jSONObject.optString("code");
        this.name = jSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
        this.position = jSONObject.optInt("position", Integer.MAX_VALUE);
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public int getPosition() {
        return this.position;
    }

    public JSONObject jsonValue() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", this.code);
            jSONObject.put(AppMeasurementSdk.ConditionalUserProperty.NAME, this.name);
            jSONObject.put("position", this.position);
        } catch (Exception unused) {
        }
        return jSONObject;
    }
}
