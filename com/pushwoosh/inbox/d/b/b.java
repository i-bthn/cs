package com.pushwoosh.inbox.d.b;

import android.os.Bundle;
import com.pushwoosh.function.Result;
import com.pushwoosh.inbox.d.a;
import com.pushwoosh.inbox.internal.data.InboxMessageStatus;
import com.pushwoosh.notification.handlers.notification.PushNotificationOpenHandler;

public class b implements PushNotificationOpenHandler {
    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Result result) {
    }

    @Override // com.pushwoosh.notification.handlers.notification.PushNotificationOpenHandler
    public void postHandleNotification(Bundle bundle) {
        String b = a.b(bundle);
        if (b != null) {
            com.pushwoosh.inbox.b.b.a().a(b, InboxMessageStatus.OPEN, $$Lambda$b$CYbXbWJwhN3jBUvu2KqR1vYkKI.INSTANCE);
        }
    }
}
