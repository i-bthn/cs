package com.google.firebase.events;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@17.0.0 */
public interface Publisher {
    @KeepForSdk
    void publish(Event<?> event);
}
