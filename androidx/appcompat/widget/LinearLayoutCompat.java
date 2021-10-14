package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.core.view.GravityCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface DividerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface OrientationMode {
    }

    /* access modifiers changed from: package-private */
    public int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getLocationOffset(View view) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getNextLocationOffset(View view) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int measureNullChild(int i) {
        return 0;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.LinearLayoutCompat, i, 0);
        int i2 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(obtainStyledAttributes.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        obtainStyledAttributes.recycle();
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.mDivider) {
            this.mDivider = drawable;
            boolean z = false;
            if (drawable != null) {
                this.mDividerWidth = drawable.getIntrinsicWidth();
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawDividersVertical(canvas);
            } else {
                drawDividersHorizontal(canvas);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void drawDividersVertical(Canvas canvas) {
        int i;
        int virtualChildCount = getVirtualChildCount();
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View virtualChildAt = getVirtualChildAt(i2);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i2))) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                i = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                i = virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawDividersHorizontal(Canvas canvas) {
        int i;
        int i2;
        int virtualChildCount = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i3 = 0; i3 < virtualChildCount; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i3))) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (isLayoutRtl) {
                    i2 = virtualChildAt.getRight() + layoutParams.rightMargin;
                } else {
                    i2 = (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, i2);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (isLayoutRtl) {
                    i = (virtualChildAt2.getLeft() - layoutParams2.leftMargin) - this.mDividerWidth;
                } else {
                    i = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                }
            } else if (isLayoutRtl) {
                i = getPaddingLeft();
            } else {
                i = (getWidth() - getPaddingRight()) - this.mDividerWidth;
            }
            drawVerticalDivider(canvas, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    /* access modifiers changed from: package-private */
    public void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public int getBaseline() {
        int i;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.mBaselineAlignedChildIndex;
        if (childCount > i2) {
            View childAt = getChildAt(i2);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i3 = this.mBaselineChildTop;
                if (this.mOrientation == 1 && (i = this.mGravity & 112) != 48) {
                    if (i == 16) {
                        i3 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                    } else if (i == 80) {
                        i3 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                    }
                }
                return i3 + ((LayoutParams) childAt.getLayoutParams()).topMargin + baseline;
            } else if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = i;
    }

    /* access modifiers changed from: package-private */
    public View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    /* access modifiers changed from: package-private */
    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean hasDividerBeforeChildAt(int i) {
        if (i == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if (i == getChildCount()) {
            if ((this.mShowDividers & 4) != 0) {
                return true;
            }
            return false;
        } else if ((this.mShowDividers & 2) == 0) {
            return false;
        } else {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (getChildAt(i2).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0333  */
    public void measureVertical(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        float f;
        int i8;
        int i9;
        boolean z;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        View view;
        int i19;
        boolean z2;
        int i20;
        int i21;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i22 = this.mBaselineAlignedChildIndex;
        boolean z3 = this.mUseLargestChild;
        float f2 = 0.0f;
        int i23 = 0;
        int i24 = 0;
        int i25 = 0;
        int i26 = 0;
        int i27 = 0;
        int i28 = 0;
        boolean z4 = false;
        boolean z5 = true;
        boolean z6 = false;
        while (true) {
            int i29 = 8;
            if (i28 < virtualChildCount) {
                View virtualChildAt = getVirtualChildAt(i28);
                if (virtualChildAt == null) {
                    this.mTotalLength += measureNullChild(i28);
                    i12 = virtualChildCount;
                    i26 = i26;
                    i13 = mode2;
                } else if (virtualChildAt.getVisibility() == 8) {
                    i28 += getChildrenSkipCount(virtualChildAt, i28);
                    i12 = virtualChildCount;
                    i26 = i26;
                    i23 = i23;
                    i13 = mode2;
                } else {
                    if (hasDividerBeforeChildAt(i28)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                    LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                    float f3 = f2 + layoutParams.weight;
                    if (mode2 == 1073741824 && layoutParams.height == 0 && layoutParams.weight > 0.0f) {
                        int i30 = this.mTotalLength;
                        this.mTotalLength = Math.max(i30, layoutParams.topMargin + i30 + layoutParams.bottomMargin);
                        i19 = i25;
                        view = virtualChildAt;
                        i14 = i27;
                        i12 = virtualChildCount;
                        i18 = i23;
                        i15 = i24;
                        z4 = true;
                        i17 = i28;
                        i13 = mode2;
                        i16 = i26;
                    } else {
                        if (layoutParams.height != 0 || layoutParams.weight <= 0.0f) {
                            i21 = Integer.MIN_VALUE;
                        } else {
                            layoutParams.height = -2;
                            i21 = 0;
                        }
                        i18 = i23;
                        i15 = i24;
                        i12 = virtualChildCount;
                        i13 = mode2;
                        i16 = i26;
                        i14 = i27;
                        i17 = i28;
                        measureChildBeforeLayout(virtualChildAt, i28, i, 0, i2, f3 == 0.0f ? this.mTotalLength : 0);
                        if (i21 != Integer.MIN_VALUE) {
                            layoutParams.height = i21;
                        }
                        int measuredHeight = virtualChildAt.getMeasuredHeight();
                        int i31 = this.mTotalLength;
                        view = virtualChildAt;
                        this.mTotalLength = Math.max(i31, i31 + measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin + getNextLocationOffset(view));
                        i19 = z3 ? Math.max(measuredHeight, i25) : i25;
                    }
                    if (i22 >= 0 && i22 == i17 + 1) {
                        this.mBaselineChildTop = this.mTotalLength;
                    }
                    if (i17 >= i22 || layoutParams.weight <= 0.0f) {
                        if (mode == 1073741824 || layoutParams.width != -1) {
                            z2 = false;
                        } else {
                            z2 = true;
                            z6 = true;
                        }
                        int i32 = layoutParams.leftMargin + layoutParams.rightMargin;
                        int measuredWidth = view.getMeasuredWidth() + i32;
                        int max = Math.max(i15, measuredWidth);
                        int combineMeasuredStates = View.combineMeasuredStates(i18, view.getMeasuredState());
                        boolean z7 = z5 && layoutParams.width == -1;
                        if (layoutParams.weight > 0.0f) {
                            if (!z2) {
                                i32 = measuredWidth;
                            }
                            i16 = Math.max(i16, i32);
                            i20 = i14;
                        } else {
                            if (!z2) {
                                i32 = measuredWidth;
                            }
                            i20 = Math.max(i14, i32);
                        }
                        i25 = i19;
                        z5 = z7;
                        i26 = i16;
                        f2 = f3;
                        i27 = i20;
                        i23 = combineMeasuredStates;
                        i28 = getChildrenSkipCount(view, i17) + i17;
                        i24 = max;
                    } else {
                        throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                    }
                }
                i28++;
                mode2 = i13;
                virtualChildCount = i12;
            } else {
                int i33 = i24;
                if (this.mTotalLength > 0) {
                    i3 = virtualChildCount;
                    if (hasDividerBeforeChildAt(i3)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                } else {
                    i3 = virtualChildCount;
                }
                if (z3) {
                    i4 = mode2;
                    if (i4 == Integer.MIN_VALUE || i4 == 0) {
                        this.mTotalLength = 0;
                        int i34 = 0;
                        while (i34 < i3) {
                            View virtualChildAt2 = getVirtualChildAt(i34);
                            if (virtualChildAt2 == null) {
                                this.mTotalLength += measureNullChild(i34);
                            } else if (virtualChildAt2.getVisibility() == i29) {
                                i34 += getChildrenSkipCount(virtualChildAt2, i34);
                            } else {
                                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                                int i35 = this.mTotalLength;
                                this.mTotalLength = Math.max(i35, i35 + i25 + layoutParams2.topMargin + layoutParams2.bottomMargin + getNextLocationOffset(virtualChildAt2));
                            }
                            i34++;
                            i29 = 8;
                        }
                    }
                } else {
                    i4 = mode2;
                }
                this.mTotalLength += getPaddingTop() + getPaddingBottom();
                int resolveSizeAndState = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumHeight()), i2, 0);
                int i36 = (16777215 & resolveSizeAndState) - this.mTotalLength;
                if (z4 || (i36 != 0 && f2 > 0.0f)) {
                    float f4 = this.mWeightSum;
                    if (f4 > 0.0f) {
                        f2 = f4;
                    }
                    this.mTotalLength = 0;
                    float f5 = f2;
                    int i37 = 0;
                    int i38 = i27;
                    i6 = i23;
                    while (i37 < i3) {
                        View virtualChildAt3 = getVirtualChildAt(i37);
                        if (virtualChildAt3.getVisibility() == 8) {
                            f = f5;
                        } else {
                            LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                            float f6 = layoutParams3.weight;
                            if (f6 > 0.0f) {
                                int i39 = (int) ((((float) i36) * f6) / f5);
                                i8 = i36 - i39;
                                f = f5 - f6;
                                int childMeasureSpec = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + layoutParams3.leftMargin + layoutParams3.rightMargin, layoutParams3.width);
                                if (layoutParams3.height == 0) {
                                    i11 = 1073741824;
                                    if (i4 == 1073741824) {
                                        if (i39 <= 0) {
                                            i39 = 0;
                                        }
                                        virtualChildAt3.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(i39, 1073741824));
                                        i6 = View.combineMeasuredStates(i6, virtualChildAt3.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                                    }
                                } else {
                                    i11 = 1073741824;
                                }
                                int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i39;
                                if (measuredHeight2 < 0) {
                                    measuredHeight2 = 0;
                                }
                                virtualChildAt3.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(measuredHeight2, i11));
                                i6 = View.combineMeasuredStates(i6, virtualChildAt3.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                            } else {
                                i8 = i36;
                                f = f5;
                            }
                            int i40 = layoutParams3.leftMargin + layoutParams3.rightMargin;
                            int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i40;
                            i33 = Math.max(i33, measuredWidth2);
                            if (mode != 1073741824) {
                                i9 = i6;
                                i10 = -1;
                                if (layoutParams3.width == -1) {
                                    z = true;
                                    if (!z) {
                                        i40 = measuredWidth2;
                                    }
                                    i38 = Math.max(i38, i40);
                                    boolean z8 = !z5 && layoutParams3.width == i10;
                                    int i41 = this.mTotalLength;
                                    this.mTotalLength = Math.max(i41, virtualChildAt3.getMeasuredHeight() + i41 + layoutParams3.topMargin + layoutParams3.bottomMargin + getNextLocationOffset(virtualChildAt3));
                                    z5 = z8;
                                    i36 = i8;
                                    i6 = i9;
                                }
                            } else {
                                i9 = i6;
                                i10 = -1;
                            }
                            z = false;
                            if (!z) {
                            }
                            i38 = Math.max(i38, i40);
                            if (!z5) {
                            }
                            int i412 = this.mTotalLength;
                            this.mTotalLength = Math.max(i412, virtualChildAt3.getMeasuredHeight() + i412 + layoutParams3.topMargin + layoutParams3.bottomMargin + getNextLocationOffset(virtualChildAt3));
                            z5 = z8;
                            i36 = i8;
                            i6 = i9;
                        }
                        i37++;
                        f5 = f;
                    }
                    i5 = i;
                    this.mTotalLength += getPaddingTop() + getPaddingBottom();
                    i7 = i38;
                } else {
                    i7 = Math.max(i27, i26);
                    if (z3 && i4 != 1073741824) {
                        for (int i42 = 0; i42 < i3; i42++) {
                            View virtualChildAt4 = getVirtualChildAt(i42);
                            if (!(virtualChildAt4 == null || virtualChildAt4.getVisibility() == 8 || ((LayoutParams) virtualChildAt4.getLayoutParams()).weight <= 0.0f)) {
                                virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(i25, 1073741824));
                            }
                        }
                    }
                    i6 = i23;
                    i5 = i;
                }
                if (z5 || mode == 1073741824) {
                    i7 = i33;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max(i7 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i5, i6), resolveSizeAndState);
                if (z6) {
                    forceUniformWidth(i3, i2);
                    return;
                }
                return;
            }
        }
    }

    private void forceUniformWidth(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x046b  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0499  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01e8  */
    public void measureHorizontal(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        float f;
        int i12;
        int i13;
        boolean z;
        int i14;
        int i15;
        boolean z2;
        boolean z3;
        int i16;
        View view;
        int i17;
        boolean z4;
        int i18;
        int i19;
        int i20;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        iArr[3] = -1;
        iArr[2] = -1;
        iArr[1] = -1;
        iArr[0] = -1;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        boolean z5 = this.mBaselineAligned;
        boolean z6 = this.mUseLargestChild;
        int i21 = 1073741824;
        boolean z7 = mode == 1073741824;
        float f2 = 0.0f;
        int i22 = 0;
        int i23 = 0;
        int i24 = 0;
        int i25 = 0;
        int i26 = 0;
        boolean z8 = false;
        int i27 = 0;
        boolean z9 = true;
        boolean z10 = false;
        while (i22 < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i22);
            if (virtualChildAt == null) {
                this.mTotalLength += measureNullChild(i22);
                z3 = z6;
                z2 = z5;
            } else if (virtualChildAt.getVisibility() == 8) {
                i22 += getChildrenSkipCount(virtualChildAt, i22);
                z3 = z6;
                z2 = z5;
            } else {
                if (hasDividerBeforeChildAt(i22)) {
                    this.mTotalLength += this.mDividerWidth;
                }
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                float f3 = f2 + layoutParams.weight;
                if (mode == i21 && layoutParams.width == 0 && layoutParams.weight > 0.0f) {
                    if (z7) {
                        this.mTotalLength += layoutParams.leftMargin + layoutParams.rightMargin;
                    } else {
                        int i28 = this.mTotalLength;
                        this.mTotalLength = Math.max(i28, layoutParams.leftMargin + i28 + layoutParams.rightMargin);
                    }
                    if (z5) {
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                        virtualChildAt.measure(makeMeasureSpec, makeMeasureSpec);
                        i16 = i22;
                        z3 = z6;
                        z2 = z5;
                        view = virtualChildAt;
                    } else {
                        i16 = i22;
                        z3 = z6;
                        z2 = z5;
                        view = virtualChildAt;
                        i17 = 1073741824;
                        z8 = true;
                        if (mode2 == i17) {
                        }
                        z4 = false;
                        int i29 = layoutParams.topMargin + layoutParams.bottomMargin;
                        int measuredHeight = view.getMeasuredHeight() + i29;
                        int combineMeasuredStates = View.combineMeasuredStates(i27, view.getMeasuredState());
                        if (z2) {
                        }
                        int max = Math.max(i18, measuredHeight);
                        if (!z9) {
                        }
                        if (layoutParams.weight > 0.0f) {
                        }
                        i24 = max;
                        i27 = combineMeasuredStates;
                        z9 = r6;
                        i22 = getChildrenSkipCount(view, i19) + i19;
                        f2 = f3;
                    }
                } else {
                    if (layoutParams.width != 0 || layoutParams.weight <= 0.0f) {
                        i20 = Integer.MIN_VALUE;
                    } else {
                        layoutParams.width = -2;
                        i20 = 0;
                    }
                    i16 = i22;
                    z3 = z6;
                    z2 = z5;
                    measureChildBeforeLayout(virtualChildAt, i16, i, f3 == 0.0f ? this.mTotalLength : 0, i2, 0);
                    if (i20 != Integer.MIN_VALUE) {
                        layoutParams.width = i20;
                    }
                    int measuredWidth = virtualChildAt.getMeasuredWidth();
                    if (z7) {
                        view = virtualChildAt;
                        this.mTotalLength += layoutParams.leftMargin + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(view);
                    } else {
                        view = virtualChildAt;
                        int i30 = this.mTotalLength;
                        this.mTotalLength = Math.max(i30, i30 + measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin + getNextLocationOffset(view));
                    }
                    if (z3) {
                        i23 = Math.max(measuredWidth, i23);
                        i17 = 1073741824;
                        if (mode2 == i17 && layoutParams.height == -1) {
                            z4 = true;
                            z10 = true;
                        } else {
                            z4 = false;
                        }
                        int i292 = layoutParams.topMargin + layoutParams.bottomMargin;
                        int measuredHeight2 = view.getMeasuredHeight() + i292;
                        int combineMeasuredStates2 = View.combineMeasuredStates(i27, view.getMeasuredState());
                        if (z2) {
                            int baseline = view.getBaseline();
                            if (baseline != -1) {
                                int i31 = ((((layoutParams.gravity < 0 ? this.mGravity : layoutParams.gravity) & 112) >> 4) & -2) >> 1;
                                iArr[i31] = Math.max(iArr[i31], baseline);
                                iArr2[i31] = Math.max(iArr2[i31], measuredHeight2 - baseline);
                                i18 = i24;
                            } else {
                                i18 = i24;
                            }
                        } else {
                            i18 = i24;
                        }
                        int max2 = Math.max(i18, measuredHeight2);
                        boolean z11 = !z9 && layoutParams.height == -1;
                        if (layoutParams.weight > 0.0f) {
                            if (!z4) {
                                i292 = measuredHeight2;
                            }
                            i26 = Math.max(i26, i292);
                            i19 = i16;
                        } else {
                            if (z4) {
                                measuredHeight2 = i292;
                            }
                            i25 = Math.max(i25, measuredHeight2);
                            i26 = i26;
                            i19 = i16;
                        }
                        i24 = max2;
                        i27 = combineMeasuredStates2;
                        z9 = z11;
                        i22 = getChildrenSkipCount(view, i19) + i19;
                        f2 = f3;
                    }
                }
                i17 = 1073741824;
                if (mode2 == i17) {
                }
                z4 = false;
                int i2922 = layoutParams.topMargin + layoutParams.bottomMargin;
                int measuredHeight22 = view.getMeasuredHeight() + i2922;
                int combineMeasuredStates22 = View.combineMeasuredStates(i27, view.getMeasuredState());
                if (z2) {
                }
                int max22 = Math.max(i18, measuredHeight22);
                if (!z9) {
                }
                if (layoutParams.weight > 0.0f) {
                }
                i24 = max22;
                i27 = combineMeasuredStates22;
                z9 = z11;
                i22 = getChildrenSkipCount(view, i19) + i19;
                f2 = f3;
            }
            i22++;
            iArr2 = iArr2;
            z6 = z3;
            z5 = z2;
            i21 = 1073741824;
        }
        int i32 = i24;
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            this.mTotalLength += this.mDividerWidth;
        }
        if (iArr[1] == -1 && iArr[0] == -1 && iArr[2] == -1 && iArr[3] == -1) {
            i3 = i27;
        } else {
            i3 = i27;
            i32 = Math.max(i32, Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))) + Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))));
        }
        if (!z6 || !(mode == Integer.MIN_VALUE || mode == 0)) {
            i4 = i32;
        } else {
            this.mTotalLength = 0;
            int i33 = 0;
            while (i33 < virtualChildCount) {
                View virtualChildAt2 = getVirtualChildAt(i33);
                if (virtualChildAt2 == null) {
                    this.mTotalLength += measureNullChild(i33);
                    i15 = i32;
                } else if (virtualChildAt2.getVisibility() == 8) {
                    i33 += getChildrenSkipCount(virtualChildAt2, i33);
                    i15 = i32;
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                    if (z7) {
                        this.mTotalLength += layoutParams2.leftMargin + i23 + layoutParams2.rightMargin + getNextLocationOffset(virtualChildAt2);
                        i15 = i32;
                    } else {
                        int i34 = this.mTotalLength;
                        i15 = i32;
                        this.mTotalLength = Math.max(i34, i34 + i23 + layoutParams2.leftMargin + layoutParams2.rightMargin + getNextLocationOffset(virtualChildAt2));
                    }
                }
                i33++;
                i32 = i15;
            }
            i4 = i32;
        }
        this.mTotalLength += getPaddingLeft() + getPaddingRight();
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumWidth()), i, 0);
        int i35 = (16777215 & resolveSizeAndState) - this.mTotalLength;
        if (z8 || (i35 != 0 && f2 > 0.0f)) {
            float f4 = this.mWeightSum;
            if (f4 > 0.0f) {
                f2 = f4;
            }
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            this.mTotalLength = 0;
            int i36 = i25;
            int i37 = i3;
            int i38 = -1;
            float f5 = f2;
            int i39 = 0;
            while (i39 < virtualChildCount) {
                View virtualChildAt3 = getVirtualChildAt(i39);
                if (virtualChildAt3 == null) {
                    i11 = i35;
                    i10 = virtualChildCount;
                } else if (virtualChildAt3.getVisibility() == 8) {
                    i11 = i35;
                    i10 = virtualChildCount;
                } else {
                    LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                    float f6 = layoutParams3.weight;
                    if (f6 > 0.0f) {
                        int i40 = (int) ((((float) i35) * f6) / f5);
                        float f7 = f5 - f6;
                        int i41 = i35 - i40;
                        i10 = virtualChildCount;
                        int childMeasureSpec = getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + layoutParams3.topMargin + layoutParams3.bottomMargin, layoutParams3.height);
                        if (layoutParams3.width == 0) {
                            i14 = 1073741824;
                            if (mode == 1073741824) {
                                if (i40 <= 0) {
                                    i40 = 0;
                                }
                                virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(i40, 1073741824), childMeasureSpec);
                                i37 = View.combineMeasuredStates(i37, virtualChildAt3.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                                f5 = f7;
                                i11 = i41;
                            }
                        } else {
                            i14 = 1073741824;
                        }
                        int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i40;
                        if (measuredWidth2 < 0) {
                            measuredWidth2 = 0;
                        }
                        virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2, i14), childMeasureSpec);
                        i37 = View.combineMeasuredStates(i37, virtualChildAt3.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                        f5 = f7;
                        i11 = i41;
                    } else {
                        i11 = i35;
                        i10 = virtualChildCount;
                    }
                    if (z7) {
                        this.mTotalLength += virtualChildAt3.getMeasuredWidth() + layoutParams3.leftMargin + layoutParams3.rightMargin + getNextLocationOffset(virtualChildAt3);
                        f = f5;
                        i12 = 1073741824;
                    } else {
                        int i42 = this.mTotalLength;
                        f = f5;
                        this.mTotalLength = Math.max(i42, virtualChildAt3.getMeasuredWidth() + i42 + layoutParams3.leftMargin + layoutParams3.rightMargin + getNextLocationOffset(virtualChildAt3));
                        i12 = 1073741824;
                    }
                    boolean z12 = mode2 != i12 && layoutParams3.height == -1;
                    int i43 = layoutParams3.topMargin + layoutParams3.bottomMargin;
                    int measuredHeight3 = virtualChildAt3.getMeasuredHeight() + i43;
                    i38 = Math.max(i38, measuredHeight3);
                    if (!z12) {
                        i43 = measuredHeight3;
                    }
                    int max3 = Math.max(i36, i43);
                    if (z9) {
                        i13 = -1;
                        if (layoutParams3.height == -1) {
                            z = true;
                            if (!z5) {
                                int baseline2 = virtualChildAt3.getBaseline();
                                if (baseline2 != i13) {
                                    int i44 = ((((layoutParams3.gravity < 0 ? this.mGravity : layoutParams3.gravity) & 112) >> 4) & -2) >> 1;
                                    iArr[i44] = Math.max(iArr[i44], baseline2);
                                    iArr2[i44] = Math.max(iArr2[i44], measuredHeight3 - baseline2);
                                }
                            }
                            i36 = max3;
                            z9 = z;
                            f5 = f;
                        }
                    } else {
                        i13 = -1;
                    }
                    z = false;
                    if (!z5) {
                    }
                    i36 = max3;
                    z9 = z;
                    f5 = f;
                }
                i39++;
                i35 = i11;
                virtualChildCount = i10;
            }
            i5 = virtualChildCount;
            i6 = i2;
            this.mTotalLength += getPaddingLeft() + getPaddingRight();
            if (iArr[1] == -1 && iArr[0] == -1 && iArr[2] == -1 && iArr[3] == -1) {
                i9 = i38;
            } else {
                i9 = Math.max(i38, Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))) + Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))));
            }
            i7 = i9;
            i3 = i37;
            i8 = i36;
        } else {
            i8 = Math.max(i25, i26);
            if (z6 && mode != 1073741824) {
                for (int i45 = 0; i45 < virtualChildCount; i45++) {
                    View virtualChildAt4 = getVirtualChildAt(i45);
                    if (!(virtualChildAt4 == null || virtualChildAt4.getVisibility() == 8 || ((LayoutParams) virtualChildAt4.getLayoutParams()).weight <= 0.0f)) {
                        virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(i23, 1073741824), View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            i5 = virtualChildCount;
            i7 = i4;
            i6 = i2;
        }
        if (z9 || mode2 == 1073741824) {
            i8 = i7;
        }
        setMeasuredDimension(resolveSizeAndState | (i3 & ViewCompat.MEASURED_STATE_MASK), View.resolveSizeAndState(Math.max(i8 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i6, i3 << 16));
        if (z10) {
            forceUniformHeight(i5, i);
        }
    }

    private void forceUniformHeight(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, makeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: package-private */
    public void layoutVertical(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int paddingLeft = getPaddingLeft();
        int i7 = i3 - i;
        int paddingRight = i7 - getPaddingRight();
        int paddingRight2 = (i7 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i8 = this.mGravity;
        int i9 = i8 & 112;
        int i10 = i8 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (i9 == 16) {
            i5 = getPaddingTop() + (((i4 - i2) - this.mTotalLength) / 2);
        } else if (i9 != 80) {
            i5 = getPaddingTop();
        } else {
            i5 = ((getPaddingTop() + i4) - i2) - this.mTotalLength;
        }
        int i11 = 0;
        while (i11 < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i11);
            if (virtualChildAt == null) {
                i5 += measureNullChild(i11);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                int i12 = layoutParams.gravity;
                if (i12 < 0) {
                    i12 = i10;
                }
                int absoluteGravity = GravityCompat.getAbsoluteGravity(i12, ViewCompat.getLayoutDirection(this)) & 7;
                if (absoluteGravity == 1) {
                    i6 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                } else if (absoluteGravity != 5) {
                    i6 = layoutParams.leftMargin + paddingLeft;
                } else {
                    i6 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                }
                if (hasDividerBeforeChildAt(i11)) {
                    i5 += this.mDividerHeight;
                }
                int i13 = i5 + layoutParams.topMargin;
                setChildFrame(virtualChildAt, i6, i13 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                i11 += getChildrenSkipCount(virtualChildAt, i11);
                i5 = i13 + measuredHeight + layoutParams.bottomMargin + getNextLocationOffset(virtualChildAt);
            }
            i11++;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x010e  */
    public void layoutHorizontal(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingTop = getPaddingTop();
        int i16 = i4 - i2;
        int paddingBottom = i16 - getPaddingBottom();
        int paddingBottom2 = (i16 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        int i17 = this.mGravity;
        int i18 = i17 & 112;
        boolean z = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        int absoluteGravity = GravityCompat.getAbsoluteGravity(8388615 & i17, ViewCompat.getLayoutDirection(this));
        if (absoluteGravity == 1) {
            i5 = getPaddingLeft() + (((i3 - i) - this.mTotalLength) / 2);
        } else if (absoluteGravity != 5) {
            i5 = getPaddingLeft();
        } else {
            i5 = ((getPaddingLeft() + i3) - i) - this.mTotalLength;
        }
        if (isLayoutRtl) {
            i7 = virtualChildCount - 1;
            i6 = -1;
        } else {
            i7 = 0;
            i6 = 1;
        }
        int i19 = 0;
        while (i19 < virtualChildCount) {
            int i20 = i7 + (i6 * i19);
            View virtualChildAt = getVirtualChildAt(i20);
            if (virtualChildAt == null) {
                i5 += measureNullChild(i20);
                i8 = paddingTop;
                i10 = virtualChildCount;
                i9 = i18;
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (z) {
                    i11 = i19;
                    i10 = virtualChildCount;
                    if (layoutParams.height != -1) {
                        i12 = virtualChildAt.getBaseline();
                        i13 = layoutParams.gravity;
                        if (i13 < 0) {
                            i13 = i18;
                        }
                        i14 = i13 & 112;
                        i9 = i18;
                        if (i14 != 16) {
                            i15 = ((((paddingBottom2 - measuredHeight) / 2) + paddingTop) + layoutParams.topMargin) - layoutParams.bottomMargin;
                        } else if (i14 == 48) {
                            int i21 = layoutParams.topMargin + paddingTop;
                            i15 = i12 != -1 ? i21 + (iArr[1] - i12) : i21;
                        } else if (i14 != 80) {
                            i15 = paddingTop;
                        } else {
                            int i22 = (paddingBottom - measuredHeight) - layoutParams.bottomMargin;
                            i15 = i12 != -1 ? i22 - (iArr2[2] - (virtualChildAt.getMeasuredHeight() - i12)) : i22;
                        }
                        if (hasDividerBeforeChildAt(i20)) {
                            i5 += this.mDividerWidth;
                        }
                        int i23 = layoutParams.leftMargin + i5;
                        i8 = paddingTop;
                        setChildFrame(virtualChildAt, i23 + getLocationOffset(virtualChildAt), i15, measuredWidth, measuredHeight);
                        i19 = i11 + getChildrenSkipCount(virtualChildAt, i20);
                        i5 = i23 + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(virtualChildAt);
                    }
                } else {
                    i11 = i19;
                    i10 = virtualChildCount;
                }
                i12 = -1;
                i13 = layoutParams.gravity;
                if (i13 < 0) {
                }
                i14 = i13 & 112;
                i9 = i18;
                if (i14 != 16) {
                }
                if (hasDividerBeforeChildAt(i20)) {
                }
                int i232 = layoutParams.leftMargin + i5;
                i8 = paddingTop;
                setChildFrame(virtualChildAt, i232 + getLocationOffset(virtualChildAt), i15, measuredWidth, measuredHeight);
                i19 = i11 + getChildrenSkipCount(virtualChildAt, i20);
                i5 = i232 + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(virtualChildAt);
            } else {
                i8 = paddingTop;
                i10 = virtualChildCount;
                i9 = i18;
            }
            i19++;
            virtualChildCount = i10;
            i18 = i9;
            paddingTop = i8;
        }
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= GravityCompat.START;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int i3 = this.mGravity;
        if ((8388615 & i3) != i2) {
            this.mGravity = i2 | (-8388616 & i3);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.mGravity;
        if ((i3 & 112) != i2) {
            this.mGravity = i2 | (i3 & -113);
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        int i = this.mOrientation;
        if (i == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LinearLayoutCompat_Layout);
            this.weight = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2);
            this.gravity = -1;
            this.weight = f;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = -1;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.gravity = -1;
            this.weight = layoutParams.weight;
            this.gravity = layoutParams.gravity;
        }
    }
}
