package com.pushwoosh.notification;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.utils.a;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.c;
import com.pushwoosh.notification.handlers.message.system.MessageSystemHandleChainProvider;
import com.pushwoosh.notification.handlers.message.user.MessageHandleChainProvider;
import com.pushwoosh.notification.handlers.notification.NotificationOpenHandlerChainProvider;
import com.pushwoosh.notification.handlers.notification.d;
import java.util.List;

public class NotificationServiceExtension {
    private a a = new a(NotificationOpenHandlerChainProvider.getNotificationOpenHandlerChain());
    private d b = new d(MessageSystemHandleChainProvider.getMessageSystemChain(), MessageHandleChainProvider.getHandleProcessor());
    private c c = PushwooshPlatform.getInstance().e();
    private PushwooshNotificationManager d = PushwooshPlatform.getInstance().notificationManager();
    private c e = PushwooshPlatform.getInstance().c();
    @Nullable
    private Context f = AndroidPlatformModule.getApplicationContext();
    private d g = PushwooshPlatform.getInstance().m();

    /* access modifiers changed from: package-private */
    public void a(Bundle bundle) {
        if (bundle == null) {
            PWLog.info("handle null message");
            return;
        }
        PWLog.info("NotificationService", "handleMessage: " + bundle.toString());
        if (!this.b.a(bundle)) {
            PushMessage a2 = this.c.a(bundle);
            boolean onMessageReceived = onMessageReceived(a2);
            if (onMessageReceived && this.e.h()) {
                this.g.postHandleNotification(bundle);
            }
            this.b.a(a2, onMessageReceived);
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final Context getApplicationContext() {
        return this.f;
    }

    @WorkerThread
    public final void handleMessage(Bundle bundle) {
        a(bundle);
    }

    public final void handleNotification(Bundle bundle) {
        if (bundle == null) {
            PWLog.info("open null notification");
            return;
        }
        PushMessage pushMessage = new PushMessage(bundle);
        try {
            if (!preHandleNotificationsWithUrl() || !this.a.b(bundle)) {
                this.d.a(pushMessage);
                startActivityForPushMessage(pushMessage);
                this.a.a(bundle);
                onMessageOpened(pushMessage);
            }
        } finally {
            this.a.a(bundle);
            onMessageOpened(pushMessage);
        }
    }

    public final void handleNotificationGroup(List<PushMessage> list) {
        onMessagesGroupOpened(list);
    }

    /* access modifiers changed from: protected */
    public boolean isAppOnForeground() {
        return a.r();
    }

    /* access modifiers changed from: protected */
    public void onMessageOpened(PushMessage pushMessage) {
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public boolean onMessageReceived(PushMessage pushMessage) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onMessagesGroupOpened(List<PushMessage> list) {
        handleNotification(list.get(list.size() - 1).toBundle());
    }

    /* access modifiers changed from: protected */
    public boolean preHandleNotificationsWithUrl() {
        return true;
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void startActivityForPushMessage(PushMessage pushMessage) {
        this.a.a(pushMessage);
    }
}
