package com.google.android.material.chip;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.AnimatorRes;
import androidx.annotation.BoolRes;
import androidx.annotation.CallSuper;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.ripple.RippleUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Chip extends AppCompatCheckBox implements ChipDrawable.Delegate {
    private static final int CLOSE_ICON_VIRTUAL_ID = 0;
    private static final Rect EMPTY_BOUNDS = new Rect();
    private static final String NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";
    private static final int[] SELECTED_STATE = {16842913};
    private static final String TAG = "Chip";
    @Nullable
    private ChipDrawable chipDrawable;
    private boolean closeIconFocused;
    private boolean closeIconHovered;
    private boolean closeIconPressed;
    private boolean deferredCheckedValue;
    private int focusedVirtualView;
    private final ResourcesCompat.FontCallback fontCallback;
    @Nullable
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListenerInternal;
    @Nullable
    private View.OnClickListener onCloseIconClickListener;
    private final Rect rect;
    private final RectF rectF;
    @Nullable
    private RippleDrawable ripple;
    private final ChipTouchHelper touchHelper;

    public Chip(Context context) {
        this(context, null);
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.chipStyle);
    }

    public Chip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.focusedVirtualView = Integer.MIN_VALUE;
        this.rect = new Rect();
        this.rectF = new RectF();
        this.fontCallback = new ResourcesCompat.FontCallback() {
            /* class com.google.android.material.chip.Chip.AnonymousClass1 */

            @Override // androidx.core.content.res.ResourcesCompat.FontCallback
            public void onFontRetrievalFailed(int i) {
            }

            @Override // androidx.core.content.res.ResourcesCompat.FontCallback
            public void onFontRetrieved(@NonNull Typeface typeface) {
                Chip chip = Chip.this;
                chip.setText(chip.getText());
                Chip.this.requestLayout();
                Chip.this.invalidate();
            }
        };
        validateAttributes(attributeSet);
        ChipDrawable createFromAttributes = ChipDrawable.createFromAttributes(context, attributeSet, i, R.style.Widget_MaterialComponents_Chip_Action);
        setChipDrawable(createFromAttributes);
        this.touchHelper = new ChipTouchHelper(this);
        ViewCompat.setAccessibilityDelegate(this, this.touchHelper);
        initOutlineProvider();
        setChecked(this.deferredCheckedValue);
        createFromAttributes.setShouldDrawText(false);
        setText(createFromAttributes.getText());
        setEllipsize(createFromAttributes.getEllipsize());
        setIncludeFontPadding(false);
        if (getTextAppearance() != null) {
            updateTextPaintDrawState(getTextAppearance());
        }
        setSingleLine();
        setGravity(8388627);
        updatePaddingInternal();
    }

    private void updatePaddingInternal() {
        ChipDrawable chipDrawable2;
        if (!TextUtils.isEmpty(getText()) && (chipDrawable2 = this.chipDrawable) != null) {
            float chipStartPadding = chipDrawable2.getChipStartPadding() + this.chipDrawable.getChipEndPadding() + this.chipDrawable.getTextStartPadding() + this.chipDrawable.getTextEndPadding();
            if ((this.chipDrawable.isChipIconVisible() && this.chipDrawable.getChipIcon() != null) || (this.chipDrawable.getCheckedIcon() != null && this.chipDrawable.isCheckedIconVisible() && isChecked())) {
                chipStartPadding += this.chipDrawable.getIconStartPadding() + this.chipDrawable.getIconEndPadding() + this.chipDrawable.getChipIconSize();
            }
            if (this.chipDrawable.isCloseIconVisible() && this.chipDrawable.getCloseIcon() != null) {
                chipStartPadding += this.chipDrawable.getCloseIconStartPadding() + this.chipDrawable.getCloseIconEndPadding() + this.chipDrawable.getCloseIconSize();
            }
            if (((float) ViewCompat.getPaddingEnd(this)) != chipStartPadding) {
                ViewCompat.setPaddingRelative(this, ViewCompat.getPaddingStart(this), getPaddingTop(), (int) chipStartPadding, getPaddingBottom());
            }
        }
    }

    private void validateAttributes(@Nullable AttributeSet attributeSet) {
        if (attributeSet != null) {
            if (attributeSet.getAttributeValue(NAMESPACE_ANDROID, "background") != null) {
                throw new UnsupportedOperationException("Do not set the background; Chip manages its own background drawable.");
            } else if (attributeSet.getAttributeValue(NAMESPACE_ANDROID, "drawableLeft") != null) {
                throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
            } else if (attributeSet.getAttributeValue(NAMESPACE_ANDROID, "drawableStart") != null) {
                throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
            } else if (attributeSet.getAttributeValue(NAMESPACE_ANDROID, "drawableEnd") != null) {
                throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            } else if (attributeSet.getAttributeValue(NAMESPACE_ANDROID, "drawableRight") != null) {
                throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            } else if (!attributeSet.getAttributeBooleanValue(NAMESPACE_ANDROID, "singleLine", true) || attributeSet.getAttributeIntValue(NAMESPACE_ANDROID, "lines", 1) != 1 || attributeSet.getAttributeIntValue(NAMESPACE_ANDROID, "minLines", 1) != 1 || attributeSet.getAttributeIntValue(NAMESPACE_ANDROID, "maxLines", 1) != 1) {
                throw new UnsupportedOperationException("Chip does not support multi-line text");
            } else if (attributeSet.getAttributeIntValue(NAMESPACE_ANDROID, "gravity", 8388627) != 8388627) {
                Log.w(TAG, "Chip text must be vertically center and start aligned");
            }
        }
    }

    private void initOutlineProvider() {
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new ViewOutlineProvider() {
                /* class com.google.android.material.chip.Chip.AnonymousClass2 */

                @TargetApi(21)
                public void getOutline(View view, Outline outline) {
                    if (Chip.this.chipDrawable != null) {
                        Chip.this.chipDrawable.getOutline(outline);
                    } else {
                        outline.setAlpha(0.0f);
                    }
                }
            });
        }
    }

    public Drawable getChipDrawable() {
        return this.chipDrawable;
    }

    public void setChipDrawable(@NonNull ChipDrawable chipDrawable2) {
        ChipDrawable chipDrawable3 = this.chipDrawable;
        if (chipDrawable3 != chipDrawable2) {
            unapplyChipDrawable(chipDrawable3);
            this.chipDrawable = chipDrawable2;
            applyChipDrawable(this.chipDrawable);
            if (RippleUtils.USE_FRAMEWORK_RIPPLE) {
                this.ripple = new RippleDrawable(RippleUtils.convertToRippleDrawableColor(this.chipDrawable.getRippleColor()), this.chipDrawable, null);
                this.chipDrawable.setUseCompatRipple(false);
                ViewCompat.setBackground(this, this.ripple);
                return;
            }
            this.chipDrawable.setUseCompatRipple(true);
            ViewCompat.setBackground(this, this.chipDrawable);
        }
    }

    private void unapplyChipDrawable(@Nullable ChipDrawable chipDrawable2) {
        if (chipDrawable2 != null) {
            chipDrawable2.setDelegate(null);
        }
    }

    private void applyChipDrawable(@NonNull ChipDrawable chipDrawable2) {
        chipDrawable2.setDelegate(this);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, SELECTED_STATE);
        }
        return onCreateDrawableState;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        ChipDrawable chipDrawable2;
        if (TextUtils.isEmpty(getText()) || (chipDrawable2 = this.chipDrawable) == null || chipDrawable2.shouldDrawText()) {
            super.onDraw(canvas);
            return;
        }
        int save = canvas.save();
        canvas.translate(calculateTextOffsetFromStart(this.chipDrawable), 0.0f);
        super.onDraw(canvas);
        canvas.restoreToCount(save);
    }

    public void setGravity(int i) {
        if (i != 8388627) {
            Log.w(TAG, "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i);
        }
    }

    private float calculateTextOffsetFromStart(@NonNull ChipDrawable chipDrawable2) {
        float chipStartPadding = getChipStartPadding() + chipDrawable2.calculateChipIconWidth() + getTextStartPadding();
        return ViewCompat.getLayoutDirection(this) == 0 ? chipStartPadding : -chipStartPadding;
    }

    public void setBackgroundTintList(@Nullable ColorStateList colorStateList) {
        throw new UnsupportedOperationException("Do not set the background tint list; Chip manages its own background drawable.");
    }

    public void setBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        throw new UnsupportedOperationException("Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setBackgroundColor(int i) {
        throw new UnsupportedOperationException("Do not set the background color; Chip manages its own background drawable.");
    }

    public void setBackgroundResource(int i) {
        throw new UnsupportedOperationException("Do not set the background resource; Chip manages its own background drawable.");
    }

    public void setBackground(Drawable drawable) {
        if (drawable == this.chipDrawable || drawable == this.ripple) {
            super.setBackground(drawable);
            return;
        }
        throw new UnsupportedOperationException("Do not set the background; Chip manages its own background drawable.");
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == this.chipDrawable || drawable == this.ripple) {
            super.setBackgroundDrawable(drawable);
            return;
        }
        throw new UnsupportedOperationException("Do not set the background drawable; Chip manages its own background drawable.");
    }

    public void setCompoundDrawables(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (i3 == 0) {
            super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
        }
    }

    public void setCompoundDrawablesRelative(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (i3 == 0) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        } else if (drawable3 == null) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
    }

    public TextUtils.TruncateAt getEllipsize() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getEllipsize();
        }
        return null;
    }

    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.chipDrawable != null) {
            if (truncateAt != TextUtils.TruncateAt.MARQUEE) {
                super.setEllipsize(truncateAt);
                ChipDrawable chipDrawable2 = this.chipDrawable;
                if (chipDrawable2 != null) {
                    chipDrawable2.setEllipsize(truncateAt);
                    return;
                }
                return;
            }
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
    }

    public void setSingleLine(boolean z) {
        if (z) {
            super.setSingleLine(z);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public void setLines(int i) {
        if (i <= 1) {
            super.setLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public void setMinLines(int i) {
        if (i <= 1) {
            super.setMinLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public void setMaxLines(int i) {
        if (i <= 1) {
            super.setMaxLines(i);
            return;
        }
        throw new UnsupportedOperationException("Chip does not support multi-line text");
    }

    public void setMaxWidth(@Px int i) {
        super.setMaxWidth(i);
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setMaxWidth(i);
        }
    }

    @Override // com.google.android.material.chip.ChipDrawable.Delegate
    public void onChipDrawableSizeChange() {
        updatePaddingInternal();
        requestLayout();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setChecked(boolean z) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 == null) {
            this.deferredCheckedValue = z;
        } else if (chipDrawable2.isCheckable()) {
            boolean isChecked = isChecked();
            super.setChecked(z);
            if (isChecked != z && (onCheckedChangeListener = this.onCheckedChangeListenerInternal) != null) {
                onCheckedChangeListener.onCheckedChanged(this, z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setOnCheckedChangeListenerInternal(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListenerInternal = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.onCloseIconClickListener = onClickListener;
    }

    @CallSuper
    public boolean performCloseIconClick() {
        boolean z;
        playSoundEffect(0);
        View.OnClickListener onClickListener = this.onCloseIconClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(this);
            z = true;
        } else {
            z = false;
        }
        this.touchHelper.sendEventForVirtualView(0, 1);
        return z;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int actionMasked = motionEvent.getActionMasked();
        boolean contains = getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY());
        switch (actionMasked) {
            case 0:
                if (contains) {
                    setCloseIconPressed(true);
                    z = true;
                    break;
                }
                z = false;
                break;
            case 1:
                if (this.closeIconPressed) {
                    performCloseIconClick();
                    z = true;
                    setCloseIconPressed(false);
                    break;
                }
                z = false;
                setCloseIconPressed(false);
            case 2:
                if (this.closeIconPressed) {
                    if (!contains) {
                        setCloseIconPressed(false);
                    }
                    z = true;
                    break;
                }
                z = false;
                break;
            case 3:
                z = false;
                setCloseIconPressed(false);
                break;
            default:
                z = false;
                break;
        }
        if (z || super.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            setCloseIconHovered(getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()));
        } else if (actionMasked == 10) {
            setCloseIconHovered(false);
        }
        return super.onHoverEvent(motionEvent);
    }

    @SuppressLint({"PrivateApi"})
    private boolean handleAccessibilityExit(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 10) {
            try {
                Field declaredField = ExploreByTouchHelper.class.getDeclaredField("mHoveredVirtualViewId");
                declaredField.setAccessible(true);
                if (((Integer) declaredField.get(this.touchHelper)).intValue() != Integer.MIN_VALUE) {
                    Method declaredMethod = ExploreByTouchHelper.class.getDeclaredMethod("updateHoveredVirtualView", Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(this.touchHelper, Integer.MIN_VALUE);
                    return true;
                }
            } catch (NoSuchMethodException e) {
                Log.e(TAG, "Unable to send Accessibility Exit event", e);
            } catch (IllegalAccessException e2) {
                Log.e(TAG, "Unable to send Accessibility Exit event", e2);
            } catch (InvocationTargetException e3) {
                Log.e(TAG, "Unable to send Accessibility Exit event", e3);
            } catch (NoSuchFieldException e4) {
                Log.e(TAG, "Unable to send Accessibility Exit event", e4);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return handleAccessibilityExit(motionEvent) || this.touchHelper.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.touchHelper.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i, Rect rect2) {
        if (z) {
            setFocusedVirtualView(-1);
        } else {
            setFocusedVirtualView(Integer.MIN_VALUE);
        }
        invalidate();
        super.onFocusChanged(z, i, rect2);
        this.touchHelper.onFocusChanged(z, i, rect2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0067  */
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        int i2;
        int keyCode = keyEvent.getKeyCode();
        boolean z = false;
        if (keyCode != 61) {
            if (keyCode != 66) {
                switch (keyCode) {
                    case 21:
                        if (keyEvent.hasNoModifiers()) {
                            z = moveFocus(ViewUtils.isLayoutRtl(this));
                            break;
                        }
                        break;
                    case 22:
                        if (keyEvent.hasNoModifiers()) {
                            z = moveFocus(!ViewUtils.isLayoutRtl(this));
                            break;
                        }
                        break;
                }
            }
            switch (this.focusedVirtualView) {
                case -1:
                    performClick();
                    return true;
                case 0:
                    performCloseIconClick();
                    return true;
            }
        } else {
            if (keyEvent.hasNoModifiers()) {
                i2 = 2;
            } else {
                i2 = keyEvent.hasModifiers(1) ? 1 : 0;
            }
            if (i2 != 0) {
                ViewParent parent = getParent();
                View view = this;
                do {
                    view = view.focusSearch(i2);
                    if (view == null || view == this) {
                        if (view != null) {
                            view.requestFocus();
                            return true;
                        }
                    }
                } while (view.getParent() == parent);
                if (view != null) {
                }
            }
        }
        if (!z) {
            return super.onKeyDown(i, keyEvent);
        }
        invalidate();
        return true;
    }

    private boolean moveFocus(boolean z) {
        ensureFocus();
        if (z) {
            if (this.focusedVirtualView == -1) {
                setFocusedVirtualView(0);
                return true;
            }
        } else if (this.focusedVirtualView == 0) {
            setFocusedVirtualView(-1);
            return true;
        }
        return false;
    }

    private void ensureFocus() {
        if (this.focusedVirtualView == Integer.MIN_VALUE) {
            setFocusedVirtualView(-1);
        }
    }

    public void getFocusedRect(Rect rect2) {
        if (this.focusedVirtualView == 0) {
            rect2.set(getCloseIconTouchBoundsInt());
        } else {
            super.getFocusedRect(rect2);
        }
    }

    private void setFocusedVirtualView(int i) {
        int i2 = this.focusedVirtualView;
        if (i2 != i) {
            if (i2 == 0) {
                setCloseIconFocused(false);
            }
            this.focusedVirtualView = i;
            if (i == 0) {
                setCloseIconFocused(true);
            }
        }
    }

    private void setCloseIconPressed(boolean z) {
        if (this.closeIconPressed != z) {
            this.closeIconPressed = z;
            refreshDrawableState();
        }
    }

    private void setCloseIconHovered(boolean z) {
        if (this.closeIconHovered != z) {
            this.closeIconHovered = z;
            refreshDrawableState();
        }
    }

    private void setCloseIconFocused(boolean z) {
        if (this.closeIconFocused != z) {
            this.closeIconFocused = z;
            refreshDrawableState();
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if ((chipDrawable2 == null || !chipDrawable2.isCloseIconStateful()) ? false : this.chipDrawable.setCloseIconState(createCloseIconDrawableState())) {
            invalidate();
        }
    }

    private int[] createCloseIconDrawableState() {
        int i = 0;
        int i2 = isEnabled() ? 1 : 0;
        if (this.closeIconFocused) {
            i2++;
        }
        if (this.closeIconHovered) {
            i2++;
        }
        if (this.closeIconPressed) {
            i2++;
        }
        if (isChecked()) {
            i2++;
        }
        int[] iArr = new int[i2];
        if (isEnabled()) {
            iArr[0] = 16842910;
            i = 1;
        }
        if (this.closeIconFocused) {
            iArr[i] = 16842908;
            i++;
        }
        if (this.closeIconHovered) {
            iArr[i] = 16843623;
            i++;
        }
        if (this.closeIconPressed) {
            iArr[i] = 16842919;
            i++;
        }
        if (isChecked()) {
            iArr[i] = 16842913;
        }
        return iArr;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean hasCloseIcon() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        return (chipDrawable2 == null || chipDrawable2.getCloseIcon() == null) ? false : true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private RectF getCloseIconTouchBounds() {
        this.rectF.setEmpty();
        if (hasCloseIcon()) {
            this.chipDrawable.getCloseIconTouchBounds(this.rectF);
        }
        return this.rectF;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        this.rect.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
        return this.rect;
    }

    @TargetApi(24)
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        if (!getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) || !isEnabled()) {
            return null;
        }
        return PointerIcon.getSystemIcon(getContext(), 1002);
    }

    /* access modifiers changed from: private */
    public class ChipTouchHelper extends ExploreByTouchHelper {
        ChipTouchHelper(Chip chip) {
            super(chip);
        }

        /* access modifiers changed from: protected */
        @Override // androidx.customview.widget.ExploreByTouchHelper
        public int getVirtualViewAt(float f, float f2) {
            return (!Chip.this.hasCloseIcon() || !Chip.this.getCloseIconTouchBounds().contains(f, f2)) ? -1 : 0;
        }

        /* access modifiers changed from: protected */
        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void getVisibleVirtualViews(List<Integer> list) {
            if (Chip.this.hasCloseIcon()) {
                list.add(0);
            }
        }

        /* access modifiers changed from: protected */
        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (Chip.this.hasCloseIcon()) {
                CharSequence closeIconContentDescription = Chip.this.getCloseIconContentDescription();
                if (closeIconContentDescription != null) {
                    accessibilityNodeInfoCompat.setContentDescription(closeIconContentDescription);
                } else {
                    CharSequence text = Chip.this.getText();
                    Context context = Chip.this.getContext();
                    int i2 = R.string.mtrl_chip_close_icon_content_description;
                    Object[] objArr = new Object[1];
                    if (TextUtils.isEmpty(text)) {
                        text = "";
                    }
                    objArr[0] = text;
                    accessibilityNodeInfoCompat.setContentDescription(context.getString(i2, objArr).trim());
                }
                accessibilityNodeInfoCompat.setBoundsInParent(Chip.this.getCloseIconTouchBoundsInt());
                accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                accessibilityNodeInfoCompat.setEnabled(Chip.this.isEnabled());
                return;
            }
            accessibilityNodeInfoCompat.setContentDescription("");
            accessibilityNodeInfoCompat.setBoundsInParent(Chip.EMPTY_BOUNDS);
        }

        /* access modifiers changed from: protected */
        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.setCheckable(Chip.this.chipDrawable != null && Chip.this.chipDrawable.isCheckable());
            accessibilityNodeInfoCompat.setClassName(Chip.class.getName());
            CharSequence text = Chip.this.getText();
            if (Build.VERSION.SDK_INT >= 23) {
                accessibilityNodeInfoCompat.setText(text);
            } else {
                accessibilityNodeInfoCompat.setContentDescription(text);
            }
        }

        /* access modifiers changed from: protected */
        @Override // androidx.customview.widget.ExploreByTouchHelper
        public boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
            if (i2 == 16 && i == 0) {
                return Chip.this.performCloseIconClick();
            }
            return false;
        }
    }

    @Nullable
    public ColorStateList getChipBackgroundColor() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getChipBackgroundColor();
        }
        return null;
    }

    public void setChipBackgroundColorResource(@ColorRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipBackgroundColorResource(i);
        }
    }

    public void setChipBackgroundColor(@Nullable ColorStateList colorStateList) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipBackgroundColor(colorStateList);
        }
    }

    public float getChipMinHeight() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getChipMinHeight();
        }
        return 0.0f;
    }

    public void setChipMinHeightResource(@DimenRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipMinHeightResource(i);
        }
    }

    public void setChipMinHeight(float f) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipMinHeight(f);
        }
    }

    public float getChipCornerRadius() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getChipCornerRadius();
        }
        return 0.0f;
    }

    public void setChipCornerRadiusResource(@DimenRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipCornerRadiusResource(i);
        }
    }

    public void setChipCornerRadius(float f) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipCornerRadius(f);
        }
    }

    @Nullable
    public ColorStateList getChipStrokeColor() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getChipStrokeColor();
        }
        return null;
    }

    public void setChipStrokeColorResource(@ColorRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipStrokeColorResource(i);
        }
    }

    public void setChipStrokeColor(@Nullable ColorStateList colorStateList) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipStrokeColor(colorStateList);
        }
    }

    public float getChipStrokeWidth() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getChipStrokeWidth();
        }
        return 0.0f;
    }

    public void setChipStrokeWidthResource(@DimenRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipStrokeWidthResource(i);
        }
    }

    public void setChipStrokeWidth(float f) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipStrokeWidth(f);
        }
    }

    @Nullable
    public ColorStateList getRippleColor() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getRippleColor();
        }
        return null;
    }

    public void setRippleColorResource(@ColorRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setRippleColorResource(i);
        }
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setRippleColor(colorStateList);
        }
    }

    public CharSequence getText() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        return chipDrawable2 != null ? chipDrawable2.getText() : "";
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        if (this.chipDrawable != null) {
            if (charSequence == null) {
                charSequence = "";
            }
            CharSequence unicodeWrap = BidiFormatter.getInstance().unicodeWrap(charSequence);
            if (this.chipDrawable.shouldDrawText()) {
                unicodeWrap = null;
            }
            super.setText(unicodeWrap, bufferType);
            ChipDrawable chipDrawable2 = this.chipDrawable;
            if (chipDrawable2 != null) {
                chipDrawable2.setText(charSequence);
            }
        }
    }

    @Deprecated
    public void setChipTextResource(@StringRes int i) {
        setText(getResources().getString(i));
    }

    @Deprecated
    public void setChipText(@Nullable CharSequence charSequence) {
        setText(charSequence);
    }

    @Nullable
    private TextAppearance getTextAppearance() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getTextAppearance();
        }
        return null;
    }

    private void updateTextPaintDrawState(TextAppearance textAppearance) {
        TextPaint paint = getPaint();
        paint.drawableState = this.chipDrawable.getState();
        textAppearance.updateDrawState(getContext(), paint, this.fontCallback);
    }

    public void setTextAppearanceResource(@StyleRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setTextAppearanceResource(i);
        }
        setTextAppearance(getContext(), i);
    }

    public void setTextAppearance(@Nullable TextAppearance textAppearance) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setTextAppearance(textAppearance);
        }
        if (getTextAppearance() != null) {
            getTextAppearance().updateMeasureState(getContext(), getPaint(), this.fontCallback);
            updateTextPaintDrawState(textAppearance);
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setTextAppearanceResource(i);
        }
        if (getTextAppearance() != null) {
            getTextAppearance().updateMeasureState(context, getPaint(), this.fontCallback);
            updateTextPaintDrawState(getTextAppearance());
        }
    }

    public void setTextAppearance(int i) {
        super.setTextAppearance(i);
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setTextAppearanceResource(i);
        }
        if (getTextAppearance() != null) {
            getTextAppearance().updateMeasureState(getContext(), getPaint(), this.fontCallback);
            updateTextPaintDrawState(getTextAppearance());
        }
    }

    public boolean isChipIconVisible() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        return chipDrawable2 != null && chipDrawable2.isChipIconVisible();
    }

    @Deprecated
    public boolean isChipIconEnabled() {
        return isChipIconVisible();
    }

    public void setChipIconVisible(@BoolRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipIconVisible(i);
        }
    }

    public void setChipIconVisible(boolean z) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipIconVisible(z);
        }
    }

    @Deprecated
    public void setChipIconEnabledResource(@BoolRes int i) {
        setChipIconVisible(i);
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        setChipIconVisible(z);
    }

    @Nullable
    public Drawable getChipIcon() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getChipIcon();
        }
        return null;
    }

    public void setChipIconResource(@DrawableRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipIconResource(i);
        }
    }

    public void setChipIcon(@Nullable Drawable drawable) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipIcon(drawable);
        }
    }

    @Nullable
    public ColorStateList getChipIconTint() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getChipIconTint();
        }
        return null;
    }

    public void setChipIconTintResource(@ColorRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipIconTintResource(i);
        }
    }

    public void setChipIconTint(@Nullable ColorStateList colorStateList) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipIconTint(colorStateList);
        }
    }

    public float getChipIconSize() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getChipIconSize();
        }
        return 0.0f;
    }

    public void setChipIconSizeResource(@DimenRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipIconSizeResource(i);
        }
    }

    public void setChipIconSize(float f) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipIconSize(f);
        }
    }

    public boolean isCloseIconVisible() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        return chipDrawable2 != null && chipDrawable2.isCloseIconVisible();
    }

    @Deprecated
    public boolean isCloseIconEnabled() {
        return isCloseIconVisible();
    }

    public void setCloseIconVisible(@BoolRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCloseIconVisible(i);
        }
    }

    public void setCloseIconVisible(boolean z) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCloseIconVisible(z);
        }
    }

    @Deprecated
    public void setCloseIconEnabledResource(@BoolRes int i) {
        setCloseIconVisible(i);
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    @Nullable
    public Drawable getCloseIcon() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getCloseIcon();
        }
        return null;
    }

    public void setCloseIconResource(@DrawableRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCloseIconResource(i);
        }
    }

    public void setCloseIcon(@Nullable Drawable drawable) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCloseIcon(drawable);
        }
    }

    @Nullable
    public ColorStateList getCloseIconTint() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getCloseIconTint();
        }
        return null;
    }

    public void setCloseIconTintResource(@ColorRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCloseIconTintResource(i);
        }
    }

    public void setCloseIconTint(@Nullable ColorStateList colorStateList) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCloseIconTint(colorStateList);
        }
    }

    public float getCloseIconSize() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getCloseIconSize();
        }
        return 0.0f;
    }

    public void setCloseIconSizeResource(@DimenRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCloseIconSizeResource(i);
        }
    }

    public void setCloseIconSize(float f) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCloseIconSize(f);
        }
    }

    public void setCloseIconContentDescription(@Nullable CharSequence charSequence) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCloseIconContentDescription(charSequence);
        }
    }

    @Nullable
    public CharSequence getCloseIconContentDescription() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getCloseIconContentDescription();
        }
        return null;
    }

    public boolean isCheckable() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        return chipDrawable2 != null && chipDrawable2.isCheckable();
    }

    public void setCheckableResource(@BoolRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCheckableResource(i);
        }
    }

    public void setCheckable(boolean z) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCheckable(z);
        }
    }

    public boolean isCheckedIconVisible() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        return chipDrawable2 != null && chipDrawable2.isCheckedIconVisible();
    }

    @Deprecated
    public boolean isCheckedIconEnabled() {
        return isCheckedIconVisible();
    }

    public void setCheckedIconVisible(@BoolRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCheckedIconVisible(i);
        }
    }

    public void setCheckedIconVisible(boolean z) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCheckedIconVisible(z);
        }
    }

    @Deprecated
    public void setCheckedIconEnabledResource(@BoolRes int i) {
        setCheckedIconVisible(i);
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        setCheckedIconVisible(z);
    }

    @Nullable
    public Drawable getCheckedIcon() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getCheckedIcon();
        }
        return null;
    }

    public void setCheckedIconResource(@DrawableRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCheckedIconResource(i);
        }
    }

    public void setCheckedIcon(@Nullable Drawable drawable) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCheckedIcon(drawable);
        }
    }

    @Nullable
    public MotionSpec getShowMotionSpec() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getShowMotionSpec();
        }
        return null;
    }

    public void setShowMotionSpecResource(@AnimatorRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setShowMotionSpecResource(i);
        }
    }

    public void setShowMotionSpec(@Nullable MotionSpec motionSpec) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setShowMotionSpec(motionSpec);
        }
    }

    @Nullable
    public MotionSpec getHideMotionSpec() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getHideMotionSpec();
        }
        return null;
    }

    public void setHideMotionSpecResource(@AnimatorRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setHideMotionSpecResource(i);
        }
    }

    public void setHideMotionSpec(@Nullable MotionSpec motionSpec) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setHideMotionSpec(motionSpec);
        }
    }

    public float getChipStartPadding() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getChipStartPadding();
        }
        return 0.0f;
    }

    public void setChipStartPaddingResource(@DimenRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipStartPaddingResource(i);
        }
    }

    public void setChipStartPadding(float f) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipStartPadding(f);
        }
    }

    public float getIconStartPadding() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getIconStartPadding();
        }
        return 0.0f;
    }

    public void setIconStartPaddingResource(@DimenRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setIconStartPaddingResource(i);
        }
    }

    public void setIconStartPadding(float f) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setIconStartPadding(f);
        }
    }

    public float getIconEndPadding() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getIconEndPadding();
        }
        return 0.0f;
    }

    public void setIconEndPaddingResource(@DimenRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setIconEndPaddingResource(i);
        }
    }

    public void setIconEndPadding(float f) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setIconEndPadding(f);
        }
    }

    public float getTextStartPadding() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getTextStartPadding();
        }
        return 0.0f;
    }

    public void setTextStartPaddingResource(@DimenRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setTextStartPaddingResource(i);
        }
    }

    public void setTextStartPadding(float f) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setTextStartPadding(f);
        }
    }

    public float getTextEndPadding() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getTextEndPadding();
        }
        return 0.0f;
    }

    public void setTextEndPaddingResource(@DimenRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setTextEndPaddingResource(i);
        }
    }

    public void setTextEndPadding(float f) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setTextEndPadding(f);
        }
    }

    public float getCloseIconStartPadding() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getCloseIconStartPadding();
        }
        return 0.0f;
    }

    public void setCloseIconStartPaddingResource(@DimenRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCloseIconStartPaddingResource(i);
        }
    }

    public void setCloseIconStartPadding(float f) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCloseIconStartPadding(f);
        }
    }

    public float getCloseIconEndPadding() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getCloseIconEndPadding();
        }
        return 0.0f;
    }

    public void setCloseIconEndPaddingResource(@DimenRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCloseIconEndPaddingResource(i);
        }
    }

    public void setCloseIconEndPadding(float f) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setCloseIconEndPadding(f);
        }
    }

    public float getChipEndPadding() {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            return chipDrawable2.getChipEndPadding();
        }
        return 0.0f;
    }

    public void setChipEndPaddingResource(@DimenRes int i) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipEndPaddingResource(i);
        }
    }

    public void setChipEndPadding(float f) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != null) {
            chipDrawable2.setChipEndPadding(f);
        }
    }
}
