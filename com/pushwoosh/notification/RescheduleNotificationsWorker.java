package com.pushwoosh.notification;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.h;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.b;
import com.pushwoosh.repository.c;

public class RescheduleNotificationsWorker extends Worker {
    public RescheduleNotificationsWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    private static long a() {
        h timeProvider = AndroidPlatformModule.getInstance().getTimeProvider();
        return timeProvider != null ? timeProvider.b() : System.currentTimeMillis();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(long j, b bVar) {
        Bundle a = bVar.a();
        PWLog.debug("RescheduleNotificationsWorker", "Rescheduling local push: " + a.toString());
        LocalNotificationReceiver.rescheduleNotification(bVar, j);
    }

    @Override // androidx.work.Worker
    @NonNull
    public ListenableWorker.Result doWork() {
        RepositoryModule.getLocalNotificationStorage().a(new c.a(a()) {
            /* class com.pushwoosh.notification.$$Lambda$RescheduleNotificationsWorker$bndvRkknD_nTrm3zAZdVbGLOPFA */
            private final /* synthetic */ long f$0;

            {
                this.f$0 = r1;
            }

            @Override // com.pushwoosh.repository.c.a
            public final void a(b bVar) {
                RescheduleNotificationsWorker.a(this.f$0, bVar);
            }
        });
        return ListenableWorker.Result.success();
    }
}
