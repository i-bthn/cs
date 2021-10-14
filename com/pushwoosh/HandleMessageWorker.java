package com.pushwoosh;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RepositoryModule;

public class HandleMessageWorker extends Worker {
    public HandleMessageWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    private ListenableWorker.Result a() {
        return getRunAttemptCount() < 2 ? ListenableWorker.Result.retry() : ListenableWorker.Result.success();
    }

    @Override // androidx.work.Worker
    @NonNull
    public ListenableWorker.Result doWork() {
        long j = getInputData().getLong("data_push_bundle_id", -1);
        if (j == -1) {
            return a();
        }
        try {
            Bundle c = RepositoryModule.getPushBundleStorage().c(j);
            if (c == null) {
                return a();
            }
            RepositoryModule.getPushBundleStorage().a(j);
            try {
                PushwooshPlatform.getInstance().k().handleMessage(c);
                return ListenableWorker.Result.success();
            } catch (Exception e) {
                PWLog.exception(e);
                return a();
            }
        } catch (Exception unused) {
            return a();
        }
    }
}
