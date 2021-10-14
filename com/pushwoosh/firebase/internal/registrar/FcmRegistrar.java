package com.pushwoosh.firebase.internal.registrar;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import com.pushwoosh.PushwooshWorkManagerHelper;
import com.pushwoosh.firebase.internal.checker.FirebaseChecker;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import com.pushwoosh.internal.registrar.PushRegistrar;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.RepositoryModule;

public class FcmRegistrar implements PushRegistrar {
    private b a;

    private static class b {
        @Nullable
        private final Context a;
        private final RegistrationPrefs b;

        private b() {
            this.a = AndroidPlatformModule.getApplicationContext();
            this.b = RepositoryModule.getRegistrationPreferences();
        }

        static void a(@NonNull Context context) {
            try {
                context.getPackageManager().getPermissionInfo("com.google.android.c2dm.permission.RECEIVE", 4096);
            } catch (PackageManager.NameNotFoundException unused) {
                throw new IllegalStateException("Application does not define permission com.google.android.c2dm.permission.RECEIVE");
            }
        }

        /* access modifiers changed from: package-private */
        public void a() {
            PushwooshWorkManagerHelper.enqueueOneTimeUniqueWork((OneTimeWorkRequest) ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(FcmRegistrarWorker.class).setInputData(new Data.Builder().putBoolean("DATA_REGISTER", true).build())).setConstraints(PushwooshWorkManagerHelper.getNetworkAvailableConstraints())).build(), "FcmRegistrarWorker", ExistingWorkPolicy.REPLACE);
        }

        /* access modifiers changed from: package-private */
        public void a(String str) throws Exception {
            String str2 = this.b.projectId().get();
            GeneralUtils.checkNotNullOrEmpty(str, "mAppId");
            GeneralUtils.checkNotNullOrEmpty(str2, "mSenderId");
            Context context = this.a;
            if (context == null) {
                PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            } else {
                a(context);
            }
        }

        /* access modifiers changed from: package-private */
        public void b() {
            PushwooshWorkManagerHelper.enqueueOneTimeUniqueWork((OneTimeWorkRequest) ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(FcmRegistrarWorker.class).setInputData(new Data.Builder().putBoolean("DATA_UNREGISTER", true).build())).setConstraints(PushwooshWorkManagerHelper.getNetworkAvailableConstraints())).build(), "FcmRegistrarWorker", ExistingWorkPolicy.REPLACE);
        }
    }

    @Override // com.pushwoosh.internal.registrar.PushRegistrar
    public void checkDevice(String str) throws Exception {
        this.a.a(str);
    }

    @Override // com.pushwoosh.internal.registrar.PushRegistrar
    public void init() {
        new FirebaseChecker().check();
        this.a = new b();
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
