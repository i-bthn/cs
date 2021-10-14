package com.pushwoosh.e.a.a.a;

import android.content.SharedPreferences;
import com.pushwoosh.e.a.a.a.i.b;
import com.pushwoosh.e.a.a.a.i.e;
import com.pushwoosh.e.a.a.a.p.c;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

final class a implements e {
    private final com.pushwoosh.e.a.a.a.k.c.a a;
    private final b b;
    private final com.pushwoosh.e.a.a.a.g.a.a c;
    private final com.pushwoosh.e.a.a.a.g.b.a d;
    private final c e;
    private final com.pushwoosh.e.a.a.a.o.a f;
    private final Lock g;
    private final Lock h;
    private final com.pushwoosh.e.a.a.a.l.b i;

    a(com.pushwoosh.e.a.a.a.k.c.a aVar, b bVar, com.pushwoosh.e.a.a.a.g.a.a aVar2, com.pushwoosh.e.a.a.a.g.b.a aVar3, c cVar, com.pushwoosh.e.a.a.a.o.a aVar4, com.pushwoosh.e.a.a.a.m.a aVar5, com.pushwoosh.e.a.a.a.l.b bVar2) {
        this.a = aVar;
        this.b = bVar;
        this.c = aVar2;
        this.d = aVar3;
        this.e = cVar;
        this.f = aVar4;
        this.g = aVar5.a();
        this.h = aVar5.c();
        this.i = bVar2;
    }

    private f a() {
        this.g.lock();
        try {
            return new c(this.a, this.b, this.e, this.f, this.d, this.c, this.h);
        } finally {
            this.g.unlock();
        }
    }

    public boolean contains(String str) {
        return this.i.contains(str);
    }

    @Override // com.pushwoosh.e.a.a.a.e
    public f edit() {
        return a();
    }

    @Override // android.content.SharedPreferences, com.pushwoosh.e.a.a.a.e
    public Map<String, Object> getAll() {
        return this.i.getAll();
    }

    public boolean getBoolean(String str, boolean z) {
        return ((Boolean) this.i.a(str, Boolean.valueOf(z))).booleanValue();
    }

    public float getFloat(String str, float f2) {
        return ((Float) this.i.a(str, Float.valueOf(f2))).floatValue();
    }

    public int getInt(String str, int i2) {
        return ((Integer) this.i.a(str, Integer.valueOf(i2))).intValue();
    }

    public long getLong(String str, long j) {
        return ((Long) this.i.a(str, Long.valueOf(j))).longValue();
    }

    public String getString(String str, String str2) {
        return (String) this.i.a(str, str2);
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        return (Set) this.i.a(str, set);
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.h.lock();
        try {
            this.b.registerOnSharedPreferenceChangeListener(new e(this, onSharedPreferenceChangeListener));
        } finally {
            this.h.unlock();
        }
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.h.lock();
        try {
            this.b.unregisterOnSharedPreferenceChangeListener(new e(this, onSharedPreferenceChangeListener));
        } finally {
            this.h.unlock();
        }
    }
}
