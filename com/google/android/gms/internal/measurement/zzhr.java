package com.google.android.gms.internal.measurement;

import java.io.IOException;

final class zzhr extends zzhp<zzhs, zzhs> {
    zzhr() {
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final boolean zza(zzgy zzgy) {
        return false;
    }

    private static void zza(Object obj, zzhs zzhs) {
        ((zzey) obj).zzahz = zzhs;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final void zzj(Object obj) {
        ((zzey) obj).zzahz.zzry();
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ int zzt(zzhs zzhs) {
        return zzhs.zzuk();
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ int zzz(zzhs zzhs) {
        return zzhs.zzws();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ zzhs zzg(zzhs zzhs, zzhs zzhs2) {
        zzhs zzhs3 = zzhs;
        zzhs zzhs4 = zzhs2;
        if (zzhs4.equals(zzhs.zzwq())) {
            return zzhs3;
        }
        return zzhs.zza(zzhs3, zzhs4);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, com.google.android.gms.internal.measurement.zzim] */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ void zzc(zzhs zzhs, zzim zzim) throws IOException {
        zzhs.zza(zzim);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, com.google.android.gms.internal.measurement.zzim] */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ void zza(zzhs zzhs, zzim zzim) throws IOException {
        zzhs.zzb(zzim);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ void zzf(Object obj, zzhs zzhs) {
        zza(obj, zzhs);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ zzhs zzy(Object obj) {
        zzhs zzhs = ((zzey) obj).zzahz;
        if (zzhs != zzhs.zzwq()) {
            return zzhs;
        }
        zzhs zzwr = zzhs.zzwr();
        zza(obj, zzwr);
        return zzwr;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ zzhs zzx(Object obj) {
        return ((zzey) obj).zzahz;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ void zze(Object obj, zzhs zzhs) {
        zza(obj, zzhs);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ zzhs zzp(zzhs zzhs) {
        zzhs zzhs2 = zzhs;
        zzhs2.zzry();
        return zzhs2;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ zzhs zzwp() {
        return zzhs.zzwr();
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, java.lang.Object] */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ void zza(zzhs zzhs, int i, zzhs zzhs2) {
        zzhs.zzb((i << 3) | 3, zzhs2);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, com.google.android.gms.internal.measurement.zzdp] */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ void zza(zzhs zzhs, int i, zzdp zzdp) {
        zzhs.zzb((i << 3) | 2, zzdp);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, long] */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ void zzb(zzhs zzhs, int i, long j) {
        zzhs.zzb((i << 3) | 1, Long.valueOf(j));
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int] */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ void zzc(zzhs zzhs, int i, int i2) {
        zzhs.zzb((i << 3) | 5, Integer.valueOf(i2));
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, long] */
    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final /* synthetic */ void zza(zzhs zzhs, int i, long j) {
        zzhs.zzb(i << 3, Long.valueOf(j));
    }
}
