package com.pushwoosh.inbox.ui.presentation.presenter;

import android.os.Bundle;
import com.pushwoosh.inbox.PushwooshInbox;
import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.event.InboxMessagesUpdatedEvent;
import com.pushwoosh.inbox.ui.OnInboxMessageClickListener;
import com.pushwoosh.inbox.ui.PushwooshInboxStyle;
import com.pushwoosh.inbox.ui.PushwooshInboxUi;
import com.pushwoosh.inbox.ui.model.repository.InboxEvent;
import com.pushwoosh.inbox.ui.model.repository.InboxRepository;
import com.pushwoosh.inbox.ui.presentation.data.UserError;
import com.pushwoosh.internal.event.Subscription;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 &2\u00020\u0001:\u0001&B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0016\u001a\u00020\bH\u0002J\b\u0010\u0017\u001a\u00020\bH\u0002J\u0012\u0010\u0018\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u0015H\u0016J\u000e\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u0010J\u0010\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u001aH\u0016J\b\u0010!\u001a\u00020\bH\u0016J\u0006\u0010\"\u001a\u00020\bJ\u0010\u0010#\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0010J\u0010\u0010$\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0014J\b\u0010%\u001a\u00020\bH\u0002R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/presenter/InboxPresenter;", "Lcom/pushwoosh/inbox/ui/presentation/presenter/BasePresenter;", "inboxView", "Lcom/pushwoosh/inbox/ui/presentation/presenter/InboxView;", "(Lcom/pushwoosh/inbox/ui/presentation/presenter/InboxView;)V", "callback", "Lkotlin/Function1;", "Lcom/pushwoosh/inbox/ui/model/repository/InboxEvent;", "", "getCallback", "()Lkotlin/jvm/functions/Function1;", "inboxEvent", "inboxViewRef", "Ljava/lang/ref/WeakReference;", "messageList", "Ljava/util/ArrayList;", "Lcom/pushwoosh/inbox/data/InboxMessage;", "subscription", "Lcom/pushwoosh/internal/event/Subscription;", "Lcom/pushwoosh/inbox/event/InboxMessagesUpdatedEvent;", "swipeToRefresh", "", "implementState", "loadInboxMessages", "onCreate", "bundle", "Landroid/os/Bundle;", "onDestroy", "isFinished", "onItemClick", "inboxMessage", "onSaveInstanceState", "out", "onViewCreated", "refreshItems", "removeItem", "restoreState", "showList", "Companion", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: InboxPresenter.kt */
public final class InboxPresenter extends BasePresenter {
    public static final Companion Companion = new Companion(null);
    private static final String KEY_SWIPE_REFRESH = "KEY_SWIPE_REFRESH";
    private InboxEvent inboxEvent;
    private final WeakReference<InboxView> inboxViewRef;
    private final ArrayList<InboxMessage> messageList = new ArrayList<>();
    private Subscription<InboxMessagesUpdatedEvent> subscription;
    private boolean swipeToRefresh;

    public InboxPresenter(@NotNull InboxView inboxView) {
        Intrinsics.checkParameterIsNotNull(inboxView, "inboxView");
        this.inboxViewRef = new WeakReference<>(inboxView);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/presenter/InboxPresenter$Companion;", "", "()V", InboxPresenter.KEY_SWIPE_REFRESH, "", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: InboxPresenter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final Function1<InboxEvent, Unit> getCallback() {
        return new InboxPresenter$callback$1(this);
    }

    @Override // com.pushwoosh.inbox.ui.presentation.presenter.BasePresenter, com.pushwoosh.inbox.ui.presentation.lifecycle.LifecycleListener
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.subscription = InboxRepository.INSTANCE.subscribeToEvent();
        InboxRepository.INSTANCE.addCallback(getCallback());
        this.inboxEvent = new InboxEvent.OnCreate();
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inbox.ui.presentation.presenter.BasePresenter
    public void restoreState(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        this.swipeToRefresh = bundle.getBoolean(KEY_SWIPE_REFRESH, this.swipeToRefresh);
        this.inboxEvent = new InboxEvent.RestoreState();
        implementState();
    }

    @Override // com.pushwoosh.inbox.ui.presentation.presenter.BasePresenter, com.pushwoosh.inbox.ui.presentation.lifecycle.LifecycleListener
    public void onViewCreated() {
        InboxView inboxView;
        super.onViewCreated();
        if (!this.messageList.isEmpty() && getRestore() && (inboxView = this.inboxViewRef.get()) != null) {
            inboxView.showList(this.messageList);
        }
        implementState();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final void implementState() {
        if (getViewEnable()) {
            InboxEvent inboxEvent2 = this.inboxEvent;
            if (inboxEvent2 instanceof InboxEvent.OnCreate) {
                loadInboxMessages();
            } else if (inboxEvent2 instanceof InboxEvent.Loading) {
                if (!this.swipeToRefresh) {
                    InboxView inboxView = this.inboxViewRef.get();
                    if (inboxView != null) {
                        inboxView.showTotalProgress();
                        return;
                    }
                    return;
                }
                InboxView inboxView2 = this.inboxViewRef.get();
                if (inboxView2 != null) {
                    inboxView2.showSwipeRefreshProgress();
                }
            } else if (inboxEvent2 instanceof InboxEvent.FinishLoading) {
                InboxView inboxView3 = this.inboxViewRef.get();
                if (inboxView3 != null) {
                    inboxView3.hideProgress();
                }
            } else if (inboxEvent2 instanceof InboxEvent.FailedLoading) {
                InboxView inboxView4 = this.inboxViewRef.get();
                if (inboxView4 != null) {
                    inboxView4.failedLoadingInboxList(new UserError(null, PushwooshInboxStyle.INSTANCE.getListErrorMessage(), 1, null));
                }
                this.swipeToRefresh = false;
            } else if (inboxEvent2 instanceof InboxEvent.SuccessLoadingCache) {
                this.messageList.clear();
                this.messageList.addAll(((InboxEvent.SuccessLoadingCache) inboxEvent2).getInboxMessages());
                showList();
                InboxRepository.INSTANCE.loadInbox(!getRestore(), null, -1);
            } else if (inboxEvent2 instanceof InboxEvent.SuccessLoading) {
                this.messageList.clear();
                this.messageList.addAll(((InboxEvent.SuccessLoading) inboxEvent2).getInboxMessages());
                showList();
            } else if (inboxEvent2 instanceof InboxEvent.InboxEmpty) {
                this.messageList.clear();
                InboxView inboxView5 = this.inboxViewRef.get();
                if (inboxView5 != null) {
                    inboxView5.showEmptyView();
                }
            } else if (inboxEvent2 instanceof InboxEvent.InboxMessagesUpdated) {
                InboxEvent.InboxMessagesUpdated inboxMessagesUpdated = (InboxEvent.InboxMessagesUpdated) inboxEvent2;
                this.messageList.removeAll(inboxMessagesUpdated.getDeleted());
                this.messageList.addAll(inboxMessagesUpdated.getAddedInboxMessages());
                for (T t : inboxMessagesUpdated.getUpdatedInboxMessages()) {
                    if (this.messageList.contains(t)) {
                        ArrayList<InboxMessage> arrayList = this.messageList;
                        arrayList.set(arrayList.indexOf(t), t);
                    } else {
                        this.messageList.add(t);
                    }
                }
                showList();
            }
        }
    }

    private final void loadInboxMessages() {
        this.messageList.clear();
        Collection<InboxMessage> loadCachedInbox = InboxRepository.INSTANCE.loadCachedInbox(null, 40);
        if (!loadCachedInbox.isEmpty()) {
            this.messageList.addAll(loadCachedInbox);
        }
        showList();
        InboxRepository.INSTANCE.loadCachedInboxAsync(null, -1);
    }

    private final void showList() {
        Collections.sort(this.messageList, InboxPresenter$showList$1.INSTANCE);
        InboxView inboxView = this.inboxViewRef.get();
        if (inboxView != null) {
            inboxView.showList(this.messageList);
        }
    }

    @Override // com.pushwoosh.inbox.ui.presentation.presenter.BasePresenter, com.pushwoosh.inbox.ui.presentation.lifecycle.LifecycleListener
    public void onSaveInstanceState(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "out");
        bundle.putBoolean(KEY_SWIPE_REFRESH, this.swipeToRefresh);
    }

    @Override // com.pushwoosh.inbox.ui.presentation.presenter.BasePresenter, com.pushwoosh.inbox.ui.presentation.lifecycle.LifecycleListener
    public void onDestroy(boolean z) {
        Subscription<InboxMessagesUpdatedEvent> subscription2 = this.subscription;
        if (subscription2 != null) {
            subscription2.unsubscribe();
        }
        InboxRepository.INSTANCE.removeCallback(getCallback());
    }

    public final void removeItem(@Nullable InboxMessage inboxMessage) {
        if (inboxMessage != null) {
            this.messageList.remove(inboxMessage);
            InboxRepository.INSTANCE.removeItem(inboxMessage);
        }
    }

    public final void refreshItems() {
        this.swipeToRefresh = true;
        InboxRepository.INSTANCE.loadInbox(true, null, -1);
    }

    public final void onItemClick(@NotNull InboxMessage inboxMessage) {
        Intrinsics.checkParameterIsNotNull(inboxMessage, "inboxMessage");
        PushwooshInbox.performAction(inboxMessage.getCode());
        OnInboxMessageClickListener onMessageClickListener = PushwooshInboxUi.INSTANCE.getOnMessageClickListener();
        if (onMessageClickListener != null) {
            onMessageClickListener.onInboxMessageClick(inboxMessage);
        }
    }
}
