package com.pushwoosh;

import android.content.Context;
import androidx.work.Constraints;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;

public final class PushwooshWorkManagerHelper {
    private static WorkManager a() throws Exception {
        try {
            return (WorkManager) WorkManager.class.getMethod("getInstance", Context.class).invoke(null, AndroidPlatformModule.getApplicationContext());
        } catch (NoSuchMethodException | NullPointerException e) {
            if (e instanceof NullPointerException) {
                PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            }
            return WorkManager.getInstance();
        }
    }

    public static void enqueueOneTimeUniqueWork(OneTimeWorkRequest oneTimeWorkRequest, String str, ExistingWorkPolicy existingWorkPolicy) {
        try {
            a().enqueueUniqueWork(str, existingWorkPolicy, oneTimeWorkRequest);
        } catch (Exception e) {
            PWLog.error("Failed to enqueue work.");
            e.printStackTrace();
        }
    }

    public static Constraints getNetworkAvailableConstraints() {
        return new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
    }
}
