package com.pushwoosh.e.a.a.a;

import com.pushwoosh.e.a.a.a.i.b;
import com.pushwoosh.e.a.a.a.j.e;
import com.pushwoosh.e.a.a.a.o.c.b.d;
import com.pushwoosh.e.a.a.a.o.c.b.f;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

/* access modifiers changed from: package-private */
public final class c implements f {
    private final Map<String, com.pushwoosh.e.a.a.a.o.c.a> a = new HashMap();
    private final Set<String> b = new HashSet();
    private final com.pushwoosh.e.a.a.a.k.c.a c;
    private final b d;
    private final com.pushwoosh.e.a.a.a.p.c e;
    private final com.pushwoosh.e.a.a.a.o.a f;
    private final com.pushwoosh.e.a.a.a.g.b.a g;
    private final com.pushwoosh.e.a.a.a.g.a.a h;
    private final Lock i;
    private boolean j;

    /* access modifiers changed from: package-private */
    public class a implements Runnable {
        a() {
        }

        public void run() {
            c.this.a();
        }
    }

    c(com.pushwoosh.e.a.a.a.k.c.a aVar, b bVar, com.pushwoosh.e.a.a.a.p.c cVar, com.pushwoosh.e.a.a.a.o.a aVar2, com.pushwoosh.e.a.a.a.g.b.a aVar3, com.pushwoosh.e.a.a.a.g.a.a aVar4, Lock lock) {
        this.c = aVar;
        this.d = bVar;
        this.e = cVar;
        this.f = aVar2;
        this.g = aVar3;
        this.h = aVar4;
        this.i = lock;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a() {
        List<com.pushwoosh.e.a.a.a.k.c.c> b2 = b();
        this.c.a(b2);
        a(b2);
    }

    private void a(List<com.pushwoosh.e.a.a.a.k.c.c> list) {
        for (com.pushwoosh.e.a.a.a.k.c.c cVar : list) {
            String c2 = cVar.c();
            byte[] b2 = cVar.b();
            if (cVar.a() == 3) {
                this.d.a(c2);
            }
            if (cVar.a() == 2) {
                this.d.a(c2, b2);
            }
        }
    }

    private List<com.pushwoosh.e.a.a.a.k.c.c> b() {
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(f());
        linkedList.addAll(h());
        return linkedList;
    }

    private void c() {
        if (!this.j) {
            this.j = true;
            return;
        }
        throw new e("Transaction should be applied or committed only once!");
    }

    private com.pushwoosh.e.a.a.a.p.a d() {
        e();
        g();
        c();
        return this.e.a(new a());
    }

    private void e() {
        for (String str : this.b) {
            this.h.remove(str);
            this.g.remove(str);
        }
    }

    private List<com.pushwoosh.e.a.a.a.k.c.c> f() {
        LinkedList linkedList = new LinkedList();
        for (String str : this.b) {
            linkedList.add(com.pushwoosh.e.a.a.a.k.c.c.a(str));
        }
        return linkedList;
    }

    private void g() {
        for (String str : this.a.keySet()) {
            Object a2 = this.a.get(str).a();
            this.h.a(str);
            this.g.a(str, a2);
        }
    }

    private List<com.pushwoosh.e.a.a.a.k.c.c> h() {
        Set<String> keySet = this.a.keySet();
        LinkedList linkedList = new LinkedList();
        for (String str : keySet) {
            linkedList.add(com.pushwoosh.e.a.a.a.k.c.c.b(str, this.a.get(str).b()));
        }
        return linkedList;
    }

    public void apply() {
        this.i.lock();
        try {
            d();
        } finally {
            this.i.unlock();
        }
    }

    public f clear() {
        this.i.lock();
        try {
            this.b.addAll(this.h.a());
            return this;
        } finally {
            this.i.unlock();
        }
    }

    public boolean commit() {
        this.i.lock();
        try {
            return d().c();
        } finally {
            this.i.unlock();
        }
    }

    @Override // com.pushwoosh.e.a.a.a.f
    public f putBoolean(String str, boolean z) {
        this.i.lock();
        try {
            this.a.put(str, new com.pushwoosh.e.a.a.a.o.c.b.a(z, this.f));
            return this;
        } finally {
            this.i.unlock();
        }
    }

    @Override // com.pushwoosh.e.a.a.a.f
    public f putFloat(String str, float f2) {
        this.i.lock();
        try {
            this.a.put(str, new com.pushwoosh.e.a.a.a.o.c.b.b(f2, this.f));
            return this;
        } finally {
            this.i.unlock();
        }
    }

    @Override // com.pushwoosh.e.a.a.a.f
    public f putInt(String str, int i2) {
        this.i.lock();
        try {
            this.a.put(str, new com.pushwoosh.e.a.a.a.o.c.b.c(i2, this.f));
            return this;
        } finally {
            this.i.unlock();
        }
    }

    @Override // com.pushwoosh.e.a.a.a.f
    public f putLong(String str, long j2) {
        this.i.lock();
        try {
            this.a.put(str, new d(j2, this.f));
            return this;
        } finally {
            this.i.unlock();
        }
    }

    @Override // com.pushwoosh.e.a.a.a.f
    public f putString(String str, String str2) {
        if (str2 == null) {
            return remove(str);
        }
        this.i.lock();
        try {
            this.a.put(str, new com.pushwoosh.e.a.a.a.o.c.b.e(str2, this.f));
            return this;
        } finally {
            this.i.unlock();
        }
    }

    @Override // android.content.SharedPreferences.Editor, com.pushwoosh.e.a.a.a.f
    public f putStringSet(String str, Set<String> set) {
        if (set == null) {
            return remove(str);
        }
        this.i.lock();
        try {
            this.a.put(str, new f(set, this.f));
            return this;
        } finally {
            this.i.unlock();
        }
    }

    public f remove(String str) {
        this.i.lock();
        try {
            this.b.add(str);
            return this;
        } finally {
            this.i.unlock();
        }
    }
}
