package com.pushwoosh.secure.crypt.c.b;

import android.content.Context;
import androidx.annotation.WorkerThread;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.secure.crypt.c.d.b;
import java.security.KeyPair;

public class a implements b {
    private volatile KeyPair a;
    private c b;
    private final com.pushwoosh.secure.crypt.c.d.a c;
    private final com.pushwoosh.secure.crypt.c.d.a d;
    private final String e;

    public a(Context context, String str, String str2, String str3) {
        this(b.a(context, str, str2), b.b(context, str, str2), str3);
    }

    a(com.pushwoosh.secure.crypt.c.d.a aVar, com.pushwoosh.secure.crypt.c.d.a aVar2, String str) {
        this.c = aVar;
        this.d = aVar2;
        this.e = str;
    }

    private void a(com.pushwoosh.secure.crypt.c.d.a aVar, int i, String str) throws Exception {
        this.a = aVar.a(str, i);
    }

    @Override // com.pushwoosh.secure.crypt.c.b.b
    public void a() {
        this.c.a();
    }

    @Override // com.pushwoosh.secure.crypt.c.b.b
    @WorkerThread
    public void a(int i) {
        try {
            a(this.c, i, this.e);
        } catch (IllegalStateException e2) {
            PWLog.exception(e2);
            try {
                a(this.d, i, this.e);
            } catch (Exception e3) {
                PWLog.exception(e3);
            }
        }
    }

    @Override // com.pushwoosh.secure.crypt.c.b.b
    public void a(int i, com.pushwoosh.secure.b.a<KeyPair> aVar) {
        c cVar = this.b;
        if (cVar != null) {
            cVar.cancel(true);
        }
        this.b = new c(this, i);
        this.b.a(aVar);
        this.b.execute(new Void[0]);
    }

    @Override // com.pushwoosh.secure.crypt.c.b.b
    public boolean b() {
        return this.a != null;
    }

    @Override // com.pushwoosh.secure.crypt.c.b.b
    public KeyPair c() {
        return this.a;
    }
}
