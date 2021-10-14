package com.pushwoosh.notification.handlers.message.system;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.chain.Chain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class e implements Chain<MessageSystemHandler> {
    private final Collection<MessageSystemHandler> a;

    public static final class b {
        private final Collection<MessageSystemHandler> a = new ArrayList();

        /* access modifiers changed from: package-private */
        public b a(MessageSystemHandler messageSystemHandler) {
            this.a.add(messageSystemHandler);
            return this;
        }

        public e a() {
            return new e(this.a);
        }
    }

    private e(@NonNull Collection<MessageSystemHandler> collection) {
        this.a = collection;
    }

    /* renamed from: a */
    public void addItem(MessageSystemHandler messageSystemHandler) {
        this.a.add(messageSystemHandler);
    }

    /* renamed from: b */
    public void removeItem(MessageSystemHandler messageSystemHandler) {
        this.a.remove(messageSystemHandler);
    }

    @Override // com.pushwoosh.internal.chain.Chain
    public Iterator<MessageSystemHandler> getIterator() {
        return this.a.iterator();
    }
}
