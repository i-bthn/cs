package com.phonegap.plugin.mobileaccessibility;

import android.view.ViewParent;

/* access modifiers changed from: package-private */
public abstract class AbstractMobileAccessibilityHelper {
    MobileAccessibility mMobileAccessibility;
    ViewParent mParent;

    public abstract void addStateChangeListeners();

    public abstract void announceForAccessibility(CharSequence charSequence);

    public abstract double getTextZoom();

    public abstract void initialize(MobileAccessibility mobileAccessibility);

    public abstract boolean isClosedCaptioningEnabled();

    public abstract boolean isScreenReaderRunning();

    public abstract boolean isTouchExplorationEnabled();

    public abstract void onAccessibilityStateChanged(boolean z);

    public abstract void onCaptioningEnabledChanged(boolean z);

    public abstract void onTouchExplorationStateChanged(boolean z);

    public abstract void removeStateChangeListeners();

    public abstract void setTextZoom(double d);

    AbstractMobileAccessibilityHelper() {
    }
}
