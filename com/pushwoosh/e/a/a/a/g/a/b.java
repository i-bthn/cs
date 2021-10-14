package com.pushwoosh.e.a.a.a.g.a;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public final class b implements a {
    private final Set<String> a;

    public b(String str, Map<String, Set<String>> map) {
        this.a = a(str, map);
    }

    private Set<String> a(String str, Map<String, Set<String>> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        ConcurrentSkipListSet concurrentSkipListSet = new ConcurrentSkipListSet();
        map.put(str, concurrentSkipListSet);
        return concurrentSkipListSet;
    }

    @Override // com.pushwoosh.e.a.a.a.g.a.a
    public Set<String> a() {
        return Collections.unmodifiableSet(this.a);
    }

    @Override // com.pushwoosh.e.a.a.a.g.a.a
    public void a(String str) {
        this.a.add(str);
    }

    @Override // com.pushwoosh.e.a.a.a.g.a.a
    public void remove(String str) {
        this.a.remove(str);
    }
}
