package com.pushwoosh.notification.handlers.notification;

import androidx.annotation.NonNull;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.internal.chain.Chain;
import com.pushwoosh.notification.handlers.notification.c;

public final class NotificationOpenHandlerChainProvider {
    private static Chain<PushNotificationOpenHandler> a;

    private NotificationOpenHandlerChainProvider() {
    }

    @NonNull
    private static Chain<PushNotificationOpenHandler> a() {
        return new c.b().a(PushwooshPlatform.getInstance().m()).a(new a()).a(new e()).a(new f()).a(new b()).a();
    }

    @NonNull
    public static Chain<PushNotificationOpenHandler> getNotificationOpenHandlerChain() {
        return a;
    }

    public static void init() {
        a = a();
    }
}
