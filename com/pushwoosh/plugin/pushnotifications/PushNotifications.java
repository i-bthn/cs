package com.pushwoosh.plugin.pushnotifications;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.pushwoosh.GDPRManager;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.badge.PushwooshBadge;
import com.pushwoosh.exception.GetTagsException;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.exception.RegisterForPushNotificationsException;
import com.pushwoosh.exception.UnregisterForPushNotificationException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.PushwooshInApp;
import com.pushwoosh.inbox.ui.presentation.view.activity.InboxActivity;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.LocalNotification;
import com.pushwoosh.notification.LocalNotificationReceiver;
import com.pushwoosh.notification.PushMessage;
import com.pushwoosh.notification.PushwooshNotificationSettings;
import com.pushwoosh.notification.SoundType;
import com.pushwoosh.notification.VibrateType;
import com.pushwoosh.tags.Tags;
import com.pushwoosh.tags.TagsBundle;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PushNotifications extends CordovaPlugin {
    public static final String TAG = "CordovaPlugin";
    private static final Map<String, Method> exportedMethods;
    private static AtomicBoolean sAppReady = new AtomicBoolean();
    private static PushNotifications sInstance;
    private static String sReceivedPushData;
    private static String sStartPushData;
    private static final Object sStartPushLock = new Object();
    private final HashMap<String, CallbackContext> callbackIds = new HashMap<>();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Retention(RetentionPolicy.RUNTIME)
    @interface CordovaMethod {
    }

    static {
        HashMap hashMap = new HashMap();
        for (Method method : new ArrayList(Arrays.asList(PushNotifications.class.getDeclaredMethods()))) {
            if (method.isAnnotationPresent(CordovaMethod.class)) {
                hashMap.put(method.getName(), method);
            }
        }
        exportedMethods = hashMap;
    }

    public PushNotifications() {
        sInstance = this;
        sAppReady.set(false);
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onDestroy() {
        super.onDestroy();
        PWLog.noise("OnDestroy");
        sAppReady.set(false);
    }

    private JSONObject getPushFromIntent(Intent intent) {
        if (intent == null || !intent.hasExtra(Pushwoosh.PUSH_RECEIVE_EVENT)) {
            return null;
        }
        try {
            return new JSONObject(intent.getExtras().getString(Pushwoosh.PUSH_RECEIVE_EVENT));
        } catch (JSONException e) {
            PWLog.error(TAG, "Failed to parse push notification", e);
            return null;
        }
    }

    @CordovaMethod
    private boolean onDeviceReady(JSONArray jSONArray, CallbackContext callbackContext) {
        String str;
        try {
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            try {
                if (jSONObject.has("appid")) {
                    str = jSONObject.getString("appid");
                } else {
                    str = jSONObject.getString("pw_appid");
                }
                Pushwoosh.getInstance().setAppId(str);
                Pushwoosh.getInstance().setSenderId(jSONObject.getString("projectid"));
                synchronized (sStartPushLock) {
                    if (sReceivedPushData != null) {
                        doOnPushReceived(sReceivedPushData);
                    }
                    if (sStartPushData != null) {
                        doOnPushOpened(sStartPushData);
                    }
                }
                sAppReady.set(true);
                return true;
            } catch (Exception e) {
                PWLog.error(TAG, "Missing pw_appid parameter. Did you follow the guide correctly?", e);
                return false;
            }
        } catch (JSONException e2) {
            PWLog.error(TAG, "No parameters has been passed to onDeviceReady function. Did you follow the guide correctly?", e2);
            return false;
        }
    }

    @CordovaMethod
    private boolean registerDevice(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            this.callbackIds.put("registerDevice", callbackContext);
            Pushwoosh.getInstance().registerForPushNotifications(new Callback<String, RegisterForPushNotificationsException>() {
                /* class com.pushwoosh.plugin.pushnotifications.PushNotifications.AnonymousClass1 */

                @Override // com.pushwoosh.function.Callback
                public void process(@NonNull Result<String, RegisterForPushNotificationsException> result) {
                    if (result.isSuccess()) {
                        PushNotifications.this.doOnRegistered(result.getData());
                    } else if (result.getException() != null) {
                        PushNotifications.this.doOnRegisteredError(result.getException().getMessage());
                    }
                }
            });
            return true;
        } catch (RuntimeException e) {
            this.callbackIds.remove("registerDevice");
            PWLog.error(TAG, "registering for push notifications failed", e);
            callbackContext.error(e.getMessage());
            return true;
        }
    }

    @CordovaMethod
    private boolean unregisterDevice(JSONArray jSONArray, CallbackContext callbackContext) {
        this.callbackIds.put("unregisterDevice", callbackContext);
        try {
            Pushwoosh.getInstance().unregisterForPushNotifications(new Callback<String, UnregisterForPushNotificationException>() {
                /* class com.pushwoosh.plugin.pushnotifications.PushNotifications.AnonymousClass2 */

                @Override // com.pushwoosh.function.Callback
                public void process(@NonNull Result<String, UnregisterForPushNotificationException> result) {
                    if (result.isSuccess()) {
                        PushNotifications.this.doOnUnregistered(result.getData());
                    } else if (result.getException() != null) {
                        PushNotifications.this.doOnUnregisteredError(result.getException().getMessage());
                    }
                }
            });
            return true;
        } catch (Exception e) {
            this.callbackIds.remove("unregisterDevice");
            callbackContext.error(e.getMessage());
            return true;
        }
    }

    @CordovaMethod
    private boolean setTags(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            this.callbackIds.put("setTags", callbackContext);
            Pushwoosh.getInstance().sendTags(Tags.fromJson(jSONObject), new Callback<Void, PushwooshException>() {
                /* class com.pushwoosh.plugin.pushnotifications.PushNotifications.AnonymousClass3 */

                @Override // com.pushwoosh.function.Callback
                public void process(@NonNull Result<Void, PushwooshException> result) {
                    CallbackContext callbackContext = (CallbackContext) PushNotifications.this.callbackIds.get("setTags");
                    if (callbackContext != null) {
                        if (result.isSuccess()) {
                            callbackContext.success(new JSONObject());
                        } else if (result.getException() != null) {
                            callbackContext.error(result.getException().getMessage());
                        }
                        PushNotifications.this.callbackIds.remove("setTags");
                    }
                }
            });
            return true;
        } catch (JSONException e) {
            PWLog.error(TAG, "No tags information passed (missing parameters)", e);
            return false;
        }
    }

    @CordovaMethod
    private boolean getTags(JSONArray jSONArray, CallbackContext callbackContext) {
        this.callbackIds.put("getTags", callbackContext);
        Pushwoosh.getInstance().getTags(new Callback<TagsBundle, GetTagsException>() {
            /* class com.pushwoosh.plugin.pushnotifications.PushNotifications.AnonymousClass4 */

            @Override // com.pushwoosh.function.Callback
            public void process(@NonNull Result<TagsBundle, GetTagsException> result) {
                CallbackContext callbackContext = (CallbackContext) PushNotifications.this.callbackIds.get("getTags");
                if (callbackContext != null) {
                    if (result.isSuccess()) {
                        callbackContext.success(result.getData().toJson());
                    } else {
                        callbackContext.error(result.getException().getMessage());
                    }
                    PushNotifications.this.callbackIds.remove("getTags");
                }
            }
        });
        return true;
    }

    @CordovaMethod
    private boolean getPushToken(JSONArray jSONArray, CallbackContext callbackContext) {
        callbackContext.success(Pushwoosh.getInstance().getPushToken());
        return true;
    }

    @CordovaMethod
    private boolean getPushwooshHWID(JSONArray jSONArray, CallbackContext callbackContext) {
        callbackContext.success(Pushwoosh.getInstance().getHwid());
        return true;
    }

    @CordovaMethod
    private boolean createLocalNotification(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            try {
                String string = jSONObject.getString(NotificationCompat.CATEGORY_MESSAGE);
                int i = jSONObject.getInt("seconds");
                if (string == null) {
                    return false;
                }
                String string2 = jSONObject.getString("userData");
                Bundle bundle = new Bundle();
                if (string2 != null) {
                    bundle.putString("u", string2);
                }
                Pushwoosh.getInstance().scheduleLocalNotification(new LocalNotification.Builder().setMessage(string).setDelay(i).setExtras(bundle).build());
                return true;
            } catch (JSONException e) {
                PWLog.error(TAG, "Not correct parameters passed (missing parameters)", e);
                return false;
            }
        } catch (JSONException e2) {
            PWLog.error(TAG, "No parameters passed (missing parameters)", e2);
            return false;
        }
    }

    @CordovaMethod
    private boolean clearLocalNotification(JSONArray jSONArray, CallbackContext callbackContext) {
        LocalNotificationReceiver.cancelAll();
        return true;
    }

    @CordovaMethod
    private boolean getLaunchNotification(JSONArray jSONArray, CallbackContext callbackContext) {
        PushMessage launchNotification = Pushwoosh.getInstance().getLaunchNotification();
        if (launchNotification == null) {
            callbackContext.success((String) null);
            return true;
        }
        callbackContext.success(launchNotification.toJson().toString());
        return true;
    }

    @CordovaMethod
    private boolean clearLaunchNotification(JSONArray jSONArray, CallbackContext callbackContext) {
        Pushwoosh.getInstance().clearLaunchNotification();
        return true;
    }

    @CordovaMethod
    private boolean setMultiNotificationMode(JSONArray jSONArray, CallbackContext callbackContext) {
        PushwooshNotificationSettings.setMultiNotificationMode(true);
        return true;
    }

    @CordovaMethod
    private boolean setSingleNotificationMode(JSONArray jSONArray, CallbackContext callbackContext) {
        PushwooshNotificationSettings.setMultiNotificationMode(false);
        return true;
    }

    @CordovaMethod
    private boolean setSoundType(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            Integer num = (Integer) jSONArray.get(0);
            if (num == null) {
                return false;
            }
            PushwooshNotificationSettings.setSoundNotificationType(SoundType.fromInt(num.intValue()));
            return true;
        } catch (Exception e) {
            PWLog.error(TAG, "No sound parameters passed (missing parameters)", e);
            return false;
        }
    }

    @CordovaMethod
    private boolean setVibrateType(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            Integer num = (Integer) jSONArray.get(0);
            if (num == null) {
                return false;
            }
            PushwooshNotificationSettings.setVibrateNotificationType(VibrateType.fromInt(num.intValue()));
            return true;
        } catch (Exception e) {
            PWLog.error(TAG, "No vibration parameters passed (missing parameters)", e);
            return false;
        }
    }

    @CordovaMethod
    private boolean setLightScreenOnNotification(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PushwooshNotificationSettings.setLightScreenOnNotification(jSONArray.getBoolean(0));
            return true;
        } catch (Exception e) {
            PWLog.error(TAG, "No parameters passed (missing parameters)", e);
            return false;
        }
    }

    @CordovaMethod
    private boolean setEnableLED(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PushwooshNotificationSettings.setEnableLED(jSONArray.getBoolean(0));
            return true;
        } catch (Exception e) {
            PWLog.error(TAG, "No parameters passed (missing parameters)", e);
            return false;
        }
    }

    @CordovaMethod
    private boolean setColorLED(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            String str = (String) jSONArray.get(0);
            if (str == null) {
                return false;
            }
            PushwooshNotificationSettings.setColorLED(GeneralUtils.parseColor(str));
            return true;
        } catch (Exception e) {
            PWLog.error(TAG, "No parameters passed (missing parameters)", e);
            return false;
        }
    }

    @CordovaMethod
    private boolean getPushHistory(JSONArray jSONArray, CallbackContext callbackContext) {
        List<PushMessage> pushHistory = Pushwoosh.getInstance().getPushHistory();
        ArrayList arrayList = new ArrayList();
        for (PushMessage pushMessage : pushHistory) {
            arrayList.add(pushMessage.toJson().toString());
        }
        callbackContext.success(new JSONArray((Collection) arrayList));
        return true;
    }

    @CordovaMethod
    private boolean clearPushHistory(JSONArray jSONArray, CallbackContext callbackContext) {
        Pushwoosh.getInstance().clearPushHistory();
        return true;
    }

    @CordovaMethod
    private boolean clearNotificationCenter(JSONArray jSONArray, CallbackContext callbackContext) {
        NotificationManagerCompat.from(this.cordova.getActivity()).cancelAll();
        return true;
    }

    @CordovaMethod
    private boolean setApplicationIconBadgeNumber(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PushwooshBadge.setBadgeNumber(Integer.valueOf(jSONArray.getJSONObject(0).getInt("badge")).intValue());
            return true;
        } catch (JSONException e) {
            PWLog.error(TAG, "No parameters passed (missing parameters)", e);
            return false;
        }
    }

    @CordovaMethod
    private boolean getApplicationIconBadgeNumber(JSONArray jSONArray, CallbackContext callbackContext) {
        callbackContext.success(Integer.valueOf(PushwooshBadge.getBadgeNumber()).intValue());
        return true;
    }

    @CordovaMethod
    private boolean addToApplicationIconBadgeNumber(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PushwooshBadge.addBadgeNumber(Integer.valueOf(jSONArray.getJSONObject(0).getInt("badge")).intValue());
            return true;
        } catch (JSONException e) {
            PWLog.error(TAG, "No parameters passed (missing parameters)", e);
            return false;
        }
    }

    @CordovaMethod
    private boolean setUserId(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PushwooshInApp.getInstance().setUserId(jSONArray.getString(0));
            return true;
        } catch (JSONException e) {
            PWLog.error(TAG, "No parameters passed (missing parameters)", e);
            return true;
        }
    }

    @CordovaMethod
    private boolean postEvent(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PushwooshInApp.getInstance().postEvent(jSONArray.getString(0), Tags.fromJson(jSONArray.getJSONObject(1)));
        } catch (JSONException e) {
            PWLog.error(TAG, "No parameters passed (missing parameters)", e);
        }
        return true;
    }

    @CordovaMethod
    private boolean getRemoteNotificationStatus(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            String str = PushwooshNotificationSettings.areNotificationsEnabled() ? "1" : "0";
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("enabled", str);
            callbackContext.success(jSONObject);
            return true;
        } catch (Exception e) {
            callbackContext.error(e.getMessage());
            return true;
        }
    }

    @CordovaMethod
    private boolean presentInboxUI(JSONArray jSONArray, CallbackContext callbackContext) {
        if (jSONArray.length() > 0) {
            InboxUiStyleManager.setStyle(this.cordova.getActivity(), jSONArray.optJSONObject(0));
        }
        this.cordova.getActivity().startActivity(new Intent(this.cordova.getActivity(), InboxActivity.class));
        return true;
    }

    @CordovaMethod
    public boolean showGDPRConsentUI(JSONArray jSONArray, CallbackContext callbackContext) {
        GDPRManager.getInstance().showGDPRConsentUI();
        return true;
    }

    @CordovaMethod
    public boolean showGDPRDeletionUI(JSONArray jSONArray, CallbackContext callbackContext) {
        GDPRManager.getInstance().showGDPRDeletionUI();
        return true;
    }

    @CordovaMethod
    public boolean isDeviceDataRemoved(JSONArray jSONArray, CallbackContext callbackContext) {
        callbackContext.success(GDPRManager.getInstance().isDeviceDataRemoved() ? 1 : 0);
        return true;
    }

    @CordovaMethod
    public boolean isCommunicationEnabled(JSONArray jSONArray, CallbackContext callbackContext) {
        callbackContext.success(GDPRManager.getInstance().isCommunicationEnabled() ? 1 : 0);
        return true;
    }

    @CordovaMethod
    public boolean isAvailableGDPR(JSONArray jSONArray, CallbackContext callbackContext) {
        callbackContext.success(GDPRManager.getInstance().isAvailable() ? 1 : 0);
        return true;
    }

    @CordovaMethod
    public boolean removeAllDeviceData(JSONArray jSONArray, final CallbackContext callbackContext) {
        GDPRManager.getInstance().removeAllDeviceData(new Callback<Void, PushwooshException>() {
            /* class com.pushwoosh.plugin.pushnotifications.PushNotifications.AnonymousClass5 */

            @Override // com.pushwoosh.function.Callback
            public void process(@NonNull Result<Void, PushwooshException> result) {
                if (result.isSuccess()) {
                    callbackContext.success();
                } else {
                    callbackContext.error(result.getException().getMessage());
                }
            }
        });
        return true;
    }

    @CordovaMethod
    public boolean setCommunicationEnabled(JSONArray jSONArray, final CallbackContext callbackContext) {
        try {
            GDPRManager.getInstance().setCommunicationEnabled(jSONArray.getBoolean(0), new Callback<Void, PushwooshException>() {
                /* class com.pushwoosh.plugin.pushnotifications.PushNotifications.AnonymousClass6 */

                @Override // com.pushwoosh.function.Callback
                public void process(@NonNull Result<Void, PushwooshException> result) {
                    if (result.isSuccess()) {
                        callbackContext.success();
                    } else {
                        callbackContext.error(result.getException().getMessage());
                    }
                }
            });
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) {
        PWLog.debug(TAG, "Plugin Method Called: " + str);
        Method method = exportedMethods.get(str);
        if (method == null) {
            PWLog.debug(TAG, "Invalid action : " + str + " passed");
            return false;
        }
        try {
            return ((Boolean) method.invoke(this, jSONArray, callbackContext)).booleanValue();
        } catch (Exception e) {
            PWLog.error(TAG, "Failed to execute action : " + str, e);
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void doOnRegistered(String str) {
        CallbackContext callbackContext = this.callbackIds.get("registerDevice");
        if (callbackContext != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("pushToken", str);
                callbackContext.success(jSONObject);
            } catch (Exception unused) {
                callbackContext.error("Internal error");
            }
            this.callbackIds.remove("registerDevice");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void doOnRegisteredError(String str) {
        CallbackContext callbackContext = this.callbackIds.get("registerDevice");
        if (callbackContext != null) {
            callbackContext.error(str);
            this.callbackIds.remove("registerDevice");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void doOnUnregistered(String str) {
        CallbackContext callbackContext = this.callbackIds.get("unregisterDevice");
        if (callbackContext != null) {
            callbackContext.success(str);
            this.callbackIds.remove("unregisterDevice");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void doOnUnregisteredError(String str) {
        CallbackContext callbackContext = this.callbackIds.get("unregisterDevice");
        if (callbackContext != null) {
            callbackContext.error(str);
            this.callbackIds.remove("unregisterDevice");
        }
    }

    private void doOnPushOpened(String str) {
        PWLog.debug(TAG, "push opened: " + str);
        evalJs(String.format("cordova.require(\"pushwoosh-cordova-plugin.PushNotification\").notificationCallback(%s);", convertNotification(str)));
        sStartPushData = null;
    }

    public void doOnPushReceived(String str) {
        PWLog.debug(TAG, "push received: " + str);
        evalJs(String.format("cordova.require(\"pushwoosh-cordova-plugin.PushNotification\").pushReceivedCallback(%s);", convertNotification(str)));
        sReceivedPushData = null;
    }

    private String convertNotification(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            String optString = jSONObject2.optString("title");
            Boolean valueOf = Boolean.valueOf(jSONObject2.optBoolean("foreground"));
            Boolean valueOf2 = Boolean.valueOf(jSONObject2.optBoolean("onStart"));
            JSONObject optJSONObject = jSONObject2.optJSONObject("userdata");
            jSONObject.put("android", jSONObject2);
            jSONObject.put("message", optString);
            jSONObject.put("foreground", valueOf);
            jSONObject.put("onStart", valueOf2);
            jSONObject.put("userdata", optJSONObject);
        } catch (JSONException e) {
            PWLog.error(TAG, "push message parsing failed", e);
        }
        return jSONObject.toString().replace("%", "%\"+\"");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void evalJs(String str) {
        final String str2 = "javascript:" + str;
        this.handler.post(new Runnable() {
            /* class com.pushwoosh.plugin.pushnotifications.PushNotifications.AnonymousClass7 */

            public void run() {
                try {
                    PushNotifications.this.webView.loadUrl(str2);
                } catch (Exception e) {
                    PWLog.exception(e);
                }
            }
        });
    }

    static void openPush(String str) {
        try {
            synchronized (sStartPushLock) {
                sStartPushData = str;
                if (sAppReady.get() && sInstance != null) {
                    sInstance.doOnPushOpened(str);
                }
            }
        } catch (Exception e) {
            PWLog.exception(e);
        }
    }

    static void messageReceived(String str) {
        try {
            synchronized (sStartPushLock) {
                sReceivedPushData = str;
                if (sAppReady.get() && sInstance != null) {
                    sInstance.doOnPushReceived(str);
                }
            }
        } catch (Exception e) {
            PWLog.exception(e);
        }
    }

    public class JavascriptInterfaceCordova {
        public JavascriptInterfaceCordova() {
        }

        @JavascriptInterface
        public void callFunction(String str) {
            PushNotifications.this.evalJs(String.format("%s();", str));
        }

        @JavascriptInterface
        public void callFunction(String str, String str2) {
            String str3;
            if (str2 == null || str2.isEmpty()) {
                str3 = String.format("%s();", str);
            } else {
                str3 = String.format("%s(%s);", str, str2);
            }
            PushNotifications.this.evalJs(str3);
        }
    }

    @CordovaMethod
    private boolean addJavaScriptInterface(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PushwooshInApp.getInstance().addJavascriptInterface(new JavascriptInterfaceCordova(), jSONArray.getString(0));
            return true;
        } catch (JSONException e) {
            PWLog.error(TAG, "No parameters has been passed to addJavaScriptInterface function. Did you follow the guide correctly?", e);
            return false;
        }
    }
}
