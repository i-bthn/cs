package com.pushwoosh.e.a.a.a.o.c.b;

import com.pushwoosh.e.a.a.a.o.b.l;
import com.pushwoosh.e.a.a.a.o.c.a;
import java.util.HashSet;
import java.util.Set;

public final class f implements a {
    private final Set<String> a;
    private final l b;

    public f(Set<String> set, com.pushwoosh.e.a.a.a.o.a aVar) {
        this.a = set;
        this.b = aVar.f();
    }

    @Override // com.pushwoosh.e.a.a.a.o.c.a
    public Object a() {
        return new HashSet(this.a);
    }

    @Override // com.pushwoosh.e.a.a.a.o.c.a
    public byte[] b() {
        return this.b.a(this.a);
    }
}
