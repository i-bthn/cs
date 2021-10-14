package com.pushwoosh.secure.crypt.c.a.a;

import android.annotation.TargetApi;
import android.security.keystore.KeyGenParameterSpec;
import com.pushwoosh.secure.crypt.c.a.a;
import com.pushwoosh.secure.crypt.c.a.e;

@TargetApi(23)
public class d implements a {
    private final KeyGenParameterSpec.Builder a;

    public d(String str) {
        this.a = new KeyGenParameterSpec.Builder(str, 2);
    }

    @Override // com.pushwoosh.secure.crypt.c.a.a.a
    public a a() {
        return new e(this.a);
    }
}
