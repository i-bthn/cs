package com.pushwoosh.badge;

import com.pushwoosh.badge.c.a;
import com.pushwoosh.internal.Plugin;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.platform.prefs.migration.MigrationScheme;
import com.pushwoosh.notification.handlers.message.user.MessageHandleChainProvider;
import java.util.Collection;
import java.util.Collections;

public class BadgePlugin implements Plugin {
    @Override // com.pushwoosh.internal.Plugin
    public Collection<? extends MigrationScheme> getPrefsMigrationSchemes(PrefsProvider prefsProvider) {
        return Collections.singleton(a.a(prefsProvider));
    }

    @Override // com.pushwoosh.internal.Plugin
    public void init() {
        MessageHandleChainProvider.getHandleProcessor().addItem(new com.pushwoosh.badge.b.a());
        a.c();
    }
}
