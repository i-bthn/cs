package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* access modifiers changed from: package-private */
public class zzgf implements zzgh {
    protected final zzfj zzj;

    zzgf(zzfj zzfj) {
        Preconditions.checkNotNull(zzfj);
        this.zzj = zzfj;
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public zzr zzae() {
        return this.zzj.zzae();
    }

    public zzs zzad() {
        return this.zzj.zzad();
    }

    public zzeo zzac() {
        return this.zzj.zzac();
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public zzef zzab() {
        return this.zzj.zzab();
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public zzfc zzaa() {
        return this.zzj.zzaa();
    }

    public zzjs zzz() {
        return this.zzj.zzz();
    }

    public zzed zzy() {
        return this.zzj.zzy();
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public Context getContext() {
        return this.zzj.getContext();
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public Clock zzx() {
        return this.zzj.zzx();
    }

    public zzac zzw() {
        return this.zzj.zzw();
    }

    public void zzo() {
        this.zzj.zzaa().zzo();
    }

    public void zzn() {
        this.zzj.zzaa().zzn();
    }

    public void zzm() {
        this.zzj.zzm();
    }

    public void zzl() {
        this.zzj.zzl();
    }
}
