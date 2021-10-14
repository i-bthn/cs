package com.pushwoosh.repository;

import androidx.annotation.NonNull;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.utils.JsonUtils;
import com.pushwoosh.internal.utils.a;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class r implements a.AbstractC0024a<a> {
    private com.pushwoosh.internal.utils.a<a> a = new com.pushwoosh.internal.utils.a<>(this, 1000);

    public static class a {
        private final JSONObject a;
        private final Callback<Void, PushwooshException> b;

        a(JSONObject jSONObject, Callback<Void, PushwooshException> callback) {
            this.a = jSONObject;
            this.b = callback;
        }

        public Callback<Void, PushwooshException> a() {
            return this.b;
        }

        @NonNull
        public JSONObject b() {
            return this.a;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(List list, Result result) {
        if (result.isSuccess()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar.a() != null) {
                    aVar.a().process(Result.fromData(null));
                }
            }
            return;
        }
        a(list, (NetworkException) result.getException());
    }

    private void a(List<a> list, NetworkException networkException) {
        RepositoryModule.getRegistrationPreferences().setTagsFailed().set(true);
        for (a aVar : list) {
            if (aVar.a() != null) {
                aVar.a().process(Result.fromException(networkException));
            }
        }
    }

    @Override // com.pushwoosh.internal.utils.a.AbstractC0024a
    public void a(List<a> list) {
        JSONObject jSONObject = new JSONObject();
        for (a aVar : list) {
            JsonUtils.mergeJson(aVar.b(), jSONObject);
        }
        RequestManager requestManager = NetworkModule.getRequestManager();
        if (requestManager == null) {
            a(list, new NetworkException("Request manager is null"));
        } else {
            requestManager.sendRequest(new u(jSONObject), new Callback(list) {
                /* class com.pushwoosh.repository.$$Lambda$r$hOy6KCQdtWmddTTYfE9oK_EBSSI */
                private final /* synthetic */ List f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    r.this.a((r) this.f$1, (List) result);
                }
            });
        }
    }

    public void a(@NonNull JSONObject jSONObject, Callback<Void, PushwooshException> callback) {
        this.a.a(new a(jSONObject, callback));
    }
}
