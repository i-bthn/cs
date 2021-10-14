package com.pushwoosh.inapp;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.pushwoosh.inapp.j.c;

public class InAppRetrieverWorker extends Worker {
    public InAppRetrieverWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    private static void a() {
        c c = b.c();
        if (c != null) {
            c.c();
        }
    }

    @Override // androidx.work.Worker
    @NonNull
    public ListenableWorker.Result doWork() {
        a();
        return ListenableWorker.Result.success();
    }
}
