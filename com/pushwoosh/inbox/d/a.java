package com.pushwoosh.inbox.d;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.pushwoosh.inbox.data.InboxMessageType;
import com.pushwoosh.internal.utils.JsonUtils;
import com.pushwoosh.internal.utils.PWLog;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public static InboxMessageType a(String str) {
        if (TextUtils.isEmpty(str)) {
            return InboxMessageType.PLAIN;
        }
        try {
            return a(new JSONObject(str));
        } catch (JSONException unused) {
            PWLog.noise("Failed to parse inbox type from actionPayload");
            return InboxMessageType.PLAIN;
        }
    }

    private static InboxMessageType a(JSONObject jSONObject) throws JSONException {
        if (c(jSONObject) != null) {
            return InboxMessageType.RICH_MEDIA;
        }
        String d = d(jSONObject);
        return d != null ? d.startsWith("http") ? InboxMessageType.URL : InboxMessageType.DEEP_LINK : b(jSONObject) != null ? InboxMessageType.REMOTE_URL : InboxMessageType.PLAIN;
    }

    public static String a(Bundle bundle) {
        return bundle.getString("p", null);
    }

    @Nullable
    public static String b(Bundle bundle) {
        return bundle.getString("pw_inbox", null);
    }

    @Nullable
    public static String b(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("r")) {
            return null;
        }
        return jSONObject.getString("r");
    }

    @Nullable
    public static String c(Bundle bundle) {
        return bundle.getString("inbox_params");
    }

    @Nullable
    public static JSONObject c(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("rm")) {
            return null;
        }
        return new JSONObject(jSONObject.getString("rm"));
    }

    public static InboxMessageType d(Bundle bundle) {
        try {
            return a(JsonUtils.bundleToJsonWithUserData(bundle));
        } catch (JSONException unused) {
            PWLog.noise("Failed to parse inbox type form bundle");
            return InboxMessageType.PLAIN;
        }
    }

    @Nullable
    public static String d(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("l")) {
            return null;
        }
        return jSONObject.getString("l");
    }

    @Nullable
    public static String e(Bundle bundle) {
        return bundle.getString("title");
    }

    public static long f(Bundle bundle) {
        return bundle.getLong("google.sent_time", System.currentTimeMillis());
    }

    @Nullable
    public static String g(Bundle bundle) {
        return bundle.getString("header", null);
    }
}
