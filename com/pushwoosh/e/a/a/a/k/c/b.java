package com.pushwoosh.e.a.a.a.k.c;

import com.pushwoosh.e.a.a.a.k.a.a;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;

public final class b implements a {
    private final a a;
    private final Lock b;
    private final com.pushwoosh.e.a.a.a.h.a c;
    private final com.pushwoosh.e.a.a.a.h.b d;

    public b(a aVar, com.pushwoosh.e.a.a.a.m.a aVar2, com.pushwoosh.e.a.a.a.h.a aVar3, com.pushwoosh.e.a.a.a.h.b bVar) {
        this.a = aVar;
        this.b = aVar2.b();
        this.d = bVar;
        this.c = aVar3;
    }

    private c b(String str) {
        return c.a(str, this.d.b(this.a.a(this.c.a(str))));
    }

    private void b(List<c> list) {
        for (c cVar : list) {
            int a2 = cVar.a();
            String a3 = this.c.a(cVar.c());
            if (a2 == 2) {
                this.a.a(a3, this.d.a(cVar.b()));
            }
            if (a2 == 3) {
                this.a.remove(a3);
            }
        }
    }

    private List<c> c() {
        String[] a2 = this.a.a();
        ArrayList arrayList = new ArrayList(a2.length);
        for (String str : a2) {
            arrayList.add(b(str));
        }
        return arrayList;
    }

    private Set<String> d() {
        String[] a2 = this.a.a();
        HashSet hashSet = new HashSet();
        for (String str : a2) {
            hashSet.add(this.c.b(str));
        }
        return hashSet;
    }

    @Override // com.pushwoosh.e.a.a.a.k.c.a
    public c a(String str) {
        return b(str);
    }

    @Override // com.pushwoosh.e.a.a.a.k.c.a
    public Set<String> a() {
        return d();
    }

    @Override // com.pushwoosh.e.a.a.a.k.c.a
    public void a(List<c> list) {
        b(list);
    }

    @Override // com.pushwoosh.e.a.a.a.k.c.a
    public List<c> b() {
        return c();
    }

    @Override // com.pushwoosh.e.a.a.a.k.c.a
    public void lock() {
        this.b.lock();
    }

    @Override // com.pushwoosh.e.a.a.a.k.c.a
    public void unlock() {
        this.b.unlock();
    }
}
