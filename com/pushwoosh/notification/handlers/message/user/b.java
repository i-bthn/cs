package com.pushwoosh.notification.handlers.message.user;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.inapp.view.i.h.b;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.a;
import com.pushwoosh.internal.utils.LockScreenUtils;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.repository.RepositoryModule;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class b extends NotificationMessageHandler {

    /* access modifiers changed from: private */
    /* renamed from: com.pushwoosh.notification.handlers.message.user.b$b  reason: collision with other inner class name */
    public static class AsyncTaskC0026b extends AsyncTask<Void, Void, Void> {
        private final com.pushwoosh.inapp.view.i.h.b a;

        AsyncTaskC0026b(com.pushwoosh.inapp.view.i.h.b bVar) {
            this.a = bVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            b.b(this.a);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static class c extends AsyncTask<Void, Void, Void> {
        private c() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            com.pushwoosh.inapp.view.i.h.b a = RepositoryModule.getSilentRichMediaStorage().a();
            if (a == null) {
                return null;
            }
            b.b(a);
            return null;
        }
    }

    b() {
        EventBus.subscribe(a.c.class, $$Lambda$b$_F6Sq4Gc2K8u3cQ9fquDyTGZ9o.INSTANCE);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(a.c cVar) {
        new c().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void a(String str) {
        if (com.pushwoosh.inapp.b.c() != null) {
            com.pushwoosh.inapp.b.c().a(str);
            PushwooshPlatform.getInstance().o().k();
        }
    }

    private void a(String str, String str2, boolean z) {
        com.pushwoosh.inapp.view.i.h.b a2 = new b.C0014b().b(str).c(str2).a(z).a();
        if (a2 != null) {
            new AsyncTaskC0026b(a2).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private boolean a(PushMessage pushMessage) {
        if (pushMessage != null && !TextUtils.isEmpty(pushMessage.getCustomData()) && pushMessage.isSilent()) {
            try {
                return new JSONObject(pushMessage.getCustomData()).getBoolean("pw_force_show_rm");
            } catch (JSONException unused) {
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static void b(com.pushwoosh.inapp.view.i.h.b bVar) {
        com.pushwoosh.richmedia.a h = PushwooshPlatform.getInstance().h();
        if (h != null) {
            h.a(bVar);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.notification.handlers.message.user.NotificationMessageHandler
    public void handleNotification(PushMessage pushMessage) {
        boolean z;
        String w = com.pushwoosh.notification.b.w(pushMessage.toBundle());
        String sound = pushMessage.getSound();
        if (pushMessage.isLockScreen()) {
            if (LockScreenUtils.isScreenLocked()) {
                z = true;
            } else {
                RepositoryModule.getLockScreenMediaStorage().a(pushMessage);
                return;
            }
        } else if (!a(pushMessage)) {
            return;
        } else {
            if (AndroidPlatformModule.isApplicationInForeground()) {
                z = false;
            } else {
                RepositoryModule.getSilentRichMediaStorage().a(pushMessage);
                return;
            }
        }
        a(w, sound, z);
    }

    @Override // com.pushwoosh.notification.handlers.message.user.MessageHandler, com.pushwoosh.notification.handlers.message.user.NotificationMessageHandler
    public void handlePushMessage(PushMessage pushMessage) {
        String w = com.pushwoosh.notification.b.w(pushMessage.toBundle());
        if (w != null) {
            a(w);
        }
        super.handlePushMessage(pushMessage);
    }
}
