package com.pushwoosh.inbox.ui.presentation.view.adapter.inbox;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.data.InboxMessageType;
import com.pushwoosh.inbox.ui.presentation.view.adapter.BaseRecyclerAdapter;
import com.pushwoosh.inbox.ui.presentation.view.adapter.ItemTouchHelperAdapter;
import com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProvider;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 22\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u00012B/\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\n¢\u0006\u0002\u0010\u000eJ\u001e\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0014H\u0014J\u0010\u0010+\u001a\u00020\u00142\u0006\u0010,\u001a\u00020\u0014H\u0016J\u001e\u0010-\u001a\u00020\r2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010,\u001a\u00020\u0014H\u0016J\u0010\u0010/\u001a\u00020\r2\u0006\u0010,\u001a\u00020\u0014H\u0016J\b\u00100\u001a\u00020\rH\u0016J\b\u00101\u001a\u00020\rH\u0016R,\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R*\u0010\u0015\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\r\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR*\u0010\u001b\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\r\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\"\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010$\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#¨\u00063"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/adapter/inbox/InboxAdapter;", "Lcom/pushwoosh/inbox/ui/presentation/view/adapter/BaseRecyclerAdapter;", "Lcom/pushwoosh/inbox/ui/presentation/view/adapter/BaseRecyclerAdapter$ViewHolder;", "Lcom/pushwoosh/inbox/data/InboxMessage;", "Lcom/pushwoosh/inbox/ui/presentation/view/adapter/ItemTouchHelperAdapter;", "context", "Landroid/content/Context;", "colorSchemeProvider", "Lcom/pushwoosh/inbox/ui/presentation/view/style/ColorSchemeProvider;", "attachmentClickListener", "Lkotlin/Function2;", "", "Landroid/view/View;", "", "(Landroid/content/Context;Lcom/pushwoosh/inbox/ui/presentation/view/style/ColorSchemeProvider;Lkotlin/jvm/functions/Function2;)V", "getAttachmentClickListener", "()Lkotlin/jvm/functions/Function2;", "setAttachmentClickListener", "(Lkotlin/jvm/functions/Function2;)V", "lastPosition", "", "onItemClick", "Lkotlin/Function1;", "getOnItemClick", "()Lkotlin/jvm/functions/Function1;", "setOnItemClick", "(Lkotlin/jvm/functions/Function1;)V", "onItemRemoved", "getOnItemRemoved", "setOnItemRemoved", "onItemStartSwipe", "Lkotlin/Function0;", "getOnItemStartSwipe", "()Lkotlin/jvm/functions/Function0;", "setOnItemStartSwipe", "(Lkotlin/jvm/functions/Function0;)V", "onItemStopSwipe", "getOnItemStopSwipe", "setOnItemStopSwipe", "createViewHolderInstance", "parent", "Landroid/view/ViewGroup;", "viewType", "getItemViewType", "position", "onBindViewHolder", "holder", "onItemSwiped", "startSwipe", "stopSwipe", "Companion", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: InboxAdapter.kt */
public final class InboxAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.ViewHolder<InboxMessage>, InboxMessage> implements ItemTouchHelperAdapter {
    public static final Companion Companion = new Companion(null);
    public static final int TEXT_VIEW_TYPE = 0;
    @NotNull
    private Function2<? super String, ? super View, Unit> attachmentClickListener;
    private final ColorSchemeProvider colorSchemeProvider;
    private int lastPosition = -1;
    @Nullable
    private Function1<? super InboxMessage, Unit> onItemClick;
    @Nullable
    private Function1<? super InboxMessage, Unit> onItemRemoved;
    @Nullable
    private Function0<Unit> onItemStartSwipe;
    @Nullable
    private Function0<Unit> onItemStopSwipe;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InboxAdapter(@NotNull Context context, @NotNull ColorSchemeProvider colorSchemeProvider2, @NotNull Function2<? super String, ? super View, Unit> function2) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(colorSchemeProvider2, "colorSchemeProvider");
        Intrinsics.checkParameterIsNotNull(function2, "attachmentClickListener");
        this.colorSchemeProvider = colorSchemeProvider2;
        this.attachmentClickListener = function2;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/adapter/inbox/InboxAdapter$Companion;", "", "()V", "TEXT_VIEW_TYPE", "", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: InboxAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: kotlin.jvm.functions.Function1<? super com.pushwoosh.inbox.data.InboxMessage, kotlin.Unit>, kotlin.jvm.functions.Function1<com.pushwoosh.inbox.data.InboxMessage, kotlin.Unit> */
    @Nullable
    public final Function1<InboxMessage, Unit> getOnItemRemoved() {
        return this.onItemRemoved;
    }

    public final void setOnItemRemoved(@Nullable Function1<? super InboxMessage, Unit> function1) {
        this.onItemRemoved = function1;
    }

    @Nullable
    public final Function0<Unit> getOnItemStartSwipe() {
        return this.onItemStartSwipe;
    }

    public final void setOnItemStartSwipe(@Nullable Function0<Unit> function0) {
        this.onItemStartSwipe = function0;
    }

    @Nullable
    public final Function0<Unit> getOnItemStopSwipe() {
        return this.onItemStopSwipe;
    }

    public final void setOnItemStopSwipe(@Nullable Function0<Unit> function0) {
        this.onItemStopSwipe = function0;
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: kotlin.jvm.functions.Function1<? super com.pushwoosh.inbox.data.InboxMessage, kotlin.Unit>, kotlin.jvm.functions.Function1<com.pushwoosh.inbox.data.InboxMessage, kotlin.Unit> */
    @Nullable
    public final Function1<InboxMessage, Unit> getOnItemClick() {
        return this.onItemClick;
    }

    public final void setOnItemClick(@Nullable Function1<? super InboxMessage, Unit> function1) {
        this.onItemClick = function1;
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: kotlin.jvm.functions.Function2<? super java.lang.String, ? super android.view.View, kotlin.Unit>, kotlin.jvm.functions.Function2<java.lang.String, android.view.View, kotlin.Unit> */
    @NotNull
    public final Function2<String, View, Unit> getAttachmentClickListener() {
        return this.attachmentClickListener;
    }

    public final void setAttachmentClickListener(@NotNull Function2<? super String, ? super View, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.attachmentClickListener = function2;
    }

    @Override // com.pushwoosh.inbox.ui.presentation.view.adapter.ItemTouchHelperAdapter
    public void startSwipe() {
        Function0<Unit> function0 = this.onItemStartSwipe;
        if (function0 != null) {
            function0.invoke();
        }
    }

    @Override // com.pushwoosh.inbox.ui.presentation.view.adapter.ItemTouchHelperAdapter
    public void stopSwipe() {
        Function0<Unit> function0 = this.onItemStopSwipe;
        if (function0 != null) {
            function0.invoke();
        }
    }

    @Override // com.pushwoosh.inbox.ui.presentation.view.adapter.ItemTouchHelperAdapter
    public void onItemSwiped(int i) {
        Function1<? super InboxMessage, Unit> function1 = this.onItemRemoved;
        if (function1 != null) {
            function1.invoke(getItem(i));
        }
        getCollection().remove(i);
        notifyItemRemoved(i);
    }

    @Override // com.pushwoosh.inbox.ui.presentation.view.adapter.BaseRecyclerAdapter
    public void onBindViewHolder(@NotNull BaseRecyclerAdapter.ViewHolder<InboxMessage> viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        super.onBindViewHolder((BaseRecyclerAdapter.ViewHolder) viewHolder, i);
        viewHolder.itemView.setOnClickListener(new InboxAdapter$onBindViewHolder$1(this, i));
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inbox.ui.presentation.view.adapter.BaseRecyclerAdapter
    @NotNull
    public BaseRecyclerAdapter.ViewHolder<InboxMessage> createViewHolderInstance(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        if (i == 0) {
            return new InboxViewHolder(viewGroup, this, this.colorSchemeProvider, this.attachmentClickListener);
        }
        throw new IllegalArgumentException("Unknown type: " + i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        InboxMessageType inboxMessageType;
        InboxMessage inboxMessage = (InboxMessage) getItem(i);
        if (inboxMessage == null || (inboxMessageType = inboxMessage.getType()) == null) {
            inboxMessageType = InboxMessageType.PLAIN;
        }
        switch (inboxMessageType) {
            case PLAIN:
            case RICH_MEDIA:
            case URL:
            case REMOTE_URL:
            case DEEP_LINK:
                return 0;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
