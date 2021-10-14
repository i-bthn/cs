package com.pushwoosh.plugin.geolocation;

import com.pushwoosh.internal.Plugin;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.platform.prefs.migration.MigrationScheme;
import com.pushwoosh.notification.handlers.message.system.MessageSystemHandleChainProvider;
import com.pushwoosh.notification.handlers.message.system.MessageSystemHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PushGeolocationPlugin implements Plugin {
    private static PushGeolocationPlugin sharedInstance;
    private LocationPushesStorage storage;

    @Override // com.pushwoosh.internal.Plugin
    public Collection<? extends MigrationScheme> getPrefsMigrationSchemes(PrefsProvider prefsProvider) {
        return null;
    }

    @Override // com.pushwoosh.internal.Plugin
    public void init() {
        sharedInstance = this;
        this.storage = new LocationPushesStorage();
        if (this.storage.isLocationTrackingEnabled()) {
            setupHandler();
        }
    }

    public void setupHandler() {
        removeHandler();
        MessageSystemHandleChainProvider.getMessageSystemChain().addItem(new LocationMessageHandler(this.storage));
    }

    public void removeHandler() {
        Iterator<MessageSystemHandler> iterator = MessageSystemHandleChainProvider.getMessageSystemChain().getIterator();
        ArrayList arrayList = new ArrayList();
        while (iterator.hasNext()) {
            MessageSystemHandler next = iterator.next();
            if (next instanceof LocationMessageHandler) {
                arrayList.add(next);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            MessageSystemHandleChainProvider.getMessageSystemChain().removeItem((MessageSystemHandler) it.next());
        }
    }

    public LocationPushesStorage getStorage() {
        return this.storage;
    }

    public static PushGeolocationPlugin sharedInstance() {
        return sharedInstance;
    }
}
