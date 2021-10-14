package com.pushwoosh.internal.platform.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;
import java.io.File;
import java.lang.ref.WeakReference;

public class b implements a {
    private final WeakReference<Context> a;

    public b(@Nullable Context context) {
        this.a = new WeakReference<>(context);
    }

    @Nullable
    private Context i() {
        return this.a.get();
    }

    @Override // com.pushwoosh.internal.platform.c.a
    public String a() {
        return i() == null ? "" : i().getPackageName();
    }

    @Override // com.pushwoosh.internal.platform.c.a
    public String b() {
        return i() == null ? "" : Settings.Secure.getString(i().getContentResolver(), "android_id");
    }

    @Override // com.pushwoosh.internal.platform.c.a
    public File c() {
        if (i() == null) {
            return null;
        }
        return i().getExternalCacheDir();
    }

    @Override // com.pushwoosh.internal.platform.c.a
    @Nullable
    public String d() {
        try {
            if (i() == null) {
                return null;
            }
            return i().getPackageManager().getPackageInfo(a(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            PWLog.exception(e);
            return null;
        }
    }

    @Override // com.pushwoosh.internal.platform.c.a
    public CharSequence e() {
        if (i() == null) {
            return null;
        }
        return i().getPackageManager().getApplicationLabel(i().getApplicationInfo());
    }

    @Override // com.pushwoosh.internal.platform.c.a
    public int f() {
        try {
            if (i() == null) {
                return 0;
            }
            return i().getPackageManager().getPackageInfo(a(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            PWLog.exception(e);
            return 0;
        }
    }

    @Override // com.pushwoosh.internal.platform.c.a
    public String g() {
        if (i() == null) {
            return null;
        }
        return i().getPackageManager().getInstallerPackageName(a());
    }

    @Override // com.pushwoosh.internal.platform.c.a
    @Nullable
    public ApplicationInfo h() {
        try {
            if (i() == null) {
                return null;
            }
            return i().getPackageManager().getApplicationInfo(i().getPackageName(), 128);
        } catch (Exception e) {
            PWLog.exception(e);
            return null;
        }
    }
}
