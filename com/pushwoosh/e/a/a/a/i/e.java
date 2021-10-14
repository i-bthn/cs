package com.pushwoosh.e.a.a.a.i;

import android.content.SharedPreferences;

public final class e implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final SharedPreferences a;
    private final SharedPreferences.OnSharedPreferenceChangeListener b;

    public e(SharedPreferences sharedPreferences, SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.a = sharedPreferences;
        this.b = onSharedPreferenceChangeListener;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || e.class != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = this.b;
        if (onSharedPreferenceChangeListener == null ? eVar.b != null : !onSharedPreferenceChangeListener.equals(eVar.b)) {
            return false;
        }
        SharedPreferences sharedPreferences = this.a;
        return sharedPreferences != null ? sharedPreferences.equals(eVar.a) : eVar.a == null;
    }

    public int hashCode() {
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = this.b;
        int i = 0;
        int hashCode = (onSharedPreferenceChangeListener != null ? onSharedPreferenceChangeListener.hashCode() : 0) * 31;
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences != null) {
            i = sharedPreferences.hashCode();
        }
        return hashCode + i;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.b.onSharedPreferenceChanged(this.a, str);
    }
}
