package com.pushwoosh.e.a.a.a.o;

import com.pushwoosh.e.a.a.a.o.b.b;
import com.pushwoosh.e.a.a.a.o.b.c;
import com.pushwoosh.e.a.a.a.o.b.d;
import com.pushwoosh.e.a.a.a.o.b.e;
import com.pushwoosh.e.a.a.a.o.b.f;
import com.pushwoosh.e.a.a.a.o.b.g;
import com.pushwoosh.e.a.a.a.o.b.h;
import com.pushwoosh.e.a.a.a.o.b.i;
import com.pushwoosh.e.a.a.a.o.b.j;
import com.pushwoosh.e.a.a.a.o.b.k;
import com.pushwoosh.e.a.a.a.o.b.l;
import java.util.HashSet;
import java.util.Set;

public final class a {
    private final com.pushwoosh.e.a.a.a.o.b.a a = new com.pushwoosh.e.a.a.a.o.b.a();
    private final c b = new c();
    private final b c = new b();
    private final d d = new d();
    private final e e = new e();
    private final f f = new f();
    private final g g = new g();
    private final h h = new h();
    private final j i = new j();
    private final k j = new k();
    private final l k = new l();
    private final i l;

    public a(com.pushwoosh.e.a.a.a.o.b.m.b bVar) {
        this.l = new i(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, bVar);
    }

    public com.pushwoosh.e.a.a.a.o.b.a a() {
        return this.a;
    }

    public Object a(Object obj) {
        return obj instanceof com.pushwoosh.e.a.a.a.o.b.m.a ? ((com.pushwoosh.e.a.a.a.o.b.m.a) obj).a() : obj instanceof Set ? new HashSet((Set) obj) : obj;
    }

    public Object a(String str, byte[] bArr) {
        if (bArr.length != 0) {
            byte b2 = bArr[0];
            if (this.a.a(b2)) {
                return Boolean.valueOf(this.a.a(bArr));
            }
            if (this.g.a(b2)) {
                return Integer.valueOf(this.g.a(bArr));
            }
            if (this.h.a(b2)) {
                return Long.valueOf(this.h.a(bArr));
            }
            if (this.e.a(b2)) {
                return Double.valueOf(this.e.a(bArr));
            }
            if (this.f.a(b2)) {
                return Float.valueOf(this.f.a(bArr));
            }
            if (this.j.a(b2)) {
                return this.j.a(bArr);
            }
            if (this.k.a(b2)) {
                return this.k.a(bArr);
            }
            if (this.l.a(b2)) {
                return this.l.a(str, bArr);
            }
            if (this.i.a(b2)) {
                return Short.valueOf(this.i.a(bArr));
            }
            if (this.b.a(b2)) {
                return Byte.valueOf(this.b.a(bArr));
            }
            if (this.c.a(b2)) {
                return this.c.a(bArr);
            }
            if (this.d.a(b2)) {
                return Character.valueOf(this.d.a(bArr));
            }
            throw new UnsupportedClassVersionError(String.format("Flag verification failed. Incorrect flag '%s'", Byte.valueOf(b2)));
        }
        throw new com.pushwoosh.e.a.a.a.j.d(String.format("%s key's value is zero bytes for deserialize", str));
    }

    public f b() {
        return this.f;
    }

    public g c() {
        return this.g;
    }

    public h d() {
        return this.h;
    }

    public k e() {
        return this.j;
    }

    public l f() {
        return this.k;
    }
}
