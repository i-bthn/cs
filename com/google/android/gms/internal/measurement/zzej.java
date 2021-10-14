package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzej {
    private static final Class<?> zzaeq = zztl();

    private static Class<?> zztl() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzel zztm() {
        if (zzaeq != null) {
            try {
                return zzdu("getEmptyRegistry");
            } catch (Exception unused) {
            }
        }
        return zzel.zzaev;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0014  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x000e  */
    static zzel zztn() {
        zzel zzel;
        if (zzaeq != null) {
            try {
                zzel = zzdu("loadGeneratedRegistry");
            } catch (Exception unused) {
            }
            if (zzel == null) {
                zzel = zzel.zztn();
            }
            return zzel != null ? zztm() : zzel;
        }
        zzel = null;
        if (zzel == null) {
        }
        if (zzel != null) {
        }
    }

    private static final zzel zzdu(String str) throws Exception {
        return (zzel) zzaeq.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
    }
}
