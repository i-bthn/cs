package com.pushwoosh.inbox.e;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.event.InboxMessagesUpdatedEvent;
import com.pushwoosh.inbox.exception.InboxMessagesException;
import com.pushwoosh.inbox.f.a;
import com.pushwoosh.inbox.internal.data.InboxMessageSource;
import com.pushwoosh.inbox.internal.data.InboxMessageStatus;
import com.pushwoosh.internal.command.CommandApplayer;
import com.pushwoosh.internal.command.CommandParams;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.event.UserIdUpdatedEvent;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.notification.builder.NotificationBuilderManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.LongCompanionObject;

public class b {
    private final RequestManager a;
    private final com.pushwoosh.inbox.f.a b;
    private final CommandApplayer c;
    private final com.pushwoosh.inbox.b.d.b d = new com.pushwoosh.inbox.b.d.b();
    private final com.pushwoosh.inbox.c.a.a e = new com.pushwoosh.inbox.c.a.a(com.pushwoosh.inbox.b.a.a());
    private d<Integer> f;
    private d<Integer> g;
    private d<Integer> h;
    private e<Integer> i;
    private e<Integer> j;
    private e<Integer> k;
    private final Handler l = new Handler(Looper.getMainLooper());

    /* access modifiers changed from: private */
    /* renamed from: com.pushwoosh.inbox.e.b$b  reason: collision with other inner class name */
    public static class AsyncTaskC0017b extends AsyncTask<Void, Void, com.pushwoosh.inbox.internal.data.b> {
        private final WeakReference<b> a;
        private final String b;
        @Nullable
        private final Callback<com.pushwoosh.inbox.internal.data.b, PushwooshException> c;

        private AsyncTaskC0017b(b bVar, String str, @Nullable Callback<com.pushwoosh.inbox.internal.data.b, PushwooshException> callback) {
            this.a = new WeakReference<>(bVar);
            this.b = str;
            this.c = callback;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public com.pushwoosh.inbox.internal.data.b doInBackground(Void... voidArr) {
            b bVar = this.a.get();
            if (bVar == null) {
                return null;
            }
            bVar.b.a(this.b);
            return null;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(com.pushwoosh.inbox.internal.data.b bVar) {
            super.onPostExecute(bVar);
            Callback<com.pushwoosh.inbox.internal.data.b, PushwooshException> callback = this.c;
            if (callback != null) {
                callback.process(Result.from(bVar, null));
            }
        }
    }

    /* access modifiers changed from: private */
    public static class c extends AsyncTask<Void, Void, Collection<InboxMessage>> {
        private WeakReference<b> a;
        @Nullable
        private Callback<Collection<InboxMessage>, InboxMessagesException> b;
        @Nullable
        private Result<com.pushwoosh.inbox.e.f.a, NetworkException> c;
        private long d;
        private int e;

        private c(b bVar, @Nullable Callback<Collection<InboxMessage>, InboxMessagesException> callback, @Nullable Result<com.pushwoosh.inbox.e.f.a, NetworkException> result, long j, int i) {
            this.a = new WeakReference<>(bVar);
            this.b = callback;
            this.c = result;
            this.d = j;
            this.e = i;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Collection<InboxMessage> doInBackground(Void... voidArr) {
            b bVar = this.a.get();
            if (bVar == null) {
                return null;
            }
            return bVar.c((b) bVar.b.a(this.d, this.e));
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Collection<InboxMessage> collection) {
            super.onPostExecute(collection);
            Result<com.pushwoosh.inbox.e.f.a, NetworkException> result = this.c;
            InboxMessagesException inboxMessagesException = (result == null || result.getException() == null) ? null : new InboxMessagesException("Can't load inboxList", this.c.getException());
            Callback<Collection<InboxMessage>, InboxMessagesException> callback = this.b;
            if (callback != null) {
                callback.process(Result.from(collection, inboxMessagesException));
            }
        }
    }

    /* access modifiers changed from: private */
    public static class d extends AsyncTask<Void, Void, com.pushwoosh.inbox.f.b.a> {
        private final WeakReference<b> a;
        private final Collection<com.pushwoosh.inbox.internal.data.b> b;
        private final boolean c;
        @Nullable
        private final Callback<com.pushwoosh.inbox.f.b.a, PushwooshException> d;

        private d(b bVar, Collection<com.pushwoosh.inbox.internal.data.b> collection, boolean z, @Nullable Callback<com.pushwoosh.inbox.f.b.a, PushwooshException> callback) {
            this.a = new WeakReference<>(bVar);
            this.b = collection;
            this.c = z;
            this.d = callback;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public com.pushwoosh.inbox.f.b.a doInBackground(Void... voidArr) {
            b bVar = this.a.get();
            if (bVar == null) {
                return null;
            }
            return bVar.b.a(this.b, this.c);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(com.pushwoosh.inbox.f.b.a aVar) {
            super.onPostExecute(aVar);
            Callback<com.pushwoosh.inbox.f.b.a, PushwooshException> callback = this.d;
            if (callback != null) {
                callback.process(Result.from(aVar, null));
            }
        }
    }

    public b(RequestManager requestManager, com.pushwoosh.inbox.f.a aVar, CommandApplayer commandApplayer) {
        this.a = requestManager;
        this.b = aVar;
        this.c = commandApplayer;
        b();
        c();
        EventBus.subscribe(UserIdUpdatedEvent.class, new EventListener() {
            /* class com.pushwoosh.inbox.e.$$Lambda$b$fwCD9fK3mHWCdeQrRoFIWv5ycac */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                b.this.a((b) ((UserIdUpdatedEvent) event));
            }
        });
    }

    private long a(InboxMessage inboxMessage) {
        return inboxMessage == null ? LongCompanionObject.MAX_VALUE : ((com.pushwoosh.inbox.internal.data.a) inboxMessage).a().h();
    }

    private com.pushwoosh.inbox.f.b.a a(Collection<com.pushwoosh.inbox.internal.data.b> collection, boolean z) {
        com.pushwoosh.inbox.f.b.a a2 = this.b.a(collection, z);
        for (Map.Entry<String, InboxMessageStatus> entry : a2.b().entrySet()) {
            a(entry.getKey(), entry.getValue(), true);
        }
        return a2;
    }

    private Collection<InboxMessage> a(Collection<String> collection) {
        return c(this.b.a(collection));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Pair pair) {
        EventBus.sendEvent(new com.pushwoosh.inbox.event.a().c(c((Collection) pair.first)).b((Collection) pair.second).a());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(@Nullable Callback callback, long j2, int i2, Result result) {
        new c(callback, result, j2, i2).execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Result result) {
        com.pushwoosh.inbox.f.b.a aVar = (com.pushwoosh.inbox.f.b.a) result.getData();
        if (aVar != null) {
            for (Map.Entry<String, InboxMessageStatus> entry : aVar.b().entrySet()) {
                new AsyncTaskC0017b(entry.getKey(), new Callback(entry) {
                    /* class com.pushwoosh.inbox.e.$$Lambda$b$BMPeWgYOBVYLQECjwyhhUyXNU0 */
                    private final /* synthetic */ Map.Entry f$1;

                    {
                        this.f$1 = r2;
                    }

                    @Override // com.pushwoosh.function.Callback
                    public final void process(Result result) {
                        b.this.a((b) this.f$1, (Map.Entry) result);
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        }
    }

    private void a(com.pushwoosh.inbox.c.b.c cVar) {
        if (cVar.a() != null) {
            this.b.b(cVar.a());
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(InboxMessage inboxMessage, @Nullable Callback callback) {
        callback.process(inboxMessage == null ? Result.fromException(new InboxMessagesException("Unknown inbox")) : Result.fromData(inboxMessage));
    }

    @WorkerThread
    private void a(com.pushwoosh.inbox.e.f.a aVar) {
        if (aVar == null) {
            return;
        }
        if (aVar != com.pushwoosh.inbox.e.f.a.d || !aVar.d()) {
            Pair<Collection<com.pushwoosh.inbox.internal.data.b>, Collection<String>> b2 = b(aVar.c());
            ((Collection) b2.second).addAll(aVar.b());
            this.l.post(new Runnable() {
                /* class com.pushwoosh.inbox.e.$$Lambda$b$wq5znN6lmb1F2acoh3ZtFLpZ0Fo */

                public final void run() {
                    EventBus.sendEvent(InboxMessagesUpdatedEvent.this);
                }
            });
        }
    }

    private void a(com.pushwoosh.inbox.internal.data.b bVar, InboxMessageStatus inboxMessageStatus, boolean z) {
        if (bVar != null) {
            if (Build.VERSION.SDK_INT >= 23) {
                NotificationBuilderManager.removeInboxNotificationFromStatusBar(bVar.d());
            } else {
                NotificationBuilderManager.removeInboxNotification(bVar.d());
            }
        }
        if (bVar != null && bVar.j() == InboxMessageSource.SERVICE) {
            this.a.sendRequest(new com.pushwoosh.inbox.c.b.d(bVar.h(), inboxMessageStatus, bVar.c()));
        }
        if (bVar != null && z && inboxMessageStatus == InboxMessageStatus.OPEN) {
            a(bVar.c(), bVar.i());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(UserIdUpdatedEvent userIdUpdatedEvent) {
        b(true, null);
    }

    private void a(String str, InboxMessageStatus inboxMessageStatus, boolean z) {
        a(this.b.a(str), inboxMessageStatus, z);
    }

    private void a(String str, String str2) {
        this.c.applyCommand($$Lambda$b$UVWl6sZ9cm2yw32gBKH4MU79S_4.INSTANCE, new CommandParams(new Pair(str, str2)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Map.Entry entry, Result result) {
        a((com.pushwoosh.inbox.internal.data.b) result.getData(), (InboxMessageStatus) entry.getValue(), true);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    public /* synthetic */ void a(boolean z, @Nullable Callback callback) {
        Result<com.pushwoosh.inbox.e.f.a, NetworkException> result;
        com.pushwoosh.inbox.e.f.a aVar;
        com.pushwoosh.inbox.c.b.c cVar;
        if (this.e.check() || z) {
            this.e.c();
            try {
                Collection<com.pushwoosh.inbox.internal.data.b> b2 = this.b.b();
                Result sendRequestSync = this.a.sendRequestSync(new com.pushwoosh.inbox.c.b.b());
                if (sendRequestSync.isSuccess() && (cVar = (com.pushwoosh.inbox.c.b.c) sendRequestSync.getData()) != null) {
                    a(cVar);
                    com.pushwoosh.inbox.f.b.a a2 = a((Collection<com.pushwoosh.inbox.internal.data.b>) cVar.b(), true);
                    a2.a().addAll(cVar.a());
                    for (com.pushwoosh.inbox.internal.data.b bVar : b2) {
                        a(Collections.singletonMap(bVar.d(), bVar.f()), false, (Callback<InboxMessage, InboxMessagesException>) null);
                    }
                    aVar = new com.pushwoosh.inbox.e.f.a(a2.c(), a2.d(), cVar.a());
                    a(aVar);
                } else if (sendRequestSync.getException() != null) {
                    result = Result.fromException(sendRequestSync.getException());
                    this.e.a();
                    this.l.post(new Runnable() {
                        /* class com.pushwoosh.inbox.e.$$Lambda$b$fEdTfbM9Tpunak6287_5TsfLLU */

                        public final void run() {
                            b.this.e();
                        }
                    });
                    e(result);
                    if (callback == null) {
                        callback.process(result);
                        return;
                    }
                    return;
                } else {
                    aVar = com.pushwoosh.inbox.e.f.a.d;
                }
                result = Result.fromData(aVar);
                this.e.a();
                this.l.post(new Runnable() {
                    /* class com.pushwoosh.inbox.e.$$Lambda$b$fEdTfbM9Tpunak6287_5TsfLLU */

                    public final void run() {
                        b.this.e();
                    }
                });
                e(result);
                if (callback == null) {
                }
            } catch (Throwable th) {
                this.e.a();
                throw th;
            }
        } else {
            e(Result.fromData(com.pushwoosh.inbox.e.f.a.d));
            if (callback != null) {
                callback.process(Result.fromData(null));
            }
        }
    }

    private Pair<Collection<com.pushwoosh.inbox.internal.data.b>, Collection<String>> b(@Nullable Collection<String> collection) {
        if (collection == null || collection.isEmpty()) {
            return new Pair<>(new ArrayList(), new ArrayList());
        }
        Collection<com.pushwoosh.inbox.internal.data.b> a2 = this.b.a(collection);
        Iterator<com.pushwoosh.inbox.internal.data.b> it = a2.iterator();
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            com.pushwoosh.inbox.internal.data.b next = it.next();
            if (next.l()) {
                arrayList.add(next.d());
                it.remove();
            }
        }
        return new Pair<>(a2, arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Result b(Result result) {
        return Result.from(Integer.valueOf(this.b.a()), result.getException() == null ? null : new InboxMessagesException("Can't update count of the inboxMessages with no action performed", result.getException()));
    }

    private void b() {
        this.f = new d<>(new com.pushwoosh.inbox.b.d.c() {
            /* class com.pushwoosh.inbox.e.$$Lambda$b$EgQmq77hTyOeS__Fq5QMKFEHqbM */

            @Override // com.pushwoosh.inbox.b.d.c
            public final Object a(Object obj) {
                return b.this.b((Result) obj);
            }
        });
        this.g = new d<>(new com.pushwoosh.inbox.b.d.c() {
            /* class com.pushwoosh.inbox.e.$$Lambda$b$uIBDIFoKXglYMlLmUX3laEkdTKM */

            @Override // com.pushwoosh.inbox.b.d.c
            public final Object a(Object obj) {
                return b.this.c((Result) obj);
            }
        });
        this.h = new d<>(new com.pushwoosh.inbox.b.d.c() {
            /* class com.pushwoosh.inbox.e.$$Lambda$b$FXxIAReklBOThrdL1RZQjnp_dPU */

            @Override // com.pushwoosh.inbox.b.d.c
            public final Object a(Object obj) {
                return b.this.d((Result) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(Map map, boolean z, @Nullable Callback callback) {
        boolean z2 = false;
        for (Map.Entry entry : map.entrySet()) {
            boolean z3 = !this.b.a((String) entry.getKey(), (InboxMessageStatus) entry.getValue()).isEmpty();
            if (z3) {
                this.l.post(new Runnable() {
                    /* class com.pushwoosh.inbox.e.$$Lambda$b$fEdTfbM9Tpunak6287_5TsfLLU */

                    public final void run() {
                        b.this.e();
                    }
                });
                z2 = true;
            }
            if (z3) {
                a((String) entry.getKey(), (InboxMessageStatus) entry.getValue(), z);
            }
        }
        Pair<Collection<com.pushwoosh.inbox.internal.data.b>, Collection<String>> b2 = b(map.keySet());
        for (com.pushwoosh.inbox.internal.data.b bVar : (Collection) b2.first) {
            if (callback != null) {
                this.l.post(new Runnable(callback) {
                    /* class com.pushwoosh.inbox.e.$$Lambda$b$36mwNwqO90pQheRjKrBDSFM7siw */
                    private final /* synthetic */ Callback f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        b.a(InboxMessage.this, this.f$1);
                    }
                });
            }
        }
        if (z2) {
            this.l.post(new Runnable(b2) {
                /* class com.pushwoosh.inbox.e.$$Lambda$b$Pida0VVKFfynwOcQJh9kze7Ehw */
                private final /* synthetic */ Pair f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    b.this.a((b) this.f$1);
                }
            });
        }
    }

    private void b(boolean z, @Nullable Callback<com.pushwoosh.inbox.e.f.a, NetworkException> callback) {
        if (!this.e.b()) {
            NetworkModule.execute(new Runnable(z, callback) {
                /* class com.pushwoosh.inbox.e.$$Lambda$b$sCNEYbpuNMRY0BKZqfuOOVnVkY */
                private final /* synthetic */ boolean f$1;
                private final /* synthetic */ Callback f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    b.this.a((b) this.f$1, (boolean) this.f$2);
                }
            });
        } else if (callback != null) {
            callback.process(Result.fromData(null));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Result c(Result result) {
        return Result.from(Integer.valueOf(this.b.c()), result.getException() == null ? null : new InboxMessagesException("Can't update count of the unread inboxMessages", result.getException()));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @NonNull
    private Collection<InboxMessage> c(@Nullable Collection<com.pushwoosh.inbox.internal.data.b> collection) {
        if (collection == null || collection.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (com.pushwoosh.inbox.internal.data.b bVar : collection) {
            arrayList.add(this.d.a(bVar));
        }
        return arrayList;
    }

    private void c() {
        com.pushwoosh.inbox.f.a aVar = this.b;
        aVar.getClass();
        this.i = new e<>(new c() {
            /* class com.pushwoosh.inbox.e.$$Lambda$AMIOSQfRhTj6VYrw9W1G8BNT0A */

            @Override // com.pushwoosh.inbox.e.c
            public final Object run() {
                return Integer.valueOf(a.this.a());
            }
        });
        com.pushwoosh.inbox.f.a aVar2 = this.b;
        aVar2.getClass();
        this.j = new e<>(new c() {
            /* class com.pushwoosh.inbox.e.$$Lambda$hn1oTdWXElHR1odAN86D18N79h0 */

            @Override // com.pushwoosh.inbox.e.c
            public final Object run() {
                return Integer.valueOf(a.this.c());
            }
        });
        com.pushwoosh.inbox.f.a aVar3 = this.b;
        aVar3.getClass();
        this.k = new e<>(new c() {
            /* class com.pushwoosh.inbox.e.$$Lambda$MzI3GsYOJWW2MkYWn9cXkOmOTjs */

            @Override // com.pushwoosh.inbox.e.c
            public final Object run() {
                return Integer.valueOf(a.this.d());
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Result d(Result result) {
        return Result.from(Integer.valueOf(this.b.d()), result.getException() == null ? null : new InboxMessagesException("Can't update total count of the inboxMessages", result.getException()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String d() {
        return "pushStat";
    }

    /* access modifiers changed from: private */
    public void e() {
        this.k.a();
        this.i.a();
        this.j.a();
    }

    private void e(Result<com.pushwoosh.inbox.e.f.a, NetworkException> result) {
        this.f.a(result);
        this.g.a(result);
        this.h.a(result);
    }

    public Collection<InboxMessage> a(@Nullable InboxMessage inboxMessage, int i2) throws InboxMessagesException {
        if (inboxMessage == null || (inboxMessage instanceof com.pushwoosh.inbox.internal.data.a)) {
            return c(this.b.a(a(inboxMessage), i2));
        }
        throw new InboxMessagesException("Provided InboxMessage is not instance of InboxMessageImpl");
    }

    public void a() {
        new d(Collections.emptyList(), true, new Callback() {
            /* class com.pushwoosh.inbox.e.$$Lambda$b$itIR3BNlzfPgog2FzF75O5cuUk */

            @Override // com.pushwoosh.function.Callback
            public final void process(Result result) {
                b.this.a((b) result);
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void a(Callback<Integer, InboxMessagesException> callback) {
        this.k.a(callback);
    }

    public void a(@Nullable Callback<Collection<InboxMessage>, InboxMessagesException> callback, @Nullable InboxMessage inboxMessage, int i2) throws InboxMessagesException {
        if (inboxMessage == null || (inboxMessage instanceof com.pushwoosh.inbox.internal.data.a)) {
            new c(callback, null, a(inboxMessage), i2).execute(new Void[0]);
            return;
        }
        throw new InboxMessagesException("Provided InboxMessage is not instance of InboxMessageImpl");
    }

    @WorkerThread
    public void a(com.pushwoosh.inbox.internal.data.b bVar) {
        if (bVar.getMessage() != null) {
            com.pushwoosh.inbox.f.b.a a2 = a((Collection<com.pushwoosh.inbox.internal.data.b>) Collections.singleton(bVar), false);
            a(new com.pushwoosh.inbox.e.f.a(a2.c(), a2.d(), a2.a()));
            this.l.post(new Runnable() {
                /* class com.pushwoosh.inbox.e.$$Lambda$b$fEdTfbM9Tpunak6287_5TsfLLU */

                public final void run() {
                    b.this.e();
                }
            });
            return;
        }
        b(true, null);
    }

    public void a(String str, InboxMessageStatus inboxMessageStatus, Callback<InboxMessage, InboxMessagesException> callback) {
        a(Collections.singletonMap(str, inboxMessageStatus), false, callback);
    }

    public void a(Map<String, InboxMessageStatus> map, boolean z, @Nullable Callback<InboxMessage, InboxMessagesException> callback) {
        NetworkModule.execute(new Runnable(map, z, callback) {
            /* class com.pushwoosh.inbox.e.$$Lambda$b$r9oArOXu1cajAjsXoATnL76Fhk */
            private final /* synthetic */ Map f$1;
            private final /* synthetic */ boolean f$2;
            private final /* synthetic */ Callback f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                b.this.b((b) this.f$1, (Map) this.f$2, (boolean) this.f$3);
            }
        });
    }

    public void b(@Nullable Callback<Integer, InboxMessagesException> callback) {
        this.i.a(callback);
    }

    public void b(@Nullable Callback<Collection<InboxMessage>, InboxMessagesException> callback, @Nullable InboxMessage inboxMessage, int i2) {
        if (inboxMessage == null || (inboxMessage instanceof com.pushwoosh.inbox.internal.data.a)) {
            b(false, new Callback(callback, a(inboxMessage), i2) {
                /* class com.pushwoosh.inbox.e.$$Lambda$b$y2Y0tBKXRd4pdNRZWJNraMjY_hU */
                private final /* synthetic */ Callback f$1;
                private final /* synthetic */ long f$2;
                private final /* synthetic */ int f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r5;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    b.this.a(this.f$1, this.f$2, this.f$3, result);
                }
            });
        } else if (callback != null) {
            callback.process(Result.fromException(new InboxMessagesException("Provided InboxMessage is not instance of InboxMessageImpl")));
        }
    }

    public void c(@Nullable Callback<Integer, InboxMessagesException> callback) {
        this.j.a(callback);
    }

    public void d(@Nullable Callback<Integer, InboxMessagesException> callback) {
        this.f.a(callback);
        b(false, null);
    }

    public void e(Callback<Integer, InboxMessagesException> callback) {
        this.h.a(callback);
        b(false, null);
    }

    public void f(@Nullable Callback<Integer, InboxMessagesException> callback) {
        this.g.a(callback);
        b(false, null);
    }

    public void g(Callback<Integer, InboxMessagesException> callback) {
        this.k.b(callback);
    }

    public void h(@Nullable Callback<Integer, InboxMessagesException> callback) {
        this.i.b(callback);
    }

    public void i(@Nullable Callback<Integer, InboxMessagesException> callback) {
        this.j.b(callback);
    }
}
