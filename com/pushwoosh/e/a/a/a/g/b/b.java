package com.pushwoosh.e.a.a.a.g.b;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class b implements a {
    private final Map<String, Object> a;

    public b(String str, Map<String, Map<String, Object>> map) {
        this.a = a(str, map);
    }

    private Map<String, Object> a(String str, Map<String, Map<String, Object>> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        map.put(str, concurrentHashMap);
        return concurrentHashMap;
    }

    @Override // com.pushwoosh.e.a.a.a.g.b.a
    public Object a(String str) {
        return this.a.get(str);
    }

    @Override // com.pushwoosh.e.a.a.a.g.b.a
    public Set<String> a() {
        return Collections.unmodifiableSet(this.a.keySet());
    }

    @Override // com.pushwoosh.e.a.a.a.g.b.a
    public void a(String str, Object obj) {
        this.a.put(str, obj);
    }

    @Override // com.pushwoosh.e.a.a.a.g.b.a
    public boolean contains(String str) {
        return this.a.containsKey(str);
    }

    @Override // com.pushwoosh.e.a.a.a.g.b.a
    public Map<String, Object> getAll() {
        return this.a;
    }

    @Override // com.pushwoosh.e.a.a.a.g.b.a
    public void remove(String str) {
        this.a.remove(str);
    }
}
