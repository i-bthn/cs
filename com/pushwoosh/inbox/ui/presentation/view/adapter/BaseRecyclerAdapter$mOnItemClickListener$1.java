package com.pushwoosh.inbox.ui.presentation.view.adapter;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0001\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "VH", "Lcom/pushwoosh/inbox/ui/presentation/view/adapter/BaseRecyclerAdapter$ViewHolder;", "Model", "<anonymous parameter 0>", "Landroid/view/View;", "<anonymous parameter 1>", "", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: BaseRecyclerAdapter.kt */
final class BaseRecyclerAdapter$mOnItemClickListener$1 extends Lambda implements Function2<View, Integer, Unit> {
    public static final BaseRecyclerAdapter$mOnItemClickListener$1 INSTANCE = new BaseRecyclerAdapter$mOnItemClickListener$1();

    BaseRecyclerAdapter$mOnItemClickListener$1() {
        super(2);
    }

    public final void invoke(@NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "<anonymous parameter 0>");
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(View view, Integer num) {
        invoke(view, num.intValue());
        return Unit.INSTANCE;
    }
}
