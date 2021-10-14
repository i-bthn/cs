package com.pushwoosh.internal.utils;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.repository.RepositoryModule;
import java.util.List;

public class LockScreenReceiver extends BroadcastReceiver {

    /* access modifiers changed from: private */
    public static class b extends AsyncTask<Void, Void, Void> {
        private b() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            RepositoryModule.getLockScreenMediaStorage().a();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static class c extends AsyncTask<Void, Void, List<Uri>> {
        private final Callback<List<Uri>, PushwooshException> a;

        public c(Callback<List<Uri>, PushwooshException> callback) {
            this.a = callback;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public List<Uri> doInBackground(Void... voidArr) {
            return RepositoryModule.getLockScreenMediaStorage().d();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(List<Uri> list) {
            super.onPostExecute(list);
            this.a.process(Result.from(list, null));
        }
    }

    private static class d extends AsyncTask<Void, Void, Void> {
        private d() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            List<com.pushwoosh.inapp.view.i.h.b> c = RepositoryModule.getLockScreenMediaStorage().c();
            if (c != null && !c.isEmpty()) {
                com.pushwoosh.richmedia.a h = PushwooshPlatform.getInstance().h();
                if (h == null) {
                    PWLog.error("LockScreenReceiver", "RichMediaController is null");
                    return null;
                }
                for (com.pushwoosh.inapp.view.i.h.b bVar : c) {
                    h.a(bVar);
                }
                RepositoryModule.getLockScreenMediaStorage().b();
            }
            return null;
        }
    }

    private void a(Context context) {
        new c(new Callback(context) {
            /* class com.pushwoosh.internal.utils.$$Lambda$LockScreenReceiver$twqlkk1Z7ZmQKSyDlb_JXkiCurk */
            private final /* synthetic */ Context f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.pushwoosh.function.Callback
            public final void process(Result result) {
                LockScreenReceiver.this.a((LockScreenReceiver) this.f$1, (Context) result);
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Context context, Result result) {
        List<Uri> list = (List) result.getData();
        if (!(list == null || list.isEmpty())) {
            for (Uri uri : list) {
                a(uri, context);
            }
            new b().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private void a(Uri uri, Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            PWLog.error("Can't open remote url: " + uri, e);
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (TextUtils.equals(intent.getAction(), "android.intent.action.SCREEN_ON")) {
                new d().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            } else if (TextUtils.equals(intent.getAction(), "android.intent.action.USER_PRESENT")) {
                a(context);
            }
        }
    }
}
