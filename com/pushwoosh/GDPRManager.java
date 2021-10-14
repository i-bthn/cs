package com.pushwoosh;

import androidx.core.app.NotificationCompat;
import com.pushwoosh.exception.GetTagsException;
import com.pushwoosh.exception.PostEventException;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.c;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.PushwooshNotificationManager;
import com.pushwoosh.repository.o;
import com.pushwoosh.tags.TagsBundle;
import java.util.Arrays;
import java.util.HashSet;

public class GDPRManager {
    public static final String TAG;
    private o a;
    private PushwooshNotificationManager b;
    private c c;

    GDPRManager(o oVar, PushwooshNotificationManager pushwooshNotificationManager, c cVar) {
        new HashSet(Arrays.asList("Application Version", "Language", "Last Application Open", "First Install"));
        this.a = oVar;
        this.b = pushwooshNotificationManager;
        this.c = cVar;
    }

    private TagsBundle a(TagsBundle tagsBundle) {
        TagsBundle.Builder builder = new TagsBundle.Builder();
        for (String str : tagsBundle.getMap().keySet()) {
            builder.putString(str, null);
        }
        return builder.build();
    }

    private void a(Callback<Void, PushwooshException> callback) {
        PWLog.debug(TAG, "The GDPR solution isn’t available for this account");
        if (callback != null) {
            callback.process(Result.fromException(new PushwooshException("The GDPR solution isn’t available for this account")));
        }
    }

    private void a(Callback<Void, PushwooshException> callback, PushwooshException pushwooshException) {
        if (callback != null) {
            callback.process(pushwooshException != null ? Result.fromException(pushwooshException) : Result.fromData(null));
        }
    }

    /* access modifiers changed from: public */
    /* access modifiers changed from: private */
    /* renamed from: a */
    public void f(Result<Void, PostEventException> result, Callback<Void, PushwooshException> callback) {
        if (result.isSuccess()) {
            this.a.a(new Callback(callback) {
                /* class com.pushwoosh.$$Lambda$GDPRManager$hCrswL1NGRepkrMyZCDAPjW54wM */
                private final /* synthetic */ Callback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    GDPRManager.lambda$hCrswL1NGRepkrMyZCDAPjW54wM(GDPRManager.this, this.f$1, result);
                }
            });
            return;
        }
        if (callback != null) {
            callback.process(Result.fromException(result.getException()));
        }
        PWLog.error(TAG, "cant remove all device data", result.getException());
    }

    /* access modifiers changed from: public */
    /* access modifiers changed from: private */
    public void a(boolean z, Result<Void, PostEventException> result, Callback<Void, PushwooshException> callback) {
        if (!result.isSuccess()) {
            String str = TAG;
            PWLog.error(str, "cant set Communication Enable to " + z, result.getException());
            if (callback != null) {
                callback.process(Result.fromException(result.getException()));
                return;
            }
            return;
        }
        this.a.a(z);
        if (z) {
            this.b.a(new Callback(callback) {
                /* class com.pushwoosh.$$Lambda$GDPRManager$kgMyE9fKssS6_XLsPY1qMCpPlPs */
                private final /* synthetic */ Callback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    GDPRManager.lambda$kgMyE9fKssS6_XLsPY1qMCpPlPs(GDPRManager.this, this.f$1, result);
                }
            });
        } else {
            this.b.b(new Callback(callback) {
                /* class com.pushwoosh.$$Lambda$GDPRManager$r53aWto0gui5e2fkK1wBCLobpa4 */
                private final /* synthetic */ Callback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    GDPRManager.lambda$r53aWto0gui5e2fkK1wBCLobpa4(GDPRManager.this, this.f$1, result);
                }
            });
        }
    }

    /* access modifiers changed from: public */
    private /* synthetic */ void b(Callback callback, Result result) {
        a(callback, result.getException());
    }

    /* access modifiers changed from: public */
    /* access modifiers changed from: private */
    /* renamed from: b */
    public void a(Result<Void, PushwooshException> result, Callback<Void, PushwooshException> callback) {
        if (result.isSuccess()) {
            this.b.b(new Callback(callback) {
                /* class com.pushwoosh.$$Lambda$GDPRManager$65kAjumT1XAbdWbGrG56UZRtIU */
                private final /* synthetic */ Callback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    GDPRManager.m0lambda$65kAjumT1XAbdWbGrG56UZRtIU(GDPRManager.this, this.f$1, result);
                }
            });
        } else if (callback != null) {
            callback.process(result);
        }
    }

    /* access modifiers changed from: public */
    private /* synthetic */ void c(Callback callback, Result result) {
        a(callback, result.getException());
    }

    /* access modifiers changed from: public */
    private /* synthetic */ void e(Callback callback, Result result) {
        a(callback, result.getException());
        if (result.isSuccess()) {
            this.a.l();
        }
    }

    /* access modifiers changed from: public */
    /* access modifiers changed from: private */
    /* renamed from: g */
    public void d(Callback<Void, PushwooshException> callback, Result<TagsBundle, GetTagsException> result) {
        if (result.isSuccess()) {
            this.a.a(a(result.getData()), new Callback(callback) {
                /* class com.pushwoosh.$$Lambda$GDPRManager$WZxSpYS3Txud69QsmBr7kYrKvXw */
                private final /* synthetic */ Callback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    GDPRManager.lambda$WZxSpYS3Txud69QsmBr7kYrKvXw(GDPRManager.this, this.f$1, result);
                }
            });
        } else if (callback != null) {
            callback.process(Result.fromException(result.getException()));
        }
    }

    public static GDPRManager getInstance() {
        return PushwooshPlatform.getInstance().d();
    }

    public boolean isAvailable() {
        PWLog.debug(TAG, "isAvailable");
        return this.a.i();
    }

    public boolean isCommunicationEnabled() {
        PWLog.debug(TAG, "isCommunicationEnabled");
        return this.a.g();
    }

    public boolean isDeviceDataRemoved() {
        PWLog.debug(TAG, "isDeviceDataRemoved");
        return this.a.h();
    }

    public void removeAllDeviceData(Callback<Void, PushwooshException> callback) {
        if (!isAvailable()) {
            a(callback);
            return;
        }
        TagsBundle build = new TagsBundle.Builder().putBoolean(NotificationCompat.CATEGORY_STATUS, true).putInt("device_type", 3).build();
        c cVar = this.c;
        if (cVar != null) {
            cVar.a("GDPRDelete", build, new Callback(callback) {
                /* class com.pushwoosh.$$Lambda$GDPRManager$OGIGOd926WjDES_3Or3o_sZIaU */
                private final /* synthetic */ Callback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    GDPRManager.m1lambda$OGIGOd926WjDES_3Or3o_sZIaU(GDPRManager.this, this.f$1, result);
                }
            });
        }
    }

    public void setCommunicationEnabled(boolean z, Callback<Void, PushwooshException> callback) {
        if (!isAvailable()) {
            a(callback);
            return;
        }
        this.c.a("GDPRConsent", new TagsBundle.Builder().putBoolean("channel", z).putInt("device_type", 3).build(), new Callback(z, callback) {
            /* class com.pushwoosh.$$Lambda$GDPRManager$4dS7wM5pU78MyoQ2MYVN7oaKueI */
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ Callback f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.pushwoosh.function.Callback
            public final void process(Result result) {
                GDPRManager.lambda$4dS7wM5pU78MyoQ2MYVN7oaKueI(GDPRManager.this, this.f$1, this.f$2, result);
            }
        });
    }

    public void showGDPRConsentUI() {
        PWLog.debug(TAG, "showGDPRConsentUI");
        this.c.c();
    }

    public void showGDPRDeletionUI() {
        PWLog.debug(TAG, "showGDPRDeletionUI");
        this.c.d();
    }
}
