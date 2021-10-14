package com.pushwoosh.repository.config;

import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.preference.PreferenceIntValue;

public final class b {
    private final PreferenceIntValue a = new PreferenceIntValue(AndroidPlatformModule.getPrefsProvider().providePrefs("com.pushwoosh.config"), "pw_is_logger_on", 1);

    public PreferenceIntValue a() {
        return this.a;
    }
}
