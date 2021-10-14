package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
@WorkerThread
public final class zzen implements Runnable {
    private final String packageName;
    private final URL url;
    private final byte[] zzlc;
    private final zzel zzld;
    private final Map<String, String> zzle;
    private final /* synthetic */ zzej zzlf;

    public zzen(zzej zzej, String str, URL url2, byte[] bArr, Map<String, String> map, zzel zzel) {
        this.zzlf = zzej;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url2);
        Preconditions.checkNotNull(zzel);
        this.url = url2;
        this.zzlc = bArr;
        this.zzld = zzel;
        this.packageName = str;
        this.zzle = map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c4 A[SYNTHETIC, Splitter:B:43:0x00c4] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0101 A[SYNTHETIC, Splitter:B:56:0x0101] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x011d  */
    public final void run() {
        Map<String, List<String>> map;
        IOException iOException;
        int i;
        HttpURLConnection httpURLConnection;
        IOException e;
        Throwable th;
        IOException e2;
        this.zzlf.zzn();
        OutputStream outputStream = null;
        try {
            httpURLConnection = this.zzlf.zza(this.url);
            try {
                if (this.zzle != null) {
                    for (Map.Entry<String, String> entry : this.zzle.entrySet()) {
                        httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                if (this.zzlc != null) {
                    byte[] zzc = this.zzlf.zzgw().zzc(this.zzlc);
                    this.zzlf.zzab().zzgs().zza("Uploading data. size", Integer.valueOf(zzc.length));
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.addRequestProperty("Content-Encoding", "gzip");
                    httpURLConnection.setFixedLengthStreamingMode(zzc.length);
                    httpURLConnection.connect();
                    OutputStream outputStream2 = httpURLConnection.getOutputStream();
                    try {
                        outputStream2.write(zzc);
                        outputStream2.close();
                    } catch (IOException e3) {
                        map = null;
                        iOException = e3;
                        outputStream = outputStream2;
                    } catch (Throwable th2) {
                        th = th2;
                        map = null;
                        outputStream = outputStream2;
                        i = 0;
                        if (outputStream != null) {
                        }
                        if (httpURLConnection != null) {
                        }
                        this.zzlf.zzaa().zza(new zzek(this.packageName, this.zzld, i, null, null, map));
                        throw th;
                    }
                }
                i = httpURLConnection.getResponseCode();
            } catch (IOException e4) {
                e = e4;
                map = null;
                iOException = e;
                i = 0;
                if (outputStream != null) {
                }
                if (httpURLConnection != null) {
                }
                this.zzlf.zzaa().zza(new zzek(this.packageName, this.zzld, i, iOException, null, map));
            } catch (Throwable th3) {
                th = th3;
                map = null;
                i = 0;
                if (outputStream != null) {
                }
                if (httpURLConnection != null) {
                }
                this.zzlf.zzaa().zza(new zzek(this.packageName, this.zzld, i, null, null, map));
                throw th;
            }
            try {
                map = httpURLConnection.getHeaderFields();
                try {
                    zzej zzej = this.zzlf;
                    byte[] bArr = zzej.zza(httpURLConnection);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    this.zzlf.zzaa().zza(new zzek(this.packageName, this.zzld, i, null, bArr, map));
                } catch (IOException e5) {
                    e2 = e5;
                    iOException = e2;
                    if (outputStream != null) {
                    }
                    if (httpURLConnection != null) {
                    }
                    this.zzlf.zzaa().zza(new zzek(this.packageName, this.zzld, i, iOException, null, map));
                } catch (Throwable th4) {
                    th = th4;
                    if (outputStream != null) {
                    }
                    if (httpURLConnection != null) {
                    }
                    this.zzlf.zzaa().zza(new zzek(this.packageName, this.zzld, i, null, null, map));
                    throw th;
                }
            } catch (IOException e6) {
                e2 = e6;
                map = null;
                iOException = e2;
                if (outputStream != null) {
                }
                if (httpURLConnection != null) {
                }
                this.zzlf.zzaa().zza(new zzek(this.packageName, this.zzld, i, iOException, null, map));
            } catch (Throwable th5) {
                th = th5;
                map = null;
                if (outputStream != null) {
                }
                if (httpURLConnection != null) {
                }
                this.zzlf.zzaa().zza(new zzek(this.packageName, this.zzld, i, null, null, map));
                throw th;
            }
        } catch (IOException e7) {
            e = e7;
            httpURLConnection = null;
            map = null;
            iOException = e;
            i = 0;
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e8) {
                    this.zzlf.zzab().zzgk().zza("Error closing HTTP compressed POST connection output stream. appId", zzef.zzam(this.packageName), e8);
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            this.zzlf.zzaa().zza(new zzek(this.packageName, this.zzld, i, iOException, null, map));
        } catch (Throwable th6) {
            th = th6;
            httpURLConnection = null;
            map = null;
            i = 0;
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e9) {
                    this.zzlf.zzab().zzgk().zza("Error closing HTTP compressed POST connection output stream. appId", zzef.zzam(this.packageName), e9);
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            this.zzlf.zzaa().zza(new zzek(this.packageName, this.zzld, i, null, null, map));
            throw th;
        }
    }
}
