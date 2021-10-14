package com.pushwoosh.inbox.ui.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0003\u001a\n\u0010\b\u001a\u00020\t*\u00020\n\u001a\u0012\u0010\u000b\u001a\u00020\f*\u00020\u00032\u0006\u0010\r\u001a\u00020\f\u001a\u0012\u0010\u000e\u001a\u00020\f*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\f¨\u0006\u0010"}, d2 = {"getBitmap", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "drawableId", "", "vectorDrawable", "Landroid/graphics/drawable/VectorDrawable;", "clear", "", "Landroid/view/View;", "dpFromPx", "", "px", "pxFromDp", "dp", "pushwoosh-inbox-ui_release"}, k = 2, mv = {1, 1, 15})
/* compiled from: ViewExtension.kt */
public final class ViewExtensionKt {
    public static final void clear(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$clear");
        view.setAlpha(1.0f);
        view.setScaleY(1.0f);
        view.setScaleX(1.0f);
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setPivotX(((float) view.getMeasuredWidth()) / 2.0f);
        view.setPivotY(((float) view.getMeasuredHeight()) / 2.0f);
        ViewPropertyAnimator interpolator = view.animate().setInterpolator(null);
        Intrinsics.checkExpressionValueIsNotNull(interpolator, "animate().setInterpolator(null)");
        interpolator.setStartDelay(0);
    }

    @Nullable
    public static final Bitmap getBitmap(@Nullable Context context, int i) {
        if (context == null) {
            return null;
        }
        Drawable drawable = ContextCompat.getDrawable(context, i);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable instanceof VectorDrawable) {
            return getBitmap((VectorDrawable) drawable);
        }
        throw new IllegalArgumentException("unsupported drawable type");
    }

    @TargetApi(21)
    private static final Bitmap getBitmap(VectorDrawable vectorDrawable) {
        Bitmap createBitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "bitmap");
        return createBitmap;
    }

    public static final float dpFromPx(@NotNull Context context, float f) {
        Intrinsics.checkParameterIsNotNull(context, "$this$dpFromPx");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return f / resources.getDisplayMetrics().density;
    }

    public static final float pxFromDp(@NotNull Context context, float f) {
        Intrinsics.checkParameterIsNotNull(context, "$this$pxFromDp");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return f * resources.getDisplayMetrics().density;
    }
}
