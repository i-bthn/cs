package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzbk;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class zzjo extends zzjh {
    zzjo(zzjg zzjg) {
        super(zzjg);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.measurement.internal.zzjh
    public final boolean zzbk() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzbs.zzk.zza zza, Object obj) {
        Preconditions.checkNotNull(obj);
        zza.zzqz().zzra().zzrb();
        if (obj instanceof String) {
            zza.zzdc((String) obj);
        } else if (obj instanceof Long) {
            zza.zzbl(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zza.zzc(((Double) obj).doubleValue());
        } else {
            zzab().zzgk().zza("Ignoring invalid (type) user attribute value", obj);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzbs.zze.zza zza, Object obj) {
        Preconditions.checkNotNull(obj);
        zza.zzmu().zzmv().zzmw();
        if (obj instanceof String) {
            zza.zzca((String) obj);
        } else if (obj instanceof Long) {
            zza.zzam(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zza.zza(((Double) obj).doubleValue());
        } else {
            zzab().zzgk().zza("Ignoring invalid (type) event param value", obj);
        }
    }

    static zzbs.zze zza(zzbs.zzc zzc, String str) {
        for (zzbs.zze zze : zzc.zzmj()) {
            if (zze.getName().equals(str)) {
                return zze;
            }
        }
        return null;
    }

    static Object zzb(zzbs.zzc zzc, String str) {
        zzbs.zze zza = zza(zzc, str);
        if (zza == null) {
            return null;
        }
        if (zza.zzmx()) {
            return zza.zzmy();
        }
        if (zza.zzna()) {
            return Long.valueOf(zza.zznb());
        }
        if (zza.zznd()) {
            return Double.valueOf(zza.zzne());
        }
        return null;
    }

    static void zza(zzbs.zzc.zza zza, String str, Object obj) {
        List<zzbs.zze> zzmj = zza.zzmj();
        int i = 0;
        while (true) {
            if (i >= zzmj.size()) {
                i = -1;
                break;
            } else if (str.equals(zzmj.get(i).getName())) {
                break;
            } else {
                i++;
            }
        }
        zzbs.zze.zza zzbz = zzbs.zze.zzng().zzbz(str);
        if (obj instanceof Long) {
            zzbz.zzam(((Long) obj).longValue());
        } else if (obj instanceof String) {
            zzbz.zzca((String) obj);
        } else if (obj instanceof Double) {
            zzbz.zza(((Double) obj).doubleValue());
        }
        if (i >= 0) {
            zza.zza(i, zzbz);
        } else {
            zza.zza(zzbz);
        }
    }

    /* access modifiers changed from: package-private */
    public final String zza(zzbs.zzf zzf) {
        List<zzbs.zze> zzmj;
        if (zzf == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        for (zzbs.zzg zzg : zzf.zzni()) {
            if (zzg != null) {
                zza(sb, 1);
                sb.append("bundle {\n");
                if (zzg.zznx()) {
                    zza(sb, 1, "protocol_version", Integer.valueOf(zzg.zzny()));
                }
                zza(sb, 1, "platform", zzg.zzom());
                if (zzg.zzoq()) {
                    zza(sb, 1, "gmp_version", Long.valueOf(zzg.zzao()));
                }
                if (zzg.zzor()) {
                    zza(sb, 1, "uploading_gmp_version", Long.valueOf(zzg.zzos()));
                }
                if (zzg.zzpq()) {
                    zza(sb, 1, "dynamite_version", Long.valueOf(zzg.zzaq()));
                }
                if (zzg.zzpi()) {
                    zza(sb, 1, "config_version", Long.valueOf(zzg.zzpj()));
                }
                zza(sb, 1, "gmp_app_id", zzg.getGmpAppId());
                zza(sb, 1, "admob_app_id", zzg.zzpp());
                zza(sb, 1, "app_id", zzg.zzag());
                zza(sb, 1, "app_version", zzg.zzal());
                if (zzg.zzpf()) {
                    zza(sb, 1, "app_version_major", Integer.valueOf(zzg.zzpg()));
                }
                zza(sb, 1, "firebase_instance_id", zzg.getFirebaseInstanceId());
                if (zzg.zzow()) {
                    zza(sb, 1, "dev_cert_hash", Long.valueOf(zzg.zzap()));
                }
                zza(sb, 1, "app_store", zzg.zzan());
                if (zzg.zzoc()) {
                    zza(sb, 1, "upload_timestamp_millis", Long.valueOf(zzg.zzod()));
                }
                if (zzg.zzoe()) {
                    zza(sb, 1, "start_timestamp_millis", Long.valueOf(zzg.zznq()));
                }
                if (zzg.zzof()) {
                    zza(sb, 1, "end_timestamp_millis", Long.valueOf(zzg.zznr()));
                }
                if (zzg.zzog()) {
                    zza(sb, 1, "previous_bundle_start_timestamp_millis", Long.valueOf(zzg.zzoh()));
                }
                if (zzg.zzoj()) {
                    zza(sb, 1, "previous_bundle_end_timestamp_millis", Long.valueOf(zzg.zzok()));
                }
                zza(sb, 1, "app_instance_id", zzg.getAppInstanceId());
                zza(sb, 1, "resettable_device_id", zzg.zzot());
                zza(sb, 1, "device_id", zzg.zzph());
                zza(sb, 1, "ds_id", zzg.zzpl());
                if (zzg.zzou()) {
                    zza(sb, 1, "limited_ad_tracking", Boolean.valueOf(zzg.zzov()));
                }
                zza(sb, 1, "os_version", zzg.getOsVersion());
                zza(sb, 1, "device_model", zzg.zzon());
                zza(sb, 1, "user_default_language", zzg.zzcr());
                if (zzg.zzoo()) {
                    zza(sb, 1, "time_zone_offset_minutes", Integer.valueOf(zzg.zzop()));
                }
                if (zzg.zzox()) {
                    zza(sb, 1, "bundle_sequential_index", Integer.valueOf(zzg.zzoy()));
                }
                if (zzg.zzpb()) {
                    zza(sb, 1, "service_upload", Boolean.valueOf(zzg.zzpc()));
                }
                zza(sb, 1, "health_monitor", zzg.zzoz());
                if (zzg.zzpk() && zzg.zzbd() != 0) {
                    zza(sb, 1, "android_id", Long.valueOf(zzg.zzbd()));
                }
                if (zzg.zzpn()) {
                    zza(sb, 1, "retry_counter", Integer.valueOf(zzg.zzpo()));
                }
                List<zzbs.zzk> zzno = zzg.zzno();
                if (zzno != null) {
                    for (zzbs.zzk zzk : zzno) {
                        if (zzk != null) {
                            zza(sb, 2);
                            sb.append("user_property {\n");
                            zza(sb, 2, "set_timestamp_millis", zzk.zzqs() ? Long.valueOf(zzk.zzqt()) : null);
                            zza(sb, 2, AppMeasurementSdk.ConditionalUserProperty.NAME, zzy().zzal(zzk.getName()));
                            zza(sb, 2, "string_value", zzk.zzmy());
                            zza(sb, 2, "int_value", zzk.zzna() ? Long.valueOf(zzk.zznb()) : null);
                            zza(sb, 2, "double_value", zzk.zznd() ? Double.valueOf(zzk.zzne()) : null);
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzbs.zza> zzpd = zzg.zzpd();
                String zzag = zzg.zzag();
                if (zzpd != null) {
                    for (zzbs.zza zza : zzpd) {
                        if (zza != null) {
                            zza(sb, 2);
                            sb.append("audience_membership {\n");
                            if (zza.zzly()) {
                                zza(sb, 2, "audience_id", Integer.valueOf(zza.zzlz()));
                            }
                            if (zza.zzma()) {
                                zza(sb, 2, "new_audience", Boolean.valueOf(zza.zzmb()));
                            }
                            zza(sb, 2, "current_data", zza.zzlv(), zzag);
                            zza(sb, 2, "previous_data", zza.zzlx(), zzag);
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzbs.zzc> zznl = zzg.zznl();
                if (zznl != null) {
                    for (zzbs.zzc zzc : zznl) {
                        if (zzc != null) {
                            zza(sb, 2);
                            sb.append("event {\n");
                            zza(sb, 2, AppMeasurementSdk.ConditionalUserProperty.NAME, zzy().zzaj(zzc.getName()));
                            if (zzc.zzml()) {
                                zza(sb, 2, "timestamp_millis", Long.valueOf(zzc.getTimestampMillis()));
                            }
                            if (zzc.zzmo()) {
                                zza(sb, 2, "previous_timestamp_millis", Long.valueOf(zzc.zzmm()));
                            }
                            if (zzc.zzmp()) {
                                zza(sb, 2, "count", Integer.valueOf(zzc.getCount()));
                            }
                            if (!(zzc.zzmk() == 0 || (zzmj = zzc.zzmj()) == null)) {
                                for (zzbs.zze zze : zzmj) {
                                    if (zze != null) {
                                        zza(sb, 3);
                                        sb.append("param {\n");
                                        zza(sb, 3, AppMeasurementSdk.ConditionalUserProperty.NAME, zzy().zzak(zze.getName()));
                                        zza(sb, 3, "string_value", zze.zzmy());
                                        zza(sb, 3, "int_value", zze.zzna() ? Long.valueOf(zze.zznb()) : null);
                                        zza(sb, 3, "double_value", zze.zznd() ? Double.valueOf(zze.zzne()) : null);
                                        zza(sb, 3);
                                        sb.append("}\n");
                                    }
                                }
                            }
                            zza(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                zza(sb, 1);
                sb.append("}\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zza(zzbk.zza zza) {
        if (zza == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (zza.zzkb()) {
            zza(sb, 0, "filter_id", Integer.valueOf(zza.getId()));
        }
        zza(sb, 0, "event_name", zzy().zzaj(zza.zzjz()));
        String zza2 = zza(zza.zzkf(), zza.zzkg(), zza.zzki());
        if (!zza2.isEmpty()) {
            zza(sb, 0, "filter_type", zza2);
        }
        zza(sb, 1, "event_count_filter", zza.zzke());
        sb.append("  filters {\n");
        for (zzbk.zzb zzb : zza.zzkc()) {
            zza(sb, 2, zzb);
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zza(zzbk.zzd zzd) {
        if (zzd == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (zzd.zzkb()) {
            zza(sb, 0, "filter_id", Integer.valueOf(zzd.getId()));
        }
        zza(sb, 0, "property_name", zzy().zzal(zzd.getPropertyName()));
        String zza = zza(zzd.zzkf(), zzd.zzkg(), zzd.zzki());
        if (!zza.isEmpty()) {
            zza(sb, 0, "filter_type", zza);
        }
        zza(sb, 1, zzd.zzli());
        sb.append("}\n");
        return sb.toString();
    }

    private static String zza(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    private final void zza(StringBuilder sb, int i, String str, zzbs.zzi zzi, String str2) {
        if (zzi != null) {
            zza(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            if (zzi.zzpz() != 0) {
                zza(sb, 4);
                sb.append("results: ");
                int i2 = 0;
                for (Long l : zzi.zzpy()) {
                    int i3 = i2 + 1;
                    if (i2 != 0) {
                        sb.append(", ");
                    }
                    sb.append(l);
                    i2 = i3;
                }
                sb.append('\n');
            }
            if (zzi.zzpw() != 0) {
                zza(sb, 4);
                sb.append("status: ");
                int i4 = 0;
                for (Long l2 : zzi.zzpv()) {
                    int i5 = i4 + 1;
                    if (i4 != 0) {
                        sb.append(", ");
                    }
                    sb.append(l2);
                    i4 = i5;
                }
                sb.append('\n');
            }
            if (zzad().zzq(str2)) {
                if (zzi.zzqc() != 0) {
                    zza(sb, 4);
                    sb.append("dynamic_filter_timestamps: {");
                    int i6 = 0;
                    for (zzbs.zzb zzb : zzi.zzqb()) {
                        int i7 = i6 + 1;
                        if (i6 != 0) {
                            sb.append(", ");
                        }
                        sb.append(zzb.zzme() ? Integer.valueOf(zzb.getIndex()) : null);
                        sb.append(":");
                        sb.append(zzb.zzmf() ? Long.valueOf(zzb.zzmg()) : null);
                        i6 = i7;
                    }
                    sb.append("}\n");
                }
                if (zzi.zzqf() != 0) {
                    zza(sb, 4);
                    sb.append("sequence_filter_timestamps: {");
                    int i8 = 0;
                    for (zzbs.zzj zzj : zzi.zzqe()) {
                        int i9 = i8 + 1;
                        if (i8 != 0) {
                            sb.append(", ");
                        }
                        sb.append(zzj.zzme() ? Integer.valueOf(zzj.getIndex()) : null);
                        sb.append(": [");
                        int i10 = 0;
                        for (Long l3 : zzj.zzqk()) {
                            long longValue = l3.longValue();
                            int i11 = i10 + 1;
                            if (i10 != 0) {
                                sb.append(", ");
                            }
                            sb.append(longValue);
                            i10 = i11;
                        }
                        sb.append("]");
                        i8 = i9;
                    }
                    sb.append("}\n");
                }
            }
            zza(sb, 3);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, String str, zzbk.zzc zzc) {
        if (zzc != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzc.zzku()) {
                zza(sb, i, "comparison_type", zzc.zzkv().name());
            }
            if (zzc.zzkw()) {
                zza(sb, i, "match_as_float", Boolean.valueOf(zzc.zzkx()));
            }
            zza(sb, i, "comparison_value", zzc.zzkz());
            zza(sb, i, "min_comparison_value", zzc.zzlb());
            zza(sb, i, "max_comparison_value", zzc.zzld());
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, zzbk.zzb zzb) {
        if (zzb != null) {
            zza(sb, i);
            sb.append("filter {\n");
            if (zzb.zzkp()) {
                zza(sb, i, "complement", Boolean.valueOf(zzb.zzkq()));
            }
            zza(sb, i, "param_name", zzy().zzak(zzb.zzkr()));
            int i2 = i + 1;
            zzbk.zze zzkm = zzb.zzkm();
            if (zzkm != null) {
                zza(sb, i2);
                sb.append("string_filter");
                sb.append(" {\n");
                if (zzkm.zzlk()) {
                    zza(sb, i2, "match_type", zzkm.zzll().name());
                }
                zza(sb, i2, "expression", zzkm.zzln());
                if (zzkm.zzlo()) {
                    zza(sb, i2, "case_sensitive", Boolean.valueOf(zzkm.zzlp()));
                }
                if (zzkm.zzlr() > 0) {
                    zza(sb, i2 + 1);
                    sb.append("expression_list {\n");
                    for (String str : zzkm.zzlq()) {
                        zza(sb, i2 + 2);
                        sb.append(str);
                        sb.append("\n");
                    }
                    sb.append("}\n");
                }
                zza(sb, i2);
                sb.append("}\n");
            }
            zza(sb, i2, "number_filter", zzb.zzko());
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append('\n');
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        zzab().zzgk().zzao("Failed to load parcelable from buffer");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r1.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001c */
    public final <T extends Parcelable> T zza(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        T createFromParcel = creator.createFromParcel(obtain);
        obtain.recycle();
        return createFromParcel;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zze(zzai zzai, zzn zzn) {
        Preconditions.checkNotNull(zzai);
        Preconditions.checkNotNull(zzn);
        if (!TextUtils.isEmpty(zzn.zzcg) || !TextUtils.isEmpty(zzn.zzcu)) {
            return true;
        }
        zzae();
        return false;
    }

    static boolean zzbj(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static boolean zza(List<Long> list, int i) {
        if (i >= (list.size() << 6)) {
            return false;
        }
        return ((1 << (i % 64)) & list.get(i / 64).longValue()) != 0;
    }

    static List<Long> zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            long j = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i << 6) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    j |= 1 << i2;
                }
            }
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public final List<Long> zza(List<Long> list, List<Integer> list2) {
        ArrayList arrayList = new ArrayList(list);
        for (Integer num : list2) {
            if (num.intValue() < 0) {
                zzab().zzgn().zza("Ignoring negative bit index to be cleared", num);
            } else {
                int intValue = num.intValue() / 64;
                if (intValue >= arrayList.size()) {
                    zzab().zzgn().zza("Ignoring bit index greater than bitSet size", num, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(intValue, Long.valueOf(((Long) arrayList.get(intValue)).longValue() & ((1 << (num.intValue() % 64)) ^ -1)));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (size2 >= 0 && ((Long) arrayList.get(size2)).longValue() == 0) {
            size = size2;
            size2--;
        }
        return arrayList.subList(0, size);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzx().currentTimeMillis() - j) > j2;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final long zza(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        zzz().zzo();
        MessageDigest messageDigest = zzjs.getMessageDigest();
        if (messageDigest != null) {
            return zzjs.zzd(messageDigest.digest(bArr));
        }
        zzab().zzgk().zzao("Failed to get MD5");
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final byte[] zzb(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzab().zzgk().zza("Failed to ungzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public final byte[] zzc(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzab().zzgk().zza("Failed to gzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final List<Integer> zzju() {
        Map<String, String> zzk = zzak.zzk(this.zzkz.getContext());
        if (zzk == null || zzk.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int intValue = zzak.zzhr.get(null).intValue();
        Iterator<Map.Entry<String, String>> it = zzk.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, String> next = it.next();
            if (next.getKey().startsWith("measurement.id.")) {
                try {
                    int parseInt = Integer.parseInt(next.getValue());
                    if (parseInt != 0) {
                        arrayList.add(Integer.valueOf(parseInt));
                        if (arrayList.size() >= intValue) {
                            zzab().zzgn().zza("Too many experiment IDs. Number of IDs", Integer.valueOf(arrayList.size()));
                            break;
                        }
                    } else {
                        continue;
                    }
                } catch (NumberFormatException e) {
                    zzab().zzgn().zza("Experiment ID NumberFormatException", e);
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzje
    public final /* bridge */ /* synthetic */ zzjo zzgw() {
        return super.zzgw();
    }

    @Override // com.google.android.gms.measurement.internal.zzje
    public final /* bridge */ /* synthetic */ zzp zzgx() {
        return super.zzgx();
    }

    @Override // com.google.android.gms.measurement.internal.zzje
    public final /* bridge */ /* synthetic */ zzx zzgy() {
        return super.zzgy();
    }

    @Override // com.google.android.gms.measurement.internal.zzje
    public final /* bridge */ /* synthetic */ zzfd zzgz() {
        return super.zzgz();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzl() {
        super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzm() {
        super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzn() {
        super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzo() {
        super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzac zzw() {
        return super.zzw();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ Clock zzx() {
        return super.zzx();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzed zzy() {
        return super.zzy();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzjs zzz() {
        return super.zzz();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzfc zzaa() {
        return super.zzaa();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzef zzab() {
        return super.zzab();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzeo zzac() {
        return super.zzac();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzs zzad() {
        return super.zzad();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzr zzae() {
        return super.zzae();
    }
}
