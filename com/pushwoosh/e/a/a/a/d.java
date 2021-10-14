package com.pushwoosh.e.a.a.a;

import android.content.SharedPreferences;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

final class d {
    private static final Map<String, ReadWriteLock> a = new ConcurrentHashMap();
    private static final Map<String, Lock> b = new ConcurrentHashMap();
    private static final Map<String, Map<String, Object>> c = new ConcurrentHashMap();
    private static final Map<String, Set<String>> d = new ConcurrentHashMap();
    private static final Map<String, List<SharedPreferences.OnSharedPreferenceChangeListener>> e = new ConcurrentHashMap();
    private static final Map<String, ExecutorService> f = new ConcurrentHashMap();

    d() {
    }

    /* access modifiers changed from: package-private */
    public Map<String, List<SharedPreferences.OnSharedPreferenceChangeListener>> a() {
        return e;
    }

    /* access modifiers changed from: package-private */
    public Map<String, Set<String>> b() {
        return d;
    }

    /* access modifiers changed from: package-private */
    public Map<String, Map<String, Object>> c() {
        return c;
    }

    /* access modifiers changed from: package-private */
    public Map<String, ExecutorService> d() {
        return f;
    }

    /* access modifiers changed from: package-private */
    public Map<String, ReadWriteLock> e() {
        return a;
    }

    /* access modifiers changed from: package-private */
    public Map<String, Lock> f() {
        return b;
    }
}
