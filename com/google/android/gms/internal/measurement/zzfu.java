package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzfu extends zzfs {
    private static final Class<?> zzajv = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzfu() {
        super();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzfs
    public final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzfs
    public final void zzb(Object obj, long j) {
        Object obj2;
        List list = (List) zzhv.zzp(obj, j);
        if (list instanceof zzfp) {
            obj2 = ((zzfp) list).zzvg();
        } else if (!zzajv.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzgu) || !(list instanceof zzff)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzff zzff = (zzff) list;
                if (zzff.zzrx()) {
                    zzff.zzry();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzhv.zza(obj, j, obj2);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        List<L> list;
        List<L> zzd = zzd(obj, j);
        if (zzd.isEmpty()) {
            if (zzd instanceof zzfp) {
                list = new zzfq(i);
            } else if (!(zzd instanceof zzgu) || !(zzd instanceof zzff)) {
                list = new ArrayList<>(i);
            } else {
                list = ((zzff) zzd).zzap(i);
            }
            zzhv.zza(obj, j, list);
            return list;
        } else if (zzajv.isAssignableFrom(zzd.getClass())) {
            ArrayList arrayList = new ArrayList(zzd.size() + i);
            arrayList.addAll(zzd);
            zzhv.zza(obj, j, arrayList);
            return arrayList;
        } else if (zzd instanceof zzhu) {
            zzfq zzfq = new zzfq(zzd.size() + i);
            zzfq.addAll((zzhu) zzd);
            zzhv.zza(obj, j, zzfq);
            return zzfq;
        } else if (!(zzd instanceof zzgu) || !(zzd instanceof zzff)) {
            return zzd;
        } else {
            zzff zzff = (zzff) zzd;
            if (zzff.zzrx()) {
                return zzd;
            }
            zzff zzap = zzff.zzap(zzd.size() + i);
            zzhv.zza(obj, j, zzap);
            return zzap;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzfs
    public final <E> void zza(Object obj, Object obj2, long j) {
        List zzd = zzd(obj2, j);
        List zza = zza(obj, j, zzd.size());
        int size = zza.size();
        int size2 = zzd.size();
        if (size > 0 && size2 > 0) {
            zza.addAll(zzd);
        }
        if (size > 0) {
            zzd = zza;
        }
        zzhv.zza(obj, j, zzd);
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zzhv.zzp(obj, j);
    }
}
