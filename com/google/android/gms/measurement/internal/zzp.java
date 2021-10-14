package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzbk;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzey;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* access modifiers changed from: package-private */
public final class zzp extends zzjh {
    zzp(zzjg zzjg) {
        super(zzjg);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.measurement.internal.zzjh
    public final boolean zzbk() {
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:421:0x0e51, code lost:
        if (r0.zzkb() == false) goto L_0x0e5c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:422:0x0e53, code lost:
        r8 = java.lang.Integer.valueOf(r0.getId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:423:0x0e5c, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:424:0x0e5d, code lost:
        r1.zza("Invalid property filter ID. appId, id", r4, java.lang.String.valueOf(r8));
        r15.add(java.lang.Integer.valueOf(r5));
        r0 = r66;
        r1 = r1;
        r7 = r43;
        r3 = r3;
        r4 = r4;
        r12 = r12;
        r43 = r40;
        r24 = r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0380  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0496  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x04b3  */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x04d0  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0557  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x05da  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x0666  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x067a  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x0689  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x07b5  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x0806  */
    /* JADX WARNING: Removed duplicated region for block: B:299:0x0a31  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x0a3a  */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x0a92  */
    /* JADX WARNING: Removed duplicated region for block: B:308:0x0aa3  */
    /* JADX WARNING: Removed duplicated region for block: B:428:0x0ea2  */
    /* JADX WARNING: Removed duplicated region for block: B:432:0x0ebe  */
    @WorkerThread
    public final List<zzbs.zza> zza(String str, List<zzbs.zzc> list, List<zzbs.zzk> list2) {
        Long l;
        Map<Integer, zzbs.zzi> zzaf;
        ArrayMap arrayMap;
        ArrayMap arrayMap2;
        ArrayMap arrayMap3;
        ArrayMap arrayMap4;
        ArrayMap arrayMap5;
        ArrayMap arrayMap6;
        ArrayMap arrayMap7;
        String str2;
        ArrayMap arrayMap8;
        ArrayMap arrayMap9;
        ArrayMap arrayMap10;
        ArrayMap arrayMap11;
        ArrayMap arrayMap12;
        Iterator it;
        zzbs.zza.C0003zza zza;
        Iterator it2;
        ArrayMap arrayMap13;
        SQLiteException e;
        ArrayList arrayList;
        ArrayMap arrayMap14;
        ArrayMap arrayMap15;
        ArrayMap arrayMap16;
        Map map;
        ArrayMap arrayMap17;
        Map map2;
        ArrayMap arrayMap18;
        ArrayMap arrayMap19;
        ArrayMap arrayMap20;
        Object obj;
        Integer num;
        ArrayList arrayList2;
        Long l2;
        zzbs.zzc zzc;
        long j;
        HashSet hashSet;
        zzbs.zzc zzc2;
        String str3;
        zzae zzc3;
        zzbs.zzc zzc4;
        HashSet hashSet2;
        ArrayMap arrayMap21;
        ArrayMap arrayMap22;
        ArrayMap arrayMap23;
        ArrayMap arrayMap24;
        ArrayMap arrayMap25;
        zzae zzae;
        ArrayMap arrayMap26;
        String str4;
        Map<Integer, List<zzbk.zza>> map3;
        Map<Integer, List<zzbk.zza>> map4;
        Iterator<Integer> it3;
        BitSet bitSet;
        ArrayMap arrayMap27;
        long j2;
        Map map5;
        ArrayMap arrayMap28;
        ArrayMap arrayMap29;
        ArrayMap arrayMap30;
        ArrayMap arrayMap31;
        Iterator<Integer> it4;
        ArrayMap arrayMap32;
        BitSet bitSet2;
        long j3;
        BitSet bitSet3;
        ArrayMap arrayMap33;
        zzae zzae2;
        ArrayMap arrayMap34;
        ArrayMap arrayMap35;
        Integer num2;
        String str5;
        Long l3;
        HashSet hashSet3;
        Long l4;
        ArrayList arrayList3;
        SQLiteException e2;
        Map<Integer, zzbs.zzi> map6;
        Iterator it5;
        Map<Integer, zzbs.zzi> map7;
        ArrayMap arrayMap36;
        ArrayMap arrayMap37;
        ArrayMap arrayMap38;
        ArrayMap arrayMap39;
        ArrayMap arrayMap40;
        ArrayMap arrayMap41;
        ArrayMap arrayMap42;
        boolean z;
        Iterator it6;
        Map<Integer, zzbs.zzi> map8;
        Long l5;
        zzx zzgy;
        String str6 = str;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(list2);
        HashSet hashSet4 = new HashSet();
        ArrayMap arrayMap43 = new ArrayMap();
        ArrayMap arrayMap44 = new ArrayMap();
        ArrayMap arrayMap45 = new ArrayMap();
        ArrayMap arrayMap46 = new ArrayMap();
        ArrayMap arrayMap47 = new ArrayMap();
        boolean zzq = zzad().zzq(str6);
        boolean zzd = zzad().zzd(str6, zzak.zziq);
        boolean zzd2 = zzad().zzd(str6, zzak.zziy);
        boolean zzd3 = zzad().zzd(str6, zzak.zziz);
        if (zzd2 || zzd3) {
            Iterator<zzbs.zzc> it7 = list.iterator();
            while (true) {
                if (!it7.hasNext()) {
                    break;
                }
                zzbs.zzc next = it7.next();
                if ("_s".equals(next.getName())) {
                    l = Long.valueOf(next.getTimestampMillis());
                    break;
                }
            }
            if (l != null && zzd3) {
                zzgy = zzgy();
                zzgy.zzbi();
                zzgy.zzo();
                Preconditions.checkNotEmpty(str);
                ContentValues contentValues = new ContentValues();
                contentValues.put("current_session_count", (Integer) 0);
                zzgy.getWritableDatabase().update("events", contentValues, "app_id = ?", new String[]{str6});
            }
            zzaf = zzgy().zzaf(str6);
            if (zzaf != null || zzaf.isEmpty()) {
                arrayMap3 = arrayMap45;
                arrayMap2 = arrayMap46;
                arrayMap = arrayMap44;
            } else {
                HashSet hashSet5 = new HashSet(zzaf.keySet());
                if (!zzd2 || l == null) {
                    map6 = zzaf;
                } else {
                    zzp zzgx = zzgx();
                    Preconditions.checkNotEmpty(str);
                    Preconditions.checkNotNull(zzaf);
                    map6 = new ArrayMap<>();
                    if (!zzaf.isEmpty()) {
                        Map<Integer, List<Integer>> zzae3 = zzgx.zzgy().zzae(str6);
                        Iterator<Integer> it8 = zzaf.keySet().iterator();
                        while (it8.hasNext()) {
                            int intValue = it8.next().intValue();
                            zzbs.zzi zzi = zzaf.get(Integer.valueOf(intValue));
                            List<Integer> list3 = zzae3.get(Integer.valueOf(intValue));
                            if (list3 == null || list3.isEmpty()) {
                                map6.put(Integer.valueOf(intValue), zzi);
                                zzae3 = zzae3;
                                it8 = it8;
                                zzgx = zzgx;
                            } else {
                                List<Long> zza2 = zzgx.zzgw().zza(zzi.zzpy(), list3);
                                if (!zza2.isEmpty()) {
                                    zzbs.zzi.zza zzo = ((zzbs.zzi.zza) zzi.zzuj()).zzqr().zzo(zza2);
                                    zzo.zzqq().zzn(zzgx.zzgw().zza(zzi.zzpv(), list3));
                                    for (int i = 0; i < zzi.zzqc(); i++) {
                                        if (list3.contains(Integer.valueOf(zzi.zzae(i).getIndex()))) {
                                            zzo.zzaj(i);
                                        }
                                    }
                                    for (int i2 = 0; i2 < zzi.zzqf(); i2++) {
                                        if (list3.contains(Integer.valueOf(zzi.zzag(i2).getIndex()))) {
                                            zzo.zzak(i2);
                                        }
                                    }
                                    map6.put(Integer.valueOf(intValue), (zzbs.zzi) ((zzey) zzo.zzug()));
                                    zzae3 = zzae3;
                                    it8 = it8;
                                    zzgx = zzgx;
                                } else {
                                    zzae3 = zzae3;
                                    it8 = it8;
                                }
                            }
                        }
                    }
                }
                Iterator it9 = hashSet5.iterator();
                while (it9.hasNext()) {
                    int intValue2 = ((Integer) it9.next()).intValue();
                    zzbs.zzi zzi2 = map6.get(Integer.valueOf(intValue2));
                    BitSet bitSet4 = (BitSet) arrayMap44.get(Integer.valueOf(intValue2));
                    BitSet bitSet5 = (BitSet) arrayMap45.get(Integer.valueOf(intValue2));
                    if (zzq) {
                        arrayMap36 = new ArrayMap();
                        if (zzi2 == null || zzi2.zzqc() == 0) {
                            it5 = it9;
                            map7 = map6;
                        } else {
                            for (zzbs.zzb zzb : zzi2.zzqb()) {
                                if (zzb.zzme()) {
                                    it6 = it9;
                                    Integer valueOf = Integer.valueOf(zzb.getIndex());
                                    if (zzb.zzmf()) {
                                        Long valueOf2 = Long.valueOf(zzb.zzmg());
                                        map8 = map6;
                                        l5 = valueOf2;
                                    } else {
                                        map8 = map6;
                                        l5 = null;
                                    }
                                    arrayMap36.put(valueOf, l5);
                                } else {
                                    it6 = it9;
                                    map8 = map6;
                                }
                                map6 = map8;
                                it9 = it6;
                            }
                            it5 = it9;
                            map7 = map6;
                        }
                        arrayMap46.put(Integer.valueOf(intValue2), arrayMap36);
                    } else {
                        it5 = it9;
                        map7 = map6;
                        arrayMap36 = null;
                    }
                    if (bitSet4 == null) {
                        bitSet4 = new BitSet();
                        arrayMap44.put(Integer.valueOf(intValue2), bitSet4);
                        bitSet5 = new BitSet();
                        arrayMap45.put(Integer.valueOf(intValue2), bitSet5);
                    }
                    if (zzi2 != null) {
                        int i3 = 0;
                        while (i3 < (zzi2.zzpw() << 6)) {
                            if (zzjo.zza(zzi2.zzpv(), i3)) {
                                arrayMap42 = arrayMap45;
                                arrayMap41 = arrayMap46;
                                arrayMap40 = arrayMap44;
                                zzab().zzgs().zza("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue2), Integer.valueOf(i3));
                                bitSet5.set(i3);
                                if (zzjo.zza(zzi2.zzpy(), i3)) {
                                    bitSet4.set(i3);
                                    z = true;
                                    if (arrayMap36 != null && !z) {
                                        arrayMap36.remove(Integer.valueOf(i3));
                                    }
                                    i3++;
                                    arrayMap45 = arrayMap42;
                                    arrayMap46 = arrayMap41;
                                    arrayMap44 = arrayMap40;
                                }
                            } else {
                                arrayMap42 = arrayMap45;
                                arrayMap41 = arrayMap46;
                                arrayMap40 = arrayMap44;
                            }
                            z = false;
                            arrayMap36.remove(Integer.valueOf(i3));
                            i3++;
                            arrayMap45 = arrayMap42;
                            arrayMap46 = arrayMap41;
                            arrayMap44 = arrayMap40;
                        }
                        arrayMap39 = arrayMap45;
                        arrayMap38 = arrayMap46;
                        arrayMap37 = arrayMap44;
                    } else {
                        arrayMap39 = arrayMap45;
                        arrayMap38 = arrayMap46;
                        arrayMap37 = arrayMap44;
                    }
                    zzbs.zza.C0003zza zzk = zzbs.zza.zzmc().zzk(false);
                    if (zzd2) {
                        zzk.zza(zzaf.get(Integer.valueOf(intValue2)));
                    } else {
                        zzk.zza(zzi2);
                    }
                    zzbs.zzi.zza zzn = zzbs.zzi.zzqh().zzo(zzjo.zza(bitSet4)).zzn(zzjo.zza(bitSet5));
                    if (zzq) {
                        zzn.zzp(zza(arrayMap36));
                        arrayMap47.put(Integer.valueOf(intValue2), new ArrayMap());
                    }
                    zzk.zza(zzn);
                    arrayMap43.put(Integer.valueOf(intValue2), (zzbs.zza) ((zzey) zzk.zzug()));
                    arrayMap45 = arrayMap39;
                    map6 = map7;
                    arrayMap46 = arrayMap38;
                    it9 = it5;
                    arrayMap44 = arrayMap37;
                }
                arrayMap3 = arrayMap45;
                arrayMap2 = arrayMap46;
                arrayMap = arrayMap44;
            }
            if (list.isEmpty()) {
                ArrayMap arrayMap48 = new ArrayMap();
                long j4 = 0;
                Long l6 = null;
                zzbs.zzc zzc5 = null;
                for (zzbs.zzc zzc6 : list) {
                    String name = zzc6.getName();
                    List<zzbs.zze> zzmj = zzc6.zzmj();
                    zzgw();
                    Long l7 = (Long) zzjo.zzb(zzc6, "_eid");
                    boolean z2 = l7 != null;
                    if (z2 && name.equals("_ep")) {
                        zzgw();
                        String str7 = (String) zzjo.zzb(zzc6, "_en");
                        if (TextUtils.isEmpty(str7)) {
                            zzab().zzgk().zza("Extra parameter without an event name. eventId", l7);
                            hashSet3 = hashSet4;
                        } else {
                            if (zzc5 == null || l6 == null || l7.longValue() != l6.longValue()) {
                                Pair<zzbs.zzc, Long> zza3 = zzgy().zza(str6, l7);
                                if (zza3 == null || zza3.first == null) {
                                    hashSet3 = hashSet4;
                                    zzab().zzgk().zza("Extra parameter without existing main event. eventName, eventId", str7, l7);
                                } else {
                                    zzbs.zzc zzc7 = (zzbs.zzc) zza3.first;
                                    j4 = ((Long) zza3.second).longValue();
                                    zzgw();
                                    zzc5 = zzc7;
                                    l4 = (Long) zzjo.zzb(zzc7, "_eid");
                                }
                            } else {
                                l4 = l6;
                            }
                            j = j4 - 1;
                            if (j <= 0) {
                                zzx zzgy2 = zzgy();
                                zzgy2.zzo();
                                zzgy2.zzab().zzgs().zza("Clearing complex main event info. appId", str6);
                                try {
                                    SQLiteDatabase writableDatabase = zzgy2.getWritableDatabase();
                                    try {
                                        String[] strArr = new String[1];
                                        try {
                                            strArr[0] = str6;
                                            writableDatabase.execSQL("delete from main_event_params where app_id=?", strArr);
                                            hashSet = hashSet4;
                                            zzc2 = zzc6;
                                        } catch (SQLiteException e3) {
                                            e2 = e3;
                                            zzgy2.zzab().zzgk().zza("Error clearing complex main event", e2);
                                            hashSet = hashSet4;
                                            zzc2 = zzc6;
                                            arrayList3 = new ArrayList();
                                            while (r1.hasNext()) {
                                            }
                                            if (arrayList3.isEmpty()) {
                                            }
                                            zzc3 = zzgy().zzc(str6, zzc2.getName());
                                            if (zzc3 != null) {
                                            }
                                            zzgy().zza(zzae);
                                            long j5 = zzae.zzfg;
                                            ArrayMap arrayMap49 = arrayMap23;
                                            map3 = (Map) arrayMap49.get(str3);
                                            if (map3 != null) {
                                            }
                                            it3 = map4.keySet().iterator();
                                            while (it3.hasNext()) {
                                            }
                                            str6 = str4;
                                            arrayMap3 = arrayMap26;
                                            arrayMap = arrayMap21;
                                            arrayMap48 = arrayMap49;
                                            arrayMap47 = arrayMap25;
                                            j4 = j;
                                            zzc5 = zzc;
                                            l6 = l2;
                                            arrayMap2 = arrayMap24;
                                            arrayMap43 = arrayMap22;
                                            hashSet4 = hashSet2;
                                        }
                                    } catch (SQLiteException e4) {
                                        e2 = e4;
                                        zzgy2.zzab().zzgk().zza("Error clearing complex main event", e2);
                                        hashSet = hashSet4;
                                        zzc2 = zzc6;
                                        arrayList3 = new ArrayList();
                                        while (r1.hasNext()) {
                                        }
                                        if (arrayList3.isEmpty()) {
                                        }
                                        zzc3 = zzgy().zzc(str6, zzc2.getName());
                                        if (zzc3 != null) {
                                        }
                                        zzgy().zza(zzae);
                                        long j52 = zzae.zzfg;
                                        ArrayMap arrayMap492 = arrayMap23;
                                        map3 = (Map) arrayMap492.get(str3);
                                        if (map3 != null) {
                                        }
                                        it3 = map4.keySet().iterator();
                                        while (it3.hasNext()) {
                                        }
                                        str6 = str4;
                                        arrayMap3 = arrayMap26;
                                        arrayMap = arrayMap21;
                                        arrayMap48 = arrayMap492;
                                        arrayMap47 = arrayMap25;
                                        j4 = j;
                                        zzc5 = zzc;
                                        l6 = l2;
                                        arrayMap2 = arrayMap24;
                                        arrayMap43 = arrayMap22;
                                        hashSet4 = hashSet2;
                                    }
                                } catch (SQLiteException e5) {
                                    e2 = e5;
                                    zzgy2.zzab().zzgk().zza("Error clearing complex main event", e2);
                                    hashSet = hashSet4;
                                    zzc2 = zzc6;
                                    arrayList3 = new ArrayList();
                                    while (r1.hasNext()) {
                                    }
                                    if (arrayList3.isEmpty()) {
                                    }
                                    zzc3 = zzgy().zzc(str6, zzc2.getName());
                                    if (zzc3 != null) {
                                    }
                                    zzgy().zza(zzae);
                                    long j522 = zzae.zzfg;
                                    ArrayMap arrayMap4922 = arrayMap23;
                                    map3 = (Map) arrayMap4922.get(str3);
                                    if (map3 != null) {
                                    }
                                    it3 = map4.keySet().iterator();
                                    while (it3.hasNext()) {
                                    }
                                    str6 = str4;
                                    arrayMap3 = arrayMap26;
                                    arrayMap = arrayMap21;
                                    arrayMap48 = arrayMap4922;
                                    arrayMap47 = arrayMap25;
                                    j4 = j;
                                    zzc5 = zzc;
                                    l6 = l2;
                                    arrayMap2 = arrayMap24;
                                    arrayMap43 = arrayMap22;
                                    hashSet4 = hashSet2;
                                }
                            } else {
                                hashSet = hashSet4;
                                zzc2 = zzc6;
                                zzgy().zza(str, l7, j, zzc5);
                            }
                            arrayList3 = new ArrayList();
                            for (zzbs.zze zze : zzc5.zzmj()) {
                                zzgw();
                                if (zzjo.zza(zzc2, zze.getName()) == null) {
                                    arrayList3.add(zze);
                                }
                            }
                            if (arrayList3.isEmpty()) {
                                for (zzbs.zze zze2 : zzmj) {
                                    arrayList3.add(zze2);
                                }
                                arrayList2 = arrayList3;
                                zzc = zzc5;
                                str3 = str7;
                                l2 = l4;
                            } else {
                                zzab().zzgn().zza("No unique parameters in main event. eventName", str7);
                                zzc = zzc5;
                                str3 = str7;
                                arrayList2 = zzmj;
                                l2 = l4;
                            }
                        }
                        hashSet4 = hashSet3;
                    } else {
                        hashSet = hashSet4;
                        zzc2 = zzc6;
                        if (z2) {
                            zzgw();
                            Object zzb2 = zzjo.zzb(zzc2, "_epc");
                            if (zzb2 == null) {
                                zzb2 = 0L;
                            }
                            j = ((Long) zzb2).longValue();
                            if (j <= 0) {
                                zzab().zzgn().zza("Complex event with zero extra param count. eventName", name);
                                str5 = name;
                                l3 = l7;
                            } else {
                                str5 = name;
                                l3 = l7;
                                zzgy().zza(str, l7, j, zzc2);
                            }
                            l2 = l3;
                            str3 = str5;
                            zzc = zzc2;
                            arrayList2 = zzmj;
                        } else {
                            l2 = l6;
                            j = j4;
                            zzc = zzc5;
                            str3 = name;
                            arrayList2 = zzmj;
                        }
                    }
                    zzc3 = zzgy().zzc(str6, zzc2.getName());
                    if (zzc3 != null) {
                        zzab().zzgn().zza("Event aggregate wasn't created during raw event logging. appId, event", zzef.zzam(str), zzy().zzaj(str3));
                        if (zzd3) {
                            arrayMap23 = arrayMap48;
                            arrayMap26 = arrayMap3;
                            arrayMap24 = arrayMap2;
                            arrayMap22 = arrayMap43;
                            arrayMap21 = arrayMap;
                            zzc4 = zzc2;
                            hashSet2 = hashSet;
                            str4 = str6;
                            zzae = new zzae(str, zzc2.getName(), 1, 1, 1, zzc4.getTimestampMillis(), 0, null, null, null, null);
                            arrayMap25 = arrayMap47;
                        } else {
                            str4 = str6;
                            arrayMap23 = arrayMap48;
                            arrayMap22 = arrayMap43;
                            zzc4 = zzc2;
                            arrayMap26 = arrayMap3;
                            hashSet2 = hashSet;
                            arrayMap24 = arrayMap2;
                            arrayMap21 = arrayMap;
                            zzae = new zzae(str, zzc4.getName(), 1, 1, zzc4.getTimestampMillis(), 0, null, null, null, null);
                            arrayMap25 = arrayMap47;
                        }
                    } else {
                        str4 = str6;
                        arrayMap23 = arrayMap48;
                        arrayMap22 = arrayMap43;
                        zzc4 = zzc2;
                        arrayMap26 = arrayMap3;
                        hashSet2 = hashSet;
                        arrayMap24 = arrayMap2;
                        arrayMap21 = arrayMap;
                        if (zzd3) {
                            zzae = new zzae(zzc3.zzce, zzc3.name, zzc3.zzfg + 1, zzc3.zzfh + 1, zzc3.zzfi + 1, zzc3.zzfj, zzc3.zzfk, zzc3.zzfl, zzc3.zzfm, zzc3.zzfn, zzc3.zzfo);
                            arrayMap25 = arrayMap47;
                        } else {
                            arrayMap25 = arrayMap47;
                            zzae = new zzae(zzc3.zzce, zzc3.name, zzc3.zzfg + 1, zzc3.zzfh + 1, zzc3.zzfi, zzc3.zzfj, zzc3.zzfk, zzc3.zzfl, zzc3.zzfm, zzc3.zzfn, zzc3.zzfo);
                        }
                    }
                    zzgy().zza(zzae);
                    long j5222 = zzae.zzfg;
                    ArrayMap arrayMap49222 = arrayMap23;
                    map3 = (Map) arrayMap49222.get(str3);
                    if (map3 != null) {
                        Map<Integer, List<zzbk.zza>> zzh = zzgy().zzh(str4, str3);
                        if (zzh == null) {
                            zzh = new ArrayMap<>();
                        }
                        arrayMap49222.put(str3, zzh);
                        map4 = zzh;
                    } else {
                        map4 = map3;
                    }
                    it3 = map4.keySet().iterator();
                    while (it3.hasNext()) {
                        int intValue3 = it3.next().intValue();
                        if (hashSet2.contains(Integer.valueOf(intValue3))) {
                            zzab().zzgs().zza("Skipping failed audience ID", Integer.valueOf(intValue3));
                            hashSet2 = hashSet2;
                        } else {
                            ArrayMap arrayMap50 = arrayMap21;
                            BitSet bitSet6 = (BitSet) arrayMap50.get(Integer.valueOf(intValue3));
                            BitSet bitSet7 = (BitSet) arrayMap26.get(Integer.valueOf(intValue3));
                            if (zzq) {
                                j2 = j5222;
                                arrayMap28 = arrayMap24;
                                arrayMap27 = (Map) arrayMap28.get(Integer.valueOf(intValue3));
                                arrayMap29 = arrayMap25;
                                map5 = (Map) arrayMap29.get(Integer.valueOf(intValue3));
                                bitSet = bitSet6;
                            } else {
                                j2 = j5222;
                                arrayMap29 = arrayMap25;
                                arrayMap28 = arrayMap24;
                                bitSet = bitSet6;
                                map5 = null;
                                arrayMap27 = null;
                            }
                            ArrayMap arrayMap51 = arrayMap22;
                            if (((zzbs.zza) arrayMap51.get(Integer.valueOf(intValue3))) == null) {
                                arrayMap30 = arrayMap49222;
                                arrayMap51.put(Integer.valueOf(intValue3), (zzbs.zza) ((zzey) zzbs.zza.zzmc().zzk(true).zzug()));
                                BitSet bitSet8 = new BitSet();
                                arrayMap50.put(Integer.valueOf(intValue3), bitSet8);
                                bitSet7 = new BitSet();
                                arrayMap26.put(Integer.valueOf(intValue3), bitSet7);
                                if (zzq) {
                                    ArrayMap arrayMap52 = new ArrayMap();
                                    arrayMap28.put(Integer.valueOf(intValue3), arrayMap52);
                                    ArrayMap arrayMap53 = new ArrayMap();
                                    arrayMap29.put(Integer.valueOf(intValue3), arrayMap53);
                                    it4 = it3;
                                    bitSet7 = bitSet7;
                                    arrayMap32 = arrayMap53;
                                    arrayMap31 = arrayMap52;
                                    bitSet2 = bitSet8;
                                } else {
                                    bitSet2 = bitSet8;
                                    arrayMap31 = arrayMap27;
                                    it4 = it3;
                                    arrayMap32 = map5;
                                }
                            } else {
                                arrayMap30 = arrayMap49222;
                                bitSet2 = bitSet;
                                arrayMap31 = arrayMap27;
                                it4 = it3;
                                arrayMap32 = map5;
                            }
                            for (zzbk.zza zza4 : map4.get(Integer.valueOf(intValue3))) {
                                if (!zzd3 || !zzd2) {
                                    bitSet3 = bitSet7;
                                } else if (zza4.zzki()) {
                                    bitSet3 = bitSet7;
                                    j3 = zzae.zzfi;
                                    if (!zzab().isLoggable(2)) {
                                        zzeh zzgs = zzab().zzgs();
                                        arrayMap35 = arrayMap26;
                                        Integer valueOf3 = Integer.valueOf(intValue3);
                                        if (zza4.zzkb()) {
                                            arrayMap33 = arrayMap50;
                                            num2 = Integer.valueOf(zza4.getId());
                                            arrayMap34 = arrayMap29;
                                        } else {
                                            arrayMap33 = arrayMap50;
                                            arrayMap34 = arrayMap29;
                                            num2 = null;
                                        }
                                        zzae2 = zzae;
                                        zzgs.zza("Evaluating filter. audience, filter, event", valueOf3, num2, zzy().zzaj(zza4.zzjz()));
                                        zzab().zzgs().zza("Filter definition", zzgw().zza(zza4));
                                    } else {
                                        arrayMap35 = arrayMap26;
                                        arrayMap33 = arrayMap50;
                                        arrayMap34 = arrayMap29;
                                        zzae2 = zzae;
                                    }
                                    if (zza4.zzkb() || zza4.getId() > 256) {
                                        zzab().zzgn().zza("Invalid event filter ID. appId, id", zzef.zzam(str), String.valueOf(!zza4.zzkb() ? Integer.valueOf(zza4.getId()) : null));
                                        arrayMap50 = arrayMap33;
                                        str4 = str;
                                        arrayMap31 = arrayMap31;
                                        bitSet7 = bitSet3;
                                        arrayMap26 = arrayMap35;
                                        map4 = map4;
                                        arrayMap51 = arrayMap51;
                                        arrayMap29 = arrayMap34;
                                        zzae = zzae2;
                                        arrayMap28 = arrayMap28;
                                    } else if (zzq) {
                                        boolean zzkf = zza4.zzkf();
                                        boolean zzkg = zza4.zzkg();
                                        boolean z3 = zzkf || zzkg || (zzd2 && zza4.zzki());
                                        if (!bitSet2.get(zza4.getId()) || z3) {
                                            Boolean zza5 = zza(zza4, str3, arrayList2, j3);
                                            zzab().zzgs().zza("Event filter result", zza5 == null ? "null" : zza5);
                                            if (zza5 == null) {
                                                hashSet2.add(Integer.valueOf(intValue3));
                                                arrayMap50 = arrayMap33;
                                                str4 = str;
                                                arrayMap31 = arrayMap31;
                                                bitSet7 = bitSet3;
                                                arrayMap26 = arrayMap35;
                                                map4 = map4;
                                                arrayMap51 = arrayMap51;
                                                arrayMap29 = arrayMap34;
                                                zzae = zzae2;
                                                arrayMap28 = arrayMap28;
                                            } else {
                                                bitSet3.set(zza4.getId());
                                                if (zza5.booleanValue()) {
                                                    bitSet2.set(zza4.getId());
                                                    if (!z3 || !zzc4.zzml()) {
                                                        arrayMap50 = arrayMap33;
                                                        str4 = str;
                                                        arrayMap31 = arrayMap31;
                                                        bitSet7 = bitSet3;
                                                        arrayMap26 = arrayMap35;
                                                        map4 = map4;
                                                        arrayMap51 = arrayMap51;
                                                        arrayMap29 = arrayMap34;
                                                        zzae = zzae2;
                                                        arrayMap28 = arrayMap28;
                                                    } else if (zzkg) {
                                                        zzb(arrayMap32, zza4.getId(), zzc4.getTimestampMillis());
                                                        arrayMap50 = arrayMap33;
                                                        str4 = str;
                                                        arrayMap31 = arrayMap31;
                                                        bitSet7 = bitSet3;
                                                        arrayMap26 = arrayMap35;
                                                        map4 = map4;
                                                        arrayMap51 = arrayMap51;
                                                        arrayMap29 = arrayMap34;
                                                        zzae = zzae2;
                                                        arrayMap28 = arrayMap28;
                                                    } else {
                                                        zza(arrayMap31, zza4.getId(), zzc4.getTimestampMillis());
                                                        arrayMap50 = arrayMap33;
                                                        str4 = str;
                                                        arrayMap31 = arrayMap31;
                                                        bitSet7 = bitSet3;
                                                        arrayMap26 = arrayMap35;
                                                        map4 = map4;
                                                        arrayMap51 = arrayMap51;
                                                        arrayMap29 = arrayMap34;
                                                        zzae = zzae2;
                                                        arrayMap28 = arrayMap28;
                                                    }
                                                } else {
                                                    arrayMap50 = arrayMap33;
                                                    str4 = str;
                                                    arrayMap31 = arrayMap31;
                                                    bitSet7 = bitSet3;
                                                    arrayMap26 = arrayMap35;
                                                    map4 = map4;
                                                    arrayMap51 = arrayMap51;
                                                    arrayMap29 = arrayMap34;
                                                    zzae = zzae2;
                                                    arrayMap28 = arrayMap28;
                                                }
                                            }
                                        } else {
                                            zzab().zzgs().zza("Event filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(intValue3), zza4.zzkb() ? Integer.valueOf(zza4.getId()) : null);
                                            bitSet7 = bitSet3;
                                            arrayMap26 = arrayMap35;
                                            arrayMap29 = arrayMap34;
                                            zzae = zzae2;
                                            arrayMap50 = arrayMap33;
                                            str4 = str;
                                        }
                                    } else if (bitSet2.get(zza4.getId())) {
                                        zzab().zzgs().zza("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue3), zza4.zzkb() ? Integer.valueOf(zza4.getId()) : null);
                                        arrayMap50 = arrayMap33;
                                        str4 = str;
                                        arrayMap31 = arrayMap31;
                                        bitSet7 = bitSet3;
                                        arrayMap26 = arrayMap35;
                                        map4 = map4;
                                        arrayMap51 = arrayMap51;
                                        arrayMap29 = arrayMap34;
                                        zzae = zzae2;
                                        arrayMap28 = arrayMap28;
                                    } else {
                                        Boolean zza6 = zza(zza4, str3, arrayList2, j3);
                                        zzab().zzgs().zza("Event filter result", zza6 == null ? "null" : zza6);
                                        if (zza6 == null) {
                                            hashSet2.add(Integer.valueOf(intValue3));
                                            arrayMap50 = arrayMap33;
                                            str4 = str;
                                            arrayMap31 = arrayMap31;
                                            bitSet7 = bitSet3;
                                            arrayMap26 = arrayMap35;
                                            map4 = map4;
                                            arrayMap51 = arrayMap51;
                                            arrayMap29 = arrayMap34;
                                            zzae = zzae2;
                                            arrayMap28 = arrayMap28;
                                        } else {
                                            bitSet3.set(zza4.getId());
                                            if (zza6.booleanValue()) {
                                                bitSet2.set(zza4.getId());
                                            }
                                            arrayMap50 = arrayMap33;
                                            str4 = str;
                                            arrayMap31 = arrayMap31;
                                            bitSet7 = bitSet3;
                                            arrayMap26 = arrayMap35;
                                            map4 = map4;
                                            arrayMap51 = arrayMap51;
                                            arrayMap29 = arrayMap34;
                                            zzae = zzae2;
                                            arrayMap28 = arrayMap28;
                                        }
                                    }
                                } else {
                                    bitSet3 = bitSet7;
                                }
                                j3 = j2;
                                if (!zzab().isLoggable(2)) {
                                }
                                if (zza4.zzkb()) {
                                }
                                zzab().zzgn().zza("Invalid event filter ID. appId, id", zzef.zzam(str), String.valueOf(!zza4.zzkb() ? Integer.valueOf(zza4.getId()) : null));
                                arrayMap50 = arrayMap33;
                                str4 = str;
                                arrayMap31 = arrayMap31;
                                bitSet7 = bitSet3;
                                arrayMap26 = arrayMap35;
                                map4 = map4;
                                arrayMap51 = arrayMap51;
                                arrayMap29 = arrayMap34;
                                zzae = zzae2;
                                arrayMap28 = arrayMap28;
                            }
                            arrayMap21 = arrayMap50;
                            arrayMap25 = arrayMap29;
                            arrayMap24 = arrayMap28;
                            arrayMap22 = arrayMap51;
                            hashSet2 = hashSet2;
                            j5222 = j2;
                            it3 = it4;
                            arrayMap49222 = arrayMap30;
                        }
                    }
                    str6 = str4;
                    arrayMap3 = arrayMap26;
                    arrayMap = arrayMap21;
                    arrayMap48 = arrayMap49222;
                    arrayMap47 = arrayMap25;
                    j4 = j;
                    zzc5 = zzc;
                    l6 = l2;
                    arrayMap2 = arrayMap24;
                    arrayMap43 = arrayMap22;
                    hashSet4 = hashSet2;
                }
                str2 = str6;
                arrayMap5 = arrayMap47;
                arrayMap6 = arrayMap43;
                arrayMap7 = arrayMap3;
                arrayMap4 = arrayMap2;
                arrayMap8 = arrayMap;
            } else {
                str2 = str6;
                arrayMap5 = arrayMap47;
                arrayMap6 = arrayMap43;
                arrayMap7 = arrayMap3;
                arrayMap4 = arrayMap2;
                arrayMap8 = arrayMap;
            }
            if (list2.isEmpty()) {
                ArrayMap arrayMap54 = new ArrayMap();
                Iterator<zzbs.zzk> it10 = list2.iterator();
                while (it10.hasNext()) {
                    zzbs.zzk next2 = it10.next();
                    Map<Integer, List<zzbk.zzd>> map9 = (Map) arrayMap54.get(next2.getName());
                    if (map9 == null) {
                        map9 = zzgy().zzi(str2, next2.getName());
                        if (map9 == null) {
                            map9 = new ArrayMap<>();
                        }
                        arrayMap54.put(next2.getName(), map9);
                    }
                    Iterator<Integer> it11 = map9.keySet().iterator();
                    while (it11.hasNext()) {
                        int intValue4 = it11.next().intValue();
                        if (!hashSet4.contains(Integer.valueOf(intValue4))) {
                            BitSet bitSet9 = (BitSet) arrayMap8.get(Integer.valueOf(intValue4));
                            BitSet bitSet10 = (BitSet) arrayMap7.get(Integer.valueOf(intValue4));
                            if (zzq) {
                                arrayMap17 = arrayMap4;
                                map2 = (Map) arrayMap17.get(Integer.valueOf(intValue4));
                                arrayMap16 = arrayMap5;
                                map = (Map) arrayMap16.get(Integer.valueOf(intValue4));
                                arrayMap15 = arrayMap54;
                            } else {
                                arrayMap16 = arrayMap5;
                                arrayMap17 = arrayMap4;
                                arrayMap15 = arrayMap54;
                                map2 = null;
                                map = null;
                            }
                            ArrayMap arrayMap55 = arrayMap6;
                            if (((zzbs.zza) arrayMap55.get(Integer.valueOf(intValue4))) == null) {
                                arrayMap55.put(Integer.valueOf(intValue4), (zzbs.zza) ((zzey) zzbs.zza.zzmc().zzk(true).zzug()));
                                bitSet9 = new BitSet();
                                arrayMap8.put(Integer.valueOf(intValue4), bitSet9);
                                bitSet10 = new BitSet();
                                arrayMap7.put(Integer.valueOf(intValue4), bitSet10);
                                if (zzq) {
                                    map2 = new ArrayMap();
                                    arrayMap17.put(Integer.valueOf(intValue4), map2);
                                    map = new ArrayMap();
                                    arrayMap16.put(Integer.valueOf(intValue4), map);
                                }
                            }
                            Iterator<zzbk.zzd> it12 = map9.get(Integer.valueOf(intValue4)).iterator();
                            while (true) {
                                if (!it12.hasNext()) {
                                    arrayMap54 = arrayMap15;
                                    arrayMap6 = arrayMap55;
                                    arrayMap5 = arrayMap16;
                                    arrayMap4 = arrayMap17;
                                    it10 = it10;
                                    break;
                                }
                                zzbk.zzd next3 = it12.next();
                                if (zzab().isLoggable(2)) {
                                    zzeh zzgs2 = zzab().zzgs();
                                    arrayMap18 = arrayMap8;
                                    Integer valueOf4 = Integer.valueOf(intValue4);
                                    if (next3.zzkb()) {
                                        arrayMap5 = arrayMap16;
                                        num = Integer.valueOf(next3.getId());
                                    } else {
                                        arrayMap5 = arrayMap16;
                                        num = null;
                                    }
                                    arrayMap19 = arrayMap17;
                                    zzgs2.zza("Evaluating filter. audience, filter, property", valueOf4, num, zzy().zzal(next3.getPropertyName()));
                                    zzab().zzgs().zza("Filter definition", zzgw().zza(next3));
                                } else {
                                    arrayMap18 = arrayMap8;
                                    arrayMap19 = arrayMap17;
                                    arrayMap5 = arrayMap16;
                                }
                                if (!next3.zzkb() || next3.getId() > 256) {
                                    zzeh zzgn = zzab().zzgn();
                                    Object zzam = zzef.zzam(str);
                                } else if (zzq) {
                                    boolean zzkf2 = next3.zzkf();
                                    boolean zzkg2 = next3.zzkg();
                                    boolean z4 = zzd2 && next3.zzki();
                                    boolean z5 = zzkf2 || zzkg2 || z4;
                                    if (!bitSet9.get(next3.getId()) || z5) {
                                        Boolean zza7 = zza(next3, next2);
                                        zzeh zzgs3 = zzab().zzgs();
                                        if (zza7 == null) {
                                            arrayMap20 = arrayMap55;
                                            obj = "null";
                                        } else {
                                            arrayMap20 = arrayMap55;
                                            obj = zza7;
                                        }
                                        zzgs3.zza("Property filter result", obj);
                                        if (zza7 == null) {
                                            hashSet4.add(Integer.valueOf(intValue4));
                                            arrayMap8 = arrayMap18;
                                            map9 = map9;
                                            it12 = it12;
                                            it11 = it11;
                                            arrayMap7 = arrayMap7;
                                            arrayMap16 = arrayMap5;
                                            arrayMap17 = arrayMap19;
                                            arrayMap55 = arrayMap20;
                                        } else {
                                            bitSet10.set(next3.getId());
                                            if (!zzd2 || !z4 || zza7.booleanValue()) {
                                                if (!zzd) {
                                                    bitSet9.set(next3.getId(), zza7.booleanValue());
                                                } else if (!bitSet9.get(next3.getId()) || next3.zzkf()) {
                                                    bitSet9.set(next3.getId(), zza7.booleanValue());
                                                }
                                                if (!zza7.booleanValue() || !z5) {
                                                    arrayMap8 = arrayMap18;
                                                    map9 = map9;
                                                    it12 = it12;
                                                    it11 = it11;
                                                    arrayMap7 = arrayMap7;
                                                    arrayMap16 = arrayMap5;
                                                    arrayMap17 = arrayMap19;
                                                    arrayMap55 = arrayMap20;
                                                } else if (next2.zzqs()) {
                                                    long zzqt = next2.zzqt();
                                                    if (zzd2 && z4 && l != null) {
                                                        zzqt = l.longValue();
                                                    }
                                                    if (zzkg2) {
                                                        zzb(map, next3.getId(), zzqt);
                                                        arrayMap8 = arrayMap18;
                                                        map9 = map9;
                                                        it12 = it12;
                                                        it11 = it11;
                                                        arrayMap7 = arrayMap7;
                                                        arrayMap16 = arrayMap5;
                                                        arrayMap17 = arrayMap19;
                                                        arrayMap55 = arrayMap20;
                                                    } else {
                                                        zza(map2, next3.getId(), zzqt);
                                                        arrayMap8 = arrayMap18;
                                                        map9 = map9;
                                                        it12 = it12;
                                                        it11 = it11;
                                                        arrayMap7 = arrayMap7;
                                                        arrayMap16 = arrayMap5;
                                                        arrayMap17 = arrayMap19;
                                                        arrayMap55 = arrayMap20;
                                                    }
                                                } else {
                                                    arrayMap8 = arrayMap18;
                                                    map9 = map9;
                                                    it12 = it12;
                                                    it11 = it11;
                                                    arrayMap7 = arrayMap7;
                                                    arrayMap16 = arrayMap5;
                                                    arrayMap17 = arrayMap19;
                                                    arrayMap55 = arrayMap20;
                                                }
                                            } else {
                                                arrayMap8 = arrayMap18;
                                                map9 = map9;
                                                it12 = it12;
                                                it11 = it11;
                                                arrayMap7 = arrayMap7;
                                                arrayMap16 = arrayMap5;
                                                arrayMap17 = arrayMap19;
                                                arrayMap55 = arrayMap20;
                                            }
                                        }
                                    } else {
                                        zzab().zzgs().zza("Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(intValue4), next3.zzkb() ? Integer.valueOf(next3.getId()) : null);
                                        map9 = map9;
                                        it12 = it12;
                                        it11 = it11;
                                        arrayMap16 = arrayMap5;
                                        arrayMap17 = arrayMap19;
                                        arrayMap8 = arrayMap18;
                                    }
                                } else if (bitSet9.get(next3.getId())) {
                                    zzab().zzgs().zza("Property filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue4), next3.zzkb() ? Integer.valueOf(next3.getId()) : null);
                                    arrayMap8 = arrayMap18;
                                    map9 = map9;
                                    it12 = it12;
                                    it11 = it11;
                                    arrayMap7 = arrayMap7;
                                    arrayMap16 = arrayMap5;
                                    arrayMap17 = arrayMap19;
                                    arrayMap55 = arrayMap55;
                                } else {
                                    Boolean zza8 = zza(next3, next2);
                                    zzab().zzgs().zza("Property filter result", zza8 == null ? "null" : zza8);
                                    if (zza8 == null) {
                                        hashSet4.add(Integer.valueOf(intValue4));
                                        arrayMap8 = arrayMap18;
                                        map9 = map9;
                                        it12 = it12;
                                        it11 = it11;
                                        arrayMap7 = arrayMap7;
                                        arrayMap16 = arrayMap5;
                                        arrayMap17 = arrayMap19;
                                        arrayMap55 = arrayMap55;
                                    } else {
                                        bitSet10.set(next3.getId());
                                        if (zza8.booleanValue()) {
                                            bitSet9.set(next3.getId());
                                        }
                                        arrayMap8 = arrayMap18;
                                        map9 = map9;
                                        it12 = it12;
                                        it11 = it11;
                                        arrayMap7 = arrayMap7;
                                        arrayMap16 = arrayMap5;
                                        arrayMap17 = arrayMap19;
                                        arrayMap55 = arrayMap55;
                                    }
                                }
                            }
                        } else {
                            zzab().zzgs().zza("Skipping failed audience ID", Integer.valueOf(intValue4));
                        }
                    }
                    str2 = str;
                }
                arrayMap12 = arrayMap8;
                arrayMap9 = arrayMap6;
                arrayMap10 = arrayMap4;
                arrayMap11 = arrayMap7;
            } else {
                arrayMap12 = arrayMap8;
                arrayMap9 = arrayMap6;
                arrayMap10 = arrayMap4;
                arrayMap11 = arrayMap7;
            }
            ArrayList arrayList4 = new ArrayList();
            it = arrayMap12.keySet().iterator();
            while (it.hasNext()) {
                int intValue5 = ((Integer) it.next()).intValue();
                if (!hashSet4.contains(Integer.valueOf(intValue5))) {
                    zzbs.zza zza9 = (zzbs.zza) arrayMap9.get(Integer.valueOf(intValue5));
                    if (zza9 == null) {
                        zza = zzbs.zza.zzmc();
                    } else {
                        zza = (zzbs.zza.C0003zza) zza9.zzuj();
                    }
                    zza.zzi(intValue5);
                    ArrayMap arrayMap56 = arrayMap11;
                    zzbs.zzi.zza zzn2 = zzbs.zzi.zzqh().zzo(zzjo.zza((BitSet) arrayMap12.get(Integer.valueOf(intValue5)))).zzn(zzjo.zza((BitSet) arrayMap56.get(Integer.valueOf(intValue5))));
                    if (zzq) {
                        ArrayMap arrayMap57 = arrayMap10;
                        zzn2.zzp(zza((Map) arrayMap57.get(Integer.valueOf(intValue5))));
                        ArrayMap arrayMap58 = arrayMap5;
                        Map map10 = (Map) arrayMap58.get(Integer.valueOf(intValue5));
                        if (map10 == null) {
                            arrayList = Collections.emptyList();
                            it2 = it;
                            arrayMap13 = arrayMap56;
                        } else {
                            ArrayList arrayList5 = new ArrayList(map10.size());
                            for (Integer num3 : map10.keySet()) {
                                zzbs.zzj.zza zzal = zzbs.zzj.zzqo().zzal(num3.intValue());
                                List list4 = (List) map10.get(num3);
                                if (list4 != null) {
                                    Collections.sort(list4);
                                    for (Iterator it13 = list4.iterator(); it13.hasNext(); it13 = it13) {
                                        zzal.zzbj(((Long) it13.next()).longValue());
                                        map10 = map10;
                                    }
                                }
                                arrayList5.add((zzbs.zzj) ((zzey) zzal.zzug()));
                                it = it;
                                map10 = map10;
                                arrayMap56 = arrayMap56;
                            }
                            it2 = it;
                            arrayMap13 = arrayMap56;
                            arrayList = arrayList5;
                        }
                        if (!zzd || !zza.zzlw()) {
                            arrayMap10 = arrayMap57;
                            arrayMap5 = arrayMap58;
                        } else {
                            List<zzbs.zzj> zzqe = zza.zzlx().zzqe();
                            if (zzqe.isEmpty()) {
                                arrayMap10 = arrayMap57;
                                arrayMap5 = arrayMap58;
                            } else {
                                ArrayList arrayList6 = new ArrayList(arrayList);
                                ArrayMap arrayMap59 = new ArrayMap();
                                for (zzbs.zzj zzj : zzqe) {
                                    if (zzj.zzme()) {
                                        if (zzj.zzql() > 0) {
                                            arrayMap59.put(Integer.valueOf(zzj.getIndex()), Long.valueOf(zzj.zzai(zzj.zzql() - 1)));
                                        }
                                    }
                                }
                                int i4 = 0;
                                while (i4 < arrayList6.size()) {
                                    zzbs.zzj zzj2 = (zzbs.zzj) arrayList6.get(i4);
                                    Long l8 = (Long) arrayMap59.remove(zzj2.zzme() ? Integer.valueOf(zzj2.getIndex()) : null);
                                    if (l8 != null) {
                                        ArrayList arrayList7 = new ArrayList();
                                        arrayMap14 = arrayMap57;
                                        if (l8.longValue() < zzj2.zzai(0)) {
                                            arrayList7.add(l8);
                                        }
                                        arrayList7.addAll(zzj2.zzqk());
                                        arrayList6.set(i4, (zzbs.zzj) ((zzey) ((zzbs.zzj.zza) zzj2.zzuj()).zzqw().zzr(arrayList7).zzug()));
                                    } else {
                                        arrayMap14 = arrayMap57;
                                    }
                                    i4++;
                                    arrayMap57 = arrayMap14;
                                }
                                arrayMap10 = arrayMap57;
                                for (Integer num4 : arrayMap59.keySet()) {
                                    arrayList6.add((zzbs.zzj) ((zzey) zzbs.zzj.zzqo().zzal(num4.intValue()).zzbj(((Long) arrayMap59.get(num4)).longValue()).zzug()));
                                    arrayMap58 = arrayMap58;
                                }
                                arrayMap5 = arrayMap58;
                                arrayList = arrayList6;
                            }
                        }
                        zzn2.zzq(arrayList);
                    } else {
                        it2 = it;
                        arrayMap13 = arrayMap56;
                    }
                    zza.zza(zzn2);
                    arrayMap9.put(Integer.valueOf(intValue5), (zzbs.zza) ((zzey) zza.zzug()));
                    arrayList4.add((zzbs.zza) ((zzey) zza.zzug()));
                    zzx zzgy3 = zzgy();
                    zzbs.zzi zzlv = zza.zzlv();
                    zzgy3.zzbi();
                    zzgy3.zzo();
                    Preconditions.checkNotEmpty(str);
                    Preconditions.checkNotNull(zzlv);
                    byte[] byteArray = zzlv.toByteArray();
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("app_id", str);
                    contentValues2.put("audience_id", Integer.valueOf(intValue5));
                    contentValues2.put("current_results", byteArray);
                    try {
                        try {
                            if (zzgy3.getWritableDatabase().insertWithOnConflict("audience_filter_values", null, contentValues2, 5) == -1) {
                                zzgy3.zzab().zzgk().zza("Failed to insert filter results (got -1). appId", zzef.zzam(str));
                            }
                            it = it2;
                            arrayMap9 = arrayMap9;
                            arrayMap11 = arrayMap13;
                        } catch (SQLiteException e6) {
                            e = e6;
                            zzgy3.zzab().zzgk().zza("Error storing filter results. appId", zzef.zzam(str), e);
                            it = it2;
                            arrayMap9 = arrayMap9;
                            arrayMap11 = arrayMap13;
                        }
                    } catch (SQLiteException e7) {
                        e = e7;
                        zzgy3.zzab().zzgk().zza("Error storing filter results. appId", zzef.zzam(str), e);
                        it = it2;
                        arrayMap9 = arrayMap9;
                        arrayMap11 = arrayMap13;
                    }
                }
            }
            return arrayList4;
        }
        l = null;
        zzgy = zzgy();
        zzgy.zzbi();
        zzgy.zzo();
        Preconditions.checkNotEmpty(str);
        ContentValues contentValues3 = new ContentValues();
        contentValues3.put("current_session_count", (Integer) 0);
        try {
            zzgy.getWritableDatabase().update("events", contentValues3, "app_id = ?", new String[]{str6});
        } catch (SQLiteException e8) {
            zzgy.zzab().zzgk().zza("Error resetting session-scoped event counts. appId", zzef.zzam(str), e8);
        }
        zzaf = zzgy().zzaf(str6);
        if (zzaf != null) {
        }
        arrayMap3 = arrayMap45;
        arrayMap2 = arrayMap46;
        arrayMap = arrayMap44;
        if (list.isEmpty()) {
        }
        if (list2.isEmpty()) {
        }
        ArrayList arrayList42 = new ArrayList();
        it = arrayMap12.keySet().iterator();
        while (it.hasNext()) {
        }
        return arrayList42;
    }

    private final Boolean zza(zzbk.zza zza, String str, List<zzbs.zze> list, long j) {
        Boolean bool;
        if (zza.zzkd()) {
            Boolean zza2 = zza(j, zza.zzke());
            if (zza2 == null) {
                return null;
            }
            if (!zza2.booleanValue()) {
                return false;
            }
        }
        HashSet hashSet = new HashSet();
        for (zzbk.zzb zzb : zza.zzkc()) {
            if (zzb.zzkr().isEmpty()) {
                zzab().zzgn().zza("null or empty param name in filter. event", zzy().zzaj(str));
                return null;
            }
            hashSet.add(zzb.zzkr());
        }
        ArrayMap arrayMap = new ArrayMap();
        for (zzbs.zze zze : list) {
            if (hashSet.contains(zze.getName())) {
                if (zze.zzna()) {
                    arrayMap.put(zze.getName(), zze.zzna() ? Long.valueOf(zze.zznb()) : null);
                } else if (zze.zznd()) {
                    arrayMap.put(zze.getName(), zze.zznd() ? Double.valueOf(zze.zzne()) : null);
                } else if (zze.zzmx()) {
                    arrayMap.put(zze.getName(), zze.zzmy());
                } else {
                    zzab().zzgn().zza("Unknown value for param. event, param", zzy().zzaj(str), zzy().zzak(zze.getName()));
                    return null;
                }
            }
        }
        Iterator<zzbk.zzb> it = zza.zzkc().iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                return true;
            }
            zzbk.zzb next = it.next();
            if (!next.zzkp() || !next.zzkq()) {
                z = false;
            }
            String zzkr = next.zzkr();
            if (zzkr.isEmpty()) {
                zzab().zzgn().zza("Event has empty param name. event", zzy().zzaj(str));
                return null;
            }
            Object obj = arrayMap.get(zzkr);
            if (obj instanceof Long) {
                if (!next.zzkn()) {
                    zzab().zzgn().zza("No number filter for long param. event, param", zzy().zzaj(str), zzy().zzak(zzkr));
                    return null;
                }
                Boolean zza3 = zza(((Long) obj).longValue(), next.zzko());
                if (zza3 == null) {
                    return null;
                }
                if (zza3.booleanValue() == z) {
                    return false;
                }
            } else if (obj instanceof Double) {
                if (!next.zzkn()) {
                    zzab().zzgn().zza("No number filter for double param. event, param", zzy().zzaj(str), zzy().zzak(zzkr));
                    return null;
                }
                Boolean zza4 = zza(((Double) obj).doubleValue(), next.zzko());
                if (zza4 == null) {
                    return null;
                }
                if (zza4.booleanValue() == z) {
                    return false;
                }
            } else if (obj instanceof String) {
                if (next.zzkl()) {
                    bool = zza((String) obj, next.zzkm());
                } else if (next.zzkn()) {
                    String str2 = (String) obj;
                    if (zzjo.zzbj(str2)) {
                        bool = zza(str2, next.zzko());
                    } else {
                        zzab().zzgn().zza("Invalid param value for number filter. event, param", zzy().zzaj(str), zzy().zzak(zzkr));
                        return null;
                    }
                } else {
                    zzab().zzgn().zza("No filter for String param. event, param", zzy().zzaj(str), zzy().zzak(zzkr));
                    return null;
                }
                if (bool == null) {
                    return null;
                }
                if (bool.booleanValue() == z) {
                    return false;
                }
            } else if (obj == null) {
                zzab().zzgs().zza("Missing param for filter. event, param", zzy().zzaj(str), zzy().zzak(zzkr));
                return false;
            } else {
                zzab().zzgn().zza("Unknown param type. event, param", zzy().zzaj(str), zzy().zzak(zzkr));
                return null;
            }
        }
    }

    private final Boolean zza(zzbk.zzd zzd, zzbs.zzk zzk) {
        zzbk.zzb zzli = zzd.zzli();
        boolean zzkq = zzli.zzkq();
        if (zzk.zzna()) {
            if (zzli.zzkn()) {
                return zza(zza(zzk.zznb(), zzli.zzko()), zzkq);
            }
            zzab().zzgn().zza("No number filter for long property. property", zzy().zzal(zzk.getName()));
            return null;
        } else if (zzk.zznd()) {
            if (zzli.zzkn()) {
                return zza(zza(zzk.zzne(), zzli.zzko()), zzkq);
            }
            zzab().zzgn().zza("No number filter for double property. property", zzy().zzal(zzk.getName()));
            return null;
        } else if (!zzk.zzmx()) {
            zzab().zzgn().zza("User property has no value, property", zzy().zzal(zzk.getName()));
            return null;
        } else if (zzli.zzkl()) {
            return zza(zza(zzk.zzmy(), zzli.zzkm()), zzkq);
        } else {
            if (!zzli.zzkn()) {
                zzab().zzgn().zza("No string or number filter defined. property", zzy().zzal(zzk.getName()));
            } else if (zzjo.zzbj(zzk.zzmy())) {
                return zza(zza(zzk.zzmy(), zzli.zzko()), zzkq);
            } else {
                zzab().zzgn().zza("Invalid user property value for Numeric number filter. property, value", zzy().zzal(zzk.getName()), zzk.zzmy());
            }
            return null;
        }
    }

    @VisibleForTesting
    private static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() != z);
    }

    @VisibleForTesting
    private final Boolean zza(String str, zzbk.zze zze) {
        String str2;
        List<String> list;
        Preconditions.checkNotNull(zze);
        if (str == null || !zze.zzlk() || zze.zzll() == zzbk.zze.zza.UNKNOWN_MATCH_TYPE) {
            return null;
        }
        if (zze.zzll() == zzbk.zze.zza.IN_LIST) {
            if (zze.zzlr() == 0) {
                return null;
            }
        } else if (!zze.zzlm()) {
            return null;
        }
        zzbk.zze.zza zzll = zze.zzll();
        boolean zzlp = zze.zzlp();
        if (zzlp || zzll == zzbk.zze.zza.REGEXP || zzll == zzbk.zze.zza.IN_LIST) {
            str2 = zze.zzln();
        } else {
            str2 = zze.zzln().toUpperCase(Locale.ENGLISH);
        }
        if (zze.zzlr() == 0) {
            list = null;
        } else {
            List<String> zzlq = zze.zzlq();
            if (zzlp) {
                list = zzlq;
            } else {
                ArrayList arrayList = new ArrayList(zzlq.size());
                for (String str3 : zzlq) {
                    arrayList.add(str3.toUpperCase(Locale.ENGLISH));
                }
                list = Collections.unmodifiableList(arrayList);
            }
        }
        return zza(str, zzll, zzlp, str2, list, zzll == zzbk.zze.zza.REGEXP ? str2 : null);
    }

    private final Boolean zza(String str, zzbk.zze.zza zza, boolean z, String str2, List<String> list, String str3) {
        if (str == null) {
            return null;
        }
        if (zza == zzbk.zze.zza.IN_LIST) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && zza != zzbk.zze.zza.REGEXP) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (zzo.zzdu[zza.ordinal()]) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException unused) {
                    zzab().zzgn().zza("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    private final Boolean zza(long j, zzbk.zzc zzc) {
        try {
            return zza(new BigDecimal(j), zzc, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(double d, zzbk.zzc zzc) {
        try {
            return zza(new BigDecimal(d), zzc, Math.ulp(d));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(String str, zzbk.zzc zzc) {
        if (!zzjo.zzbj(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzc, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0086, code lost:
        if (r2 != null) goto L_0x0088;
     */
    @VisibleForTesting
    private static Boolean zza(BigDecimal bigDecimal, zzbk.zzc zzc, double d) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        Preconditions.checkNotNull(zzc);
        if (!zzc.zzku() || zzc.zzkv() == zzbk.zzc.zzb.UNKNOWN_COMPARISON_TYPE) {
            return null;
        }
        if (zzc.zzkv() == zzbk.zzc.zzb.BETWEEN) {
            if (!zzc.zzla() || !zzc.zzlc()) {
                return null;
            }
        } else if (!zzc.zzky()) {
            return null;
        }
        zzbk.zzc.zzb zzkv = zzc.zzkv();
        if (zzc.zzkv() == zzbk.zzc.zzb.BETWEEN) {
            if (!zzjo.zzbj(zzc.zzlb()) || !zzjo.zzbj(zzc.zzld())) {
                return null;
            }
            try {
                BigDecimal bigDecimal5 = new BigDecimal(zzc.zzlb());
                bigDecimal3 = new BigDecimal(zzc.zzld());
                bigDecimal2 = bigDecimal5;
                bigDecimal4 = null;
            } catch (NumberFormatException unused) {
                return null;
            }
        } else if (!zzjo.zzbj(zzc.zzkz())) {
            return null;
        } else {
            try {
                bigDecimal4 = new BigDecimal(zzc.zzkz());
                bigDecimal2 = null;
                bigDecimal3 = null;
            } catch (NumberFormatException unused2) {
                return null;
            }
        }
        if (zzkv == zzbk.zzc.zzb.BETWEEN) {
            if (bigDecimal2 == null) {
                return null;
            }
        }
        boolean z = false;
        switch (zzo.zzdv[zzkv.ordinal()]) {
            case 1:
                if (bigDecimal.compareTo(bigDecimal4) == -1) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 2:
                if (bigDecimal.compareTo(bigDecimal4) == 1) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 3:
                if (d != 0.0d) {
                    if (bigDecimal.compareTo(bigDecimal4.subtract(new BigDecimal(d).multiply(new BigDecimal(2)))) == 1 && bigDecimal.compareTo(bigDecimal4.add(new BigDecimal(d).multiply(new BigDecimal(2)))) == -1) {
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
                if (bigDecimal.compareTo(bigDecimal4) == 0) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 4:
                if (!(bigDecimal.compareTo(bigDecimal2) == -1 || bigDecimal.compareTo(bigDecimal3) == 1)) {
                    z = true;
                }
                return Boolean.valueOf(z);
        }
        return null;
    }

    private static List<zzbs.zzb> zza(Map<Integer, Long> map) {
        if (map == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(map.size());
        for (Integer num : map.keySet()) {
            int intValue = num.intValue();
            arrayList.add((zzbs.zzb) ((zzey) zzbs.zzb.zzmh().zzk(intValue).zzae(map.get(Integer.valueOf(intValue)).longValue()).zzug()));
        }
        return arrayList;
    }

    private static void zza(Map<Integer, Long> map, int i, long j) {
        Long l = map.get(Integer.valueOf(i));
        long j2 = j / 1000;
        if (l == null || j2 > l.longValue()) {
            map.put(Integer.valueOf(i), Long.valueOf(j2));
        }
    }

    private static void zzb(Map<Integer, List<Long>> map, int i, long j) {
        List<Long> list = map.get(Integer.valueOf(i));
        if (list == null) {
            list = new ArrayList<>();
            map.put(Integer.valueOf(i), list);
        }
        list.add(Long.valueOf(j / 1000));
    }
}
