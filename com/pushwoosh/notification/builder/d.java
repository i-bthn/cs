package com.pushwoosh.notification.builder;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.pushwoosh.internal.platform.AndroidPlatformModule;

/* access modifiers changed from: package-private */
@RequiresApi(26)
public class d implements b {
    private final Notification.Builder a;

    d(Context context, String str) {
        this.a = new Notification.Builder(context, str);
    }

    @Override // com.pushwoosh.notification.builder.b
    public Notification a() {
        return this.a.build();
    }

    @Override // com.pushwoosh.notification.builder.b
    public b a(int i) {
        this.a.setSmallIcon(i);
        if (i == -1) {
            this.a.setSmallIcon(a.a(AndroidPlatformModule.getApplicationContext(), AndroidPlatformModule.getAppInfoProvider().a()));
        }
        return this;
    }

    @Override // com.pushwoosh.notification.builder.b
    public b a(int i, int i2, int i3) {
        return this;
    }

    @Override // com.pushwoosh.notification.builder.b
    public b a(int i, CharSequence charSequence, PendingIntent pendingIntent) {
        this.a.addAction(new Notification.Action(i, charSequence, pendingIntent));
        return this;
    }

    @Override // com.pushwoosh.notification.builder.b
    public b a(long j) {
        this.a.setWhen(j);
        this.a.setShowWhen(true);
        return this;
    }

    @Override // com.pushwoosh.notification.builder.b
    public b a(Bitmap bitmap) {
        if (bitmap != null) {
            this.a.setLargeIcon(bitmap);
        }
        return this;
    }

    @Override // com.pushwoosh.notification.builder.b
    public b a(@Nullable Bitmap bitmap, CharSequence charSequence) {
        this.a.setStyle(bitmap != null ? new Notification.BigPictureStyle().bigPicture(bitmap).setSummaryText(charSequence) : new Notification.BigTextStyle().bigText(charSequence));
        return this;
    }

    @Override // com.pushwoosh.notification.builder.b
    public b a(Bundle bundle) {
        this.a.setExtras(bundle);
        return this;
    }

    @Override // com.pushwoosh.notification.builder.b
    public b a(CharSequence charSequence) {
        this.a.setContentText(charSequence);
        return this;
    }

    @Override // com.pushwoosh.notification.builder.b
    public b a(Integer num) {
        if (num != null) {
            this.a.setColor(num.intValue());
        }
        return this;
    }

    @Override // com.pushwoosh.notification.builder.b
    public b b(int i) {
        this.a.setPriority(i);
        return this;
    }

    @Override // com.pushwoosh.notification.builder.b
    public b b(CharSequence charSequence) {
        this.a.setContentTitle(charSequence);
        return this;
    }

    @Override // com.pushwoosh.notification.builder.b
    public b c(int i) {
        this.a.setVisibility(i);
        return this;
    }

    @Override // com.pushwoosh.notification.builder.b
    public b c(CharSequence charSequence) {
        this.a.setTicker(charSequence);
        return this;
    }
}
