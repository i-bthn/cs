package com.pushwoosh;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.exception.GetTagsException;
import com.pushwoosh.exception.MergeUserException;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.exception.RegisterForPushNotificationsException;
import com.pushwoosh.exception.SetEmailException;
import com.pushwoosh.exception.SetUserException;
import com.pushwoosh.exception.SetUserIdException;
import com.pushwoosh.exception.UnregisterForPushNotificationException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.j.c;
import com.pushwoosh.internal.b;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.LocalNotification;
import com.pushwoosh.notification.LocalNotificationRequest;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.notification.PushwooshNotificationManager;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.o;
import com.pushwoosh.tags.TagsBundle;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Pushwoosh {
    public static final int PUSH_HISTORY_CAPACITY = 16;
    public static final String PUSH_RECEIVE_EVENT = "PUSH_RECEIVE_EVENT";
    private static final Pushwoosh e = new Pushwoosh();
    private final PushwooshNotificationManager a;
    private final o b;
    private final c c;
    private final b d;

    private Pushwoosh() {
        b bVar;
        PushwooshPlatform instance = PushwooshPlatform.getInstance();
        if (instance == null) {
            PushwooshPlatform.r();
            bVar = null;
            this.a = null;
            this.b = null;
            this.c = null;
        } else {
            this.a = instance.notificationManager();
            this.b = instance.o();
            instance.d();
            new AtomicBoolean();
            this.c = com.pushwoosh.inapp.b.c();
            bVar = instance.f();
        }
        this.d = bVar;
    }

    @NonNull
    public static Pushwoosh getInstance() {
        return e;
    }

    private void sendMessageDelivery(Bundle bundle) {
        if (bundle.containsKey("pw_msg")) {
            PushwooshMessagingServiceHelper.sendMessageDeliveryEvent(bundle);
        } else {
            PWLog.warn("/messageDeliveryEvent request was not sent, as the push was not received from Pushwoosh");
        }
    }

    private void sendPushStat(Bundle bundle) {
        if (bundle.containsKey("pw_msg")) {
            PushwooshMessagingServiceHelper.sendPushStat(bundle);
        } else {
            PWLog.warn("/pushStat request was not sent, as the push was not received from Pushwoosh");
        }
    }

    public void clearLaunchNotification() {
        PushwooshNotificationManager pushwooshNotificationManager = this.a;
        if (pushwooshNotificationManager != null) {
            pushwooshNotificationManager.a();
        }
    }

    public void clearPushHistory() {
        if (this.b != null) {
            RepositoryModule.getNotificationPreferences().l().clear();
        }
    }

    public void enableHuaweiPushNotifications() {
        b bVar = this.d;
        if (bVar != null) {
            bVar.a();
        }
    }

    @Deprecated
    public String getAppId() {
        return getApplicationCode();
    }

    public String getApplicationCode() {
        return this.b != null ? RepositoryModule.getRegistrationPreferences().applicationId().get() : "";
    }

    @NonNull
    public String getHwid() {
        return this.a != null ? this.b.d() : "";
    }

    public String getLanguage() {
        return this.b != null ? RepositoryModule.getRegistrationPreferences().language().get() : "";
    }

    @Nullable
    public PushMessage getLaunchNotification() {
        PushwooshNotificationManager pushwooshNotificationManager = this.a;
        if (pushwooshNotificationManager != null) {
            return pushwooshNotificationManager.b();
        }
        return null;
    }

    @NonNull
    public List<PushMessage> getPushHistory() {
        o oVar = this.b;
        return oVar != null ? oVar.f() : new ArrayList();
    }

    @Nullable
    public String getPushToken() {
        PushwooshNotificationManager pushwooshNotificationManager = this.a;
        return pushwooshNotificationManager != null ? pushwooshNotificationManager.c() : "";
    }

    public String getSenderId() {
        return this.a != null ? RepositoryModule.getRegistrationPreferences().projectId().get() : "";
    }

    public void getTags(@NonNull Callback<TagsBundle, GetTagsException> callback) {
        o oVar = this.b;
        if (oVar != null) {
            oVar.a(callback);
        }
    }

    @Nullable
    public String getUserId() {
        return RepositoryModule.getRegistrationPreferences().userId().get();
    }

    public void mergeUserId(@NonNull String str, @NonNull String str2, boolean z, @Nullable Callback<Void, MergeUserException> callback) {
        this.c.a(str, str2, z, callback);
    }

    public void registerForPushNotifications() {
        registerForPushNotifications(null);
    }

    public void registerForPushNotifications(Callback<String, RegisterForPushNotificationsException> callback) {
        PushwooshNotificationManager pushwooshNotificationManager = this.a;
        if (pushwooshNotificationManager != null) {
            pushwooshNotificationManager.a(callback);
        }
    }

    @NonNull
    public LocalNotificationRequest scheduleLocalNotification(LocalNotification localNotification) {
        PushwooshNotificationManager pushwooshNotificationManager = this.a;
        if (pushwooshNotificationManager != null) {
            return pushwooshNotificationManager.a(localNotification);
        }
        return null;
    }

    public void sendAppOpen() {
        o oVar = this.b;
        if (oVar != null) {
            oVar.m();
        }
    }

    public void sendInappPurchase(@NonNull String str, @NonNull BigDecimal bigDecimal, @NonNull String str2) {
        o oVar = this.b;
        if (oVar != null) {
            oVar.a(str, bigDecimal, str2, new Date());
        }
    }

    @Deprecated
    public void sendTags(@NonNull TagsBundle tagsBundle) {
        setTags(tagsBundle);
    }

    @Deprecated
    public void sendTags(@NonNull TagsBundle tagsBundle, Callback<Void, PushwooshException> callback) {
        setTags(tagsBundle, callback);
    }

    public void setAppId(@NonNull String str) {
        PushwooshNotificationManager pushwooshNotificationManager = this.a;
        if (pushwooshNotificationManager != null) {
            pushwooshNotificationManager.a(str);
        }
    }

    public void setEmail(@NonNull String str) {
        setEmail(str, (Callback<Boolean, SetEmailException>) null);
    }

    public void setEmail(@NonNull String str, Callback<Boolean, SetEmailException> callback) {
        if (!TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            this.c.a(arrayList, callback);
        }
    }

    public void setEmail(@NonNull List<String> list) {
        setEmail(list, (Callback<Boolean, SetEmailException>) null);
    }

    public void setEmail(@NonNull List<String> list, Callback<Boolean, SetEmailException> callback) {
        if (!list.isEmpty()) {
            this.c.a(list, callback);
        }
    }

    public void setEmailTags(@NonNull TagsBundle tagsBundle, @NonNull String str) {
        setEmailTags(tagsBundle, str, null);
    }

    public void setEmailTags(@NonNull TagsBundle tagsBundle, String str, Callback<Void, PushwooshException> callback) {
        o oVar = this.b;
        if (oVar != null) {
            oVar.a(tagsBundle, str, callback);
        }
    }

    public void setLanguage(@Nullable String str) {
        RepositoryModule.getRegistrationPreferences().setLanguage(str);
    }

    public void setSenderId(@NonNull String str) {
        PushwooshNotificationManager pushwooshNotificationManager = this.a;
        if (pushwooshNotificationManager != null) {
            pushwooshNotificationManager.b(str);
        }
    }

    public void setTags(@NonNull TagsBundle tagsBundle) {
        setTags(tagsBundle, null);
    }

    public void setTags(@NonNull TagsBundle tagsBundle, Callback<Void, PushwooshException> callback) {
        o oVar = this.b;
        if (oVar != null) {
            oVar.a(tagsBundle, callback);
        }
    }

    public void setUser(@NonNull String str, @NonNull List<String> list) {
        setUser(str, list, null);
    }

    public void setUser(@NonNull String str, @NonNull List<String> list, Callback<Boolean, SetUserException> callback) {
        if (!TextUtils.isEmpty(str)) {
            RepositoryModule.getRegistrationPreferences().userId().set(str);
        }
        this.c.a(str, list, callback);
    }

    public void setUserId(@NonNull String str) {
        setUserId(str, null);
    }

    public void setUserId(@NonNull String str, Callback<Boolean, SetUserIdException> callback) {
        if (!TextUtils.equals(str, RepositoryModule.getRegistrationPreferences().userId().get())) {
            if (!TextUtils.isEmpty(str)) {
                RepositoryModule.getRegistrationPreferences().userId().set(str);
            }
            this.c.b(str, callback);
        } else if (callback != null) {
            callback.process(Result.fromData(true));
        }
    }

    public void unregisterForPushNotifications() {
        unregisterForPushNotifications(null);
    }

    public void unregisterForPushNotifications(Callback<String, UnregisterForPushNotificationException> callback) {
        PushwooshNotificationManager pushwooshNotificationManager = this.a;
        if (pushwooshNotificationManager != null) {
            pushwooshNotificationManager.b(callback);
        }
    }
}
