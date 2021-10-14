package com.pushwoosh.inbox.ui.presentation.view.style;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003\u001a\u0010\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0003H\u0002\u001a\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003¨\u0006\t"}, d2 = {"getAdaptiveRippleDrawable", "Landroid/graphics/drawable/Drawable;", "normalColor", "", "pressedColor", "getRippleMask", "color", "getStateListDrawable", "Landroid/graphics/drawable/StateListDrawable;", "pushwoosh-inbox-ui_release"}, k = 2, mv = {1, 1, 15})
/* compiled from: DrawableUtils.kt */
public final class DrawableUtilsKt {
    @NotNull
    public static final Drawable getAdaptiveRippleDrawable(int i, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new RippleDrawable(ColorStateList.valueOf(i2), new ColorDrawable(i), getRippleMask(i));
        }
        return getStateListDrawable(i, i2);
    }

    private static final Drawable getRippleMask(int i) {
        float[] fArr = new float[8];
        for (int i2 = 0; i2 < 8; i2++) {
            fArr[i2] = 3.0f;
        }
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, null, null));
        Paint paint = shapeDrawable.getPaint();
        Intrinsics.checkExpressionValueIsNotNull(paint, "shapeDrawable.paint");
        paint.setColor(i);
        return shapeDrawable;
    }

    @NotNull
    public static final StateListDrawable getStateListDrawable(int i, int i2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(i2));
        stateListDrawable.addState(new int[]{16842908}, new ColorDrawable(i2));
        stateListDrawable.addState(new int[]{16843518}, new ColorDrawable(i2));
        stateListDrawable.addState(new int[0], new ColorDrawable(i));
        return stateListDrawable;
    }
}
