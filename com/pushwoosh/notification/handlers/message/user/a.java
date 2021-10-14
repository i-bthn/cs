package com.pushwoosh.notification.handlers.message.user;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.chain.Chain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* access modifiers changed from: package-private */
public class a implements Chain<MessageHandler> {
    private final Collection<MessageHandler> a;

    public static final class b {
        private final Collection<MessageHandler> a = new ArrayList();

        /* access modifiers changed from: package-private */
        public b a(MessageHandler messageHandler) {
            this.a.add(messageHandler);
            return this;
        }

        public a a() {
            return new a(this.a);
        }
    }

    private a(@NonNull Collection<MessageHandler> collection) {
        this.a = collection;
    }

    /* renamed from: a */
    public void addItem(MessageHandler messageHandler) {
        this.a.add(messageHandler);
    }

    /* renamed from: b */
    public void removeItem(MessageHandler messageHandler) {
        this.a.remove(messageHandler);
    }

    @Override // com.pushwoosh.internal.chain.Chain
    public Iterator<MessageHandler> getIterator() {
        return this.a.iterator();
    }
}
