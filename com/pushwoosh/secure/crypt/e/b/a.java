package com.pushwoosh.secure.crypt.e.b;

import android.util.Base64;
import java.security.PublicKey;

public final class a implements b {
    @Override // com.pushwoosh.secure.crypt.e.b.b
    public String a(PublicKey publicKey) {
        return Base64.encodeToString(publicKey.getEncoded(), 0).replaceAll("(\\r|\\n)", "");
    }
}
