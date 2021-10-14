package com.pushwoosh.inapp.event;

import com.pushwoosh.inapp.j.l.b;
import com.pushwoosh.internal.event.Event;

public class a implements Event {
    private final b a;
    private final EnumC0009a b;

    /* renamed from: com.pushwoosh.inapp.event.a$a  reason: collision with other inner class name */
    public enum EnumC0009a {
        DOWNLOADING_ZIP,
        DOWNLOADED_ZIP,
        DEPLOYED,
        DEPLOY_FAILED
    }

    public a(EnumC0009a aVar, b bVar) {
        this.b = aVar;
        this.a = bVar;
    }

    public String a() {
        return this.a.c();
    }

    public EnumC0009a b() {
        return this.b;
    }
}
