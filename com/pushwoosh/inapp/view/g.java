package com.pushwoosh.inapp.view;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.inapp.event.d;
import com.pushwoosh.inapp.j.l.b;
import com.pushwoosh.inapp.view.h.a;
import com.pushwoosh.inapp.view.h.c;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RepositoryModule;
import java.util.Map;
import java.util.logging.Logger;

public class g extends WebViewClient implements a {
    private final c a;
    private final Map<String, Object> b;
    private c c;
    private Handler d = new Handler(Looper.getMainLooper());
    private b e;
    private View f;

    public g(c cVar, b bVar) {
        this.a = cVar;
        this.e = bVar;
        this.b = PushwooshPlatform.getInstance().n().b();
    }

    private void a(Context context) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
            launchIntentForPackage.setFlags(872415232);
            context.startActivity(launchIntentForPackage);
        } catch (ActivityNotFoundException unused) {
            Logger.getLogger(g.class.getSimpleName()).severe("Failed to start default launch activity.");
        }
    }

    private void a(Uri uri, Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            PWLog.error("Can't open remote url: " + uri, e2);
        }
    }

    private boolean a() {
        return (this.a.b() & 1) != 0;
    }

    private boolean a(Context context, Uri uri) {
        PWLog.noise("[InApp]WebClient", "Trying to open url: " + uri);
        if (!uri.getScheme().equals("pushwoosh")) {
            if (!uri.getScheme().startsWith("file")) {
                if (!a()) {
                    a(uri, context);
                } else {
                    RepositoryModule.getLockScreenMediaStorage().a(uri);
                }
                this.a.close();
            }
            return true;
        } else if (uri.getHost() != null) {
            return a(uri.getHost(), context);
        } else {
            PWLog.error("[InApp]WebClient", "Wrong url format: " + uri);
            return true;
        }
    }

    private boolean a(String str, Context context) {
        if (!str.equalsIgnoreCase("close")) {
            if (!str.equalsIgnoreCase("open")) {
                PWLog.error("[InApp]WebClient", "Unrecognized pushwoosh method: " + str);
                return true;
            } else if (!a()) {
                return true;
            } else {
                a(context);
            }
        }
        this.a.close();
        return true;
    }

    public void a(View view) {
        this.f = view;
    }

    public void a(WebView webView) {
        this.c = new c(this, webView, this.f, RepositoryModule.getNotificationPreferences().b().get());
        RepositoryModule.getNotificationPreferences().b().set(null);
        webView.setWebViewClient(this);
        webView.addJavascriptInterface(new com.pushwoosh.inapp.view.h.b(webView, this), "pushManager");
        webView.addJavascriptInterface(this.c, "pushwooshImpl");
        for (Map.Entry<String, Object> entry : this.b.entrySet()) {
            webView.addJavascriptInterface(entry.getValue(), entry.getKey());
        }
    }

    @Override // com.pushwoosh.inapp.view.h.a
    public void close() {
        Handler handler = this.d;
        c cVar = this.a;
        cVar.getClass();
        handler.post(new Runnable() {
            /* class com.pushwoosh.inapp.view.$$Lambda$JTBEu4xESZ6oWA781GIsTUygg */

            public final void run() {
                c.this.close();
            }
        });
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        PWLog.noise("[InApp]WebClient", "Finished loading url: " + str);
        this.c.onPageFinished(webView);
        this.a.c();
        EventBus.sendEvent(new d(this.e));
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        PWLog.noise("[InApp]WebClient", "Page started: " + str);
        this.c.onPageStarted(webView);
    }

    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        PWLog.error("[InApp]WebClient", "Page failed: " + webResourceRequest.toString() + "; " + webResourceError.toString());
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return a(webView.getContext(), webResourceRequest.getUrl());
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return a(webView.getContext(), Uri.parse(str));
    }
}
