package com.pushwoosh.internal.preference;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;

public class PreferenceIntValue implements PreferenceValue {
    @Nullable
    private final SharedPreferences a;
    private final String b;
    private int c;

    public PreferenceIntValue(@Nullable SharedPreferences sharedPreferences, String str, int i) {
        int i2;
        this.b = str;
        if (sharedPreferences == null) {
            i2 = i;
        } else {
            try {
                i2 = sharedPreferences.getInt(str, i);
            } catch (Exception e) {
                PWLog.exception(e);
                this.c = i;
            }
        }
        this.c = i2;
        this.a = sharedPreferences;
    }

    public int get() {
        return this.c;
    }

    public void set(int i) {
        this.c = i;
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            PWLog.error("Incorrect state of the app preferences is null");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(this.b, i);
        edit.apply();
    }
}
