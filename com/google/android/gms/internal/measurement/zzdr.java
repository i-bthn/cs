package com.google.android.gms.internal.measurement;

import java.util.Comparator;

final class zzdr implements Comparator<zzdp> {
    zzdr() {
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzdp zzdp, zzdp zzdp2) {
        zzdp zzdp3 = zzdp;
        zzdp zzdp4 = zzdp2;
        zzdu zzdu = (zzdu) zzdp3.iterator();
        zzdu zzdu2 = (zzdu) zzdp4.iterator();
        while (zzdu.hasNext() && zzdu2.hasNext()) {
            int compare = Integer.compare(zzdp.zza(zzdu.nextByte()), zzdp.zza(zzdu2.nextByte()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzdp3.size(), zzdp4.size());
    }
}
