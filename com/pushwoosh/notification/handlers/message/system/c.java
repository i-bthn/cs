package com.pushwoosh.notification.handlers.message.system;

import android.os.Bundle;
import com.pushwoosh.notification.b;

/* access modifiers changed from: package-private */
public abstract class c implements MessageSystemHandler {
    c() {
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(Bundle bundle, String str);

    @Override // com.pushwoosh.notification.handlers.message.system.MessageSystemHandler
    public final boolean preHandleMessage(Bundle bundle) {
        if (b.F(bundle)) {
            return a(bundle, b.j(bundle));
        }
        return false;
    }
}
