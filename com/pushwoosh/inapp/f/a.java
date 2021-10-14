package com.pushwoosh.inapp.f;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.inapp.event.b;
import com.pushwoosh.inapp.f.a;
import com.pushwoosh.inapp.view.d;
import com.pushwoosh.inapp.view.e;
import com.pushwoosh.inapp.view.i.h.b;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.h;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class a {
    private String a;
    private long b;
    private d c;
    private SharedPreferences d;
    private e<String> e = new e<>(this, null);
    private Date f;
    private String g;
    private h h;
    private b i;

    /* renamed from: com.pushwoosh.inapp.f.a$a */
    public class RunnableC0010a implements Runnable {
        RunnableC0010a() {
            a.this = r1;
        }

        public void run() {
            if (a.this.i != null) {
                a.this.i.a(c.RICHMEDIA_CLOSED);
                a.this.i = null;
            }
        }
    }

    public interface b {
        void a(c cVar);
    }

    public interface c<T extends Event> {
        boolean onReceive(T t);
    }

    public interface d {
        boolean check();
    }

    public class e<T> {
        private T a;
        private g<T> b;

        private e(a aVar) {
        }

        /* synthetic */ e(a aVar, RunnableC0010a aVar2) {
            this(aVar);
        }

        public void a(g<T> gVar) {
            synchronized (this) {
                if (this.a != null) {
                    gVar.a(this.a);
                } else {
                    this.b = gVar;
                }
            }
        }

        public void a(T t) {
            synchronized (this) {
                if (this.b != null) {
                    this.b.a(t);
                    this.b = null;
                }
                this.a = t;
            }
        }
    }

    public class f<T extends Event> implements EventListener<T> {
        private c<T> a;
        private Class<T> b;
        private f c;

        f(a aVar, Class<T> cls, c<T> cVar) {
            this.a = cVar;
            this.b = cls;
            EventBus.subscribe(this.b, this);
        }

        public void a() {
            f fVar = this.c;
            if (fVar != null) {
                if (fVar.c == this) {
                    fVar.a(null);
                }
                this.c.a();
            }
            EventBus.unsubscribe(this.b, this);
        }

        public void a(f fVar) {
            this.c = fVar;
        }

        @Override // com.pushwoosh.internal.event.EventListener
        public void onReceive(T t) {
            if (this.a.onReceive(t)) {
                a();
            }
        }
    }

    public interface g<T> {
        void a(T t);
    }

    public a(String str, float f2, SharedPreferences sharedPreferences, d dVar, h hVar) {
        this.a = str;
        this.b = (long) (f2 * 8.64E7f);
        this.d = sharedPreferences;
        this.c = dVar;
        this.h = hVar;
    }

    /* access modifiers changed from: public */
    private /* synthetic */ void a(Handler handler, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, b bVar, String str) {
        handler.post(new Runnable(atomicBoolean, atomicBoolean2, str, bVar) {
            /* class com.pushwoosh.inapp.f.$$Lambda$a$vMQDTAZCM8eUSkFW_E3ABU0JbxU */
            private final /* synthetic */ AtomicBoolean f$1;
            private final /* synthetic */ AtomicBoolean f$2;
            private final /* synthetic */ String f$3;
            private final /* synthetic */ a.b f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void run() {
                a.lambda$vMQDTAZCM8eUSkFW_E3ABU0JbxU(a.this, this.f$1, this.f$2, this.f$3, this.f$4);
            }
        });
    }

    /* access modifiers changed from: public */
    private void a(com.pushwoosh.inapp.event.b bVar) {
        com.pushwoosh.inapp.j.l.b a2 = bVar.a();
        if (a2 == null) {
            PWLog.error("BusinessCase", "resource in event is null");
        } else if (TextUtils.equals(a2.c(), this.g)) {
            EventBus.unsubscribe(com.pushwoosh.inapp.event.b.class, new EventListener() {
                /* class com.pushwoosh.inapp.f.$$Lambda$a$OefiKKPtoiK0Bzw7YVSQQJ623gY */

                @Override // com.pushwoosh.internal.event.EventListener
                public final void onReceive(Event event) {
                    a.lambda$OefiKKPtoiK0Bzw7YVSQQJ623gY(a.this, (b) event);
                }
            });
            new Handler().postDelayed(new RunnableC0010a(), 1000);
        }
    }

    public static /* synthetic */ void a(AtomicBoolean atomicBoolean, b bVar, AtomicBoolean atomicBoolean2) {
        if (!atomicBoolean.get()) {
            if (bVar != null) {
                bVar.a(c.LOADING_FAILED);
            }
            atomicBoolean2.set(true);
        }
    }

    /* access modifiers changed from: public */
    private /* synthetic */ void a(AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, String str, b bVar) {
        if (atomicBoolean.get()) {
            PWLog.debug("BusinessCase", this.a + " timeout Exceeded");
            return;
        }
        atomicBoolean2.set(true);
        com.pushwoosh.inapp.j.l.b bVar2 = null;
        com.pushwoosh.inapp.k.d d2 = com.pushwoosh.inapp.b.d();
        if (d2 != null) {
            bVar2 = d2.a(str);
        }
        if (bVar2 != null) {
            if (bVar != null) {
                f fVar = new f(this, com.pushwoosh.inapp.view.d.class, new c(str) {
                    /* class com.pushwoosh.inapp.f.$$Lambda$a$1dS4KoQ8vt7s_TVFTGuJxPlXQs */
                    private final /* synthetic */ String f$0;

                    {
                        this.f$0 = r1;
                    }

                    @Override // com.pushwoosh.inapp.f.a.c
                    public final boolean onReceive(Event event) {
                        return a.m9lambda$1dS4KoQ8vt7s_TVFTGuJxPlXQs(this.f$0, (d) event);
                    }
                });
                f fVar2 = new f(this, com.pushwoosh.inapp.view.e.class, new c(str, bVar) {
                    /* class com.pushwoosh.inapp.f.$$Lambda$a$q4Qe3id05QGOeAM6m5qgUXngOQ4 */
                    private final /* synthetic */ String f$0;
                    private final /* synthetic */ a.b f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    @Override // com.pushwoosh.inapp.f.a.c
                    public final boolean onReceive(Event event) {
                        return a.lambda$q4Qe3id05QGOeAM6m5qgUXngOQ4(this.f$0, this.f$1, (e) event);
                    }
                });
                fVar.a(fVar2);
                fVar2.a(fVar);
            }
            com.pushwoosh.inapp.view.i.h.b a2 = new b.C0014b().a(bVar2).a();
            this.g = bVar2.c();
            this.i = bVar;
            EventBus.subscribe(com.pushwoosh.inapp.event.b.class, new EventListener() {
                /* class com.pushwoosh.inapp.f.$$Lambda$a$OefiKKPtoiK0Bzw7YVSQQJ623gY */

                @Override // com.pushwoosh.internal.event.EventListener
                public final void onReceive(Event event) {
                    a.lambda$OefiKKPtoiK0Bzw7YVSQQJ623gY(a.this, (com.pushwoosh.inapp.event.b) event);
                }
            });
            com.pushwoosh.richmedia.a h2 = PushwooshPlatform.getInstance().h();
            if (h2 != null) {
                h2.a(a2);
            }
            c();
        } else if (bVar != null) {
            bVar.a(c.LOADING_FAILED);
        }
    }

    public static /* synthetic */ boolean a(String str, b bVar, com.pushwoosh.inapp.view.e eVar) {
        if (!eVar.a().c().equals(str)) {
            return false;
        }
        bVar.a(c.LOADING_FAILED);
        return true;
    }

    public static /* synthetic */ boolean a(String str, com.pushwoosh.inapp.view.d dVar) {
        return dVar.a().c().equals(str);
    }

    private boolean b() {
        if (this.b == 0) {
            return false;
        }
        long j = this.d.getLong(this.a, Long.MIN_VALUE);
        if (j != Long.MIN_VALUE) {
            this.f = new Date(j);
        }
        return this.f != null && this.h.b() - this.f.getTime() < this.b;
    }

    private void c() {
        this.d.edit().putLong(this.a, this.h.b()).apply();
    }

    public String a() {
        return this.a;
    }

    public void a(b bVar) {
        PWLog.debug("[BusinessCase]", "trigger " + this.a);
        if (!this.c.check()) {
            if (bVar != null) {
                bVar.a(c.CONDITION_NOT_SATISFIED);
                return;
            }
            PWLog.debug("BusinessCase", this.a + " condition not satisfied");
        } else if (!b()) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);
            Looper myLooper = Looper.myLooper();
            if (myLooper == null) {
                PWLog.debug("BusinessCase", "Looper is null. Using MainLooper instead, which will cause StrictMode policy violation");
            }
            if (myLooper == null) {
                myLooper = Looper.getMainLooper();
            }
            Handler handler = new Handler(myLooper);
            handler.postDelayed(new Runnable(atomicBoolean2, bVar, atomicBoolean) {
                /* class com.pushwoosh.inapp.f.$$Lambda$a$qCb0Pyb8Qrzu2CtVmkrEeLg1C0 */
                private final /* synthetic */ AtomicBoolean f$0;
                private final /* synthetic */ a.b f$1;
                private final /* synthetic */ AtomicBoolean f$2;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    a.m10lambda$qCb0Pyb8Qrzu2CtVmkrEeLg1C0(this.f$0, this.f$1, this.f$2);
                }
            }, 4000);
            this.e.a(new g(handler, atomicBoolean, atomicBoolean2, bVar) {
                /* class com.pushwoosh.inapp.f.$$Lambda$a$_H6USHQSbmZaPFrIwa9bti_MxMs */
                private final /* synthetic */ Handler f$1;
                private final /* synthetic */ AtomicBoolean f$2;
                private final /* synthetic */ AtomicBoolean f$3;
                private final /* synthetic */ a.b f$4;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                }

                @Override // com.pushwoosh.inapp.f.a.g
                public final void a(Object obj) {
                    a.lambda$_H6USHQSbmZaPFrIwa9bti_MxMs(a.this, this.f$1, this.f$2, this.f$3, this.f$4, (String) obj);
                }
            });
        } else if (bVar != null) {
            bVar.a(c.TRIGGER_CAP_EXCEEDED);
        } else {
            PWLog.debug("BusinessCase", this.a + " trigger cap exceeded");
        }
    }

    public void a(String str) {
        this.e.a(str);
    }
}
