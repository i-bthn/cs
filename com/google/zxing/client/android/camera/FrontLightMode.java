package com.google.zxing.client.android.camera;

import android.content.SharedPreferences;
import com.google.zxing.client.android.PreferencesActivity;

public enum FrontLightMode {
    ON,
    AUTO,
    OFF;

    private static FrontLightMode parse(String str) {
        return str == null ? OFF : valueOf(str);
    }

    public static FrontLightMode readPref(SharedPreferences sharedPreferences) {
        return parse(sharedPreferences.getString(PreferencesActivity.KEY_FRONT_LIGHT_MODE, OFF.toString()));
    }
}
