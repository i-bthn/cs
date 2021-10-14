package com.pushwoosh.inbox.b.d;

import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.internal.data.b;

public class a implements c<InboxMessage, b> {
    public b a(InboxMessage inboxMessage) {
        return inboxMessage instanceof com.pushwoosh.inbox.internal.data.a ? ((com.pushwoosh.inbox.internal.data.a) inboxMessage).a() : new b.C0019b().a(inboxMessage).a();
    }
}
