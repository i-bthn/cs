package com.pushwoosh.notification.event;

import com.pushwoosh.internal.event.DataEvent;

public class DeregistrationSuccessEvent extends DataEvent<String> {
    public DeregistrationSuccessEvent(String str) {
        super(str);
    }
}
