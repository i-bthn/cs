package com.pushwoosh.inbox.ui.presentation.view.style;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/style/ColorSchemeProviderFactory;", "", "()V", "generateColorScheme", "Lcom/pushwoosh/inbox/ui/presentation/view/style/ColorSchemeProvider;", "context", "Landroid/content/Context;", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: ColorSchemeProviderFactory.kt */
public final class ColorSchemeProviderFactory {
    public static final ColorSchemeProviderFactory INSTANCE = new ColorSchemeProviderFactory();

    private ColorSchemeProviderFactory() {
    }

    @NotNull
    public final ColorSchemeProvider generateColorScheme(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return new ContextColorSchemeProvider(context);
    }
}
