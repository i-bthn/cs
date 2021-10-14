package com.pushwoosh.plugin.geolocation;

import android.location.Location;
import android.os.Bundle;
import com.pddstudio.preferences.encrypted.EncryptedPreferences;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationPushesStorage {
    private static final String ENABLED_KEY = "ENABLED_KEY";
    private static final String PREFERENCE_NAME = "PWBroadcastSecurityKey";
    private static final String PUSHES_KEY = "PUSHES_KEY";
    private EncryptedPreferences encryptedPreferences = new EncryptedPreferences.Builder(AndroidPlatformModule.getApplicationContext()).withPreferenceName(PREFERENCE_NAME).withEncryptionPassword(Pushwoosh.getInstance().getHwid()).build();

    LocationPushesStorage() {
    }

    public void put(Bundle bundle, Location location) {
        synchronized (this) {
            try {
                JSONArray jSONArray = new JSONArray(this.encryptedPreferences.getString(PUSHES_KEY, "[]"));
                while (jSONArray.length() > 20) {
                    jSONArray.remove(0);
                }
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                for (String str : bundle.keySet()) {
                    try {
                        jSONObject2.put(str, JSONObject.wrap(bundle.get(str)));
                    } catch (JSONException unused) {
                    }
                }
                jSONObject.put("payload", jSONObject2);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("latitude", location.getLatitude());
                jSONObject3.put("longitude", location.getLongitude());
                jSONObject3.put("accuracy", (double) location.getAccuracy());
                jSONObject.put("geolocation", jSONObject3);
                jSONArray.put(jSONObject);
                this.encryptedPreferences.edit().putString(PUSHES_KEY, jSONArray.toString()).commit();
            } catch (JSONException e) {
                e.printStackTrace();
                clear();
            }
        }
    }

    public void clear() {
        this.encryptedPreferences.edit().remove(PUSHES_KEY).commit();
    }

    /* JADX INFO: finally extract failed */
    public JSONArray pop() {
        JSONArray jSONArray;
        synchronized (this) {
            try {
                jSONArray = new JSONArray(this.encryptedPreferences.getString(PUSHES_KEY, "[]"));
                clear();
            } catch (JSONException e) {
                e.printStackTrace();
                clear();
                jSONArray = null;
            } catch (Throwable th) {
                clear();
                throw th;
            }
        }
        return jSONArray;
    }

    public void setLocationTrackingEnbaled(boolean z) {
        this.encryptedPreferences.edit().putBoolean(ENABLED_KEY, z).commit();
    }

    public boolean isLocationTrackingEnabled() {
        return this.encryptedPreferences.getBoolean(ENABLED_KEY, false);
    }
}
