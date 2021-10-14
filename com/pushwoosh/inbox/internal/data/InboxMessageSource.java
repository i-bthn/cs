package com.pushwoosh.inbox.internal.data;

import com.pushwoosh.internal.utils.PWLog;

public enum InboxMessageSource {
    PUSH(0),
    SERVICE(1);
    
    private final int code;

    private InboxMessageSource(int i) {
        this.code = i;
    }

    public static InboxMessageSource getByCode(int i) {
        InboxMessageSource[] values = values();
        for (InboxMessageSource inboxMessageSource : values) {
            if (inboxMessageSource.code == i) {
                return inboxMessageSource;
            }
        }
        PWLog.error("Unknown code of source: " + i);
        return null;
    }

    public int compare(InboxMessageSource inboxMessageSource) {
        return Integer.valueOf(inboxMessageSource.code).compareTo(Integer.valueOf(this.code));
    }

    public int getCode() {
        return this.code;
    }
}
