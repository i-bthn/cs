package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzz;

/* access modifiers changed from: package-private */
public final class zzaa extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ String zzas;
    private final /* synthetic */ zzl zzat;
    private final /* synthetic */ String zzx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaa(zzz zzz, String str, String str2, zzl zzl) {
        super(zzz);
        this.zzaa = zzz;
        this.zzx = str;
        this.zzas = str2;
        this.zzat = zzl;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzz.zzb
    public final void zzf() throws RemoteException {
        this.zzaa.zzar.getConditionalUserProperties(this.zzx, this.zzas, this.zzat);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzz.zzb
    public final void zzk() {
        this.zzat.zzb((Bundle) null);
    }
}
