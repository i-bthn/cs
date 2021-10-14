package com.phonegap.plugin.mobileaccessibility;

import android.annotation.TargetApi;
import android.view.accessibility.AccessibilityManager;
import java.lang.reflect.InvocationTargetException;

@TargetApi(14)
public class IceCreamSandwichMobileAccessibilityHelper extends DonutMobileAccessibilityHelper {
    private AccessibilityManager.AccessibilityStateChangeListener mAccessibilityStateChangeListener;

    @Override // com.phonegap.plugin.mobileaccessibility.DonutMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public boolean isScreenReaderRunning() {
        return this.mAccessibilityManager.getEnabledAccessibilityServiceList(1).size() > 0;
    }

    @Override // com.phonegap.plugin.mobileaccessibility.DonutMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void addStateChangeListeners() {
        if (this.mAccessibilityStateChangeListener == null) {
            this.mAccessibilityStateChangeListener = new InternalAccessibilityStateChangeListener();
        }
        this.mAccessibilityManager.addAccessibilityStateChangeListener(this.mAccessibilityStateChangeListener);
    }

    @Override // com.phonegap.plugin.mobileaccessibility.DonutMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void removeStateChangeListeners() {
        this.mAccessibilityManager.removeAccessibilityStateChangeListener(this.mAccessibilityStateChangeListener);
        this.mAccessibilityStateChangeListener = null;
    }

    @Override // com.phonegap.plugin.mobileaccessibility.DonutMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public double getTextZoom() {
        try {
            Object invoke = this.mView.getClass().getMethod("getSettings", new Class[0]).invoke(this.mView, new Object[0]);
            return Double.valueOf(invoke.getClass().getMethod("getTextZoom", new Class[0]).invoke(invoke, new Object[0]).toString()).doubleValue();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
        }
        return 100.0d;
    }

    @Override // com.phonegap.plugin.mobileaccessibility.DonutMobileAccessibilityHelper, com.phonegap.plugin.mobileaccessibility.AbstractMobileAccessibilityHelper
    public void setTextZoom(double d) {
        try {
            Object invoke = this.mView.getClass().getMethod("getSettings", new Class[0]).invoke(this.mView, new Object[0]);
            invoke.getClass().getMethod("setTextZoom", Integer.TYPE).invoke(invoke, Integer.valueOf((int) d));
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
        }
    }

    private class InternalAccessibilityStateChangeListener implements AccessibilityManager.AccessibilityStateChangeListener {
        private InternalAccessibilityStateChangeListener() {
        }

        public void onAccessibilityStateChanged(boolean z) {
            IceCreamSandwichMobileAccessibilityHelper.this.mMobileAccessibility.onAccessibilityStateChanged(z);
        }
    }
}
