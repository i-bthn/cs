package com.pushwoosh.internal.network;

import android.os.AsyncTask;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.config.b;

public class NetworkModule {
    private static RequestManager a;

    static class a extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ Runnable a;

        a(Runnable runnable) {
            this.a = runnable;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            try {
                this.a.run();
                return null;
            } catch (Exception e) {
                PWLog.error(e.getMessage());
                return null;
            }
        }
    }

    public static void execute(Runnable runnable) {
        new a(runnable).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @Nullable
    public static RequestManager getRequestManager() {
        return a;
    }

    public static void init(RegistrationPrefs registrationPrefs, b bVar) {
        if (a == null) {
            a = new f(registrationPrefs, bVar);
        }
    }

    public static void setRequestManager(RequestManager requestManager) {
        a = requestManager;
    }
}
