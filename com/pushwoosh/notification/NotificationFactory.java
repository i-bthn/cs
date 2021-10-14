package com.pushwoosh.notification;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.pushwoosh.NotificationOpenReceiver;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.e;
import com.pushwoosh.notification.g.a;
import com.pushwoosh.notification.g.b;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.l;

public abstract class NotificationFactory {
    @Nullable
    private final Context a = AndroidPlatformModule.getApplicationContext();
    private b b = new b(this.a);

    /* access modifiers changed from: protected */
    public final void addCancel(@NonNull Notification notification) {
        notification.flags |= 16;
    }

    /* access modifiers changed from: protected */
    public String addChannel(PushMessage pushMessage) {
        String b2 = a.b(pushMessage);
        return this.b.a(pushMessage, channelName(b2), channelDescription(b2));
    }

    /* access modifiers changed from: protected */
    public final void addLED(@NonNull Notification notification, @Nullable Integer num, int i, int i2) {
        l notificationPreferences = RepositoryModule.getNotificationPreferences();
        boolean z = notificationPreferences.f().get();
        int i3 = notificationPreferences.e().get();
        if (z || num != null) {
            b bVar = this.b;
            if (num != null) {
                i3 = num.intValue();
            }
            bVar.a(notification, i3, i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public final void addSound(@NonNull Notification notification, @Nullable String str) {
        Uri b2 = e.b(str);
        if (b2 != null) {
            this.b.a(notification, b2, str == null);
        }
    }

    /* access modifiers changed from: protected */
    public final void addVibration(@NonNull Notification notification, boolean z) {
        this.b.a(notification, RepositoryModule.getNotificationPreferences().r().get(), z);
    }

    @Nullable
    public String channelDescription(String str) {
        return "";
    }

    public String channelName(String str) {
        return str;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final Context getApplicationContext() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public final CharSequence getContentFromHtml(String str) {
        return TextUtils.isEmpty(str) ? str : Html.fromHtml(str);
    }

    @NonNull
    public Intent getNotificationIntent(@NonNull PushMessage pushMessage) {
        Intent intent = new Intent(this.a, NotificationOpenReceiver.class);
        intent.putExtra("pushBundle", pushMessage.toBundle());
        intent.setAction(Long.toString(System.currentTimeMillis()));
        return intent;
    }

    @Nullable
    @WorkerThread
    public abstract Notification onGenerateNotification(@NonNull PushMessage pushMessage);
}
