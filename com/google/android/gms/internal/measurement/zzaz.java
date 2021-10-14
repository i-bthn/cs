package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.internal.measurement.zzz;
import com.google.android.gms.measurement.internal.zzgn;

/* access modifiers changed from: package-private */
public final class zzaz extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ zzgn zzbk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaz(zzz zzz, zzgn zzgn) {
        super(zzz);
        this.zzaa = zzz;
        this.zzbk = zzgn;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzz.zzb
    public final void zzf() throws RemoteException {
        Pair pair;
        int i = 0;
        while (true) {
            if (i >= this.zzaa.zzaf.size()) {
                pair = null;
                break;
            } else if (this.zzbk.equals(((Pair) this.zzaa.zzaf.get(i)).first)) {
                pair = (Pair) this.zzaa.zzaf.get(i);
                break;
            } else {
                i++;
            }
        }
        if (pair == null) {
            Log.w(this.zzaa.zzu, "OnEventListener had not been registered.");
            return;
        }
        this.zzaa.zzar.unregisterOnMeasurementEventListener((zzq) pair.second);
        this.zzaa.zzaf.remove(pair);
    }
}
