package com.pushwoosh.inbox.ui.presentation.view.adapter.inbox;

import android.view.View;
import android.widget.ImageView;
import com.pushwoosh.inbox.ui.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: InboxViewHolder.kt */
public final class InboxViewHolder$fillView$1 implements View.OnClickListener {
    final /* synthetic */ String $bannerUrl;
    final /* synthetic */ InboxViewHolder this$0;

    InboxViewHolder$fillView$1(InboxViewHolder inboxViewHolder, String str) {
        this.this$0 = inboxViewHolder;
        this.$bannerUrl = str;
    }

    public final void onClick(View view) {
        Function2<String, View, Unit> attachmentClickListener = this.this$0.getAttachmentClickListener();
        String str = this.$bannerUrl;
        View view2 = this.this$0.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view2, "itemView");
        ImageView imageView = (ImageView) view2.findViewById(R.id.inboxBannerImage);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "itemView.inboxBannerImage");
        attachmentClickListener.invoke(str, imageView);
    }
}
