package com.pushwoosh;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.network.b;
import com.pushwoosh.internal.network.c;
import com.pushwoosh.internal.network.g;
import com.pushwoosh.repository.RepositoryModule;

public class SendCachedRequestWorker extends Worker {
    public SendCachedRequestWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    private ListenableWorker.Result a() {
        return getRunAttemptCount() >= 3 ? ListenableWorker.Result.success() : ListenableWorker.Result.retry();
    }

    @Override // androidx.work.Worker
    @NonNull
    public ListenableWorker.Result doWork() {
        long j = getInputData().getLong("data_cached_request_id", -1);
        if (j == -1) {
            return a();
        }
        g requestStorage = RepositoryModule.getRequestStorage();
        b a = requestStorage.a(j);
        if (a == null) {
            return a();
        }
        RequestManager requestManager = NetworkModule.getRequestManager();
        if (requestManager == null) {
            return a();
        }
        Result sendRequestSync = requestManager.sendRequestSync(a, getRunAttemptCount());
        if (!sendRequestSync.isSuccess() && (sendRequestSync.getException() instanceof c)) {
            return a();
        }
        requestStorage.a(a.b());
        return ListenableWorker.Result.success();
    }
}
