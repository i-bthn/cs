package com.pushwoosh.notification.event;

import com.pushwoosh.internal.event.DataEvent;

public class RegistrationErrorEvent extends DataEvent<String> {
    public RegistrationErrorEvent(String str) {
        super(str);
    }
}
