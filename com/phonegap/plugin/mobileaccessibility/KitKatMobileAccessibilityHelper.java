package com.phonegap.plugin.mobileaccessibility;

import android.annotation.TargetApi;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;

@TargetApi(19)
public class KitKatMobileAccessibilityHelper extends JellyBeanMobileAccessibilityHelper {
    private CaptioningManager.CaptioningChangeListener mCaptioningChangeListener;
    private CaptioningManager mCaptioningManager;
    private AccessibilityManager.TouchExplorationStateChangeListener mTouchExplorationStateChangeListener;

    @Override // com.phonegap.plugin.mobileaccessibility.DonutMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.JellyBeanMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void initialize(MobileAccessibility mobileAccessibility) {
        super.initialize(mobileAccessibility);
        this.mCaptioningManager = (CaptioningManager) mobileAccessibility.cordova.getActivity().getSystemService("captioning");
    }

    @Override // com.phonegap.plugin.mobileaccessibility.IceCreamSandwichMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.DonutMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public boolean isScreenReaderRunning() {
        return this.mAccessibilityManager.getEnabledAccessibilityServiceList(33).size() > 0;
    }

    @Override // com.phonegap.plugin.mobileaccessibility.DonutMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public boolean isClosedCaptioningEnabled() {
        return this.mCaptioningManager.isEnabled();
    }

    @Override // com.phonegap.plugin.mobileaccessibility.DonutMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public boolean isTouchExplorationEnabled() {
        return this.mAccessibilityManager.isTouchExplorationEnabled();
    }

    @Override // com.phonegap.plugin.mobileaccessibility.IceCreamSandwichMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.DonutMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void addStateChangeListeners() {
        super.addStateChangeListeners();
        if (this.mCaptioningChangeListener == null) {
            this.mCaptioningChangeListener = new CaptioningManager.CaptioningChangeListener() {
                /* class com.phonegap.plugin.mobileaccessibility.KitKatMobileAccessibilityHelper.AnonymousClass1 */

                public void onEnabledChanged(boolean z) {
                    KitKatMobileAccessibilityHelper.this.onCaptioningEnabledChanged(z);
                }
            };
        }
        this.mCaptioningManager.addCaptioningChangeListener(this.mCaptioningChangeListener);
        if (this.mTouchExplorationStateChangeListener == null) {
            this.mTouchExplorationStateChangeListener = new InternalTouchExplorationStateChangeListener();
        }
        this.mAccessibilityManager.addTouchExplorationStateChangeListener(this.mTouchExplorationStateChangeListener);
    }

    @Override // com.phonegap.plugin.mobileaccessibility.IceCreamSandwichMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.DonutMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void removeStateChangeListeners() {
        super.removeStateChangeListeners();
        CaptioningManager.CaptioningChangeListener captioningChangeListener = this.mCaptioningChangeListener;
        if (captioningChangeListener != null) {
            this.mCaptioningManager.removeCaptioningChangeListener(captioningChangeListener);
            this.mCaptioningChangeListener = null;
        }
        if (this.mTouchExplorationStateChangeListener != null) {
            this.mAccessibilityManager.removeTouchExplorationStateChangeListener(this.mTouchExplorationStateChangeListener);
            this.mTouchExplorationStateChangeListener = null;
        }
    }

    private class InternalTouchExplorationStateChangeListener implements AccessibilityManager.TouchExplorationStateChangeListener {
        private InternalTouchExplorationStateChangeListener() {
        }

        public void onTouchExplorationStateChanged(boolean z) {
            KitKatMobileAccessibilityHelper.this.mMobileAccessibility.onTouchExplorationStateChanged(z);
        }
    }
}
