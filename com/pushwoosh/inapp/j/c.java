package com.pushwoosh.inapp.j;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.exception.MergeUserException;
import com.pushwoosh.exception.PostEventException;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.exception.SetEmailException;
import com.pushwoosh.exception.SetUserException;
import com.pushwoosh.exception.SetUserIdException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.event.a;
import com.pushwoosh.inapp.h.b;
import com.pushwoosh.inapp.j.c;
import com.pushwoosh.inapp.k.d;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.event.Subscription;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.tags.TagsBundle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class c {
    @Nullable
    private RequestManager a;
    private final d b;
    private final com.pushwoosh.inapp.j.k.c c;
    private final b d;
    private final b e;
    private final AtomicBoolean f = new AtomicBoolean(false);
    private final RegistrationPrefs g;

    /* access modifiers changed from: private */
    public class a {
        private int a;
        private int b;

        public a(c cVar, int i) {
            this.a = i;
        }

        public void a() {
            this.b++;
        }

        public boolean b() {
            return this.b == this.a;
        }
    }

    public c(@Nullable RequestManager requestManager, d dVar, com.pushwoosh.inapp.j.k.c cVar, b bVar, com.pushwoosh.inapp.k.c cVar2, RegistrationPrefs registrationPrefs) {
        this.a = requestManager;
        this.b = dVar;
        this.c = cVar;
        this.e = bVar;
        this.g = registrationPrefs;
        this.d = new b(dVar, cVar2);
        EventBus.subscribe(com.pushwoosh.inapp.view.d.class, new EventListener() {
            /* class com.pushwoosh.inapp.j.$$Lambda$c$32Rn_ZxZoUCYSmCH3ZAFO8KMI5c */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                c.a(RequestManager.this, (com.pushwoosh.inapp.view.d) event);
            }
        });
    }

    private String a(Result result) {
        return a(result, "an error occurred during /registerEmail request");
    }

    private String a(Result result, String str) {
        return (result.getException() == null || TextUtils.isEmpty(result.getException().getMessage())) ? str : result.getException().getMessage();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(@Nullable Callback callback, Result result) {
        Result fromException;
        if (callback != null) {
            if (result.isSuccess()) {
                fromException = Result.fromData(null);
            } else if (result.getException() != null) {
                fromException = Result.fromException(new MergeUserException(((NetworkException) result.getException()).getMessage()));
            } else {
                return;
            }
            callback.process(fromException);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Callback callback, a aVar, String str, Result result) {
        if (callback != null) {
            if (result.isSuccess()) {
                aVar.a();
            } else {
                callback.process(Result.fromException(new SetEmailException(b(result, str))));
            }
            if (aVar.b()) {
                callback.process(Result.fromData(true));
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(com.pushwoosh.inapp.j.l.b bVar, a.EnumC0009a[] aVarArr, CountDownLatch countDownLatch, com.pushwoosh.inapp.event.a aVar) {
        if (aVar == null) {
            return;
        }
        if ((aVar.b().equals(a.EnumC0009a.DEPLOY_FAILED) || aVar.b().equals(a.EnumC0009a.DEPLOYED)) && aVar.a().equals(bVar.c())) {
            aVarArr[0] = aVar.b();
            countDownLatch.countDown();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(@Nullable RequestManager requestManager, com.pushwoosh.inapp.view.d dVar) {
        requestManager.sendRequest(new j(dVar.a().c()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(String str, Callback callback, Result result) {
        if (result.isSuccess()) {
            a(str, RepositoryModule.getRegistrationPreferences().userId().get(), new Callback(callback) {
                /* class com.pushwoosh.inapp.j.$$Lambda$c$mCE8tG43ldgphZgqknUBFaWZWA */
                private final /* synthetic */ Callback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    c.this.c((c) this.f$1, (Callback) result);
                }
            });
        }
    }

    private void a(@NonNull String str, String str2, @NonNull Callback<Boolean, PushwooshException> callback) {
        RequestManager requestManager;
        h hVar = new h(str2, str);
        if (d() && (requestManager = this.a) != null) {
            requestManager.sendRequest(hVar, new Callback() {
                /* class com.pushwoosh.inapp.j.$$Lambda$c$T2PCMJXPnsurAh0I0_xFW68ECts */

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    c.f(Callback.this, result);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(String str, @NonNull List list, Callback callback, Result result) {
        if (result.isSuccess()) {
            RepositoryModule.getRegistrationPreferences().userId().set(str);
            a(list, new Callback(callback) {
                /* class com.pushwoosh.inapp.j.$$Lambda$c$wDxo8neOYe7l36eMg6l6VWCCkwk */
                private final /* synthetic */ Callback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    c.this.b((c) this.f$1, (Callback) result);
                }
            });
            return;
        }
        callback.process(Result.fromException(new SetUserException(c(result))));
    }

    private void a(List<com.pushwoosh.inapp.j.l.b> list) {
        boolean z;
        Iterator<com.pushwoosh.inapp.j.l.b> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            String d2 = it.next().d();
            if (d2 != null && !d2.isEmpty()) {
                z = true;
                break;
            }
        }
        this.g.gdprEnable().set(z);
    }

    @WorkerThread
    private com.pushwoosh.inapp.j.k.a b(List<com.pushwoosh.inapp.j.l.b> list) {
        ArrayList arrayList = new ArrayList();
        for (com.pushwoosh.inapp.j.l.b bVar : list) {
            if (!this.d.a(bVar)) {
                arrayList.add(bVar);
            }
        }
        return arrayList.isEmpty() ? com.pushwoosh.inapp.j.k.a.b() : this.c.a(arrayList);
    }

    private String b(Result result) {
        return a(result, "an error occurred during /registerEmailUser request");
    }

    private String b(Result result, String str) {
        return a(result, "an error occurred during registration of " + str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(Callback callback, Result result) {
        Result fromException;
        if (callback != null) {
            if (result.isSuccess()) {
                fromException = Result.fromData(true);
            } else if (!result.isSuccess()) {
                fromException = Result.fromException(new SetUserException(a(result)));
            } else {
                return;
            }
            callback.process(fromException);
        }
    }

    private boolean b(com.pushwoosh.inapp.j.l.b bVar) {
        if (!this.d.a(bVar)) {
            return this.c.a(bVar) ? c(bVar) : !this.c.a(Collections.singletonList(bVar)).a().isEmpty();
        }
        return true;
    }

    private String c(Result result) {
        return a(result, "an error occurred during /registerUser request");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(Callback callback, Result result) {
        if (callback != null) {
            callback.process(result.isSuccess() ? Result.fromData(true) : Result.fromException(new PushwooshException(b(result))));
        }
    }

    private void c(@NonNull String str, @NonNull Callback<Boolean, PushwooshException> callback) {
        RequestManager requestManager;
        g gVar = new g(str);
        if (d() && (requestManager = this.a) != null) {
            requestManager.sendRequest(gVar, new Callback() {
                /* class com.pushwoosh.inapp.j.$$Lambda$c$LXULh_7W3d8PPRjuGhkdcUVINl4 */

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    c.e(Callback.this, result);
                }
            });
        }
    }

    private boolean c(com.pushwoosh.inapp.j.l.b bVar) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        a.EnumC0009a[] aVarArr = {a.EnumC0009a.DEPLOY_FAILED};
        Subscription subscribe = EventBus.subscribe(com.pushwoosh.inapp.event.a.class, new EventListener(aVarArr, countDownLatch) {
            /* class com.pushwoosh.inapp.j.$$Lambda$c$IAt3wlgPOhqwfpUpSAiCDxoe2IY */
            private final /* synthetic */ a.EnumC0009a[] f$1;
            private final /* synthetic */ CountDownLatch f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                c.a(com.pushwoosh.inapp.j.l.b.this, this.f$1, this.f$2, (a) event);
            }
        });
        try {
            countDownLatch.await();
            subscribe.unsubscribe();
            return aVarArr[0].equals(a.EnumC0009a.DEPLOYED);
        } catch (InterruptedException e2) {
            PWLog.error("Deploy interrupted", e2);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void d(@Nullable Callback callback, Result result) {
        if (callback != null) {
            f fVar = (f) result.getData();
            if (fVar != null) {
                callback.process(Result.fromData((fVar.b() != null || !fVar.c()) ? fVar.b() : new com.pushwoosh.inapp.j.l.b(fVar.a(), fVar.c())));
                return;
            }
            NetworkException networkException = (NetworkException) result.getException();
            if (networkException != null) {
                callback.process(Result.fromException(new PostEventException(networkException.getMessage())));
                PWLog.warn("[InApp]InAppRepository", networkException.getMessage(), networkException);
            }
        }
    }

    private boolean d() {
        if (this.a != null) {
            return true;
        }
        this.a = NetworkModule.getRequestManager();
        return this.a != null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void e(@NonNull Callback callback, Result result) {
        callback.process(result.isSuccess() ? Result.fromData(true) : Result.fromException(result.getException()));
    }

    private boolean e() throws Exception {
        PWLog.noise("Wait until getInApps finished");
        int i = 0;
        while (!this.f.get() && i < 25) {
            Thread.sleep(200);
            i++;
        }
        if (this.f.get()) {
            return true;
        }
        throw new TimeoutException("InApp wait timeout");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void f(@NonNull Callback callback, Result result) {
        callback.process(result.isSuccess() ? Result.fromData(true) : Result.fromException(result.getException()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void g(Callback callback, Result result) {
        if (callback != null) {
            callback.process(result.isSuccess() ? Result.fromData(true) : Result.fromException(new SetUserIdException(c(result))));
        }
    }

    @WorkerThread
    public Result<com.pushwoosh.inapp.i.a, com.pushwoosh.inapp.g.a> a(com.pushwoosh.inapp.j.l.b bVar) {
        PWLog.noise("mapToHtmlData for resource " + bVar.c() + " inApp is required: " + bVar.m() + " inAppLoaded: " + this.f.get());
        if (bVar.l()) {
            try {
                if (this.f.get() || (bVar.m() && e())) {
                    com.pushwoosh.inapp.j.l.b a2 = this.b.a(bVar.c());
                    if (a2 != null) {
                        bVar = a2;
                    } else {
                        return Result.fromException(new com.pushwoosh.inapp.g.a(String.format("Rich media with code %s does not exist.", bVar.c())));
                    }
                }
            } catch (Exception e2) {
                return Result.fromException(new com.pushwoosh.inapp.g.a(String.format("Can't download or update richMedia: %s", bVar.c()), e2));
            }
        }
        if (this.d.a(bVar) || b(bVar)) {
            try {
                return Result.fromData(this.e.a(bVar));
            } catch (IOException e3) {
                return Result.fromException(new com.pushwoosh.inapp.g.a(String.format("Can't mapping resource %s to htmlData", bVar.c()), e3));
            }
        } else {
            return Result.fromException(new com.pushwoosh.inapp.g.a("Can't download or update richMedia: " + bVar.c()));
        }
    }

    @WorkerThread
    public Result<com.pushwoosh.inapp.j.l.b, com.pushwoosh.inapp.g.a> a(String str) {
        try {
            com.pushwoosh.inapp.j.l.b a2 = com.pushwoosh.inapp.j.l.b.a(str);
            if (b(a2)) {
                return Result.fromData(a2);
            }
            return Result.fromException(new com.pushwoosh.inapp.g.a("Can't download or update richMedia: " + a2.c()));
        } catch (com.pushwoosh.inapp.g.a e2) {
            return Result.fromException(e2);
        }
    }

    public com.pushwoosh.inapp.j.l.b a() {
        return this.b.b();
    }

    public void a(String str, Callback<Boolean, PushwooshException> callback) {
        c(str, new Callback(str, callback) {
            /* class com.pushwoosh.inapp.j.$$Lambda$c$8ghrNzMWVLK8VN3kgOhlIYzfneY */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ Callback f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.pushwoosh.function.Callback
            public final void process(Result result) {
                c.this.a((c) this.f$1, (String) this.f$2, (Callback) result);
            }
        });
    }

    public void a(String str, TagsBundle tagsBundle, @Nullable Callback<com.pushwoosh.inapp.j.l.b, PostEventException> callback) {
        RequestManager requestManager;
        e eVar = new e(str, PushwooshPlatform.getInstance().o().b(), tagsBundle);
        if (d() && (requestManager = this.a) != null) {
            requestManager.sendRequest(eVar, new Callback() {
                /* class com.pushwoosh.inapp.j.$$Lambda$c$gleCaWiCicZmNOYDSBDWY71Oj38 */

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    c.d(Callback.this, result);
                }
            });
        } else if (callback != null) {
            callback.process(Result.fromException(new PostEventException("Request Manager is null")));
        }
    }

    public void a(String str, String str2, boolean z, @Nullable Callback<Void, MergeUserException> callback) {
        RequestManager requestManager;
        d dVar = new d(str, str2, z);
        if (d() && (requestManager = this.a) != null) {
            requestManager.sendRequest(dVar, new Callback() {
                /* class com.pushwoosh.inapp.j.$$Lambda$c$QvYxmZnfsz2wjC8HCC_kMVl4Fck */

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    c.a(Callback.this, result);
                }
            });
        } else if (callback != null) {
            callback.process(Result.fromException(new MergeUserException("Request Manager is null")));
        }
    }

    public void a(String str, @NonNull List<String> list, Callback<Boolean, SetUserException> callback) {
        if (!TextUtils.isEmpty(str)) {
            b(str, new Callback(str, list, callback) {
                /* class com.pushwoosh.inapp.j.$$Lambda$c$U6sx5ycRhRQey01Jy2SZmAl_kU */
                private final /* synthetic */ String f$1;
                private final /* synthetic */ List f$2;
                private final /* synthetic */ Callback f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    c.this.a((c) this.f$1, (String) this.f$2, (List) this.f$3, (Callback) result);
                }
            });
        } else {
            PWLog.warn("userId cannot be empty");
        }
    }

    public void a(@NonNull List<String> list, Callback<Boolean, SetEmailException> callback) {
        if (list.isEmpty()) {
            PWLog.warn("emails array list is empty or null");
            return;
        }
        a aVar = new a(this, list.size());
        for (String str : list) {
            a(str, new Callback(callback, aVar, str) {
                /* class com.pushwoosh.inapp.j.$$Lambda$c$xo6HQuHWCwmKCH3MCBYUG0arOgA */
                private final /* synthetic */ Callback f$1;
                private final /* synthetic */ c.a f$2;
                private final /* synthetic */ String f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    c.this.a((c) this.f$1, (Callback) this.f$2, (c.a) this.f$3, (String) result);
                }
            });
        }
    }

    public com.pushwoosh.inapp.j.l.b b() {
        return this.b.a();
    }

    public void b(String str) {
        b(str, (Callback<Boolean, SetUserIdException>) null);
    }

    public void b(String str, Callback<Boolean, SetUserIdException> callback) {
        RequestManager requestManager;
        i iVar = new i(str);
        if (d() && (requestManager = this.a) != null) {
            requestManager.sendRequest(iVar, new Callback(callback) {
                /* class com.pushwoosh.inapp.j.$$Lambda$c$07C1IWSFX4mQhGSjqftH6jPtgcE */
                private final /* synthetic */ Callback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    c.this.g(this.f$1, result);
                }
            });
        }
    }

    @WorkerThread
    public Result<Void, NetworkException> c() {
        PushwooshException networkException;
        Result<Void, NetworkException> result;
        try {
            a aVar = new a();
            if (d()) {
                if (this.a != null) {
                    Result sendRequestSync = this.a.sendRequestSync(aVar);
                    List<com.pushwoosh.inapp.j.l.b> list = (List) sendRequestSync.getData();
                    if (!sendRequestSync.isSuccess()) {
                        networkException = sendRequestSync.getException();
                        result = Result.fromException(networkException);
                        return result;
                    }
                    if (list != null && !list.isEmpty()) {
                        ArrayList<String> arrayList = new ArrayList();
                        arrayList.addAll(this.b.a(list));
                        com.pushwoosh.inapp.f.d.a(list);
                        for (String str : arrayList) {
                            this.c.a(str);
                        }
                        a(list);
                        b(list);
                    }
                    result = Result.fromData(null);
                    return result;
                }
            }
            networkException = new NetworkException("Request Manager is null");
            result = Result.fromException(networkException);
            return result;
        } finally {
            this.f.set(true);
        }
    }
}
