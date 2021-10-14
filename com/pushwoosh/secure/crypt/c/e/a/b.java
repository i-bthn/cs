package com.pushwoosh.secure.crypt.c.e.a;

import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.secure.crypt.c.e.c;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class b implements a {
    private final com.pushwoosh.secure.crypt.d.b a;
    private final String b;

    public b(com.pushwoosh.secure.crypt.d.b bVar, String str) {
        this.a = bVar;
        this.b = str;
    }

    @Override // com.pushwoosh.secure.crypt.c.e.a.a
    public com.pushwoosh.secure.crypt.c.e.b a() {
        try {
            KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
            instance.load(null);
            return new c(this.a, this.b, instance);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            PWLog.exception(e);
            return null;
        }
    }
}
