package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class zzgc<K, V> extends LinkedHashMap<K, V> {
    private static final zzgc zzake;
    private boolean zzacz = true;

    private zzgc() {
    }

    private zzgc(Map<K, V> map) {
        super(map);
    }

    public static <K, V> zzgc<K, V> zzvl() {
        return zzake;
    }

    public final void zza(zzgc<K, V> zzgc) {
        zzvn();
        if (!zzgc.isEmpty()) {
            putAll(zzgc);
        }
    }

    @Override // java.util.LinkedHashMap, java.util.AbstractMap, java.util.Map, java.util.HashMap
    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public final void clear() {
        zzvn();
        super.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public final V put(K k, V v) {
        zzvn();
        zzez.checkNotNull(k);
        zzez.checkNotNull(v);
        return (V) super.put(k, v);
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public final void putAll(Map<? extends K, ? extends V> map) {
        zzvn();
        for (Object obj : map.keySet()) {
            zzez.checkNotNull(obj);
            zzez.checkNotNull(map.get(obj));
        }
        super.putAll(map);
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public final V remove(Object obj) {
        zzvn();
        return (V) super.remove(obj);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x005e A[RETURN] */
    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this != map) {
                if (size() == map.size()) {
                    Iterator<Map.Entry<K, V>> it = entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Map.Entry<K, V> next = it.next();
                        if (map.containsKey(next.getKey())) {
                            V value = next.getValue();
                            Object obj2 = map.get(next.getKey());
                            if (!(value instanceof byte[]) || !(obj2 instanceof byte[])) {
                                z2 = value.equals(obj2);
                                continue;
                            } else {
                                z2 = Arrays.equals((byte[]) value, (byte[]) obj2);
                                continue;
                            }
                            if (!z2) {
                                z = false;
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    }
                } else {
                    z = false;
                }
                if (!z) {
                    return true;
                }
            }
            z = true;
            if (!z) {
                return false;
            }
        }
        return false;
    }

    private static int zzs(Object obj) {
        if (obj instanceof byte[]) {
            return zzez.hashCode((byte[]) obj);
        }
        if (!(obj instanceof zzfc)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    public final int hashCode() {
        int i = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            i += zzs(entry.getValue()) ^ zzs(entry.getKey());
        }
        return i;
    }

    public final zzgc<K, V> zzvm() {
        return isEmpty() ? new zzgc<>() : new zzgc<>(this);
    }

    public final void zzry() {
        this.zzacz = false;
    }

    public final boolean isMutable() {
        return this.zzacz;
    }

    private final void zzvn() {
        if (!this.zzacz) {
            throw new UnsupportedOperationException();
        }
    }

    static {
        zzgc zzgc = new zzgc();
        zzake = zzgc;
        zzgc.zzacz = false;
    }
}
