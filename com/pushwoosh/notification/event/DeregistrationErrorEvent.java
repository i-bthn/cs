package com.pushwoosh.notification.event;

import com.pushwoosh.internal.event.DataEvent;

public class DeregistrationErrorEvent extends DataEvent<String> {
    public DeregistrationErrorEvent(String str) {
        super(str);
    }
}
