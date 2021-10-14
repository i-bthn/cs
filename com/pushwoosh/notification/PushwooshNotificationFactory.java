package com.pushwoosh.notification;

import android.app.Notification;
import android.graphics.Bitmap;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.e;
import com.pushwoosh.notification.builder.NotificationBuilderManager;
import com.pushwoosh.notification.builder.b;

public class PushwooshNotificationFactory extends NotificationFactory {
    /* access modifiers changed from: protected */
    public Bitmap getBigPicture(PushMessage pushMessage) {
        return e.c(pushMessage.getBigPictureUrl(), -1);
    }

    /* access modifiers changed from: protected */
    public Bitmap getLargeIcon(PushMessage pushMessage) {
        int a = (int) AndroidPlatformModule.getResourceProvider().a(17104902);
        String largeIconUrl = pushMessage.getLargeIconUrl();
        if (largeIconUrl != null) {
            return e.a(largeIconUrl, a);
        }
        return null;
    }

    @Override // com.pushwoosh.notification.NotificationFactory
    @Nullable
    @WorkerThread
    public Notification onGenerateNotification(@NonNull PushMessage pushMessage) {
        Bitmap largeIcon = getLargeIcon(pushMessage);
        Bitmap bigPicture = getBigPicture(pushMessage);
        String addChannel = addChannel(pushMessage);
        if (getApplicationContext() == null) {
            return null;
        }
        b createNotificationBuilder = NotificationBuilderManager.createNotificationBuilder(getApplicationContext(), addChannel);
        createNotificationBuilder.b(getContentFromHtml(pushMessage.getHeader())).a(getContentFromHtml(pushMessage.getMessage())).a(pushMessage.getSmallIcon()).a(bigPicture, getContentFromHtml(pushMessage.getMessage())).a(largeIcon).a(pushMessage.getIconBackgroundColor()).b(pushMessage.getPriority()).c(pushMessage.getVisibility()).c(getContentFromHtml(pushMessage.getTicker())).a(System.currentTimeMillis());
        for (Action action : pushMessage.getActions()) {
            NotificationBuilderManager.addAction(getApplicationContext(), createNotificationBuilder, action);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            createNotificationBuilder.a(pushMessage.toBundle());
        }
        Notification a = createNotificationBuilder.a();
        addLED(a, pushMessage.getLed(), pushMessage.getLedOnMS(), pushMessage.getLedOffMS());
        addSound(a, pushMessage.getSound());
        addVibration(a, pushMessage.getVibration());
        addCancel(a);
        return a;
    }
}
