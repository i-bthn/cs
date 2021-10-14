package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
public final class zzij implements Runnable {
    private final /* synthetic */ String zzas;
    private final /* synthetic */ String zzdn;
    private final /* synthetic */ zzn zzpg;
    private final /* synthetic */ zzhv zzrd;
    private final /* synthetic */ AtomicReference zzrl;
    private final /* synthetic */ String zzx;

    zzij(zzhv zzhv, AtomicReference atomicReference, String str, String str2, String str3, zzn zzn) {
        this.zzrd = zzhv;
        this.zzrl = atomicReference;
        this.zzdn = str;
        this.zzx = str2;
        this.zzas = str3;
        this.zzpg = zzn;
    }

    public final void run() {
        synchronized (this.zzrl) {
            try {
                zzdx zzdx = this.zzrd.zzrf;
                if (zzdx == null) {
                    this.zzrd.zzab().zzgk().zza("Failed to get conditional properties", zzef.zzam(this.zzdn), this.zzx, this.zzas);
                    this.zzrl.set(Collections.emptyList());
                    return;
                }
                if (TextUtils.isEmpty(this.zzdn)) {
                    this.zzrl.set(zzdx.zza(this.zzx, this.zzas, this.zzpg));
                } else {
                    this.zzrl.set(zzdx.zzc(this.zzdn, this.zzx, this.zzas));
                }
                this.zzrd.zzir();
                this.zzrl.notify();
            } catch (RemoteException e) {
                this.zzrd.zzab().zzgk().zza("Failed to get conditional properties", zzef.zzam(this.zzdn), this.zzx, e);
                this.zzrl.set(Collections.emptyList());
            } finally {
                this.zzrl.notify();
            }
        }
    }
}
