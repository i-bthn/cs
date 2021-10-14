package com.pushwoosh.secure.crypt.c.a;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class c implements a {
    /* access modifiers changed from: package-private */
    public KeyPair a(int i, KeyPairGenerator keyPairGenerator) {
        keyPairGenerator.initialize(i);
        return keyPairGenerator.generateKeyPair();
    }

    @Override // com.pushwoosh.secure.crypt.c.a.a
    public KeyPair a(String str, int i) throws Exception {
        return a(i, KeyPairGenerator.getInstance("RSA"));
    }
}
