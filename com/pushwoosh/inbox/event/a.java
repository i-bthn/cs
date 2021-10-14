package com.pushwoosh.inbox.event;

import com.pushwoosh.inbox.data.InboxMessage;
import java.util.Collection;

public class a {
    private Collection<InboxMessage> a;
    private Collection<InboxMessage> b;
    private Collection<String> c;

    public InboxMessagesUpdatedEvent a() {
        return new InboxMessagesUpdatedEvent(this.a, this.b, this.c);
    }

    public a a(Collection<InboxMessage> collection) {
        this.a = collection;
        return this;
    }

    public a b(Collection<String> collection) {
        this.c = collection;
        return this;
    }

    public a c(Collection<InboxMessage> collection) {
        this.b = collection;
        return this;
    }
}
