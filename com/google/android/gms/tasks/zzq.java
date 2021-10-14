package com.google.android.gms.tasks;

import androidx.annotation.NonNull;

/* access modifiers changed from: package-private */
public interface zzq<TResult> {
    void cancel();

    void onComplete(@NonNull Task<TResult> task);
}
