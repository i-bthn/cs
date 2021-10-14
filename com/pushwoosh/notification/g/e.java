package com.pushwoosh.notification.g;

import android.app.Notification;
import android.media.AudioManager;
import android.net.Uri;
import androidx.annotation.RequiresApi;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.notification.VibrateType;

/* access modifiers changed from: package-private */
@RequiresApi(14)
public class e implements d {
    e() {
    }

    @Override // com.pushwoosh.notification.g.d
    public void a(Notification notification, int i, int i2, int i3) {
        notification.ledARGB = i;
        notification.flags |= 1;
        notification.ledOnMS = i2;
        notification.ledOffMS = i3;
    }

    @Override // com.pushwoosh.notification.g.d
    public void a(Notification notification, Uri uri, boolean z) {
        notification.sound = uri;
        if (z) {
            notification.defaults |= 1;
        }
    }

    @Override // com.pushwoosh.notification.g.d
    public void a(Notification notification, VibrateType vibrateType, boolean z) {
        AudioManager audioManager = AndroidPlatformModule.getManagerProvider().getAudioManager();
        if (audioManager != null) {
            if ((z || vibrateType == VibrateType.ALWAYS || (audioManager.getRingerMode() == 1 && vibrateType == VibrateType.DEFAULT_MODE)) && com.pushwoosh.internal.utils.e.b()) {
                notification.defaults |= 2;
            }
        }
    }

    @Override // com.pushwoosh.notification.g.d
    public void a(String str) {
    }

    @Override // com.pushwoosh.notification.g.d
    public void a(String str, String str2, String str3, PushMessage pushMessage) {
    }
}
