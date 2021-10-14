package com.pushwoosh.inbox.ui;

import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import androidx.annotation.AnimRes;
import androidx.annotation.DrawableRes;
import com.pushwoosh.inbox.ui.model.customizing.formatter.DefaultDateFormatter;
import com.pushwoosh.inbox.ui.model.customizing.formatter.InboxDateFormatter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\r\n\u0002\b \n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010w\u001a\u00020xR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\f\u0010\u0007\"\u0004\b\r\u0010\tR\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u000f\u0010\u0007\"\u0004\b\u0010\u0010\tR\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0012\u0010\u0007\"\u0004\b\u0013\u0010\tR\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0015\u0010\u0007\"\u0004\b\u0016\u0010\tR\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u001e\u0010\u0007\"\u0004\b\u001f\u0010\tR\u001a\u0010 \u001a\u00020!X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001e\u0010&\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u0010\n\u0002\u0010,\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001e\u0010-\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u00102\u001a\u0004\u0018\u000103X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001e\u00108\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b9\u0010\u0007\"\u0004\b:\u0010\tR\u001e\u0010;\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u0010\n\u0002\u0010,\u001a\u0004\b<\u0010)\"\u0004\b=\u0010+R\u001e\u0010>\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b?\u0010\u0007\"\u0004\b@\u0010\tR\u001e\u0010A\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\bB\u0010\u0007\"\u0004\bC\u0010\tR\u001e\u0010D\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\bE\u0010\u0007\"\u0004\bF\u0010\tR\u001c\u0010G\u001a\u0004\u0018\u00010HX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u001e\u0010M\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010/\"\u0004\bO\u00101R\u001a\u0010P\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010/\"\u0004\bR\u00101R\u001c\u0010S\u001a\u0004\u0018\u000103X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u00105\"\u0004\bU\u00107R\u001c\u0010V\u001a\u0004\u0018\u00010WX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R\u001a\u0010\\\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010/\"\u0004\b^\u00101R\u001c\u0010_\u001a\u0004\u0018\u000103X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u00105\"\u0004\ba\u00107R\u001c\u0010b\u001a\u0004\u0018\u00010WX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010Y\"\u0004\bd\u0010[R\u001e\u0010e\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\bf\u0010\u0007\"\u0004\bg\u0010\tR\u001e\u0010h\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\bi\u0010\u0007\"\u0004\bj\u0010\tR\u001e\u0010k\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\bl\u0010\u0007\"\u0004\bm\u0010\tR\u001e\u0010n\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\bo\u0010\u0007\"\u0004\bp\u0010\tR\u001e\u0010q\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\br\u0010\u0007\"\u0004\bs\u0010\tR\u001e\u0010t\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u0010\n\u0002\u0010,\u001a\u0004\bu\u0010)\"\u0004\bv\u0010+¨\u0006y"}, d2 = {"Lcom/pushwoosh/inbox/ui/PushwooshInboxStyle;", "", "()V", "EMPTY_ANIMATION", "", "accentColor", "getAccentColor", "()Ljava/lang/Integer;", "setAccentColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "backgroundColor", "getBackgroundColor", "setBackgroundColor", "barAccentColor", "getBarAccentColor", "setBarAccentColor", "barBackgroundColor", "getBarBackgroundColor", "setBarBackgroundColor", "barTextColor", "getBarTextColor", "setBarTextColor", "barTitle", "", "getBarTitle", "()Ljava/lang/String;", "setBarTitle", "(Ljava/lang/String;)V", "dateColor", "getDateColor", "setDateColor", "dateFormatter", "Lcom/pushwoosh/inbox/ui/model/customizing/formatter/InboxDateFormatter;", "getDateFormatter", "()Lcom/pushwoosh/inbox/ui/model/customizing/formatter/InboxDateFormatter;", "setDateFormatter", "(Lcom/pushwoosh/inbox/ui/model/customizing/formatter/InboxDateFormatter;)V", "dateTextSize", "", "getDateTextSize", "()Ljava/lang/Float;", "setDateTextSize", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "defaultImageIcon", "getDefaultImageIcon", "()I", "setDefaultImageIcon", "(I)V", "defaultImageIconDrawable", "Landroid/graphics/drawable/Drawable;", "getDefaultImageIconDrawable", "()Landroid/graphics/drawable/Drawable;", "setDefaultImageIconDrawable", "(Landroid/graphics/drawable/Drawable;)V", "descriptionColor", "getDescriptionColor", "setDescriptionColor", "descriptionTextSize", "getDescriptionTextSize", "setDescriptionTextSize", "dividerColor", "getDividerColor", "setDividerColor", "highlightColor", "getHighlightColor", "setHighlightColor", "imageTypeColor", "getImageTypeColor", "setImageTypeColor", "listAnimation", "Landroid/view/animation/Animation;", "getListAnimation", "()Landroid/view/animation/Animation;", "setListAnimation", "(Landroid/view/animation/Animation;)V", "listAnimationResource", "getListAnimationResource", "setListAnimationResource", "listEmptyImage", "getListEmptyImage", "setListEmptyImage", "listEmptyImageDrawable", "getListEmptyImageDrawable", "setListEmptyImageDrawable", "listEmptyText", "", "getListEmptyText", "()Ljava/lang/CharSequence;", "setListEmptyText", "(Ljava/lang/CharSequence;)V", "listErrorImage", "getListErrorImage", "setListErrorImage", "listErrorImageDrawable", "getListErrorImageDrawable", "setListErrorImageDrawable", "listErrorMessage", "getListErrorMessage", "setListErrorMessage", "readDateColor", "getReadDateColor", "setReadDateColor", "readDescriptionColor", "getReadDescriptionColor", "setReadDescriptionColor", "readImageTypeColor", "getReadImageTypeColor", "setReadImageTypeColor", "readTitleColor", "getReadTitleColor", "setReadTitleColor", "titleColor", "getTitleColor", "setTitleColor", "titleTextSize", "getTitleTextSize", "setTitleTextSize", "clearColors", "", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: PushwooshInboxStyle.kt */
public final class PushwooshInboxStyle {
    public static final int EMPTY_ANIMATION = -1;
    public static final PushwooshInboxStyle INSTANCE = new PushwooshInboxStyle();
    @Nullable
    private static Integer accentColor;
    @Nullable
    private static Integer backgroundColor;
    @Nullable
    private static Integer barAccentColor;
    @Nullable
    private static Integer barBackgroundColor;
    @Nullable
    private static Integer barTextColor;
    @Nullable
    private static String barTitle;
    @Nullable
    private static Integer dateColor;
    @NotNull
    private static InboxDateFormatter dateFormatter = new DefaultDateFormatter(null, 1, null);
    @Nullable
    private static Float dateTextSize;
    @DrawableRes
    private static int defaultImageIcon = -1;
    @Nullable
    private static Drawable defaultImageIconDrawable;
    @Nullable
    private static Integer descriptionColor;
    @Nullable
    private static Float descriptionTextSize;
    @Nullable
    private static Integer dividerColor;
    @Nullable
    private static Integer highlightColor;
    @Nullable
    private static Integer imageTypeColor;
    @Nullable
    private static Animation listAnimation;
    @AnimRes
    private static int listAnimationResource = 17432578;
    private static int listEmptyImage = R.drawable.inbox_ic_empty;
    @Nullable
    private static Drawable listEmptyImageDrawable;
    @Nullable
    private static CharSequence listEmptyText = "Currently there are no messages in the Inbox.";
    private static int listErrorImage = R.drawable.inbox_ic_error;
    @Nullable
    private static Drawable listErrorImageDrawable;
    @Nullable
    private static CharSequence listErrorMessage = "It seems something went wrong. Please try again later!";
    @Nullable
    private static Integer readDateColor;
    @Nullable
    private static Integer readDescriptionColor;
    @Nullable
    private static Integer readImageTypeColor;
    @Nullable
    private static Integer readTitleColor;
    @Nullable
    private static Integer titleColor;
    @Nullable
    private static Float titleTextSize;

    private PushwooshInboxStyle() {
    }

    @NotNull
    public final InboxDateFormatter getDateFormatter() {
        return dateFormatter;
    }

    public final void setDateFormatter(@NotNull InboxDateFormatter inboxDateFormatter) {
        Intrinsics.checkParameterIsNotNull(inboxDateFormatter, "<set-?>");
        dateFormatter = inboxDateFormatter;
    }

    public final int getListAnimationResource() {
        return listAnimationResource;
    }

    public final void setListAnimationResource(int i) {
        listAnimationResource = i;
    }

    @Nullable
    public final Animation getListAnimation() {
        return listAnimation;
    }

    public final void setListAnimation(@Nullable Animation animation) {
        listAnimation = animation;
    }

    public final int getDefaultImageIcon() {
        return defaultImageIcon;
    }

    public final void setDefaultImageIcon(int i) {
        defaultImageIcon = i;
    }

    @Nullable
    public final Drawable getDefaultImageIconDrawable() {
        return defaultImageIconDrawable;
    }

    public final void setDefaultImageIconDrawable(@Nullable Drawable drawable) {
        defaultImageIconDrawable = drawable;
    }

    public final int getListErrorImage() {
        return listErrorImage;
    }

    public final void setListErrorImage(int i) {
        listErrorImage = i;
    }

    @Nullable
    public final Drawable getListErrorImageDrawable() {
        return listErrorImageDrawable;
    }

    public final void setListErrorImageDrawable(@Nullable Drawable drawable) {
        listErrorImageDrawable = drawable;
    }

    @Nullable
    public final CharSequence getListErrorMessage() {
        return listErrorMessage;
    }

    public final void setListErrorMessage(@Nullable CharSequence charSequence) {
        listErrorMessage = charSequence;
    }

    public final int getListEmptyImage() {
        return listEmptyImage;
    }

    public final void setListEmptyImage(int i) {
        listEmptyImage = i;
    }

    @Nullable
    public final Drawable getListEmptyImageDrawable() {
        return listEmptyImageDrawable;
    }

    public final void setListEmptyImageDrawable(@Nullable Drawable drawable) {
        listEmptyImageDrawable = drawable;
    }

    @Nullable
    public final CharSequence getListEmptyText() {
        return listEmptyText;
    }

    public final void setListEmptyText(@Nullable CharSequence charSequence) {
        listEmptyText = charSequence;
    }

    @Nullable
    public final Integer getAccentColor() {
        return accentColor;
    }

    public final void setAccentColor(@Nullable Integer num) {
        accentColor = num;
    }

    @Nullable
    public final Integer getBackgroundColor() {
        return backgroundColor;
    }

    public final void setBackgroundColor(@Nullable Integer num) {
        backgroundColor = num;
    }

    @Nullable
    public final Integer getHighlightColor() {
        return highlightColor;
    }

    public final void setHighlightColor(@Nullable Integer num) {
        highlightColor = num;
    }

    @Nullable
    public final Integer getImageTypeColor() {
        return imageTypeColor;
    }

    public final void setImageTypeColor(@Nullable Integer num) {
        imageTypeColor = num;
    }

    @Nullable
    public final Integer getReadImageTypeColor() {
        return readImageTypeColor;
    }

    public final void setReadImageTypeColor(@Nullable Integer num) {
        readImageTypeColor = num;
    }

    @Nullable
    public final Integer getTitleColor() {
        return titleColor;
    }

    public final void setTitleColor(@Nullable Integer num) {
        titleColor = num;
    }

    @Nullable
    public final Integer getReadTitleColor() {
        return readTitleColor;
    }

    public final void setReadTitleColor(@Nullable Integer num) {
        readTitleColor = num;
    }

    @Nullable
    public final Integer getDescriptionColor() {
        return descriptionColor;
    }

    public final void setDescriptionColor(@Nullable Integer num) {
        descriptionColor = num;
    }

    @Nullable
    public final Integer getReadDescriptionColor() {
        return readDescriptionColor;
    }

    public final void setReadDescriptionColor(@Nullable Integer num) {
        readDescriptionColor = num;
    }

    @Nullable
    public final Integer getDateColor() {
        return dateColor;
    }

    public final void setDateColor(@Nullable Integer num) {
        dateColor = num;
    }

    @Nullable
    public final Integer getReadDateColor() {
        return readDateColor;
    }

    public final void setReadDateColor(@Nullable Integer num) {
        readDateColor = num;
    }

    @Nullable
    public final Integer getDividerColor() {
        return dividerColor;
    }

    public final void setDividerColor(@Nullable Integer num) {
        dividerColor = num;
    }

    @Nullable
    public final Integer getBarBackgroundColor() {
        return barBackgroundColor;
    }

    public final void setBarBackgroundColor(@Nullable Integer num) {
        barBackgroundColor = num;
    }

    @Nullable
    public final Integer getBarAccentColor() {
        return barAccentColor;
    }

    public final void setBarAccentColor(@Nullable Integer num) {
        barAccentColor = num;
    }

    @Nullable
    public final Integer getBarTextColor() {
        return barTextColor;
    }

    public final void setBarTextColor(@Nullable Integer num) {
        barTextColor = num;
    }

    @Nullable
    public final String getBarTitle() {
        return barTitle;
    }

    public final void setBarTitle(@Nullable String str) {
        barTitle = str;
    }

    @Nullable
    public final Float getTitleTextSize() {
        return titleTextSize;
    }

    public final void setTitleTextSize(@Nullable Float f) {
        titleTextSize = f;
    }

    @Nullable
    public final Float getDescriptionTextSize() {
        return descriptionTextSize;
    }

    public final void setDescriptionTextSize(@Nullable Float f) {
        descriptionTextSize = f;
    }

    @Nullable
    public final Float getDateTextSize() {
        return dateTextSize;
    }

    public final void setDateTextSize(@Nullable Float f) {
        dateTextSize = f;
    }

    public final void clearColors() {
        Integer num = null;
        accentColor = num;
        backgroundColor = num;
        highlightColor = num;
        imageTypeColor = num;
        readImageTypeColor = num;
        titleColor = num;
        readTitleColor = num;
        descriptionColor = num;
        readDescriptionColor = num;
        dateColor = num;
        readDateColor = num;
        dividerColor = num;
    }
}
