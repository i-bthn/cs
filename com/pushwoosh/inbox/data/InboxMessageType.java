package com.pushwoosh.inbox.data;

import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;

public enum InboxMessageType {
    PLAIN(0),
    RICH_MEDIA(1),
    URL(2),
    DEEP_LINK(3),
    REMOTE_URL(4);
    
    private int code;

    private InboxMessageType(int i) {
        this.code = i;
    }

    @Nullable
    public static InboxMessageType getByCode(int i) {
        InboxMessageType[] values = values();
        for (InboxMessageType inboxMessageType : values) {
            if (inboxMessageType.code == i) {
                return inboxMessageType;
            }
        }
        PWLog.error("Incorrect type of InboxMessageType: " + i);
        return null;
    }

    public int getCode() {
        return this.code;
    }
}
