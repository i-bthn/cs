package com.pushwoosh.inapp.view.i.h;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;

public class b {
    private final com.pushwoosh.inapp.j.l.b a;
    private final String b;
    private final boolean c;
    private final a d;
    private long e;

    /* renamed from: com.pushwoosh.inapp.view.i.h.b$b  reason: collision with other inner class name */
    public static class C0014b {
        private com.pushwoosh.inapp.j.l.b a;
        private String b = "";
        private boolean c = false;
        private a d = a.IN_APP;
        private long e = 0;

        public C0014b a(long j) {
            this.e = j;
            return this;
        }

        public C0014b a(com.pushwoosh.inapp.j.l.b bVar) {
            this.a = bVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        public C0014b a(a aVar) {
            this.d = aVar;
            return this;
        }

        public C0014b a(String str) {
            return str == null ? this : a(new com.pushwoosh.inapp.j.l.b(str)).a(a.REMOTE_URL);
        }

        public C0014b a(boolean z) {
            this.c = z;
            return this;
        }

        public b a() {
            return new b(this.a, this.b, this.c, this.d, this.e);
        }

        public C0014b b(String str) {
            if (str == null) {
                return this;
            }
            try {
                return a(com.pushwoosh.inapp.j.l.b.a(str)).a(a.RICH_MEDIA);
            } catch (com.pushwoosh.inapp.g.a e2) {
                PWLog.error("Can't parse richMedia: " + str, e2);
                return this;
            }
        }

        public C0014b c(String str) {
            this.b = str;
            return this;
        }
    }

    private b(@Nullable com.pushwoosh.inapp.j.l.b bVar, @Nullable String str, boolean z, @NonNull a aVar, long j) {
        this.a = bVar;
        this.b = str;
        this.c = z;
        this.d = aVar;
        this.e = j;
    }

    public long a() {
        return this.e;
    }

    @Nullable
    public com.pushwoosh.inapp.j.l.b b() {
        return this.a;
    }

    @NonNull
    public a c() {
        return this.d;
    }

    @Nullable
    public String d() {
        return this.b;
    }

    public boolean e() {
        return this.c;
    }
}
