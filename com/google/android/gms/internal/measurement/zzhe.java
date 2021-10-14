package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzhe implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zzhc zzalq;
    private Iterator<Map.Entry<K, V>> zzalr;

    private zzhe(zzhc zzhc) {
        this.zzalq = zzhc;
        this.pos = this.zzalq.zzall.size();
    }

    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zzalq.zzall.size()) || zzwm().hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Map.Entry<K, V>> zzwm() {
        if (this.zzalr == null) {
            this.zzalr = this.zzalq.zzalo.entrySet().iterator();
        }
        return this.zzalr;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        if (zzwm().hasNext()) {
            return (Map.Entry) zzwm().next();
        }
        List list = this.zzalq.zzall;
        int i = this.pos - 1;
        this.pos = i;
        return (Map.Entry) list.get(i);
    }

    /* synthetic */ zzhe(zzhc zzhc, zzhb zzhb) {
        this(zzhc);
    }
}
