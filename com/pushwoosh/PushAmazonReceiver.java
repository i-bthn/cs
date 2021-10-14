package com.pushwoosh;

import com.amazon.device.messaging.ADMMessageReceiver;

public class PushAmazonReceiver extends ADMMessageReceiver {
    public PushAmazonReceiver() {
        super(PushAmazonIntentService.class);
    }
}
