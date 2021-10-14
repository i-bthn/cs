package com.pushwoosh.notification.g;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.notification.VibrateType;

public class b {
    private final d a;

    public b(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            this.a = new f(notificationManager);
        } else {
            this.a = new e();
        }
    }

    public String a(PushMessage pushMessage, String str, @Nullable String str2) {
        String b = a.b(pushMessage);
        String a2 = a.a(b);
        if (TextUtils.isEmpty(str)) {
            str = b;
        }
        this.a.a(a2, str, str2, pushMessage);
        return a2;
    }

    public String a(String str) {
        this.a.a(str);
        return str;
    }

    public void a(Notification notification, int i, int i2, int i3) {
        this.a.a(notification, i, i2, i3);
    }

    public void a(Notification notification, Uri uri, boolean z) {
        this.a.a(notification, uri, z);
    }

    public void a(Notification notification, VibrateType vibrateType, boolean z) {
        this.a.a(notification, vibrateType, z);
    }
}
