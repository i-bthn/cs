package com.pushwoosh.internal.platform.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.annotation.Nullable;

/* access modifiers changed from: package-private */
public class b implements PrefsProvider {
    @Nullable
    private final Context a;

    b(@Nullable Context context) {
        this.a = context;
    }

    @Override // com.pushwoosh.internal.platform.prefs.PrefsProvider
    public SharedPreferences provideDefault() {
        Context context = this.a;
        if (context == null) {
            return null;
        }
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override // com.pushwoosh.internal.platform.prefs.PrefsProvider
    public SharedPreferences providePrefs(String str) {
        Context context = this.a;
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences(str, 0);
    }
}
