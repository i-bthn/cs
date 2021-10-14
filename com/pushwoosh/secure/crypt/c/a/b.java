package com.pushwoosh.secure.crypt.c.a;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/* access modifiers changed from: package-private */
public abstract class b implements a {
    final String a;

    b(String str) {
        this.a = str;
    }

    @Override // com.pushwoosh.secure.crypt.c.a.a
    public KeyPair a(String str, int i) throws Exception {
        KeyPairGenerator instance = KeyPairGenerator.getInstance(this.a, "AndroidKeyStore");
        a(instance, str, i);
        return instance.generateKeyPair();
    }

    /* access modifiers changed from: protected */
    public abstract void a(KeyPairGenerator keyPairGenerator, String str, int i) throws Exception;
}
