package com.pushwoosh.secure.crypt.c.d;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.secure.crypt.c.a.a;
import com.pushwoosh.secure.crypt.c.e.b;
import java.security.KeyPair;

/* access modifiers changed from: package-private */
public class c implements a {
    @Nullable
    final b a;
    private final a b;

    c(@Nullable b bVar, a aVar) {
        this.a = bVar;
        this.b = aVar;
    }

    @NonNull
    private KeyPair b(String str, int i) throws Exception {
        KeyPair a2 = this.b.a(str, i);
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(a2.getPublic(), a2.getPrivate(), str, i);
        }
        return a2;
    }

    private void b() {
        b bVar = this.a;
        if (bVar != null && !bVar.b()) {
            PWLog.debug("CryptoKeysProvider", "delete existing keys");
            this.a.c();
        }
    }

    @Override // com.pushwoosh.secure.crypt.c.d.a
    @WorkerThread
    public KeyPair a(String str, int i) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (this.a != null) {
                if (this.a.c(str, i)) {
                    try {
                        KeyPair a2 = this.a.a();
                        PWLog.debug("CryptoKeysProvider", "Get local time: " + (System.currentTimeMillis() - currentTimeMillis));
                        return a2;
                    } catch (Exception unused) {
                        PWLog.debug("CryptoKeysProvider", "something wrong with existing keys, attempt to regenerate");
                        b();
                        KeyPair b2 = b(str, i);
                        PWLog.debug("CryptoKeysProvider", "Create new key time: " + (System.currentTimeMillis() - currentTimeMillis));
                        return b2;
                    }
                }
            }
            b();
            PWLog.debug("CryptoKeysProvider", "generateNewKeys");
            KeyPair b3 = b(str, i);
            PWLog.debug("CryptoKeysProvider", "Create new key time: " + (System.currentTimeMillis() - currentTimeMillis));
            return b3;
        } catch (Throwable th) {
            if (0 != 0) {
                PWLog.debug("CryptoKeysProvider", ((String) null) + (System.currentTimeMillis() - currentTimeMillis));
            }
            throw th;
        }
    }

    @Override // com.pushwoosh.secure.crypt.c.d.a
    @WorkerThread
    public void a() {
        b();
    }
}
