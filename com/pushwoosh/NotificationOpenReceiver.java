package com.pushwoosh;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.notification.SummaryNotificationUtils;
import com.pushwoosh.notification.builder.NotificationBuilderManager;
import com.pushwoosh.repository.RepositoryModule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationOpenReceiver extends BroadcastReceiver {

    /* access modifiers changed from: private */
    public interface b {
        void a();
    }

    /* access modifiers changed from: private */
    public interface c {
        void a(List<PushMessage> list);
    }

    /* access modifiers changed from: private */
    public static class d extends AsyncTask<Void, Void, List<PushMessage>> {
        private final c a;
        private final b b;

        public d(@NonNull c cVar, @NonNull b bVar) {
            this.a = cVar;
            this.b = bVar;
        }

        private List<PushMessage> a() {
            List<Bundle> b2 = RepositoryModule.getPushBundleStorage().b();
            if (b2 == null || b2.isEmpty()) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            for (Bundle bundle : b2) {
                arrayList.add(new PushMessage(bundle));
            }
            return arrayList;
        }

        /* access modifiers changed from: protected */
        @RequiresApi(api = 23)
        /* renamed from: a */
        public List<PushMessage> doInBackground(Void... voidArr) {
            try {
                List<PushMessage> a2 = a();
                RepositoryModule.getPushBundleStorage().a();
                return a2;
            } catch (Exception unused) {
                return null;
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(List<PushMessage> list) {
            super.onPostExecute(list);
            if (list == null || list.isEmpty()) {
                this.b.a();
            } else {
                this.a.a(list);
            }
        }
    }

    /* access modifiers changed from: private */
    public static class e extends AsyncTask<Void, Void, Void> {
        private e() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            RepositoryModule.getPushBundleStorage().a();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static class f extends AsyncTask<Void, Void, Void> {
        private final long a;

        public f(long j) {
            this.a = j;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            if (AndroidPlatformModule.getApplicationContext() == null) {
                PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
                return null;
            }
            RepositoryModule.getPushBundleStorage().b(this.a);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static class g extends AsyncTask<Void, Void, Void> {
        private g() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            List<StatusBarNotification> activeNotifications;
            Notification summaryNotification;
            if (Build.VERSION.SDK_INT < 24 || (activeNotifications = NotificationBuilderManager.getActiveNotifications()) == null || activeNotifications.isEmpty() || (summaryNotification = SummaryNotificationUtils.getSummaryNotification(activeNotifications.size())) == null) {
                return null;
            }
            SummaryNotificationUtils.fireSummaryNotification(summaryNotification);
            return null;
        }
    }

    private void a(Intent intent) {
        if (intent.getBooleanExtra("is_summary_notification", false)) {
            new e().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
            return;
        }
        long longExtra = intent.getLongExtra("row_id", 0);
        if (longExtra > 0) {
            new f(longExtra).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
            new g().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }
    }

    /* access modifiers changed from: private */
    public void a(List<PushMessage> list) {
        try {
            PushwooshPlatform.getInstance().k().handleNotificationGroup(list);
        } catch (Exception e2) {
            PWLog.exception(e2);
        }
    }

    private void b(Intent intent) {
        try {
            PushwooshPlatform.getInstance().k().handleNotification(intent.getBundleExtra("pushBundle"));
        } catch (Exception e2) {
            PWLog.exception(e2);
        }
        if (intent.getIntExtra(FirebaseAnalytics.Param.GROUP_ID, 0) == 20191017) {
            new g().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }
    }

    private void c(Intent intent) {
        new d(new c() {
            /* class com.pushwoosh.$$Lambda$NotificationOpenReceiver$hj0WSJKhoCuNsFfOjT1uCK8 */

            @Override // com.pushwoosh.NotificationOpenReceiver.c
            public final void a(List list) {
                NotificationOpenReceiver.m2lambda$hj0WSJKhoCuNsFfOjT1uCK8(NotificationOpenReceiver.this, list);
            }
        }, new b(intent) {
            /* class com.pushwoosh.$$Lambda$NotificationOpenReceiver$ELQUHcKtxhjaqkIF571LfffUWSY */
            private final /* synthetic */ Intent f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.pushwoosh.NotificationOpenReceiver.b
            public final void a() {
                NotificationOpenReceiver.lambda$ELQUHcKtxhjaqkIF571LfffUWSY(NotificationOpenReceiver.this, this.f$1);
            }
        }).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void d(Intent intent) {
        b(intent);
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getBooleanExtra("is_delete_intent", false)) {
                a(intent);
            } else if (intent.getBooleanExtra("is_summary_notification", false)) {
                c(intent);
            } else {
                b(intent);
            }
        }
    }
}
