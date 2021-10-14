package com.pushwoosh.internal.preference;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;

public class PreferenceLongValue implements PreferenceValue {
    @Nullable
    private final SharedPreferences a;
    private final String b;
    private long c;

    public PreferenceLongValue(@Nullable SharedPreferences sharedPreferences, String str, long j) {
        long j2;
        this.b = str;
        this.a = sharedPreferences;
        if (sharedPreferences == null) {
            j2 = j;
        } else {
            try {
                j2 = sharedPreferences.getLong(str, j);
            } catch (Exception e) {
                PWLog.exception(e);
                this.c = j;
                return;
            }
        }
        this.c = j2;
    }

    public long get() {
        return this.c;
    }

    public void set(long j) {
        this.c = j;
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            PWLog.error("Incorrect state of the app preferences is null");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(this.b, j);
        edit.apply();
    }
}
