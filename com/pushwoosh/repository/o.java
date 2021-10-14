package com.pushwoosh.repository;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.exception.GetTagsException;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.f.a;
import com.pushwoosh.internal.event.ConfigLoadedEvent;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.PushRequest;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.network.g;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.repository.config.Channel;
import com.pushwoosh.repository.config.b;
import com.pushwoosh.repository.config.c;
import com.pushwoosh.repository.config.d;
import com.pushwoosh.tags.Tags;
import com.pushwoosh.tags.TagsBundle;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class o {
    @Nullable
    private final RequestManager a;
    private final r b;
    private final RegistrationPrefs c;
    private final l d;
    private final g e;
    private String f;
    private List<Channel> g;
    private List<c> h;
    private String i;
    private b j;

    class a extends com.pushwoosh.function.a<Void> {
        final /* synthetic */ Callback d;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        a(o oVar, PushRequest pushRequest, g gVar, Callback callback) {
            super(pushRequest, gVar);
            this.d = callback;
        }

        @Override // com.pushwoosh.function.Callback, com.pushwoosh.function.a
        public void process(@NonNull Result<Void, NetworkException> result) {
            Result result2;
            Callback callback;
            super.process(result);
            if (result.isSuccess()) {
                callback = this.d;
                result2 = Result.fromData(result.getData());
            } else {
                callback = this.d;
                result2 = Result.fromException(result.getException());
            }
            callback.process(result2);
        }
    }

    public o(RequestManager requestManager, r rVar, RegistrationPrefs registrationPrefs, l lVar, g gVar, @Nullable b bVar) {
        JSONObject jSONObject;
        this.a = requestManager;
        this.b = rVar;
        this.c = registrationPrefs;
        this.d = lVar;
        this.e = gVar;
        this.j = bVar;
        if (registrationPrefs.setTagsFailed().get() && (jSONObject = lVar.p().get()) != null) {
            PWLog.debug("Resending application tags");
            rVar.a(jSONObject, new Callback() {
                /* class com.pushwoosh.repository.$$Lambda$o$Wqy60EX1j5U4DSQ0PcCYg8G6dbE */

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    o.a(RegistrationPrefs.this, result);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(@Nullable Callback callback, Result result) {
        Result result2;
        TagsBundle tagsBundle;
        if (callback != null) {
            if (result.isSuccess()) {
                tagsBundle = result.getData() == null ? Tags.empty() : (TagsBundle) result.getData();
                this.d.p().set(tagsBundle.toJson());
            } else {
                JSONObject jSONObject = this.d.p().get();
                if (jSONObject != null) {
                    tagsBundle = Tags.fromJson(jSONObject);
                } else {
                    result2 = Result.fromException(new GetTagsException(result.getException() == null ? "" : ((NetworkException) result.getException()).getMessage()));
                    callback.process(result2);
                }
            }
            result2 = Result.fromData(tagsBundle);
            callback.process(result2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Result result) {
        com.pushwoosh.repository.config.a aVar = (com.pushwoosh.repository.config.a) result.getData();
        if (aVar != null) {
            this.g = aVar.a();
            this.h = aVar.b();
            this.i = aVar.d();
            b bVar = this.j;
            if (bVar != null) {
                bVar.a().set(aVar.c());
            }
            EventBus.sendEvent(new ConfigLoadedEvent());
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(RegistrationPrefs registrationPrefs, Result result) {
        if (result.isSuccess()) {
            registrationPrefs.setTagsFailed().set(false);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(Result result) {
        if (result.isSuccess()) {
            this.c.lastAttributesRegistration().set(new Date().getTime());
        }
    }

    private boolean o() {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.add(10, -24);
        Calendar instance3 = Calendar.getInstance();
        instance3.setTime(new Date(this.c.lastAttributesRegistration().get()));
        return !instance2.before(instance3) || !instance3.before(instance);
    }

    public List<Channel> a() {
        return this.g;
    }

    public void a(@Nullable Callback<TagsBundle, GetTagsException> callback) {
        e eVar = new e();
        RequestManager requestManager = this.a;
        if (requestManager != null) {
            requestManager.sendRequest(eVar, new Callback(callback) {
                /* class com.pushwoosh.repository.$$Lambda$o$vmPZxca9BTymQSX2V6O4S8_FXQ */
                private final /* synthetic */ Callback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    o.this.a((o) this.f$1, (Callback) result);
                }
            });
        } else if (callback != null) {
            callback.process(Result.fromException(new GetTagsException("Request Manager is null")));
        }
    }

    public void a(@NonNull TagsBundle tagsBundle, Callback<Void, PushwooshException> callback) {
        JSONObject json = tagsBundle.toJson();
        try {
            this.d.p().merge(json);
        } catch (Exception e2) {
            PWLog.exception(e2);
        }
        this.b.a(json, callback);
    }

    public void a(@NonNull TagsBundle tagsBundle, String str, Callback<Void, PushwooshException> callback) {
        JSONObject json = tagsBundle.toJson();
        RequestManager requestManager = NetworkModule.getRequestManager();
        if (requestManager == null) {
            PWLog.warn("Cannot send email tags", new NetworkException("Request manager is null"));
            return;
        }
        t tVar = new t(json, str);
        requestManager.sendRequest(tVar, new a(this, tVar, RepositoryModule.getRequestStorage(), callback));
    }

    public void a(String str) {
        k kVar = new k(str);
        RequestManager requestManager = this.a;
        if (requestManager != null) {
            requestManager.sendRequest(kVar, new com.pushwoosh.function.a(kVar, this.e));
        }
    }

    public void a(String str, String str2) {
        if (str == null || !TextUtils.equals(str, this.d.d().get())) {
            this.d.d().set(str);
            n nVar = new n(str, str2);
            RequestManager requestManager = this.a;
            if (requestManager != null) {
                requestManager.sendRequest(nVar, new com.pushwoosh.function.a(nVar, this.e));
                return;
            }
            return;
        }
        PWLog.warn("Push stat for (" + str + ") already sent");
    }

    public void a(String str, BigDecimal bigDecimal, String str2, Date date) {
        x xVar = new x(str, bigDecimal, str2, date);
        RequestManager requestManager = this.a;
        if (requestManager != null) {
            requestManager.sendRequest(xVar, new com.pushwoosh.function.a(xVar, this.e));
        }
    }

    public void a(boolean z) {
        this.c.communicationEnable().set(z);
    }

    public String b() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public List<c> c() {
        return this.h;
    }

    public String d() {
        return this.c.hwid().get();
    }

    public String e() {
        return this.i;
    }

    public List<PushMessage> f() {
        ArrayList<String> arrayList = this.d.l().get();
        ArrayList arrayList2 = new ArrayList();
        for (String str : arrayList) {
            Bundle bundle = new Bundle();
            try {
                JSONObject jSONObject = new JSONObject(str);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (jSONObject.get(next) instanceof String) {
                        bundle.putString(next, jSONObject.getString(next));
                    }
                }
                arrayList2.add(new PushMessage(bundle));
            } catch (Exception e2) {
                PWLog.exception(e2);
            }
        }
        return arrayList2;
    }

    public boolean g() {
        return this.c.communicationEnable().get();
    }

    public boolean h() {
        return this.c.removeAllDeviceData().get();
    }

    public boolean i() {
        return this.c.gdprEnable().get();
    }

    public void j() {
        RequestManager requestManager = this.a;
        if (requestManager != null) {
            requestManager.sendRequest(new d(), new Callback() {
                /* class com.pushwoosh.repository.$$Lambda$o$gBwgxy9t1IGX57kxWKRREJTfe4k */

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    o.this.a((o) result);
                }
            });
        }
    }

    public void k() {
        e eVar = new e();
        RequestManager requestManager = this.a;
        if (requestManager != null) {
            Result sendRequestSync = requestManager.sendRequestSync(eVar);
            if (sendRequestSync.isSuccess() && sendRequestSync.getData() != null) {
                JSONObject json = ((TagsBundle) sendRequestSync.getData()).toJson();
                if (json.length() > 0) {
                    this.d.p().set(json);
                }
            }
        }
    }

    public void l() {
        this.d.p().set(null);
        this.c.removeAllDeviceData().set(true);
    }

    public void m() {
        a aVar = new a();
        RequestManager requestManager = this.a;
        if (requestManager != null) {
            requestManager.sendRequest(aVar, new com.pushwoosh.function.a(aVar, this.e));
            com.pushwoosh.inapp.f.d b2 = PushwooshPlatform.getInstance().b();
            b2.a("welcome-inapp", (a.b) null);
            b2.a("app-update-message", (a.b) null);
        }
    }

    public void n() {
        RequestManager requestManager;
        if (o() && (requestManager = this.a) != null) {
            requestManager.sendRequest(new s(null), new Callback() {
                /* class com.pushwoosh.repository.$$Lambda$o$n_5nU7X_KTqr2O_376Ut0ufnxdM */

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    o.this.b((o) result);
                }
            });
        }
    }
}
