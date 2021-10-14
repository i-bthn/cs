package com.pushwoosh.notification;

import androidx.annotation.ColorInt;

public class PushwooshSummaryNotificationFactory extends SummaryNotificationFactory {
    @Override // com.pushwoosh.notification.SummaryNotificationFactory
    @ColorInt
    public int summaryNotificationColor() {
        return -1;
    }

    @Override // com.pushwoosh.notification.SummaryNotificationFactory
    public int summaryNotificationIconResId() {
        return -1;
    }

    @Override // com.pushwoosh.notification.SummaryNotificationFactory
    public String summaryNotificationMessage(int i) {
        return "";
    }
}
