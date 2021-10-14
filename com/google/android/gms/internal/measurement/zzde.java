package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzde<T> implements zzdb<T>, Serializable {
    @NullableDecl
    private final T zzaby;

    zzde(@NullableDecl T t) {
        this.zzaby = t;
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final T get() {
        return this.zzaby;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof zzde)) {
            return false;
        }
        T t = this.zzaby;
        T t2 = ((zzde) obj).zzaby;
        if (t == t2) {
            return true;
        }
        if (t == null || !t.equals(t2)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzaby});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzaby);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
        sb.append("Suppliers.ofInstance(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
