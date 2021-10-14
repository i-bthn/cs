package com.google.android.gms.internal.measurement;

final /* synthetic */ class zzes {
    static final /* synthetic */ int[] zzafe = new int[zzev.values().length];
    static final /* synthetic */ int[] zzaff = new int[zzfk.values().length];

    /* JADX WARNING: Can't wrap try/catch for region: R(14:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|20) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0047 */
    static {
        try {
            zzaff[zzfk.BYTE_STRING.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzaff[zzfk.MESSAGE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzaff[zzfk.STRING.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        zzafe[zzev.MAP.ordinal()] = 1;
        zzafe[zzev.VECTOR.ordinal()] = 2;
        zzafe[zzev.SCALAR.ordinal()] = 3;
    }
}
