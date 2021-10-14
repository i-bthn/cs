package com.pushwoosh.internal.richmedia;

import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.inapp.view.i.h.b;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.richmedia.a;

public final class ResourceAction {
    private ResourceAction() {
    }

    public static void performRemoteUrlAction(String str) {
        b a = new b.C0014b().a(str).a();
        a h = PushwooshPlatform.getInstance().h();
        if (h != null) {
            h.a(a);
        }
    }

    public static void performRichMediaAction(String str) {
        b a = new b.C0014b().b(str).a((long) RepositoryModule.getNotificationPreferences().m().get()).a();
        a h = PushwooshPlatform.getInstance().h();
        if (h != null) {
            h.a(a);
        }
    }
}
