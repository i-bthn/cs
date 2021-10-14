package com.pushwoosh.inbox.ui.model.repository;

import com.pushwoosh.inbox.data.InboxMessage;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\t\u0003\u0004\u0005\u0006\u0007\b\t\n\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0001\t\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014¨\u0006\u0015"}, d2 = {"Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent;", "", "()V", "FailedLoading", "FinishLoading", "InboxEmpty", "InboxMessagesUpdated", "Loading", "OnCreate", "RestoreState", "SuccessLoading", "SuccessLoadingCache", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$OnCreate;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$Loading;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$FinishLoading;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$FailedLoading;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$SuccessLoadingCache;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$SuccessLoading;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$InboxEmpty;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$InboxMessagesUpdated;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$RestoreState;", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: InboxRepository.kt */
public abstract class InboxEvent {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$OnCreate;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent;", "()V", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: InboxRepository.kt */
    public static final class OnCreate extends InboxEvent {
        public OnCreate() {
            super(null);
        }
    }

    private InboxEvent() {
    }

    public /* synthetic */ InboxEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$Loading;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent;", "()V", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: InboxRepository.kt */
    public static final class Loading extends InboxEvent {
        public Loading() {
            super(null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$FinishLoading;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent;", "()V", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: InboxRepository.kt */
    public static final class FinishLoading extends InboxEvent {
        public FinishLoading() {
            super(null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$FailedLoading;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent;", "error", "", "(Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: InboxRepository.kt */
    public static final class FailedLoading extends InboxEvent {
        @NotNull
        private final Throwable error;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FailedLoading(@NotNull Throwable th) {
            super(null);
            Intrinsics.checkParameterIsNotNull(th, "error");
            this.error = th;
        }

        @NotNull
        public final Throwable getError() {
            return this.error;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$SuccessLoadingCache;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent;", "inboxMessages", "", "Lcom/pushwoosh/inbox/data/InboxMessage;", "(Ljava/util/Collection;)V", "getInboxMessages", "()Ljava/util/Collection;", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: InboxRepository.kt */
    public static final class SuccessLoadingCache extends InboxEvent {
        @NotNull
        private final Collection<InboxMessage> inboxMessages;

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.Collection<? extends com.pushwoosh.inbox.data.InboxMessage> */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SuccessLoadingCache(@NotNull Collection<? extends InboxMessage> collection) {
            super(null);
            Intrinsics.checkParameterIsNotNull(collection, "inboxMessages");
            this.inboxMessages = collection;
        }

        @NotNull
        public final Collection<InboxMessage> getInboxMessages() {
            return this.inboxMessages;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$SuccessLoading;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent;", "inboxMessages", "", "Lcom/pushwoosh/inbox/data/InboxMessage;", "(Ljava/util/Collection;)V", "getInboxMessages", "()Ljava/util/Collection;", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: InboxRepository.kt */
    public static final class SuccessLoading extends InboxEvent {
        @NotNull
        private final Collection<InboxMessage> inboxMessages;

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.Collection<? extends com.pushwoosh.inbox.data.InboxMessage> */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SuccessLoading(@NotNull Collection<? extends InboxMessage> collection) {
            super(null);
            Intrinsics.checkParameterIsNotNull(collection, "inboxMessages");
            this.inboxMessages = collection;
        }

        @NotNull
        public final Collection<InboxMessage> getInboxMessages() {
            return this.inboxMessages;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$InboxEmpty;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent;", "()V", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: InboxRepository.kt */
    public static final class InboxEmpty extends InboxEvent {
        public InboxEmpty() {
            super(null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$InboxMessagesUpdated;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent;", "addedInboxMessages", "", "Lcom/pushwoosh/inbox/data/InboxMessage;", "updatedInboxMessages", "deleted", "(Ljava/util/Collection;Ljava/util/Collection;Ljava/util/Collection;)V", "getAddedInboxMessages", "()Ljava/util/Collection;", "getDeleted", "getUpdatedInboxMessages", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: InboxRepository.kt */
    public static final class InboxMessagesUpdated extends InboxEvent {
        @NotNull
        private final Collection<InboxMessage> addedInboxMessages;
        @NotNull
        private final Collection<InboxMessage> deleted;
        @NotNull
        private final Collection<InboxMessage> updatedInboxMessages;

        @NotNull
        public final Collection<InboxMessage> getAddedInboxMessages() {
            return this.addedInboxMessages;
        }

        @NotNull
        public final Collection<InboxMessage> getUpdatedInboxMessages() {
            return this.updatedInboxMessages;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.Collection<? extends com.pushwoosh.inbox.data.InboxMessage> */
        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.Collection<? extends com.pushwoosh.inbox.data.InboxMessage> */
        /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.Collection<? extends com.pushwoosh.inbox.data.InboxMessage> */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public InboxMessagesUpdated(@NotNull Collection<? extends InboxMessage> collection, @NotNull Collection<? extends InboxMessage> collection2, @NotNull Collection<? extends InboxMessage> collection3) {
            super(null);
            Intrinsics.checkParameterIsNotNull(collection, "addedInboxMessages");
            Intrinsics.checkParameterIsNotNull(collection2, "updatedInboxMessages");
            Intrinsics.checkParameterIsNotNull(collection3, "deleted");
            this.addedInboxMessages = collection;
            this.updatedInboxMessages = collection2;
            this.deleted = collection3;
        }

        @NotNull
        public final Collection<InboxMessage> getDeleted() {
            return this.deleted;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent$RestoreState;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent;", "()V", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: InboxRepository.kt */
    public static final class RestoreState extends InboxEvent {
        public RestoreState() {
            super(null);
        }
    }
}
