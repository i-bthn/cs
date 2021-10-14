package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.MainThread;
import com.google.android.gms.measurement.internal.zzez;
import com.google.android.gms.measurement.internal.zzfa;

public final class AppMeasurementInstallReferrerReceiver extends BroadcastReceiver implements zzfa {
    private zzez zzn;

    @Override // com.google.android.gms.measurement.internal.zzfa
    public final void doStartService(Context context, Intent intent) {
    }

    @MainThread
    public final void onReceive(Context context, Intent intent) {
        if (this.zzn == null) {
            this.zzn = new zzez(this);
        }
        this.zzn.onReceive(context, intent);
    }

    @Override // com.google.android.gms.measurement.internal.zzfa
    public final BroadcastReceiver.PendingResult doGoAsync() {
        return goAsync();
    }
}
