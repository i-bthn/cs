package com.pushwoosh.notification.builder;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.Action;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.l;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationBuilderManager {

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a = new int[Action.a.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            a[Action.a.ACTIVITY.ordinal()] = 1;
            a[Action.a.BROADCAST.ordinal()] = 2;
        }
    }

    public static void addAction(Context context, b bVar, Action action) {
        int i;
        int i2;
        if (action.getIcon() != null) {
            if (action.getIcon().startsWith("android.R.drawable")) {
                String replace = action.getIcon().replace("android.R.drawable.", "");
                Field[] fields = R.drawable.class.getFields();
                i2 = 0;
                for (Field field : fields) {
                    try {
                        if (replace.equalsIgnoreCase(field.getName())) {
                            i2 = field.getInt(field);
                        }
                    } catch (Exception e) {
                        PWLog.exception(e);
                    }
                }
            } else {
                i2 = 0;
            }
            i = i2 == 0 ? AndroidPlatformModule.getResourceProvider().a(action.getIcon(), "drawable") : i2;
        } else {
            i = 0;
        }
        String title = action.getTitle();
        String intentAction = action.getIntentAction();
        Intent intent = new Intent();
        String url = action.getUrl();
        if (url != null) {
            intent = new Intent(intentAction, Uri.parse(url));
        }
        Class<?> actionClass = action.getActionClass();
        if (actionClass != null) {
            intent.setClass(context, actionClass);
        }
        if (intentAction != null) {
            intent.setAction(intentAction);
        }
        JSONObject extras = action.getExtras();
        if (extras != null) {
            Iterator<String> keys = extras.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    intent.putExtra(next, extras.getString(next));
                } catch (JSONException e2) {
                    PWLog.exception(e2);
                }
            }
        }
        int i3 = a.a[action.getType().ordinal()];
        PendingIntent service = i3 != 1 ? i3 != 2 ? PendingIntent.getService(context, 0, intent, 134217728) : PendingIntent.getBroadcast(context, 0, intent, 134217728) : PendingIntent.getActivity(context, 0, intent, 134217728);
        if (service != null) {
            bVar.a(i, title, service);
        }
    }

    public static void addLED(@NonNull b bVar, @Nullable Integer num, int i, int i2) {
        l notificationPreferences = RepositoryModule.getNotificationPreferences();
        boolean z = notificationPreferences.f().get();
        int i3 = notificationPreferences.e().get();
        if (z || num != null) {
            if (num != null) {
                i3 = num.intValue();
            }
            bVar.a(i3, i, i2);
        }
    }

    public static b createNotificationBuilder(Context context, String str) {
        return Build.VERSION.SDK_INT >= 26 ? new d(context, str) : new c(context, str);
    }

    @RequiresApi(24)
    public static e createSummaryNotificationBuilder(Context context, String str) {
        return new f(context, str);
    }

    @RequiresApi(api = 23)
    public static List<StatusBarNotification> getActiveNotifications() {
        ArrayList arrayList = new ArrayList();
        NotificationManager notificationManager = AndroidPlatformModule.getManagerProvider().getNotificationManager();
        if (notificationManager != null) {
            try {
                StatusBarNotification[] activeNotifications = notificationManager.getActiveNotifications();
                for (StatusBarNotification statusBarNotification : activeNotifications) {
                    if (!isGroupSummary(statusBarNotification)) {
                        arrayList.add(statusBarNotification);
                    }
                }
            } catch (Throwable unused) {
                return Collections.emptyList();
            }
        } else {
            PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
        }
        return arrayList;
    }

    @RequiresApi(api = 21)
    public static boolean isGroupSummary(StatusBarNotification statusBarNotification) {
        return (statusBarNotification.getNotification().flags & 512) != 0;
    }

    @RequiresApi(api = 18)
    public static boolean isReplacingMessage(PushMessage pushMessage, List<StatusBarNotification> list) {
        if (list == null || list.isEmpty() || TextUtils.isEmpty(pushMessage.getTag())) {
            return false;
        }
        for (StatusBarNotification statusBarNotification : list) {
            if (statusBarNotification.getId() == 0 && TextUtils.equals(pushMessage.getTag(), statusBarNotification.getTag())) {
                return true;
            }
        }
        return false;
    }

    public static void removeInboxNotification(String str) {
        NotificationManager notificationManager;
        try {
            Integer b = RepositoryModule.getInboxNotificationStorage().b(str);
            String a2 = RepositoryModule.getInboxNotificationStorage().a(str);
            if (b != null && (notificationManager = AndroidPlatformModule.getManagerProvider().getNotificationManager()) != null) {
                if (!TextUtils.isEmpty(a2)) {
                    notificationManager.cancel(a2, b.intValue());
                } else {
                    notificationManager.cancel(b.intValue());
                }
            }
        } catch (Exception e) {
            PWLog.error("Can't delete notification from notification manager", e);
        }
    }

    @RequiresApi(api = 23)
    public static void removeInboxNotificationFromStatusBar(String str) {
        String string;
        try {
            NotificationManager notificationManager = AndroidPlatformModule.getManagerProvider().getNotificationManager();
            StatusBarNotification[] activeNotifications = notificationManager.getActiveNotifications();
            int length = activeNotifications.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                StatusBarNotification statusBarNotification = activeNotifications[i];
                Bundle bundle = statusBarNotification.getNotification().extras;
                if (bundle == null || (string = bundle.getString("pw_inbox")) == null || !TextUtils.equals(str, string)) {
                    i++;
                } else {
                    if (TextUtils.isEmpty(statusBarNotification.getTag())) {
                        notificationManager.cancel(statusBarNotification.getId());
                    } else {
                        notificationManager.cancel(statusBarNotification.getTag(), statusBarNotification.getId());
                    }
                    if (statusBarNotification.getNotification().deleteIntent != null) {
                        statusBarNotification.getNotification().deleteIntent.send();
                    }
                }
            }
            StatusBarNotification[] activeNotifications2 = notificationManager.getActiveNotifications();
            if (activeNotifications2.length == 1) {
                StatusBarNotification statusBarNotification2 = activeNotifications2[0];
                if (isGroupSummary(statusBarNotification2)) {
                    notificationManager.cancel(statusBarNotification2.getId());
                    if (statusBarNotification2.getNotification().deleteIntent != null) {
                        statusBarNotification2.getNotification().deleteIntent.send();
                    }
                }
            }
        } catch (Exception e) {
            PWLog.error("Can't delete message from status bar", e);
        }
    }

    @RequiresApi(api = 20)
    public static void setGroupToActiveNotifications(List<StatusBarNotification> list, String str) {
        if (AndroidPlatformModule.getApplicationContext() == null) {
            PWLog.error("setGroupToActiveNotifications Incorrect state of app. Context is null");
            return;
        }
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        for (StatusBarNotification statusBarNotification : list) {
            Notification build = (Build.VERSION.SDK_INT >= 24 ? Notification.Builder.recoverBuilder(applicationContext, statusBarNotification.getNotification()) : new Notification.Builder(applicationContext)).setOnlyAlertOnce(true).setGroup(str).build();
            if (list.size() == 1) {
                NotificationManagerCompat.from(applicationContext).cancel(statusBarNotification.getTag(), statusBarNotification.getId());
            }
            NotificationManagerCompat.from(applicationContext).notify(statusBarNotification.getId(), build);
        }
    }

    @RequiresApi(api = 24)
    public static Notification setNotificationGroup(@NonNull Notification notification, @Nullable String str) {
        if (AndroidPlatformModule.getApplicationContext() != null) {
            return Notification.Builder.recoverBuilder(AndroidPlatformModule.getApplicationContext(), notification).setGroup(str).build();
        }
        PWLog.error("setNotificationGroup Incorrect state of app. Context is null");
        return null;
    }
}
