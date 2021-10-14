package com.pushwoosh.amazon.internal.registrar;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.device.messaging.ADM;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import com.pushwoosh.internal.registrar.PushRegistrar;
import com.pushwoosh.internal.utils.PWLog;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdmRegistrar implements PushRegistrar {
    private a a;

    private static class a {
        private final ADM a = new ADM(this.b);
        @Nullable
        private Context b = AndroidPlatformModule.getApplicationContext();

        a() {
        }

        static void a(@NonNull Context context) {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            String str = packageName + ".permission.RECEIVE_ADM_MESSAGE";
            try {
                packageManager.getPermissionInfo(str, 4096);
                try {
                    ActivityInfo[] activityInfoArr = packageManager.getPackageInfo(packageName, 2).receivers;
                    if (activityInfoArr == null || activityInfoArr.length == 0) {
                        throw new IllegalStateException("No receiver for package " + packageName);
                    }
                    if (PWLog.isLoggable("ADMRegistrar", 2)) {
                        PWLog.noise("ADMRegistrar", "number of receivers for " + packageName + ": " + activityInfoArr.length);
                    }
                    HashSet hashSet = new HashSet();
                    for (ActivityInfo activityInfo : activityInfoArr) {
                        if ("com.amazon.device.messaging.permission.SEND".equals(activityInfo.permission)) {
                            hashSet.add(activityInfo.name);
                        }
                    }
                    if (!hashSet.isEmpty()) {
                        a(context, hashSet, "com.amazon.device.messaging.intent.REGISTRATION");
                        a(context, hashSet, "com.amazon.device.messaging.intent.RECEIVE");
                        return;
                    }
                    throw new IllegalStateException("No receiver allowed to receive com.amazon.device.messaging.permission.SEND");
                } catch (PackageManager.NameNotFoundException unused) {
                    throw new IllegalStateException("Could not get receivers for package " + packageName);
                }
            } catch (PackageManager.NameNotFoundException unused2) {
                throw new IllegalStateException("Application does not define permission " + str);
            }
        }

        private static void a(@NonNull Context context, @NonNull Set<String> set, @Nullable String str) {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            Intent intent = new Intent(str);
            intent.setPackage(packageName);
            List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 32);
            if (!queryBroadcastReceivers.isEmpty()) {
                if (PWLog.isLoggable("ADMRegistrar", 2)) {
                    PWLog.noise("ADMRegistrar", "Found " + queryBroadcastReceivers.size() + " receivers for action " + str);
                }
                for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                    String str2 = resolveInfo.activityInfo.name;
                    if (!set.contains(str2)) {
                        throw new IllegalStateException("Receiver " + str2 + " is not set with permission " + "com.amazon.device.messaging.permission.SEND");
                    }
                }
                return;
            }
            throw new IllegalStateException("No receivers for action " + str);
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.a.startRegister();
        }

        /* access modifiers changed from: package-private */
        public void a(String str) throws Exception {
            GeneralUtils.checkNotNullOrEmpty(str, "mAppId");
            if (this.a.isSupported()) {
                Context context = this.b;
                if (context == null) {
                    PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
                } else {
                    a(context);
                }
            } else {
                throw new UnsupportedOperationException("ADM is not supported on the current device");
            }
        }

        /* access modifiers changed from: package-private */
        public void b() {
            this.a.startUnregister();
        }
    }

    @Override // com.pushwoosh.internal.registrar.PushRegistrar
    public void checkDevice(String str) throws Exception {
        this.a.a(str);
    }

    @Override // com.pushwoosh.internal.registrar.PushRegistrar
    public void init() {
        this.a = new a();
    }

    @Override // com.pushwoosh.internal.registrar.PushRegistrar
    public void registerPW() {
        this.a.a();
    }

    @Override // com.pushwoosh.internal.registrar.PushRegistrar
    public void unregisterPW() {
        this.a.b();
    }
}
