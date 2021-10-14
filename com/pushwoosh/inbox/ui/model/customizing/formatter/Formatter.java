package com.pushwoosh.inbox.ui.model.customizing.formatter;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0000*\u0006\b\u0001\u0010\u0002 \u00012\u00020\u0003J\u0015\u0010\u0004\u001a\u00028\u00012\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/pushwoosh/inbox/ui/model/customizing/formatter/Formatter;", "Input", "Output", "", "transform", "input", "(Ljava/lang/Object;)Ljava/lang/Object;", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: InboxDateFormatter.kt */
public interface Formatter<Input, Output> {
    Output transform(Input input);
}
