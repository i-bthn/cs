package com.pushwoosh.repository;

import android.text.TextUtils;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.platform.utils.a;
import com.pushwoosh.internal.preference.PreferenceBooleanValue;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.b;
import com.pushwoosh.notification.PushwooshNotificationManager;
import com.pushwoosh.notification.e;
import com.pushwoosh.tags.TagsBundle;
import org.json.JSONObject;

public class g {
    private static final String m = "g";
    private RequestManager a;
    private r b;
    private PreferenceBooleanValue c;
    private b d;
    private PushwooshNotificationManager e;
    private RegistrationPrefs f;
    private d g;
    private boolean h;
    private boolean i;
    private TagsBundle j;
    private String k;
    private String l;

    public g(RequestManager requestManager, r rVar, PreferenceBooleanValue preferenceBooleanValue, b bVar, PushwooshNotificationManager pushwooshNotificationManager, RegistrationPrefs registrationPrefs, d dVar) {
        this.a = requestManager;
        this.b = rVar;
        this.c = preferenceBooleanValue;
        this.d = bVar;
        this.e = pushwooshNotificationManager;
        this.f = registrationPrefs;
        this.g = dVar;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Result result) {
        e();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(Result result) {
        if (result.isSuccess()) {
            if (((TagsBundle) result.getData()).getMap().size() > 0) {
                this.j = (TagsBundle) result.getData();
            } else {
                this.c.set(true);
                PWLog.debug(m, "getTags empty");
            }
            synchronized (this) {
                this.h = true;
                if (this.i) {
                    a();
                }
            }
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(((NetworkException) result.getException()).getMessage());
            int i2 = jSONObject.getInt("status_code");
            String string = jSONObject.getString("status_message");
            if (i2 == 210 && string.equals("Device not found")) {
                this.c.set(true);
                PWLog.debug(m, "getTags returned \"Device not found\"");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void c(Result<Void, PushwooshException> result) {
        if (result.isSuccess()) {
            this.c.set(true);
            PWLog.noise(m, "migration success");
        }
    }

    private boolean c() {
        String c2 = this.e.c();
        return c2 != null && !c2.isEmpty() && this.f.isRegisteredForPush().get();
    }

    private void d() {
        this.a.sendRequest(new f(this.c.get() ? this.k : a.h()), new Callback() {
            /* class com.pushwoosh.repository.$$Lambda$g$NtmqUJUb2GeemBDp_0U6u8Qz1Q */

            @Override // com.pushwoosh.function.Callback
            public final void process(Result result) {
                g.this.b(result);
            }
        });
    }

    private void e() {
        TagsBundle tagsBundle = this.j;
        if (tagsBundle != null) {
            JSONObject json = tagsBundle.toJson();
            String str = m;
            PWLog.noise(str, "data for migration:" + json.toString());
            this.b.a(json, new Callback() {
                /* class com.pushwoosh.repository.$$Lambda$g$bJt4z9Z90gwC25Q9_l5_YYjGvPs */

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    g.this.c(result);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (c()) {
            if (!this.l.equals(this.k)) {
                this.f.lastPushRegistration().set(0);
            }
            if (this.f.lastPushRegistration().get() == 0) {
                e.a(new Callback() {
                    /* class com.pushwoosh.repository.$$Lambda$g$oXbIAl4NxqsVAvwaIa2dPLEf0x4 */

                    @Override // com.pushwoosh.function.Callback
                    public final void process(Result result) {
                        g.this.a(result);
                    }
                });
                this.g.a();
                return;
            }
        }
        e();
    }

    public void a(String str, String str2) {
        if ((!this.c.get() || !str.equals(str2)) && !str2.isEmpty()) {
            this.k = str2;
            this.l = str;
            if (!this.c.get()) {
                synchronized (this) {
                    this.i = true;
                    if (this.h) {
                        a();
                    }
                }
                return;
            }
            synchronized (this) {
                this.i = true;
            }
            d();
            return;
        }
        PWLog.noise(m, "migration tags already done");
    }

    public void b() {
        PWLog.noise(m, "prepare migration");
        String h2 = a.h();
        if (this.d.e() || TextUtils.isEmpty(h2)) {
            this.c.set(true);
        }
        if (!this.c.get()) {
            d();
        }
    }
}
