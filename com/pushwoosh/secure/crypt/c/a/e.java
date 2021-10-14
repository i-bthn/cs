package com.pushwoosh.secure.crypt.c.a;

import android.annotation.TargetApi;
import android.security.keystore.KeyGenParameterSpec;
import androidx.annotation.NonNull;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

@TargetApi(23)
public class e extends b {
    private final KeyGenParameterSpec.Builder b;

    public e(KeyGenParameterSpec.Builder builder) {
        super("RSA");
        this.b = builder;
        a();
    }

    private void a() {
        this.b.setDigests("SHA-256", "SHA-512");
    }

    @NonNull
    private KeyGenParameterSpec b(String str, int i) {
        return this.b.setEncryptionPaddings(str).setKeySize(i).build();
    }

    @Override // com.pushwoosh.secure.crypt.c.a.b, com.pushwoosh.secure.crypt.c.a.a
    public /* bridge */ /* synthetic */ KeyPair a(String str, int i) throws Exception {
        return super.a(str, i);
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.secure.crypt.c.a.b
    public void a(KeyPairGenerator keyPairGenerator, String str, int i) throws Exception {
        keyPairGenerator.initialize(b(str, i));
    }
}
