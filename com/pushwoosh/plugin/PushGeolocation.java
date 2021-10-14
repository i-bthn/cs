package com.pushwoosh.plugin;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.plugin.geolocation.Callback;
import com.pushwoosh.plugin.geolocation.LocationManager;
import com.pushwoosh.plugin.geolocation.PushGeolocationPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PushGeolocation extends CordovaPlugin {
    private LocationManager locationManager = new LocationManager();

    @Override // org.apache.cordova.CordovaPlugin
    @SuppressLint({"StaticFieldLeak"})
    public boolean execute(String str, JSONArray jSONArray, final CallbackContext callbackContext) {
        if (str.equals("requestPermissions")) {
            this.locationManager.requestPermissions(this.cordova.getContext().getApplicationContext(), new Callback<Boolean>() {
                /* class com.pushwoosh.plugin.PushGeolocation.AnonymousClass1 */

                public void call(Boolean bool, Exception exc) {
                    if (exc != null) {
                        PWLog.error("cannot receive permissions", exc);
                    }
                }
            });
            return true;
        } else if (str.equals("startTrackingPushGeolocation")) {
            this.locationManager.requestPermissions(this.cordova.getContext().getApplicationContext(), new Callback<Boolean>() {
                /* class com.pushwoosh.plugin.PushGeolocation.AnonymousClass2 */

                public void call(Boolean bool, Exception exc) {
                    if (exc == null) {
                        PushGeolocationPlugin.sharedInstance().getStorage().setLocationTrackingEnbaled(true);
                        PushGeolocationPlugin.sharedInstance().setupHandler();
                        callbackContext.success();
                        return;
                    }
                    callbackContext.error(PushGeolocation.this.errorToJSON(exc, "Permission request failed"));
                }
            });
            return true;
        } else if (str.equals("stopTrackingPushGeolocation")) {
            PushGeolocationPlugin.sharedInstance().getStorage().setLocationTrackingEnbaled(false);
            PushGeolocationPlugin.sharedInstance().getStorage().clear();
            PushGeolocationPlugin.sharedInstance().removeHandler();
            return true;
        } else if (!str.equals("popReceivedPushes")) {
            return false;
        } else {
            new AsyncTask<Void, Void, Void>() {
                /* class com.pushwoosh.plugin.PushGeolocation.AnonymousClass3 */

                /* access modifiers changed from: protected */
                public Void doInBackground(Void... voidArr) {
                    callbackContext.success(PushGeolocationPlugin.sharedInstance().getStorage().pop());
                    return null;
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return true;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private JSONObject errorToJSON(Exception exc, String str) {
        PWLog.error("PushwooshSecurePlugin", str, exc);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("error", exc);
            jSONObject.put("errorDescription", exc.getLocalizedMessage());
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
