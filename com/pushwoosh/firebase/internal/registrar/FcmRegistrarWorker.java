package com.pushwoosh.firebase.internal.registrar;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.internal.utils.NotificationRegistrarHelper;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RepositoryModule;
import java.io.IOException;

public class FcmRegistrarWorker extends Worker {
    public FcmRegistrarWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    private static void a() {
        try {
            String token = FirebaseInstanceId.getInstance().getToken(Pushwoosh.getInstance().getSenderId(), FirebaseMessaging.INSTANCE_ID_SCOPE);
            if (token != null) {
                PWLog.info("FcmRegistrarWorker", "FCM token is " + token);
                NotificationRegistrarHelper.onRegisteredForRemoteNotifications(token);
                return;
            }
            PWLog.info("FcmRegistrarWorker", "FCM token is empty");
        } catch (IllegalStateException unused) {
            PWLog.error("FcmRegistrarWorker", "FCM registration error: Failed to retrieve token. Is firebase configured correctly?");
            NotificationRegistrarHelper.onFailedToRegisterForRemoteNotifications("");
        } catch (IOException e) {
            String localizedMessage = e.getLocalizedMessage();
            PWLog.error("FcmRegistrarWorker", "FCM registration error:" + localizedMessage);
        }
    }

    private static void b() {
        try {
            FirebaseInstanceId.getInstance().deleteInstanceId();
            PWLog.debug("FcmRegistrarWorker", "Fcm deregistration success");
            NotificationRegistrarHelper.onUnregisteredFromRemoteNotifications(RepositoryModule.getRegistrationPreferences().pushToken().get());
        } catch (Exception e) {
            PWLog.error("FcmRegistrarWorker", "Fcm deregstration error", e);
            NotificationRegistrarHelper.onFailedToUnregisterFromRemoteNotifications(e.getMessage());
        }
    }

    @Override // androidx.work.Worker
    @NonNull
    public ListenableWorker.Result doWork() {
        boolean z = getInputData().getBoolean("DATA_REGISTER", false);
        boolean z2 = getInputData().getBoolean("DATA_UNREGISTER", false);
        if (z) {
            a();
        } else if (z2) {
            b();
        }
        return ListenableWorker.Result.success();
    }
}
