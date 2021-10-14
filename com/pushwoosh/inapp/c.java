package com.pushwoosh.inapp;

import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.exception.PostEventException;
import com.pushwoosh.exception.ReloadInAppsException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.view.i.h.b;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.tags.TagsBundle;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class c {
    private final RegistrationPrefs a = RepositoryModule.getRegistrationPreferences();
    private final Map<String, Object> b = new HashMap();
    private final Map<String, String> c = new HashMap();
    private final com.pushwoosh.inapp.j.c d = b.c();
    private d e;

    /* access modifiers changed from: private */
    public interface a {
        void a();
    }

    private static class b extends AsyncTask<Void, Void, Result<Void, NetworkException>> {
        private final Callback<Boolean, ReloadInAppsException> a;

        public b(Callback<Boolean, ReloadInAppsException> callback) {
            this.a = callback;
        }

        private Result<Void, NetworkException> a() throws NullPointerException {
            com.pushwoosh.inapp.j.c c = b.c();
            if (c != null) {
                return c.c();
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Result<Void, NetworkException> doInBackground(Void... voidArr) {
            return a();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Result<Void, NetworkException> result) {
            if (this.a == null) {
                return;
            }
            if (result.isSuccess()) {
                this.a.process(Result.fromData(true));
            } else {
                this.a.process(Result.fromException(new ReloadInAppsException(result.getException() != null ? result.getException().getMessage() : "Unknown error occurred while reloading inapps")));
            }
        }
    }

    /* renamed from: com.pushwoosh.inapp.c$c  reason: collision with other inner class name */
    private static class AsyncTaskC0008c extends e {
        public AsyncTaskC0008c(c cVar, a aVar) {
            super(cVar, aVar);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public com.pushwoosh.inapp.j.l.b doInBackground(Void... voidArr) {
            if (this.a.get() != null) {
                return this.a.get().d.a();
            }
            return null;
        }
    }

    private static class d extends e {
        public d(c cVar, a aVar) {
            super(cVar, aVar);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public com.pushwoosh.inapp.j.l.b doInBackground(Void... voidArr) {
            if (this.a.get() != null) {
                return this.a.get().d.b();
            }
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static abstract class e extends AsyncTask<Void, Void, com.pushwoosh.inapp.j.l.b> {
        protected final WeakReference<c> a;
        private final a b;

        public e(c cVar, a aVar) {
            this.a = new WeakReference<>(cVar);
            this.b = aVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(com.pushwoosh.inapp.j.l.b bVar) {
            super.onPostExecute(bVar);
            if (bVar == null || this.a.get() == null) {
                this.b.a();
            } else {
                this.a.get().a((c) bVar);
            }
        }
    }

    public c(d dVar) {
        this.e = dVar;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(@Nullable Callback callback, Result result) {
        if (result.isSuccess()) {
            com.pushwoosh.inapp.j.l.b bVar = (com.pushwoosh.inapp.j.l.b) result.getData();
            if (callback != null) {
                callback.process(Result.fromData(null));
            }
            if (bVar != null) {
                if (this.a.communicationEnable().get()) {
                    a(bVar);
                } else {
                    PWLog.error("[InApp]PushwooshInApp", "cant show inApp because all communication disable");
                }
            }
        } else {
            if (callback != null) {
                callback.process(Result.fromException(result.getException()));
            }
            PWLog.warn("[InApp]PushwooshInApp", result.getException() == null ? "" : ((PostEventException) result.getException()).getMessage(), result.getException());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(com.pushwoosh.inapp.j.l.b bVar) {
        if (bVar == null) {
            PWLog.error("[InApp]PushwooshInApp", "resource is null, can not finds resource");
            return;
        }
        com.pushwoosh.inapp.view.i.h.b a2 = new b.C0014b().a(bVar).a();
        com.pushwoosh.richmedia.a h = PushwooshPlatform.getInstance().h();
        if (h != null) {
            h.a(a2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e() {
        a(this.d.a());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void f() {
        a(this.d.b());
    }

    public void a() {
        this.e.a();
    }

    public void a(Callback<Boolean, ReloadInAppsException> callback) {
        if (this.d != null) {
            new b(callback).execute(new Void[0]);
        }
    }

    public void a(@NonNull Object obj, @NonNull String str) {
        this.b.put(str, obj);
    }

    public void a(@NonNull String str) {
        this.b.remove(str);
    }

    public void a(@NonNull String str, @Nullable TagsBundle tagsBundle, @Nullable Callback<Void, PostEventException> callback) {
        this.d.a(str, tagsBundle, new Callback(callback) {
            /* class com.pushwoosh.inapp.$$Lambda$c$dd9xhO86c9d10XZlADME60uZw4 */
            private final /* synthetic */ Callback f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.pushwoosh.function.Callback
            public final void process(Result result) {
                c.this.a((c) this.f$1, (Callback) result);
            }
        });
    }

    public void a(@NonNull String str, @NonNull String str2) {
        this.c.put(str2, str);
    }

    public Map<String, Object> b() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.b);
        for (Map.Entry<String, String> entry : this.c.entrySet()) {
            String key = entry.getKey();
            try {
                Object newInstance = Class.forName(entry.getValue()).newInstance();
                if (newInstance != null) {
                    hashMap.put(key, newInstance);
                }
            } catch (Exception e2) {
                PWLog.warn("[InApp]PushwooshInApp", "Failed to instantiate javascript interface for " + key, e2);
            }
        }
        return hashMap;
    }

    public void b(@NonNull String str) {
        if (!TextUtils.equals(str, this.a.userId().get())) {
            this.a.userId().set(str);
            this.d.b(str);
        }
    }

    public void c() {
        new AsyncTaskC0008c(this, new a() {
            /* class com.pushwoosh.inapp.$$Lambda$c$PezZUkFBq35YVArPIpbEQsp_UCw */

            @Override // com.pushwoosh.inapp.c.a
            public final void a() {
                c.this.e();
            }
        }).execute(new Void[0]);
    }

    public void d() {
        new d(this, new a() {
            /* class com.pushwoosh.inapp.$$Lambda$c$Uakvpkdk26NabFyB3Ckfy93DI5Q */

            @Override // com.pushwoosh.inapp.c.a
            public final void a() {
                c.this.f();
            }
        }).execute(new Void[0]);
    }
}
