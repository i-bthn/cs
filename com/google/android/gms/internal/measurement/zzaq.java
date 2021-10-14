package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzz;

/* access modifiers changed from: package-private */
public final class zzaq extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ int zzbd = 5;
    private final /* synthetic */ String zzbe;
    private final /* synthetic */ Object zzbf;
    private final /* synthetic */ Object zzbg;
    private final /* synthetic */ Object zzbh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaq(zzz zzz, boolean z, int i, String str, Object obj, Object obj2, Object obj3) {
        super(false);
        this.zzaa = zzz;
        this.zzbe = str;
        this.zzbf = obj;
        this.zzbg = null;
        this.zzbh = null;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzz.zzb
    public final void zzf() throws RemoteException {
        this.zzaa.zzar.logHealthData(this.zzbd, this.zzbe, ObjectWrapper.wrap(this.zzbf), ObjectWrapper.wrap(this.zzbg), ObjectWrapper.wrap(this.zzbh));
    }
}
