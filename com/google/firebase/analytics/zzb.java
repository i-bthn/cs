package com.google.firebase.analytics;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

final class zzb implements Callable<String> {
    private final /* synthetic */ FirebaseAnalytics zzaca;

    zzb(FirebaseAnalytics firebaseAnalytics) {
        this.zzaca = firebaseAnalytics;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        String str;
        String zza = FirebaseAnalytics.zza(this.zzaca);
        if (zza != null) {
            return zza;
        }
        if (FirebaseAnalytics.zzb(this.zzaca)) {
            str = FirebaseAnalytics.zzc(this.zzaca).getAppInstanceId();
        } else {
            str = FirebaseAnalytics.zzd(this.zzaca).zzq().zzy(120000);
        }
        if (str != null) {
            FirebaseAnalytics.zza(this.zzaca, str);
            return str;
        }
        throw new TimeoutException();
    }
}
