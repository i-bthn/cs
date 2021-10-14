package com.pushwoosh.e.a.a.a.m;

import com.pushwoosh.e.a.a.a.k.b.b;
import java.io.File;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class c implements a {
    private final File a;
    private final ReadWriteLock b;
    private final Lock c;

    public c(String str, b bVar, Map<String, ReadWriteLock> map, Map<String, Lock> map2) {
        this.a = bVar.a();
        this.b = a(str, map);
        this.c = b(str, map2);
    }

    private ReadWriteLock a(String str, Map<String, ReadWriteLock> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
        map.put(str, reentrantReadWriteLock);
        return reentrantReadWriteLock;
    }

    private Lock b(String str, Map<String, Lock> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        File file = this.a;
        b bVar = new b(new File(file, str + ".lock"));
        map.put(str, bVar);
        return bVar;
    }

    @Override // com.pushwoosh.e.a.a.a.m.a
    public Lock a() {
        return this.b.readLock();
    }

    @Override // com.pushwoosh.e.a.a.a.m.a
    public Lock b() {
        return this.c;
    }

    @Override // com.pushwoosh.e.a.a.a.m.a
    public Lock c() {
        return this.b.writeLock();
    }
}
