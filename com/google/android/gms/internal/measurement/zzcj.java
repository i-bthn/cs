package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.core.content.PermissionChecker;

/* access modifiers changed from: package-private */
public final class zzcj implements zzce {
    @GuardedBy("GservicesLoader.class")
    static zzcj zzaau;
    private final Context zzob;

    static zzcj zzp(Context context) {
        zzcj zzcj;
        synchronized (zzcj.class) {
            if (zzaau == null) {
                zzaau = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzcj(context) : new zzcj();
            }
            zzcj = zzaau;
        }
        return zzcj;
    }

    private zzcj(Context context) {
        this.zzob = context;
        this.zzob.getContentResolver().registerContentObserver(zzbz.CONTENT_URI, true, new zzcl(this, null));
    }

    private zzcj() {
        this.zzob = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzde */
    public final String zzdd(String str) {
        if (this.zzob == null) {
            return null;
        }
        try {
            return (String) zzch.zza(new zzci(this, str));
        } catch (SecurityException e) {
            String valueOf = String.valueOf(str);
            Log.e("GservicesLoader", valueOf.length() != 0 ? "Unable to read GServices for: ".concat(valueOf) : new String("Unable to read GServices for: "), e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zzdf(String str) {
        return zzbz.zza(this.zzob.getContentResolver(), str, (String) null);
    }
}
