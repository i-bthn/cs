package com.pushwoosh.e.a.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import com.pushwoosh.e.a.a.a.i.c;
import com.pushwoosh.e.a.a.a.i.d;
import com.pushwoosh.e.a.a.a.n.a;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public final class b {
    private final d a = new d();
    private final Map<String, ReadWriteLock> b = this.a.e();
    private final Map<String, Lock> c = this.a.f();
    private final Map<String, ExecutorService> d = this.a.d();
    private final Map<String, Map<String, Object>> e = this.a.c();
    private final Map<String, Set<String>> f = this.a.b();
    private final Map<String, List<SharedPreferences.OnSharedPreferenceChangeListener>> g = this.a.a();
    private final Context h;
    private final com.pushwoosh.e.a.a.a.o.b.m.b i = new com.pushwoosh.e.a.a.a.o.b.m.b();
    private final a j = new a();
    private File k;
    private String l = "default";
    private boolean m = false;
    private boolean n = true;
    private com.pushwoosh.e.a.a.a.h.a o = com.pushwoosh.e.a.a.a.h.a.a;
    private com.pushwoosh.e.a.a.a.h.b p = com.pushwoosh.e.a.a.a.h.b.a;
    private c q = c.a;

    public b(Context context) {
        this.h = context;
        this.k = context.getFilesDir();
    }

    private a b() {
        com.pushwoosh.e.a.a.a.o.a aVar;
        com.pushwoosh.e.a.a.a.i.b bVar;
        com.pushwoosh.e.a.a.a.k.b.a aVar2 = new com.pushwoosh.e.a.a.a.k.b.a(this.l, this.k);
        com.pushwoosh.e.a.a.a.k.a.b bVar2 = new com.pushwoosh.e.a.a.a.k.a.b(aVar2);
        com.pushwoosh.e.a.a.a.m.c cVar = new com.pushwoosh.e.a.a.a.m.c(this.l, aVar2, this.b, this.c);
        com.pushwoosh.e.a.a.a.k.c.b bVar3 = new com.pushwoosh.e.a.a.a.k.c.b(bVar2, cVar, this.o, this.p);
        com.pushwoosh.e.a.a.a.g.a.b bVar4 = new com.pushwoosh.e.a.a.a.g.a.b(this.l, this.f);
        com.pushwoosh.e.a.a.a.g.b.b bVar5 = new com.pushwoosh.e.a.a.a.g.b.b(this.l, this.e);
        com.pushwoosh.e.a.a.a.p.b bVar6 = new com.pushwoosh.e.a.a.a.p.b(this.l, this.q, this.d);
        com.pushwoosh.e.a.a.a.o.a aVar3 = new com.pushwoosh.e.a.a.a.o.a(this.i);
        if (this.m) {
            aVar = aVar3;
            bVar = new com.pushwoosh.e.a.a.a.i.a(this.h, this.l, bVar4, bVar5, aVar3, bVar6, this.p, aVar2, this.g);
        } else {
            aVar = aVar3;
            bVar = new d(this.l, this.g);
        }
        return new a(bVar3, bVar, bVar4, bVar5, bVar6, aVar, cVar, this.n ? new com.pushwoosh.e.a.a.a.l.c(cVar, bVar6, bVar4, bVar5, bVar3, aVar) : new com.pushwoosh.e.a.a.a.l.a(cVar, bVar6, bVar4, bVar5, bVar3, aVar));
    }

    public b a(c cVar) {
        this.q = cVar;
        return this;
    }

    public b a(String str) {
        this.l = str;
        return this;
    }

    public b a(boolean z) {
        this.m = z;
        return this;
    }

    public e a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new com.pushwoosh.e.a.a.a.j.c("Preferences should be instantiated in the main thread.");
        } else if (!this.n || !this.m) {
            a b2 = b();
            this.j.a(b2);
            return b2;
        } else {
            throw new UnsupportedOperationException("IPC mode can't be used with lazy in-memory cache strategy!");
        }
    }
}
