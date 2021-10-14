package com.pushwoosh.notification.handlers.notification;

import android.os.Bundle;
import android.text.TextUtils;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.inapp.view.i.h.b;
import com.pushwoosh.notification.b;
import com.pushwoosh.richmedia.a;

/* access modifiers changed from: package-private */
public class e implements PushNotificationOpenHandler {
    e() {
    }

    @Override // com.pushwoosh.notification.handlers.notification.PushNotificationOpenHandler
    public void postHandleNotification(Bundle bundle) {
        String v = b.v(bundle);
        if (!TextUtils.isEmpty(v)) {
            com.pushwoosh.inapp.view.i.h.b a = new b.C0014b().a(v).a();
            a h = PushwooshPlatform.getInstance().h();
            if (h != null) {
                h.a(a);
            }
        }
    }
}
