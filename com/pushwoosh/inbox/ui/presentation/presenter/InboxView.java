package com.pushwoosh.inbox.ui.presentation.presenter;

import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.ui.presentation.data.UserError;
import com.pushwoosh.inbox.ui.presentation.lifecycle.Lifecycle;
import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&¨\u0006\u000e"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/presenter/InboxView;", "Lcom/pushwoosh/inbox/ui/presentation/lifecycle/Lifecycle;", "failedLoadingInboxList", "", "userError", "Lcom/pushwoosh/inbox/ui/presentation/data/UserError;", "hideProgress", "showEmptyView", "showList", "inboxList", "", "Lcom/pushwoosh/inbox/data/InboxMessage;", "showSwipeRefreshProgress", "showTotalProgress", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: InboxPresenter.kt */
public interface InboxView extends Lifecycle {
    void failedLoadingInboxList(@NotNull UserError userError);

    void hideProgress();

    void showEmptyView();

    void showList(@NotNull Collection<? extends InboxMessage> collection);

    void showSwipeRefreshProgress();

    void showTotalProgress();
}
