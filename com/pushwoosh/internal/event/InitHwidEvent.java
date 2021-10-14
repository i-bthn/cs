package com.pushwoosh.internal.event;

public class InitHwidEvent implements Event {
    private String a;

    public InitHwidEvent(String str) {
        this.a = str;
    }

    public String getHwid() {
        return this.a;
    }
}
