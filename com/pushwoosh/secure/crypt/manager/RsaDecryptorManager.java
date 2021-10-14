package com.pushwoosh.secure.crypt.manager;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.secure.crypt.a.a;
import com.pushwoosh.secure.crypt.d.c;
import com.pushwoosh.secure.crypt.e.a.b;
import java.security.KeyPair;
import java.security.PublicKey;

public class RsaDecryptorManager implements DecryptorManager {
    public static final String ALIAS = "Pushwoosh";
    private final a a;
    private final com.pushwoosh.secure.crypt.c.c.a b;

    public RsaDecryptorManager(Context context, int i) {
        this(context, new com.pushwoosh.secure.crypt.e.a.a(), "PKCS1Padding", i);
    }

    private RsaDecryptorManager(Context context, b bVar, String str, int i) {
        this.a = a(bVar, str);
        this.b = a(context, str, i);
    }

    @NonNull
    private static a a(b bVar, String str) {
        return new com.pushwoosh.secure.crypt.a.b(bVar, str);
    }

    @NonNull
    private static com.pushwoosh.secure.crypt.c.c.a a(Context context, String str, int i) {
        return new com.pushwoosh.secure.crypt.c.c.b(new com.pushwoosh.secure.crypt.c.b.a(context, ALIAS, "RSA", str), i);
    }

    public static com.pushwoosh.secure.crypt.c.e.b createCryptoStorage(Context context) {
        com.pushwoosh.secure.crypt.d.b a2 = c.a(context, ALIAS);
        return (Build.VERSION.SDK_INT >= 19 ? new com.pushwoosh.secure.crypt.c.e.a.b(a2, ALIAS) : new com.pushwoosh.secure.crypt.c.e.a.c(a2)).a();
    }

    public static boolean isKeysExist(Context context) {
        return !createCryptoStorage(context).b();
    }

    /* access modifiers changed from: package-private */
    public com.pushwoosh.secure.crypt.c.c.a b() {
        return this.b;
    }

    @Override // com.pushwoosh.secure.crypt.manager.DecryptorManager
    @Nullable
    public String decrypt(String str) {
        try {
            return this.a.a(this.b.a().getPrivate(), str);
        } catch (com.pushwoosh.secure.crypt.b.a e) {
            PWLog.exception(e);
            return null;
        }
    }

    @Override // com.pushwoosh.secure.crypt.manager.DecryptorManager
    @Nullable
    public byte[] decryptBytes(String str) {
        try {
            return this.a.b(this.b.a().getPrivate(), str);
        } catch (com.pushwoosh.secure.crypt.b.a e) {
            PWLog.exception(e);
            return null;
        }
    }

    public final void getKeyPairAsync(@NonNull com.pushwoosh.secure.b.a<KeyPair> aVar) {
        ((com.pushwoosh.secure.crypt.c.c.b) b()).a(aVar);
    }

    @WorkerThread
    public final PublicKey getPublicKey() {
        return this.b.a().getPublic();
    }

    @Override // com.pushwoosh.secure.crypt.manager.DecryptorManager
    public void removePublicKey(@Nullable Callback<Void, PushwooshException> callback) {
        this.b.b();
        if (callback != null) {
            callback.process(Result.fromData(null));
        }
    }
}
