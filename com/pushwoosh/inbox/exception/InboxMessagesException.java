package com.pushwoosh.inbox.exception;

import com.pushwoosh.exception.PushwooshException;

public class InboxMessagesException extends PushwooshException {
    public InboxMessagesException(String str) {
        super(str);
    }

    public InboxMessagesException(String str, Throwable th) {
        super(str, th);
    }

    public InboxMessagesException(Throwable th) {
        super(th);
    }
}
