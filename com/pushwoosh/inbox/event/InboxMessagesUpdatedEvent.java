package com.pushwoosh.inbox.event;

import androidx.annotation.NonNull;
import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.internal.event.Event;
import java.util.Collection;
import java.util.Collections;

public class InboxMessagesUpdatedEvent implements Event {
    private Collection<InboxMessage> messagesAdded;
    private Collection<String> messagesDeleted;
    private Collection<InboxMessage> messagesUpdated;

    InboxMessagesUpdatedEvent(Collection<InboxMessage> collection, Collection<InboxMessage> collection2, Collection<String> collection3) {
        this.messagesAdded = collection == null ? Collections.emptyList() : collection;
        this.messagesUpdated = collection2 == null ? Collections.emptyList() : collection2;
        this.messagesDeleted = collection3 == null ? Collections.emptyList() : collection3;
    }

    @NonNull
    public Collection<InboxMessage> getMessagesAdded() {
        return this.messagesAdded;
    }

    @NonNull
    public Collection<String> getMessagesDeleted() {
        return this.messagesDeleted;
    }

    @NonNull
    public Collection<InboxMessage> getMessagesUpdated() {
        return this.messagesUpdated;
    }
}
