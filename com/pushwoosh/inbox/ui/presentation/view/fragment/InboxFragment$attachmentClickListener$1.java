package com.pushwoosh.inbox.ui.presentation.view.fragment;

import android.view.View;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", ImagesContract.URL, "", "view", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: InboxFragment.kt */
public final class InboxFragment$attachmentClickListener$1 extends Lambda implements Function2<String, View, Unit> {
    final /* synthetic */ InboxFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InboxFragment$attachmentClickListener$1(InboxFragment inboxFragment) {
        super(2);
        this.this$0 = inboxFragment;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(String str, View view) {
        invoke(str, view);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull String str, @NotNull View view) {
        Intrinsics.checkParameterIsNotNull(str, ImagesContract.URL);
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.this$0.onAttachmentClicked(str, view);
    }
}
