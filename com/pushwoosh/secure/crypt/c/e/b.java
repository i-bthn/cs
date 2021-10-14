package com.pushwoosh.secure.crypt.c.e;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface b {
    KeyPair a();

    void a(PublicKey publicKey, PrivateKey privateKey, String str, int i);

    boolean b();

    void c();

    boolean c(String str, int i);
}
