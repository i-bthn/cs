package com.pushwoosh.internal.network;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.event.InitHwidEvent;
import com.pushwoosh.internal.event.Subscription;
import com.pushwoosh.internal.platform.utils.a;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;
import com.pushwoosh.repository.RepositoryModule;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PushRequest<S> {
    /* access modifiers changed from: package-private */
    public JSONObject a() throws JSONException, InterruptedException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("application", getApplicationId());
        jSONObject.put("hwid", getHwid());
        jSONObject.put("v", "6.2.8");
        jSONObject.put("device_type", DeviceSpecificProvider.getInstance().deviceType());
        String userId = getUserId();
        if (!TextUtils.isEmpty(userId)) {
            jSONObject.put("userId", userId);
        }
        buildParams(jSONObject);
        return jSONObject;
    }

    /* access modifiers changed from: protected */
    public void buildParams(JSONObject jSONObject) throws JSONException {
    }

    /* access modifiers changed from: protected */
    public String getApplicationId() {
        return RepositoryModule.getRegistrationPreferences().applicationId().get();
    }

    /* access modifiers changed from: protected */
    @NonNull
    public String getHwid() throws InterruptedException {
        String hwid = Pushwoosh.getInstance().getHwid();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Subscription subscribe = EventBus.subscribe(InitHwidEvent.class, new EventListener(countDownLatch) {
            /* class com.pushwoosh.internal.network.$$Lambda$PushRequest$oKZndh5i4v9iQ6KhyFKFQZEaDo */
            private final /* synthetic */ CountDownLatch f$0;

            {
                this.f$0 = r1;
            }

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                InitHwidEvent initHwidEvent = (InitHwidEvent) event;
                this.f$0.countDown();
            }
        });
        if (hwid.isEmpty()) {
            countDownLatch.await(5, TimeUnit.SECONDS);
        }
        subscribe.unsubscribe();
        String hwid2 = Pushwoosh.getInstance().getHwid();
        return hwid2.isEmpty() ? a.g() : hwid2;
    }

    public abstract String getMethod();

    /* access modifiers changed from: protected */
    public String getUserId() {
        return RepositoryModule.getRegistrationPreferences().userId().get();
    }

    @Nullable
    public S parseResponse(@NonNull JSONObject jSONObject) throws JSONException {
        return null;
    }
}
