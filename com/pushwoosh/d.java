package com.pushwoosh;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.pushwoosh.BootReceiver;
import com.pushwoosh.internal.Plugin;
import com.pushwoosh.internal.event.ConfigLoadedEvent;
import com.pushwoosh.internal.event.Emitter;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.event.InitHwidEvent;
import com.pushwoosh.internal.event.Subscription;
import com.pushwoosh.internal.platform.a;
import com.pushwoosh.internal.specific.DeviceSpecificProvider;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.b;
import com.pushwoosh.internal.utils.c;
import com.pushwoosh.notification.PushwooshNotificationManager;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.g;
import com.pushwoosh.repository.o;
import com.pushwoosh.secure.crypt.manager.RsaDecryptorManager;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class d {
    public static final String p = "d";
    private final AtomicBoolean a = new AtomicBoolean(false);
    private final AtomicBoolean b = new AtomicBoolean(false);
    private final AtomicReference<String> c = new AtomicReference<>("");
    private final AtomicReference<String> d = new AtomicReference<>("");
    private final AtomicBoolean e = new AtomicBoolean(false);
    private final c f;
    private final RegistrationPrefs g;
    private final g h;
    private final b i;
    private final o j;
    private final PushwooshNotificationManager k;
    private final com.pushwoosh.inapp.c l;
    private final com.pushwoosh.repository.d m;
    private final com.pushwoosh.appevents.b n;
    private final com.pushwoosh.internal.b o;

    /* access modifiers changed from: package-private */
    public class a implements EventListener<ConfigLoadedEvent> {
        a() {
        }

        /* renamed from: a */
        public void onReceive(ConfigLoadedEvent configLoadedEvent) {
            EventBus.unsubscribe(ConfigLoadedEvent.class, this);
            d.this.j.n();
        }
    }

    public d(c cVar, RegistrationPrefs registrationPrefs, g gVar, b bVar, o oVar, PushwooshNotificationManager pushwooshNotificationManager, com.pushwoosh.inapp.c cVar2, com.pushwoosh.repository.d dVar, com.pushwoosh.appevents.b bVar2, com.pushwoosh.internal.b bVar3) {
        this.f = cVar;
        this.g = registrationPrefs;
        this.h = gVar;
        this.i = bVar;
        this.j = oVar;
        this.k = pushwooshNotificationManager;
        this.l = cVar2;
        this.m = dVar;
        this.n = bVar2;
        this.o = bVar3;
    }

    private void a(Pair<String, String> pair) {
        if (this.a.get()) {
            this.i.d();
            if (this.b.get()) {
                this.h.a((String) pair.first, (String) pair.second);
                this.j.m();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(BootReceiver.DeviceBootedEvent deviceBootedEvent) {
        this.k.f();
    }

    private void a(Subscription<a.d> subscription, Subscription<PushwooshNotificationManager.b> subscription2) {
        PWLog.debug("initHwid");
        com.pushwoosh.internal.platform.utils.a.a(new com.pushwoosh.internal.platform.utils.b(subscription, subscription2) {
            /* class com.pushwoosh.$$Lambda$d$a6iPtmuI4gyb0WXNBtHny5MJg */
            private final /* synthetic */ Subscription f$1;
            private final /* synthetic */ Subscription f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.pushwoosh.internal.platform.utils.b
            public final void a(String str) {
                d.this.a((d) this.f$1, this.f$2, (Subscription) str);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(a.d dVar) {
        this.a.set(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(PushwooshNotificationManager.b bVar) {
        this.b.set(true);
    }

    /* access modifiers changed from: private */
    public void a(String str, Subscription<a.d> subscription, Subscription<PushwooshNotificationManager.b> subscription2) {
        this.d.set(this.g.hwid().get());
        this.c.set(str);
        this.g.hwid().set(this.c.get());
        h();
        EventBus.sendEvent(new InitHwidEvent(this.c.get()));
        a(new Pair<>(this.c.get(), this.d.get()));
        i();
        subscription.unsubscribe();
        subscription2.unsubscribe();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(a.d dVar) {
        d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(PushwooshNotificationManager.b bVar) {
        f();
    }

    private void c() {
        Log.i(RsaDecryptorManager.ALIAS, "HWID: " + this.g.hwid().get());
        PWLog.debug("PushwooshModule", "onApplicationCreated");
        PWLog.info(p, String.format("This is %s device", DeviceSpecificProvider.getInstance().type()));
        for (Plugin plugin : this.f.c()) {
            plugin.init();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(a.d dVar) {
        e();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(PushwooshNotificationManager.b bVar) {
        g();
    }

    private void d() {
        PWLog.debug("onAppOpen");
        this.i.d();
        this.a.set(true);
        if (this.b.get()) {
            g();
        }
    }

    private void e() {
        PWLog.debug("onAppReady");
        if (this.a.get()) {
            g();
        }
    }

    private void f() {
        if (this.e.compareAndSet(false, true)) {
            j();
            this.j.j();
            this.m.a();
            this.l.a();
        }
    }

    private void g() {
        PWLog.debug("sendAppOpenEndTagMigrate");
        if (!this.c.get().isEmpty()) {
            this.h.a(this.c.get(), this.d.get());
            this.j.m();
        }
    }

    private void h() {
        if (TextUtils.isEmpty(this.g.userId().get())) {
            this.l.b(this.g.hwid().get());
        }
    }

    private void i() {
        EventBus.subscribe(a.d.class, new EventListener() {
            /* class com.pushwoosh.$$Lambda$d$F1JOw2j6Hzn2rrNnBJlbrVALsg */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                d.this.b((d) ((a.d) event));
            }
        });
        PWLog.debug("appOpen:" + this.a.get() + " onAppReady:" + this.b.get());
        if (this.a.get()) {
            EventBus.subscribe(PushwooshNotificationManager.b.class, new EventListener() {
                /* class com.pushwoosh.$$Lambda$d$GhT0SYvAG_XuQFF7sCegbs45F0 */

                @Override // com.pushwoosh.internal.event.EventListener
                public final void onReceive(Event event) {
                    d.this.c((d) ((PushwooshNotificationManager.b) event));
                }
            });
        } else {
            Emitter.when(Emitter.forEvent(a.d.class), Emitter.forEvent(PushwooshNotificationManager.b.class)).bind(new EventListener() {
                /* class com.pushwoosh.$$Lambda$d$7qAy23eltSEE_TCyED3G9Li_1Ko */

                @Override // com.pushwoosh.internal.event.EventListener
                public final void onReceive(Event event) {
                    d.this.c((d) ((a.d) event));
                }
            });
        }
        EventBus.subscribe(BootReceiver.DeviceBootedEvent.class, new EventListener() {
            /* class com.pushwoosh.$$Lambda$d$cXcagtgoGkJBYkC_CB8AY9p2ZBA */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                d.this.a((d) ((BootReceiver.DeviceBootedEvent) event));
            }
        });
    }

    private void j() {
        EventBus.subscribe(ConfigLoadedEvent.class, new a());
    }

    public void a() {
        PWLog.init();
        Subscription<a.d> subscribe = EventBus.subscribe(a.d.class, new EventListener() {
            /* class com.pushwoosh.$$Lambda$d$bmYzCcm2wNIvEpKbo0s6YkvbiY */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                d.this.a((d) ((a.d) event));
            }
        });
        Subscription<PushwooshNotificationManager.b> subscribe2 = EventBus.subscribe(PushwooshNotificationManager.b.class, new EventListener() {
            /* class com.pushwoosh.$$Lambda$d$9RMcRfK0hvVn6IUtkAZOXED0Mqg */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                d.this.a((d) ((PushwooshNotificationManager.b) event));
            }
        });
        Emitter.when(Emitter.forEvent(PushwooshNotificationManager.b.class), Emitter.forEvent(InitHwidEvent.class)).bind(new EventListener() {
            /* class com.pushwoosh.$$Lambda$d$_n6fOl5_K7dMyyiWPaMLAAdgIRg */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                d.this.b((d) ((PushwooshNotificationManager.b) event));
            }
        });
        if (!this.o.b()) {
            this.k.d();
        }
        this.k.e();
        a(subscribe, subscribe2);
        c();
        this.n.a();
    }

    public void b() {
        this.e.set(false);
    }
}
