package com.google.android.gms.measurement.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import kotlin.jvm.internal.LongCompanionObject;

/* access modifiers changed from: package-private */
public final class zzfh<V> extends FutureTask<V> implements Comparable<zzfh> {
    private final String zzns;
    private final /* synthetic */ zzfc zznt;
    private final long zznw = zzfc.zznj.getAndIncrement();
    final boolean zznx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfh(zzfc zzfc, Callable<V> callable, boolean z, String str) {
        super(callable);
        this.zznt = zzfc;
        Preconditions.checkNotNull(str);
        this.zzns = str;
        this.zznx = z;
        if (this.zznw == LongCompanionObject.MAX_VALUE) {
            zzfc.zzab().zzgk().zzao("Tasks index overflow");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfh(zzfc zzfc, Runnable runnable, boolean z, String str) {
        super(runnable, null);
        this.zznt = zzfc;
        Preconditions.checkNotNull(str);
        this.zzns = str;
        this.zznx = false;
        if (this.zznw == LongCompanionObject.MAX_VALUE) {
            zzfc.zzab().zzgk().zzao("Tasks index overflow");
        }
    }

    /* access modifiers changed from: protected */
    public final void setException(Throwable th) {
        this.zznt.zzab().zzgk().zza(this.zzns, th);
        if (th instanceof zzff) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(@NonNull zzfh zzfh) {
        zzfh zzfh2 = zzfh;
        boolean z = this.zznx;
        if (z != zzfh2.zznx) {
            return z ? -1 : 1;
        }
        long j = this.zznw;
        long j2 = zzfh2.zznw;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        this.zznt.zzab().zzgl().zza("Two tasks share the same index. index", Long.valueOf(this.zznw));
        return 0;
    }
}
