package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzhb extends zzhc<FieldDescriptorType, Object> {
    zzhb(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzhc
    public final void zzry() {
        if (!isImmutable()) {
            for (int i = 0; i < zzwh(); i++) {
                Map.Entry zzcf = zzcf(i);
                if (((zzeq) zzcf.getKey()).zzty()) {
                    zzcf.setValue(Collections.unmodifiableList((List) zzcf.getValue()));
                }
            }
            for (Map.Entry entry : zzwi()) {
                if (((zzeq) entry.getKey()).zzty()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzry();
    }
}
