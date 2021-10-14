package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzhd extends zzhj {
    private final /* synthetic */ zzhc zzalq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzhd(zzhc zzhc) {
        super(zzhc, null);
        this.zzalq = zzhc;
    }

    @Override // java.util.AbstractCollection, com.google.android.gms.internal.measurement.zzhj, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzhe(this.zzalq, null);
    }

    /* synthetic */ zzhd(zzhc zzhc, zzhb zzhb) {
        this(zzhc);
    }
}
