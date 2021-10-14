package com.pushwoosh.internal.platform.prefs.migration;

import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import java.util.Collection;

public class a implements b {
    private final PrefsProvider a;

    public a(PrefsProvider prefsProvider) {
        this.a = prefsProvider;
    }

    @Override // com.pushwoosh.internal.platform.prefs.migration.b
    public void a(Collection<? extends MigrationScheme> collection) {
        for (MigrationScheme migrationScheme : collection) {
            migrationScheme.a(this.a);
        }
    }
}
