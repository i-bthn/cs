package com.pushwoosh.function;

import androidx.annotation.NonNull;
import androidx.work.BackoffPolicy;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import com.pushwoosh.PushwooshWorkManagerHelper;
import com.pushwoosh.SendCachedRequestWorker;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.network.PushRequest;
import com.pushwoosh.internal.network.c;
import com.pushwoosh.internal.network.g;
import java.util.concurrent.TimeUnit;

public class a<Response> implements Callback<Response, NetworkException> {
    private Callback<Response, NetworkException> a;
    private PushRequest<Response> b;
    private g c;

    public a(Callback<Response, NetworkException> callback, PushRequest<Response> pushRequest, g gVar) {
        this.a = callback;
        this.b = pushRequest;
        this.c = gVar;
    }

    public a(PushRequest<Response> pushRequest, g gVar) {
        this(null, pushRequest, gVar);
    }

    private void a(long j) {
        PushwooshWorkManagerHelper.enqueueOneTimeUniqueWork((OneTimeWorkRequest) ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(SendCachedRequestWorker.class).setInputData(new Data.Builder().putLong("data_cached_request_id", j).build())).setConstraints(PushwooshWorkManagerHelper.getNetworkAvailableConstraints())).setBackoffCriteria(BackoffPolicy.LINEAR, 5, TimeUnit.SECONDS)).build(), "SendCachedRequestWorker", ExistingWorkPolicy.APPEND);
    }

    @Override // com.pushwoosh.function.Callback
    public void process(@NonNull Result<Response, NetworkException> result) {
        if (!result.isSuccess() && (result.getException() instanceof c)) {
            long a2 = this.c.a((PushRequest<?>) this.b);
            if (a2 >= 0) {
                a(a2);
            }
        }
        Callback<Response, NetworkException> callback = this.a;
        if (callback != null) {
            callback.process(result);
        }
    }
}
