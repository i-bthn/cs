package com.pushwoosh.notification.handlers.notification;

import android.os.Bundle;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.inapp.view.i.h.b;
import com.pushwoosh.notification.b;

/* access modifiers changed from: package-private */
public class a implements PushNotificationOpenHandler {
    a() {
    }

    @Override // com.pushwoosh.notification.handlers.notification.PushNotificationOpenHandler
    public void postHandleNotification(Bundle bundle) {
        String h = b.h(bundle);
        if (h != null) {
            com.pushwoosh.inapp.view.i.h.b a = new b.C0014b().a(String.format("https://go.pushwoosh.com/content/%s", h)).a();
            com.pushwoosh.richmedia.a h2 = PushwooshPlatform.getInstance().h();
            if (h2 != null) {
                h2.a(a);
            }
        }
    }
}
