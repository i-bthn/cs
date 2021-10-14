package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzfi implements zzhk {
    private final zzfj zzny;
    private final zzp zznz;

    zzfi(zzfj zzfj, zzp zzp) {
        this.zzny = zzfj;
        this.zznz = zzp;
    }

    @Override // com.google.android.gms.measurement.internal.zzhk
    public final void zza(String str, int i, Throwable th, byte[] bArr, Map map) {
        this.zzny.zza(this.zznz, str, i, th, bArr, map);
    }
}
