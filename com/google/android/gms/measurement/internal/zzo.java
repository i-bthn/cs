package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzbk;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzo {
    static final /* synthetic */ int[] zzdu = new int[zzbk.zze.zza.values().length];
    static final /* synthetic */ int[] zzdv = new int[zzbk.zzc.zzb.values().length];

    /* JADX WARNING: Can't wrap try/catch for region: R(20:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|(3:27|28|30)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|(3:27|28|30)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(23:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|(3:27|28|30)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0048 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0052 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0066 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0071 */
    static {
        try {
            zzdv[zzbk.zzc.zzb.LESS_THAN.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzdv[zzbk.zzc.zzb.GREATER_THAN.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzdv[zzbk.zzc.zzb.EQUAL.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zzdv[zzbk.zzc.zzb.BETWEEN.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        zzdu[zzbk.zze.zza.REGEXP.ordinal()] = 1;
        zzdu[zzbk.zze.zza.BEGINS_WITH.ordinal()] = 2;
        zzdu[zzbk.zze.zza.ENDS_WITH.ordinal()] = 3;
        zzdu[zzbk.zze.zza.PARTIAL.ordinal()] = 4;
        zzdu[zzbk.zze.zza.EXACT.ordinal()] = 5;
        try {
            zzdu[zzbk.zze.zza.IN_LIST.ordinal()] = 6;
        } catch (NoSuchFieldError unused5) {
        }
    }
}
