package com.pushwoosh.inbox.ui.presentation.view.adapter.inbox;

import android.view.View;
import com.pushwoosh.inbox.data.InboxMessage;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: InboxAdapter.kt */
final class InboxAdapter$onBindViewHolder$1 implements View.OnClickListener {
    final /* synthetic */ int $position;
    final /* synthetic */ InboxAdapter this$0;

    InboxAdapter$onBindViewHolder$1(InboxAdapter inboxAdapter, int i) {
        this.this$0 = inboxAdapter;
        this.$position = i;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    public final void onClick(View view) {
        Function1<InboxMessage, Unit> onItemClick = this.this$0.getOnItemClick();
        if (onItemClick != null) {
            onItemClick.invoke(this.this$0.getItem(this.$position));
        }
    }
}
