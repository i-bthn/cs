package com.pushwoosh.inbox.ui.model.repository;

import androidx.core.app.NotificationCompat;
import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.event.InboxMessagesUpdatedEvent;
import com.pushwoosh.inbox.ui.model.repository.InboxEvent;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", NotificationCompat.CATEGORY_EVENT, "Lcom/pushwoosh/inbox/event/InboxMessagesUpdatedEvent;", "kotlin.jvm.PlatformType", "onReceive"}, k = 3, mv = {1, 1, 15})
/* compiled from: InboxRepository.kt */
public final class InboxRepository$subscribeToEvent$1<T extends Event> implements EventListener<T> {
    public static final InboxRepository$subscribeToEvent$1 INSTANCE = new InboxRepository$subscribeToEvent$1();

    InboxRepository$subscribeToEvent$1() {
    }

    public final void onReceive(InboxMessagesUpdatedEvent inboxMessagesUpdatedEvent) {
        boolean z;
        Intrinsics.checkExpressionValueIsNotNull(inboxMessagesUpdatedEvent, NotificationCompat.CATEGORY_EVENT);
        Collection<InboxMessage> messagesAdded = inboxMessagesUpdatedEvent.getMessagesAdded();
        Intrinsics.checkExpressionValueIsNotNull(messagesAdded, "event.messagesAdded");
        ArrayList arrayList = new ArrayList();
        for (T t : messagesAdded) {
            InboxRepository inboxRepository = InboxRepository.INSTANCE;
            if (true ^ InboxRepository.currentInboxMessages.contains(t)) {
                arrayList.add(t);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList);
        Collection<InboxMessage> messagesUpdated = inboxMessagesUpdatedEvent.getMessagesUpdated();
        Intrinsics.checkExpressionValueIsNotNull(messagesUpdated, "event.messagesUpdated");
        ArrayList arrayList3 = new ArrayList();
        for (T t2 : messagesUpdated) {
            T t3 = t2;
            InboxRepository inboxRepository2 = InboxRepository.INSTANCE;
            if (InboxRepository.currentInboxMessages.contains(t3)) {
                InboxRepository inboxRepository3 = InboxRepository.INSTANCE;
                List list = InboxRepository.currentInboxMessages;
                InboxRepository inboxRepository4 = InboxRepository.INSTANCE;
                int indexOf = InboxRepository.currentInboxMessages.indexOf(t3);
                Intrinsics.checkExpressionValueIsNotNull(t3, "it");
                list.set(indexOf, t3);
                z = true;
            } else {
                Intrinsics.checkExpressionValueIsNotNull(t3, "it");
                arrayList2.add(t3);
                z = false;
            }
            if (z) {
                arrayList3.add(t2);
            }
        }
        ArrayList arrayList4 = arrayList3;
        InboxRepository inboxRepository5 = InboxRepository.INSTANCE;
        InboxRepository.currentInboxMessages.addAll(arrayList2);
        InboxRepository inboxRepository6 = InboxRepository.INSTANCE;
        if (!InboxRepository.currentInboxMessages.isEmpty()) {
            InboxRepository inboxRepository7 = InboxRepository.INSTANCE;
            Collection remove = InboxRepositoryKt.remove(InboxRepository.currentInboxMessages, new InboxRepository$subscribeToEvent$1$deleted$1(inboxMessagesUpdatedEvent));
            InboxRepository inboxRepository8 = InboxRepository.INSTANCE;
            InboxRepository inboxRepository9 = InboxRepository.INSTANCE;
            inboxRepository8.updateEvent(InboxRepository.currentInboxMessages.isEmpty() ? new InboxEvent.InboxEmpty() : new InboxEvent.InboxMessagesUpdated(arrayList2, arrayList4, remove));
        }
    }
}
