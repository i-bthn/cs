package com.pushwoosh.notification;

public enum SoundType {
    DEFAULT_MODE(0),
    NO_SOUND(1),
    ALWAYS(2);
    
    private final int a;

    private SoundType(int i) {
        this.a = i;
    }

    public static SoundType fromInt(int i) {
        return i != 0 ? i != 1 ? i != 2 ? DEFAULT_MODE : ALWAYS : NO_SOUND : DEFAULT_MODE;
    }

    public int getValue() {
        return this.a;
    }
}
