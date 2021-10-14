package com.pushwoosh.inbox.ui.presentation.view.adapter.inbox;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.data.InboxMessageType;
import com.pushwoosh.inbox.ui.PushwooshInboxStyle;
import com.pushwoosh.inbox.ui.R;
import com.pushwoosh.inbox.ui.presentation.view.adapter.BaseRecyclerAdapter;
import com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProvider;
import com.pushwoosh.inbox.ui.presentation.view.view.TintableImageView;
import com.pushwoosh.inbox.ui.utils.DateExtensionKt;
import com.pushwoosh.inbox.ui.utils.GlideUtils;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.apache.cordova.globalization.Globalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\n¢\u0006\u0002\u0010\u000eJ\u001a\u0010\u0013\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J,\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0002J/\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00162\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020\u001bH\u0002¢\u0006\u0002\u0010&J\u0010\u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020$H\u0002R,\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/adapter/inbox/InboxViewHolder;", "Lcom/pushwoosh/inbox/ui/presentation/view/adapter/BaseRecyclerAdapter$ViewHolder;", "Lcom/pushwoosh/inbox/data/InboxMessage;", "viewGroup", "Landroid/view/ViewGroup;", "adapter", "Lcom/pushwoosh/inbox/ui/presentation/view/adapter/inbox/InboxAdapter;", "colorSchemeProvider", "Lcom/pushwoosh/inbox/ui/presentation/view/style/ColorSchemeProvider;", "attachmentClickListener", "Lkotlin/Function2;", "", "Landroid/view/View;", "", "(Landroid/view/ViewGroup;Lcom/pushwoosh/inbox/ui/presentation/view/adapter/inbox/InboxAdapter;Lcom/pushwoosh/inbox/ui/presentation/view/style/ColorSchemeProvider;Lkotlin/jvm/functions/Function2;)V", "getAttachmentClickListener", "()Lkotlin/jvm/functions/Function2;", "setAttachmentClickListener", "(Lkotlin/jvm/functions/Function2;)V", "fillView", "model", "position", "", "getInboxLabelText", "", "title", "titleColor", "Landroid/content/res/ColorStateList;", Globalization.DATE, "dateColor", "getTextAppearanceSpan", "Landroid/text/style/TextAppearanceSpan;", "context", "Landroid/content/Context;", "appearance", "textSizeSp", "", "colorStateList", "(Landroid/content/Context;ILjava/lang/Float;Landroid/content/res/ColorStateList;)Landroid/text/style/TextAppearanceSpan;", "spToPx", "sp", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: InboxViewHolder.kt */
public final class InboxViewHolder extends BaseRecyclerAdapter.ViewHolder<InboxMessage> {
    @NotNull
    private Function2<? super String, ? super View, Unit> attachmentClickListener;
    private final ColorSchemeProvider colorSchemeProvider;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InboxViewHolder(@NotNull ViewGroup viewGroup, @NotNull InboxAdapter inboxAdapter, @NotNull ColorSchemeProvider colorSchemeProvider2, @NotNull Function2<? super String, ? super View, Unit> function2) {
        super(R.layout.pw_item_inbox, viewGroup, inboxAdapter);
        Intrinsics.checkParameterIsNotNull(viewGroup, "viewGroup");
        Intrinsics.checkParameterIsNotNull(inboxAdapter, "adapter");
        Intrinsics.checkParameterIsNotNull(colorSchemeProvider2, "colorSchemeProvider");
        Intrinsics.checkParameterIsNotNull(function2, "attachmentClickListener");
        this.colorSchemeProvider = colorSchemeProvider2;
        this.attachmentClickListener = function2;
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: kotlin.jvm.functions.Function2<? super java.lang.String, ? super android.view.View, kotlin.Unit>, kotlin.jvm.functions.Function2<java.lang.String, android.view.View, kotlin.Unit> */
    @NotNull
    public final Function2<String, View, Unit> getAttachmentClickListener() {
        return this.attachmentClickListener;
    }

    public final void setAttachmentClickListener(@NotNull Function2<? super String, ? super View, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.attachmentClickListener = function2;
    }

    public void fillView(@Nullable InboxMessage inboxMessage, int i) {
        RequestBuilder<Drawable> requestBuilder;
        if (inboxMessage != null) {
            View view = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
            view.setBackground(this.colorSchemeProvider.getCellBackground());
            View view2 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view2, "itemView");
            TextView textView = (TextView) view2.findViewById(R.id.inboxLabelTextView);
            Intrinsics.checkExpressionValueIsNotNull(textView, "itemView.inboxLabelTextView");
            String title = inboxMessage.getTitle();
            ColorStateList titleColor = this.colorSchemeProvider.getTitleColor();
            Date sendDate = inboxMessage.getSendDate();
            Intrinsics.checkExpressionValueIsNotNull(sendDate, "model.sendDate");
            textView.setText(getInboxLabelText(title, titleColor, DateExtensionKt.parseToString(sendDate), this.colorSchemeProvider.getDateColor()));
            if (PushwooshInboxStyle.INSTANCE.getDescriptionTextSize() != null) {
                View view3 = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view3, "itemView");
                TextView textView2 = (TextView) view3.findViewById(R.id.inboxDescriptionTextView);
                Float descriptionTextSize = PushwooshInboxStyle.INSTANCE.getDescriptionTextSize();
                if (descriptionTextSize == null) {
                    Intrinsics.throwNpe();
                }
                textView2.setTextSize(2, descriptionTextSize.floatValue());
            }
            View view4 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view4, "itemView");
            ((TextView) view4.findViewById(R.id.inboxDescriptionTextView)).setTextColor(this.colorSchemeProvider.getDescriptionColor());
            View view5 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view5, "itemView");
            ((TintableImageView) view5.findViewById(R.id.inboxStatusImageView)).setColorFilter(this.colorSchemeProvider.getImageColor());
            View view6 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view6, "itemView");
            TextView textView3 = (TextView) view6.findViewById(R.id.inboxDescriptionTextView);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "itemView.inboxDescriptionTextView");
            textView3.setText(inboxMessage.getMessage());
            View view7 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view7, "itemView");
            InboxMessageType type = inboxMessage.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "model.type");
            ((TintableImageView) view7.findViewById(R.id.inboxStatusImageView)).setImageResource(InboxViewHolderKt.getResource(type));
            View view8 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view8, "itemView");
            TextView textView4 = (TextView) view8.findViewById(R.id.inboxLabelTextView);
            Intrinsics.checkExpressionValueIsNotNull(textView4, "itemView.inboxLabelTextView");
            textView4.setSelected(!inboxMessage.isActionPerformed());
            View view9 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view9, "itemView");
            TintableImageView tintableImageView = (TintableImageView) view9.findViewById(R.id.inboxStatusImageView);
            Intrinsics.checkExpressionValueIsNotNull(tintableImageView, "itemView.inboxStatusImageView");
            View view10 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view10, "itemView");
            TextView textView5 = (TextView) view10.findViewById(R.id.inboxLabelTextView);
            Intrinsics.checkExpressionValueIsNotNull(textView5, "itemView.inboxLabelTextView");
            tintableImageView.setSelected(textView5.isSelected());
            View view11 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view11, "itemView");
            TextView textView6 = (TextView) view11.findViewById(R.id.inboxDescriptionTextView);
            Intrinsics.checkExpressionValueIsNotNull(textView6, "itemView.inboxDescriptionTextView");
            View view12 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view12, "itemView");
            TextView textView7 = (TextView) view12.findViewById(R.id.inboxLabelTextView);
            Intrinsics.checkExpressionValueIsNotNull(textView7, "itemView.inboxLabelTextView");
            textView6.setSelected(textView7.isSelected());
            Glide.with(this.itemView).clear(this.itemView);
            if (TextUtils.isEmpty(inboxMessage.getImageUrl())) {
                View view13 = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view13, "itemView");
                requestBuilder = Glide.with(view13.getContext()).load(this.colorSchemeProvider.getDefaultIcon()).listener(new InboxViewHolder$fillView$requestBuilder$1(this));
            } else {
                requestBuilder = Glide.with(this.itemView).load(inboxMessage.getImageUrl());
            }
            Intrinsics.checkExpressionValueIsNotNull(requestBuilder, "if (TextUtils.isEmpty(mo…model.imageUrl)\n        }");
            View view14 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view14, "itemView");
            GlideUtils.applyInto(requestBuilder, (ImageView) view14.findViewById(R.id.inboxImageView));
            String bannerUrl = inboxMessage.getBannerUrl();
            if (bannerUrl == null || TextUtils.isEmpty(bannerUrl)) {
                View view15 = this.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view15, "itemView");
                ImageView imageView = (ImageView) view15.findViewById(R.id.inboxBannerImage);
                Intrinsics.checkExpressionValueIsNotNull(imageView, "itemView.inboxBannerImage");
                imageView.setVisibility(8);
                return;
            }
            View view16 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view16, "itemView");
            ((ImageView) view16.findViewById(R.id.inboxBannerImage)).setOnClickListener(new InboxViewHolder$fillView$1(this, bannerUrl));
            View view17 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view17, "itemView");
            ImageView imageView2 = (ImageView) view17.findViewById(R.id.inboxBannerImage);
            Intrinsics.checkExpressionValueIsNotNull(imageView2, "itemView.inboxBannerImage");
            imageView2.setVisibility(0);
            View view18 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view18, "itemView");
            RequestBuilder<Drawable> load = Glide.with(view18.getContext()).load(bannerUrl);
            View view19 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view19, "itemView");
            Intrinsics.checkExpressionValueIsNotNull(load.into((ImageView) view19.findViewById(R.id.inboxBannerImage)), "Glide.with(itemView.cont…temView.inboxBannerImage)");
        }
    }

    private final CharSequence getInboxLabelText(String str, ColorStateList colorStateList, String str2, ColorStateList colorStateList2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (str != null) {
            String str3 = str;
            if (!TextUtils.isEmpty(str3)) {
                SpannableString spannableString = new SpannableString(str3);
                spannableString.setSpan(getTextAppearanceSpan(getContext(), R.style.TextAppearance_Inbox_InboxTitle, PushwooshInboxStyle.INSTANCE.getTitleTextSize(), colorStateList), 0, str.length(), 33);
                spannableStringBuilder.append((CharSequence) spannableString);
                spannableStringBuilder.append((CharSequence) "  ");
            }
        }
        if (str2 != null) {
            String str4 = str2;
            if (!TextUtils.isEmpty(str4)) {
                SpannableString spannableString2 = new SpannableString(str4);
                spannableString2.setSpan(getTextAppearanceSpan(getContext(), R.style.TextAppearance_Inbox_InboxDate, PushwooshInboxStyle.INSTANCE.getDateTextSize(), colorStateList2), 0, str2.length(), 33);
                spannableStringBuilder.append((CharSequence) spannableString2);
                spannableStringBuilder.append((CharSequence) " ");
            }
        }
        return spannableStringBuilder;
    }

    private final TextAppearanceSpan getTextAppearanceSpan(Context context, int i, Float f, ColorStateList colorStateList) {
        TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(context, i);
        return new TextAppearanceSpan(textAppearanceSpan.getFamily(), textAppearanceSpan.getTextStyle(), f != null ? spToPx(f.floatValue()) : textAppearanceSpan.getTextSize(), colorStateList, colorStateList);
    }

    private final int spToPx(float f) {
        Resources resources = getContext().getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        return (int) TypedValue.applyDimension(2, f, resources.getDisplayMetrics());
    }
}
