package com.pushwoosh.inbox.ui.presentation.view.fragment;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.pushwoosh.inbox.ui.presentation.view.adapter.SimpleItemTouchHelperCallback;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "onRefresh"}, k = 3, mv = {1, 1, 15})
/* compiled from: InboxFragment.kt */
final class InboxFragment$onViewCreated$5 implements SwipeRefreshLayout.OnRefreshListener {
    final /* synthetic */ InboxFragment this$0;

    InboxFragment$onViewCreated$5(InboxFragment inboxFragment) {
        this.this$0 = inboxFragment;
    }

    @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
    public final void onRefresh() {
        InboxFragment.access$getInboxPresenter$p(this.this$0).refreshItems();
        SimpleItemTouchHelperCallback simpleItemTouchHelperCallback = this.this$0.callback;
        if (simpleItemTouchHelperCallback != null) {
            simpleItemTouchHelperCallback.setTouchable(false);
        }
    }
}
