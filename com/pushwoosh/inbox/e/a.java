package com.pushwoosh.inbox.e;

import android.os.AsyncTask;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inbox.exception.InboxMessagesException;

/* access modifiers changed from: package-private */
public class a<T> extends AsyncTask<Void, Void, T> {
    private final c<T> a;
    private final Callback<T, InboxMessagesException> b;

    public a(c<T> cVar, Callback<T, InboxMessagesException> callback) {
        this.a = cVar;
        this.b = callback;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public T doInBackground(Void... voidArr) {
        return this.a.run();
    }

    /* access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(T t) {
        super.onPostExecute(t);
        Callback<T, InboxMessagesException> callback = this.b;
        if (callback != null) {
            callback.process(Result.fromData(t));
        }
    }
}
