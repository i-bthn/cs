package com.pushwoosh.notification;

import com.pushwoosh.exception.RegisterForPushNotificationsException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.event.Subscription;
import com.pushwoosh.notification.event.RegistrationErrorEvent;
import com.pushwoosh.notification.event.RegistrationSuccessEvent;

public final class e {
    private final Callback<String, RegisterForPushNotificationsException> a;
    private Subscription<RegistrationSuccessEvent> b;
    private Subscription<RegistrationErrorEvent> c;

    private e(Callback<String, RegisterForPushNotificationsException> callback) {
        this.a = callback;
    }

    private void a() {
        this.b = EventBus.subscribe(RegistrationSuccessEvent.class, new EventListener() {
            /* class com.pushwoosh.notification.$$Lambda$e$0guPpufTMzkMAfV74kTkbYJwhA8 */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                e.this.a((e) ((RegistrationSuccessEvent) event));
            }
        });
        this.c = EventBus.subscribe(RegistrationErrorEvent.class, new EventListener() {
            /* class com.pushwoosh.notification.$$Lambda$e$Ua_EhLO_rQd6k3vjqomHu1AzkBQ */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                e.this.a((e) ((RegistrationErrorEvent) event));
            }
        });
    }

    public static void a(Callback<String, RegisterForPushNotificationsException> callback) {
        if (callback != null) {
            new e(callback).a();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(RegistrationErrorEvent registrationErrorEvent) {
        b();
        this.a.process(Result.fromException(new RegisterForPushNotificationsException((String) registrationErrorEvent.getData())));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(RegistrationSuccessEvent registrationSuccessEvent) {
        b();
        this.a.process(Result.fromData(registrationSuccessEvent.getData()));
    }

    private void b() {
        Subscription<RegistrationSuccessEvent> subscription = this.b;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Subscription<RegistrationErrorEvent> subscription2 = this.c;
        if (subscription2 != null) {
            subscription2.unsubscribe();
        }
    }
}
