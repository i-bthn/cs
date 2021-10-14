package com.pushwoosh.inapp.view;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.view.b;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.e;
import com.pushwoosh.repository.RepositoryModule;
import java.lang.ref.WeakReference;

public class RichMediaWebActivity extends WebActivity implements b.AbstractC0012b {
    private boolean d;
    final Handler e = new Handler();
    private boolean f;
    @Nullable
    private f g;
    private String h;
    private int i;
    private com.pushwoosh.inapp.i.a j;
    private boolean k;
    private boolean l;
    private boolean m = false;
    private boolean n;

    /* access modifiers changed from: package-private */
    public class a implements Animation.AnimationListener {
        a() {
        }

        public void onAnimationEnd(Animation animation) {
            RichMediaWebActivity.super.close();
            RichMediaWebActivity.this.overridePendingTransition(0, 0);
            RichMediaWebActivity.this.g.setVisibility(8);
            RichMediaWebActivity.this.f();
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    /* access modifiers changed from: private */
    public static class b extends AsyncTask<Void, Void, Ringtone> {
        private final WeakReference<Context> a;
        private final Uri b;
        private final Callback<Ringtone, PushwooshException> c;

        public b(Context context, Uri uri, Callback<Ringtone, PushwooshException> callback) {
            this.a = new WeakReference<>(context);
            this.b = uri;
            this.c = callback;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Ringtone doInBackground(Void... voidArr) {
            if (this.a.get() == null) {
                return null;
            }
            try {
                return RingtoneManager.getRingtone(this.a.get(), this.b);
            } catch (Exception e) {
                PWLog.error("Failed parse ringtone with songUri: " + this.b, e);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Ringtone ringtone) {
            super.onPostExecute(ringtone);
            this.c.process(Result.from(ringtone, null));
        }
    }

    private static Intent a(Context context) {
        return new Intent(context, RichMediaWebActivity.class);
    }

    public static Intent a(Context context, com.pushwoosh.inapp.j.l.b bVar) {
        return WebActivity.a(a(context), bVar, "", 0);
    }

    public static Intent a(Context context, com.pushwoosh.inapp.j.l.b bVar, String str) {
        Intent a2 = WebActivity.a(a(context), bVar, str, 1);
        a2.addFlags(343932928);
        return a2;
    }

    private static /* synthetic */ void a(Result result) {
        Ringtone ringtone = (Ringtone) result.getData();
        if (ringtone != null) {
            ringtone.play();
        }
    }

    public static Intent b(Context context, com.pushwoosh.inapp.j.l.b bVar) {
        return WebActivity.a(a(context), bVar, "", 0);
    }

    private /* synthetic */ void e() {
        this.d = true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void f() {
        if (!this.m) {
            this.m = true;
            EventBus.sendEvent(new com.pushwoosh.inapp.event.b(this.b));
        }
    }

    private void g() {
        long timeOutBackButtonEnable = PushwooshPlatform.getInstance().i().getTimeOutBackButtonEnable();
        if (timeOutBackButtonEnable == 0) {
            this.d = true;
        } else {
            this.e.postDelayed(new Runnable() {
                /* class com.pushwoosh.inapp.view.$$Lambda$RichMediaWebActivity$lLubULZGZtD3q1M5tUiHWlUiwAA */

                public final void run() {
                    RichMediaWebActivity.lambda$lLubULZGZtD3q1M5tUiHWlUiwAA(RichMediaWebActivity.this);
                }
            }, timeOutBackButtonEnable);
        }
    }

    private void h() {
        if (this.g != null) {
            if ((this.i & 1) != 0) {
                getWindow().addFlags(524288);
                if (RepositoryModule.getNotificationPreferences().g().get()) {
                    e.c();
                }
            }
            Uri b2 = e.b(this.h);
            if (b2 != null && !this.n) {
                this.n = true;
                new b(this, b2, $$Lambda$RichMediaWebActivity$yAYNuqZzLQpbCU18B4BKWIOnVOQ.INSTANCE).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
            if (!this.l) {
                if (this.b.k() && !this.k) {
                    this.k = true;
                    EventBus.sendEvent(new d(this.b));
                }
                this.g.a();
                this.l = true;
            }
        }
    }

    public static /* synthetic */ void lambda$lLubULZGZtD3q1M5tUiHWlUiwAA(RichMediaWebActivity richMediaWebActivity) {
        richMediaWebActivity.e();
    }

    public static /* synthetic */ void lambda$yAYNuqZzLQpbCU18B4BKWIOnVOQ(Result result) {
        a(result);
    }

    @Override // com.pushwoosh.inapp.view.b.AbstractC0012b
    public void a() {
        f fVar = this.g;
        if (fVar != null) {
            fVar.f();
        }
    }

    @Override // com.pushwoosh.inapp.view.b.AbstractC0012b
    public void a(com.pushwoosh.inapp.g.a aVar) {
        PWLog.error("[InApp]RichMediaWebAct", "Failed loading html data", aVar);
        if (this.b.k()) {
            EventBus.sendEvent(new e(this.b));
        }
        close();
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.WebActivity
    public void a(com.pushwoosh.inapp.j.l.b bVar, String str, int i2) {
        this.h = str;
        this.i = i2;
        this.n = false;
        b bVar2 = (b) getFragmentManager().findFragmentByTag("[InApp]RichMediaWebActpushwoosh.inAppFragment");
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        if (bVar2 != null) {
            beginTransaction.remove(bVar2);
        }
        this.k = false;
        beginTransaction.add(b.b(bVar), "[InApp]RichMediaWebActpushwoosh.inAppFragment").commitAllowingStateLoss();
        this.k = false;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.WebActivity
    public void a(@Nullable f fVar) {
        this.g = fVar;
        setContentView(fVar);
        b bVar = (b) getFragmentManager().findFragmentByTag("[InApp]RichMediaWebActpushwoosh.inAppFragment");
        if (bVar != null) {
            bVar.b();
        }
    }

    @Override // com.pushwoosh.inapp.view.b.AbstractC0012b
    public boolean a(com.pushwoosh.inapp.i.a aVar) {
        if (this.g == null || aVar.equals(this.j)) {
            return false;
        }
        this.j = aVar;
        String b2 = aVar.b();
        String c = aVar.c();
        if (!c.endsWith("/")) {
            c = c + "/";
        }
        this.g.a(c, b2.replace("<head>", "<head>\n<script type=\"text/javascript\">(function () {if (window.pushwoosh) return;window._pwCallbackHelper = {    __callbacks: {},    __cbCounter: 0,    invokeCallback: function(cbID) {        var args = Array.prototype.slice.call(arguments);        args.shift();        var cb = this.__callbacks[cbID];        this.__callbacks[cbID] = undefined;        return cb.apply(null, args);    },    registerCallback: function(func) {        var cbID = \"__cb\" + (+new Date) + this.__cbCounter;        this.__cbCounter++;        this.__callbacks[cbID] = func;        return cbID;    }};window.pushwoosh = {    _hwid: \"%s\",    _version: \"%s\",    postEvent: function(event, attributes, successCallback, errorCallback) {        if (!attributes) {            attributes = {};        }        if (!successCallback) {            successCallback = function() {};        }        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(successCallback);        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.postEvent(event, JSON.stringify(attributes), successCbId, errorCbId);    },    sendTags: function(tags) {        pushwooshImpl.sendTags(JSON.stringify(tags));    },    getTags: function(successCallback, errorCallback) {        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(function(tagsString) {            console.log(\"tags: \" + tagsString);            successCallback(JSON.parse(tagsString));        });        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.getTags(successCbId, errorCbId);    },    isCommunicationEnabled: function() {        return pushwooshImpl.isCommunicationEnabled();    },    setCommunicationEnabled: function(enabled) {        pushwooshImpl.setCommunicationEnabled(enabled);    },    removeAllDeviceData: function() {        pushwooshImpl.removeAllDeviceData();    },    log: function(str) {        pushwooshImpl.log(str);    },    closeInApp: function() {        pushwooshImpl.closeInApp();    },    getHwid: function() {        return this._hwid;    },    getVersion: function() {        return this._version;    },    getCustomData: function() {         var customData = pushwooshImpl.getCustomData();         if (customData) {             return JSON.parse(customData);         } else {             return null;         }    },    registerForPushNotifications: function() {        pushwooshImpl.registerForPushNotifications();    },    openAppSettings: function() {        pushwooshImpl.openAppSettings();    },    getChannels: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(channels) {             callback(JSON.parse(channels));        });        pushwooshImpl.getChannels(clb);    },    unregisterForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(callback);        pushwooshImpl.unregisterForPushNotifications(clb);    },    isRegisteredForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(state) {           if (state == 'true') {callback(true);} else if (state == 'false') {callback(false);}        });        pushwooshImpl.isRegisteredForPushNotifications(clb);    }};}());</script>"), "text/html", Key.STRING_CHARSET_NAME, null);
        return true;
    }

    @Override // com.pushwoosh.inapp.view.c, com.pushwoosh.inapp.view.WebActivity
    public void c() {
        super.c();
        h();
    }

    @Override // com.pushwoosh.inapp.view.c, com.pushwoosh.inapp.view.WebActivity
    public void close() {
        b bVar = (b) getFragmentManager().findFragmentByTag("[InApp]RichMediaWebActpushwoosh.inAppFragment");
        if (bVar != null) {
            getFragmentManager().beginTransaction().remove(bVar).commitAllowingStateLoss();
        }
        if (!this.f) {
            this.f = true;
            f fVar = this.g;
            if (fVar != null) {
                fVar.a(new a());
            }
        }
    }

    @Override // com.pushwoosh.inapp.view.b.AbstractC0012b
    public void d() {
        f fVar = this.g;
        if (fVar != null) {
            fVar.d();
        }
    }

    public void onBackPressed() {
        if (this.d) {
            close();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.WebActivity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
        } catch (Throwable unused) {
            finish();
        }
        if (bundle != null) {
            if (bundle.getBoolean("IS_CLOSED")) {
                finish();
                return;
            }
            this.l = bundle.getBoolean("IS_ANIMATED");
            this.n = bundle.getBoolean("KEY_IS_SOUND_PLAYED");
            this.h = bundle.getString("extraSound", "");
            this.i = bundle.getInt("extraMode", this.i);
        }
        g();
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.WebActivity
    public void onDestroy() {
        super.onDestroy();
        f();
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.inapp.view.WebActivity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("IS_ANIMATED", this.l);
        bundle.putBoolean("IS_CLOSED", this.f);
        bundle.putBoolean("KEY_IS_SOUND_PLAYED", this.n);
        bundle.putString("extraSound", this.h);
        bundle.putInt("extraMode", this.i);
    }
}
