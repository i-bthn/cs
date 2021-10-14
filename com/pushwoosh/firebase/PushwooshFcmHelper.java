package com.pushwoosh.firebase;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.PushwooshMessagingServiceHelper;
import com.pushwoosh.firebase.a.a;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RepositoryModule;
import java.io.IOException;
import java.util.Map;

public class PushwooshFcmHelper {
    private static final String TAG = "FcmHelper";

    public static boolean isPushwooshMessage(RemoteMessage remoteMessage) {
        return a.a(remoteMessage);
    }

    public static Bundle messageToBundle(RemoteMessage remoteMessage) {
        return com.pushwoosh.firebase.a.b.a.a(remoteMessage);
    }

    public static boolean onMessageReceived(Context context, RemoteMessage remoteMessage) {
        if (!isPushwooshMessage(remoteMessage) || !DeviceSpecificProvider.getInstance().isFirebase()) {
            return false;
        }
        String from = remoteMessage.getFrom();
        Map<String, String> data = remoteMessage.getData();
        PWLog.info(TAG, "Received message: " + data.toString() + " from: " + from);
        return PushwooshMessagingServiceHelper.onMessageReceived(context, com.pushwoosh.firebase.a.b.a.a(remoteMessage));
    }

    public static void onTokenRefresh(@Nullable String str) {
        if (AndroidPlatformModule.getApplicationContext() == null) {
            PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            return;
        }
        try {
            String token = FirebaseInstanceId.getInstance().getToken(Pushwoosh.getInstance().getSenderId(), FirebaseMessaging.INSTANCE_ID_SCOPE);
            PWLog.debug(TAG, "onTokenRefresh");
            if (token == null || !token.equals(RepositoryModule.getRegistrationPreferences().pushToken().get())) {
                PushwooshMessagingServiceHelper.onTokenRefresh(token);
            }
        } catch (IOException e) {
            String localizedMessage = e.getLocalizedMessage();
            PWLog.error("PushwooshFcmHelper", "FCM registration error:" + localizedMessage);
        }
    }
}
