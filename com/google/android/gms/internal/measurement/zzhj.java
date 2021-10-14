package com.google.android.gms.internal.measurement;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* access modifiers changed from: package-private */
public class zzhj extends AbstractSet<Map.Entry<K, V>> {
    private final /* synthetic */ zzhc zzalq;

    private zzhj(zzhc zzhc) {
        this.zzalq = zzhc;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        return new zzhk(this.zzalq, null);
    }

    public int size() {
        return this.zzalq.size();
    }

    public boolean contains(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.zzalq.get(entry.getKey());
        Object value = entry.getValue();
        if (obj2 != value) {
            return obj2 != null && obj2.equals(value);
        }
        return true;
    }

    public boolean remove(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzalq.remove(entry.getKey());
        return true;
    }

    public void clear() {
        this.zzalq.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public /* synthetic */ boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzalq.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    /* synthetic */ zzhj(zzhc zzhc, zzhb zzhb) {
        this(zzhc);
    }
}
