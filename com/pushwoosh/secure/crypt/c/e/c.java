package com.pushwoosh.secure.crypt.c.e;

import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.secure.crypt.d.b;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;

public class c extends a {
    private final String a;
    private final KeyStore b;

    public c(b bVar, String str, KeyStore keyStore) {
        super(bVar);
        this.a = str;
        this.b = keyStore;
    }

    @Override // com.pushwoosh.secure.crypt.c.e.b
    public KeyPair a() {
        try {
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) this.b.getEntry(this.a, null);
            return new KeyPair(privateKeyEntry.getCertificate().getPublicKey(), privateKeyEntry.getPrivateKey());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException e) {
            PWLog.exception(e);
            return null;
        }
    }

    @Override // com.pushwoosh.secure.crypt.c.e.b
    public void a(PublicKey publicKey, PrivateKey privateKey, String str, int i) {
        a(str, i);
    }

    @Override // com.pushwoosh.secure.crypt.c.e.b
    public boolean b() {
        try {
            return true ^ this.b.containsAlias(this.a);
        } catch (KeyStoreException e) {
            PWLog.exception(e);
            return true;
        }
    }

    @Override // com.pushwoosh.secure.crypt.c.e.b
    public void c() {
        try {
            this.b.deleteEntry(this.a);
        } catch (KeyStoreException e) {
            PWLog.exception(e);
        }
    }
}
