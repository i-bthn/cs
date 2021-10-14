package org.apache.cordova.engine;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.WebView;
import org.apache.cordova.ICordovaCookieManager;

/* access modifiers changed from: package-private */
public class SystemCookieManager implements ICordovaCookieManager {
    private final CookieManager cookieManager = CookieManager.getInstance();
    protected final WebView webView;

    @TargetApi(21)
    public SystemCookieManager(WebView webView2) {
        this.webView = webView2;
        CookieManager cookieManager2 = this.cookieManager;
        CookieManager.setAcceptFileSchemeCookies(true);
        if (Build.VERSION.SDK_INT >= 21) {
            this.cookieManager.setAcceptThirdPartyCookies(this.webView, true);
        }
    }

    @Override // org.apache.cordova.ICordovaCookieManager
    public void setCookiesEnabled(boolean z) {
        this.cookieManager.setAcceptCookie(z);
    }

    @Override // org.apache.cordova.ICordovaCookieManager
    public void setCookie(String str, String str2) {
        this.cookieManager.setCookie(str, str2);
    }

    @Override // org.apache.cordova.ICordovaCookieManager
    public String getCookie(String str) {
        return this.cookieManager.getCookie(str);
    }

    @Override // org.apache.cordova.ICordovaCookieManager
    public void clearCookies() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.cookieManager.removeAllCookies(null);
        } else {
            this.cookieManager.removeAllCookie();
        }
    }

    @Override // org.apache.cordova.ICordovaCookieManager
    public void flush() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.cookieManager.flush();
        }
    }
}
