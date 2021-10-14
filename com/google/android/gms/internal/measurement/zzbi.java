package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzz;

final class zzbi extends zzz.zzb {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zzz.zzc zzbw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbi(zzz.zzc zzc, Activity activity) {
        super(zzz.this);
        this.zzbw = zzc;
        this.val$activity = activity;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzz.zzb
    public final void zzf() throws RemoteException {
        zzz.this.zzar.onActivityDestroyed(ObjectWrapper.wrap(this.val$activity), this.zzbt);
    }
}
