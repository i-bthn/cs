package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzcc extends ContentObserver {
    private final /* synthetic */ zzca zzaaq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcc(zzca zzca, Handler handler) {
        super(null);
        this.zzaaq = zzca;
    }

    public final void onChange(boolean z) {
        this.zzaaq.zzrf();
    }
}
