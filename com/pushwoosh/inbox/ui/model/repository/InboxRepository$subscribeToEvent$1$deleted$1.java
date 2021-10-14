package com.pushwoosh.inbox.ui.model.repository;

import androidx.core.app.NotificationCompat;
import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.event.InboxMessagesUpdatedEvent;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/pushwoosh/inbox/data/InboxMessage;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: InboxRepository.kt */
final class InboxRepository$subscribeToEvent$1$deleted$1 extends Lambda implements Function1<InboxMessage, Boolean> {
    final /* synthetic */ InboxMessagesUpdatedEvent $event;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InboxRepository$subscribeToEvent$1$deleted$1(InboxMessagesUpdatedEvent inboxMessagesUpdatedEvent) {
        super(1);
        this.$event = inboxMessagesUpdatedEvent;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Boolean invoke(InboxMessage inboxMessage) {
        return Boolean.valueOf(invoke(inboxMessage));
    }

    public final boolean invoke(@NotNull InboxMessage inboxMessage) {
        Intrinsics.checkParameterIsNotNull(inboxMessage, "it");
        InboxMessagesUpdatedEvent inboxMessagesUpdatedEvent = this.$event;
        Intrinsics.checkExpressionValueIsNotNull(inboxMessagesUpdatedEvent, NotificationCompat.CATEGORY_EVENT);
        return inboxMessagesUpdatedEvent.getMessagesDeleted().contains(inboxMessage.getCode());
    }
}
