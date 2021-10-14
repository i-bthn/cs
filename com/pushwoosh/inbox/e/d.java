package com.pushwoosh.inbox.e;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inbox.b.d.c;
import com.pushwoosh.inbox.e.f.a;
import com.pushwoosh.inbox.exception.InboxMessagesException;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.utils.PWLog;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/* access modifiers changed from: package-private */
public class d<T> {
    private final Collection<Callback<T, InboxMessagesException>> a = new ConcurrentLinkedQueue();
    private final Handler b = new Handler(Looper.getMainLooper());
    private final c<Result<a, NetworkException>, Result<T, InboxMessagesException>> c;

    d(c<Result<a, NetworkException>, Result<T, InboxMessagesException>> cVar) {
        this.c = cVar;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(Result result) {
        c(this.c.a(result));
    }

    private void c(Result<T, InboxMessagesException> result) {
        for (Callback<T, InboxMessagesException> callback : this.a) {
            if (callback != null) {
                try {
                    callback.process(result);
                    this.a.remove(callback);
                } catch (Exception e) {
                    PWLog.error("Error occurred while processing Callback", e.getMessage());
                }
            }
        }
        this.a.clear();
    }

    /* access modifiers changed from: package-private */
    public void a(@Nullable Callback<T, InboxMessagesException> callback) {
        if (callback != null) {
            this.a.add(callback);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Result<a, NetworkException> result) {
        this.b.post(new Runnable(result) {
            /* class com.pushwoosh.inbox.e.$$Lambda$d$Wy_FUig_nFHNJQ1iVF_11xoDPoQ */
            private final /* synthetic */ Result f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                d.lambda$Wy_FUig_nFHNJQ1iVF_11xoDPoQ(d.this, this.f$1);
            }
        });
    }
}
