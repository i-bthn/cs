package com.pushwoosh.internal.event;

import com.pushwoosh.internal.event.Emitter;
import com.pushwoosh.internal.event.Event;
import java.util.LinkedList;
import java.util.List;

public abstract class Emitter<T extends Event> {
    protected EventListener<T> listener;

    /* access modifiers changed from: package-private */
    public static class a extends Emitter<T> {
        boolean a = false;
        List<T> b = new LinkedList();
        final /* synthetic */ Emitter c;
        final /* synthetic */ Emitter d;

        a(Emitter emitter, Emitter emitter2) {
            this.c = emitter;
            this.d = emitter2;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(Event event) {
            if (this.a) {
                emit(event);
            } else {
                this.b.add(event);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.pushwoosh.internal.event.Emitter$a */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: private */
        public /* synthetic */ void b(Event event) {
            this.a = true;
            for (T t : this.b) {
                emit(t);
            }
        }

        @Override // com.pushwoosh.internal.event.Emitter
        public void bind(EventListener<T> eventListener) {
            Emitter.super.bind(eventListener);
            this.c.bind(new EventListener() {
                /* class com.pushwoosh.internal.event.$$Lambda$Emitter$a$f3XGCY9y5us6ZDcZ0F166vEnPxw */

                @Override // com.pushwoosh.internal.event.EventListener
                public final void onReceive(Event event) {
                    Emitter.a.this.a(event);
                }
            });
            this.d.bind(new EventListener() {
                /* class com.pushwoosh.internal.event.$$Lambda$Emitter$a$k3VjIbNk83JiDbf_pqAqrv0dyiM */

                @Override // com.pushwoosh.internal.event.EventListener
                public final void onReceive(Event event) {
                    Emitter.a.this.b(event);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public static class b extends Emitter<T> {
        final /* synthetic */ Class a;

        b(Class cls) {
            this.a = cls;
        }

        @Override // com.pushwoosh.internal.event.Emitter
        public void bind(EventListener<T> eventListener) {
            Emitter.super.bind(eventListener);
            EventBus.subscribe(this.a, eventListener);
        }

        @Override // com.pushwoosh.internal.event.Emitter
        public void unbind() {
            EventBus.unsubscribe(this.a, this.listener);
            Emitter.super.unbind();
        }
    }

    public static <T extends Event> Emitter<T> forEvent(Class<T> cls) {
        return new b(cls);
    }

    public static <T extends Event> Emitter<T> when(Emitter<T> emitter, Emitter<?> emitter2) {
        return new a(emitter, emitter2);
    }

    public void bind(EventListener<T> eventListener) {
        this.listener = eventListener;
    }

    /* access modifiers changed from: protected */
    public void emit(T t) {
        EventListener<T> eventListener = this.listener;
        if (eventListener != null) {
            eventListener.onReceive(t);
        }
    }

    public void unbind() {
        this.listener = null;
    }
}
