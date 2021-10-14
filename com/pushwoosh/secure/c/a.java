package com.pushwoosh.secure.c;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.notification.handlers.message.system.MessageSystemHandler;
import com.pushwoosh.secure.a.e;
import com.pushwoosh.secure.c.a.b;
import com.pushwoosh.secure.crypt.manager.DecryptorManager;

public final class a implements MessageSystemHandler {
    private final DecryptorManager a;
    private final e b;

    public a(@NonNull com.pushwoosh.secure.crypt.manager.a aVar) {
        this.a = aVar;
        this.b = new e(aVar);
    }

    public void a(@Nullable Callback<Void, PushwooshException> callback) {
        this.a.removePublicKey(callback);
    }

    @Override // com.pushwoosh.notification.handlers.message.system.MessageSystemHandler
    public boolean preHandleMessage(Bundle bundle) {
        if (bundle.containsKey("pwkv")) {
            return this.b.a(bundle);
        }
        com.pushwoosh.secure.c.a.a a2 = b.a(bundle);
        if (a2 == null) {
            return false;
        }
        a2.a(bundle, this.a);
        return false;
    }
}
