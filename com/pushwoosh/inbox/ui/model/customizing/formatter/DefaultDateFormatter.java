package com.pushwoosh.inbox.ui.model.customizing.formatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/pushwoosh/inbox/ui/model/customizing/formatter/DefaultDateFormatter;", "Lcom/pushwoosh/inbox/ui/model/customizing/formatter/InboxDateFormatter;", "dateFormat", "Ljava/text/DateFormat;", "(Ljava/text/DateFormat;)V", "transform", "", "input", "Ljava/util/Date;", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: InboxDateFormatter.kt */
public final class DefaultDateFormatter implements InboxDateFormatter {
    private final DateFormat dateFormat;

    public DefaultDateFormatter() {
        this(null, 1, null);
    }

    public DefaultDateFormatter(@NotNull DateFormat dateFormat2) {
        Intrinsics.checkParameterIsNotNull(dateFormat2, "dateFormat");
        this.dateFormat = dateFormat2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DefaultDateFormatter(DateFormat dateFormat2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new SimpleDateFormat(InboxDateFormatterKt.DEFAULT_DATE_FORMAT, Locale.getDefault()) : dateFormat2);
    }

    @NotNull
    public String transform(@NotNull Date date) {
        Intrinsics.checkParameterIsNotNull(date, "input");
        String format = this.dateFormat.format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "dateFormat.format(input)");
        return format;
    }
}
