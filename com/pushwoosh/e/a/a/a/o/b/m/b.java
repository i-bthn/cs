package com.pushwoosh.e.a.a.a.o.b.m;

import java.util.HashMap;
import java.util.Map;

public final class b {
    private final Map<String, Class<? extends a>> a = new HashMap();

    public Class<? extends a> a(String str) {
        if (this.a.containsKey(str)) {
            return this.a.get(str);
        }
        throw new UnsupportedClassVersionError(String.format("Cannot find Persistable type for '%s' key. Please, add it through 'registerPersistable' builder method.", str));
    }
}
