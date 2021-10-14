package com.pushwoosh.e.a.a.a.i;

import android.content.SharedPreferences;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class d implements b {
    private final List<SharedPreferences.OnSharedPreferenceChangeListener> a;
    private final Handler b = new Handler();

    /* access modifiers changed from: package-private */
    public class a implements Runnable {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        public void run() {
            for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : new ArrayList(d.this.a)) {
                onSharedPreferenceChangeListener.onSharedPreferenceChanged(null, this.a);
            }
        }
    }

    public d(String str, Map<String, List<SharedPreferences.OnSharedPreferenceChangeListener>> map) {
        this.a = a(str, map);
    }

    private List<SharedPreferences.OnSharedPreferenceChangeListener> a(String str, Map<String, List<SharedPreferences.OnSharedPreferenceChangeListener>> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        ArrayList arrayList = new ArrayList();
        map.put(str, arrayList);
        return arrayList;
    }

    private void b(String str) {
        this.b.post(new a(str));
    }

    @Override // com.pushwoosh.e.a.a.a.i.b
    public void a(String str) {
        b(str);
    }

    @Override // com.pushwoosh.e.a.a.a.i.b
    public void a(String str, byte[] bArr) {
        b(str);
    }

    @Override // com.pushwoosh.e.a.a.a.i.b
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.a.add(onSharedPreferenceChangeListener);
    }

    @Override // com.pushwoosh.e.a.a.a.i.b
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.a.remove(onSharedPreferenceChangeListener);
    }
}
