package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.RequiresApi;

public class zzcb {
    @GuardedBy("DirectBootUtils.class")
    private static UserManager zzaan;
    private static volatile boolean zzaao = (!zzri());
    @GuardedBy("DirectBootUtils.class")
    private static boolean zzaap = false;

    private zzcb() {
    }

    public static boolean zzri() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean isUserUnlocked(Context context) {
        return !zzri() || zzo(context);
    }

    @RequiresApi(24)
    @TargetApi(24)
    @GuardedBy("DirectBootUtils.class")
    private static boolean zzn(Context context) {
        boolean z;
        int i = 1;
        while (true) {
            z = false;
            if (i > 2) {
                break;
            }
            if (zzaan == null) {
                zzaan = (UserManager) context.getSystemService(UserManager.class);
            }
            UserManager userManager = zzaan;
            if (userManager == null) {
                return true;
            }
            try {
                if (userManager.isUserUnlocked() || !userManager.isUserRunning(Process.myUserHandle())) {
                    z = true;
                }
            } catch (NullPointerException e) {
                Log.w("DirectBootUtils", "Failed to check if user is unlocked.", e);
                zzaan = null;
                i++;
            }
        }
        if (z) {
            zzaan = null;
        }
        return z;
    }

    @RequiresApi(24)
    @TargetApi(24)
    private static boolean zzo(Context context) {
        if (zzaao) {
            return true;
        }
        synchronized (zzcb.class) {
            if (zzaao) {
                return true;
            }
            boolean zzn = zzn(context);
            if (zzn) {
                zzaao = zzn;
            }
            return zzn;
        }
    }
}
