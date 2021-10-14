package com.pushwoosh.internal.event;

public class AppIdChangedEvent implements Event {
    private String a;
    private String b;

    public AppIdChangedEvent(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String getNewAppId() {
        return this.a;
    }

    public String getOldAppId() {
        return this.b;
    }
}
