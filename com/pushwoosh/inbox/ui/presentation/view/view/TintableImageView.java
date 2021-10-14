package com.pushwoosh.inbox.ui.presentation.view.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

public class TintableImageView extends AppCompatImageView {
    private ColorStateList tint;

    public TintableImageView(Context context) {
        super(context);
    }

    public TintableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TintableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.AppCompatImageView
    public void drawableStateChanged() {
        super.drawableStateChanged();
        ColorStateList colorStateList = this.tint;
        if (colorStateList != null && colorStateList.isStateful()) {
            updateTintColor();
        }
    }

    public void setColorFilter(ColorStateList colorStateList) {
        this.tint = colorStateList;
        super.setColorFilter(colorStateList.getColorForState(getDrawableState(), 0));
    }

    private void updateTintColor() {
        setColorFilter(this.tint.getColorForState(getDrawableState(), 0));
    }
}
