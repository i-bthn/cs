package com.pushwoosh.notification.handlers.notification;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;

/* access modifiers changed from: package-private */
public class b implements PushNotificationOpenHandler {
    b() {
    }

    @Override // com.pushwoosh.notification.handlers.notification.PushNotificationOpenHandler
    public void postHandleNotification(Bundle bundle) {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext == null) {
            PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            return;
        }
        String str = (String) bundle.get("launch");
        if (str != null) {
            Intent intent = null;
            try {
                PackageManager packageManager = AndroidPlatformModule.getManagerProvider().getPackageManager();
                if (packageManager != null) {
                    intent = packageManager.getLaunchIntentForPackage(str);
                }
            } catch (Exception e) {
                PWLog.error("Application not found", e);
            }
            if (intent != null) {
                intent.addFlags(268435456);
                applicationContext.startActivity(intent);
            }
        }
    }
}
