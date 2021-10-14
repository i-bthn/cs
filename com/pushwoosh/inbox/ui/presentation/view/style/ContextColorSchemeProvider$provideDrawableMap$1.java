package com.pushwoosh.inbox.ui.presentation.view.style;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", FirebaseAnalytics.Param.INDEX, "", "attr", "typedArray", "Landroid/content/res/TypedArray;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: ContextColorSchemeProvider.kt */
final class ContextColorSchemeProvider$provideDrawableMap$1 extends Lambda implements Function3<Integer, Integer, TypedArray, Unit> {
    final /* synthetic */ Map $result;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ContextColorSchemeProvider$provideDrawableMap$1(Map map) {
        super(3);
        this.$result = map;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2, TypedArray typedArray) {
        invoke(num.intValue(), num2.intValue(), typedArray);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, int i2, @NotNull TypedArray typedArray) {
        Intrinsics.checkParameterIsNotNull(typedArray, "typedArray");
        Drawable drawable = typedArray.getDrawable(i);
        if (drawable != null) {
            this.$result.put(Integer.valueOf(i2), drawable);
        }
    }
}
