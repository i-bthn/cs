package com.pushwoosh.notification;

import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.PushwooshWorkManagerHelper;
import com.pushwoosh.exception.RegisterForPushNotificationsException;
import com.pushwoosh.exception.UnregisterForPushNotificationException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.internal.event.AppIdChangedEvent;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.registrar.PushRegistrar;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.event.DeregistrationErrorEvent;
import com.pushwoosh.notification.event.RegistrationErrorEvent;
import com.pushwoosh.notification.event.RegistrationSuccessEvent;
import com.pushwoosh.notification.handlers.message.system.MessageSystemHandleChainProvider;
import com.pushwoosh.notification.handlers.message.user.MessageHandleChainProvider;
import com.pushwoosh.notification.handlers.notification.NotificationOpenHandlerChainProvider;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.d;
import java.util.concurrent.atomic.AtomicBoolean;

public class PushwooshNotificationManager {
    private final RegistrationPrefs a;
    private PushRegistrar b;
    private PushMessage c;
    private com.pushwoosh.internal.utils.c d;
    private AtomicBoolean e = new AtomicBoolean(false);
    private AtomicBoolean f = new AtomicBoolean(false);

    public static class b implements Event {
        b() {
        }
    }

    /* access modifiers changed from: private */
    public static class c extends AsyncTask<Void, Void, Void> {
        private c() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            RepositoryModule.getRequestStorage().a();
            return null;
        }
    }

    public PushwooshNotificationManager(PushRegistrar pushRegistrar, com.pushwoosh.internal.utils.c cVar) {
        this.d = cVar;
        this.b = pushRegistrar;
        this.a = RepositoryModule.getRegistrationPreferences();
    }

    public LocalNotificationRequest a(LocalNotification localNotification) {
        return new LocalNotificationRequest(LocalNotificationReceiver.scheduleNotification(localNotification.b(), localNotification.a()));
    }

    public void a() {
        this.c = null;
    }

    public void a(Callback<String, RegisterForPushNotificationsException> callback) {
        try {
            if (!this.a.communicationEnable().get()) {
                PWLog.debug("NotificationManager", "Communication with Pushwoosh is disabled");
                return;
            }
            this.a.isRegisteredForPush().set(true);
            e.a(callback);
            this.b.checkDevice(this.a.applicationId().get());
            String str = this.a.pushToken().get();
            if (TextUtils.isEmpty(str)) {
                this.b.registerPW();
            } else {
                EventBus.sendEvent(new RegistrationSuccessEvent(str));
            }
        } catch (Exception e2) {
            PWLog.exception(e2);
            EventBus.sendEvent(new RegistrationErrorEvent(e2.getMessage()));
        }
    }

    public void a(PushRegistrar pushRegistrar) {
        if (pushRegistrar != null) {
            this.b = pushRegistrar;
            d();
        }
    }

    public void a(PushMessage pushMessage) {
        this.c = pushMessage;
    }

    public void a(String str) {
        RequestManager requestManager;
        PWLog.info("NotificationManager", "App ID: " + str);
        if (!TextUtils.isEmpty(str)) {
            String str2 = this.a.applicationId().get();
            boolean z = false;
            if (!str2.equals(str)) {
                this.f.set(false);
                if (this.a.registeredOnServer().get()) {
                    PWLog.info("NotificationManager", "App id changed unregister form previous application");
                    d.a(this.a.pushToken().get(), this.a.baseUrl().get());
                }
                PushwooshPlatform.getInstance().p();
                new c().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                this.a.removeAppId();
                this.a.forceRegister().set(this.a.isRegisteredForPush().get());
                EventBus.sendEvent(new AppIdChangedEvent(str, str2));
                z = true;
            }
            this.a.setAppId(str);
            if (z && (requestManager = NetworkModule.getRequestManager()) != null) {
                requestManager.updateBaseUrl(this.a.baseUrl().get());
            }
            if (!this.f.get()) {
                EventBus.sendEvent(new b());
                this.f.set(true);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Application id is empty");
    }

    public PushMessage b() {
        return this.c;
    }

    public void b(Callback<String, UnregisterForPushNotificationException> callback) {
        f.a(callback);
        this.a.isRegisteredForPush().set(false);
        this.b.unregisterPW();
    }

    public void b(String str) {
        PWLog.info("NotificationManager", "Sender ID: " + str);
        if (!TextUtils.isEmpty(str)) {
            String str2 = this.a.projectId().get();
            boolean z = false;
            if (!TextUtils.equals(str2, str) && !TextUtils.isEmpty(str2)) {
                PWLog.info("NotificationManager", "Sender ID changed, clearing token");
                z = !this.a.pushToken().get().isEmpty();
                this.a.removeSenderId();
            }
            this.a.projectId().set(str);
            if (z) {
                this.b.registerPW();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Sender id is empty");
    }

    public String c() {
        String str = this.a.pushToken().get();
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return null;
    }

    public void d() {
        this.b.init();
    }

    public void e() {
        MessageHandleChainProvider.init();
        MessageSystemHandleChainProvider.init();
        NotificationOpenHandlerChainProvider.init();
        String b2 = TextUtils.isEmpty(this.d.b()) ? this.a.applicationId().get() : this.d.b();
        String projectId = DeviceSpecificProvider.getInstance().projectId();
        if (!TextUtils.isEmpty(projectId)) {
            b(projectId);
        }
        if (!TextUtils.isEmpty(b2)) {
            a(b2);
        }
    }

    public void f() {
        if (this.e.get()) {
            PWLog.warn("NotificationManager", "Local pushes already rescheduled");
            return;
        }
        PushwooshWorkManagerHelper.enqueueOneTimeUniqueWork((OneTimeWorkRequest) new OneTimeWorkRequest.Builder(RescheduleNotificationsWorker.class).build(), "RescheduleNotificationsWorker", ExistingWorkPolicy.KEEP);
        this.e.set(true);
    }

    public void onFailedToRegisterForRemoteNotifications(String str) {
        EventBus.sendEvent(new RegistrationErrorEvent(str));
    }

    public void onFailedToUnregisterFromRemoteNotifications(String str) {
        EventBus.sendEvent(new DeregistrationErrorEvent(str));
    }

    public void onRegisteredForRemoteNotifications(String str) {
        RepositoryModule.getRegistrationPreferences().pushToken().set(str);
        d.a(str);
    }

    public void onUnregisteredFromRemoteNotifications(String str) {
        this.a.clearSenderIdInfo();
        d.b(str);
    }
}
