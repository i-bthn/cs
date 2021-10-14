package com.pushwoosh.inapp;

import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import com.pushwoosh.PushwooshWorkManagerHelper;

public class e implements d {
    @Override // com.pushwoosh.inapp.d
    public void a() {
        PushwooshWorkManagerHelper.enqueueOneTimeUniqueWork((OneTimeWorkRequest) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(InAppRetrieverWorker.class).setConstraints(PushwooshWorkManagerHelper.getNetworkAvailableConstraints())).build(), "InAppRetrieverWorker", ExistingWorkPolicy.KEEP);
    }
}
