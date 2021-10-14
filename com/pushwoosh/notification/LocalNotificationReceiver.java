package com.pushwoosh.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.b;
import com.pushwoosh.repository.i;

public class LocalNotificationReceiver extends BroadcastReceiver {
    public static final String EXTRA_NOTIFICATION_ID = "local_push_id";
    public static final String TAG = "LocalNotificationReceiver";
    public static final int WEEK = 604800000;

    private static class a extends AsyncTask<Void, Void, Void> {
        private final Bundle a;

        a(Bundle bundle) {
            this.a = bundle;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            PushwooshPlatform.getInstance().k().handleMessage(this.a);
            return null;
        }
    }

    private static long a(b bVar, long j) {
        return j + Math.max(5000L, bVar.e() - j);
    }

    @NonNull
    private static Intent a(Context context, int i, Bundle bundle) {
        Intent intent = new Intent(context, LocalNotificationReceiver.class);
        intent.putExtras(bundle);
        intent.putExtra(EXTRA_NOTIFICATION_ID, String.valueOf(i));
        return intent;
    }

    private static boolean a(long j, PendingIntent pendingIntent) {
        try {
            AlarmManager alarmManager = AndroidPlatformModule.getManagerProvider().getAlarmManager();
            if (alarmManager == null) {
                return false;
            }
            alarmManager.set(0, j, pendingIntent);
            return true;
        } catch (SecurityException unused) {
            PWLog.error(TAG, String.format("Too many alarms. Please clear all local alarm to continue use AlarmManager. Local notification will be skipped", new Object[0]));
            return false;
        }
    }

    private static boolean b(b bVar, long j) {
        return j - bVar.e() >= 604800000;
    }

    public static void cancelAll() {
        for (Integer num : RepositoryModule.getLocalNotificationStorage().a()) {
            try {
                cancelNotification(num.intValue());
            } catch (Exception e) {
                PWLog.exception(e);
            }
        }
    }

    public static void cancelNotification(int i) {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext == null) {
            PWLog.error(TAG, AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            return;
        }
        RepositoryModule.getLocalNotificationStorage().b(i);
        PendingIntent broadcast = PendingIntent.getBroadcast(applicationContext, i, new Intent(applicationContext, LocalNotificationReceiver.class), 134217728);
        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (alarmManager != null) {
            alarmManager.cancel(broadcast);
        }
    }

    public static void rescheduleNotification(b bVar, long j) {
        try {
            Context applicationContext = AndroidPlatformModule.getApplicationContext();
            if (applicationContext == null) {
                PWLog.error(TAG, AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            } else if (b(bVar, j)) {
                RepositoryModule.getLocalNotificationStorage().b(bVar.d());
            } else {
                int d = bVar.d();
                a(a(bVar, j), PendingIntent.getBroadcast(applicationContext, d, a(applicationContext, d, bVar.a()), 134217728));
            }
        } catch (Exception e) {
            PWLog.error(TAG, "Creation of local notification failed.", e);
        }
    }

    public static int scheduleNotification(Bundle bundle, int i) {
        try {
            Context applicationContext = AndroidPlatformModule.getApplicationContext();
            if (applicationContext == null) {
                PWLog.error(TAG, AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
                return -1;
            }
            i localNotificationStorage = RepositoryModule.getLocalNotificationStorage();
            long currentTimeMillis = System.currentTimeMillis() + (((long) i) * 1000);
            int b = localNotificationStorage.b();
            Intent a2 = a(applicationContext, b, bundle);
            PendingIntent broadcast = PendingIntent.getBroadcast(applicationContext, b, a2, 134217728);
            localNotificationStorage.a(b, a2.getExtras(), currentTimeMillis);
            if (a(currentTimeMillis, broadcast)) {
                return b;
            }
            return -1;
        } catch (Exception e) {
            PWLog.error(TAG, "Creation of local notification failed.", e);
            return -1;
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    RepositoryModule.getLocalNotificationStorage().b(Integer.parseInt(extras.getString(EXTRA_NOTIFICATION_ID)));
                    new a(extras).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                }
            } catch (Exception e) {
                PWLog.exception(e);
            }
        }
    }
}
