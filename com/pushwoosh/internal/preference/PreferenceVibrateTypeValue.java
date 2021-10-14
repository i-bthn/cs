package com.pushwoosh.internal.preference;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.VibrateType;

public class PreferenceVibrateTypeValue implements PreferenceValue {
    @Nullable
    private final SharedPreferences a;
    private final String b;
    private int c;

    public PreferenceVibrateTypeValue(@Nullable SharedPreferences sharedPreferences, String str, VibrateType vibrateType) {
        int i;
        this.b = str;
        if (sharedPreferences == null) {
            try {
                i = vibrateType.getValue();
            } catch (Exception e) {
                PWLog.exception(e);
                this.c = vibrateType.getValue();
            }
        } else {
            i = sharedPreferences.getInt(str, vibrateType.getValue());
        }
        this.c = i;
        this.a = sharedPreferences;
    }

    public VibrateType get() {
        return VibrateType.fromInt(this.c);
    }

    public void set(VibrateType vibrateType) {
        this.c = vibrateType.getValue();
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            PWLog.error("Incorrect state of the app preferences is null");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(this.b, vibrateType.getValue());
        edit.apply();
    }
}
