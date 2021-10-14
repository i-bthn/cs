package com.google.android.gms.measurement.internal;

import java.util.Iterator;

/* access modifiers changed from: package-private */
public final class zzag implements Iterator<String> {
    private Iterator<String> zzfr = this.zzfs.zzft.keySet().iterator();
    private final /* synthetic */ zzah zzfs;

    zzag(zzah zzah) {
        this.zzfs = zzah;
    }

    public final boolean hasNext() {
        return this.zzfr.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zzfr.next();
    }
}
