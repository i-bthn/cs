package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
@WorkerThread
public final class zzhn implements Runnable {
    private final String packageName;
    private final URL url;
    private final byte[] zzlc = null;
    private final Map<String, String> zzle;
    private final zzhk zzqm;
    private final /* synthetic */ zzhl zzqn;

    public zzhn(zzhl zzhl, String str, URL url2, byte[] bArr, Map<String, String> map, zzhk zzhk) {
        this.zzqn = zzhl;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url2);
        Preconditions.checkNotNull(zzhk);
        this.url = url2;
        this.zzqm = zzhk;
        this.packageName = str;
        this.zzle = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x007d  */
    public final void run() {
        int i;
        IOException e;
        HttpURLConnection httpURLConnection;
        Map<String, List<String>> map;
        int i2;
        Throwable th;
        Map<String, List<String>> map2;
        int responseCode;
        Map<String, List<String>> headerFields;
        this.zzqn.zzn();
        try {
            httpURLConnection = this.zzqn.zza(this.url);
            try {
                if (this.zzle != null) {
                    for (Map.Entry<String, String> entry : this.zzle.entrySet()) {
                        httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                responseCode = httpURLConnection.getResponseCode();
            } catch (IOException e2) {
                e = e2;
                map = null;
                i = 0;
                if (httpURLConnection != null) {
                }
                zza(i, e, null, map);
            } catch (Throwable th2) {
                th = th2;
                map2 = null;
                i2 = 0;
                if (httpURLConnection != null) {
                }
                zza(i2, null, null, map2);
                throw th;
            }
            try {
                headerFields = httpURLConnection.getHeaderFields();
            } catch (IOException e3) {
                e = e3;
                i = responseCode;
                map = null;
                if (httpURLConnection != null) {
                }
                zza(i, e, null, map);
            } catch (Throwable th3) {
                th = th3;
                i2 = responseCode;
                map2 = null;
                if (httpURLConnection != null) {
                }
                zza(i2, null, null, map2);
                throw th;
            }
            try {
                zzhl zzhl = this.zzqn;
                byte[] bArr = zzhl.zza(httpURLConnection);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                zza(responseCode, null, bArr, headerFields);
            } catch (IOException e4) {
                i = responseCode;
                map = headerFields;
                e = e4;
                if (httpURLConnection != null) {
                }
                zza(i, e, null, map);
            } catch (Throwable th4) {
                i2 = responseCode;
                map2 = headerFields;
                th = th4;
                if (httpURLConnection != null) {
                }
                zza(i2, null, null, map2);
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            map = null;
            httpURLConnection = null;
            i = 0;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            zza(i, e, null, map);
        } catch (Throwable th5) {
            th = th5;
            map2 = null;
            httpURLConnection = null;
            i2 = 0;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            zza(i2, null, null, map2);
            throw th;
        }
    }

    private final void zza(int i, Exception exc, byte[] bArr, Map<String, List<String>> map) {
        this.zzqn.zzaa().zza(new zzhm(this, i, exc, bArr, map));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(int i, Exception exc, byte[] bArr, Map map) {
        this.zzqm.zza(this.packageName, i, exc, bArr, map);
    }
}
