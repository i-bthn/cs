package com.pushwoosh.inbox.e;

import android.os.AsyncTask;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inbox.exception.InboxMessagesException;
import com.pushwoosh.internal.utils.PWLog;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/* access modifiers changed from: package-private */
public class e<T> {
    private final Collection<Callback<T, InboxMessagesException>> a = new ConcurrentLinkedQueue();
    private final c<T> b;

    e(c<T> cVar) {
        this.b = cVar;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Result result) {
        for (Callback<T, InboxMessagesException> callback : this.a) {
            if (callback != null) {
                try {
                    callback.process(result);
                } catch (Exception e) {
                    PWLog.error("Error occurred while processing Callback", e.getMessage());
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        new a(this.b, new Callback() {
            /* class com.pushwoosh.inbox.e.$$Lambda$e$IsjsaXOB0OZ8Vz6Covz8Qkd5XI */

            @Override // com.pushwoosh.function.Callback
            public final void process(Result result) {
                e.m22lambda$IsjsaXOB0OZ8Vz6Covz8Qkd5XI(e.this, result);
            }
        }).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    /* access modifiers changed from: package-private */
    public void a(Callback<T, InboxMessagesException> callback) {
        if (callback != null) {
            this.a.add(callback);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(Callback<T, InboxMessagesException> callback) {
        if (callback != null) {
            this.a.remove(callback);
        }
    }
}
