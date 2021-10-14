package com.pushwoosh.secure.crypt.e.a;

import android.util.Base64;

public final class a implements b {
    @Override // com.pushwoosh.secure.crypt.e.a.b
    public byte[] a(String str) {
        return Base64.decode(str, 0);
    }
}
