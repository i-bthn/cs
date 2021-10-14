package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzx;

@VisibleForTesting
public final class zzgm {
    final Context zzob;
    String zzoc;
    String zzod;
    Boolean zzow;
    zzx zzpr;
    long zzs;
    boolean zzt = true;
    String zzv;

    @VisibleForTesting
    public zzgm(Context context, zzx zzx) {
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zzob = applicationContext;
        if (zzx != null) {
            this.zzpr = zzx;
            this.zzv = zzx.zzv;
            this.zzoc = zzx.origin;
            this.zzod = zzx.zzu;
            this.zzt = zzx.zzt;
            this.zzs = zzx.zzs;
            if (zzx.zzw != null) {
                this.zzow = Boolean.valueOf(zzx.zzw.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
