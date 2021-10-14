package com.pushwoosh.inbox.ui.presentation.view.fragment;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.pushwoosh.inbox.ui.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: InboxFragment.kt */
final class InboxFragment$onViewCreated$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ InboxFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InboxFragment$onViewCreated$2(InboxFragment inboxFragment) {
        super(0);
        this.this$0 = inboxFragment;
    }

    @Override // kotlin.jvm.functions.Function0
    public final void invoke() {
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) this.this$0._$_findCachedViewById(R.id.inboxSwipeRefreshLayout);
        Intrinsics.checkExpressionValueIsNotNull(swipeRefreshLayout, "inboxSwipeRefreshLayout");
        swipeRefreshLayout.setEnabled(false);
    }
}
