package com.pushwoosh.plugin;

import androidx.annotation.NonNull;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.utils.PWLog;
import java.util.ArrayList;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PushwooshSecure extends CordovaPlugin {
    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            if (str.equals("setBaseURL")) {
                if (jSONArray.length() == 0) {
                    PWLog.error("PushwooshSecure", "setBaseURL: not enough params");
                    return true;
                }
                JSONObject jSONObject = jSONArray.getJSONObject(0);
                String string = jSONObject.getString("baseURL");
                JSONArray optJSONArray = jSONObject.optJSONArray("publicKeyPins");
                String optString = jSONObject.optString("overrideHost", null);
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    Object obj = optJSONArray.get(i);
                    if (obj instanceof String) {
                        arrayList.add((String) obj);
                    } else {
                        PWLog.error("PushwooshSecure", "pin should be base64 string");
                    }
                }
                com.pushwoosh.secure.PushwooshSecure.setBaseURL(string, arrayList, optString);
                return true;
            } else if (str.equals("setupDecryption")) {
                com.pushwoosh.secure.PushwooshSecure.setupDecryption(wrapCallback(callbackContext, "Error while setuping secure pushes"));
                return true;
            } else {
                if (str.equals("teardownDecryption")) {
                    com.pushwoosh.secure.PushwooshSecure.teardownDecryption(wrapCallback(callbackContext, "Error while tearing down secure pushes"));
                    return true;
                }
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Callback<Void, PushwooshException> wrapCallback(final CallbackContext callbackContext, final String str) {
        return new Callback<Void, PushwooshException>() {
            /* class com.pushwoosh.plugin.PushwooshSecure.AnonymousClass1 */

            @Override // com.pushwoosh.function.Callback
            public void process(@NonNull Result<Void, PushwooshException> result) {
                if (result.isSuccess()) {
                    callbackContext.success();
                    return;
                }
                PWLog.error("PushwooshSecurePlugin", str, result.getException());
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("error", result.getException());
                    jSONObject.put("errorDescription", result.getException().getLocalizedMessage());
                } catch (JSONException unused) {
                }
                callbackContext.error(jSONObject);
            }
        };
    }
}
