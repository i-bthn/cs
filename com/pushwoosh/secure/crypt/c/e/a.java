package com.pushwoosh.secure.crypt.c.e;

import com.pushwoosh.secure.crypt.d.b;

public abstract class a implements b {
    private final b a;

    public a(b bVar) {
        this.a = bVar;
    }

    private boolean a(int i) {
        return i == this.a.a("CryptoStorageKEY_KEY_SIZE", -1);
    }

    private boolean a(String str) {
        return str.equals(this.a.b("CryptoStorageKEY_ENCRYPTION_PADDING", (String) null));
    }

    /* access modifiers changed from: protected */
    public void a(String str, int i) {
        this.a.a("CryptoStorageKEY_ENCRYPTION_PADDING", str);
        this.a.b("CryptoStorageKEY_KEY_SIZE", i);
    }

    /* access modifiers changed from: protected */
    public boolean b(String str, int i) {
        return a(i) && a(str);
    }

    @Override // com.pushwoosh.secure.crypt.c.e.b
    public boolean c(String str, int i) {
        return b(str, i) && !b();
    }
}
