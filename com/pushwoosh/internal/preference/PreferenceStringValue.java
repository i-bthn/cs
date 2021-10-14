package com.pushwoosh.internal.preference;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;

public class PreferenceStringValue implements PreferenceValue {
    @Nullable
    private final SharedPreferences a;
    private final String b;
    private String c;

    public PreferenceStringValue(@Nullable SharedPreferences sharedPreferences, String str, String str2) {
        String str3;
        this.b = str;
        if (sharedPreferences == null) {
            str3 = str2;
        } else {
            try {
                str3 = sharedPreferences.getString(str, str2);
            } catch (Exception e) {
                PWLog.exception(e);
                this.c = str2;
            }
        }
        this.c = str3;
        this.a = sharedPreferences;
    }

    public String get() {
        return this.c;
    }

    public void set(String str) {
        this.c = str;
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            PWLog.error("Incorrect state of the app preferences is null");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.b, str);
        edit.apply();
    }
}
