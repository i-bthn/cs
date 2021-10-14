package com.pushwoosh.inapp.j;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.pushwoosh.inapp.k.c;
import com.pushwoosh.inapp.k.d;
import java.io.File;

/* access modifiers changed from: package-private */
public class b implements com.pushwoosh.internal.checker.b<com.pushwoosh.inapp.j.l.b> {
    private final d a;
    private final c b;

    b(d dVar, c cVar) {
        this.a = dVar;
        this.b = cVar;
    }

    @WorkerThread
    public boolean a(@NonNull com.pushwoosh.inapp.j.l.b bVar) {
        com.pushwoosh.inapp.j.l.b a2 = this.a.a(bVar.c());
        File c = this.b.c(bVar.c());
        return a2 != null && a2.i() == bVar.i() && c != null && c.exists();
    }
}
