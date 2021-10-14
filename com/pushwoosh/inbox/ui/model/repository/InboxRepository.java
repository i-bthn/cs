package com.pushwoosh.inbox.ui.model.repository;

import com.pushwoosh.function.Callback;
import com.pushwoosh.inbox.PushwooshInbox;
import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.event.InboxMessagesUpdatedEvent;
import com.pushwoosh.inbox.exception.InboxMessagesException;
import com.pushwoosh.inbox.ui.model.repository.InboxEvent;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.Subscription;
import com.pushwoosh.internal.utils.PWLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000b\u001a\u00020\u00072\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005J\"\u0010\r\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u0017\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0015\u001a\u00020\u0016J \u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0015\u001a\u00020\u0016J\u001a\u0010\u001a\u001a\u00020\u00072\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005J\u000e\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\nJ\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dJ\u0010\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0006H\u0002R \u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/pushwoosh/inbox/ui/model/repository/InboxRepository;", "", "()V", "callbacks", "", "Lkotlin/Function1;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent;", "", "currentInboxEvent", "currentInboxMessages", "Lcom/pushwoosh/inbox/data/InboxMessage;", "addCallback", "callback", "getLoadMessagesCallback", "Lcom/pushwoosh/function/Callback;", "", "Lcom/pushwoosh/inbox/exception/InboxMessagesException;", "isLoadingCachedMessages", "", "loadCachedInbox", "inboxMessage", "limit", "", "loadCachedInboxAsync", "loadInbox", "forceRequest", "removeCallback", "removeItem", "subscribeToEvent", "Lcom/pushwoosh/internal/event/Subscription;", "Lcom/pushwoosh/inbox/event/InboxMessagesUpdatedEvent;", "updateEvent", "inboxEvent", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: InboxRepository.kt */
public final class InboxRepository {
    public static final InboxRepository INSTANCE = new InboxRepository();
    private static final List<Function1<InboxEvent, Unit>> callbacks = new ArrayList();
    private static InboxEvent currentInboxEvent;
    private static final List<InboxMessage> currentInboxMessages = new ArrayList();

    private InboxRepository() {
    }

    public final void addCallback(@NotNull Function1<? super InboxEvent, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        callbacks.add(function1);
        InboxEvent inboxEvent = currentInboxEvent;
        if (inboxEvent != null) {
            if (inboxEvent == null) {
                Intrinsics.throwNpe();
            }
            function1.invoke(inboxEvent);
        }
    }

    public final void removeCallback(@NotNull Function1<? super InboxEvent, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        callbacks.remove(function1);
    }

    @NotNull
    public final Subscription<InboxMessagesUpdatedEvent> subscribeToEvent() {
        Subscription<InboxMessagesUpdatedEvent> subscribe = EventBus.subscribe(InboxMessagesUpdatedEvent.class, InboxRepository$subscribeToEvent$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "EventBus.subscribe(Inbox… deleted))\n            })");
        return subscribe;
    }

    public final void loadInbox(boolean z, @Nullable InboxMessage inboxMessage, int i) {
        updateEvent(new InboxEvent.Loading());
        PushwooshInbox.loadMessages(getLoadMessagesCallback(false), inboxMessage, i);
    }

    @NotNull
    public final Collection<InboxMessage> loadCachedInbox(@Nullable InboxMessage inboxMessage, int i) {
        Collection<InboxMessage> loadCachedMessages = PushwooshInbox.loadCachedMessages(inboxMessage, i);
        Intrinsics.checkExpressionValueIsNotNull(loadCachedMessages, "PushwooshInbox.loadCache…ages(inboxMessage, limit)");
        return loadCachedMessages;
    }

    public final void loadCachedInboxAsync(@Nullable InboxMessage inboxMessage, int i) {
        PushwooshInbox.loadCachedMessages(getLoadMessagesCallback(true), inboxMessage, i);
    }

    private final Callback<Collection<InboxMessage>, InboxMessagesException> getLoadMessagesCallback(boolean z) {
        return new InboxRepository$getLoadMessagesCallback$1(z);
    }

    /* access modifiers changed from: private */
    public final void updateEvent(InboxEvent inboxEvent) {
        PWLog.noise("updateEvent", "InboxEvent: " + inboxEvent.getClass().getName());
        currentInboxEvent = inboxEvent;
        Iterator<T> it = callbacks.iterator();
        while (it.hasNext()) {
            it.next().invoke(inboxEvent);
        }
    }

    public final void removeItem(@NotNull InboxMessage inboxMessage) {
        Intrinsics.checkParameterIsNotNull(inboxMessage, "inboxMessage");
        PushwooshInbox.deleteMessage(inboxMessage.getCode());
    }
}
