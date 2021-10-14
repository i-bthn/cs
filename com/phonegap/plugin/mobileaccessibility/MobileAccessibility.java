package com.phonegap.plugin.mobileaccessibility;

import android.os.Build;
import android.webkit.WebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MobileAccessibility extends CordovaPlugin {
    private boolean mCachedIsScreenReaderRunning = false;
    private CallbackContext mCallbackContext = null;
    private boolean mClosedCaptioningEnabled = false;
    private float mFontScale = 1.0f;
    private boolean mIsScreenReaderRunning = false;
    private AbstractMobileAccessibilityHelper mMobileAccessibilityHelper;
    private boolean mTouchExplorationEnabled = false;

    @Override // org.apache.cordova.CordovaPlugin
    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        if (Build.VERSION.SDK_INT >= 19) {
            this.mMobileAccessibilityHelper = new KitKatMobileAccessibilityHelper();
        } else if (Build.VERSION.SDK_INT >= 16) {
            this.mMobileAccessibilityHelper = new JellyBeanMobileAccessibilityHelper();
        } else if (Build.VERSION.SDK_INT >= 14) {
            this.mMobileAccessibilityHelper = new IceCreamSandwichMobileAccessibilityHelper();
        } else {
            this.mMobileAccessibilityHelper = new DonutMobileAccessibilityHelper();
        }
        this.mMobileAccessibilityHelper.initialize(this);
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        try {
            if (str.equals("isScreenReaderRunning")) {
                isScreenReaderRunning(callbackContext);
                return true;
            } else if (str.equals("isClosedCaptioningEnabled")) {
                isClosedCaptioningEnabled(callbackContext);
                return true;
            } else if (str.equals("isTouchExplorationEnabled")) {
                isTouchExplorationEnabled(callbackContext);
                return true;
            } else if (str.equals("postNotification")) {
                if (jSONArray.length() > 1) {
                    String string = jSONArray.getString(1);
                    if (!string.isEmpty()) {
                        announceForAccessibility(string, callbackContext);
                    }
                }
                return true;
            } else if (str.equals("getTextZoom")) {
                getTextZoom(callbackContext);
                return true;
            } else if (str.equals("setTextZoom")) {
                if (jSONArray.length() > 0) {
                    double d = jSONArray.getDouble(0);
                    if (d > 0.0d) {
                        setTextZoom(d, callbackContext);
                    }
                }
                return true;
            } else if (str.equals("updateTextZoom")) {
                updateTextZoom(callbackContext);
                return true;
            } else if (str.equals("start")) {
                start(callbackContext);
                return true;
            } else {
                if (str.equals("stop")) {
                    stop();
                    return true;
                }
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onPause(boolean z) {
        this.mCachedIsScreenReaderRunning = this.mIsScreenReaderRunning;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onResume(boolean z) {
        boolean z2 = this.mIsScreenReaderRunning;
        if (z2 && !this.mCachedIsScreenReaderRunning) {
            this.mCachedIsScreenReaderRunning = z2;
            stop();
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                /* class com.phonegap.plugin.mobileaccessibility.MobileAccessibility.AnonymousClass1 */

                /* JADX WARNING: Can't wrap try/catch for region: R(5:0|1|2|3|14) */
                /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:4:0x003b, code lost:
                    r0 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:5:0x003c, code lost:
                    r0.printStackTrace();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:6:0x0040, code lost:
                    r0 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:7:0x0041, code lost:
                    r0.printStackTrace();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:8:0x0045, code lost:
                    r0 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:9:0x0046, code lost:
                    r0.printStackTrace();
                 */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x000a */
                public void run() {
                    ((WebView) MobileAccessibility.this.webView).reload();
                    MobileAccessibility.this.webView.getClass().getMethod("getView", new Class[0]).invoke(MobileAccessibility.this.webView, new Object[0]).getClass().getMethod("reload", new Class[0]).invoke(MobileAccessibility.this.webView, new Object[0]);
                }
            });
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onDestroy() {
        stop();
    }

    private void isScreenReaderRunning(final CallbackContext callbackContext) {
        this.mIsScreenReaderRunning = this.mMobileAccessibilityHelper.isScreenReaderRunning();
        this.cordova.getThreadPool().execute(new Runnable() {
            /* class com.phonegap.plugin.mobileaccessibility.MobileAccessibility.AnonymousClass2 */

            public void run() {
                callbackContext.success(MobileAccessibility.this.mIsScreenReaderRunning ? 1 : 0);
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean isScreenReaderRunning() {
        this.mIsScreenReaderRunning = this.mMobileAccessibilityHelper.isScreenReaderRunning();
        return this.mIsScreenReaderRunning;
    }

    private void isClosedCaptioningEnabled(final CallbackContext callbackContext) {
        this.mClosedCaptioningEnabled = this.mMobileAccessibilityHelper.isClosedCaptioningEnabled();
        this.cordova.getThreadPool().execute(new Runnable() {
            /* class com.phonegap.plugin.mobileaccessibility.MobileAccessibility.AnonymousClass3 */

            public void run() {
                callbackContext.success(MobileAccessibility.this.mClosedCaptioningEnabled ? 1 : 0);
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean isClosedCaptioningEnabled() {
        this.mClosedCaptioningEnabled = this.mMobileAccessibilityHelper.isClosedCaptioningEnabled();
        return this.mClosedCaptioningEnabled;
    }

    private void isTouchExplorationEnabled(final CallbackContext callbackContext) {
        this.mTouchExplorationEnabled = this.mMobileAccessibilityHelper.isTouchExplorationEnabled();
        this.cordova.getThreadPool().execute(new Runnable() {
            /* class com.phonegap.plugin.mobileaccessibility.MobileAccessibility.AnonymousClass4 */

            public void run() {
                callbackContext.success(MobileAccessibility.this.mTouchExplorationEnabled ? 1 : 0);
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean isTouchExplorationEnabled() {
        this.mTouchExplorationEnabled = this.mMobileAccessibilityHelper.isTouchExplorationEnabled();
        return this.mTouchExplorationEnabled;
    }

    private void announceForAccessibility(CharSequence charSequence, CallbackContext callbackContext) {
        this.mMobileAccessibilityHelper.announceForAccessibility(charSequence);
        if (callbackContext != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("stringValue", charSequence);
                jSONObject.put("wasSuccessful", this.mIsScreenReaderRunning);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            callbackContext.success(jSONObject);
        }
    }

    public void onAccessibilityStateChanged(boolean z) {
        this.mIsScreenReaderRunning = z;
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            /* class com.phonegap.plugin.mobileaccessibility.MobileAccessibility.AnonymousClass5 */

            public void run() {
                MobileAccessibility.this.sendMobileAccessibilityStatusChangedCallback();
            }
        });
    }

    public void onCaptioningEnabledChanged(boolean z) {
        this.mClosedCaptioningEnabled = z;
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            /* class com.phonegap.plugin.mobileaccessibility.MobileAccessibility.AnonymousClass6 */

            public void run() {
                MobileAccessibility.this.sendMobileAccessibilityStatusChangedCallback();
            }
        });
    }

    public void onTouchExplorationStateChanged(boolean z) {
        this.mTouchExplorationEnabled = z;
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            /* class com.phonegap.plugin.mobileaccessibility.MobileAccessibility.AnonymousClass7 */

            public void run() {
                MobileAccessibility.this.sendMobileAccessibilityStatusChangedCallback();
            }
        });
    }

    private void getTextZoom(final CallbackContext callbackContext) {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            /* class com.phonegap.plugin.mobileaccessibility.MobileAccessibility.AnonymousClass8 */

            public void run() {
                double textZoom = MobileAccessibility.this.mMobileAccessibilityHelper.getTextZoom();
                CallbackContext callbackContext = callbackContext;
                if (callbackContext != null) {
                    callbackContext.success((int) textZoom);
                }
            }
        });
    }

    private void setTextZoom(final double d, final CallbackContext callbackContext) {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            /* class com.phonegap.plugin.mobileaccessibility.MobileAccessibility.AnonymousClass9 */

            public void run() {
                MobileAccessibility.this.mMobileAccessibilityHelper.setTextZoom(d);
                CallbackContext callbackContext = callbackContext;
                if (callbackContext != null) {
                    callbackContext.success((int) MobileAccessibility.this.mMobileAccessibilityHelper.getTextZoom());
                }
            }
        });
    }

    public void setTextZoom(final double d) {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            /* class com.phonegap.plugin.mobileaccessibility.MobileAccessibility.AnonymousClass10 */

            public void run() {
                MobileAccessibility.this.mMobileAccessibilityHelper.setTextZoom(d);
            }
        });
    }

    private void updateTextZoom(CallbackContext callbackContext) {
        float f = this.cordova.getActivity().getResources().getConfiguration().fontScale;
        if (f != this.mFontScale) {
            this.mFontScale = f;
        }
        setTextZoom((double) Math.round(this.mFontScale * 100.0f), callbackContext);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendMobileAccessibilityStatusChangedCallback() {
        if (this.mCallbackContext != null) {
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, getMobileAccessibilityStatus());
            pluginResult.setKeepCallback(true);
            this.mCallbackContext.sendPluginResult(pluginResult);
        }
    }

    private JSONObject getMobileAccessibilityStatus() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isScreenReaderRunning", this.mIsScreenReaderRunning);
            jSONObject.put("isClosedCaptioningEnabled", this.mClosedCaptioningEnabled);
            jSONObject.put("isTouchExplorationEnabled", this.mTouchExplorationEnabled);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void start(CallbackContext callbackContext) {
        this.mCallbackContext = callbackContext;
        this.mMobileAccessibilityHelper.addStateChangeListeners();
        sendMobileAccessibilityStatusChangedCallback();
    }

    private void stop() {
        if (this.mCallbackContext != null) {
            sendMobileAccessibilityStatusChangedCallback();
            this.mMobileAccessibilityHelper.removeStateChangeListeners();
            this.mCallbackContext = null;
        }
    }
}
