package com.pushwoosh.internal.preference;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;

public class PreferenceBooleanValue implements PreferenceValue {
    @Nullable
    private final SharedPreferences a;
    private final String b;
    private boolean c;

    public PreferenceBooleanValue(@Nullable SharedPreferences sharedPreferences, String str, boolean z) {
        boolean z2;
        this.b = str;
        if (sharedPreferences == null) {
            z2 = z;
        } else {
            try {
                z2 = sharedPreferences.getBoolean(str, z);
            } catch (Exception e) {
                PWLog.exception(e);
                this.c = z;
            }
        }
        this.c = z2;
        this.a = sharedPreferences;
    }

    public boolean get() {
        return this.c;
    }

    public void set(boolean z) {
        this.c = z;
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            PWLog.error("Incorrect state of the app preferences is null");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(this.b, z);
        edit.apply();
    }
}
