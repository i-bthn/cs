package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzz;

/* access modifiers changed from: package-private */
public final class zzba extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ Bundle zzbj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzba(zzz zzz, Bundle bundle) {
        super(zzz);
        this.zzaa = zzz;
        this.zzbj = bundle;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzz.zzb
    public final void zzf() throws RemoteException {
        this.zzaa.zzar.setConditionalUserProperty(this.zzbj, this.timestamp);
    }
}
