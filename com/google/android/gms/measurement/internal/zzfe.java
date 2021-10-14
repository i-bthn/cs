package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;

/* access modifiers changed from: package-private */
public final class zzfe implements Thread.UncaughtExceptionHandler {
    private final String zzns;
    private final /* synthetic */ zzfc zznt;

    public zzfe(zzfc zzfc, String str) {
        this.zznt = zzfc;
        Preconditions.checkNotNull(str);
        this.zzns = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zznt.zzab().zzgk().zza(this.zzns, th);
    }
}
