package com.pushwoosh.inbox.f.b;

import androidx.annotation.NonNull;
import com.pushwoosh.inbox.internal.data.InboxMessageStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class a {
    private final Collection<String> a;
    private final Collection<String> b;
    private final Collection<String> c;
    private final Map<String, InboxMessageStatus> d;

    private a() {
        this(null, null, null, null);
    }

    private a(Collection<String> collection, Collection<String> collection2, Map<String, InboxMessageStatus> map, Collection<String> collection3) {
        this.a = collection == null ? Collections.emptyList() : collection;
        this.d = map == null ? Collections.emptyMap() : map;
        this.b = collection2 == null ? Collections.emptyList() : collection2;
        this.c = collection3 == null ? Collections.emptyList() : collection3;
    }

    public static a e() {
        return new a(new ArrayList(), new ArrayList(), new HashMap(), new ArrayList());
    }

    public static a f() {
        return new a();
    }

    @NonNull
    public Collection<String> a() {
        return this.c;
    }

    @NonNull
    public Map<String, InboxMessageStatus> b() {
        return this.d;
    }

    @NonNull
    public Collection<String> c() {
        return this.a;
    }

    @NonNull
    public Collection<String> d() {
        return this.b;
    }
}
