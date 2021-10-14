package com.pushwoosh.internal.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.LinkedList;
import java.util.List;

public class a<T> {
    private final List<T> a = new LinkedList();
    private final int b;
    private final AbstractC0024a<T> c;
    private final Handler d;

    /* renamed from: com.pushwoosh.internal.utils.a$a  reason: collision with other inner class name */
    public interface AbstractC0024a<W> {
        void a(List<W> list);
    }

    public a(AbstractC0024a<T> aVar, int i) {
        this.b = i;
        this.c = aVar;
        this.d = new Handler(Looper.getMainLooper());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a() {
        LinkedList linkedList = new LinkedList();
        synchronized (this.a) {
            linkedList.addAll(this.a);
            this.a.clear();
        }
        this.c.a(linkedList);
    }

    public void a(T t) {
        synchronized (this.a) {
            if (this.a.isEmpty()) {
                this.d.postDelayed(new Runnable() {
                    /* class com.pushwoosh.internal.utils.$$Lambda$a$Cs2p6PrvqsiM8Nk2Z9PGCNv_5Qw */

                    public final void run() {
                        a.lambda$Cs2p6PrvqsiM8Nk2Z9PGCNv_5Qw(a.this);
                    }
                }, (long) this.b);
            }
            this.a.add(t);
        }
    }
}
