package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

final class zzhk implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zzhc zzalq;
    private Iterator<Map.Entry<K, V>> zzalr;
    private boolean zzalv;

    private zzhk(zzhc zzhc) {
        this.zzalq = zzhc;
        this.pos = -1;
    }

    public final boolean hasNext() {
        if (this.pos + 1 < this.zzalq.zzall.size() || (!this.zzalq.zzalm.isEmpty() && zzwm().hasNext())) {
            return true;
        }
        return false;
    }

    public final void remove() {
        if (this.zzalv) {
            this.zzalv = false;
            this.zzalq.zzwk();
            if (this.pos < this.zzalq.zzall.size()) {
                zzhc zzhc = this.zzalq;
                int i = this.pos;
                this.pos = i - 1;
                Object unused = zzhc.zzcg(i);
                return;
            }
            zzwm().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }

    private final Iterator<Map.Entry<K, V>> zzwm() {
        if (this.zzalr == null) {
            this.zzalr = this.zzalq.zzalm.entrySet().iterator();
        }
        return this.zzalr;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        this.zzalv = true;
        int i = this.pos + 1;
        this.pos = i;
        if (i < this.zzalq.zzall.size()) {
            return (Map.Entry) this.zzalq.zzall.get(this.pos);
        }
        return (Map.Entry) zzwm().next();
    }

    /* synthetic */ zzhk(zzhc zzhc, zzhb zzhb) {
        this(zzhc);
    }
}
