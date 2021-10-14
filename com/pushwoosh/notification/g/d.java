package com.pushwoosh.notification.g;

import android.app.Notification;
import android.net.Uri;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.notification.VibrateType;

/* access modifiers changed from: package-private */
public interface d {
    void a(Notification notification, int i, int i2, int i3);

    void a(Notification notification, Uri uri, boolean z);

    void a(Notification notification, VibrateType vibrateType, boolean z);

    void a(String str);

    void a(String str, String str2, String str3, PushMessage pushMessage);
}
