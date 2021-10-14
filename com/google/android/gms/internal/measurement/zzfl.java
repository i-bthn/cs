package com.google.android.gms.internal.measurement;

import java.util.Map;

final class zzfl<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzfj> zzajl;

    private zzfl(Map.Entry<K, zzfj> entry) {
        this.zzajl = entry;
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        return this.zzajl.getKey();
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.zzajl.getValue() == null) {
            return null;
        }
        return zzfj.zzvc();
    }

    public final zzfj zzve() {
        return this.zzajl.getValue();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj instanceof zzgi) {
            return this.zzajl.getValue().zzi((zzgi) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
