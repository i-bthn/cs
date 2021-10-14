package com.pushwoosh.e.a.a.a.i;

import android.content.SharedPreferences;

public interface b {
    void a(String str);

    void a(String str, byte[] bArr);

    void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener);

    void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener);
}
