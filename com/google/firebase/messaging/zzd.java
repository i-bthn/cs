package com.google.firebase.messaging;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_messaging.zzj;
import com.google.android.gms.internal.firebase_messaging.zzk;
import com.google.android.gms.internal.firebase_messaging.zzn;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;

/* access modifiers changed from: package-private */
public final class zzd implements Closeable {
    private final URL url;
    @Nullable
    private Task<Bitmap> zzea;
    @Nullable
    private volatile InputStream zzeb;

    @Nullable
    public static zzd zzo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new zzd(new URL(str));
        } catch (MalformedURLException unused) {
            String valueOf = String.valueOf(str);
            Log.w("FirebaseMessaging", valueOf.length() != 0 ? "Not downloading image, bad URL: ".concat(valueOf) : new String("Not downloading image, bad URL: "));
            return null;
        }
    }

    private zzd(URL url2) {
        this.url = url2;
    }

    public final void zza(Executor executor) {
        this.zzea = Tasks.call(executor, new zze(this));
    }

    public final Task<Bitmap> getTask() {
        return (Task) Preconditions.checkNotNull(this.zzea);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00a4, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00a5, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a9, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00aa, code lost:
        r4 = r2;
        r2 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00b1, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b6, code lost:
        if (r0 != null) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b8, code lost:
        zza(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00bb, code lost:
        throw r1;
     */
    public final Bitmap zzat() throws IOException {
        Throwable th;
        Throwable th2;
        String valueOf = String.valueOf(this.url);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
        sb.append("Starting download of: ");
        sb.append(valueOf);
        Log.i("FirebaseMessaging", sb.toString());
        try {
            InputStream inputStream = this.url.openConnection().getInputStream();
            InputStream zza = zzj.zza(inputStream, PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
            this.zzeb = inputStream;
            Bitmap decodeStream = BitmapFactory.decodeStream(zza);
            if (decodeStream != null) {
                if (Log.isLoggable("FirebaseMessaging", 3)) {
                    String valueOf2 = String.valueOf(this.url);
                    StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 31);
                    sb2.append("Successfully downloaded image: ");
                    sb2.append(valueOf2);
                    Log.d("FirebaseMessaging", sb2.toString());
                }
                zza(null, zza);
                if (inputStream != null) {
                    zza(null, inputStream);
                }
                return decodeStream;
            }
            String valueOf3 = String.valueOf(this.url);
            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 24);
            sb3.append("Failed to decode image: ");
            sb3.append(valueOf3);
            String sb4 = sb3.toString();
            Log.w("FirebaseMessaging", sb4);
            throw new IOException(sb4);
            zza(th, zza);
            throw th2;
        } catch (IOException e) {
            String valueOf4 = String.valueOf(this.url);
            StringBuilder sb5 = new StringBuilder(String.valueOf(valueOf4).length() + 26);
            sb5.append("Failed to download image: ");
            sb5.append(valueOf4);
            Log.w("FirebaseMessaging", sb5.toString());
            throw e;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        zzk.zza(this.zzeb);
    }

    private static /* synthetic */ void zza(Throwable th, InputStream inputStream) {
        if (th != null) {
            try {
                inputStream.close();
            } catch (Throwable th2) {
                zzn.zza(th, th2);
            }
        } else {
            inputStream.close();
        }
    }
}
