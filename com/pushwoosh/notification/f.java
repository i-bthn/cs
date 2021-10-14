package com.pushwoosh.notification;

import com.pushwoosh.exception.UnregisterForPushNotificationException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.event.Subscription;
import com.pushwoosh.notification.event.DeregistrationErrorEvent;
import com.pushwoosh.notification.event.DeregistrationSuccessEvent;

/* access modifiers changed from: package-private */
public final class f {
    private final Callback<String, UnregisterForPushNotificationException> a;
    private Subscription<DeregistrationSuccessEvent> b;
    private Subscription<DeregistrationErrorEvent> c;

    private f(Callback<String, UnregisterForPushNotificationException> callback) {
        this.a = callback;
    }

    private void a() {
        this.b = EventBus.subscribe(DeregistrationSuccessEvent.class, new EventListener() {
            /* class com.pushwoosh.notification.$$Lambda$f$ReCBDMQhJ4eR44se9_KZ1g5er_Q */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                f.this.a((f) ((DeregistrationSuccessEvent) event));
            }
        });
        this.c = EventBus.subscribe(DeregistrationErrorEvent.class, new EventListener() {
            /* class com.pushwoosh.notification.$$Lambda$f$5JW5nthUIlHPuaoCIsWkAv6QeyA */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                f.this.a((f) ((DeregistrationErrorEvent) event));
            }
        });
    }

    public static void a(Callback<String, UnregisterForPushNotificationException> callback) {
        if (callback != null) {
            new f(callback).a();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DeregistrationErrorEvent deregistrationErrorEvent) {
        b();
        this.a.process(Result.fromException(new UnregisterForPushNotificationException((String) deregistrationErrorEvent.getData())));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DeregistrationSuccessEvent deregistrationSuccessEvent) {
        b();
        this.a.process(Result.fromData(deregistrationSuccessEvent.getData()));
    }

    private void b() {
        Subscription<DeregistrationSuccessEvent> subscription = this.b;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Subscription<DeregistrationErrorEvent> subscription2 = this.c;
        if (subscription2 != null) {
            subscription2.unsubscribe();
        }
    }
}
