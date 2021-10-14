package com.pushwoosh.notification.handlers.notification;

import android.os.Bundle;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.inapp.view.i.h.b;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.l;
import com.pushwoosh.richmedia.a;

/* access modifiers changed from: package-private */
public class f implements PushNotificationOpenHandler {
    private final l a = RepositoryModule.getNotificationPreferences();

    f() {
    }

    @Override // com.pushwoosh.notification.handlers.notification.PushNotificationOpenHandler
    public void postHandleNotification(Bundle bundle) {
        b a2 = new b.C0014b().b(com.pushwoosh.notification.b.w(bundle)).a((long) this.a.m().get()).a();
        RepositoryModule.getNotificationPreferences().b().set(com.pushwoosh.notification.b.f(bundle));
        a h = PushwooshPlatform.getInstance().h();
        if (h != null) {
            h.a(a2);
        }
    }
}
