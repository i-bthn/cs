package com.pushwoosh.notification.handlers.message.user;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pushwoosh.NotificationOpenReceiver;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.e;
import com.pushwoosh.notification.LocalNotificationReceiver;
import com.pushwoosh.notification.NotificationFactory;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.notification.PushwooshNotificationFactory;
import com.pushwoosh.notification.SummaryNotificationUtils;
import com.pushwoosh.notification.builder.NotificationBuilderManager;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.i;
import com.pushwoosh.repository.l;
import java.util.List;

/* access modifiers changed from: package-private */
public class c extends NotificationMessageHandler {
    private static final String d = "c";
    private static final Object e = new Object();
    private final NotificationFactory b = a();
    private final l c = RepositoryModule.getNotificationPreferences();

    c() {
    }

    private int a(String str) {
        int i;
        if (!TextUtils.isEmpty(str)) {
            return 0;
        }
        synchronized (e) {
            i = this.c.h().get();
            if (this.c.i().get()) {
                i++;
                this.c.h().set(i);
            }
        }
        return i;
    }

    @NonNull
    private Intent a(long j) {
        Intent intent = new Intent(AndroidPlatformModule.getApplicationContext(), NotificationOpenReceiver.class);
        intent.putExtra("row_id", j);
        intent.putExtra("is_delete_intent", true);
        intent.setAction(Long.toString(System.currentTimeMillis()));
        return intent;
    }

    private NotificationFactory a() {
        try {
            Class<?> cls = RepositoryModule.getNotificationPreferences().k().get();
            if (cls != null) {
                return (NotificationFactory) cls.newInstance();
            }
        } catch (Exception e2) {
            PWLog.exception(e2);
        }
        return new PushwooshNotificationFactory();
    }

    private void a(Notification notification, Intent intent, @Nullable Intent intent2, PushMessage pushMessage) {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext == null) {
            PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            return;
        }
        String tag = pushMessage.getTag();
        int a = a(tag);
        notification.contentIntent = PendingIntent.getBroadcast(applicationContext, a, intent, 268435456);
        if (intent2 != null) {
            notification.deleteIntent = PendingIntent.getBroadcast(applicationContext, a, intent2, 268435456);
        }
        a(intent, tag, a);
        NotificationManager notificationManager = AndroidPlatformModule.getManagerProvider().getNotificationManager();
        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT < 23) {
                a(pushMessage, a, tag);
            }
            notificationManager.notify(tag, a, notification);
            b();
            b(pushMessage);
            EventBus.sendEvent(new NotificationCreatedEvent(a, tag, pushMessage));
        }
    }

    private void a(Intent intent, String str, int i) {
        i localNotificationStorage = RepositoryModule.getLocalNotificationStorage();
        localNotificationStorage.a(i, str);
        if (intent.hasExtra(LocalNotificationReceiver.EXTRA_NOTIFICATION_ID)) {
            localNotificationStorage.a(intent.getIntExtra(LocalNotificationReceiver.EXTRA_NOTIFICATION_ID, 0), i, str);
        }
    }

    private void a(PushMessage pushMessage, int i, String str) {
        try {
            String string = pushMessage.toBundle().getString("pw_inbox");
            if (!TextUtils.isEmpty(string)) {
                RepositoryModule.getInboxNotificationStorage().a(string, i, str);
            }
        } catch (Exception e2) {
            PWLog.error(d, e2);
        }
    }

    private void b() {
        if (RepositoryModule.getNotificationPreferences().g().get()) {
            e.c();
        }
    }

    private void b(PushMessage pushMessage) {
        this.c.l().add(pushMessage.toJson().toString());
    }

    /* access modifiers changed from: protected */
    public void a(PushMessage pushMessage) {
        Notification onGenerateNotification = this.b.onGenerateNotification(pushMessage);
        if (onGenerateNotification != null) {
            if (Build.VERSION.SDK_INT >= 24) {
                onGenerateNotification = NotificationBuilderManager.setNotificationGroup(onGenerateNotification, "group_undefined");
                List<StatusBarNotification> activeNotifications = NotificationBuilderManager.getActiveNotifications();
                if (activeNotifications.size() >= 1) {
                    int size = NotificationBuilderManager.isReplacingMessage(pushMessage, activeNotifications) ? activeNotifications.size() : activeNotifications.size() + 1;
                    NotificationBuilderManager.setGroupToActiveNotifications(activeNotifications, "group_undefined");
                    Notification summaryNotification = SummaryNotificationUtils.getSummaryNotification(size);
                    if (summaryNotification != null) {
                        SummaryNotificationUtils.fireSummaryNotification(summaryNotification);
                    } else {
                        return;
                    }
                }
            }
            Intent notificationIntent = this.b.getNotificationIntent(pushMessage);
            notificationIntent.putExtra(FirebaseAnalytics.Param.GROUP_ID, 20191017);
            Intent intent = null;
            try {
                intent = a(RepositoryModule.getPushBundleStorage().a(pushMessage.toBundle()));
            } catch (Exception unused) {
            }
            a(onGenerateNotification, notificationIntent, intent, pushMessage);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.notification.handlers.message.user.NotificationMessageHandler
    public void handleNotification(PushMessage pushMessage) {
        if (pushMessage.isSilent()) {
            return;
        }
        if (this.c.i().get()) {
            a(pushMessage);
            return;
        }
        Notification onGenerateNotification = this.b.onGenerateNotification(pushMessage);
        if (onGenerateNotification != null) {
            a(onGenerateNotification, this.b.getNotificationIntent(pushMessage), null, pushMessage);
        }
    }
}
