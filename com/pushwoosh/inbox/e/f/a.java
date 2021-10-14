package com.pushwoosh.inbox.e.f;

import androidx.annotation.NonNull;
import java.util.Collection;
import java.util.Collections;

public class a {
    public static final a d = new a(Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    private Collection<String> a;
    private Collection<String> b;
    private Collection<String> c;

    public a(Collection<String> collection, Collection<String> collection2, Collection<String> collection3) {
        this.a = collection == null ? Collections.emptyList() : collection;
        this.b = collection2 == null ? Collections.emptyList() : collection2;
        this.c = collection3 == null ? Collections.emptyList() : collection3;
    }

    @NonNull
    public Collection<String> a() {
        return this.a;
    }

    @NonNull
    public Collection<String> b() {
        return this.c;
    }

    @NonNull
    public Collection<String> c() {
        return this.b;
    }

    public boolean d() {
        return this.a.isEmpty() && this.b.isEmpty() && this.c.isEmpty();
    }
}
