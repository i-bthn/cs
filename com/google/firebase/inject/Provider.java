package com.google.firebase.inject;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@17.0.0 */
public interface Provider<T> {
    @KeepForSdk
    T get();
}
