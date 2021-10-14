package com.pushwoosh.richmedia;

import com.pushwoosh.inapp.view.i.h.b;
import com.pushwoosh.internal.utils.PWLog;

public class RichMedia {
    private final String a = RichMedia.class.getSimpleName();
    private String b;
    private Source c;
    private b d;
    private boolean e;
    private boolean f;

    public enum Source {
        PushMessageSource,
        InAppSource
    }

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a = new int[com.pushwoosh.inapp.view.i.h.a.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            a[com.pushwoosh.inapp.view.i.h.a.IN_APP.ordinal()] = 1;
            a[com.pushwoosh.inapp.view.i.h.a.RICH_MEDIA.ordinal()] = 2;
            try {
                a[com.pushwoosh.inapp.view.i.h.a.REMOTE_URL.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    RichMedia(b bVar) {
        Source source;
        this.d = bVar;
        com.pushwoosh.inapp.j.l.b b2 = bVar.b();
        this.e = this.d.e();
        if (b2 == null) {
            PWLog.error(this.a, "resource is empty");
            return;
        }
        this.f = b2.m();
        this.e = this.d.e();
        int i = a.a[this.d.c().ordinal()];
        if (i == 1) {
            this.b = b2.c();
            source = Source.InAppSource;
        } else if (i == 2) {
            this.b = b2.c();
            source = Source.PushMessageSource;
        } else if (i == 3) {
            throw new IllegalArgumentException("ResourceType can not equals REMOTE URL");
        } else {
            return;
        }
        this.c = source;
    }

    /* access modifiers changed from: package-private */
    public b a() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RichMedia.class != obj.getClass()) {
            return false;
        }
        RichMedia richMedia = (RichMedia) obj;
        return this.b.equals(richMedia.b) && this.c == richMedia.c;
    }

    public String getContent() {
        return this.b;
    }

    public Source getSource() {
        return this.c;
    }

    public int hashCode() {
        return (this.b.hashCode() * 31) + this.c.hashCode();
    }

    public boolean isLockScreen() {
        return this.e;
    }

    public boolean isRequired() {
        return this.f;
    }

    public String toString() {
        return "RichMedia{content='" + this.b + '\'' + ", resourceType=" + this.c + '}';
    }
}
