package com.pushwoosh.notification;

import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.internal.chain.Chain;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.handlers.notification.PushNotificationOpenHandler;
import java.util.Iterator;

/* access modifiers changed from: package-private */
public class a {
    private Chain<PushNotificationOpenHandler> a;
    @Nullable
    private final Context b = AndroidPlatformModule.getApplicationContext();

    a(Chain<PushNotificationOpenHandler> chain) {
        this.a = chain;
    }

    /* access modifiers changed from: package-private */
    public void a(Bundle bundle) {
        Iterator<PushNotificationOpenHandler> iterator = this.a.getIterator();
        while (iterator.hasNext()) {
            iterator.next().postHandleNotification(bundle);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(PushMessage pushMessage) {
        boolean z;
        Bundle bundle = new Bundle();
        bundle.putString(Pushwoosh.PUSH_RECEIVE_EVENT, pushMessage.toJson().toString());
        Intent intent = new Intent();
        String str = AndroidPlatformModule.getAppInfoProvider().a() + ".MESSAGE";
        intent.setAction(str);
        intent.setFlags(872415232);
        intent.putExtras(bundle);
        try {
            if (this.b != null) {
                this.b.startActivity(intent);
            }
            z = false;
        } catch (ActivityNotFoundException unused) {
            z = true;
            PWLog.warn("Can't launch activity. Are you sure you have an activity with '" + str + "' action in your manifest? Launching default activity.");
        }
        if (z) {
            try {
                String a2 = AndroidPlatformModule.getAppInfoProvider().a();
                PackageManager packageManager = AndroidPlatformModule.getManagerProvider().getPackageManager();
                Intent launchIntentForPackage = packageManager == null ? null : packageManager.getLaunchIntentForPackage(a2);
                if (launchIntentForPackage != null) {
                    launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
                    launchIntentForPackage.setFlags(872415232);
                    launchIntentForPackage.putExtras(bundle);
                    this.b.startActivity(launchIntentForPackage);
                    return;
                }
                throw new ActivityNotFoundException();
            } catch (ActivityNotFoundException e) {
                PWLog.error("Failed to start default launch activity.", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(Bundle bundle) {
        String o = b.o(bundle);
        if (!TextUtils.isEmpty(o) && this.b != null) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(o));
                if (intent.resolveActivity(this.b.getPackageManager()) == null) {
                    return false;
                }
                intent.addFlags(272629760);
                PendingIntent.getActivity(this.b, 0, intent, 0).send();
                return true;
            } catch (Exception e) {
                PWLog.exception(e);
            }
        }
        return false;
    }
}
