package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzz;

/* access modifiers changed from: package-private */
public final class zzat extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ zzl zzat;
    private final /* synthetic */ Bundle zzbj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzat(zzz zzz, Bundle bundle, zzl zzl) {
        super(zzz);
        this.zzaa = zzz;
        this.zzbj = bundle;
        this.zzat = zzl;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzz.zzb
    public final void zzf() throws RemoteException {
        this.zzaa.zzar.performAction(this.zzbj, this.zzat, this.timestamp);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzz.zzb
    public final void zzk() {
        this.zzat.zzb((Bundle) null);
    }
}
