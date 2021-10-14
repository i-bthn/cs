package com.google.android.gms.internal.measurement;

import java.util.Iterator;

final class zzhw implements Iterator<String> {
    private final /* synthetic */ zzhu zzamc;
    private Iterator<String> zzamy = this.zzamc.zzamd.iterator();

    zzhw(zzhu zzhu) {
        this.zzamc = zzhu;
    }

    public final boolean hasNext() {
        return this.zzamy.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zzamy.next();
    }
}
