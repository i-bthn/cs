package com.pushwoosh.notification.handlers.notification;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.chain.Chain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class c implements Chain<PushNotificationOpenHandler> {
    private final Collection<PushNotificationOpenHandler> a;

    public static final class b {
        private final Collection<PushNotificationOpenHandler> a = new ArrayList();

        /* access modifiers changed from: package-private */
        public b a(PushNotificationOpenHandler pushNotificationOpenHandler) {
            this.a.add(pushNotificationOpenHandler);
            return this;
        }

        public c a() {
            return new c(this.a);
        }
    }

    private c(@NonNull Collection<PushNotificationOpenHandler> collection) {
        this.a = collection;
    }

    /* renamed from: a */
    public void addItem(PushNotificationOpenHandler pushNotificationOpenHandler) {
        this.a.add(pushNotificationOpenHandler);
    }

    /* renamed from: b */
    public void removeItem(PushNotificationOpenHandler pushNotificationOpenHandler) {
        this.a.remove(pushNotificationOpenHandler);
    }

    @Override // com.pushwoosh.internal.chain.Chain
    public Iterator<PushNotificationOpenHandler> getIterator() {
        return this.a.iterator();
    }
}
