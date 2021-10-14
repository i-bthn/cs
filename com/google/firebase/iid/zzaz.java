package com.google.firebase.iid;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzaz {
    @GuardedBy("itself")
    private final zzav zzar;
    @GuardedBy("this")
    private int zzdp = 0;
    @GuardedBy("this")
    private final Map<Integer, TaskCompletionSource<Void>> zzdq = new ArrayMap();

    zzaz(zzav zzav) {
        this.zzar = zzav;
    }

    /* access modifiers changed from: package-private */
    public final synchronized Task<Void> zza(String str) {
        String zzai;
        TaskCompletionSource<Void> taskCompletionSource;
        int i;
        synchronized (this.zzar) {
            zzai = this.zzar.zzai();
            zzav zzav = this.zzar;
            StringBuilder sb = new StringBuilder(String.valueOf(zzai).length() + 1 + String.valueOf(str).length());
            sb.append(zzai);
            sb.append(",");
            sb.append(str);
            zzav.zzf(sb.toString());
        }
        taskCompletionSource = new TaskCompletionSource<>();
        Map<Integer, TaskCompletionSource<Void>> map = this.zzdq;
        if (TextUtils.isEmpty(zzai)) {
            i = 0;
        } else {
            i = zzai.split(",").length - 1;
        }
        map.put(Integer.valueOf(this.zzdp + i), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzao() {
        return zzap() != null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (zza(r5, r0) != false) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2 = r4.zzdq.remove(java.lang.Integer.valueOf(r4.zzdp));
        zzk(r0);
        r4.zzdp++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        if (r2 == null) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        r2.setResult(null);
     */
    @WorkerThread
    public final boolean zzc(FirebaseInstanceId firebaseInstanceId) {
        while (true) {
            synchronized (this) {
                String zzap = zzap();
                if (zzap == null) {
                    if (FirebaseInstanceId.zzm()) {
                        Log.d("FirebaseInstanceId", "topic sync succeeded");
                    }
                    return true;
                }
            }
        }
    }

    @Nullable
    @GuardedBy("this")
    private final String zzap() {
        String zzai;
        synchronized (this.zzar) {
            zzai = this.zzar.zzai();
        }
        if (TextUtils.isEmpty(zzai)) {
            return null;
        }
        String[] split = zzai.split(",");
        if (split.length <= 1 || TextUtils.isEmpty(split[1])) {
            return null;
        }
        return split[1];
    }

    private final synchronized boolean zzk(String str) {
        synchronized (this.zzar) {
            String zzai = this.zzar.zzai();
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (!zzai.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                return false;
            }
            String valueOf3 = String.valueOf(",");
            String valueOf4 = String.valueOf(str);
            this.zzar.zzf(zzai.substring((valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).length()));
            return true;
        }
    }

    @WorkerThread
    private static boolean zza(FirebaseInstanceId firebaseInstanceId, String str) {
        String[] split = str.split("!");
        if (split.length == 2) {
            String str2 = split[0];
            String str3 = split[1];
            char c = 65535;
            try {
                int hashCode = str2.hashCode();
                if (hashCode != 83) {
                    if (hashCode == 85) {
                        if (str2.equals("U")) {
                            c = 1;
                        }
                    }
                } else if (str2.equals("S")) {
                    c = 0;
                }
                switch (c) {
                    case 0:
                        firebaseInstanceId.zzb(str3);
                        if (FirebaseInstanceId.zzm()) {
                            Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                            break;
                        }
                        break;
                    case 1:
                        firebaseInstanceId.zzc(str3);
                        if (FirebaseInstanceId.zzm()) {
                            Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                            break;
                        }
                        break;
                }
            } catch (IOException e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("FirebaseInstanceId", valueOf.length() != 0 ? "Topic sync failed: ".concat(valueOf) : new String("Topic sync failed: "));
                return false;
            }
        }
        return true;
    }
}
