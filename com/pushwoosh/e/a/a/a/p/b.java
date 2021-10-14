package com.pushwoosh.e.a.a.a.p;

import com.pushwoosh.e.a.a.a.i.c;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public final class b implements c {
    private final c a;
    private final ExecutorService b;

    /* access modifiers changed from: package-private */
    public class a implements ThreadFactory {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        public Thread newThread(Runnable runnable) {
            return b.this.a((b) runnable, (Runnable) this.a);
        }
    }

    public b(String str, c cVar, Map<String, ExecutorService> map) {
        this.a = cVar;
        this.b = a(str, map);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Thread a(Runnable runnable, String str) {
        Thread thread = new Thread(runnable);
        thread.setName(String.format("binaryprefs-pool-%s", str));
        thread.setPriority(10);
        return thread;
    }

    private ExecutorService a(String str, Map<String, ExecutorService> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1, a(str));
        map.put(str, newFixedThreadPool);
        return newFixedThreadPool;
    }

    private ThreadFactory a(String str) {
        return new a(str);
    }

    @Override // com.pushwoosh.e.a.a.a.p.c
    public a a(Runnable runnable) {
        return new a(this.b.submit(runnable), this.a);
    }

    @Override // com.pushwoosh.e.a.a.a.p.c
    public a a(Callable<?> callable) {
        return new a(this.b.submit(callable), this.a);
    }
}
