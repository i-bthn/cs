package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzz;
import java.util.ArrayList;

/* access modifiers changed from: package-private */
public final class zzy extends zzz.zzb {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ String zzx;
    private final /* synthetic */ String zzy;
    private final /* synthetic */ Bundle zzz;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzy(zzz zzz2, String str, String str2, Context context, Bundle bundle) {
        super(zzz2);
        this.zzaa = zzz2;
        this.zzx = str;
        this.zzy = str2;
        this.val$context = context;
        this.zzz = bundle;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0054 A[Catch:{ RemoteException -> 0x00a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0060 A[Catch:{ RemoteException -> 0x00a1 }] */
    @Override // com.google.android.gms.internal.measurement.zzz.zzb
    public final void zzf() {
        String str;
        String str2;
        String str3;
        boolean z;
        boolean z2;
        int i;
        try {
            this.zzaa.zzaf = new ArrayList();
            zzz zzz2 = this.zzaa;
            if (zzz.zza(this.zzx, this.zzy)) {
                String str4 = this.zzy;
                str2 = this.zzx;
                str = str4;
                str3 = this.zzaa.zzu;
            } else {
                str3 = null;
                str2 = null;
                str = null;
            }
            zzz.zze(this.val$context);
            if (!zzz.zzai.booleanValue()) {
                if (str2 == null) {
                    z = false;
                    this.zzaa.zzar = this.zzaa.zza(this.val$context, z);
                    if (this.zzaa.zzar != null) {
                        Log.w(this.zzaa.zzu, "Failed to connect to measurement client.");
                        return;
                    }
                    int i2 = zzz.zzd(this.val$context);
                    int i3 = zzz.zzc(this.val$context);
                    if (z) {
                        i = Math.max(i2, i3);
                        z2 = i3 < i2;
                    } else {
                        i = i2 > 0 ? i2 : i3;
                        z2 = i2 > 0;
                    }
                    this.zzaa.zzar.initialize(ObjectWrapper.wrap(this.val$context), new zzx(16250, (long) i, z2, str3, str2, str, this.zzz), this.timestamp);
                    return;
                }
            }
            z = true;
            this.zzaa.zzar = this.zzaa.zza(this.val$context, z);
            if (this.zzaa.zzar != null) {
            }
        } catch (RemoteException e) {
            this.zzaa.zza((zzz) e, (Exception) true, false);
        }
    }
}
