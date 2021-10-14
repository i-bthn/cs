package com.pushwoosh.e.a.a.a.o.b.m.c;

import com.pushwoosh.e.a.a.a.o.b.a;
import com.pushwoosh.e.a.a.a.o.b.c;
import com.pushwoosh.e.a.a.a.o.b.d;
import com.pushwoosh.e.a.a.a.o.b.e;
import com.pushwoosh.e.a.a.a.o.b.f;
import com.pushwoosh.e.a.a.a.o.b.g;
import com.pushwoosh.e.a.a.a.o.b.h;
import com.pushwoosh.e.a.a.a.o.b.j;
import com.pushwoosh.e.a.a.a.o.b.k;

public final class b implements a {
    private final g a;
    private final com.pushwoosh.e.a.a.a.o.b.m.b b;
    private int c = 0;
    private byte[] d;
    private String e;

    public b(a aVar, c cVar, com.pushwoosh.e.a.a.a.o.b.b bVar, d dVar, e eVar, f fVar, g gVar, h hVar, j jVar, k kVar, com.pushwoosh.e.a.a.a.o.b.m.b bVar2) {
        this.a = gVar;
        this.b = bVar2;
    }

    private com.pushwoosh.e.a.a.a.o.b.m.a a(Class<? extends com.pushwoosh.e.a.a.a.o.b.m.a> cls) {
        try {
            return (com.pushwoosh.e.a.a.a.o.b.m.a) cls.newInstance();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    private void a(int i) {
        int i2 = this.c + i;
        int length = this.d.length;
        if (i2 > length) {
            throw new ArrayIndexOutOfBoundsException(String.format("Can't read out of bounds array (expected size: %s bytes > disk size: %s bytes) for %s! keyMay be your read/write contract isn't mirror-implemented or old disk version is not backward compatible with new class version?", this.e, Integer.valueOf(i2), Integer.valueOf(length)));
        }
    }

    private void b() {
        if (this.d.length == 0) {
            throw new UnsupportedOperationException(String.format("Cannot deserialize empty byte array for %s key! May be your read/write contract isn't mirror-implemented or old disk version is not backward compatible with new class version?", this.e));
        }
    }

    private com.pushwoosh.e.a.a.a.o.b.m.a c() {
        com.pushwoosh.e.a.a.a.o.b.m.a a2 = a(this.b.a(this.e));
        a2.a(this);
        return a2;
    }

    private void d() {
        this.c++;
    }

    public int a() {
        int a2 = this.a.a();
        a(a2);
        byte b2 = this.d[this.c];
        if (this.a.a(b2)) {
            int a3 = this.a.a(this.d, this.c);
            this.c += a2;
            return a3;
        }
        throw new ClassCastException(String.format("int cannot be deserialized in '%s' flag type", Byte.valueOf(b2)));
    }

    @Override // com.pushwoosh.e.a.a.a.o.b.m.c.a
    public com.pushwoosh.e.a.a.a.o.b.m.a a(String str, byte[] bArr) {
        this.c = 0;
        this.e = str;
        this.d = bArr;
        b();
        d();
        a();
        return c();
    }
}
