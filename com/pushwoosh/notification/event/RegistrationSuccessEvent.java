package com.pushwoosh.notification.event;

import com.pushwoosh.internal.event.DataEvent;

public class RegistrationSuccessEvent extends DataEvent<String> {
    public RegistrationSuccessEvent(String str) {
        super(str);
    }
}
