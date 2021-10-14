package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

/* access modifiers changed from: package-private */
public final class zzdo extends zzdq {
    private final int limit = this.zzadg.size();
    private int position = 0;
    private final /* synthetic */ zzdp zzadg;

    zzdo(zzdp zzdp) {
        this.zzadg = zzdp;
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    @Override // com.google.android.gms.internal.measurement.zzdu
    public final byte nextByte() {
        int i = this.position;
        if (i < this.limit) {
            this.position = i + 1;
            return this.zzadg.zzar(i);
        }
        throw new NoSuchElementException();
    }
}
