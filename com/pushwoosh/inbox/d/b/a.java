package com.pushwoosh.inbox.d.b;

import android.os.Bundle;
import androidx.annotation.WorkerThread;
import com.pushwoosh.inbox.b.b;
import com.pushwoosh.inbox.internal.data.b;
import com.pushwoosh.notification.handlers.message.system.MessageSystemHandler;

public class a implements MessageSystemHandler {
    @Override // com.pushwoosh.notification.handlers.message.system.MessageSystemHandler
    @WorkerThread
    public boolean preHandleMessage(Bundle bundle) {
        if (com.pushwoosh.inbox.d.a.b(bundle) == null) {
            return false;
        }
        b.a().a(new b.C0019b().a(bundle).a());
        return false;
    }
}
