package com.pushwoosh.e.a.a.a.l;

import com.pushwoosh.e.a.a.a.p.c;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

public final class a implements b {
    private final Lock a;
    private final c b;
    private final com.pushwoosh.e.a.a.a.g.a.a c;
    private final com.pushwoosh.e.a.a.a.g.b.a d;
    private final com.pushwoosh.e.a.a.a.k.c.a e;
    private final com.pushwoosh.e.a.a.a.o.a f;

    /* access modifiers changed from: package-private */
    /* renamed from: com.pushwoosh.e.a.a.a.l.a$a  reason: collision with other inner class name */
    public class RunnableC0007a implements Runnable {
        RunnableC0007a() {
        }

        public void run() {
            a.this.b();
        }
    }

    public a(com.pushwoosh.e.a.a.a.m.a aVar, c cVar, com.pushwoosh.e.a.a.a.g.a.a aVar2, com.pushwoosh.e.a.a.a.g.b.a aVar3, com.pushwoosh.e.a.a.a.k.c.a aVar4, com.pushwoosh.e.a.a.a.o.a aVar5) {
        this.a = aVar.a();
        this.b = cVar;
        this.c = aVar2;
        this.d = aVar3;
        this.e = aVar4;
        this.f = aVar5;
        a();
    }

    private void a() {
        this.e.lock();
        this.a.lock();
        try {
            this.b.a(new RunnableC0007a()).a();
        } finally {
            this.e.unlock();
            this.a.unlock();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b() {
        if (c()) {
            for (com.pushwoosh.e.a.a.a.k.c.c cVar : this.e.b()) {
                String c2 = cVar.c();
                this.d.a(c2, this.f.a(c2, cVar.b()));
                this.c.a(c2);
            }
        }
    }

    private boolean c() {
        return !this.d.a().containsAll(this.c.a());
    }

    @Override // com.pushwoosh.e.a.a.a.l.b
    public Object a(String str, Object obj) {
        this.a.lock();
        try {
            Object a2 = this.d.a(str);
            if (a2 == null) {
                return obj;
            }
            Object a3 = this.f.a(a2);
            this.a.unlock();
            return a3;
        } finally {
            this.a.unlock();
        }
    }

    @Override // com.pushwoosh.e.a.a.a.l.b
    public boolean contains(String str) {
        this.a.lock();
        try {
            return this.d.contains(str);
        } finally {
            this.a.unlock();
        }
    }

    @Override // com.pushwoosh.e.a.a.a.l.b
    public Map<String, Object> getAll() {
        this.a.lock();
        try {
            Map<String, Object> all = this.d.getAll();
            HashMap hashMap = new HashMap(all.size());
            for (String str : all.keySet()) {
                hashMap.put(str, this.f.a(all.get(str)));
            }
            return Collections.unmodifiableMap(hashMap);
        } finally {
            this.a.unlock();
        }
    }
}
