package com.pushwoosh.inapp.j.l;

public enum a {
    FULLSCREEN("fullscreen"),
    DIALOG("centerbox"),
    TOP("topbanner"),
    BOTTOM("bottombanner");
    
    private String a;

    private a(String str) {
        this.a = str;
    }

    public static a a(String str) {
        a[] values = values();
        for (a aVar : values) {
            if (aVar.a.equals(str)) {
                return aVar;
            }
        }
        return DIALOG;
    }

    public String b() {
        return this.a;
    }
}
