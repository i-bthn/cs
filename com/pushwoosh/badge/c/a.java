package com.pushwoosh.badge.c;

import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.platform.prefs.migration.MigrationScheme;
import com.pushwoosh.internal.preference.PreferenceIntValue;

public final class a {
    private final PreferenceIntValue a = new PreferenceIntValue(AndroidPlatformModule.getPrefsProvider().providePrefs("com.pushwoosh.badge"), "pw_badges_count", 0);

    public static MigrationScheme a(PrefsProvider prefsProvider) {
        MigrationScheme migrationScheme = new MigrationScheme("com.pushwoosh.badge");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.INT, "pw_badges_count");
        return migrationScheme;
    }

    public PreferenceIntValue a() {
        return this.a;
    }
}
