package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.work.WorkRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzbw;
import com.google.android.gms.internal.measurement.zzel;
import com.google.android.gms.internal.measurement.zzey;
import com.google.android.gms.internal.measurement.zzx;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.jvm.internal.LongCompanionObject;

public class zzjg implements zzgh {
    private static volatile zzjg zzsn;
    private boolean zzdh;
    private final zzfj zzj;
    private zzfd zzso;
    private zzej zzsp;
    private zzx zzsq;
    private zzem zzsr;
    private zzjc zzss;
    private zzp zzst;
    private final zzjo zzsu;
    private zzhp zzsv;
    private boolean zzsw;
    private boolean zzsx;
    @VisibleForTesting
    private long zzsy;
    private List<Runnable> zzsz;
    private int zzta;
    private int zztb;
    private boolean zztc;
    private boolean zztd;
    private boolean zzte;
    private FileLock zztf;
    private FileChannel zztg;
    private List<Long> zzth;
    private List<Long> zzti;
    private long zztj;

    /* access modifiers changed from: package-private */
    public class zza implements zzz {
        zzbs.zzg zztn;
        List<Long> zzto;
        List<zzbs.zzc> zztp;
        private long zztq;

        private zza() {
        }

        @Override // com.google.android.gms.measurement.internal.zzz
        public final void zzb(zzbs.zzg zzg) {
            Preconditions.checkNotNull(zzg);
            this.zztn = zzg;
        }

        @Override // com.google.android.gms.measurement.internal.zzz
        public final boolean zza(long j, zzbs.zzc zzc) {
            Preconditions.checkNotNull(zzc);
            if (this.zztp == null) {
                this.zztp = new ArrayList();
            }
            if (this.zzto == null) {
                this.zzto = new ArrayList();
            }
            if (this.zztp.size() > 0 && zza(this.zztp.get(0)) != zza(zzc)) {
                return false;
            }
            long zzuk = this.zztq + ((long) zzc.zzuk());
            if (zzuk >= ((long) Math.max(0, zzak.zzgn.get(null).intValue()))) {
                return false;
            }
            this.zztq = zzuk;
            this.zztp.add(zzc);
            this.zzto.add(Long.valueOf(j));
            if (this.zztp.size() >= Math.max(1, zzak.zzgo.get(null).intValue())) {
                return false;
            }
            return true;
        }

        private static long zza(zzbs.zzc zzc) {
            return ((zzc.getTimestampMillis() / 1000) / 60) / 60;
        }

        /* synthetic */ zza(zzjg zzjg, zzjj zzjj) {
            this();
        }
    }

    public static zzjg zzm(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzsn == null) {
            synchronized (zzjg.class) {
                if (zzsn == null) {
                    zzsn = new zzjg(new zzjm(context));
                }
            }
        }
        return zzsn;
    }

    private zzjg(zzjm zzjm) {
        this(zzjm, null);
    }

    private zzjg(zzjm zzjm, zzfj zzfj) {
        this.zzdh = false;
        Preconditions.checkNotNull(zzjm);
        this.zzj = zzfj.zza(zzjm.zzob, (zzx) null);
        this.zztj = -1;
        zzjo zzjo = new zzjo(this);
        zzjo.initialize();
        this.zzsu = zzjo;
        zzej zzej = new zzej(this);
        zzej.initialize();
        this.zzsp = zzej;
        zzfd zzfd = new zzfd(this);
        zzfd.initialize();
        this.zzso = zzfd;
        this.zzj.zzaa().zza(new zzjj(this, zzjm));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzjm zzjm) {
        this.zzj.zzaa().zzo();
        zzx zzx = new zzx(this);
        zzx.initialize();
        this.zzsq = zzx;
        this.zzj.zzad().zza(this.zzso);
        zzp zzp = new zzp(this);
        zzp.initialize();
        this.zzst = zzp;
        zzhp zzhp = new zzhp(this);
        zzhp.initialize();
        this.zzsv = zzhp;
        zzjc zzjc = new zzjc(this);
        zzjc.initialize();
        this.zzss = zzjc;
        this.zzsr = new zzem(this);
        if (this.zzta != this.zztb) {
            this.zzj.zzab().zzgk().zza("Not all upload components initialized", Integer.valueOf(this.zzta), Integer.valueOf(this.zztb));
        }
        this.zzdh = true;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void start() {
        this.zzj.zzaa().zzo();
        zzgy().zzca();
        if (this.zzj.zzac().zzlj.get() == 0) {
            this.zzj.zzac().zzlj.set(this.zzj.zzx().currentTimeMillis());
        }
        zzjn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public final zzr zzae() {
        return this.zzj.zzae();
    }

    public final zzs zzad() {
        return this.zzj.zzad();
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public final zzef zzab() {
        return this.zzj.zzab();
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public final zzfc zzaa() {
        return this.zzj.zzaa();
    }

    public final zzfd zzgz() {
        zza(this.zzso);
        return this.zzso;
    }

    public final zzej zzjf() {
        zza(this.zzsp);
        return this.zzsp;
    }

    public final zzx zzgy() {
        zza(this.zzsq);
        return this.zzsq;
    }

    private final zzem zzjg() {
        zzem zzem = this.zzsr;
        if (zzem != null) {
            return zzem;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzjc zzjh() {
        zza(this.zzss);
        return this.zzss;
    }

    public final zzp zzgx() {
        zza(this.zzst);
        return this.zzst;
    }

    public final zzhp zzji() {
        zza(this.zzsv);
        return this.zzsv;
    }

    public final zzjo zzgw() {
        zza(this.zzsu);
        return this.zzsu;
    }

    public final zzed zzy() {
        return this.zzj.zzy();
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public final Context getContext() {
        return this.zzj.getContext();
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public final Clock zzx() {
        return this.zzj.zzx();
    }

    public final zzjs zzz() {
        return this.zzj.zzz();
    }

    @WorkerThread
    private final void zzo() {
        this.zzj.zzaa().zzo();
    }

    /* access modifiers changed from: package-private */
    public final void zzjj() {
        if (!this.zzdh) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    private static void zza(zzjh zzjh) {
        if (zzjh == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (!zzjh.isInitialized()) {
            String valueOf = String.valueOf(zzjh.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public final void zze(zzn zzn) {
        zzo();
        zzjj();
        Preconditions.checkNotEmpty(zzn.packageName);
        zzg(zzn);
    }

    private final long zzjk() {
        long currentTimeMillis = this.zzj.zzx().currentTimeMillis();
        zzeo zzac = this.zzj.zzac();
        zzac.zzbi();
        zzac.zzo();
        long j = zzac.zzln.get();
        if (j == 0) {
            j = 1 + ((long) zzac.zzz().zzjw().nextInt(86400000));
            zzac.zzln.set(j);
        }
        return ((((currentTimeMillis + j) / 1000) / 60) / 60) / 24;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzd(zzai zzai, String str) {
        zzf zzab = zzgy().zzab(str);
        if (zzab == null || TextUtils.isEmpty(zzab.zzal())) {
            this.zzj.zzab().zzgr().zza("No app data available; dropping event", str);
            return;
        }
        Boolean zzc = zzc(zzab);
        if (zzc == null) {
            if (!"_ui".equals(zzai.name)) {
                this.zzj.zzab().zzgn().zza("Could not find package. appId", zzef.zzam(str));
            }
        } else if (!zzc.booleanValue()) {
            this.zzj.zzab().zzgk().zza("App version does not match; dropping event. appId", zzef.zzam(str));
            return;
        }
        zzc(zzai, new zzn(str, zzab.getGmpAppId(), zzab.zzal(), zzab.zzam(), zzab.zzan(), zzab.zzao(), zzab.zzap(), (String) null, zzab.isMeasurementEnabled(), false, zzab.getFirebaseInstanceId(), zzab.zzbd(), 0L, 0, zzab.zzbe(), zzab.zzbf(), false, zzab.zzah(), zzab.zzbg(), zzab.zzaq(), zzab.zzbh()));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(zzai zzai, zzn zzn) {
        List<zzq> list;
        List<zzq> list2;
        List<zzq> list3;
        zzai zzai2 = zzai;
        Preconditions.checkNotNull(zzn);
        Preconditions.checkNotEmpty(zzn.packageName);
        zzo();
        zzjj();
        String str = zzn.packageName;
        long j = zzai2.zzfu;
        if (zzgw().zze(zzai2, zzn)) {
            if (!zzn.zzcq) {
                zzg(zzn);
                return;
            }
            if (this.zzj.zzad().zze(str, zzak.zzix) && zzn.zzcw != null) {
                if (zzn.zzcw.contains(zzai2.name)) {
                    Bundle zzcv = zzai2.zzfq.zzcv();
                    zzcv.putLong("ga_safelisted", 1);
                    zzai2 = new zzai(zzai2.name, new zzah(zzcv), zzai2.origin, zzai2.zzfu);
                } else {
                    this.zzj.zzab().zzgr().zza("Dropping non-safelisted event. appId, event name, origin", str, zzai2.name, zzai2.origin);
                    return;
                }
            }
            zzgy().beginTransaction();
            try {
                zzx zzgy = zzgy();
                Preconditions.checkNotEmpty(str);
                zzgy.zzo();
                zzgy.zzbi();
                if (j < 0) {
                    zzgy.zzab().zzgn().zza("Invalid time querying timed out conditional properties", zzef.zzam(str), Long.valueOf(j));
                    list = Collections.emptyList();
                } else {
                    list = zzgy.zzb("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
                }
                for (zzq zzq : list) {
                    if (zzq != null) {
                        this.zzj.zzab().zzgr().zza("User property timed out", zzq.packageName, this.zzj.zzy().zzal(zzq.zzdw.name), zzq.zzdw.getValue());
                        if (zzq.zzdx != null) {
                            zzd(new zzai(zzq.zzdx, j), zzn);
                        }
                        zzgy().zzg(str, zzq.zzdw.name);
                    }
                }
                zzx zzgy2 = zzgy();
                Preconditions.checkNotEmpty(str);
                zzgy2.zzo();
                zzgy2.zzbi();
                if (j < 0) {
                    zzgy2.zzab().zzgn().zza("Invalid time querying expired conditional properties", zzef.zzam(str), Long.valueOf(j));
                    list2 = Collections.emptyList();
                } else {
                    list2 = zzgy2.zzb("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(list2.size());
                for (zzq zzq2 : list2) {
                    if (zzq2 != null) {
                        this.zzj.zzab().zzgr().zza("User property expired", zzq2.packageName, this.zzj.zzy().zzal(zzq2.zzdw.name), zzq2.zzdw.getValue());
                        zzgy().zzd(str, zzq2.zzdw.name);
                        if (zzq2.zzdz != null) {
                            arrayList.add(zzq2.zzdz);
                        }
                        zzgy().zzg(str, zzq2.zzdw.name);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    zzd(new zzai((zzai) obj, j), zzn);
                }
                zzx zzgy3 = zzgy();
                String str2 = zzai2.name;
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotEmpty(str2);
                zzgy3.zzo();
                zzgy3.zzbi();
                if (j < 0) {
                    zzgy3.zzab().zzgn().zza("Invalid time querying triggered conditional properties", zzef.zzam(str), zzgy3.zzy().zzaj(str2), Long.valueOf(j));
                    list3 = Collections.emptyList();
                } else {
                    list3 = zzgy3.zzb("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
                }
                ArrayList arrayList3 = new ArrayList(list3.size());
                for (zzq zzq3 : list3) {
                    if (zzq3 != null) {
                        zzjn zzjn = zzq3.zzdw;
                        zzjp zzjp = new zzjp(zzq3.packageName, zzq3.origin, zzjn.name, j, zzjn.getValue());
                        if (zzgy().zza(zzjp)) {
                            this.zzj.zzab().zzgr().zza("User property triggered", zzq3.packageName, this.zzj.zzy().zzal(zzjp.name), zzjp.value);
                        } else {
                            this.zzj.zzab().zzgk().zza("Too many active user properties, ignoring", zzef.zzam(zzq3.packageName), this.zzj.zzy().zzal(zzjp.name), zzjp.value);
                        }
                        if (zzq3.zzdy != null) {
                            arrayList3.add(zzq3.zzdy);
                        }
                        zzq3.zzdw = new zzjn(zzjp);
                        zzq3.active = true;
                        zzgy().zza(zzq3);
                    }
                }
                zzd(zzai2, zzn);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i2 = 0;
                while (i2 < size2) {
                    Object obj2 = arrayList4.get(i2);
                    i2++;
                    zzd(new zzai((zzai) obj2, j), zzn);
                }
                zzgy().setTransactionSuccessful();
            } finally {
                zzgy().endTransaction();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:227:0x0855, code lost:
        if (r7.zzej < ((long) r24.zzj.zzad().zzi(r5.zzce))) goto L_0x0859;
     */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x085f  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x088f  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0281  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x02be  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x030d  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x033a  */
    @WorkerThread
    private final void zzd(zzai zzai, zzn zzn) {
        long intValue;
        zzae zzae;
        List<Integer> zzju;
        zzjp zze;
        boolean z;
        long j;
        zzjp zzjp;
        SQLiteException e;
        zzf zzab;
        Preconditions.checkNotNull(zzn);
        Preconditions.checkNotEmpty(zzn.packageName);
        long nanoTime = System.nanoTime();
        zzo();
        zzjj();
        String str = zzn.packageName;
        if (zzgw().zze(zzai, zzn)) {
            if (!zzn.zzcq) {
                zzg(zzn);
                return;
            }
            boolean z2 = false;
            if (zzgz().zzk(str, zzai.name)) {
                this.zzj.zzab().zzgn().zza("Dropping blacklisted event. appId", zzef.zzam(str), this.zzj.zzy().zzaj(zzai.name));
                if (zzgz().zzbc(str) || zzgz().zzbd(str)) {
                    z2 = true;
                }
                if (!z2 && !"_err".equals(zzai.name)) {
                    this.zzj.zzz().zza(str, 11, "_ev", zzai.name, 0);
                }
                if (z2 && (zzab = zzgy().zzab(str)) != null) {
                    if (Math.abs(this.zzj.zzx().currentTimeMillis() - Math.max(zzab.zzat(), zzab.zzas())) > zzak.zzhe.get(null).longValue()) {
                        this.zzj.zzab().zzgr().zzao("Fetching config for blacklisted app");
                        zzb(zzab);
                        return;
                    }
                    return;
                }
                return;
            }
            if (this.zzj.zzab().isLoggable(2)) {
                this.zzj.zzab().zzgs().zza("Logging event", this.zzj.zzy().zzb(zzai));
            }
            zzgy().beginTransaction();
            try {
                zzg(zzn);
                if (!"_iap".equals(zzai.name)) {
                    if (!FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzai.name)) {
                        boolean zzbk = zzjs.zzbk(zzai.name);
                        boolean equals = "_err".equals(zzai.name);
                        zzw zza2 = zzgy().zza(zzjk(), str, true, zzbk, false, equals, false);
                        intValue = zza2.zzeg - ((long) zzak.zzgp.get(null).intValue());
                        if (intValue > 0) {
                            if (intValue % 1000 == 1) {
                                this.zzj.zzab().zzgk().zza("Data loss. Too many events logged. appId, count", zzef.zzam(str), Long.valueOf(zza2.zzeg));
                            }
                            zzgy().setTransactionSuccessful();
                            zzgy().endTransaction();
                            return;
                        }
                        if (zzbk) {
                            long intValue2 = zza2.zzef - ((long) zzak.zzgr.get(null).intValue());
                            if (intValue2 > 0) {
                                if (intValue2 % 1000 == 1) {
                                    this.zzj.zzab().zzgk().zza("Data loss. Too many public events logged. appId, count", zzef.zzam(str), Long.valueOf(zza2.zzef));
                                }
                                this.zzj.zzz().zza(str, 16, "_ev", zzai.name, 0);
                                zzgy().setTransactionSuccessful();
                                zzgy().endTransaction();
                                return;
                            }
                            zza2 = zza2;
                        }
                        if (equals) {
                            long max = zza2.zzei - ((long) Math.max(0, Math.min(1000000, this.zzj.zzad().zzb(zzn.packageName, zzak.zzgq))));
                            if (max > 0) {
                                if (max == 1) {
                                    this.zzj.zzab().zzgk().zza("Too many error events logged. appId, count", zzef.zzam(str), Long.valueOf(zza2.zzei));
                                }
                                zzgy().setTransactionSuccessful();
                                zzgy().endTransaction();
                                return;
                            }
                        }
                        Bundle zzcv = zzai.zzfq.zzcv();
                        this.zzj.zzz().zza(zzcv, "_o", zzai.origin);
                        if (this.zzj.zzz().zzbr(str)) {
                            this.zzj.zzz().zza(zzcv, "_dbg", (Object) 1L);
                            this.zzj.zzz().zza(zzcv, "_r", (Object) 1L);
                        }
                        if ("_s".equals(zzai.name) && this.zzj.zzad().zzw(zzn.packageName) && (zze = zzgy().zze(zzn.packageName, "_sno")) != null && (zze.value instanceof Long)) {
                            this.zzj.zzz().zza(zzcv, "_sno", zze.value);
                        }
                        if ("_s".equals(zzai.name) && this.zzj.zzad().zze(zzn.packageName, zzak.zzif) && !this.zzj.zzad().zzw(zzn.packageName)) {
                            zzc(new zzjn("_sno", 0, null), zzn);
                        }
                        long zzac = zzgy().zzac(str);
                        if (zzac > 0) {
                            this.zzj.zzab().zzgn().zza("Data lost. Too many events stored on disk, deleted. appId", zzef.zzam(str), Long.valueOf(zzac));
                        }
                        zzaf zzaf = new zzaf(this.zzj, zzai.origin, str, zzai.name, zzai.zzfu, 0, zzcv);
                        zzae zzc = zzgy().zzc(str, zzaf.name);
                        if (zzc != null) {
                            zzaf = zzaf.zza(this.zzj, zzc.zzfj);
                            zzae = zzc.zzw(zzaf.timestamp);
                        } else if (zzgy().zzag(str) < 500 || !zzbk) {
                            zzae = new zzae(str, zzaf.name, 0, 0, zzaf.timestamp, 0, null, null, null, null);
                        } else {
                            this.zzj.zzab().zzgk().zza("Too many event names used, ignoring event. appId, name, supported count", zzef.zzam(str), this.zzj.zzy().zzaj(zzaf.name), 500);
                            this.zzj.zzz().zza(str, 8, (String) null, (String) null, 0);
                            zzgy().endTransaction();
                            return;
                        }
                        zzgy().zza(zzae);
                        zzo();
                        zzjj();
                        Preconditions.checkNotNull(zzaf);
                        Preconditions.checkNotNull(zzn);
                        Preconditions.checkNotEmpty(zzaf.zzce);
                        Preconditions.checkArgument(zzaf.zzce.equals(zzn.packageName));
                        boolean z3 = true;
                        zzbs.zzg.zza zzcc = zzbs.zzg.zzpr().zzp(1).zzcc("android");
                        if (!TextUtils.isEmpty(zzn.packageName)) {
                            zzcc.zzch(zzn.packageName);
                        }
                        if (!TextUtils.isEmpty(zzn.zzco)) {
                            zzcc.zzcg(zzn.zzco);
                        }
                        if (!TextUtils.isEmpty(zzn.zzcm)) {
                            zzcc.zzci(zzn.zzcm);
                        }
                        if (zzn.zzcn != -2147483648L) {
                            zzcc.zzv((int) zzn.zzcn);
                        }
                        zzcc.zzas(zzn.zzr);
                        if (!TextUtils.isEmpty(zzn.zzcg)) {
                            zzcc.zzcm(zzn.zzcg);
                        }
                        if (this.zzj.zzad().zza(zzak.zzit)) {
                            if (TextUtils.isEmpty(zzcc.getGmpAppId()) && !TextUtils.isEmpty(zzn.zzcu)) {
                                zzcc.zzcq(zzn.zzcu);
                            }
                        } else if (!TextUtils.isEmpty(zzn.zzcu)) {
                            zzcc.zzcq(zzn.zzcu);
                        }
                        if (zzn.zzcp != 0) {
                            zzcc.zzau(zzn.zzcp);
                        }
                        zzcc.zzax(zzn.zzs);
                        if (this.zzj.zzad().zze(zzn.packageName, zzak.zzin) && (zzju = zzgw().zzju()) != null) {
                            zzcc.zzd(zzju);
                        }
                        Pair<String, Boolean> zzap = this.zzj.zzac().zzap(zzn.packageName);
                        if (zzap == null || TextUtils.isEmpty((CharSequence) zzap.first)) {
                            if (!this.zzj.zzw().zzj(this.zzj.getContext()) && zzn.zzct) {
                                String string = Settings.Secure.getString(this.zzj.getContext().getContentResolver(), "android_id");
                                if (string == null) {
                                    this.zzj.zzab().zzgn().zza("null secure ID. appId", zzef.zzam(zzcc.zzag()));
                                    string = "null";
                                } else if (string.isEmpty()) {
                                    this.zzj.zzab().zzgn().zza("empty secure ID. appId", zzef.zzam(zzcc.zzag()));
                                }
                                zzcc.zzco(string);
                            }
                        } else if (zzn.zzcs) {
                            zzcc.zzcj((String) zzap.first);
                            if (zzap.second != null) {
                                zzcc.zzm(((Boolean) zzap.second).booleanValue());
                            }
                        }
                        this.zzj.zzw().zzbi();
                        zzbs.zzg.zza zzce = zzcc.zzce(Build.MODEL);
                        this.zzj.zzw().zzbi();
                        zzce.zzcd(Build.VERSION.RELEASE).zzt((int) this.zzj.zzw().zzcq()).zzcf(this.zzj.zzw().zzcr()).zzaw(zzn.zzcr);
                        if (this.zzj.isEnabled() && zzs.zzbv()) {
                            zzcc.zzag();
                            if (!TextUtils.isEmpty(null)) {
                                zzcc.zzcp(null);
                            }
                        }
                        zzf zzab2 = zzgy().zzab(zzn.packageName);
                        if (zzab2 == null) {
                            zzab2 = new zzf(this.zzj, zzn.packageName);
                            zzab2.zza(this.zzj.zzz().zzjy());
                            zzab2.zze(zzn.zzci);
                            zzab2.zzb(zzn.zzcg);
                            zzab2.zzd(this.zzj.zzac().zzaq(zzn.packageName));
                            zzab2.zzk(0);
                            zzab2.zze(0);
                            zzab2.zzf(0);
                            zzab2.zzf(zzn.zzcm);
                            zzab2.zzg(zzn.zzcn);
                            zzab2.zzg(zzn.zzco);
                            zzab2.zzh(zzn.zzr);
                            zzab2.zzi(zzn.zzcp);
                            zzab2.setMeasurementEnabled(zzn.zzcq);
                            zzab2.zzt(zzn.zzcr);
                            zzab2.zzj(zzn.zzs);
                            zzgy().zza(zzab2);
                        }
                        if (!TextUtils.isEmpty(zzab2.getAppInstanceId())) {
                            zzcc.zzck(zzab2.getAppInstanceId());
                        }
                        if (!TextUtils.isEmpty(zzab2.getFirebaseInstanceId())) {
                            zzcc.zzcn(zzab2.getFirebaseInstanceId());
                        }
                        List<zzjp> zzaa = zzgy().zzaa(zzn.packageName);
                        for (int i = 0; i < zzaa.size(); i++) {
                            zzbs.zzk.zza zzbk2 = zzbs.zzk.zzqu().zzdb(zzaa.get(i).name).zzbk(zzaa.get(i).zztr);
                            zzgw().zza(zzbk2, zzaa.get(i).value);
                            zzcc.zza(zzbk2);
                        }
                        try {
                            long zza3 = zzgy().zza((zzbs.zzg) ((zzey) zzcc.zzug()));
                            zzx zzgy = zzgy();
                            if (zzaf.zzfq != null) {
                                Iterator<String> it = zzaf.zzfq.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        if ("_r".equals(it.next())) {
                                            break;
                                        }
                                    } else {
                                        boolean zzl = zzgz().zzl(zzaf.zzce, zzaf.name);
                                        zzw zza4 = zzgy().zza(zzjk(), zzaf.zzce, false, false, false, false, false);
                                        if (zzl) {
                                        }
                                    }
                                }
                                if (zzgy.zza(zzaf, zza3, z3)) {
                                    this.zzsy = 0;
                                }
                                zzgy().setTransactionSuccessful();
                                if (this.zzj.zzab().isLoggable(2)) {
                                    this.zzj.zzab().zzgs().zza("Event recorded", this.zzj.zzy().zza(zzaf));
                                }
                                zzgy().endTransaction();
                                zzjn();
                                this.zzj.zzab().zzgs().zza("Background event processing time, ms", Long.valueOf(((System.nanoTime() - nanoTime) + 500000) / 1000000));
                                return;
                            }
                            z3 = false;
                            if (zzgy.zza(zzaf, zza3, z3)) {
                            }
                        } catch (IOException e2) {
                            this.zzj.zzab().zzgk().zza("Data loss. Failed to insert raw event metadata. appId", zzef.zzam(zzcc.zzag()), e2);
                        }
                        zzgy().setTransactionSuccessful();
                        if (this.zzj.zzab().isLoggable(2)) {
                        }
                        zzgy().endTransaction();
                        zzjn();
                        this.zzj.zzab().zzgs().zza("Background event processing time, ms", Long.valueOf(((System.nanoTime() - nanoTime) + 500000) / 1000000));
                        return;
                    }
                }
                String string2 = zzai.zzfq.getString("currency");
                if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzai.name)) {
                    double doubleValue = zzai.zzfq.zzah("value").doubleValue() * 1000000.0d;
                    if (doubleValue == 0.0d) {
                        double longValue = (double) zzai.zzfq.getLong("value").longValue();
                        Double.isNaN(longValue);
                        doubleValue = longValue * 1000000.0d;
                    }
                    if (doubleValue > 9.223372036854776E18d || doubleValue < -9.223372036854776E18d) {
                        this.zzj.zzab().zzgn().zza("Data lost. Currency value is too big. appId", zzef.zzam(str), Double.valueOf(doubleValue));
                        z = false;
                        if (!z) {
                            zzgy().setTransactionSuccessful();
                            return;
                        }
                        boolean zzbk3 = zzjs.zzbk(zzai.name);
                        boolean equals2 = "_err".equals(zzai.name);
                        zzw zza22 = zzgy().zza(zzjk(), str, true, zzbk3, false, equals2, false);
                        intValue = zza22.zzeg - ((long) zzak.zzgp.get(null).intValue());
                        if (intValue > 0) {
                        }
                    } else {
                        j = Math.round(doubleValue);
                    }
                } else {
                    j = zzai.zzfq.getLong("value").longValue();
                }
                if (!TextUtils.isEmpty(string2)) {
                    String upperCase = string2.toUpperCase(Locale.US);
                    if (upperCase.matches("[A-Z]{3}")) {
                        String valueOf = String.valueOf("_ltv_");
                        String valueOf2 = String.valueOf(upperCase);
                        String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                        zzjp zze2 = zzgy().zze(str, concat);
                        if (zze2 == null || !(zze2.value instanceof Long)) {
                            zzx zzgy2 = zzgy();
                            int zzb = this.zzj.zzad().zzb(str, zzak.zzhj) - 1;
                            Preconditions.checkNotEmpty(str);
                            zzgy2.zzo();
                            zzgy2.zzbi();
                            try {
                                SQLiteDatabase writableDatabase = zzgy2.getWritableDatabase();
                                String[] strArr = new String[3];
                                strArr[0] = str;
                                try {
                                    strArr[1] = str;
                                    try {
                                        strArr[2] = String.valueOf(zzb);
                                        writableDatabase.execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", strArr);
                                    } catch (SQLiteException e3) {
                                        e = e3;
                                    }
                                } catch (SQLiteException e4) {
                                    e = e4;
                                    zzgy2.zzab().zzgk().zza("Error pruning currencies. appId", zzef.zzam(str), e);
                                    zzjp = new zzjp(str, zzai.origin, concat, this.zzj.zzx().currentTimeMillis(), Long.valueOf(j));
                                    if (!zzgy().zza(zzjp)) {
                                    }
                                    z = true;
                                    if (!z) {
                                    }
                                    boolean zzbk32 = zzjs.zzbk(zzai.name);
                                    boolean equals22 = "_err".equals(zzai.name);
                                    zzw zza222 = zzgy().zza(zzjk(), str, true, zzbk32, false, equals22, false);
                                    intValue = zza222.zzeg - ((long) zzak.zzgp.get(null).intValue());
                                    if (intValue > 0) {
                                    }
                                }
                            } catch (SQLiteException e5) {
                                e = e5;
                                zzgy2.zzab().zzgk().zza("Error pruning currencies. appId", zzef.zzam(str), e);
                                zzjp = new zzjp(str, zzai.origin, concat, this.zzj.zzx().currentTimeMillis(), Long.valueOf(j));
                                if (!zzgy().zza(zzjp)) {
                                }
                                z = true;
                                if (!z) {
                                }
                                boolean zzbk322 = zzjs.zzbk(zzai.name);
                                boolean equals222 = "_err".equals(zzai.name);
                                zzw zza2222 = zzgy().zza(zzjk(), str, true, zzbk322, false, equals222, false);
                                intValue = zza2222.zzeg - ((long) zzak.zzgp.get(null).intValue());
                                if (intValue > 0) {
                                }
                            }
                            zzjp = new zzjp(str, zzai.origin, concat, this.zzj.zzx().currentTimeMillis(), Long.valueOf(j));
                        } else {
                            zzjp = new zzjp(str, zzai.origin, concat, this.zzj.zzx().currentTimeMillis(), Long.valueOf(((Long) zze2.value).longValue() + j));
                        }
                        if (!zzgy().zza(zzjp)) {
                            this.zzj.zzab().zzgk().zza("Too many unique user properties are set. Ignoring user property. appId", zzef.zzam(str), this.zzj.zzy().zzal(zzjp.name), zzjp.value);
                            this.zzj.zzz().zza(str, 9, (String) null, (String) null, 0);
                        }
                    }
                }
                z = true;
                if (!z) {
                }
                boolean zzbk3222 = zzjs.zzbk(zzai.name);
                boolean equals2222 = "_err".equals(zzai.name);
                zzw zza22222 = zzgy().zza(zzjk(), str, true, zzbk3222, false, equals2222, false);
                intValue = zza22222.zzeg - ((long) zzak.zzgp.get(null).intValue());
                if (intValue > 0) {
                }
            } finally {
                zzgy().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzjl() {
        zzf zzab;
        String str;
        zzo();
        zzjj();
        this.zzte = true;
        try {
            this.zzj.zzae();
            Boolean zzit = this.zzj.zzs().zzit();
            if (zzit == null) {
                this.zzj.zzab().zzgn().zzao("Upload data called on the client side before use of service was decided");
            } else if (zzit.booleanValue()) {
                this.zzj.zzab().zzgk().zzao("Upload called in the client side when service should be used");
                this.zzte = false;
                zzjo();
            } else if (this.zzsy > 0) {
                zzjn();
                this.zzte = false;
                zzjo();
            } else {
                zzo();
                if (this.zzth != null) {
                    this.zzj.zzab().zzgs().zzao("Uploading requested multiple times");
                    this.zzte = false;
                    zzjo();
                } else if (!zzjf().zzgv()) {
                    this.zzj.zzab().zzgs().zzao("Network not connected, ignoring upload request");
                    zzjn();
                    this.zzte = false;
                    zzjo();
                } else {
                    long currentTimeMillis = this.zzj.zzx().currentTimeMillis();
                    zzd((String) null, currentTimeMillis - zzs.zzbt());
                    long j = this.zzj.zzac().zzlj.get();
                    if (j != 0) {
                        this.zzj.zzab().zzgr().zza("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - j)));
                    }
                    String zzby = zzgy().zzby();
                    if (!TextUtils.isEmpty(zzby)) {
                        if (this.zztj == -1) {
                            this.zztj = zzgy().zzcf();
                        }
                        List<Pair<zzbs.zzg, Long>> zza2 = zzgy().zza(zzby, this.zzj.zzad().zzb(zzby, zzak.zzgl), Math.max(0, this.zzj.zzad().zzb(zzby, zzak.zzgm)));
                        if (!zza2.isEmpty()) {
                            Iterator<Pair<zzbs.zzg, Long>> it = zza2.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    str = null;
                                    break;
                                }
                                zzbs.zzg zzg = (zzbs.zzg) it.next().first;
                                if (!TextUtils.isEmpty(zzg.zzot())) {
                                    str = zzg.zzot();
                                    break;
                                }
                            }
                            if (str != null) {
                                int i = 0;
                                while (true) {
                                    if (i >= zza2.size()) {
                                        break;
                                    }
                                    zzbs.zzg zzg2 = (zzbs.zzg) zza2.get(i).first;
                                    if (!(TextUtils.isEmpty(zzg2.zzot()) || zzg2.zzot().equals(str))) {
                                        zza2 = zza2.subList(0, i);
                                        break;
                                    }
                                    i++;
                                }
                            }
                            zzbs.zzf.zza zznj = zzbs.zzf.zznj();
                            int size = zza2.size();
                            ArrayList arrayList = new ArrayList(zza2.size());
                            boolean z = zzs.zzbv() && this.zzj.zzad().zzl(zzby);
                            for (int i2 = 0; i2 < size; i2++) {
                                zzbs.zzg.zza zza3 = (zzbs.zzg.zza) ((zzbs.zzg) zza2.get(i2).first).zzuj();
                                arrayList.add((Long) zza2.get(i2).second);
                                zzbs.zzg.zza zzan = zza3.zzat(this.zzj.zzad().zzao()).zzan(currentTimeMillis);
                                this.zzj.zzae();
                                zzan.zzn(false);
                                if (!z) {
                                    zza3.zznw();
                                }
                                if (this.zzj.zzad().zze(zzby, zzak.zzis)) {
                                    zza3.zzay(zzgw().zza(((zzbs.zzg) ((zzey) zza3.zzug())).toByteArray()));
                                }
                                zznj.zza(zza3);
                            }
                            String zza4 = this.zzj.zzab().isLoggable(2) ? zzgw().zza((zzbs.zzf) ((zzey) zznj.zzug())) : null;
                            zzgw();
                            byte[] byteArray = ((zzbs.zzf) ((zzey) zznj.zzug())).toByteArray();
                            String str2 = zzak.zzgv.get(null);
                            try {
                                URL url = new URL(str2);
                                Preconditions.checkArgument(!arrayList.isEmpty());
                                if (this.zzth != null) {
                                    this.zzj.zzab().zzgk().zzao("Set uploading progress before finishing the previous upload");
                                } else {
                                    this.zzth = new ArrayList(arrayList);
                                }
                                this.zzj.zzac().zzlk.set(currentTimeMillis);
                                String str3 = "?";
                                if (size > 0) {
                                    str3 = zznj.zzo(0).zzag();
                                }
                                this.zzj.zzab().zzgs().zza("Uploading data. app, uncompressed size, data", str3, Integer.valueOf(byteArray.length), zza4);
                                this.zztd = true;
                                zzej zzjf = zzjf();
                                zzji zzji = new zzji(this, zzby);
                                zzjf.zzo();
                                zzjf.zzbi();
                                Preconditions.checkNotNull(url);
                                Preconditions.checkNotNull(byteArray);
                                Preconditions.checkNotNull(zzji);
                                zzjf.zzaa().zzb(new zzen(zzjf, zzby, url, byteArray, null, zzji));
                            } catch (MalformedURLException unused) {
                                this.zzj.zzab().zzgk().zza("Failed to parse upload URL. Not uploading. appId", zzef.zzam(zzby), str2);
                            }
                        }
                    } else {
                        this.zztj = -1;
                        String zzu = zzgy().zzu(currentTimeMillis - zzs.zzbt());
                        if (!TextUtils.isEmpty(zzu) && (zzab = zzgy().zzab(zzu)) != null) {
                            zzb(zzab);
                        }
                    }
                    this.zzte = false;
                    zzjo();
                }
            }
        } finally {
            this.zzte = false;
            zzjo();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0040, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
        r22 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0047, code lost:
        r5 = null;
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x009f, code lost:
        r5 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040 A[ExcHandler: all (th java.lang.Throwable), PHI: r3 
      PHI: (r3v35 android.database.Cursor) = (r3v33 android.database.Cursor), (r3v33 android.database.Cursor), (r3v33 android.database.Cursor), (r3v33 android.database.Cursor), (r3v0 android.database.Cursor), (r3v0 android.database.Cursor) binds: [B:22:0x0081, B:23:?, B:28:0x0092, B:29:?, B:9:0x0031, B:10:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:9:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0297  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x029e  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x02ac  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x03d8  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x03da  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x03de  */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x05ec A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x06d2  */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x0783  */
    /* JADX WARNING: Removed duplicated region for block: B:281:0x079d  */
    /* JADX WARNING: Removed duplicated region for block: B:533:0x0f1f  */
    /* JADX WARNING: Removed duplicated region for block: B:538:0x0f37  */
    @WorkerThread
    private final boolean zzd(String str, long j) {
        Throwable th;
        Throwable th2;
        Cursor cursor;
        Throwable th3;
        long j2;
        boolean z;
        zza zza2;
        zzjg zzjg;
        SecureRandom secureRandom;
        zza zza3;
        int i;
        boolean z2;
        long j3;
        boolean z3;
        long j4;
        zzf zzab;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        int i2;
        long j5;
        int i3;
        boolean z8;
        boolean z9;
        long j6;
        char c;
        boolean z10;
        String str2;
        Cursor cursor2;
        SQLiteException sQLiteException;
        Throwable th4;
        Cursor cursor3;
        String str3;
        String str4;
        Cursor cursor4;
        String[] strArr;
        String str5;
        String[] strArr2;
        zzjg zzjg2 = this;
        zzgy().beginTransaction();
        try {
            Cursor cursor5 = null;
            zza zza4 = new zza(zzjg2, null);
            zzx zzgy = zzgy();
            long j7 = zzjg2.zztj;
            Preconditions.checkNotNull(zza4);
            zzgy.zzo();
            zzgy.zzbi();
            try {
                SQLiteDatabase writableDatabase = zzgy.getWritableDatabase();
                if (TextUtils.isEmpty(null)) {
                    if (j7 != -1) {
                        try {
                            strArr2 = new String[]{String.valueOf(j7), String.valueOf(j)};
                        } catch (SQLiteException e) {
                            e = e;
                            cursor2 = cursor5;
                            str2 = null;
                        } catch (Throwable th5) {
                        }
                    } else {
                        strArr2 = new String[]{String.valueOf(j)};
                    }
                    String str6 = j7 != -1 ? "rowid <= ? and " : "";
                    StringBuilder sb = new StringBuilder(String.valueOf(str6).length() + 148);
                    sb.append("select app_id, metadata_fingerprint from raw_events where ");
                    sb.append(str6);
                    sb.append("app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;");
                    cursor5 = writableDatabase.rawQuery(sb.toString(), strArr2);
                    if (!cursor5.moveToFirst()) {
                        if (cursor5 != null) {
                            cursor5.close();
                        }
                        if (zza4.zztp != null || zza4.zztp.isEmpty()) {
                            zzbs.zzg.zza zznn = ((zzbs.zzg.zza) zza4.zztn.zzuj()).zznn();
                            boolean zze = zzjg2.zzj.zzad().zze(zza4.zztn.zzag(), zzak.zzii);
                            int i4 = 0;
                            int i5 = -1;
                            int i6 = -1;
                            boolean z11 = false;
                            long j8 = 0;
                            int i7 = 0;
                            zzbs.zzc.zza zza5 = null;
                            zzbs.zzc.zza zza6 = null;
                            while (i4 < zza4.zztp.size()) {
                                zzbs.zzc.zza zza7 = (zzbs.zzc.zza) zza4.zztp.get(i4).zzuj();
                                if (zzgz().zzk(zza4.zztn.zzag(), zza7.getName())) {
                                    z7 = z11;
                                    zzjg2.zzj.zzab().zzgn().zza("Dropping blacklisted raw event. appId", zzef.zzam(zza4.zztn.zzag()), zzjg2.zzj.zzy().zzaj(zza7.getName()));
                                    if (!(zzgz().zzbc(zza4.zztn.zzag()) || zzgz().zzbd(zza4.zztn.zzag())) && !"_err".equals(zza7.getName())) {
                                        zzjg2.zzj.zzz().zza(zza4.zztn.zzag(), 11, "_ev", zza7.getName(), 0);
                                    }
                                    i2 = i4;
                                    i7 = i7;
                                } else {
                                    z7 = z11;
                                    boolean zzl = zzgz().zzl(zza4.zztn.zzag(), zza7.getName());
                                    if (!zzl) {
                                        zzgw();
                                        String name = zza7.getName();
                                        Preconditions.checkNotEmpty(name);
                                        int hashCode = name.hashCode();
                                        if (hashCode != 94660) {
                                            if (hashCode != 95025) {
                                                if (hashCode == 95027 && name.equals("_ui")) {
                                                    c = 1;
                                                    switch (c) {
                                                        case 0:
                                                        case 1:
                                                        case 2:
                                                            z10 = true;
                                                            break;
                                                        default:
                                                            z10 = false;
                                                            break;
                                                    }
                                                    if (!z10) {
                                                        i3 = i4;
                                                        j5 = j8;
                                                        if (!zzjg2.zzj.zzad().zzs(zza4.zztn.zzag()) && zzl) {
                                                            ArrayList arrayList = new ArrayList(zza7.zzmj());
                                                            int i8 = -1;
                                                            int i9 = -1;
                                                            for (int i10 = 0; i10 < arrayList.size(); i10++) {
                                                                if ("value".equals(((zzbs.zze) arrayList.get(i10)).getName())) {
                                                                    i8 = i10;
                                                                } else if ("currency".equals(((zzbs.zze) arrayList.get(i10)).getName())) {
                                                                    i9 = i10;
                                                                }
                                                            }
                                                            if (i8 != -1) {
                                                                if (((zzbs.zze) arrayList.get(i8)).zzna() || ((zzbs.zze) arrayList.get(i8)).zznd()) {
                                                                    if (i9 == -1) {
                                                                        z8 = true;
                                                                    } else {
                                                                        String zzmy = ((zzbs.zze) arrayList.get(i9)).zzmy();
                                                                        if (zzmy.length() != 3) {
                                                                            z8 = true;
                                                                        } else {
                                                                            int i11 = 0;
                                                                            while (true) {
                                                                                if (i11 < zzmy.length()) {
                                                                                    int codePointAt = zzmy.codePointAt(i11);
                                                                                    if (!Character.isLetter(codePointAt)) {
                                                                                        z8 = true;
                                                                                    } else {
                                                                                        i11 += Character.charCount(codePointAt);
                                                                                    }
                                                                                } else {
                                                                                    z8 = false;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    if (z8) {
                                                                        zzjg2.zzj.zzab().zzgp().zzao("Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter.");
                                                                        zza7.zzm(i8);
                                                                        zza(zza7, "_c");
                                                                        zza(zza7, 19, "currency");
                                                                    }
                                                                } else {
                                                                    zzjg2.zzj.zzab().zzgp().zzao("Value must be specified with a numeric type.");
                                                                    zza7.zzm(i8);
                                                                    zza(zza7, "_c");
                                                                    zza(zza7, 18, "value");
                                                                }
                                                            }
                                                        }
                                                        if (zzjg2.zzj.zzad().zze(zza4.zztn.zzag(), zzak.zzih)) {
                                                            if ("_e".equals(zza7.getName())) {
                                                                zzgw();
                                                                if (zzjo.zza((zzbs.zzc) ((zzey) zza7.zzug()), "_fr") == null) {
                                                                    if (zza6 != null && Math.abs(zza6.getTimestampMillis() - zza7.getTimestampMillis()) <= 1000) {
                                                                        zzbs.zzc.zza zza8 = (zzbs.zzc.zza) ((zzey.zza) zza6.clone());
                                                                        if (zzjg2.zza(zza7, zza8)) {
                                                                            zznn.zza(i6, zza8);
                                                                            zza5 = null;
                                                                            zza6 = null;
                                                                        }
                                                                    }
                                                                    zza5 = zza7;
                                                                    i5 = i7;
                                                                }
                                                            } else if ("_vs".equals(zza7.getName())) {
                                                                zzgw();
                                                                if (zzjo.zza((zzbs.zzc) ((zzey) zza7.zzug()), "_et") == null) {
                                                                    if (zza5 != null && Math.abs(zza5.getTimestampMillis() - zza7.getTimestampMillis()) <= 1000) {
                                                                        zzbs.zzc.zza zza9 = (zzbs.zzc.zza) ((zzey.zza) zza5.clone());
                                                                        if (zzjg2.zza(zza9, zza7)) {
                                                                            zznn.zza(i5, zza9);
                                                                            zza5 = null;
                                                                            zza6 = null;
                                                                        }
                                                                    }
                                                                    zza6 = zza7;
                                                                    i6 = i7;
                                                                }
                                                            }
                                                        }
                                                        if (!zze && "_e".equals(zza7.getName())) {
                                                            if (zza7.zzmk() == 0) {
                                                                zzjg2.zzj.zzab().zzgn().zza("Engagement event does not contain any parameters. appId", zzef.zzam(zza4.zztn.zzag()));
                                                            } else {
                                                                zzgw();
                                                                Long l = (Long) zzjo.zzb((zzbs.zzc) ((zzey) zza7.zzug()), "_et");
                                                                if (l == null) {
                                                                    zzjg2.zzj.zzab().zzgn().zza("Engagement event does not include duration. appId", zzef.zzam(zza4.zztn.zzag()));
                                                                } else {
                                                                    j8 = j5 + l.longValue();
                                                                    i2 = i3;
                                                                    zza4.zztp.set(i2, (zzbs.zzc) ((zzey) zza7.zzug()));
                                                                    i7++;
                                                                    zznn.zza(zza7);
                                                                }
                                                            }
                                                        }
                                                        j8 = j5;
                                                        i2 = i3;
                                                        zza4.zztp.set(i2, (zzbs.zzc) ((zzey) zza7.zzug()));
                                                        i7++;
                                                        zznn.zza(zza7);
                                                    }
                                                }
                                            } else if (name.equals("_ug")) {
                                                c = 2;
                                                switch (c) {
                                                }
                                                if (!z10) {
                                                }
                                            }
                                        } else if (name.equals("_in")) {
                                            c = 0;
                                            switch (c) {
                                            }
                                            if (!z10) {
                                            }
                                        }
                                        c = 65535;
                                        switch (c) {
                                        }
                                        if (!z10) {
                                        }
                                    }
                                    int i12 = 0;
                                    boolean z12 = false;
                                    boolean z13 = false;
                                    while (i12 < zza7.zzmk()) {
                                        if ("_c".equals(zza7.zzl(i12).getName())) {
                                            j6 = j8;
                                            zza7.zza(i12, (zzbs.zze) ((zzey) ((zzbs.zze.zza) zza7.zzl(i12).zzuj()).zzam(1).zzug()));
                                            z12 = true;
                                        } else {
                                            j6 = j8;
                                            if ("_r".equals(zza7.zzl(i12).getName())) {
                                                zza7.zza(i12, (zzbs.zze) ((zzey) ((zzbs.zze.zza) zza7.zzl(i12).zzuj()).zzam(1).zzug()));
                                                z13 = true;
                                            }
                                        }
                                        i12++;
                                        i4 = i4;
                                        j8 = j6;
                                    }
                                    i3 = i4;
                                    j5 = j8;
                                    if (!z12 && zzl) {
                                        zzjg2.zzj.zzab().zzgs().zza("Marking event as conversion", zzjg2.zzj.zzy().zzaj(zza7.getName()));
                                        zza7.zza(zzbs.zze.zzng().zzbz("_c").zzam(1));
                                    }
                                    if (!z13) {
                                        zzjg2.zzj.zzab().zzgs().zza("Marking event as real-time", zzjg2.zzj.zzy().zzaj(zza7.getName()));
                                        zza7.zza(zzbs.zze.zzng().zzbz("_r").zzam(1));
                                    }
                                    if (zzgy().zza(zzjk(), zza4.zztn.zzag(), false, false, false, false, true).zzej > ((long) zzjg2.zzj.zzad().zzi(zza4.zztn.zzag()))) {
                                        zza(zza7, "_r");
                                        z9 = z7;
                                    } else {
                                        z9 = true;
                                    }
                                    if (!zzjs.zzbk(zza7.getName()) || !zzl) {
                                        z7 = z9;
                                        if (!zzjg2.zzj.zzad().zzs(zza4.zztn.zzag())) {
                                        }
                                        if (zzjg2.zzj.zzad().zze(zza4.zztn.zzag(), zzak.zzih)) {
                                        }
                                        if (zza7.zzmk() == 0) {
                                        }
                                        j8 = j5;
                                        i2 = i3;
                                        zza4.zztp.set(i2, (zzbs.zzc) ((zzey) zza7.zzug()));
                                        i7++;
                                        zznn.zza(zza7);
                                    } else {
                                        if (zzgy().zza(zzjk(), zza4.zztn.zzag(), false, false, true, false, false).zzeh > ((long) zzjg2.zzj.zzad().zzb(zza4.zztn.zzag(), zzak.zzgs))) {
                                            zzjg2.zzj.zzab().zzgn().zza("Too many conversions. Not logging as conversion. appId", zzef.zzam(zza4.zztn.zzag()));
                                            int i13 = 0;
                                            boolean z14 = false;
                                            zzbs.zze.zza zza10 = null;
                                            int i14 = -1;
                                            while (i13 < zza7.zzmk()) {
                                                zzbs.zze zzl2 = zza7.zzl(i13);
                                                if ("_c".equals(zzl2.getName())) {
                                                    zza10 = (zzbs.zze.zza) zzl2.zzuj();
                                                    i14 = i13;
                                                } else if ("_err".equals(zzl2.getName())) {
                                                    z14 = true;
                                                }
                                                i13++;
                                                z9 = z9;
                                            }
                                            z7 = z9;
                                            if (z14 && zza10 != null) {
                                                zza7.zzm(i14);
                                            } else if (zza10 != null) {
                                                zza7.zza(i14, (zzbs.zze) ((zzey) ((zzbs.zze.zza) ((zzey.zza) zza10.clone())).zzbz("_err").zzam(10).zzug()));
                                            } else {
                                                zzjg2.zzj.zzab().zzgk().zza("Did not find conversion parameter. appId", zzef.zzam(zza4.zztn.zzag()));
                                            }
                                        } else {
                                            z7 = z9;
                                        }
                                        if (!zzjg2.zzj.zzad().zzs(zza4.zztn.zzag())) {
                                        }
                                        if (zzjg2.zzj.zzad().zze(zza4.zztn.zzag(), zzak.zzih)) {
                                        }
                                        if (zza7.zzmk() == 0) {
                                        }
                                        j8 = j5;
                                        i2 = i3;
                                        zza4.zztp.set(i2, (zzbs.zzc) ((zzey) zza7.zzug()));
                                        i7++;
                                        zznn.zza(zza7);
                                    }
                                }
                                i4 = i2 + 1;
                                z11 = z7;
                            }
                            if (zze) {
                                int i15 = i7;
                                j2 = j8;
                                int i16 = 0;
                                while (i16 < i15) {
                                    zzbs.zzc zzq = zznn.zzq(i16);
                                    if ("_e".equals(zzq.getName())) {
                                        zzgw();
                                        if (zzjo.zza(zzq, "_fr") != null) {
                                            zznn.zzr(i16);
                                            i15--;
                                            i16--;
                                            i16++;
                                        }
                                    }
                                    zzgw();
                                    zzbs.zze zza11 = zzjo.zza(zzq, "_et");
                                    if (zza11 != null) {
                                        Long valueOf = zza11.zzna() ? Long.valueOf(zza11.zznb()) : null;
                                        if (valueOf != null && valueOf.longValue() > 0) {
                                            j2 += valueOf.longValue();
                                        }
                                    }
                                    i16++;
                                }
                                z = false;
                            } else {
                                j2 = j8;
                                z = false;
                            }
                            zzjg2.zza(zznn, j2, z);
                            if (zzjg2.zzj.zzad().zze(zznn.zzag(), zzak.zzja)) {
                                Iterator<zzbs.zzc> it = zznn.zznl().iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        z5 = false;
                                    } else if ("_s".equals(it.next().getName())) {
                                        z5 = true;
                                    }
                                }
                                if (z5) {
                                    zzgy().zzd(zznn.zzag(), "_se");
                                    z6 = true;
                                } else {
                                    z6 = true;
                                }
                                zzjg2.zza(zznn, j2, z6);
                            } else if (zzjg2.zzj.zzad().zze(zznn.zzag(), zzak.zzjb)) {
                                zzgy().zzd(zznn.zzag(), "_se");
                            }
                            if (zzjg2.zzj.zzad().zze(zznn.zzag(), zzak.zzij)) {
                                zzjo zzgw = zzgw();
                                zzgw.zzab().zzgs().zzao("Checking account type status for ad personalization signals");
                                if (zzgw.zzgz().zzba(zznn.zzag()) && (zzab = zzgw.zzgy().zzab(zznn.zzag())) != null && zzab.zzbe() && zzgw.zzw().zzcu()) {
                                    zzgw.zzab().zzgr().zzao("Turning off ad personalization due to account type");
                                    zzbs.zzk zzk = (zzbs.zzk) ((zzey) zzbs.zzk.zzqu().zzdb("_npa").zzbk(zzgw.zzw().zzcs()).zzbl(1).zzug());
                                    int i17 = 0;
                                    while (true) {
                                        if (i17 >= zznn.zznp()) {
                                            z4 = false;
                                        } else if ("_npa".equals(zznn.zzs(i17).getName())) {
                                            zznn.zza(i17, zzk);
                                            z4 = true;
                                        } else {
                                            i17++;
                                        }
                                    }
                                    if (!z4) {
                                        zznn.zza(zzk);
                                    }
                                }
                            }
                            zzbs.zzg.zza zznv = zznn.zznv();
                            String zzag = zznn.zzag();
                            List<zzbs.zzk> zzno = zznn.zzno();
                            List<zzbs.zzc> zznl = zznn.zznl();
                            Preconditions.checkNotEmpty(zzag);
                            zznv.zzc(zzgx().zza(zzag, zznl, zzno));
                            if (zzjg2.zzj.zzad().zzm(zza4.zztn.zzag())) {
                                try {
                                    HashMap hashMap = new HashMap();
                                    ArrayList arrayList2 = new ArrayList();
                                    SecureRandom zzjw = zzjg2.zzj.zzz().zzjw();
                                    int i18 = 0;
                                    while (i18 < zznn.zznm()) {
                                        zzbs.zzc.zza zza12 = (zzbs.zzc.zza) zznn.zzq(i18).zzuj();
                                        if (zza12.getName().equals("_ep")) {
                                            zzgw();
                                            String str7 = (String) zzjo.zzb((zzbs.zzc) ((zzey) zza12.zzug()), "_en");
                                            zzae zzae = (zzae) hashMap.get(str7);
                                            if (zzae == null) {
                                                zzae = zzgy().zzc(zza4.zztn.zzag(), str7);
                                                hashMap.put(str7, zzae);
                                            }
                                            if (zzae.zzfm == null) {
                                                if (zzae.zzfn.longValue() > 1) {
                                                    zzgw();
                                                    zzjo.zza(zza12, "_sr", zzae.zzfn);
                                                }
                                                if (zzae.zzfo != null && zzae.zzfo.booleanValue()) {
                                                    zzgw();
                                                    zzjo.zza(zza12, "_efs", (Object) 1L);
                                                }
                                                arrayList2.add((zzbs.zzc) ((zzey) zza12.zzug()));
                                            }
                                            zznn.zza(i18, zza12);
                                            zza3 = zza4;
                                            secureRandom = zzjw;
                                            i = i18;
                                        } else {
                                            long zzbb = zzgz().zzbb(zza4.zztn.zzag());
                                            zzjg2.zzj.zzz();
                                            long zzc = zzjs.zzc(zza12.getTimestampMillis(), zzbb);
                                            zzbs.zzc zzc2 = (zzbs.zzc) ((zzey) zza12.zzug());
                                            Long l2 = 1L;
                                            if (TextUtils.isEmpty("_dbg") || l2 == null) {
                                                z2 = false;
                                            } else {
                                                Iterator<zzbs.zze> it2 = zzc2.zzmj().iterator();
                                                while (true) {
                                                    if (it2.hasNext()) {
                                                        zzbs.zze next = it2.next();
                                                        if ("_dbg".equals(next.getName())) {
                                                            z2 = ((l2 instanceof Long) && l2.equals(Long.valueOf(next.zznb()))) || ((l2 instanceof String) && l2.equals(next.zzmy())) || ((l2 instanceof Double) && l2.equals(Double.valueOf(next.zzne())));
                                                        } else {
                                                            it2 = it2;
                                                        }
                                                    } else {
                                                        z2 = false;
                                                    }
                                                }
                                            }
                                            int zzm = !z2 ? zzgz().zzm(zza4.zztn.zzag(), zza12.getName()) : 1;
                                            if (zzm <= 0) {
                                                zzjg2.zzj.zzab().zzgn().zza("Sample rate must be positive. event, rate", zza12.getName(), Integer.valueOf(zzm));
                                                arrayList2.add((zzbs.zzc) ((zzey) zza12.zzug()));
                                                zznn.zza(i18, zza12);
                                                zza3 = zza4;
                                                secureRandom = zzjw;
                                                i = i18;
                                            } else {
                                                zzae zzae2 = (zzae) hashMap.get(zza12.getName());
                                                if (zzae2 == null) {
                                                    j3 = zzbb;
                                                    zzae2 = zzgy().zzc(zza4.zztn.zzag(), zza12.getName());
                                                    if (zzae2 == null) {
                                                        zzjg2.zzj.zzab().zzgn().zza("Event being bundled has no eventAggregate. appId, eventName", zza4.zztn.zzag(), zza12.getName());
                                                        if (zzjg2.zzj.zzad().zze(zza4.zztn.zzag(), zzak.zziz)) {
                                                            zzae2 = new zzae(zza4.zztn.zzag(), zza12.getName(), 1, 1, 1, zza12.getTimestampMillis(), 0, null, null, null, null);
                                                        } else {
                                                            zzae2 = new zzae(zza4.zztn.zzag(), zza12.getName(), 1, 1, zza12.getTimestampMillis(), 0, null, null, null, null);
                                                        }
                                                    }
                                                } else {
                                                    j3 = zzbb;
                                                }
                                                zzgw();
                                                Long l3 = (Long) zzjo.zzb((zzbs.zzc) ((zzey) zza12.zzug()), "_eid");
                                                Boolean valueOf2 = Boolean.valueOf(l3 != null);
                                                if (zzm == 1) {
                                                    arrayList2.add((zzbs.zzc) ((zzey) zza12.zzug()));
                                                    if (valueOf2.booleanValue() && !(zzae2.zzfm == null && zzae2.zzfn == null && zzae2.zzfo == null)) {
                                                        hashMap.put(zza12.getName(), zzae2.zza(null, null, null));
                                                    }
                                                    zznn.zza(i18, zza12);
                                                    zza3 = zza4;
                                                    secureRandom = zzjw;
                                                    i = i18;
                                                } else {
                                                    if (zzjw.nextInt(zzm) == 0) {
                                                        zzgw();
                                                        secureRandom = zzjw;
                                                        long j9 = (long) zzm;
                                                        zzjo.zza(zza12, "_sr", Long.valueOf(j9));
                                                        arrayList2.add((zzbs.zzc) ((zzey) zza12.zzug()));
                                                        if (valueOf2.booleanValue()) {
                                                            zzae2 = zzae2.zza(null, Long.valueOf(j9), null);
                                                        }
                                                        hashMap.put(zza12.getName(), zzae2.zza(zza12.getTimestampMillis(), zzc));
                                                        zza3 = zza4;
                                                        i = i18;
                                                    } else {
                                                        secureRandom = zzjw;
                                                        if (zzjg2.zzj.zzad().zzu(zza4.zztn.zzag())) {
                                                            if (zzae2.zzfl != null) {
                                                                j4 = zzae2.zzfl.longValue();
                                                                zza3 = zza4;
                                                            } else {
                                                                zzjg2.zzj.zzz();
                                                                zza3 = zza4;
                                                                j4 = zzjs.zzc(zza12.zzmm(), j3);
                                                            }
                                                            z3 = j4 != zzc;
                                                        } else {
                                                            zza3 = zza4;
                                                            z3 = Math.abs(zza12.getTimestampMillis() - zzae2.zzfk) >= 86400000;
                                                        }
                                                        if (z3) {
                                                            zzgw();
                                                            zzjo.zza(zza12, "_efs", (Object) 1L);
                                                            zzgw();
                                                            long j10 = (long) zzm;
                                                            zzjo.zza(zza12, "_sr", Long.valueOf(j10));
                                                            arrayList2.add((zzbs.zzc) ((zzey) zza12.zzug()));
                                                            if (valueOf2.booleanValue()) {
                                                                zzae2 = zzae2.zza(null, Long.valueOf(j10), true);
                                                            }
                                                            hashMap.put(zza12.getName(), zzae2.zza(zza12.getTimestampMillis(), zzc));
                                                            i = i18;
                                                        } else if (valueOf2.booleanValue()) {
                                                            hashMap.put(zza12.getName(), zzae2.zza(l3, null, null));
                                                            i = i18;
                                                        } else {
                                                            i = i18;
                                                        }
                                                    }
                                                    zznn.zza(i, zza12);
                                                }
                                            }
                                        }
                                        i18 = i + 1;
                                        zzjw = secureRandom;
                                        zza4 = zza3;
                                        zzjg2 = this;
                                    }
                                    zza2 = zza4;
                                    if (arrayList2.size() < zznn.zznm()) {
                                        zznn.zznn().zza(arrayList2);
                                    }
                                    for (Map.Entry entry : hashMap.entrySet()) {
                                        zzgy().zza((zzae) entry.getValue());
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    zzgy().endTransaction();
                                    throw th;
                                }
                            } else {
                                zza2 = zza4;
                            }
                            try {
                                zznn.zzao(LongCompanionObject.MAX_VALUE).zzap(Long.MIN_VALUE);
                                for (int i19 = 0; i19 < zznn.zznm(); i19++) {
                                    zzbs.zzc zzq2 = zznn.zzq(i19);
                                    if (zzq2.getTimestampMillis() < zznn.zznq()) {
                                        zznn.zzao(zzq2.getTimestampMillis());
                                    }
                                    if (zzq2.getTimestampMillis() > zznn.zznr()) {
                                        zznn.zzap(zzq2.getTimestampMillis());
                                    }
                                }
                                String zzag2 = zza2.zztn.zzag();
                                zzf zzab2 = zzgy().zzab(zzag2);
                                if (zzab2 == null) {
                                    zzjg = this;
                                    try {
                                        zzjg.zzj.zzab().zzgk().zza("Bundling raw events w/o app info. appId", zzef.zzam(zza2.zztn.zzag()));
                                    } catch (Throwable th7) {
                                        th2 = th7;
                                        th = th2;
                                        zzgy().endTransaction();
                                        throw th;
                                    }
                                } else {
                                    zzjg = this;
                                    if (zznn.zznm() > 0) {
                                        long zzak = zzab2.zzak();
                                        if (zzak != 0) {
                                            zznn.zzar(zzak);
                                        } else {
                                            zznn.zznt();
                                        }
                                        long zzaj = zzab2.zzaj();
                                        if (zzaj != 0) {
                                            zzak = zzaj;
                                        }
                                        if (zzak != 0) {
                                            zznn.zzaq(zzak);
                                        } else {
                                            zznn.zzns();
                                        }
                                        zzab2.zzau();
                                        zznn.zzu((int) zzab2.zzar());
                                        zzab2.zze(zznn.zznq());
                                        zzab2.zzf(zznn.zznr());
                                        String zzbc = zzab2.zzbc();
                                        if (zzbc != null) {
                                            zznn.zzcl(zzbc);
                                        } else {
                                            zznn.zznu();
                                        }
                                        zzgy().zza(zzab2);
                                    }
                                }
                                if (zznn.zznm() > 0) {
                                    zzjg.zzj.zzae();
                                    zzbw zzaw = zzgz().zzaw(zza2.zztn.zzag());
                                    if (zzaw != null) {
                                        if (zzaw.zzzk != null) {
                                            zznn.zzav(zzaw.zzzk.longValue());
                                            zzgy().zza((zzbs.zzg) ((zzey) zznn.zzug()), z11);
                                        }
                                    }
                                    if (TextUtils.isEmpty(zza2.zztn.getGmpAppId())) {
                                        zznn.zzav(-1);
                                    } else {
                                        zzjg.zzj.zzab().zzgn().zza("Did not find measurement config or missing version info. appId", zzef.zzam(zza2.zztn.zzag()));
                                    }
                                    zzgy().zza((zzbs.zzg) ((zzey) zznn.zzug()), z11);
                                }
                                zzx zzgy2 = zzgy();
                                List<Long> list = zza2.zzto;
                                Preconditions.checkNotNull(list);
                                zzgy2.zzo();
                                zzgy2.zzbi();
                                StringBuilder sb2 = new StringBuilder("rowid in (");
                                for (int i20 = 0; i20 < list.size(); i20++) {
                                    if (i20 != 0) {
                                        sb2.append(",");
                                    }
                                    sb2.append(list.get(i20).longValue());
                                }
                                sb2.append(")");
                                int delete = zzgy2.getWritableDatabase().delete("raw_events", sb2.toString(), null);
                                if (delete != list.size()) {
                                    zzgy2.zzab().zzgk().zza("Deleted fewer rows from raw events table than expected", Integer.valueOf(delete), Integer.valueOf(list.size()));
                                }
                                zzx zzgy3 = zzgy();
                                try {
                                    zzgy3.getWritableDatabase().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{zzag2, zzag2});
                                } catch (SQLiteException e2) {
                                    zzgy3.zzab().zzgk().zza("Failed to remove unused event metadata. appId", zzef.zzam(zzag2), e2);
                                }
                                zzgy().setTransactionSuccessful();
                                zzgy().endTransaction();
                                return true;
                            } catch (Throwable th8) {
                                th2 = th8;
                                th = th2;
                                zzgy().endTransaction();
                                throw th;
                            }
                        } else {
                            zzgy().setTransactionSuccessful();
                            zzgy().endTransaction();
                            return false;
                        }
                    } else {
                        str2 = cursor5.getString(0);
                        String string = cursor5.getString(1);
                        cursor5.close();
                        cursor3 = cursor5;
                        str4 = str2;
                        str3 = string;
                    }
                } else {
                    String[] strArr3 = j7 != -1 ? new String[]{null, String.valueOf(j7)} : new String[]{null};
                    String str8 = j7 != -1 ? " and rowid <= ?" : "";
                    StringBuilder sb3 = new StringBuilder(String.valueOf(str8).length() + 84);
                    sb3.append("select metadata_fingerprint from raw_events where app_id = ?");
                    sb3.append(str8);
                    sb3.append(" order by rowid limit 1;");
                    Cursor rawQuery = writableDatabase.rawQuery(sb3.toString(), strArr3);
                    if (!rawQuery.moveToFirst()) {
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        if (zza4.zztp != null || zza4.zztp.isEmpty()) {
                        }
                    } else {
                        String string2 = rawQuery.getString(0);
                        rawQuery.close();
                        cursor3 = rawQuery;
                        str3 = string2;
                        str4 = null;
                    }
                }
                try {
                    Cursor query = writableDatabase.query("raw_events_metadata", new String[]{"metadata"}, "app_id = ? and metadata_fingerprint = ?", new String[]{str4, str3}, null, null, "rowid", "2");
                    try {
                        if (!query.moveToFirst()) {
                            try {
                                zzgy.zzab().zzgk().zza("Raw event metadata record is missing. appId", zzef.zzam(str4));
                                if (query != null) {
                                    query.close();
                                }
                            } catch (SQLiteException e3) {
                                e = e3;
                                str2 = str4;
                                cursor2 = query;
                                sQLiteException = e;
                                try {
                                    zzgy.zzab().zzgk().zza("Data loss. Error selecting raw event. appId", zzef.zzam(str2), sQLiteException);
                                    if (cursor2 != null) {
                                    }
                                    if (zza4.zztp != null || zza4.zztp.isEmpty()) {
                                    }
                                } catch (Throwable th9) {
                                    th3 = th9;
                                    cursor = cursor2;
                                    if (cursor != null) {
                                    }
                                    throw th3;
                                }
                            } catch (Throwable th10) {
                                th4 = th10;
                                cursor = query;
                                th3 = th4;
                                if (cursor != null) {
                                }
                                throw th3;
                            }
                            if (zza4.zztp != null || zza4.zztp.isEmpty()) {
                            }
                        } else {
                            try {
                                zzbs.zzg zzd = zzbs.zzg.zzd(query.getBlob(0), zzel.zztq());
                                if (query.moveToNext()) {
                                    zzgy.zzab().zzgn().zza("Get multiple raw event metadata records, expected one. appId", zzef.zzam(str4));
                                }
                                query.close();
                                zza4.zzb(zzd);
                                if (j7 != -1) {
                                    str5 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
                                    strArr = new String[]{str4, str3, String.valueOf(j7)};
                                } else {
                                    str5 = "app_id = ? and metadata_fingerprint = ?";
                                    strArr = new String[]{str4, str3};
                                }
                                cursor4 = query;
                                try {
                                    cursor2 = writableDatabase.query("raw_events", new String[]{"rowid", AppMeasurementSdk.ConditionalUserProperty.NAME, "timestamp", "data"}, str5, strArr, null, null, "rowid", null);
                                    try {
                                        if (!cursor2.moveToFirst()) {
                                            zzgy.zzab().zzgn().zza("Raw event data disappeared while in transaction. appId", zzef.zzam(str4));
                                            if (cursor2 != null) {
                                                cursor2.close();
                                            }
                                        } else {
                                            while (true) {
                                                long j11 = cursor2.getLong(0);
                                                try {
                                                    zzbs.zzc.zza zza13 = (zzbs.zzc.zza) zzbs.zzc.zzmq().zzf(cursor2.getBlob(3), zzel.zztq());
                                                    zza13.zzbx(cursor2.getString(1)).zzag(cursor2.getLong(2));
                                                    if (!zza4.zza(j11, (zzbs.zzc) ((zzey) zza13.zzug()))) {
                                                        if (cursor2 != null) {
                                                            cursor2.close();
                                                        }
                                                    }
                                                } catch (IOException e4) {
                                                    zzgy.zzab().zzgk().zza("Data loss. Failed to merge raw event. appId", zzef.zzam(str4), e4);
                                                }
                                                if (!cursor2.moveToNext()) {
                                                    if (cursor2 != null) {
                                                        cursor2.close();
                                                    }
                                                }
                                            }
                                        }
                                    } catch (SQLiteException e5) {
                                        e = e5;
                                        str2 = str4;
                                        sQLiteException = e;
                                        zzgy.zzab().zzgk().zza("Data loss. Error selecting raw event. appId", zzef.zzam(str2), sQLiteException);
                                        if (cursor2 != null) {
                                        }
                                        if (zza4.zztp != null || zza4.zztp.isEmpty()) {
                                        }
                                    } catch (Throwable th11) {
                                        th4 = th11;
                                        cursor = cursor2;
                                        th3 = th4;
                                        if (cursor != null) {
                                        }
                                        throw th3;
                                    }
                                } catch (SQLiteException e6) {
                                    e = e6;
                                    str2 = str4;
                                    cursor2 = cursor4;
                                    sQLiteException = e;
                                    zzgy.zzab().zzgk().zza("Data loss. Error selecting raw event. appId", zzef.zzam(str2), sQLiteException);
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    if (zza4.zztp != null || zza4.zztp.isEmpty()) {
                                    }
                                } catch (Throwable th12) {
                                    th4 = th12;
                                    cursor = cursor4;
                                    th3 = th4;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    throw th3;
                                }
                            } catch (IOException e7) {
                                zzgy.zzab().zzgk().zza("Data loss. Failed to merge raw event metadata. appId", zzef.zzam(str4), e7);
                                if (query != null) {
                                    query.close();
                                }
                            }
                            if (zza4.zztp != null || zza4.zztp.isEmpty()) {
                            }
                        }
                    } catch (SQLiteException e8) {
                        e = e8;
                        cursor4 = query;
                        str2 = str4;
                        cursor2 = cursor4;
                        sQLiteException = e;
                        zzgy.zzab().zzgk().zza("Data loss. Error selecting raw event. appId", zzef.zzam(str2), sQLiteException);
                        if (cursor2 != null) {
                        }
                        if (zza4.zztp != null || zza4.zztp.isEmpty()) {
                        }
                    } catch (Throwable th13) {
                        th4 = th13;
                        cursor4 = query;
                        cursor = cursor4;
                        th3 = th4;
                        if (cursor != null) {
                        }
                        throw th3;
                    }
                } catch (SQLiteException e9) {
                    e = e9;
                    str2 = str4;
                    cursor2 = cursor3;
                    sQLiteException = e;
                    zzgy.zzab().zzgk().zza("Data loss. Error selecting raw event. appId", zzef.zzam(str2), sQLiteException);
                    if (cursor2 != null) {
                    }
                    if (zza4.zztp != null || zza4.zztp.isEmpty()) {
                    }
                } catch (Throwable th14) {
                    th4 = th14;
                    cursor = cursor3;
                    th3 = th4;
                    if (cursor != null) {
                    }
                    throw th3;
                }
            } catch (SQLiteException e10) {
                sQLiteException = e10;
                cursor2 = null;
                str2 = null;
                zzgy.zzab().zzgk().zza("Data loss. Error selecting raw event. appId", zzef.zzam(str2), sQLiteException);
                if (cursor2 != null) {
                }
                if (zza4.zztp != null || zza4.zztp.isEmpty()) {
                }
            } catch (Throwable th15) {
                th4 = th15;
                cursor = null;
                th3 = th4;
                if (cursor != null) {
                }
                throw th3;
            }
        } catch (Throwable th16) {
            th2 = th16;
            th = th2;
            zzgy().endTransaction();
            throw th;
        }
    }

    @VisibleForTesting
    private final void zza(zzbs.zzg.zza zza2, long j, boolean z) {
        zzjp zzjp;
        String str = "_lte";
        if (z) {
            str = "_se";
        }
        zzjp zze = zzgy().zze(zza2.zzag(), str);
        if (zze == null || zze.value == null) {
            zzjp = new zzjp(zza2.zzag(), "auto", str, this.zzj.zzx().currentTimeMillis(), Long.valueOf(j));
        } else {
            zzjp = new zzjp(zza2.zzag(), "auto", str, this.zzj.zzx().currentTimeMillis(), Long.valueOf(((Long) zze.value).longValue() + j));
        }
        zzbs.zzk zzk = (zzbs.zzk) ((zzey) zzbs.zzk.zzqu().zzdb(str).zzbk(this.zzj.zzx().currentTimeMillis()).zzbl(((Long) zzjp.value).longValue()).zzug());
        boolean z2 = false;
        int i = 0;
        while (true) {
            if (i >= zza2.zznp()) {
                break;
            } else if (str.equals(zza2.zzs(i).getName())) {
                zza2.zza(i, zzk);
                z2 = true;
                break;
            } else {
                i++;
            }
        }
        if (!z2) {
            zza2.zza(zzk);
        }
        if (j > 0) {
            zzgy().zza(zzjp);
            String str2 = "lifetime";
            if (z) {
                str2 = "session-scoped";
            }
            this.zzj.zzab().zzgr().zza("Updated engagement user property. scope, value", str2, zzjp.value);
        }
    }

    private final boolean zza(zzbs.zzc.zza zza2, zzbs.zzc.zza zza3) {
        String str;
        Preconditions.checkArgument("_e".equals(zza2.getName()));
        zzgw();
        zzbs.zze zza4 = zzjo.zza((zzbs.zzc) ((zzey) zza2.zzug()), "_sc");
        String str2 = null;
        if (zza4 == null) {
            str = null;
        } else {
            str = zza4.zzmy();
        }
        zzgw();
        zzbs.zze zza5 = zzjo.zza((zzbs.zzc) ((zzey) zza3.zzug()), "_pc");
        if (zza5 != null) {
            str2 = zza5.zzmy();
        }
        if (str2 == null || !str2.equals(str)) {
            return false;
        }
        zzgw();
        zzbs.zze zza6 = zzjo.zza((zzbs.zzc) ((zzey) zza2.zzug()), "_et");
        if (!zza6.zzna() || zza6.zznb() <= 0) {
            return true;
        }
        long zznb = zza6.zznb();
        zzgw();
        zzbs.zze zza7 = zzjo.zza((zzbs.zzc) ((zzey) zza3.zzug()), "_et");
        if (zza7 != null && zza7.zznb() > 0) {
            zznb += zza7.zznb();
        }
        zzgw();
        zzjo.zza(zza3, "_et", Long.valueOf(zznb));
        zzgw();
        zzjo.zza(zza2, "_fr", (Object) 1L);
        return true;
    }

    @VisibleForTesting
    private static void zza(zzbs.zzc.zza zza2, @NonNull String str) {
        List<zzbs.zze> zzmj = zza2.zzmj();
        for (int i = 0; i < zzmj.size(); i++) {
            if (str.equals(zzmj.get(i).getName())) {
                zza2.zzm(i);
                return;
            }
        }
    }

    @VisibleForTesting
    private static void zza(zzbs.zzc.zza zza2, int i, String str) {
        List<zzbs.zze> zzmj = zza2.zzmj();
        for (int i2 = 0; i2 < zzmj.size(); i2++) {
            if ("_err".equals(zzmj.get(i2).getName())) {
                return;
            }
        }
        zza2.zza((zzbs.zze) ((zzey) zzbs.zze.zzng().zzbz("_err").zzam(Long.valueOf((long) i).longValue()).zzug())).zza((zzbs.zze) ((zzey) zzbs.zze.zzng().zzbz("_ev").zzca(str).zzug()));
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    @VisibleForTesting
    @WorkerThread
    public final void zza(int i, Throwable th, byte[] bArr, String str) {
        zzo();
        zzjj();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zztd = false;
                zzjo();
                throw th2;
            }
        }
        List<Long> list = this.zzth;
        this.zzth = null;
        boolean z = true;
        if ((i == 200 || i == 204) && th == null) {
            try {
                this.zzj.zzac().zzlj.set(this.zzj.zzx().currentTimeMillis());
                this.zzj.zzac().zzlk.set(0);
                zzjn();
                this.zzj.zzab().zzgs().zza("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzgy().beginTransaction();
                try {
                    for (Long l : list) {
                        try {
                            zzx zzgy = zzgy();
                            long longValue = l.longValue();
                            zzgy.zzo();
                            zzgy.zzbi();
                            try {
                                if (zzgy.getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                                    throw new SQLiteException("Deleted fewer rows from queue than expected");
                                }
                            } catch (SQLiteException e) {
                                zzgy.zzab().zzgk().zza("Failed to delete a bundle in a queue table", e);
                                throw e;
                            }
                        } catch (SQLiteException e2) {
                            if (this.zzti == null || !this.zzti.contains(l)) {
                                throw e2;
                            }
                        }
                    }
                    zzgy().setTransactionSuccessful();
                    zzgy().endTransaction();
                    this.zzti = null;
                    if (!zzjf().zzgv() || !zzjm()) {
                        this.zztj = -1;
                        zzjn();
                    } else {
                        zzjl();
                    }
                    this.zzsy = 0;
                } catch (Throwable th3) {
                    zzgy().endTransaction();
                    throw th3;
                }
            } catch (SQLiteException e3) {
                this.zzj.zzab().zzgk().zza("Database error while trying to delete uploaded bundles", e3);
                this.zzsy = this.zzj.zzx().elapsedRealtime();
                this.zzj.zzab().zzgs().zza("Disable upload, time", Long.valueOf(this.zzsy));
            }
        } else {
            this.zzj.zzab().zzgs().zza("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzj.zzac().zzlk.set(this.zzj.zzx().currentTimeMillis());
            if (!(i == 503 || i == 429)) {
                z = false;
            }
            if (z) {
                this.zzj.zzac().zzll.set(this.zzj.zzx().currentTimeMillis());
            }
            zzgy().zzb(list);
            zzjn();
        }
        this.zztd = false;
        zzjo();
    }

    private final boolean zzjm() {
        zzo();
        zzjj();
        return zzgy().zzcd() || !TextUtils.isEmpty(zzgy().zzby());
    }

    @WorkerThread
    private final void zzb(zzf zzf) {
        ArrayMap arrayMap;
        zzo();
        if (!TextUtils.isEmpty(zzf.getGmpAppId()) || (zzs.zzbx() && !TextUtils.isEmpty(zzf.zzah()))) {
            zzs zzad = this.zzj.zzad();
            Uri.Builder builder = new Uri.Builder();
            String gmpAppId = zzf.getGmpAppId();
            if (TextUtils.isEmpty(gmpAppId) && zzs.zzbx()) {
                gmpAppId = zzf.zzah();
            }
            Uri.Builder encodedAuthority = builder.scheme(zzak.zzgj.get(null)).encodedAuthority(zzak.zzgk.get(null));
            String valueOf = String.valueOf(gmpAppId);
            encodedAuthority.path(valueOf.length() != 0 ? "config/app/".concat(valueOf) : new String("config/app/")).appendQueryParameter("app_instance_id", zzf.getAppInstanceId()).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", String.valueOf(zzad.zzao()));
            String uri = builder.build().toString();
            try {
                URL url = new URL(uri);
                this.zzj.zzab().zzgs().zza("Fetching remote configuration", zzf.zzag());
                zzbw zzaw = zzgz().zzaw(zzf.zzag());
                String zzax = zzgz().zzax(zzf.zzag());
                if (zzaw == null || TextUtils.isEmpty(zzax)) {
                    arrayMap = null;
                } else {
                    ArrayMap arrayMap2 = new ArrayMap();
                    arrayMap2.put("If-Modified-Since", zzax);
                    arrayMap = arrayMap2;
                }
                this.zztc = true;
                zzej zzjf = zzjf();
                String zzag = zzf.zzag();
                zzjl zzjl = new zzjl(this);
                zzjf.zzo();
                zzjf.zzbi();
                Preconditions.checkNotNull(url);
                Preconditions.checkNotNull(zzjl);
                zzjf.zzaa().zzb(new zzen(zzjf, zzag, url, null, arrayMap, zzjl));
            } catch (MalformedURLException unused) {
                this.zzj.zzab().zzgk().zza("Failed to parse config URL. Not fetching. appId", zzef.zzam(zzf.zzag()), uri);
            }
        } else {
            zzb(zzf.zzag(), 204, null, null, null);
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x014a  */
    @VisibleForTesting
    @WorkerThread
    public final void zzb(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        zzo();
        zzjj();
        Preconditions.checkNotEmpty(str);
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zztc = false;
                zzjo();
                throw th2;
            }
        }
        this.zzj.zzab().zzgs().zza("onConfigFetched. Response size", Integer.valueOf(bArr.length));
        zzgy().beginTransaction();
        try {
            zzf zzab = zzgy().zzab(str);
            boolean z = true;
            boolean z2 = (i == 200 || i == 204 || i == 304) && th == null;
            if (zzab == null) {
                this.zzj.zzab().zzgn().zza("App does not exist in onConfigFetched. appId", zzef.zzam(str));
            } else {
                if (!z2) {
                    if (i != 404) {
                        zzab.zzm(this.zzj.zzx().currentTimeMillis());
                        zzgy().zza(zzab);
                        this.zzj.zzab().zzgs().zza("Fetching config failed. code, error", Integer.valueOf(i), th);
                        zzgz().zzay(str);
                        this.zzj.zzac().zzlk.set(this.zzj.zzx().currentTimeMillis());
                        if (i != 503) {
                            if (i != 429) {
                                z = false;
                            }
                        }
                        if (z) {
                            this.zzj.zzac().zzll.set(this.zzj.zzx().currentTimeMillis());
                        }
                        zzjn();
                    }
                }
                List<String> list = map != null ? map.get("Last-Modified") : null;
                String str2 = (list == null || list.size() <= 0) ? null : list.get(0);
                if (i != 404) {
                    if (i != 304) {
                        if (!zzgz().zza(str, bArr, str2)) {
                            zzgy().endTransaction();
                            this.zztc = false;
                            zzjo();
                            return;
                        }
                        zzab.zzl(this.zzj.zzx().currentTimeMillis());
                        zzgy().zza(zzab);
                        if (i != 404) {
                            this.zzj.zzab().zzgp().zza("Config not found. Using empty config. appId", str);
                        } else {
                            this.zzj.zzab().zzgs().zza("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                        }
                        if (zzjf().zzgv() || !zzjm()) {
                            zzjn();
                        } else {
                            zzjl();
                        }
                    }
                }
                if (zzgz().zzaw(str) == null && !zzgz().zza(str, null, null)) {
                    zzgy().endTransaction();
                    this.zztc = false;
                    zzjo();
                    return;
                }
                zzab.zzl(this.zzj.zzx().currentTimeMillis());
                zzgy().zza(zzab);
                if (i != 404) {
                }
                if (zzjf().zzgv()) {
                }
                zzjn();
            }
            zzgy().setTransactionSuccessful();
            zzgy().endTransaction();
            this.zztc = false;
            zzjo();
        } catch (Throwable th3) {
            zzgy().endTransaction();
            throw th3;
        }
    }

    @WorkerThread
    private final void zzjn() {
        long j;
        long j2;
        zzo();
        zzjj();
        if (zzjr() || this.zzj.zzad().zza(zzak.zzim)) {
            if (this.zzsy > 0) {
                long abs = 3600000 - Math.abs(this.zzj.zzx().elapsedRealtime() - this.zzsy);
                if (abs > 0) {
                    this.zzj.zzab().zzgs().zza("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(abs));
                    zzjg().unregister();
                    zzjh().cancel();
                    return;
                }
                this.zzsy = 0;
            }
            if (!this.zzj.zzie() || !zzjm()) {
                this.zzj.zzab().zzgs().zzao("Nothing to upload or uploading impossible");
                zzjg().unregister();
                zzjh().cancel();
                return;
            }
            long currentTimeMillis = this.zzj.zzx().currentTimeMillis();
            long max = Math.max(0L, zzak.zzhf.get(null).longValue());
            boolean z = zzgy().zzce() || zzgy().zzbz();
            if (z) {
                String zzbu = this.zzj.zzad().zzbu();
                if (TextUtils.isEmpty(zzbu) || ".none.".equals(zzbu)) {
                    j = Math.max(0L, zzak.zzgz.get(null).longValue());
                } else {
                    j = Math.max(0L, zzak.zzha.get(null).longValue());
                }
            } else {
                j = Math.max(0L, zzak.zzgy.get(null).longValue());
            }
            long j3 = this.zzj.zzac().zzlj.get();
            long j4 = this.zzj.zzac().zzlk.get();
            long max2 = Math.max(zzgy().zzcb(), zzgy().zzcc());
            if (max2 == 0) {
                j2 = 0;
            } else {
                long abs2 = currentTimeMillis - Math.abs(max2 - currentTimeMillis);
                long abs3 = currentTimeMillis - Math.abs(j3 - currentTimeMillis);
                long abs4 = currentTimeMillis - Math.abs(j4 - currentTimeMillis);
                long max3 = Math.max(abs3, abs4);
                long j5 = abs2 + max;
                if (z && max3 > 0) {
                    j5 = Math.min(abs2, max3) + j;
                }
                j2 = !zzgw().zzb(max3, j) ? max3 + j : j5;
                if (abs4 != 0 && abs4 >= abs2) {
                    int i = 0;
                    while (true) {
                        if (i >= Math.min(20, Math.max(0, zzak.zzhh.get(null).intValue()))) {
                            j2 = 0;
                            break;
                        }
                        j2 += Math.max(0L, zzak.zzhg.get(null).longValue()) * (1 << i);
                        if (j2 > abs4) {
                            break;
                        }
                        i++;
                    }
                }
            }
            if (j2 == 0) {
                this.zzj.zzab().zzgs().zzao("Next upload time is 0");
                zzjg().unregister();
                zzjh().cancel();
            } else if (!zzjf().zzgv()) {
                this.zzj.zzab().zzgs().zzao("No network");
                zzjg().zzha();
                zzjh().cancel();
            } else {
                long j6 = this.zzj.zzac().zzll.get();
                long max4 = Math.max(0L, zzak.zzgw.get(null).longValue());
                if (!zzgw().zzb(j6, max4)) {
                    j2 = Math.max(j2, j6 + max4);
                }
                zzjg().unregister();
                long currentTimeMillis2 = j2 - this.zzj.zzx().currentTimeMillis();
                if (currentTimeMillis2 <= 0) {
                    currentTimeMillis2 = Math.max(0L, zzak.zzhb.get(null).longValue());
                    this.zzj.zzac().zzlj.set(this.zzj.zzx().currentTimeMillis());
                }
                this.zzj.zzab().zzgs().zza("Upload scheduled in approximately ms", Long.valueOf(currentTimeMillis2));
                zzjh().zzv(currentTimeMillis2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzf(Runnable runnable) {
        zzo();
        if (this.zzsz == null) {
            this.zzsz = new ArrayList();
        }
        this.zzsz.add(runnable);
    }

    @WorkerThread
    private final void zzjo() {
        zzo();
        if (this.zztc || this.zztd || this.zzte) {
            this.zzj.zzab().zzgs().zza("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zztc), Boolean.valueOf(this.zztd), Boolean.valueOf(this.zzte));
            return;
        }
        this.zzj.zzab().zzgs().zzao("Stopping uploading service(s)");
        List<Runnable> list = this.zzsz;
        if (list != null) {
            for (Runnable runnable : list) {
                runnable.run();
            }
            this.zzsz.clear();
        }
    }

    @WorkerThread
    private final Boolean zzc(zzf zzf) {
        try {
            if (zzf.zzam() != -2147483648L) {
                if (zzf.zzam() == ((long) Wrappers.packageManager(this.zzj.getContext()).getPackageInfo(zzf.zzag(), 0).versionCode)) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(this.zzj.getContext()).getPackageInfo(zzf.zzag(), 0).versionName;
                if (zzf.zzal() != null && zzf.zzal().equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @VisibleForTesting
    @WorkerThread
    private final boolean zzjp() {
        FileLock fileLock;
        zzo();
        if (!this.zzj.zzad().zza(zzak.zzjh) || (fileLock = this.zztf) == null || !fileLock.isValid()) {
            try {
                this.zztg = new RandomAccessFile(new File(this.zzj.getContext().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
                this.zztf = this.zztg.tryLock();
                if (this.zztf != null) {
                    this.zzj.zzab().zzgs().zzao("Storage concurrent access okay");
                    return true;
                }
                this.zzj.zzab().zzgk().zzao("Storage concurrent data access panic");
                return false;
            } catch (FileNotFoundException e) {
                this.zzj.zzab().zzgk().zza("Failed to acquire storage lock", e);
                return false;
            } catch (IOException e2) {
                this.zzj.zzab().zzgk().zza("Failed to access storage lock file", e2);
                return false;
            } catch (OverlappingFileLockException e3) {
                this.zzj.zzab().zzgn().zza("Storage lock already acquired", e3);
                return false;
            }
        } else {
            this.zzj.zzab().zzgs().zzao("Storage concurrent access okay");
            return true;
        }
    }

    @VisibleForTesting
    @WorkerThread
    private final int zza(FileChannel fileChannel) {
        zzo();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzj.zzab().zzgk().zzao("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0L);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    this.zzj.zzab().zzgn().zza("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            return allocate.getInt();
        } catch (IOException e) {
            this.zzj.zzab().zzgk().zza("Failed to read from channel", e);
            return 0;
        }
    }

    @VisibleForTesting
    @WorkerThread
    private final boolean zza(int i, FileChannel fileChannel) {
        zzo();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzj.zzab().zzgk().zzao("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0L);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                this.zzj.zzab().zzgk().zza("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            this.zzj.zzab().zzgk().zza("Failed to write to channel", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzjq() {
        zzo();
        zzjj();
        if (!this.zzsx) {
            this.zzsx = true;
            zzo();
            zzjj();
            if ((this.zzj.zzad().zza(zzak.zzim) || zzjr()) && zzjp()) {
                int zza2 = zza(this.zztg);
                int zzgf = this.zzj.zzr().zzgf();
                zzo();
                if (zza2 > zzgf) {
                    this.zzj.zzab().zzgk().zza("Panic: can't downgrade version. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzgf));
                } else if (zza2 < zzgf) {
                    if (zza(zzgf, this.zztg)) {
                        this.zzj.zzab().zzgs().zza("Storage version upgraded. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzgf));
                    } else {
                        this.zzj.zzab().zzgk().zza("Storage version upgrade failed. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzgf));
                    }
                }
            }
        }
        if (!this.zzsw && !this.zzj.zzad().zza(zzak.zzim)) {
            this.zzj.zzab().zzgq().zzao("This instance being marked as an uploader");
            this.zzsw = true;
            zzjn();
        }
    }

    @WorkerThread
    private final boolean zzjr() {
        zzo();
        zzjj();
        return this.zzsw;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    @WorkerThread
    public final void zzd(zzn zzn) {
        if (this.zzth != null) {
            this.zzti = new ArrayList();
            this.zzti.addAll(this.zzth);
        }
        zzx zzgy = zzgy();
        String str = zzn.packageName;
        Preconditions.checkNotEmpty(str);
        zzgy.zzo();
        zzgy.zzbi();
        try {
            SQLiteDatabase writableDatabase = zzgy.getWritableDatabase();
            String[] strArr = {str};
            int delete = writableDatabase.delete("apps", "app_id=?", strArr) + 0 + writableDatabase.delete("events", "app_id=?", strArr) + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("queue", "app_id=?", strArr) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + writableDatabase.delete("main_event_params", "app_id=?", strArr);
            if (delete > 0) {
                zzgy.zzab().zzgs().zza("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzgy.zzab().zzgk().zza("Error resetting analytics data. appId, error", zzef.zzam(str), e);
        }
        zzn zza2 = zza(this.zzj.getContext(), zzn.packageName, zzn.zzcg, zzn.zzcq, zzn.zzcs, zzn.zzct, zzn.zzdr, zzn.zzcu);
        if (zzn.zzcq) {
            zzf(zza2);
        }
    }

    private final zzn zza(Context context, String str, String str2, boolean z, boolean z2, boolean z3, long j, String str3) {
        String str4;
        String str5;
        int i;
        String str6 = "Unknown";
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            this.zzj.zzab().zzgk().zzao("PackageManager is null, can not log app install information");
            return null;
        }
        try {
            str6 = packageManager.getInstallerPackageName(str);
        } catch (IllegalArgumentException unused) {
            this.zzj.zzab().zzgk().zza("Error retrieving installer package name. appId", zzef.zzam(str));
        }
        if (str6 == null) {
            str4 = "manual_install";
        } else {
            str4 = "com.android.vending".equals(str6) ? "" : str6;
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 0);
            if (packageInfo != null) {
                CharSequence applicationLabel = Wrappers.packageManager(context).getApplicationLabel(str);
                if (!TextUtils.isEmpty(applicationLabel)) {
                    applicationLabel.toString();
                }
                str5 = packageInfo.versionName;
                i = packageInfo.versionCode;
            } else {
                str5 = "Unknown";
                i = Integer.MIN_VALUE;
            }
            this.zzj.zzae();
            return new zzn(str, str2, str5, (long) i, str4, this.zzj.zzad().zzao(), this.zzj.zzz().zzc(context, str), (String) null, z, false, "", 0L, this.zzj.zzad().zzr(str) ? j : 0, 0, z2, z3, false, str3, (Boolean) null, 0L, (List<String>) null);
        } catch (PackageManager.NameNotFoundException unused2) {
            this.zzj.zzab().zzgk().zza("Error retrieving newly installed package info. appId, appName", zzef.zzam(str), "Unknown");
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(zzjn zzjn, zzn zzn) {
        zzae zzc;
        zzo();
        zzjj();
        if (TextUtils.isEmpty(zzn.zzcg) && TextUtils.isEmpty(zzn.zzcu)) {
            return;
        }
        if (!zzn.zzcq) {
            zzg(zzn);
            return;
        }
        int zzbm = this.zzj.zzz().zzbm(zzjn.name);
        if (zzbm != 0) {
            this.zzj.zzz();
            this.zzj.zzz().zza(zzn.packageName, zzbm, "_ev", zzjs.zza(zzjn.name, 24, true), zzjn.name != null ? zzjn.name.length() : 0);
            return;
        }
        int zzc2 = this.zzj.zzz().zzc(zzjn.name, zzjn.getValue());
        if (zzc2 != 0) {
            this.zzj.zzz();
            String zza2 = zzjs.zza(zzjn.name, 24, true);
            Object value = zzjn.getValue();
            this.zzj.zzz().zza(zzn.packageName, zzc2, "_ev", zza2, (value == null || (!(value instanceof String) && !(value instanceof CharSequence))) ? 0 : String.valueOf(value).length());
            return;
        }
        Object zzd = this.zzj.zzz().zzd(zzjn.name, zzjn.getValue());
        if (zzd != null) {
            if ("_sid".equals(zzjn.name) && this.zzj.zzad().zzw(zzn.packageName)) {
                long j = zzjn.zztr;
                String str = zzjn.origin;
                long j2 = 0;
                zzjp zze = zzgy().zze(zzn.packageName, "_sno");
                if (zze == null || !(zze.value instanceof Long)) {
                    if (zze != null) {
                        this.zzj.zzab().zzgn().zza("Retrieved last session number from database does not contain a valid (long) value", zze.value);
                    }
                    if (this.zzj.zzad().zze(zzn.packageName, zzak.zzie) && (zzc = zzgy().zzc(zzn.packageName, "_s")) != null) {
                        j2 = zzc.zzfg;
                        this.zzj.zzab().zzgs().zza("Backfill the session number. Last used session number", Long.valueOf(j2));
                    }
                } else {
                    j2 = ((Long) zze.value).longValue();
                }
                zzb(new zzjn("_sno", j, Long.valueOf(j2 + 1), str), zzn);
            }
            zzjp zzjp = new zzjp(zzn.packageName, zzjn.origin, zzjn.name, zzjn.zztr, zzd);
            this.zzj.zzab().zzgr().zza("Setting user property", this.zzj.zzy().zzal(zzjp.name), zzd);
            zzgy().beginTransaction();
            try {
                zzg(zzn);
                boolean zza3 = zzgy().zza(zzjp);
                zzgy().setTransactionSuccessful();
                if (zza3) {
                    this.zzj.zzab().zzgr().zza("User property set", this.zzj.zzy().zzal(zzjp.name), zzjp.value);
                } else {
                    this.zzj.zzab().zzgk().zza("Too many unique user properties are set. Ignoring user property", this.zzj.zzy().zzal(zzjp.name), zzjp.value);
                    this.zzj.zzz().zza(zzn.packageName, 9, (String) null, (String) null, 0);
                }
            } finally {
                zzgy().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(zzjn zzjn, zzn zzn) {
        zzo();
        zzjj();
        if (TextUtils.isEmpty(zzn.zzcg) && TextUtils.isEmpty(zzn.zzcu)) {
            return;
        }
        if (!zzn.zzcq) {
            zzg(zzn);
        } else if (!this.zzj.zzad().zze(zzn.packageName, zzak.zzij)) {
            this.zzj.zzab().zzgr().zza("Removing user property", this.zzj.zzy().zzal(zzjn.name));
            zzgy().beginTransaction();
            try {
                zzg(zzn);
                zzgy().zzd(zzn.packageName, zzjn.name);
                zzgy().setTransactionSuccessful();
                this.zzj.zzab().zzgr().zza("User property removed", this.zzj.zzy().zzal(zzjn.name));
            } finally {
                zzgy().endTransaction();
            }
        } else if (!"_npa".equals(zzjn.name) || zzn.zzcv == null) {
            this.zzj.zzab().zzgr().zza("Removing user property", this.zzj.zzy().zzal(zzjn.name));
            zzgy().beginTransaction();
            try {
                zzg(zzn);
                zzgy().zzd(zzn.packageName, zzjn.name);
                zzgy().setTransactionSuccessful();
                this.zzj.zzab().zzgr().zza("User property removed", this.zzj.zzy().zzal(zzjn.name));
            } finally {
                zzgy().endTransaction();
            }
        } else {
            this.zzj.zzab().zzgr().zzao("Falling back to manifest metadata value for ad personalization");
            zzb(new zzjn("_npa", this.zzj.zzx().currentTimeMillis(), Long.valueOf(zzn.zzcv.booleanValue() ? 1 : 0), "auto"), zzn);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzjh zzjh) {
        this.zzta++;
    }

    /* access modifiers changed from: package-private */
    public final void zzjs() {
        this.zztb++;
    }

    /* access modifiers changed from: package-private */
    public final zzfj zzjt() {
        return this.zzj;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzf(zzn zzn) {
        int i;
        zzae zzae;
        long j;
        PackageInfo packageInfo;
        boolean z;
        zzjp zze;
        zzo();
        zzjj();
        Preconditions.checkNotNull(zzn);
        Preconditions.checkNotEmpty(zzn.packageName);
        if (!TextUtils.isEmpty(zzn.zzcg) || !TextUtils.isEmpty(zzn.zzcu)) {
            zzf zzab = zzgy().zzab(zzn.packageName);
            if (zzab != null && TextUtils.isEmpty(zzab.getGmpAppId()) && !TextUtils.isEmpty(zzn.zzcg)) {
                zzab.zzl(0);
                zzgy().zza(zzab);
                zzgz().zzaz(zzn.packageName);
            }
            if (!zzn.zzcq) {
                zzg(zzn);
                return;
            }
            long j2 = zzn.zzdr;
            if (j2 == 0) {
                j2 = this.zzj.zzx().currentTimeMillis();
            }
            if (this.zzj.zzad().zze(zzn.packageName, zzak.zzij)) {
                this.zzj.zzw().zzct();
            }
            int i2 = zzn.zzds;
            if (i2 == 0 || i2 == 1) {
                i = i2;
            } else {
                this.zzj.zzab().zzgn().zza("Incorrect app type, assuming installed app. appId, appType", zzef.zzam(zzn.packageName), Integer.valueOf(i2));
                i = 0;
            }
            zzgy().beginTransaction();
            try {
                if (this.zzj.zzad().zze(zzn.packageName, zzak.zzij) && ((zze = zzgy().zze(zzn.packageName, "_npa")) == null || "auto".equals(zze.origin))) {
                    if (zzn.zzcv != null) {
                        zzjn zzjn = new zzjn("_npa", j2, Long.valueOf(zzn.zzcv.booleanValue() ? 1 : 0), "auto");
                        if (zze == null || !zze.value.equals(zzjn.zzts)) {
                            zzb(zzjn, zzn);
                        }
                    } else if (zze != null) {
                        zzc(new zzjn("_npa", j2, null, "auto"), zzn);
                    }
                }
                zzf zzab2 = zzgy().zzab(zzn.packageName);
                ApplicationInfo applicationInfo = null;
                if (zzab2 != null) {
                    this.zzj.zzz();
                    if (zzjs.zza(zzn.zzcg, zzab2.getGmpAppId(), zzn.zzcu, zzab2.zzah())) {
                        this.zzj.zzab().zzgn().zza("New GMP App Id passed in. Removing cached database data. appId", zzef.zzam(zzab2.zzag()));
                        zzx zzgy = zzgy();
                        String zzag = zzab2.zzag();
                        zzgy.zzbi();
                        zzgy.zzo();
                        Preconditions.checkNotEmpty(zzag);
                        try {
                            SQLiteDatabase writableDatabase = zzgy.getWritableDatabase();
                            String[] strArr = {zzag};
                            int delete = writableDatabase.delete("events", "app_id=?", strArr) + 0 + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("apps", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("event_filters", "app_id=?", strArr) + writableDatabase.delete("property_filters", "app_id=?", strArr) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr);
                            if (delete > 0) {
                                zzgy.zzab().zzgs().zza("Deleted application data. app, records", zzag, Integer.valueOf(delete));
                            }
                        } catch (SQLiteException e) {
                            zzgy.zzab().zzgk().zza("Error deleting application data. appId, error", zzef.zzam(zzag), e);
                        }
                        zzab2 = null;
                    }
                }
                if (zzab2 != null) {
                    if (zzab2.zzam() != -2147483648L) {
                        if (zzab2.zzam() != zzn.zzcn) {
                            Bundle bundle = new Bundle();
                            bundle.putString("_pv", zzab2.zzal());
                            zzc(new zzai("_au", new zzah(bundle), "auto", j2), zzn);
                        }
                    } else if (zzab2.zzal() != null && !zzab2.zzal().equals(zzn.zzcm)) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("_pv", zzab2.zzal());
                        zzc(new zzai("_au", new zzah(bundle2), "auto", j2), zzn);
                    }
                }
                zzg(zzn);
                if (i == 0) {
                    zzae = zzgy().zzc(zzn.packageName, "_f");
                } else {
                    zzae = i == 1 ? zzgy().zzc(zzn.packageName, "_v") : null;
                }
                if (zzae == null) {
                    long j3 = ((j2 / 3600000) + 1) * 3600000;
                    if (i == 0) {
                        j = 1;
                        zzb(new zzjn("_fot", j2, Long.valueOf(j3), "auto"), zzn);
                        if (this.zzj.zzad().zzt(zzn.zzcg)) {
                            zzo();
                            this.zzj.zzht().zzat(zzn.packageName);
                        }
                        zzo();
                        zzjj();
                        Bundle bundle3 = new Bundle();
                        bundle3.putLong("_c", 1);
                        bundle3.putLong("_r", 1);
                        bundle3.putLong("_uwa", 0);
                        bundle3.putLong("_pfo", 0);
                        bundle3.putLong("_sys", 0);
                        bundle3.putLong("_sysu", 0);
                        if (this.zzj.zzad().zzz(zzn.packageName)) {
                            bundle3.putLong("_et", 1);
                        }
                        if (zzn.zzdt) {
                            bundle3.putLong("_dac", 1);
                        }
                        if (this.zzj.getContext().getPackageManager() == null) {
                            this.zzj.zzab().zzgk().zza("PackageManager is null, first open report might be inaccurate. appId", zzef.zzam(zzn.packageName));
                        } else {
                            try {
                                packageInfo = Wrappers.packageManager(this.zzj.getContext()).getPackageInfo(zzn.packageName, 0);
                            } catch (PackageManager.NameNotFoundException e2) {
                                this.zzj.zzab().zzgk().zza("Package info is null, first open report might be inaccurate. appId", zzef.zzam(zzn.packageName), e2);
                                packageInfo = null;
                            }
                            if (!(packageInfo == null || packageInfo.firstInstallTime == 0)) {
                                if (packageInfo.firstInstallTime != packageInfo.lastUpdateTime) {
                                    bundle3.putLong("_uwa", 1);
                                    z = false;
                                } else {
                                    z = true;
                                }
                                zzb(new zzjn("_fi", j2, Long.valueOf(z ? 1 : 0), "auto"), zzn);
                            }
                            try {
                                applicationInfo = Wrappers.packageManager(this.zzj.getContext()).getApplicationInfo(zzn.packageName, 0);
                            } catch (PackageManager.NameNotFoundException e3) {
                                this.zzj.zzab().zzgk().zza("Application info is null, first open report might be inaccurate. appId", zzef.zzam(zzn.packageName), e3);
                            }
                            if (applicationInfo != null) {
                                if ((applicationInfo.flags & 1) != 0) {
                                    bundle3.putLong("_sys", 1);
                                }
                                if ((applicationInfo.flags & 128) != 0) {
                                    bundle3.putLong("_sysu", 1);
                                }
                            }
                        }
                        zzx zzgy2 = zzgy();
                        String str = zzn.packageName;
                        Preconditions.checkNotEmpty(str);
                        zzgy2.zzo();
                        zzgy2.zzbi();
                        long zzj2 = zzgy2.zzj(str, "first_open_count");
                        if (zzj2 >= 0) {
                            bundle3.putLong("_pfo", zzj2);
                        }
                        zzc(new zzai("_f", new zzah(bundle3), "auto", j2), zzn);
                    } else {
                        j = 1;
                        if (i == 1) {
                            zzb(new zzjn("_fvt", j2, Long.valueOf(j3), "auto"), zzn);
                            zzo();
                            zzjj();
                            Bundle bundle4 = new Bundle();
                            bundle4.putLong("_c", 1);
                            bundle4.putLong("_r", 1);
                            if (this.zzj.zzad().zzz(zzn.packageName)) {
                                bundle4.putLong("_et", 1);
                            }
                            if (zzn.zzdt) {
                                bundle4.putLong("_dac", 1);
                            }
                            zzc(new zzai("_v", new zzah(bundle4), "auto", j2), zzn);
                        }
                    }
                    if (!this.zzj.zzad().zze(zzn.packageName, zzak.zzii)) {
                        Bundle bundle5 = new Bundle();
                        bundle5.putLong("_et", j);
                        if (this.zzj.zzad().zzz(zzn.packageName)) {
                            bundle5.putLong("_fr", j);
                        }
                        zzc(new zzai("_e", new zzah(bundle5), "auto", j2), zzn);
                    }
                } else if (zzn.zzdq) {
                    zzc(new zzai("_cd", new zzah(new Bundle()), "auto", j2), zzn);
                }
                zzgy().setTransactionSuccessful();
            } finally {
                zzgy().endTransaction();
            }
        }
    }

    @WorkerThread
    private final zzn zzbi(String str) {
        zzf zzab = zzgy().zzab(str);
        if (zzab == null || TextUtils.isEmpty(zzab.zzal())) {
            this.zzj.zzab().zzgr().zza("No app data available; dropping", str);
            return null;
        }
        Boolean zzc = zzc(zzab);
        if (zzc == null || zzc.booleanValue()) {
            return new zzn(str, zzab.getGmpAppId(), zzab.zzal(), zzab.zzam(), zzab.zzan(), zzab.zzao(), zzab.zzap(), (String) null, zzab.isMeasurementEnabled(), false, zzab.getFirebaseInstanceId(), zzab.zzbd(), 0L, 0, zzab.zzbe(), zzab.zzbf(), false, zzab.zzah(), zzab.zzbg(), zzab.zzaq(), zzab.zzbh());
        }
        this.zzj.zzab().zzgk().zza("App version does not match; dropping. appId", zzef.zzam(str));
        return null;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zze(zzq zzq) {
        zzn zzbi = zzbi(zzq.packageName);
        if (zzbi != null) {
            zzb(zzq, zzbi);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(zzq zzq, zzn zzn) {
        Preconditions.checkNotNull(zzq);
        Preconditions.checkNotEmpty(zzq.packageName);
        Preconditions.checkNotNull(zzq.origin);
        Preconditions.checkNotNull(zzq.zzdw);
        Preconditions.checkNotEmpty(zzq.zzdw.name);
        zzo();
        zzjj();
        if (TextUtils.isEmpty(zzn.zzcg) && TextUtils.isEmpty(zzn.zzcu)) {
            return;
        }
        if (!zzn.zzcq) {
            zzg(zzn);
            return;
        }
        zzq zzq2 = new zzq(zzq);
        boolean z = false;
        zzq2.active = false;
        zzgy().beginTransaction();
        try {
            zzq zzf = zzgy().zzf(zzq2.packageName, zzq2.zzdw.name);
            if (zzf != null && !zzf.origin.equals(zzq2.origin)) {
                this.zzj.zzab().zzgn().zza("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzj.zzy().zzal(zzq2.zzdw.name), zzq2.origin, zzf.origin);
            }
            if (zzf != null && zzf.active) {
                zzq2.origin = zzf.origin;
                zzq2.creationTimestamp = zzf.creationTimestamp;
                zzq2.triggerTimeout = zzf.triggerTimeout;
                zzq2.triggerEventName = zzf.triggerEventName;
                zzq2.zzdy = zzf.zzdy;
                zzq2.active = zzf.active;
                zzq2.zzdw = new zzjn(zzq2.zzdw.name, zzf.zzdw.zztr, zzq2.zzdw.getValue(), zzf.zzdw.origin);
            } else if (TextUtils.isEmpty(zzq2.triggerEventName)) {
                zzq2.zzdw = new zzjn(zzq2.zzdw.name, zzq2.creationTimestamp, zzq2.zzdw.getValue(), zzq2.zzdw.origin);
                zzq2.active = true;
                z = true;
            }
            if (zzq2.active) {
                zzjn zzjn = zzq2.zzdw;
                zzjp zzjp = new zzjp(zzq2.packageName, zzq2.origin, zzjn.name, zzjn.zztr, zzjn.getValue());
                if (zzgy().zza(zzjp)) {
                    this.zzj.zzab().zzgr().zza("User property updated immediately", zzq2.packageName, this.zzj.zzy().zzal(zzjp.name), zzjp.value);
                } else {
                    this.zzj.zzab().zzgk().zza("(2)Too many active user properties, ignoring", zzef.zzam(zzq2.packageName), this.zzj.zzy().zzal(zzjp.name), zzjp.value);
                }
                if (z && zzq2.zzdy != null) {
                    zzd(new zzai(zzq2.zzdy, zzq2.creationTimestamp), zzn);
                }
            }
            if (zzgy().zza(zzq2)) {
                this.zzj.zzab().zzgr().zza("Conditional property added", zzq2.packageName, this.zzj.zzy().zzal(zzq2.zzdw.name), zzq2.zzdw.getValue());
            } else {
                this.zzj.zzab().zzgk().zza("Too many conditional properties, ignoring", zzef.zzam(zzq2.packageName), this.zzj.zzy().zzal(zzq2.zzdw.name), zzq2.zzdw.getValue());
            }
            zzgy().setTransactionSuccessful();
        } finally {
            zzgy().endTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzf(zzq zzq) {
        zzn zzbi = zzbi(zzq.packageName);
        if (zzbi != null) {
            zzc(zzq, zzbi);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(zzq zzq, zzn zzn) {
        Preconditions.checkNotNull(zzq);
        Preconditions.checkNotEmpty(zzq.packageName);
        Preconditions.checkNotNull(zzq.zzdw);
        Preconditions.checkNotEmpty(zzq.zzdw.name);
        zzo();
        zzjj();
        if (TextUtils.isEmpty(zzn.zzcg) && TextUtils.isEmpty(zzn.zzcu)) {
            return;
        }
        if (!zzn.zzcq) {
            zzg(zzn);
            return;
        }
        zzgy().beginTransaction();
        try {
            zzg(zzn);
            zzq zzf = zzgy().zzf(zzq.packageName, zzq.zzdw.name);
            if (zzf != null) {
                this.zzj.zzab().zzgr().zza("Removing conditional user property", zzq.packageName, this.zzj.zzy().zzal(zzq.zzdw.name));
                zzgy().zzg(zzq.packageName, zzq.zzdw.name);
                if (zzf.active) {
                    zzgy().zzd(zzq.packageName, zzq.zzdw.name);
                }
                if (zzq.zzdz != null) {
                    zzd(this.zzj.zzz().zza(zzq.packageName, zzq.zzdz.name, zzq.zzdz.zzfq != null ? zzq.zzdz.zzfq.zzcv() : null, zzf.origin, zzq.zzdz.zzfu, true, false), zzn);
                }
            } else {
                this.zzj.zzab().zzgn().zza("Conditional user property doesn't exist", zzef.zzam(zzq.packageName), this.zzj.zzy().zzal(zzq.zzdw.name));
            }
            zzgy().setTransactionSuccessful();
        } finally {
            zzgy().endTransaction();
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final zzf zzg(zzn zzn) {
        boolean z;
        zzo();
        zzjj();
        Preconditions.checkNotNull(zzn);
        Preconditions.checkNotEmpty(zzn.packageName);
        zzf zzab = zzgy().zzab(zzn.packageName);
        String zzaq = this.zzj.zzac().zzaq(zzn.packageName);
        if (zzab == null) {
            zzab = new zzf(this.zzj, zzn.packageName);
            zzab.zza(this.zzj.zzz().zzjy());
            zzab.zzd(zzaq);
            z = true;
        } else if (!zzaq.equals(zzab.zzai())) {
            zzab.zzd(zzaq);
            zzab.zza(this.zzj.zzz().zzjy());
            z = true;
        } else {
            z = false;
        }
        if (!TextUtils.equals(zzn.zzcg, zzab.getGmpAppId())) {
            zzab.zzb(zzn.zzcg);
            z = true;
        }
        if (!TextUtils.equals(zzn.zzcu, zzab.zzah())) {
            zzab.zzc(zzn.zzcu);
            z = true;
        }
        if (!TextUtils.isEmpty(zzn.zzci) && !zzn.zzci.equals(zzab.getFirebaseInstanceId())) {
            zzab.zze(zzn.zzci);
            z = true;
        }
        if (!(zzn.zzr == 0 || zzn.zzr == zzab.zzao())) {
            zzab.zzh(zzn.zzr);
            z = true;
        }
        if (!TextUtils.isEmpty(zzn.zzcm) && !zzn.zzcm.equals(zzab.zzal())) {
            zzab.zzf(zzn.zzcm);
            z = true;
        }
        if (zzn.zzcn != zzab.zzam()) {
            zzab.zzg(zzn.zzcn);
            z = true;
        }
        if (zzn.zzco != null && !zzn.zzco.equals(zzab.zzan())) {
            zzab.zzg(zzn.zzco);
            z = true;
        }
        if (zzn.zzcp != zzab.zzap()) {
            zzab.zzi(zzn.zzcp);
            z = true;
        }
        if (zzn.zzcq != zzab.isMeasurementEnabled()) {
            zzab.setMeasurementEnabled(zzn.zzcq);
            z = true;
        }
        if (!TextUtils.isEmpty(zzn.zzdp) && !zzn.zzdp.equals(zzab.zzbb())) {
            zzab.zzh(zzn.zzdp);
            z = true;
        }
        if (zzn.zzcr != zzab.zzbd()) {
            zzab.zzt(zzn.zzcr);
            z = true;
        }
        if (zzn.zzcs != zzab.zzbe()) {
            zzab.zzb(zzn.zzcs);
            z = true;
        }
        if (zzn.zzct != zzab.zzbf()) {
            zzab.zzc(zzn.zzct);
            z = true;
        }
        if (this.zzj.zzad().zze(zzn.packageName, zzak.zzij) && zzn.zzcv != zzab.zzbg()) {
            zzab.zza(zzn.zzcv);
            z = true;
        }
        if (!(zzn.zzs == 0 || zzn.zzs == zzab.zzaq())) {
            zzab.zzj(zzn.zzs);
            z = true;
        }
        if (z) {
            zzgy().zza(zzab);
        }
        return zzab;
    }

    /* access modifiers changed from: package-private */
    public final String zzh(zzn zzn) {
        try {
            return (String) this.zzj.zzaa().zza(new zzjk(this, zzn)).get(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zzj.zzab().zzgk().zza("Failed to get app instance id. appId", zzef.zzam(zzn.packageName), e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzj(boolean z) {
        zzjn();
    }
}
