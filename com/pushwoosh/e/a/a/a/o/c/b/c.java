package com.pushwoosh.e.a.a.a.o.c.b;

import com.pushwoosh.e.a.a.a.o.b.g;
import com.pushwoosh.e.a.a.a.o.c.a;

public final class c implements a {
    private final int a;
    private final g b;

    public c(int i, com.pushwoosh.e.a.a.a.o.a aVar) {
        this.a = i;
        this.b = aVar.c();
    }

    @Override // com.pushwoosh.e.a.a.a.o.c.a
    public Object a() {
        return Integer.valueOf(this.a);
    }

    @Override // com.pushwoosh.e.a.a.a.o.c.a
    public byte[] b() {
        return this.b.a(this.a);
    }
}
