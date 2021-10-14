package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzf;

final class zzew implements Runnable {
    private final /* synthetic */ zzf zzmq;
    private final /* synthetic */ ServiceConnection zzmr;
    private final /* synthetic */ zzex zzms;

    zzew(zzex zzex, zzf zzf, ServiceConnection serviceConnection) {
        this.zzms = zzex;
        this.zzmq = zzf;
        this.zzmr = serviceConnection;
    }

    public final void run() {
        zzeu zzeu = this.zzms.zzmt;
        String str = this.zzms.packageName;
        zzf zzf = this.zzmq;
        ServiceConnection serviceConnection = this.zzmr;
        Bundle zza = zzeu.zza(str, zzf);
        zzeu.zzj.zzaa().zzo();
        if (zza != null) {
            long j = zza.getLong("install_begin_timestamp_seconds", 0) * 1000;
            if (j == 0) {
                zzeu.zzj.zzab().zzgk().zzao("Service response is missing Install Referrer install timestamp");
            } else {
                String string = zza.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    zzeu.zzj.zzab().zzgk().zzao("No referrer defined in install referrer response");
                } else {
                    zzeu.zzj.zzab().zzgs().zza("InstallReferrer API result", string);
                    zzjs zzz = zzeu.zzj.zzz();
                    String valueOf = String.valueOf(string);
                    Bundle zza2 = zzz.zza(Uri.parse(valueOf.length() != 0 ? "?".concat(valueOf) : new String("?")));
                    if (zza2 == null) {
                        zzeu.zzj.zzab().zzgk().zzao("No campaign params defined in install referrer result");
                    } else {
                        String string2 = zza2.getString("medium");
                        if (string2 != null && !"(not set)".equalsIgnoreCase(string2) && !"organic".equalsIgnoreCase(string2)) {
                            long j2 = zza.getLong("referrer_click_timestamp_seconds", 0) * 1000;
                            if (j2 == 0) {
                                zzeu.zzj.zzab().zzgk().zzao("Install Referrer is missing click timestamp for ad campaign");
                            } else {
                                zza2.putLong("click_timestamp", j2);
                            }
                        }
                        if (j == zzeu.zzj.zzac().zzlp.get()) {
                            zzeu.zzj.zzae();
                            zzeu.zzj.zzab().zzgs().zzao("Campaign has already been logged");
                        } else {
                            zzeu.zzj.zzac().zzlp.set(j);
                            zzeu.zzj.zzae();
                            zzeu.zzj.zzab().zzgs().zza("Logging Install Referrer campaign from sdk with ", "referrer API");
                            zza2.putString("_cis", "referrer API");
                            zzeu.zzj.zzq().logEvent("auto", "_cmp", zza2);
                        }
                    }
                }
            }
        }
        if (serviceConnection != null) {
            ConnectionTracker.getInstance().unbindService(zzeu.zzj.getContext(), serviceConnection);
        }
    }
}
