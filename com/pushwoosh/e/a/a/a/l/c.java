package com.pushwoosh.e.a.a.a.l;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

public final class c implements b {
    private final Lock a;
    private final com.pushwoosh.e.a.a.a.p.c b;
    private final com.pushwoosh.e.a.a.a.g.a.a c;
    private final com.pushwoosh.e.a.a.a.g.b.a d;
    private final com.pushwoosh.e.a.a.a.k.c.a e;
    private final com.pushwoosh.e.a.a.a.o.a f;

    /* access modifiers changed from: package-private */
    public class a implements Callable<Object> {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            return c.this.a(this.a);
        }
    }

    /* access modifiers changed from: package-private */
    public class b implements Callable<Object> {
        final /* synthetic */ String a;

        b(String str) {
            this.a = str;
        }

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            return c.this.a(this.a);
        }
    }

    public c(com.pushwoosh.e.a.a.a.m.a aVar, com.pushwoosh.e.a.a.a.p.c cVar, com.pushwoosh.e.a.a.a.g.a.a aVar2, com.pushwoosh.e.a.a.a.g.b.a aVar3, com.pushwoosh.e.a.a.a.k.c.a aVar4, com.pushwoosh.e.a.a.a.o.a aVar5) {
        this.a = aVar.a();
        this.b = cVar;
        this.c = aVar2;
        this.d = aVar3;
        this.e = aVar4;
        this.f = aVar5;
        a();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Object a(String str) {
        Object a2 = this.f.a(str, this.e.a(str).b());
        this.d.a(str, a2);
        return a2;
    }

    private void a() {
        this.a.lock();
        try {
            for (String str : this.e.a()) {
                this.c.a(str);
            }
        } finally {
            this.a.unlock();
        }
    }

    private Object b(String str) {
        Object a2 = this.d.a(str);
        if (a2 != null) {
            return a2;
        }
        this.e.lock();
        try {
            return this.b.a(new a(str)).b();
        } finally {
            this.e.unlock();
        }
    }

    private Object b(String str, Object obj) {
        Object a2 = this.d.a(str);
        if (a2 != null) {
            return a2;
        }
        if (!this.c.a().contains(str)) {
            return obj;
        }
        this.e.lock();
        try {
            return this.b.a(new b(str)).a(obj);
        } finally {
            this.e.unlock();
        }
    }

    @Override // com.pushwoosh.e.a.a.a.l.b
    public Object a(String str, Object obj) {
        this.a.lock();
        try {
            return this.f.a(b(str, obj));
        } finally {
            this.a.unlock();
        }
    }

    @Override // com.pushwoosh.e.a.a.a.l.b
    public boolean contains(String str) {
        this.a.lock();
        try {
            return this.c.a().contains(str) && this.d.contains(str);
        } finally {
            this.a.unlock();
        }
    }

    @Override // com.pushwoosh.e.a.a.a.l.b
    public Map<String, Object> getAll() {
        this.a.lock();
        try {
            Set<String> a2 = this.c.a();
            HashMap hashMap = new HashMap(a2.size());
            for (String str : a2) {
                hashMap.put(str, this.f.a(b(str)));
            }
            return Collections.unmodifiableMap(hashMap);
        } finally {
            this.a.unlock();
        }
    }
}
