package com.google.firebase.iid;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.iid.FirebaseInstanceId;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzq implements EventHandler {
    private final FirebaseInstanceId.zza zzbm;

    zzq(FirebaseInstanceId.zza zza) {
        this.zzbm = zza;
    }

    @Override // com.google.firebase.events.EventHandler
    public final void handle(Event event) {
        FirebaseInstanceId.zza zza = this.zzbm;
        synchronized (zza) {
            if (zza.isEnabled()) {
                FirebaseInstanceId.this.zzh();
            }
        }
    }
}
