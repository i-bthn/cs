package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzz;

/* access modifiers changed from: package-private */
public final class zzad extends zzz.zzb {
    private final /* synthetic */ String val$id;
    private final /* synthetic */ zzz zzaa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzad(zzz zzz, String str) {
        super(zzz);
        this.zzaa = zzz;
        this.val$id = str;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzz.zzb
    public final void zzf() throws RemoteException {
        this.zzaa.zzar.setUserId(this.val$id, this.timestamp);
    }
}
