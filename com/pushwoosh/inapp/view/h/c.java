package com.pushwoosh.inapp.view.h;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebView;
import com.pushwoosh.GDPRManager;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.exception.GetTagsException;
import com.pushwoosh.exception.PostEventException;
import com.pushwoosh.exception.UnregisterForPushNotificationException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.config.Channel;
import com.pushwoosh.tags.Tags;
import com.pushwoosh.tags.TagsBundle;
import java.lang.ref.WeakReference;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    public static final String PUSHWOOSH_JS;
    private final a a;
    private final WeakReference<WebView> b;
    private final String c;
    private final Handler d = new Handler(Looper.getMainLooper());

    public c(a aVar, WebView webView, View view, String str) {
        this.a = aVar;
        this.b = new WeakReference<>(webView);
        this.c = str;
    }

    private void a(String str) {
        this.d.post(new Runnable(String.format("javascript:_pwCallbackHelper.invokeCallback(\"%s\");", str)) {
            /* class com.pushwoosh.inapp.view.h.$$Lambda$c$C3WXubs9GGjYeRTt9IZYywmT8YQ */
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                c.lambda$C3WXubs9GGjYeRTt9IZYywmT8YQ(c.this, this.f$1);
            }
        });
    }

    /* access modifiers changed from: public */
    private /* synthetic */ void a(String str, Result result) {
        if (result.getException() == null) {
            a(str);
        } else {
            a(str, ((UnregisterForPushNotificationException) result.getException()).getMessage());
        }
    }

    private void a(String str, String str2) {
        this.d.post(new Runnable(String.format("javascript:_pwCallbackHelper.invokeCallback(\"%s\", \"%s\");", str, str2.replace("\"", "\\\""))) {
            /* class com.pushwoosh.inapp.view.h.$$Lambda$c$uQVSbHsLbFcfLKSyF2KDY2mkYFA */
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                c.lambda$uQVSbHsLbFcfLKSyF2KDY2mkYFA(c.this, this.f$1);
            }
        });
    }

    /* access modifiers changed from: public */
    private /* synthetic */ void a(String str, String str2, Result result) {
        if (result.isSuccess()) {
            TagsBundle tagsBundle = (TagsBundle) result.getData();
            a(str, (tagsBundle == null ? new JSONObject() : tagsBundle.toJson()).toString());
            return;
        }
        a(str2, ((GetTagsException) result.getException()).getMessage());
    }

    /* access modifiers changed from: public */
    private /* synthetic */ void b(String str, String str2, Result result) {
        if (result.isSuccess()) {
            a(str);
        } else {
            a(str2, ((PostEventException) result.getException()).getLocalizedMessage());
        }
    }

    /* access modifiers changed from: public */
    /* access modifiers changed from: private */
    /* renamed from: d */
    public void c(String str) {
        WebView webView = this.b.get();
        if (webView != null) {
            webView.loadUrl(str);
        }
    }

    public void closeInApp() {
        this.a.close();
    }

    public void getChannels(String str) {
        List<Channel> a2 = PushwooshPlatform.getInstance().o().a();
        JSONArray jSONArray = new JSONArray();
        for (Channel channel : a2) {
            jSONArray.put(channel.jsonValue());
        }
        a(str, jSONArray.toString());
    }

    public String getCustomData() {
        return this.c;
    }

    public void getTags(String str, String str2) {
        Pushwoosh.getInstance().getTags(new Callback(str, str2) {
            /* class com.pushwoosh.inapp.view.h.$$Lambda$c$DjSUFxeq7_7I05_vg02clOtbwkI */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.pushwoosh.function.Callback
            public final void process(Result result) {
                c.lambda$DjSUFxeq7_7I05_vg02clOtbwkI(c.this, this.f$1, this.f$2, result);
            }
        });
    }

    public boolean isCommunicationEnabled() {
        return GDPRManager.getInstance().isCommunicationEnabled();
    }

    public boolean isDeviceDataRemoved() {
        return GDPRManager.getInstance().isDeviceDataRemoved();
    }

    public void isRegisteredForPushNotifications(String str) {
        boolean z;
        try {
            z = PushwooshPlatform.getInstance().g().isRegisteredForPush().get();
        } catch (Exception unused) {
            z = false;
        }
        a(str, z ? "true" : "false");
    }

    public void log(String str) {
        PWLog.debug("[InApp]PushwooshJSInterface", str);
    }

    public void onPageFinished(WebView webView) {
        webView.loadUrl(String.format("javascript:(function () {if (window.pushwoosh) return;window._pwCallbackHelper = {    __callbacks: {},    __cbCounter: 0,    invokeCallback: function(cbID) {        var args = Array.prototype.slice.call(arguments);        args.shift();        var cb = this.__callbacks[cbID];        this.__callbacks[cbID] = undefined;        return cb.apply(null, args);    },    registerCallback: function(func) {        var cbID = \"__cb\" + (+new Date) + this.__cbCounter;        this.__cbCounter++;        this.__callbacks[cbID] = func;        return cbID;    }};window.pushwoosh = {    _hwid: \"%s\",    _version: \"%s\",    postEvent: function(event, attributes, successCallback, errorCallback) {        if (!attributes) {            attributes = {};        }        if (!successCallback) {            successCallback = function() {};        }        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(successCallback);        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.postEvent(event, JSON.stringify(attributes), successCbId, errorCbId);    },    sendTags: function(tags) {        pushwooshImpl.sendTags(JSON.stringify(tags));    },    getTags: function(successCallback, errorCallback) {        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(function(tagsString) {            console.log(\"tags: \" + tagsString);            successCallback(JSON.parse(tagsString));        });        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.getTags(successCbId, errorCbId);    },    isCommunicationEnabled: function() {        return pushwooshImpl.isCommunicationEnabled();    },    setCommunicationEnabled: function(enabled) {        pushwooshImpl.setCommunicationEnabled(enabled);    },    removeAllDeviceData: function() {        pushwooshImpl.removeAllDeviceData();    },    log: function(str) {        pushwooshImpl.log(str);    },    closeInApp: function() {        pushwooshImpl.closeInApp();    },    getHwid: function() {        return this._hwid;    },    getVersion: function() {        return this._version;    },    getCustomData: function() {         var customData = pushwooshImpl.getCustomData();         if (customData) {             return JSON.parse(customData);         } else {             return null;         }    },    registerForPushNotifications: function() {        pushwooshImpl.registerForPushNotifications();    },    openAppSettings: function() {        pushwooshImpl.openAppSettings();    },    getChannels: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(channels) {             callback(JSON.parse(channels));        });        pushwooshImpl.getChannels(clb);    },    unregisterForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(callback);        pushwooshImpl.unregisterForPushNotifications(clb);    },    isRegisteredForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(state) {           if (state == 'true') {callback(true);} else if (state == 'false') {callback(false);}        });        pushwooshImpl.isRegisteredForPushNotifications(clb);    }};}());", Pushwoosh.getInstance().getHwid(), "6.2.8"));
    }

    public void onPageStarted(WebView webView) {
        webView.loadUrl(String.format("javascript:(function () {if (window.pushwoosh) return;window._pwCallbackHelper = {    __callbacks: {},    __cbCounter: 0,    invokeCallback: function(cbID) {        var args = Array.prototype.slice.call(arguments);        args.shift();        var cb = this.__callbacks[cbID];        this.__callbacks[cbID] = undefined;        return cb.apply(null, args);    },    registerCallback: function(func) {        var cbID = \"__cb\" + (+new Date) + this.__cbCounter;        this.__cbCounter++;        this.__callbacks[cbID] = func;        return cbID;    }};window.pushwoosh = {    _hwid: \"%s\",    _version: \"%s\",    postEvent: function(event, attributes, successCallback, errorCallback) {        if (!attributes) {            attributes = {};        }        if (!successCallback) {            successCallback = function() {};        }        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(successCallback);        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.postEvent(event, JSON.stringify(attributes), successCbId, errorCbId);    },    sendTags: function(tags) {        pushwooshImpl.sendTags(JSON.stringify(tags));    },    getTags: function(successCallback, errorCallback) {        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(function(tagsString) {            console.log(\"tags: \" + tagsString);            successCallback(JSON.parse(tagsString));        });        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.getTags(successCbId, errorCbId);    },    isCommunicationEnabled: function() {        return pushwooshImpl.isCommunicationEnabled();    },    setCommunicationEnabled: function(enabled) {        pushwooshImpl.setCommunicationEnabled(enabled);    },    removeAllDeviceData: function() {        pushwooshImpl.removeAllDeviceData();    },    log: function(str) {        pushwooshImpl.log(str);    },    closeInApp: function() {        pushwooshImpl.closeInApp();    },    getHwid: function() {        return this._hwid;    },    getVersion: function() {        return this._version;    },    getCustomData: function() {         var customData = pushwooshImpl.getCustomData();         if (customData) {             return JSON.parse(customData);         } else {             return null;         }    },    registerForPushNotifications: function() {        pushwooshImpl.registerForPushNotifications();    },    openAppSettings: function() {        pushwooshImpl.openAppSettings();    },    getChannels: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(channels) {             callback(JSON.parse(channels));        });        pushwooshImpl.getChannels(clb);    },    unregisterForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(callback);        pushwooshImpl.unregisterForPushNotifications(clb);    },    isRegisteredForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(state) {           if (state == 'true') {callback(true);} else if (state == 'false') {callback(false);}        });        pushwooshImpl.isRegisteredForPushNotifications(clb);    }};}());", Pushwoosh.getInstance().getHwid(), "6.2.8"));
    }

    public void openAppSettings() {
        if (AndroidPlatformModule.getInstance() != null) {
            Context applicationContext = AndroidPlatformModule.getApplicationContext();
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", applicationContext.getPackageName());
            intent.putExtra("app_uid", applicationContext.getApplicationInfo().uid);
            intent.addFlags(268435456);
            intent.putExtra("android.provider.extra.APP_PACKAGE", applicationContext.getPackageName());
            applicationContext.startActivity(intent);
        }
    }

    public void postEvent(String str, String str2, String str3, String str4) {
        try {
            PushwooshPlatform.getInstance().n().a(str, Tags.fromJson(new JSONObject(str2)), new Callback(str3, str4) {
                /* class com.pushwoosh.inapp.view.h.$$Lambda$c$hDX8ffYfhBTHkNEjvpQRHWC3vM */
                private final /* synthetic */ String f$1;
                private final /* synthetic */ String f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    c.m13lambda$hDX8ffYfhBTHkNEjvpQRHWC3vM(c.this, this.f$1, this.f$2, result);
                }
            });
        } catch (Exception e) {
            PWLog.error("postEvent method was failed", e);
            a(str4, e.getLocalizedMessage());
        }
    }

    public void registerForPushNotifications() {
        Pushwoosh.getInstance().registerForPushNotifications();
    }

    public void removeAllDeviceData() {
        GDPRManager.getInstance().removeAllDeviceData(null);
    }

    public void sendTags(String str) {
        try {
            Pushwoosh.getInstance().sendTags(new TagsBundle.Builder().putAll(new JSONObject(str)).build());
        } catch (JSONException e) {
            PWLog.error("Invalid tags format, expected object with string properties", e);
        }
    }

    public void setCommunicationEnabled(boolean z) {
        GDPRManager.getInstance().setCommunicationEnabled(z, null);
    }

    public void unregisterForPushNotifications(String str) {
        Pushwoosh.getInstance().unregisterForPushNotifications(new Callback(str) {
            /* class com.pushwoosh.inapp.view.h.$$Lambda$c$G8GngRvv3VfTkCw_4I5Qq3ZDZBM */
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.pushwoosh.function.Callback
            public final void process(Result result) {
                c.lambda$G8GngRvv3VfTkCw_4I5Qq3ZDZBM(c.this, this.f$1, result);
            }
        });
    }
}
