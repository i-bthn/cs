package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzey;
import java.io.IOException;
import java.util.List;
import java.util.Map;

final class zzei implements zzim {
    private final zzee zzadn;

    public static zzei zza(zzee zzee) {
        if (zzee.zzaed != null) {
            return zzee.zzaed;
        }
        return new zzei(zzee);
    }

    private zzei(zzee zzee) {
        this.zzadn = (zzee) zzez.zza((Object) zzee, "output");
        this.zzadn.zzaed = this;
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final int zztk() {
        return zzey.zzd.zzaio;
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzm(int i, int i2) throws IOException {
        this.zzadn.zzf(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzi(int i, long j) throws IOException {
        this.zzadn.zza(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzj(int i, long j) throws IOException {
        this.zzadn.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zza(int i, float f) throws IOException {
        this.zzadn.zza(i, f);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zza(int i, double d) throws IOException {
        this.zzadn.zza(i, d);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzn(int i, int i2) throws IOException {
        this.zzadn.zzc(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zza(int i, long j) throws IOException {
        this.zzadn.zza(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzc(int i, int i2) throws IOException {
        this.zzadn.zzc(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzc(int i, long j) throws IOException {
        this.zzadn.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzf(int i, int i2) throws IOException {
        this.zzadn.zzf(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzb(int i, boolean z) throws IOException {
        this.zzadn.zzb(i, z);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzb(int i, String str) throws IOException {
        this.zzadn.zzb(i, str);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zza(int i, zzdp zzdp) throws IOException {
        this.zzadn.zza(i, zzdp);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzd(int i, int i2) throws IOException {
        this.zzadn.zzd(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zze(int i, int i2) throws IOException {
        this.zzadn.zze(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzb(int i, long j) throws IOException {
        this.zzadn.zzb(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zza(int i, Object obj, zzgx zzgx) throws IOException {
        this.zzadn.zza(i, (zzgi) obj, zzgx);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzb(int i, Object obj, zzgx zzgx) throws IOException {
        zzee zzee = this.zzadn;
        zzee.zzb(i, 3);
        zzgx.zza((zzgi) obj, zzee.zzaed);
        zzee.zzb(i, 4);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzbr(int i) throws IOException {
        this.zzadn.zzb(i, 3);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzbs(int i) throws IOException {
        this.zzadn.zzb(i, 4);
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzdp) {
            this.zzadn.zzb(i, (zzdp) obj);
        } else {
            this.zzadn.zzb(i, (zzgi) obj);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbj(list.get(i4).intValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbe(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzc(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbm(list.get(i4).intValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbh(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbq(list.get(i4).longValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbn(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbr(list.get(i4).longValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbn(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbt(list.get(i4).longValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbp(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzb(list.get(i4).floatValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zza(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zze(list.get(i4).doubleValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzd(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zza(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbo(list.get(i4).intValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbe(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzc(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzr(list.get(i4).booleanValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzq(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzb(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzfp) {
            zzfp zzfp = (zzfp) list;
            while (i2 < list.size()) {
                Object zzbw = zzfp.zzbw(i2);
                if (zzbw instanceof String) {
                    this.zzadn.zzb(i, (String) zzbw);
                } else {
                    this.zzadn.zza(i, (zzdp) zzbw);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzb(i, list.get(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzb(int i, List<zzdp> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzadn.zza(i, list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbk(list.get(i4).intValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbf(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzd(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbn(list.get(i4).intValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbh(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbu(list.get(i4).longValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbp(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbl(list.get(i4).intValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbg(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zze(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbs(list.get(i4).longValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbo(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zza(int i, List<?> list, zzgx zzgx) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzgx);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final void zzb(int i, List<?> list, zzgx zzgx) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzgx);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim
    public final <K, V> void zza(int i, zzfz<K, V> zzfz, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.zzadn.zzb(i, 2);
            this.zzadn.zzbf(zzga.zza(zzfz, entry.getKey(), entry.getValue()));
            zzga.zza(this.zzadn, zzfz, entry.getKey(), entry.getValue());
        }
    }
}
