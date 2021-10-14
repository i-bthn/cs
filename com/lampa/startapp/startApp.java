package com.lampa.startapp;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.Iterator;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class startApp extends CordovaPlugin {
    public static final String TAG = "startApp";

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        if (str.equals("start")) {
            start(jSONArray, callbackContext);
            return true;
        } else if (str.equals("check")) {
            check(jSONArray, callbackContext);
            return true;
        } else if (str.equals("getExtras")) {
            getExtras(callbackContext);
            return true;
        } else if (!str.equals("getExtra")) {
            return true;
        } else {
            getExtra(jSONArray, callbackContext);
            return true;
        }
    }

    public void start(JSONArray jSONArray, CallbackContext callbackContext) {
        Intent intent;
        try {
            if (jSONArray.get(0) instanceof JSONObject) {
                JSONObject jSONObject = jSONArray.getJSONObject(0);
                if (jSONObject.has("package")) {
                    intent = this.cordova.getActivity().getPackageManager().getLaunchIntentForPackage(jSONObject.getString("package"));
                } else {
                    intent = new Intent();
                }
                if (jSONObject.has("action")) {
                    intent.setAction(getIntentValueString(jSONObject.getString("action")));
                }
                if (jSONObject.has("category")) {
                    intent.addCategory(getIntentValueString(jSONObject.getString("category")));
                }
                if (jSONObject.has("type")) {
                    intent.setType(jSONObject.getString("type"));
                }
                if (jSONObject.has("uri")) {
                    intent.setData(Uri.parse(jSONObject.getString("uri")));
                }
                if (jSONObject.has("flags")) {
                    JSONArray jSONArray2 = jSONObject.getJSONArray("flags");
                    for (int i = 0; i < jSONArray2.length(); i++) {
                        intent.addFlags(getIntentValue(jSONArray2.getString(i)));
                    }
                }
                if (jSONObject.has("component")) {
                    JSONArray jSONArray3 = jSONObject.getJSONArray("component");
                    if (jSONArray3.length() == 2) {
                        intent.setComponent(new ComponentName(jSONArray3.getString(0), jSONArray3.getString(1)));
                    }
                }
                if (!jSONArray.isNull(1)) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(1);
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        intent.putExtra(parseExtraName(next), jSONObject2.getString(next));
                    }
                }
                if (jSONObject.has("intentstart") && "startActivityForResult".equals(jSONObject.getString("intentstart"))) {
                    this.cordova.getActivity().startActivityForResult(intent, 1);
                }
                if (!jSONObject.has("intentstart") || !"sendBroadcast".equals(jSONObject.getString("intentstart"))) {
                    this.cordova.getActivity().startActivity(intent);
                } else {
                    this.cordova.getActivity().sendBroadcast(intent);
                }
                callbackContext.success();
                return;
            }
            callbackContext.error("Incorrect params, array is not array object!");
        } catch (JSONException e) {
            callbackContext.error("JSONException: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            callbackContext.error("IllegalAccessException: " + e2.getMessage());
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            callbackContext.error("NoSuchFieldException: " + e3.getMessage());
            e3.printStackTrace();
        } catch (ActivityNotFoundException e4) {
            callbackContext.error("ActivityNotFoundException: " + e4.getMessage());
            e4.printStackTrace();
        }
    }

    public void check(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            if (jSONArray.get(0) instanceof JSONObject) {
                JSONObject jSONObject = jSONArray.getJSONObject(0);
                if (jSONObject.has("package")) {
                    PackageInfo packageInfo = this.cordova.getActivity().getApplicationContext().getPackageManager().getPackageInfo(jSONObject.getString("package"), 1);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("versionName", packageInfo.versionName);
                    jSONObject2.put("packageName", packageInfo.packageName);
                    jSONObject2.put("versionCode", packageInfo.versionCode);
                    jSONObject2.put("applicationInfo", packageInfo.applicationInfo);
                    callbackContext.success(jSONObject2);
                    return;
                }
                callbackContext.error("Value \"package\" in null!");
                return;
            }
            callbackContext.error("Incorrect params, array is not array object!");
        } catch (JSONException e) {
            callbackContext.error("json: " + e.toString());
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e2) {
            callbackContext.error("NameNotFoundException: " + e2.toString());
            e2.printStackTrace();
        }
    }

    public void getExtras(CallbackContext callbackContext) {
        try {
            Bundle extras = this.cordova.getActivity().getIntent().getExtras();
            JSONObject jSONObject = new JSONObject();
            if (extras != null) {
                for (String str : extras.keySet()) {
                    jSONObject.put(str, extras.get(str).toString());
                }
            }
            callbackContext.success(jSONObject);
        } catch (JSONException e) {
            callbackContext.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void getExtra(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            String parseExtraName = parseExtraName(jSONArray.getString(0));
            Intent intent = this.cordova.getActivity().getIntent();
            if (intent.hasExtra(parseExtraName)) {
                String stringExtra = intent.getStringExtra(parseExtraName);
                if (stringExtra == null) {
                    stringExtra = ((Uri) intent.getParcelableExtra(parseExtraName)).toString();
                }
                callbackContext.success(stringExtra);
                return;
            }
            callbackContext.error("extra field not found");
        } catch (JSONException e) {
            callbackContext.error(e.getMessage());
            e.printStackTrace();
        }
    }

    static String parseExtraName(String str) {
        try {
            String intentValueString = getIntentValueString(str);
            Log.i(TAG, intentValueString);
            str = intentValueString;
        } catch (NoSuchFieldException unused) {
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return str;
        }
        Log.e(TAG, str);
        return str;
    }

    static String getIntentValueString(String str) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = Intent.class.getDeclaredField(str);
        declaredField.setAccessible(true);
        return (String) declaredField.get(null);
    }

    static int getIntentValue(String str) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = Intent.class.getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.getInt(null);
    }
}
