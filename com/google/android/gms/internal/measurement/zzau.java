package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.internal.measurement.zzz;
import com.google.android.gms.measurement.internal.zzgn;

/* access modifiers changed from: package-private */
public final class zzau extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ zzgn zzbk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzau(zzz zzz, zzgn zzgn) {
        super(zzz);
        this.zzaa = zzz;
        this.zzbk = zzgn;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzz.zzb
    public final void zzf() throws RemoteException {
        for (int i = 0; i < this.zzaa.zzaf.size(); i++) {
            if (this.zzbk.equals(((Pair) this.zzaa.zzaf.get(i)).first)) {
                Log.w(this.zzaa.zzu, "OnEventListener already registered.");
                return;
            }
        }
        zzz.zzd zzd = new zzz.zzd(this.zzbk);
        this.zzaa.zzaf.add(new Pair(this.zzbk, zzd));
        this.zzaa.zzar.registerOnMeasurementEventListener(zzd);
    }
}
