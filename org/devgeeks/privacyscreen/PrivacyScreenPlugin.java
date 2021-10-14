package org.devgeeks.privacyscreen;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

public class PrivacyScreenPlugin extends CordovaPlugin {
    @Override // org.apache.cordova.CordovaPlugin
    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        this.cordova.getActivity();
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onPause(boolean z) {
        this.cordova.getActivity().getWindow().addFlags(8192);
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onResume(boolean z) {
        this.cordova.getActivity().getWindow().clearFlags(8192);
    }
}
