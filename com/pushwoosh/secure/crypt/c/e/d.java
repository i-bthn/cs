package com.pushwoosh.secure.crypt.c.e;

import androidx.core.util.Pair;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.secure.crypt.d.b;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class d extends a {
    static final BigInteger a = new BigInteger("-1");
    private final b b;

    public d(b bVar) {
        super(bVar);
        this.b = bVar;
    }

    private Pair<BigInteger, BigInteger> a(String str) {
        return new Pair<>(c(str), b(str));
    }

    private void a(String str, BigInteger bigInteger, BigInteger bigInteger2) {
        b bVar = this.b;
        bVar.a(str + "mod", bigInteger);
        b bVar2 = this.b;
        bVar2.a(str + "exp", bigInteger2);
    }

    private BigInteger b(String str) {
        b bVar = this.b;
        return bVar.b(str + "exp", a);
    }

    private BigInteger c(String str) {
        b bVar = this.b;
        return bVar.b(str + "mod", a);
    }

    private PublicKey d() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Pair<BigInteger, BigInteger> a2 = a("public.key");
        return KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(a2.first, a2.second));
    }

    private void d(String str) {
        b bVar = this.b;
        bVar.b(str + "exp");
        b bVar2 = this.b;
        bVar2.b(str + "mod");
    }

    private PrivateKey e() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Pair<BigInteger, BigInteger> a2 = a("private.key");
        return KeyFactory.getInstance("RSA").generatePrivate(new RSAPrivateKeySpec(a2.first, a2.second));
    }

    private void f() {
        d("public.key");
    }

    private void g() {
        d("private.key");
    }

    @Override // com.pushwoosh.secure.crypt.c.e.b
    public KeyPair a() {
        try {
            return new KeyPair(d(), e());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            PWLog.exception(e);
            return null;
        }
    }

    @Override // com.pushwoosh.secure.crypt.c.e.b
    public void a(PublicKey publicKey, PrivateKey privateKey, String str, int i) {
        if (publicKey instanceof RSAPublicKey) {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) publicKey;
            a("public.key", rSAPublicKey.getModulus(), rSAPublicKey.getPublicExponent());
        }
        if (privateKey instanceof RSAPrivateKey) {
            RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) privateKey;
            a("private.key", rSAPrivateKey.getModulus(), rSAPrivateKey.getPrivateExponent());
        }
        a(str, i);
    }

    @Override // com.pushwoosh.secure.crypt.c.e.b
    public boolean b() {
        return !this.b.a("private.keymod");
    }

    @Override // com.pushwoosh.secure.crypt.c.e.b
    public void c() {
        g();
        f();
    }
}
