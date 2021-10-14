package com.pushwoosh.internal.preference;

import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;

public class PreferenceClassValue {
    @Nullable
    private final SharedPreferences a;
    private final String b;
    private Class<?> c;

    public PreferenceClassValue(@Nullable SharedPreferences sharedPreferences, String str, Class<?> cls) {
        this.b = str;
        this.a = sharedPreferences;
        String str2 = null;
        if (sharedPreferences != null) {
            try {
                str2 = sharedPreferences.getString(str, null);
            } catch (Exception e) {
                PWLog.exception(e);
                this.c = cls;
                return;
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            this.c = Class.forName(str2);
        } else {
            this.c = cls;
        }
    }

    public Class<?> get() {
        return this.c;
    }

    public void set(Class<?> cls) {
        this.c = cls;
        if (this.a == null) {
            PWLog.error("Incorrect state of the app preferences is null");
            return;
        }
        String name = cls != null ? cls.getName() : null;
        SharedPreferences.Editor edit = this.a.edit();
        edit.putString(this.b, name);
        edit.apply();
    }
}
