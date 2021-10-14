package com.pushwoosh.plugin.geolocation;

import android.location.Location;
import android.os.Bundle;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.notification.handlers.message.system.MessageSystemHandler;
import java.util.Date;

public class LocationMessageHandler implements MessageSystemHandler {
    private LocationPushesStorage storage;

    LocationMessageHandler(LocationPushesStorage locationPushesStorage) {
        this.storage = locationPushesStorage;
    }

    @Override // com.pushwoosh.notification.handlers.message.system.MessageSystemHandler
    public boolean preHandleMessage(final Bundle bundle) {
        String string = bundle.getString("trlc");
        if (string == null || !string.equals("1") || !this.storage.isLocationTrackingEnabled()) {
            return false;
        }
        new LocationManager().requestLocation(AndroidPlatformModule.getApplicationContext(), new Callback<Location>() {
            /* class com.pushwoosh.plugin.geolocation.LocationMessageHandler.AnonymousClass1 */

            public void call(Location location, Exception exc) {
                if (location != null && location.getTime() + 600000 >= new Date().getTime()) {
                    LocationMessageHandler.this.storage.put(bundle, location);
                }
            }
        });
        return false;
    }
}
