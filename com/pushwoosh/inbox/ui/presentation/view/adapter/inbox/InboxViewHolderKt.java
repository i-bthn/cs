package com.pushwoosh.inbox.ui.presentation.view.adapter.inbox;

import com.pushwoosh.inbox.data.InboxMessageType;
import com.pushwoosh.inbox.ui.R;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"getResource", "", "Lcom/pushwoosh/inbox/data/InboxMessageType;", "pushwoosh-inbox-ui_release"}, k = 2, mv = {1, 1, 15})
/* compiled from: InboxViewHolder.kt */
public final class InboxViewHolderKt {
    public static final int getResource(@NotNull InboxMessageType inboxMessageType) {
        Intrinsics.checkParameterIsNotNull(inboxMessageType, "$this$getResource");
        switch (inboxMessageType) {
            case PLAIN:
                return R.drawable.inbox_ic_app_open;
            case RICH_MEDIA:
                return R.drawable.inbox_ic_rich_media;
            case URL:
                return R.drawable.inbox_ic_remote_url;
            case REMOTE_URL:
                return R.drawable.inbox_ic_remote_url;
            case DEEP_LINK:
                return R.drawable.inbox_ic_deep_link;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
