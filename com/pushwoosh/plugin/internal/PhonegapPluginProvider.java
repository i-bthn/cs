package com.pushwoosh.plugin.internal;

import com.pushwoosh.internal.PluginProvider;

public class PhonegapPluginProvider implements PluginProvider {
    @Override // com.pushwoosh.internal.PluginProvider
    public int richMediaStartDelay() {
        return 100;
    }

    @Override // com.pushwoosh.internal.PluginProvider
    public String getPluginType() {
        try {
            Class.forName("org.apache.cordova.CordovaPlugin");
            return "Cordova";
        } catch (ClassNotFoundException unused) {
            return "PhoneGap Build";
        }
    }
}
