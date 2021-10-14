package com.pushwoosh.internal;

public class a implements PluginProvider {
    @Override // com.pushwoosh.internal.PluginProvider
    public String getPluginType() {
        return "Native";
    }

    @Override // com.pushwoosh.internal.PluginProvider
    public int richMediaStartDelay() {
        return 100;
    }
}
