package com.pushwoosh.secure.crypt.c.c;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.pushwoosh.secure.b.a;
import java.security.KeyPair;

public class b implements a {
    private final com.pushwoosh.secure.crypt.c.b.b a;
    private final int b;

    public b(@NonNull com.pushwoosh.secure.crypt.c.b.b bVar, int i) {
        this.a = bVar;
        this.b = i;
    }

    @Override // com.pushwoosh.secure.crypt.c.c.a
    @NonNull
    @WorkerThread
    public KeyPair a() {
        synchronized (this.a) {
            if (this.a.b()) {
                return this.a.c();
            }
            this.a.a(this.b);
            return this.a.c();
        }
    }

    public void a(@NonNull a<KeyPair> aVar) {
        if (this.a.b()) {
            aVar.a(this.a.c());
        } else {
            this.a.a(this.b, aVar);
        }
    }

    @Override // com.pushwoosh.secure.crypt.c.c.a
    public void b() {
        this.a.a();
    }
}
