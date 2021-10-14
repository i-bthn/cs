package com.pushwoosh.internal.platform.prefs;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;

public interface PrefsProvider {
    @Nullable
    SharedPreferences provideDefault();

    @Nullable
    SharedPreferences providePrefs(String str);
}
