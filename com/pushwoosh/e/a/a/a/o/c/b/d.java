package com.pushwoosh.e.a.a.a.o.c.b;

import com.pushwoosh.e.a.a.a.o.b.h;
import com.pushwoosh.e.a.a.a.o.c.a;

public final class d implements a {
    private final long a;
    private final h b;

    public d(long j, com.pushwoosh.e.a.a.a.o.a aVar) {
        this.a = j;
        this.b = aVar.d();
    }

    @Override // com.pushwoosh.e.a.a.a.o.c.a
    public Object a() {
        return Long.valueOf(this.a);
    }

    @Override // com.pushwoosh.e.a.a.a.o.c.a
    public byte[] b() {
        return this.b.a(this.a);
    }
}
