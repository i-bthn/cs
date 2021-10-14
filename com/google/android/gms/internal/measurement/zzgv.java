package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzey;

final class zzgv implements zzgg {
    private final int flags;
    private final String info;
    private final Object[] zzakk;
    private final zzgi zzakn;

    zzgv(zzgi zzgi, String str, Object[] objArr) {
        this.zzakn = zzgi;
        this.info = str;
        this.zzakk = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.flags = charAt;
            return;
        }
        int i = charAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 >= 55296) {
                i |= (charAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            } else {
                this.flags = i | (charAt2 << i2);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzvz() {
        return this.info;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzwa() {
        return this.zzakk;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg
    public final zzgi zzvt() {
        return this.zzakn;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg
    public final int zzvr() {
        return (this.flags & 1) == 1 ? zzey.zzd.zzail : zzey.zzd.zzaim;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg
    public final boolean zzvs() {
        return (this.flags & 2) == 2;
    }
}
