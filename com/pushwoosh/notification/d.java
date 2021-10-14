package com.pushwoosh.notification;

import android.os.Bundle;
import com.pushwoosh.internal.chain.Chain;
import com.pushwoosh.notification.handlers.message.system.MessageSystemHandler;
import com.pushwoosh.notification.handlers.message.user.MessageHandler;
import java.util.Iterator;

/* access modifiers changed from: package-private */
public class d {
    private final Chain<MessageSystemHandler> a;
    private final Chain<MessageHandler> b;

    d(Chain<MessageSystemHandler> chain, Chain<MessageHandler> chain2) {
        this.a = chain;
        this.b = chain2;
    }

    /* access modifiers changed from: package-private */
    public void a(PushMessage pushMessage, boolean z) {
        Iterator<MessageHandler> iterator = this.b.getIterator();
        while (iterator.hasNext()) {
            MessageHandler next = iterator.next();
            if (!z) {
                next.handlePushMessage(pushMessage);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(Bundle bundle) {
        Iterator<MessageSystemHandler> iterator = this.a.getIterator();
        while (true) {
            boolean z = false;
            while (true) {
                if (!iterator.hasNext()) {
                    return z;
                }
                if (iterator.next().preHandleMessage(bundle) || z) {
                    z = true;
                }
            }
        }
    }
}
