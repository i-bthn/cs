package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzz;

/* access modifiers changed from: package-private */
public final class zzaw extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ boolean zzaz;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaw(zzz zzz, boolean z) {
        super(zzz);
        this.zzaa = zzz;
        this.zzaz = z;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzz.zzb
    public final void zzf() throws RemoteException {
        this.zzaa.zzar.setDataCollectionEnabled(this.zzaz);
    }
}
