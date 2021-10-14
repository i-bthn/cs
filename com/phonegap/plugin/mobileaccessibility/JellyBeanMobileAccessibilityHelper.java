package com.phonegap.plugin.mobileaccessibility;

import android.annotation.TargetApi;
import android.view.accessibility.AccessibilityEvent;

@TargetApi(16)
public class JellyBeanMobileAccessibilityHelper extends IceCreamSandwichMobileAccessibilityHelper {
    @Override // com.phonegap.plugin.mobileaccessibility.DonutMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void initialize(MobileAccessibility mobileAccessibility) {
        super.initialize(mobileAccessibility);
        this.mParent = this.mView.getParentForAccessibility();
    }

    @Override // com.phonegap.plugin.mobileaccessibility.DonutMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void announceForAccessibility(CharSequence charSequence) {
        if (this.mAccessibilityManager.isEnabled() && this.mParent != null) {
            this.mAccessibilityManager.interrupt();
            AccessibilityEvent obtain = AccessibilityEvent.obtain(16384);
            this.mView.onInitializeAccessibilityEvent(obtain);
            obtain.getText().add(charSequence);
            obtain.setContentDescription(null);
            this.mParent.requestSendAccessibilityEvent(this.mView, obtain);
        }
    }
}
