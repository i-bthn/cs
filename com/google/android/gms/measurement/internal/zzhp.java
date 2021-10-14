package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzey;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* access modifiers changed from: package-private */
public final class zzhp extends zzjh {
    public zzhp(zzjg zzjg) {
        super(zzjg);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.measurement.internal.zzjh
    public final boolean zzbk() {
        return false;
    }

    @WorkerThread
    public final byte[] zzb(@NonNull zzai zzai, @Size(min = 1) String str) {
        zzjp zzjp;
        List<zzjp> list;
        zzbs.zzf.zza zza;
        zzbs.zzg.zza zza2;
        zzf zzf;
        byte[] bArr;
        Bundle bundle;
        zzae zzae;
        long j;
        zzo();
        this.zzj.zzl();
        Preconditions.checkNotNull(zzai);
        Preconditions.checkNotEmpty(str);
        if (!zzad().zze(str, zzak.zzio)) {
            zzab().zzgr().zza("Generating ScionPayload disabled. packageName", str);
            return new byte[0];
        } else if ("_iap".equals(zzai.name) || "_iapx".equals(zzai.name)) {
            zzbs.zzf.zza zznj = zzbs.zzf.zznj();
            zzgy().beginTransaction();
            try {
                zzf zzab = zzgy().zzab(str);
                if (zzab == null) {
                    zzab().zzgr().zza("Log and bundle not available. package_name", str);
                    return new byte[0];
                } else if (!zzab.isMeasurementEnabled()) {
                    zzab().zzgr().zza("Log and bundle disabled. package_name", str);
                    byte[] bArr2 = new byte[0];
                    zzgy().endTransaction();
                    return bArr2;
                } else {
                    zzbs.zzg.zza zzcc = zzbs.zzg.zzpr().zzp(1).zzcc("android");
                    if (!TextUtils.isEmpty(zzab.zzag())) {
                        zzcc.zzch(zzab.zzag());
                    }
                    if (!TextUtils.isEmpty(zzab.zzan())) {
                        zzcc.zzcg(zzab.zzan());
                    }
                    if (!TextUtils.isEmpty(zzab.zzal())) {
                        zzcc.zzci(zzab.zzal());
                    }
                    if (zzab.zzam() != -2147483648L) {
                        zzcc.zzv((int) zzab.zzam());
                    }
                    zzcc.zzas(zzab.zzao()).zzax(zzab.zzaq());
                    if (!TextUtils.isEmpty(zzab.getGmpAppId())) {
                        zzcc.zzcm(zzab.getGmpAppId());
                    } else if (!TextUtils.isEmpty(zzab.zzah())) {
                        zzcc.zzcq(zzab.zzah());
                    }
                    zzcc.zzau(zzab.zzap());
                    if (this.zzj.isEnabled() && zzs.zzbv() && zzad().zzl(zzcc.zzag())) {
                        zzcc.zzag();
                        if (!TextUtils.isEmpty(null)) {
                            zzcc.zzcp(null);
                        }
                    }
                    Pair<String, Boolean> zzap = zzac().zzap(zzab.zzag());
                    if (zzab.zzbe() && zzap != null && !TextUtils.isEmpty((CharSequence) zzap.first)) {
                        try {
                            zzcc.zzcj(zzo((String) zzap.first, Long.toString(zzai.zzfu)));
                            if (zzap.second != null) {
                                zzcc.zzm(((Boolean) zzap.second).booleanValue());
                            }
                        } catch (SecurityException e) {
                            zzab().zzgr().zza("Resettable device id encryption failed", e.getMessage());
                            byte[] bArr3 = new byte[0];
                            zzgy().endTransaction();
                            return bArr3;
                        }
                    }
                    zzw().zzbi();
                    zzbs.zzg.zza zzce = zzcc.zzce(Build.MODEL);
                    zzw().zzbi();
                    zzce.zzcd(Build.VERSION.RELEASE).zzt((int) zzw().zzcq()).zzcf(zzw().zzcr());
                    try {
                        zzcc.zzck(zzo(zzab.getAppInstanceId(), Long.toString(zzai.zzfu)));
                        if (!TextUtils.isEmpty(zzab.getFirebaseInstanceId())) {
                            zzcc.zzcn(zzab.getFirebaseInstanceId());
                        }
                        String zzag = zzab.zzag();
                        List<zzjp> zzaa = zzgy().zzaa(zzag);
                        Iterator<zzjp> it = zzaa.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                zzjp = null;
                                break;
                            }
                            zzjp = it.next();
                            if ("_lte".equals(zzjp.name)) {
                                break;
                            }
                        }
                        if (zzjp == null || zzjp.value == null) {
                            list = zzaa;
                            zzjp zzjp2 = new zzjp(zzag, "auto", "_lte", zzx().currentTimeMillis(), 0L);
                            list.add(zzjp2);
                            zzgy().zza(zzjp2);
                        } else {
                            list = zzaa;
                        }
                        if (zzad().zze(zzag, zzak.zzij)) {
                            zzjo zzgw = zzgw();
                            zzgw.zzab().zzgs().zzao("Checking account type status for ad personalization signals");
                            if (zzgw.zzw().zzcu()) {
                                String zzag2 = zzab.zzag();
                                if (zzab.zzbe() && zzgw.zzgz().zzba(zzag2)) {
                                    zzgw.zzab().zzgr().zzao("Turning off ad personalization due to account type");
                                    Iterator<zzjp> it2 = list.iterator();
                                    while (true) {
                                        if (it2.hasNext()) {
                                            if ("_npa".equals(it2.next().name)) {
                                                it2.remove();
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                    list.add(new zzjp(zzag2, "auto", "_npa", zzgw.zzx().currentTimeMillis(), 1L));
                                }
                            }
                        }
                        zzbs.zzk[] zzkArr = new zzbs.zzk[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            zzbs.zzk.zza zzbk = zzbs.zzk.zzqu().zzdb(list.get(i).name).zzbk(list.get(i).zztr);
                            zzgw().zza(zzbk, list.get(i).value);
                            zzkArr[i] = (zzbs.zzk) ((zzey) zzbk.zzug());
                        }
                        zzcc.zzb(Arrays.asList(zzkArr));
                        Bundle zzcv = zzai.zzfq.zzcv();
                        zzcv.putLong("_c", 1);
                        zzab().zzgr().zzao("Marking in-app purchase as real-time");
                        zzcv.putLong("_r", 1);
                        zzcv.putString("_o", zzai.origin);
                        if (zzz().zzbr(zzcc.zzag())) {
                            zzz().zza(zzcv, "_dbg", (Object) 1L);
                            zzz().zza(zzcv, "_r", (Object) 1L);
                        }
                        zzae zzc = zzgy().zzc(str, zzai.name);
                        if (zzc == null) {
                            zzf = zzab;
                            zza2 = zzcc;
                            zza = zznj;
                            bundle = zzcv;
                            bArr = null;
                            j = 0;
                            zzae = new zzae(str, zzai.name, 0, 0, zzai.zzfu, 0, null, null, null, null);
                        } else {
                            zzf = zzab;
                            zza2 = zzcc;
                            zza = zznj;
                            bundle = zzcv;
                            bArr = null;
                            j = zzc.zzfj;
                            zzae = zzc.zzw(zzai.zzfu);
                        }
                        zzgy().zza(zzae);
                        zzaf zzaf = new zzaf(this.zzj, zzai.origin, str, zzai.name, zzai.zzfu, j, bundle);
                        zzbs.zzc.zza zzah = zzbs.zzc.zzmq().zzag(zzaf.timestamp).zzbx(zzaf.name).zzah(zzaf.zzfp);
                        Iterator<String> it3 = zzaf.zzfq.iterator();
                        while (it3.hasNext()) {
                            String next = it3.next();
                            zzbs.zze.zza zzbz = zzbs.zze.zzng().zzbz(next);
                            zzgw().zza(zzbz, zzaf.zzfq.get(next));
                            zzah.zza(zzbz);
                        }
                        zza2.zza(zzah).zza(zzbs.zzh.zzpt().zza(zzbs.zzd.zzms().zzak(zzae.zzfg).zzby(zzai.name)));
                        zza2.zzc(zzgx().zza(zzf.zzag(), Collections.emptyList(), zza2.zzno()));
                        if (zzah.zzml()) {
                            zza2.zzao(zzah.getTimestampMillis()).zzap(zzah.getTimestampMillis());
                        }
                        long zzak = zzf.zzak();
                        if (zzak != 0) {
                            zza2.zzar(zzak);
                        }
                        long zzaj = zzf.zzaj();
                        if (zzaj != 0) {
                            zza2.zzaq(zzaj);
                        } else if (zzak != 0) {
                            zza2.zzaq(zzak);
                        }
                        zzf.zzau();
                        zza2.zzu((int) zzf.zzar()).zzat(zzad().zzao()).zzan(zzx().currentTimeMillis()).zzn(Boolean.TRUE.booleanValue());
                        zza.zza(zza2);
                        zzf.zze(zza2.zznq());
                        zzf.zzf(zza2.zznr());
                        zzgy().zza(zzf);
                        zzgy().setTransactionSuccessful();
                        zzgy().endTransaction();
                        try {
                            return zzgw().zzc(((zzbs.zzf) ((zzey) zza.zzug())).toByteArray());
                        } catch (IOException e2) {
                            zzab().zzgk().zza("Data loss. Failed to bundle and serialize. appId", zzef.zzam(str), e2);
                            return bArr;
                        }
                    } catch (SecurityException e3) {
                        zzab().zzgr().zza("app instance id encryption failed", e3.getMessage());
                        byte[] bArr4 = new byte[0];
                        zzgy().endTransaction();
                        return bArr4;
                    }
                }
            } finally {
                zzgy().endTransaction();
            }
        } else {
            zzab().zzgr().zza("Generating a payload for this event is not available. package_name, event_name", str, zzai.name);
            return null;
        }
    }

    private static String zzo(String str, String str2) {
        throw new SecurityException("This implementation should not be used.");
    }
}
