package com.pushwoosh.internal.platform;

import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.platform.a;
import java.util.concurrent.atomic.AtomicBoolean;

/* access modifiers changed from: package-private */
public class b {
    private final AtomicBoolean a = new AtomicBoolean(false);

    b() {
        EventBus.subscribe(a.c.class, new EventListener() {
            /* class com.pushwoosh.internal.platform.$$Lambda$b$jO28UTeMBDf7ACeHR8IMAUNWauE */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                b.this.a((b) ((a.c) event));
            }
        });
        EventBus.subscribe(a.b.class, new EventListener() {
            /* class com.pushwoosh.internal.platform.$$Lambda$b$i3yWC2HDFnKuhvOwqUkqqro1e1s */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                b.this.a((b) ((a.b) event));
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(a.b bVar) {
        this.a.set(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(a.c cVar) {
        this.a.set(true);
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.a.get();
    }
}
