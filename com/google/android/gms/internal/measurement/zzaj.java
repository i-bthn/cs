package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzz;

/* access modifiers changed from: package-private */
public final class zzaj extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ String zzbb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaj(zzz zzz, String str) {
        super(zzz);
        this.zzaa = zzz;
        this.zzbb = str;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzz.zzb
    public final void zzf() throws RemoteException {
        this.zzaa.zzar.beginAdUnitExposure(this.zzbb, this.zzbt);
    }
}
