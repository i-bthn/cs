package com.phonegap.plugin.mobileaccessibility;

import android.annotation.TargetApi;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.InvocationTargetException;

@TargetApi(4)
public class DonutMobileAccessibilityHelper extends AbstractMobileAccessibilityHelper {
    AccessibilityManager mAccessibilityManager;
    View mView;

    @Override // com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void addStateChangeListeners() {
    }

    @Override // com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public boolean isClosedCaptioningEnabled() {
        return false;
    }

    @Override // com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public boolean isTouchExplorationEnabled() {
        return false;
    }

    @Override // com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void removeStateChangeListeners() {
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:0|1|2|3|4|11|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        r5.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0025, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0026, code lost:
        r5.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002b, code lost:
        r5.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002f, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0009 */
    @Override // com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void initialize(MobileAccessibility mobileAccessibility) {
        this.mMobileAccessibility = mobileAccessibility;
        this.mView = (WebView) mobileAccessibility.webView;
        this.mView = (View) mobileAccessibility.webView.getClass().getMethod("getView", new Class[0]).invoke(mobileAccessibility.webView, new Object[0]);
        this.mAccessibilityManager = (AccessibilityManager) this.mMobileAccessibility.cordova.getActivity().getSystemService("accessibility");
    }

    @Override // com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public boolean isScreenReaderRunning() {
        return this.mAccessibilityManager.isEnabled();
    }

    @Override // com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void onAccessibilityStateChanged(boolean z) {
        this.mMobileAccessibility.onAccessibilityStateChanged(z);
    }

    @Override // com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void onCaptioningEnabledChanged(boolean z) {
        this.mMobileAccessibility.onCaptioningEnabledChanged(z);
    }

    @Override // com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void onTouchExplorationStateChanged(boolean z) {
        this.mMobileAccessibility.onTouchExplorationStateChanged(z);
    }

    @Override // com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void announceForAccessibility(CharSequence charSequence) {
        if (this.mAccessibilityManager.isEnabled()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain(8);
            obtain.getText().add(charSequence);
            obtain.setEnabled(this.mView.isEnabled());
            obtain.setClassName(this.mView.getClass().getName());
            obtain.setPackageName(this.mView.getContext().getPackageName());
            obtain.setContentDescription(null);
            this.mAccessibilityManager.sendAccessibilityEvent(obtain);
        }
    }

    @Override // com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public double getTextZoom() {
        WebSettings.TextSize textSize = WebSettings.TextSize.NORMAL;
        try {
            Object invoke = this.mView.getClass().getMethod("getSettings", new Class[0]).invoke(this.mView, new Object[0]);
            textSize = (WebSettings.TextSize) invoke.getClass().getMethod("getTextSize", new Class[0]).invoke(invoke, new Object[0]);
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
        }
        switch (AnonymousClass1.$SwitchMap$android$webkit$WebSettings$TextSize[textSize.ordinal()]) {
            case 1:
                return 200.0d;
            case 2:
                return 150.0d;
            case 3:
                return 75.0d;
            case 4:
                return 50.0d;
            default:
                return 100.0d;
        }
    }

    /* renamed from: com.phonegap.plugin.mobileaccessibility.DonutMobileAccessibilityHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$webkit$WebSettings$TextSize = new int[WebSettings.TextSize.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            $SwitchMap$android$webkit$WebSettings$TextSize[WebSettings.TextSize.LARGEST.ordinal()] = 1;
            $SwitchMap$android$webkit$WebSettings$TextSize[WebSettings.TextSize.LARGER.ordinal()] = 2;
            $SwitchMap$android$webkit$WebSettings$TextSize[WebSettings.TextSize.SMALLER.ordinal()] = 3;
            try {
                $SwitchMap$android$webkit$WebSettings$TextSize[WebSettings.TextSize.SMALLEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @Override // com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void setTextZoom(double d) {
        WebSettings.TextSize textSize = WebSettings.TextSize.SMALLEST;
        if (d > 115.0d) {
            textSize = WebSettings.TextSize.LARGEST;
        } else if (d > 100.0d) {
            textSize = WebSettings.TextSize.LARGER;
        } else if (d == 100.0d) {
            textSize = WebSettings.TextSize.NORMAL;
        } else if (d > 50.0d) {
            textSize = WebSettings.TextSize.SMALLER;
        }
        try {
            Object invoke = this.mView.getClass().getMethod("getSettings", new Class[0]).invoke(this.mView, new Object[0]);
            invoke.getClass().getMethod("setTextSize", WebSettings.TextSize.class).invoke(invoke, textSize);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }
}
