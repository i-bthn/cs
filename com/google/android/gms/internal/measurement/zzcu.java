package com.google.android.gms.internal.measurement;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
public final class zzcu<T> extends zzcw<T> {
    static final zzcu<Object> zzabp = new zzcu<>();

    private zzcu() {
    }

    public final boolean equals(@NullableDecl Object obj) {
        return obj == this;
    }

    public final int hashCode() {
        return 2040732332;
    }

    @Override // com.google.android.gms.internal.measurement.zzcw
    public final boolean isPresent() {
        return false;
    }

    public final String toString() {
        return "Optional.absent()";
    }

    @Override // com.google.android.gms.internal.measurement.zzcw
    public final T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }
}
