package com.pushwoosh.internal.network;

import android.telephony.TelephonyManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pushwoosh.internal.network.i.a;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import org.json.JSONException;
import org.json.JSONObject;

class e extends PushRequest<Void> implements a {
    static String i = "mobilesdk/android";
    private final long a;
    private final long b;
    private final int c;
    @Nullable
    private final String d;
    @Nullable
    private final PushRequest e;
    @NonNull
    private final String f;
    private final int g;
    @Nullable
    private final JSONObject h;

    e(long j, long j2, int i2, @Nullable String str, @Nullable PushRequest pushRequest, @NonNull String str2, int i3, @Nullable JSONObject jSONObject) {
        this.a = j;
        this.b = j2;
        this.c = i2;
        this.d = str;
        this.e = pushRequest;
        this.f = str2;
        this.g = i3;
        this.h = jSONObject;
    }

    private JSONObject a(int i2, @Nullable JSONObject jSONObject, @NonNull String str) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(NotificationCompat.CATEGORY_STATUS, i2);
        Object obj = jSONObject;
        if (jSONObject == null) {
            obj = "";
        }
        jSONObject2.put("headers", obj);
        jSONObject2.put("message", str);
        return jSONObject2;
    }

    private JSONObject a(long j, long j2, int i2, @Nullable String str, @Nullable PushRequest pushRequest) throws JSONException, InterruptedException {
        JSONObject jSONObject = new JSONObject();
        String method = pushRequest != null ? pushRequest.getMethod() : "";
        JSONObject a2 = pushRequest != null ? pushRequest.a() : new JSONObject();
        jSONObject.put(FirebaseAnalytics.Param.METHOD, method);
        if (str == null) {
            str = "";
        }
        jSONObject.put("entrypoint", str);
        jSONObject.put("payload", a2);
        jSONObject.put("startTime", j);
        jSONObject.put("endTime", j2);
        jSONObject.put("executionTime", ((int) (j2 - j)) / 1000);
        jSONObject.put("retryCount", i2);
        return jSONObject;
    }

    private JSONObject c() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("sdk", "6.2.8");
        try {
            TelephonyManager telephonyManager = AndroidPlatformModule.getManagerProvider().getTelephonyManager();
            if (telephonyManager != null) {
                jSONObject.put("connectionType", a.a(telephonyManager.getNetworkType()));
            }
        } catch (Throwable unused) {
            jSONObject.put("connectionType", "unknown");
        }
        return jSONObject;
    }

    /* access modifiers changed from: package-private */
    @Override // com.pushwoosh.internal.network.PushRequest
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "android");
            jSONObject.put("request", a(this.a, this.b, this.c, this.d, this.e));
            jSONObject.put("error", a(this.g, this.h, this.f));
            jSONObject.put("device", c());
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public String b() {
        return "https://post-log.pushwoosh.com/";
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return i;
    }
}
