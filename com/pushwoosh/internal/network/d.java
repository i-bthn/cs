package com.pushwoosh.internal.network;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.pushwoosh.repository.RepositoryModule;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class d extends PushRequest<Void> implements LoggableRequest {
    private String a;
    private String b;

    public d(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        jSONObject.put(AppMeasurementSdk.ConditionalUserProperty.NAME, this.a);
        jSONObject.put("description", this.b);
        jSONObject.put("push_token", RepositoryModule.getRegistrationPreferences().pushToken().get());
        jSONObject.put("language", Locale.getDefault().getLanguage());
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "createTestDevice";
    }
}
