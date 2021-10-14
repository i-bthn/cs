package com.pushwoosh.inbox.ui;

import androidx.fragment.app.Fragment;
import com.pushwoosh.inbox.ui.presentation.view.fragment.InboxFragment;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/pushwoosh/inbox/ui/PushwooshInboxUi;", "", "()V", "onMessageClickListener", "Lcom/pushwoosh/inbox/ui/OnInboxMessageClickListener;", "getOnMessageClickListener", "()Lcom/pushwoosh/inbox/ui/OnInboxMessageClickListener;", "setOnMessageClickListener", "(Lcom/pushwoosh/inbox/ui/OnInboxMessageClickListener;)V", "createInboxFragment", "Landroidx/fragment/app/Fragment;", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: PushwooshInboxUi.kt */
public final class PushwooshInboxUi {
    public static final PushwooshInboxUi INSTANCE = new PushwooshInboxUi();
    @Nullable
    private static OnInboxMessageClickListener onMessageClickListener;

    private PushwooshInboxUi() {
    }

    @Nullable
    public final OnInboxMessageClickListener getOnMessageClickListener() {
        return onMessageClickListener;
    }

    public final void setOnMessageClickListener(@Nullable OnInboxMessageClickListener onInboxMessageClickListener) {
        onMessageClickListener = onInboxMessageClickListener;
    }

    @NotNull
    public final Fragment createInboxFragment() {
        return new InboxFragment();
    }
}
