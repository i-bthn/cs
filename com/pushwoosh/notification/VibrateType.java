package com.pushwoosh.notification;

public enum VibrateType {
    DEFAULT_MODE(0),
    NO_VIBRATE(1),
    ALWAYS(2);
    
    private final int a;

    private VibrateType(int i) {
        this.a = i;
    }

    public static VibrateType fromInt(int i) {
        return i != 0 ? i != 1 ? i != 2 ? DEFAULT_MODE : ALWAYS : NO_VIBRATE : DEFAULT_MODE;
    }

    public int getValue() {
        return this.a;
    }
}
