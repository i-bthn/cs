package com.pushwoosh.inbox;

import com.pushwoosh.inbox.b.b;
import com.pushwoosh.inbox.d.b.a;
import com.pushwoosh.internal.Plugin;
import com.pushwoosh.internal.event.AppIdChangedEvent;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.platform.prefs.migration.MigrationScheme;
import com.pushwoosh.notification.handlers.message.system.MessageSystemHandleChainProvider;
import com.pushwoosh.notification.handlers.notification.NotificationOpenHandlerChainProvider;
import java.util.Collection;
import java.util.Collections;

public class PushwooshInboxPlugin implements Plugin {
    /* access modifiers changed from: private */
    public static /* synthetic */ void a(AppIdChangedEvent appIdChangedEvent) {
        b.a().a();
    }

    @Override // com.pushwoosh.internal.Plugin
    public Collection<? extends MigrationScheme> getPrefsMigrationSchemes(PrefsProvider prefsProvider) {
        return Collections.emptyList();
    }

    @Override // com.pushwoosh.internal.Plugin
    public void init() {
        b.a(new com.pushwoosh.inbox.f.c.b(AndroidPlatformModule.getApplicationContext()), NetworkModule.getRequestManager(), AndroidPlatformModule.getPrefsProvider());
        MessageSystemHandleChainProvider.getMessageSystemChain().addItem(new a());
        NotificationOpenHandlerChainProvider.getNotificationOpenHandlerChain().addItem(new com.pushwoosh.inbox.d.b.b());
        EventBus.subscribe(AppIdChangedEvent.class, $$Lambda$PushwooshInboxPlugin$qbqKLhN2d0EpFjsAZmiNWPiOeXQ.INSTANCE);
    }
}
