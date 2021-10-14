package com.pushwoosh.inbox.ui.utils;

import com.pushwoosh.inbox.ui.PushwooshInboxStyle;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"parseToString", "", "Ljava/util/Date;", "pushwoosh-inbox-ui_release"}, k = 2, mv = {1, 1, 15})
/* compiled from: DateExtension.kt */
public final class DateExtensionKt {
    @NotNull
    public static final String parseToString(@NotNull Date date) {
        Intrinsics.checkParameterIsNotNull(date, "$this$parseToString");
        return (String) PushwooshInboxStyle.INSTANCE.getDateFormatter().transform(date);
    }
}
