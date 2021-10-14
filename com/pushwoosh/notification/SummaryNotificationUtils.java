package com.pushwoosh.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pushwoosh.NotificationOpenReceiver;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RepositoryModule;

public class SummaryNotificationUtils {
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    private static Intent a() {
        Intent intent = new Intent(AndroidPlatformModule.getApplicationContext(), NotificationOpenReceiver.class);
        intent.putExtra("is_summary_notification", true);
        intent.putExtra("is_delete_intent", true);
        intent.setAction(Long.toString(System.currentTimeMillis()));
        return intent;
    }

    private static SummaryNotificationFactory b() {
        try {
            Class<?> cls = RepositoryModule.getNotificationPreferences().o().get();
            if (cls != null) {
                return (SummaryNotificationFactory) cls.newInstance();
            }
        } catch (Exception e) {
            PWLog.exception(e);
        }
        return new PushwooshSummaryNotificationFactory();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static void fireSummaryNotification(@Nullable Notification notification) {
        if (AndroidPlatformModule.getApplicationContext() == null) {
            PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            return;
        }
        NotificationManager notificationManager = AndroidPlatformModule.getManagerProvider().getNotificationManager();
        if (notificationManager != null && notification != null) {
            notificationManager.notify(20191017, notification);
        }
    }

    @Nullable
    @RequiresApi(api = 24)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static Notification getSummaryNotification(int i) {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext == null) {
            PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            return null;
        }
        Notification onGenerateSummaryNotification = b().onGenerateSummaryNotification(i);
        if (onGenerateSummaryNotification == null) {
            return null;
        }
        Intent notificationIntent = SummaryNotificationFactory.getNotificationIntent();
        notificationIntent.putExtra(FirebaseAnalytics.Param.GROUP_ID, 20191017);
        notificationIntent.putExtra("is_summary_notification", true);
        onGenerateSummaryNotification.deleteIntent = PendingIntent.getBroadcast(applicationContext, 20191017, a(), 268435456);
        return onGenerateSummaryNotification;
    }
}
