package com.pushwoosh.inbox.ui.presentation.presenter;

import com.pushwoosh.inbox.data.InboxMessage;
import java.util.Comparator;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "i1", "Lcom/pushwoosh/inbox/data/InboxMessage;", "kotlin.jvm.PlatformType", "i2", "compare"}, k = 3, mv = {1, 1, 15})
/* compiled from: InboxPresenter.kt */
final class InboxPresenter$showList$1<T> implements Comparator<T> {
    public static final InboxPresenter$showList$1 INSTANCE = new InboxPresenter$showList$1();

    InboxPresenter$showList$1() {
    }

    public final int compare(InboxMessage inboxMessage, InboxMessage inboxMessage2) {
        return inboxMessage2.compareTo(inboxMessage);
    }
}
