package com.pushwoosh.internal.preference;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.SoundType;

public class PreferenceSoundTypeValue implements PreferenceValue {
    @Nullable
    private final SharedPreferences a;
    private final String b;
    private int c;

    public PreferenceSoundTypeValue(@Nullable SharedPreferences sharedPreferences, String str, SoundType soundType) {
        int i;
        this.b = str;
        if (sharedPreferences == null) {
            try {
                i = soundType.getValue();
            } catch (Exception e) {
                PWLog.exception(e);
                this.c = soundType.getValue();
            }
        } else {
            i = sharedPreferences.getInt(str, soundType.getValue());
        }
        this.c = i;
        this.a = sharedPreferences;
    }

    public SoundType get() {
        return SoundType.fromInt(this.c);
    }

    public void set(SoundType soundType) {
        this.c = soundType.getValue();
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            PWLog.error("Incorrect state of the app preferences is null");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(this.b, soundType.getValue());
        edit.apply();
    }
}
