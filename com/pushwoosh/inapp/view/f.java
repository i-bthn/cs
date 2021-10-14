package com.pushwoosh.inapp.view;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.R;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.richmedia.RichMediaStyle;
import com.pushwoosh.richmedia.animation.RichMediaAnimation;

@SuppressLint({"ViewConstructor"})
public class f extends FrameLayout {
    private com.pushwoosh.inapp.j.l.a a;
    protected FrameLayout b;
    private View c;
    protected WebView d;
    private boolean e;
    private boolean f;
    private Runnable g;
    private RichMediaAnimation h;
    private Handler i;
    int j;
    boolean k;

    /* access modifiers changed from: package-private */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a = new int[com.pushwoosh.inapp.j.l.a.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            a[com.pushwoosh.inapp.j.l.a.FULLSCREEN.ordinal()] = 1;
            a[com.pushwoosh.inapp.j.l.a.BOTTOM.ordinal()] = 2;
            a[com.pushwoosh.inapp.j.l.a.TOP.ordinal()] = 3;
            try {
                a[com.pushwoosh.inapp.j.l.a.DIALOG.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    protected f(Context context) {
        super(context);
        com.pushwoosh.inapp.j.l.a aVar = com.pushwoosh.inapp.j.l.a.FULLSCREEN;
        this.a = aVar;
        this.f = false;
        a(aVar, PushwooshPlatform.getInstance().h().a(), context);
    }

    protected f(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        com.pushwoosh.inapp.j.l.a aVar = com.pushwoosh.inapp.j.l.a.FULLSCREEN;
        this.a = aVar;
        this.f = false;
        a(aVar, PushwooshPlatform.getInstance().h().a(), context);
    }

    protected f(@NonNull Context context, com.pushwoosh.inapp.j.l.a aVar, RichMediaStyle richMediaStyle, boolean z) {
        super(context);
        this.a = com.pushwoosh.inapp.j.l.a.FULLSCREEN;
        this.f = false;
        this.k = z;
        a(aVar, richMediaStyle, context);
    }

    private View a(Context context) {
        int i2 = context.getApplicationInfo().theme;
        if (i2 == 0) {
            i2 = Build.VERSION.SDK_INT >= 21 ? 16974372 : 16973931;
        }
        View inflate = View.inflate(new ContextThemeWrapper(context, i2), R.layout.pw_default_loading_view, null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        inflate.setLayoutParams(layoutParams);
        return inflate;
    }

    private void a(View view, int i2, int i3) {
        ObjectAnimator ofInt = ObjectAnimator.ofInt(view, "backgroundColor", i2, i3);
        ofInt.setEvaluator(new ArgbEvaluator());
        ofInt.setDuration(300L);
        ofInt.start();
    }

    private void a(com.pushwoosh.inapp.j.l.a aVar, RichMediaStyle richMediaStyle, Context context) {
        this.a = aVar;
        this.h = richMediaStyle.getRichMediaAnimation();
        this.j = richMediaStyle.getBackgroundColor();
        if (this.j == 0) {
            this.j = Color.parseColor("#40000000");
        }
        this.b = new FrameLayout(getContext());
        this.b.setLayoutParams(a(aVar, 0));
        this.b.setBackgroundColor(0);
        setWebViewDataDirectorySuffixIfNeeded(context);
        e();
        this.c = richMediaStyle.getLoadingViewCreator() != null ? richMediaStyle.getLoadingViewCreator().a() : a(getContext());
        this.c.setVisibility(8);
        this.b.addView(this.d);
        this.b.addView(this.c);
        addView(this.b);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void g() {
        this.c.setAlpha(0.0f);
        this.c.setVisibility(0);
        this.c.animate().alpha(1.0f).setDuration(300).start();
    }

    private void setWebViewDataDirectorySuffixIfNeeded(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 28 && !TextUtils.equals(context.getPackageName(), Application.getProcessName())) {
                WebView.setDataDirectorySuffix(Application.getProcessName());
            }
        } catch (Throwable th) {
            PWLog.error("Error occurred when tried to set Webview data dirrectory suffix: " + th.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    @NonNull
    public FrameLayout.LayoutParams a(com.pushwoosh.inapp.j.l.a aVar, int i2) {
        int i3;
        FrameLayout.LayoutParams layoutParams;
        switch (a.a[aVar.ordinal()]) {
            case 1:
                return new FrameLayout.LayoutParams(-1, -1);
            case 2:
                layoutParams = new FrameLayout.LayoutParams(-1, -2);
                i3 = 80;
                break;
            case 3:
                FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
                layoutParams2.gravity = 48;
                if (this.k) {
                    return layoutParams2;
                }
                layoutParams2.topMargin = i2;
                return layoutParams2;
            case 4:
                layoutParams = new FrameLayout.LayoutParams(-1, -2);
                i3 = 17;
                break;
            default:
                throw new IllegalArgumentException("Unrecognized mode: " + aVar.toString());
        }
        layoutParams.gravity = i3;
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (!this.e) {
            a(this.b, Color.alpha(0), this.j);
            this.e = true;
            RichMediaAnimation richMediaAnimation = this.h;
            if (richMediaAnimation != null) {
                richMediaAnimation.openAnimation(this.d, this.b);
                return;
            }
            return;
        }
        this.b.setBackgroundColor(this.j);
    }

    /* access modifiers changed from: protected */
    public void a(Animation.AnimationListener animationListener) {
        a(this.b, this.j, Color.alpha(0));
        RichMediaAnimation richMediaAnimation = this.h;
        if (richMediaAnimation != null) {
            richMediaAnimation.closeAnimation(this.d, this.b, animationListener);
        }
    }

    /* access modifiers changed from: protected */
    public void a(com.pushwoosh.inapp.i.a aVar) {
        String b2 = aVar.b();
        String c2 = aVar.c();
        if (!c2.endsWith("/")) {
            c2 = c2 + "/";
        }
        a(c2, b2.replace("<head>", "<head>\n<script type=\"text/javascript\">(function () {if (window.pushwoosh) return;window._pwCallbackHelper = {    __callbacks: {},    __cbCounter: 0,    invokeCallback: function(cbID) {        var args = Array.prototype.slice.call(arguments);        args.shift();        var cb = this.__callbacks[cbID];        this.__callbacks[cbID] = undefined;        return cb.apply(null, args);    },    registerCallback: function(func) {        var cbID = \"__cb\" + (+new Date) + this.__cbCounter;        this.__cbCounter++;        this.__callbacks[cbID] = func;        return cbID;    }};window.pushwoosh = {    _hwid: \"%s\",    _version: \"%s\",    postEvent: function(event, attributes, successCallback, errorCallback) {        if (!attributes) {            attributes = {};        }        if (!successCallback) {            successCallback = function() {};        }        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(successCallback);        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.postEvent(event, JSON.stringify(attributes), successCbId, errorCbId);    },    sendTags: function(tags) {        pushwooshImpl.sendTags(JSON.stringify(tags));    },    getTags: function(successCallback, errorCallback) {        if (!errorCallback) {            errorCallback = function(error) {};        }        var successCbId = _pwCallbackHelper.registerCallback(function(tagsString) {            console.log(\"tags: \" + tagsString);            successCallback(JSON.parse(tagsString));        });        var errorCbId = _pwCallbackHelper.registerCallback(errorCallback);        pushwooshImpl.getTags(successCbId, errorCbId);    },    isCommunicationEnabled: function() {        return pushwooshImpl.isCommunicationEnabled();    },    setCommunicationEnabled: function(enabled) {        pushwooshImpl.setCommunicationEnabled(enabled);    },    removeAllDeviceData: function() {        pushwooshImpl.removeAllDeviceData();    },    log: function(str) {        pushwooshImpl.log(str);    },    closeInApp: function() {        pushwooshImpl.closeInApp();    },    getHwid: function() {        return this._hwid;    },    getVersion: function() {        return this._version;    },    getCustomData: function() {         var customData = pushwooshImpl.getCustomData();         if (customData) {             return JSON.parse(customData);         } else {             return null;         }    },    registerForPushNotifications: function() {        pushwooshImpl.registerForPushNotifications();    },    openAppSettings: function() {        pushwooshImpl.openAppSettings();    },    getChannels: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(channels) {             callback(JSON.parse(channels));        });        pushwooshImpl.getChannels(clb);    },    unregisterForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(callback);        pushwooshImpl.unregisterForPushNotifications(clb);    },    isRegisteredForPushNotifications: function(callback) {        var clb = _pwCallbackHelper.registerCallback(function(state) {           if (state == 'true') {callback(true);} else if (state == 'false') {callback(false);}        });        pushwooshImpl.isRegisteredForPushNotifications(clb);    }};}());</script>"), "text/html", Key.STRING_CHARSET_NAME, null);
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        this.d.loadUrl(str);
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2, String str3, String str4, String str5) {
        this.d.getSettings().setAllowFileAccess(true);
        this.d.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.d.setWebViewClient(null);
        this.d = null;
    }

    /* access modifiers changed from: protected */
    public WebView c() {
        return new WebView(getContext());
    }

    /* access modifiers changed from: protected */
    public void d() {
        if (this.f) {
            this.f = false;
            Handler handler = this.i;
            if (handler != null) {
                Runnable runnable = this.g;
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                    this.g = null;
                }
                this.i = null;
            }
            this.c.animate().alpha(0.0f).setDuration(300).start();
            this.d.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"SetJavaScriptEnabled"})
    public void e() {
        this.d = c();
        this.d.getSettings().setJavaScriptEnabled(true);
        this.d.setLayoutParams(a(this.a, getStatusBarHeight()));
        this.d.setBackgroundColor(0);
        this.d.setLongClickable(false);
        this.d.setHapticFeedbackEnabled(false);
    }

    /* access modifiers changed from: protected */
    public void f() {
        if (!this.f) {
            this.f = true;
            this.i = new Handler();
            Handler handler = this.i;
            $$Lambda$f$EYUBDBF8pK2b1e9ZPmX2g8rc81w r1 = new Runnable() {
                /* class com.pushwoosh.inapp.view.$$Lambda$f$EYUBDBF8pK2b1e9ZPmX2g8rc81w */

                public final void run() {
                    f.this.g();
                }
            };
            this.g = r1;
            handler.postDelayed(r1, 500);
            this.d.setVisibility(4);
        }
    }

    public int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void setWebViewClient(g gVar) {
        gVar.a(this.d);
    }
}
