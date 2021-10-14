package com.pushwoosh.notification.handlers.message.system;

import android.os.Bundle;
import com.pushwoosh.notification.b;

/* access modifiers changed from: package-private */
public class f implements MessageSystemHandler {
    f() {
    }

    @Override // com.pushwoosh.notification.handlers.message.system.MessageSystemHandler
    public boolean preHandleMessage(Bundle bundle) {
        return !b.G(bundle);
    }
}
