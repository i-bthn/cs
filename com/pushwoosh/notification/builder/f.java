package com.pushwoosh.notification.builder;

import android.app.Notification;
import android.content.Context;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.pushwoosh.internal.platform.AndroidPlatformModule;

@RequiresApi(24)
public class f implements e {
    private final NotificationCompat.Builder a;

    f(Context context, String str) {
        this.a = new NotificationCompat.Builder(context, str);
    }

    @Override // com.pushwoosh.notification.builder.e
    public Notification a() {
        return this.a.build();
    }

    @Override // com.pushwoosh.notification.builder.e
    public e a(int i) {
        this.a.setSmallIcon(i);
        if (i == -1 && AndroidPlatformModule.getApplicationContext() != null) {
            this.a.setSmallIcon(a.a(AndroidPlatformModule.getApplicationContext()));
        }
        return this;
    }

    @Override // com.pushwoosh.notification.builder.e
    public e a(NotificationCompat.InboxStyle inboxStyle) {
        this.a.setStyle(inboxStyle);
        return this;
    }

    @Override // com.pushwoosh.notification.builder.e
    public e a(String str) {
        this.a.setGroup(str);
        return this;
    }

    @Override // com.pushwoosh.notification.builder.e
    public e a(boolean z) {
        this.a.setAutoCancel(z);
        return this;
    }

    @Override // com.pushwoosh.notification.builder.e
    public e b(int i) {
        this.a.setColor(i);
        return this;
    }

    @Override // com.pushwoosh.notification.builder.e
    public e b(boolean z) {
        this.a.setGroupSummary(z);
        return this;
    }

    @Override // com.pushwoosh.notification.builder.e
    public e c(int i) {
        this.a.setNumber(i);
        return this;
    }
}
