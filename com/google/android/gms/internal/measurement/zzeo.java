package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzeq;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzeo<FieldDescriptorType extends zzeq<FieldDescriptorType>> {
    private static final zzeo zzafa = new zzeo(true);
    final zzhc<FieldDescriptorType, Object> zzaex = zzhc.zzce(16);
    private boolean zzaey;
    private boolean zzaez = false;

    private zzeo() {
    }

    private zzeo(boolean z) {
        zzry();
    }

    public static <T extends zzeq<T>> zzeo<T> zztr() {
        return zzafa;
    }

    public final void zzry() {
        if (!this.zzaey) {
            this.zzaex.zzry();
            this.zzaey = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzaey;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzeo)) {
            return false;
        }
        return this.zzaex.equals(((zzeo) obj).zzaex);
    }

    public final int hashCode() {
        return this.zzaex.hashCode();
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        if (this.zzaez) {
            return new zzfo(this.zzaex.entrySet().iterator());
        }
        return this.zzaex.entrySet().iterator();
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<FieldDescriptorType, Object>> descendingIterator() {
        if (this.zzaez) {
            return new zzfo(this.zzaex.zzwj().iterator());
        }
        return this.zzaex.zzwj().iterator();
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.zzaex.get(fielddescriptortype);
        return obj instanceof zzfj ? zzfj.zzvc() : obj;
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.zzty()) {
            zza(fielddescriptortype.zztw(), obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(fielddescriptortype.zztw(), obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzfj) {
            this.zzaez = true;
        }
        this.zzaex.put(fielddescriptortype, obj);
    }

    private static void zza(zzig zzig, Object obj) {
        zzez.checkNotNull(obj);
        boolean z = false;
        switch (zzer.zzafd[zzig.zzwz().ordinal()]) {
            case 1:
                z = obj instanceof Integer;
                break;
            case 2:
                z = obj instanceof Long;
                break;
            case 3:
                z = obj instanceof Float;
                break;
            case 4:
                z = obj instanceof Double;
                break;
            case 5:
                z = obj instanceof Boolean;
                break;
            case 6:
                z = obj instanceof String;
                break;
            case 7:
                if ((obj instanceof zzdp) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzfc)) {
                    z = true;
                    break;
                }
            case 9:
                if ((obj instanceof zzgi) || (obj instanceof zzfj)) {
                    z = true;
                    break;
                }
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzaex.zzwh(); i++) {
            if (!zzb(this.zzaex.zzcf(i))) {
                return false;
            }
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.zzaex.zzwi()) {
            if (!zzb(entry)) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzb(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        if (key.zztx() == zzij.MESSAGE) {
            if (key.zzty()) {
                for (zzgi zzgi : (List) entry.getValue()) {
                    if (!zzgi.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzgi) {
                    if (!((zzgi) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzfj) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzeo<FieldDescriptorType> zzeo) {
        for (int i = 0; i < zzeo.zzaex.zzwh(); i++) {
            zzc(zzeo.zzaex.zzcf(i));
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : zzeo.zzaex.zzwi()) {
            zzc(entry);
        }
    }

    private static Object zzk(Object obj) {
        if (obj instanceof zzgn) {
            return ((zzgn) obj).zzvu();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzc(Map.Entry<FieldDescriptorType, Object> entry) {
        zzgi zzgi;
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzfj) {
            value = zzfj.zzvc();
        }
        if (key.zzty()) {
            Object zza = zza(key);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object obj : (List) value) {
                ((List) zza).add(zzk(obj));
            }
            this.zzaex.put(key, zza);
        } else if (key.zztx() == zzij.MESSAGE) {
            Object zza2 = zza(key);
            if (zza2 == null) {
                this.zzaex.put(key, zzk(value));
                return;
            }
            if (zza2 instanceof zzgn) {
                zzgi = key.zza((zzgn) zza2, (zzgn) value);
            } else {
                zzgi = key.zza(((zzgi) zza2).zzuo(), (zzgi) value).zzug();
            }
            this.zzaex.put(key, zzgi);
        } else {
            this.zzaex.put(key, zzk(value));
        }
    }

    static void zza(zzee zzee, zzig zzig, int i, Object obj) throws IOException {
        if (zzig == zzig.GROUP) {
            zzgi zzgi = (zzgi) obj;
            zzez.zzf(zzgi);
            zzee.zzb(i, 3);
            zzgi.zzb(zzee);
            zzee.zzb(i, 4);
            return;
        }
        zzee.zzb(i, zzig.zzxa());
        switch (zzer.zzaee[zzig.ordinal()]) {
            case 1:
                zzee.zzd(((Double) obj).doubleValue());
                return;
            case 2:
                zzee.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzee.zzbn(((Long) obj).longValue());
                return;
            case 4:
                zzee.zzbn(((Long) obj).longValue());
                return;
            case 5:
                zzee.zzbe(((Integer) obj).intValue());
                return;
            case 6:
                zzee.zzbp(((Long) obj).longValue());
                return;
            case 7:
                zzee.zzbh(((Integer) obj).intValue());
                return;
            case 8:
                zzee.zzq(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzgi) obj).zzb(zzee);
                return;
            case 10:
                zzee.zzb((zzgi) obj);
                return;
            case 11:
                if (obj instanceof zzdp) {
                    zzee.zza((zzdp) obj);
                    return;
                } else {
                    zzee.zzdr((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzdp) {
                    zzee.zza((zzdp) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzee.zze(bArr, 0, bArr.length);
                return;
            case 13:
                zzee.zzbf(((Integer) obj).intValue());
                return;
            case 14:
                zzee.zzbh(((Integer) obj).intValue());
                return;
            case 15:
                zzee.zzbp(((Long) obj).longValue());
                return;
            case 16:
                zzee.zzbg(((Integer) obj).intValue());
                return;
            case 17:
                zzee.zzbo(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzfc) {
                    zzee.zzbe(((zzfc) obj).zzlg());
                    return;
                } else {
                    zzee.zzbe(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public final int zzts() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzaex.zzwh(); i2++) {
            i += zzd(this.zzaex.zzcf(i2));
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.zzaex.zzwi()) {
            i += zzd(entry);
        }
        return i;
    }

    private static int zzd(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (key.zztx() != zzij.MESSAGE || key.zzty() || key.zztz()) {
            return zzb((zzeq<?>) key, value);
        }
        if (value instanceof zzfj) {
            return zzee.zzb(entry.getKey().zzlg(), (zzfj) value);
        }
        return zzee.zzd(entry.getKey().zzlg(), (zzgi) value);
    }

    static int zza(zzig zzig, int i, Object obj) {
        int zzbi = zzee.zzbi(i);
        if (zzig == zzig.GROUP) {
            zzez.zzf((zzgi) obj);
            zzbi <<= 1;
        }
        return zzbi + zzb(zzig, obj);
    }

    private static int zzb(zzig zzig, Object obj) {
        switch (zzer.zzaee[zzig.ordinal()]) {
            case 1:
                return zzee.zze(((Double) obj).doubleValue());
            case 2:
                return zzee.zzb(((Float) obj).floatValue());
            case 3:
                return zzee.zzbq(((Long) obj).longValue());
            case 4:
                return zzee.zzbr(((Long) obj).longValue());
            case 5:
                return zzee.zzbj(((Integer) obj).intValue());
            case 6:
                return zzee.zzbt(((Long) obj).longValue());
            case 7:
                return zzee.zzbm(((Integer) obj).intValue());
            case 8:
                return zzee.zzr(((Boolean) obj).booleanValue());
            case 9:
                return zzee.zzd((zzgi) obj);
            case 10:
                if (obj instanceof zzfj) {
                    return zzee.zza((zzfj) obj);
                }
                return zzee.zzc((zzgi) obj);
            case 11:
                if (obj instanceof zzdp) {
                    return zzee.zzb((zzdp) obj);
                }
                return zzee.zzds((String) obj);
            case 12:
                if (obj instanceof zzdp) {
                    return zzee.zzb((zzdp) obj);
                }
                return zzee.zzg((byte[]) obj);
            case 13:
                return zzee.zzbk(((Integer) obj).intValue());
            case 14:
                return zzee.zzbn(((Integer) obj).intValue());
            case 15:
                return zzee.zzbu(((Long) obj).longValue());
            case 16:
                return zzee.zzbl(((Integer) obj).intValue());
            case 17:
                return zzee.zzbs(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzfc) {
                    return zzee.zzbo(((zzfc) obj).zzlg());
                }
                return zzee.zzbo(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zzb(zzeq<?> zzeq, Object obj) {
        zzig zztw = zzeq.zztw();
        int zzlg = zzeq.zzlg();
        if (!zzeq.zzty()) {
            return zza(zztw, zzlg, obj);
        }
        int i = 0;
        if (zzeq.zztz()) {
            for (Object obj2 : (List) obj) {
                i += zzb(zztw, obj2);
            }
            return zzee.zzbi(zzlg) + i + zzee.zzbq(i);
        }
        for (Object obj3 : (List) obj) {
            i += zza(zztw, zzlg, obj3);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzeo zzeo = new zzeo();
        for (int i = 0; i < this.zzaex.zzwh(); i++) {
            Map.Entry<FieldDescriptorType, Object> zzcf = this.zzaex.zzcf(i);
            zzeo.zza(zzcf.getKey(), zzcf.getValue());
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.zzaex.zzwi()) {
            zzeo.zza(entry.getKey(), entry.getValue());
        }
        zzeo.zzaez = this.zzaez;
        return zzeo;
    }
}
