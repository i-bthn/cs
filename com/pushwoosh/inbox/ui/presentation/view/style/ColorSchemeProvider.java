package com.pushwoosh.inbox.ui.presentation.view.style;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000bR\u0012\u0010\u0012\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u0014\u0010\u0014\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000bR\u0012\u0010\u0016\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000fR\u0012\u0010\u0018\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/style/ColorSchemeProvider;", "", "accentColor", "", "getAccentColor", "()I", "backgroundColor", "getBackgroundColor", "cellBackground", "Landroid/graphics/drawable/Drawable;", "getCellBackground", "()Landroid/graphics/drawable/Drawable;", "dateColor", "Landroid/content/res/ColorStateList;", "getDateColor", "()Landroid/content/res/ColorStateList;", "defaultIcon", "getDefaultIcon", "descriptionColor", "getDescriptionColor", "divider", "getDivider", "imageColor", "getImageColor", "titleColor", "getTitleColor", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ColorSchemeProvider.kt */
public interface ColorSchemeProvider {
    int getAccentColor();

    int getBackgroundColor();

    @Nullable
    Drawable getCellBackground();

    @NotNull
    ColorStateList getDateColor();

    @Nullable
    Drawable getDefaultIcon();

    @NotNull
    ColorStateList getDescriptionColor();

    @Nullable
    Drawable getDivider();

    @NotNull
    ColorStateList getImageColor();

    @NotNull
    ColorStateList getTitleColor();
}
