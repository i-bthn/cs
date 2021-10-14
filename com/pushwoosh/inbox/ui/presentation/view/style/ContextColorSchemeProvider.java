package com.pushwoosh.inbox.ui.presentation.view.style;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import com.pushwoosh.inbox.ui.PushwooshInboxStyle;
import com.pushwoosh.inbox.ui.R;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010$\n\u0002\b\u000e\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J)\u0010/\u001a\u00020\u000b2\u001a\u00100\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u0006010*H\u0002¢\u0006\u0002\u00102J\u0014\u00103\u001a\u000604j\u0002`52\u0006\u00106\u001a\u00020\u0006H\u0002J\b\u00107\u001a\u00020\tH\u0002J\u0012\u00108\u001a\u00020\u00062\b\b\u0001\u00106\u001a\u00020\u0006H\u0002J\u0014\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u001bH\u0002J\u0012\u0010:\u001a\u00020\u00062\b\b\u0001\u00106\u001a\u00020\u0006H\u0002J\u0014\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\u001bH\u0002J0\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020+2\u001e\u0010?\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020=0@H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00020\u00068\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0013R\u0014\u0010\u0017\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u001bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u00020\u000b8VX\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u0004\u0018\u00010\t8VX\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0019R\u0014\u0010!\u001a\u00020\u000b8VX\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u001eR\u0016\u0010#\u001a\u0004\u0018\u00010\t8VX\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0019R\u001c\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001bX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010&\u001a\u00020\u00068\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\u00020\u000b8VX\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u001eR\u0016\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*X\u0004¢\u0006\u0004\n\u0002\u0010,R\u0014\u0010-\u001a\u00020\u000b8VX\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u001e¨\u0006B"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/style/ContextColorSchemeProvider;", "Lcom/pushwoosh/inbox/ui/presentation/view/style/ColorSchemeProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_accentColor", "", "_backgroundColor", "_cellBackground", "Landroid/graphics/drawable/Drawable;", "_dateColor", "Landroid/content/res/ColorStateList;", "_defaultIcon", "_descriptionColor", "_divider", "_imageColor", "_titleColor", "accentColor", "getAccentColor", "()I", "background", "backgroundColor", "getBackgroundColor", "cellBackground", "getCellBackground", "()Landroid/graphics/drawable/Drawable;", "colorResources", "", "dateColor", "getDateColor", "()Landroid/content/res/ColorStateList;", "defaultIcon", "getDefaultIcon", "descriptionColor", "getDescriptionColor", "divider", "getDivider", "drawableResources", "highlightColor", "imageColor", "getImageColor", "states", "", "", "[[I", "titleColor", "getTitleColor", "generateStateList", "colorsState", "Lkotlin/Pair;", "([Lkotlin/Pair;)Landroid/content/res/ColorStateList;", "getResourceException", "Ljava/lang/Exception;", "Lkotlin/Exception;", "attr", "provideCellBackground", "provideColorByAttr", "provideColorMap", "provideDefaultColor", "provideDrawableMap", "provideFromResource", "", "attrs", "callback", "Lkotlin/Function3;", "Landroid/content/res/TypedArray;", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ContextColorSchemeProvider.kt */
public final class ContextColorSchemeProvider implements ColorSchemeProvider {
    private final int _accentColor;
    private final int _backgroundColor;
    private final Drawable _cellBackground;
    private final ColorStateList _dateColor;
    private final Drawable _defaultIcon;
    private final ColorStateList _descriptionColor;
    private final Drawable _divider;
    private final ColorStateList _imageColor;
    private final ColorStateList _titleColor;
    @ColorRes
    private int background;
    private final Map<Integer, Integer> colorResources = provideColorMap();
    private final Context context;
    private final Map<Integer, Drawable> drawableResources = provideDrawableMap();
    @ColorRes
    private int highlightColor;
    private final int[][] states = {new int[]{16842913}, new int[0]};

    public ContextColorSchemeProvider(@NotNull Context context2) {
        Drawable drawable;
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
        Integer highlightColor2 = PushwooshInboxStyle.INSTANCE.getHighlightColor();
        this.highlightColor = highlightColor2 != null ? highlightColor2.intValue() : provideColorByAttr(R.attr.inboxHighlightColor);
        Integer backgroundColor = PushwooshInboxStyle.INSTANCE.getBackgroundColor();
        this.background = backgroundColor != null ? backgroundColor.intValue() : provideColorByAttr(R.attr.inboxBackgroundColor);
        Integer accentColor = PushwooshInboxStyle.INSTANCE.getAccentColor();
        this._accentColor = accentColor != null ? accentColor.intValue() : provideColorByAttr(R.attr.inboxAccentColor);
        Integer dividerColor = PushwooshInboxStyle.INSTANCE.getDividerColor();
        if (dividerColor != null) {
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{dividerColor.intValue(), dividerColor.intValue()});
            Resources resources = this.context.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
            gradientDrawable.setSize(resources.getDisplayMetrics().widthPixels, this.context.getResources().getDimensionPixelSize(R.dimen.pw_divider_size));
            drawable = gradientDrawable;
        } else {
            drawable = this.drawableResources.get(16843284);
        }
        this._divider = drawable;
        this._imageColor = generateStateList(new Pair[]{new Pair<>(PushwooshInboxStyle.INSTANCE.getImageTypeColor(), Integer.valueOf(provideColorByAttr(R.attr.inboxImageTypeColor))), new Pair<>(PushwooshInboxStyle.INSTANCE.getReadImageTypeColor(), Integer.valueOf(provideColorByAttr(R.attr.inboxReadImageTypeColor)))});
        this._titleColor = generateStateList(new Pair[]{new Pair<>(PushwooshInboxStyle.INSTANCE.getTitleColor(), Integer.valueOf(provideColorByAttr(R.attr.inboxTitleColor))), new Pair<>(PushwooshInboxStyle.INSTANCE.getReadTitleColor(), Integer.valueOf(provideColorByAttr(R.attr.inboxReadTitleColor)))});
        this._descriptionColor = generateStateList(new Pair[]{new Pair<>(PushwooshInboxStyle.INSTANCE.getDescriptionColor(), Integer.valueOf(provideColorByAttr(R.attr.inboxDescriptionColor))), new Pair<>(PushwooshInboxStyle.INSTANCE.getReadDescriptionColor(), Integer.valueOf(provideColorByAttr(R.attr.inboxReadDescriptionColor)))});
        this._dateColor = generateStateList(new Pair[]{new Pair<>(PushwooshInboxStyle.INSTANCE.getDateColor(), Integer.valueOf(provideColorByAttr(R.attr.inboxDateColor))), new Pair<>(PushwooshInboxStyle.INSTANCE.getReadDateColor(), Integer.valueOf(provideColorByAttr(R.attr.inboxReadDateColor)))});
        this._cellBackground = provideCellBackground();
        this._backgroundColor = this.background;
        Drawable drawable2 = this.drawableResources.get(Integer.valueOf(R.attr.inboxDefaultIcon));
        Drawable loadIcon = this.context.getApplicationInfo().loadIcon(this.context.getPackageManager());
        if (PushwooshInboxStyle.INSTANCE.getDefaultImageIconDrawable() != null) {
            drawable2 = PushwooshInboxStyle.INSTANCE.getDefaultImageIconDrawable();
        } else if (PushwooshInboxStyle.INSTANCE.getDefaultImageIcon() == -1 ? drawable2 == null : (drawable2 = ContextCompat.getDrawable(this.context, PushwooshInboxStyle.INSTANCE.getDefaultImageIcon())) == null) {
            drawable2 = loadIcon;
        }
        this._defaultIcon = drawable2;
    }

    @Override // com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProvider
    @Nullable
    public Drawable getDefaultIcon() {
        return this._defaultIcon;
    }

    @Override // com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProvider
    @NotNull
    public Drawable getCellBackground() {
        return provideCellBackground();
    }

    @Override // com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProvider
    @NotNull
    public ColorStateList getTitleColor() {
        return this._titleColor;
    }

    @Override // com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProvider
    @NotNull
    public ColorStateList getDescriptionColor() {
        return this._descriptionColor;
    }

    @Override // com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProvider
    @NotNull
    public ColorStateList getDateColor() {
        return this._dateColor;
    }

    @Override // com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProvider
    @Nullable
    public Drawable getDivider() {
        return this._divider;
    }

    @Override // com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProvider
    public int getAccentColor() {
        return this._accentColor;
    }

    @Override // com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProvider
    @NotNull
    public ColorStateList getImageColor() {
        return this._imageColor;
    }

    @Override // com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProvider
    public int getBackgroundColor() {
        return this._backgroundColor;
    }

    private final int provideColorByAttr(@AttrRes int i) {
        Integer num = this.colorResources.get(Integer.valueOf(i));
        if (num != null && num.intValue() != 0) {
            return num.intValue();
        }
        int provideDefaultColor = provideDefaultColor(i);
        if (provideDefaultColor != 0) {
            return provideDefaultColor;
        }
        throw getResourceException(i);
    }

    private final Map<Integer, Integer> provideColorMap() {
        int[] iArr = {R.attr.inboxAccentColor, R.attr.inboxBackgroundColor, R.attr.inboxHighlightColor, R.attr.inboxImageTypeColor, R.attr.inboxReadImageTypeColor, R.attr.inboxTitleColor, R.attr.inboxReadTitleColor, R.attr.inboxDescriptionColor, R.attr.inboxReadDescriptionColor, R.attr.inboxDateColor, R.attr.inboxReadDateColor, R.attr.colorAccent, R.attr.colorControlHighlight, 16842836, 16842806, 16842808};
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        provideFromResource(iArr, new ContextColorSchemeProvider$provideColorMap$1(linkedHashMap));
        return linkedHashMap;
    }

    private final Map<Integer, Drawable> provideDrawableMap() {
        int[] iArr = {16843284, R.attr.inboxDefaultIcon};
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        provideFromResource(iArr, new ContextColorSchemeProvider$provideDrawableMap$1(linkedHashMap));
        return linkedHashMap;
    }

    private final void provideFromResource(int[] iArr, Function3<? super Integer, ? super Integer, ? super TypedArray, Unit> function3) {
        TypedArray obtainStyledAttributes = this.context.getTheme().obtainStyledAttributes(iArr);
        try {
            int length = iArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = iArr[i];
                int i4 = i2 + 1;
                Integer valueOf = Integer.valueOf(i2);
                Integer valueOf2 = Integer.valueOf(i3);
                Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "typedArray");
                function3.invoke(valueOf, valueOf2, obtainStyledAttributes);
                i++;
                i2 = i4;
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private final Drawable provideCellBackground() {
        return DrawableUtilsKt.getAdaptiveRippleDrawable(this.background, this.highlightColor);
    }

    private final int provideDefaultColor(@AttrRes int i) {
        Integer num;
        if (i == R.attr.inboxAccentColor) {
            num = Integer.valueOf(provideColorByAttr(R.attr.colorAccent));
        } else if (i == R.attr.inboxBackgroundColor) {
            num = Integer.valueOf(provideColorByAttr(16842836));
        } else if (i == R.attr.inboxHighlightColor) {
            num = Integer.valueOf(provideColorByAttr(R.attr.colorControlHighlight));
        } else if (i == R.attr.inboxImageTypeColor) {
            num = Integer.valueOf(provideColorByAttr(R.attr.inboxAccentColor));
        } else if (i == R.attr.inboxReadImageTypeColor) {
            num = Integer.valueOf(provideColorByAttr(R.attr.inboxReadDateColor));
        } else if (i == R.attr.inboxTitleColor) {
            num = Integer.valueOf(provideColorByAttr(16842806));
        } else {
            num = (i == R.attr.inboxReadTitleColor || i == R.attr.inboxReadDescriptionColor || i == R.attr.inboxDescriptionColor || i == R.attr.inboxDateColor || i == R.attr.inboxReadDateColor) ? Integer.valueOf(provideColorByAttr(16842808)) : null;
        }
        if (num != null) {
            return num.intValue();
        }
        throw getResourceException(i);
    }

    private final Exception getResourceException(int i) {
        return new IllegalArgumentException("Unknown attribute please set up " + this.context.getResources().getResourceName(i) + " into your theme");
    }

    private final ColorStateList generateStateList(Pair<Integer, Integer>[] pairArr) {
        ArrayList arrayList = new ArrayList(pairArr.length);
        for (Pair<Integer, Integer> pair : pairArr) {
            Integer first = pair.getFirst();
            arrayList.add(Integer.valueOf(first != null ? first.intValue() : pair.getSecond().intValue()));
        }
        return new ColorStateList(this.states, CollectionsKt.toIntArray(arrayList));
    }
}
