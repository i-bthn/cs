package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* access modifiers changed from: package-private */
public final class zzjk implements Callable<String> {
    private final /* synthetic */ zzn zzpg;
    private final /* synthetic */ zzjg zztl;

    zzjk(zzjg zzjg, zzn zzn) {
        this.zztl = zzjg;
        this.zzpg = zzn;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        zzf zzf = this.zztl.zzg(this.zzpg);
        if (zzf != null) {
            return zzf.getAppInstanceId();
        }
        this.zztl.zzab().zzgn().zzao("App info was null when attempting to get app instance id");
        return null;
    }
}
