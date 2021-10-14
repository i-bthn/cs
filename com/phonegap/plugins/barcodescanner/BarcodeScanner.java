package com.phonegap.plugins.barcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.encode.EncodeActivity;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BarcodeScanner extends CordovaPlugin {
    private static final String CANCELLED = "cancelled";
    private static final String DATA = "data";
    private static final String DISABLE_BEEP = "disableSuccessBeep";
    private static final String EMAIL_TYPE = "EMAIL_TYPE";
    private static final String ENCODE = "encode";
    private static final String FORMAT = "format";
    private static final String FORMATS = "formats";
    private static final String LOG_TAG = "BarcodeScanner";
    private static final String ORIENTATION = "orientation";
    private static final String PHONE_TYPE = "PHONE_TYPE";
    private static final String PREFER_FRONTCAMERA = "preferFrontCamera";
    private static final String PROMPT = "prompt";
    public static final int REQUEST_CODE = 47740;
    private static final String RESULTDISPLAY_DURATION = "resultDisplayDuration";
    private static final String SAVE_HISTORY = "saveHistory";
    private static final String SCAN = "scan";
    private static final String SHOW_FLIP_CAMERA_BUTTON = "showFlipCameraButton";
    private static final String SHOW_TORCH_BUTTON = "showTorchButton";
    private static final String SMS_TYPE = "SMS_TYPE";
    private static final String TEXT = "text";
    private static final String TEXT_TYPE = "TEXT_TYPE";
    private static final String TORCH_ON = "torchOn";
    private static final String TYPE = "type";
    private CallbackContext callbackContext;
    private String[] permissions = {"android.permission.CAMERA"};
    private JSONArray requestArgs;

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext2) {
        this.callbackContext = callbackContext2;
        this.requestArgs = jSONArray;
        if (str.equals(ENCODE)) {
            JSONObject optJSONObject = jSONArray.optJSONObject(0);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("type");
                String optString2 = optJSONObject.optString(DATA);
                if (optString == null) {
                    optString = "TEXT_TYPE";
                }
                if (optString2 == null) {
                    callbackContext2.error("User did not specify data to encode");
                    return true;
                }
                encode(optString, optString2);
            } else {
                callbackContext2.error("User did not specify data to encode");
                return true;
            }
        } else if (!str.equals(SCAN)) {
            return false;
        } else {
            if (!hasPermisssion()) {
                requestPermissions(0);
            } else {
                scan(jSONArray);
            }
        }
        return true;
    }

    public void scan(final JSONArray jSONArray) {
        this.cordova.getThreadPool().execute(new Runnable() {
            /* class com.phonegap.plugins.barcodescanner.BarcodeScanner.AnonymousClass1 */

            public void run() {
                Intent intent = new Intent(this.cordova.getActivity().getBaseContext(), CaptureActivity.class);
                intent.setAction(Intents.Scan.ACTION);
                intent.addCategory("android.intent.category.DEFAULT");
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            JSONArray names = jSONObject.names();
                            for (int i2 = 0; i2 < names.length(); i2++) {
                                try {
                                    String string = names.getString(i2);
                                    Object obj = jSONObject.get(string);
                                    if (obj instanceof Integer) {
                                        intent.putExtra(string, (Integer) obj);
                                    } else if (obj instanceof String) {
                                        intent.putExtra(string, (String) obj);
                                    }
                                } catch (JSONException e) {
                                    Log.i("CordovaLog", e.getLocalizedMessage());
                                }
                            }
                            intent.putExtra(Intents.Scan.CAMERA_ID, jSONObject.optBoolean(BarcodeScanner.PREFER_FRONTCAMERA, false) ? 1 : 0);
                            intent.putExtra(Intents.Scan.SHOW_FLIP_CAMERA_BUTTON, jSONObject.optBoolean(BarcodeScanner.SHOW_FLIP_CAMERA_BUTTON, false));
                            intent.putExtra(Intents.Scan.SHOW_TORCH_BUTTON, jSONObject.optBoolean(BarcodeScanner.SHOW_TORCH_BUTTON, false));
                            intent.putExtra(Intents.Scan.TORCH_ON, jSONObject.optBoolean(BarcodeScanner.TORCH_ON, false));
                            intent.putExtra(Intents.Scan.SAVE_HISTORY, jSONObject.optBoolean(BarcodeScanner.SAVE_HISTORY, false));
                            intent.putExtra(Intents.Scan.BEEP_ON_SCAN, !jSONObject.optBoolean(BarcodeScanner.DISABLE_BEEP, false));
                            if (jSONObject.has(BarcodeScanner.RESULTDISPLAY_DURATION)) {
                                intent.putExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS, "" + jSONObject.optLong(BarcodeScanner.RESULTDISPLAY_DURATION));
                            }
                            if (jSONObject.has(BarcodeScanner.FORMATS)) {
                                intent.putExtra(Intents.Scan.FORMATS, jSONObject.optString(BarcodeScanner.FORMATS));
                            }
                            if (jSONObject.has(BarcodeScanner.PROMPT)) {
                                intent.putExtra(Intents.Scan.PROMPT_MESSAGE, jSONObject.optString(BarcodeScanner.PROMPT));
                            }
                            if (jSONObject.has(BarcodeScanner.ORIENTATION)) {
                                intent.putExtra(Intents.Scan.ORIENTATION_LOCK, jSONObject.optString(BarcodeScanner.ORIENTATION));
                            }
                        } catch (JSONException e2) {
                            Log.i("CordovaLog", e2.getLocalizedMessage());
                        }
                    }
                }
                intent.setPackage(this.cordova.getActivity().getApplicationContext().getPackageName());
                this.cordova.startActivityForResult(this, intent, BarcodeScanner.REQUEST_CODE);
            }
        });
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onActivityResult(int i, int i2, Intent intent) {
        CallbackContext callbackContext2;
        if (i == 47740 && (callbackContext2 = this.callbackContext) != null) {
            if (i2 == -1) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(TEXT, intent.getStringExtra(Intents.Scan.RESULT));
                    jSONObject.put(FORMAT, intent.getStringExtra(Intents.Scan.RESULT_FORMAT));
                    jSONObject.put(CANCELLED, false);
                } catch (JSONException unused) {
                    Log.d(LOG_TAG, "This should never happen");
                }
                this.callbackContext.success(jSONObject);
            } else if (i2 == 0) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put(TEXT, "");
                    jSONObject2.put(FORMAT, "");
                    jSONObject2.put(CANCELLED, true);
                } catch (JSONException unused2) {
                    Log.d(LOG_TAG, "This should never happen");
                }
                this.callbackContext.success(jSONObject2);
            } else {
                callbackContext2.error("Unexpected error");
            }
        }
    }

    public void encode(String str, String str2) {
        Intent intent = new Intent(this.cordova.getActivity().getBaseContext(), EncodeActivity.class);
        intent.setAction(Intents.Encode.ACTION);
        intent.putExtra(Intents.Encode.TYPE, str);
        intent.putExtra(Intents.Encode.DATA, str2);
        intent.setPackage(this.cordova.getActivity().getApplicationContext().getPackageName());
        this.cordova.getActivity().startActivity(intent);
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean hasPermisssion() {
        for (String str : this.permissions) {
            if (!PermissionHelper.hasPermission(this, str)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void requestPermissions(int i) {
        PermissionHelper.requestPermissions(this, i, this.permissions);
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onRequestPermissionResult(int i, String[] strArr, int[] iArr) throws JSONException {
        for (int i2 : iArr) {
            if (i2 == -1) {
                Log.d(LOG_TAG, "Permission Denied!");
                this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ILLEGAL_ACCESS_EXCEPTION));
                return;
            }
        }
        if (i == 0) {
            scan(this.requestArgs);
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onRestoreStateForActivityResult(Bundle bundle, CallbackContext callbackContext2) {
        this.callbackContext = callbackContext2;
    }
}
