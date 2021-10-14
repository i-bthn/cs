package com.pushwoosh.badge;

class a {
    private static com.pushwoosh.badge.c.a a;
    private static com.pushwoosh.badge.d.a b;

    static com.pushwoosh.badge.c.a a() {
        return a;
    }

    static com.pushwoosh.badge.d.a b() {
        return b;
    }

    static void c() {
        a = new com.pushwoosh.badge.c.a();
        b = new com.pushwoosh.badge.d.a(a);
    }
}
