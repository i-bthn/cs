package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzji implements zzel {
    private final /* synthetic */ String zztk;
    private final /* synthetic */ zzjg zztl;

    zzji(zzjg zzjg, String str) {
        this.zztl = zzjg;
        this.zztk = str;
    }

    @Override // com.google.android.gms.measurement.internal.zzel
    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.zztl.zza(i, th, bArr, this.zztk);
    }
}
