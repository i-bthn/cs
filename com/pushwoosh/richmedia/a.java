package com.pushwoosh.richmedia;

import android.text.TextUtils;
import com.pushwoosh.inapp.event.b;
import com.pushwoosh.inapp.event.d;
import com.pushwoosh.inapp.k.c;
import com.pushwoosh.inapp.view.i.e;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.utils.PWLog;

public class a {
    private String a = a.class.getSimpleName();
    private e b;
    private b c;
    private RichMediaPresentingDelegate d;
    private c e;
    private RichMediaStyle f;

    public a(e eVar, b bVar, c cVar, RichMediaStyle richMediaStyle) {
        this.f = richMediaStyle;
        this.b = eVar;
        this.c = bVar;
        this.e = cVar;
        EventBus.subscribe(b.class, new EventListener() {
            /* class com.pushwoosh.richmedia.$$Lambda$a$jQPlS1iMvsAnjqulRNZeIgD8B4 */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                a.this.a((a) ((b) event));
            }
        });
        EventBus.subscribe(d.class, new EventListener() {
            /* class com.pushwoosh.richmedia.$$Lambda$a$fTjPtaE2DYkfN62xND49aFYiX8U */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                a.this.a((a) ((d) event));
            }
        });
        EventBus.subscribe(com.pushwoosh.inapp.event.c.class, new EventListener() {
            /* class com.pushwoosh.richmedia.$$Lambda$a$r_6Jqox9PWWFviA86gs1lkZ6HYA */

            @Override // com.pushwoosh.internal.event.EventListener
            public final void onReceive(Event event) {
                a.this.a((a) ((com.pushwoosh.inapp.event.c) event));
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(b bVar) {
        PWLog.noise(this.a, "handle close RichMedia");
        if (this.d != null) {
            PWLog.noise(this.a, "try use delegate onClose");
            com.pushwoosh.inapp.j.l.b a2 = bVar.a();
            if (a2 == null) {
                PWLog.error(this.a, "resource in event is null");
            } else if (!a(a2)) {
                this.d.onClose(this.c.a(a2));
            }
        } else {
            PWLog.noise(this.a, "delegate is null");
        }
    }

    /* access modifiers changed from: private */
    public void a(com.pushwoosh.inapp.event.c cVar) {
        if (cVar != null) {
            if (cVar.a() != null) {
                PWLog.error(cVar.a().getMessage());
            }
            PWLog.noise(this.a, "handle error RichMedia");
            if (this.d != null) {
                PWLog.noise(this.a, "try use delegate onError");
                com.pushwoosh.inapp.j.l.b b2 = cVar.b();
                if (b2 == null) {
                    PWLog.error(this.a, "resource in event is null");
                } else if (!a(b2)) {
                    this.d.onError(this.c.a(b2), cVar.a());
                }
            } else {
                PWLog.noise(this.a, "delegate is null");
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(d dVar) {
        PWLog.noise(this.a, "handle present RichMedia");
        if (this.d != null) {
            PWLog.noise(this.a, "try use delegate onPresent");
            com.pushwoosh.inapp.j.l.b a2 = dVar.a();
            if (a2 == null) {
                PWLog.error(this.a, "resource in event is null");
            } else if (!a(a2)) {
                this.d.onPresent(this.c.a(a2));
            }
        } else {
            PWLog.noise(this.a, "delegate is null");
        }
    }

    private boolean a(com.pushwoosh.inapp.j.l.b bVar) {
        if (!TextUtils.isEmpty(bVar.c())) {
            return false;
        }
        PWLog.noise(this.a, "code is empty, resource is not RichMedia, abort use delegate");
        return true;
    }

    private RichMedia b(com.pushwoosh.inapp.view.i.h.b bVar) {
        return this.c.a(bVar);
    }

    private boolean c(com.pushwoosh.inapp.view.i.h.b bVar) {
        String str;
        String str2;
        com.pushwoosh.inapp.j.l.b b2 = bVar.b();
        if (b2 == null) {
            str = this.a;
            str2 = "resource is null, abort show RichMedia";
        } else if (bVar.c() != com.pushwoosh.inapp.view.i.h.a.IN_APP || b2.m() || this.e.b(b2.c())) {
            return false;
        } else {
            str = this.a;
            str2 = "resource is not downloaded, abort show RichMedia";
        }
        PWLog.error(str, str2);
        return true;
    }

    private boolean d(com.pushwoosh.inapp.view.i.h.b bVar) {
        return bVar.c() != com.pushwoosh.inapp.view.i.h.a.REMOTE_URL;
    }

    private void e(com.pushwoosh.inapp.view.i.h.b bVar) {
        if (!c(bVar) && this.d.shouldPresent(b(bVar))) {
            this.b.b(bVar);
        }
    }

    public RichMediaStyle a() {
        return this.f;
    }

    public void a(com.pushwoosh.inapp.view.i.h.b bVar) {
        if (this.d == null || !d(bVar)) {
            this.b.b(bVar);
        } else {
            e(bVar);
        }
    }

    public void a(RichMedia richMedia) {
        PWLog.noise(this.a, "try show richMedia");
        if (richMedia != null) {
            String str = this.a;
            PWLog.noise(str, "showRichMedia with content:" + richMedia.getContent());
            this.b.b(richMedia.a());
            return;
        }
        PWLog.error(this.a, "richMedia is null");
    }

    public void a(RichMediaPresentingDelegate richMediaPresentingDelegate) {
        this.d = richMediaPresentingDelegate;
    }
}
