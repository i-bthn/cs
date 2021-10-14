package com.pushwoosh.e.a.a.a.i;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Process;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class a implements b {
    private final List<SharedPreferences.OnSharedPreferenceChangeListener> a;
    private final Handler b = new Handler();
    private final Context c;
    private final String d;
    private final com.pushwoosh.e.a.a.a.g.a.a e;
    private final com.pushwoosh.e.a.a.a.g.b.a f;
    private final com.pushwoosh.e.a.a.a.o.a g;
    private final com.pushwoosh.e.a.a.a.p.c h;
    private final com.pushwoosh.e.a.a.a.h.b i;
    private final String j;
    private final String k;
    private final int l;
    private final BroadcastReceiver m;
    private final BroadcastReceiver n;

    /* access modifiers changed from: package-private */
    /* renamed from: com.pushwoosh.e.a.a.a.i.a$a  reason: collision with other inner class name */
    public class C0006a extends BroadcastReceiver {
        C0006a() {
        }

        public void onReceive(Context context, Intent intent) {
            a.this.a((a) intent);
        }
    }

    /* access modifiers changed from: package-private */
    public class b extends BroadcastReceiver {
        b() {
        }

        public void onReceive(Context context, Intent intent) {
            a.this.d((a) intent);
        }
    }

    /* access modifiers changed from: package-private */
    public class c implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ byte[] b;

        c(String str, byte[] bArr) {
            this.a = str;
            this.b = bArr;
        }

        public void run() {
            a.this.c((a) this.a, (String) this.b);
        }
    }

    /* access modifiers changed from: package-private */
    public class d implements Runnable {
        final /* synthetic */ Intent a;

        d(Intent intent) {
            this.a = intent;
        }

        public void run() {
            a.this.b((a) this.a);
        }
    }

    /* access modifiers changed from: package-private */
    public class e implements Runnable {
        final /* synthetic */ String a;

        e(String str) {
            this.a = str;
        }

        public void run() {
            a.this.c((a) this.a);
        }
    }

    /* access modifiers changed from: package-private */
    public class f implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ byte[] b;

        f(String str, byte[] bArr) {
            this.a = str;
            this.b = bArr;
        }

        public void run() {
            a.this.f(this.a, this.b);
        }
    }

    /* access modifiers changed from: package-private */
    public class g implements Runnable {
        final /* synthetic */ String a;

        g(String str) {
            this.a = str;
        }

        public void run() {
            a.this.f(this.a);
        }
    }

    public a(Context context, String str, com.pushwoosh.e.a.a.a.g.a.a aVar, com.pushwoosh.e.a.a.a.g.b.a aVar2, com.pushwoosh.e.a.a.a.o.a aVar3, com.pushwoosh.e.a.a.a.p.c cVar, com.pushwoosh.e.a.a.a.h.b bVar, com.pushwoosh.e.a.a.a.k.b.b bVar2, Map<String, List<SharedPreferences.OnSharedPreferenceChangeListener>> map) {
        this.c = context;
        this.d = str;
        this.e = aVar;
        this.f = aVar2;
        this.g = aVar3;
        this.h = cVar;
        this.i = bVar;
        this.j = b(bVar2);
        this.k = a(bVar2);
        this.a = a(str, map);
        this.m = b();
        this.n = a();
        this.l = Process.myPid();
    }

    private BroadcastReceiver a() {
        return new C0006a();
    }

    private String a(com.pushwoosh.e.a.a.a.k.b.b bVar) {
        return "com.ironz.binaryprefs.ACTION_PREFERENCE_REMOVED_" + bVar.c().getAbsolutePath();
    }

    private List<SharedPreferences.OnSharedPreferenceChangeListener> a(String str, Map<String, List<SharedPreferences.OnSharedPreferenceChangeListener>> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        ArrayList arrayList = new ArrayList();
        map.put(str, arrayList);
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Intent intent) {
        if (this.d.equals(intent.getStringExtra("preference_name")) && this.l != intent.getIntExtra("preference_process_id", 0)) {
            c(intent);
        }
    }

    private void a(String str, Object obj) {
        this.e.a(str);
        this.f.a(str, obj);
        b(str);
    }

    private BroadcastReceiver b() {
        return new b();
    }

    private Object b(String str, byte[] bArr) {
        return this.g.a(str, this.i.b(bArr));
    }

    private String b(com.pushwoosh.e.a.a.a.k.b.b bVar) {
        return "com.ironz.binaryprefs.ACTION_PREFERENCE_UPDATED_" + bVar.c().getAbsolutePath();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(Intent intent) {
        d(intent.getStringExtra("preference_key"));
    }

    private void b(String str) {
        this.b.post(new e(str));
    }

    private void c() {
        this.c.registerReceiver(this.m, new IntentFilter(this.j));
        this.c.registerReceiver(this.n, new IntentFilter(this.k));
    }

    private void c(Intent intent) {
        this.h.a(new d(intent));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c(String str) {
        for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : this.a) {
            onSharedPreferenceChangeListener.onSharedPreferenceChanged(null, str);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c(String str, byte[] bArr) {
        a(str, b(str, bArr));
    }

    private void d() {
        this.c.unregisterReceiver(this.m);
        this.c.unregisterReceiver(this.n);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d(Intent intent) {
        if (this.d.equals(intent.getStringExtra("preference_name")) && this.l != intent.getIntExtra("preference_process_id", 0)) {
            d(intent.getStringExtra("preference_key"), intent.getByteArrayExtra("preference_value"));
        }
    }

    private void d(String str) {
        this.e.remove(str);
        this.f.remove(str);
        b(str);
    }

    private void d(String str, byte[] bArr) {
        this.h.a(new c(str, bArr));
    }

    private void e(String str) {
        this.h.a(new g(str));
    }

    private void e(String str, byte[] bArr) {
        this.h.a(new f(str, bArr));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void f(String str) {
        Intent intent = new Intent(this.k);
        intent.putExtra("preference_process_id", this.l);
        intent.putExtra("preference_name", this.d);
        intent.putExtra("preference_key", str);
        this.c.sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void f(String str, byte[] bArr) {
        Intent intent = new Intent(this.j);
        intent.putExtra("preference_process_id", this.l);
        intent.putExtra("preference_name", this.d);
        intent.putExtra("preference_key", str);
        intent.putExtra("preference_value", bArr);
        this.c.sendBroadcast(intent);
    }

    @Override // com.pushwoosh.e.a.a.a.i.b
    public void a(String str) {
        b(str);
        e(str);
    }

    @Override // com.pushwoosh.e.a.a.a.i.b
    public void a(String str, byte[] bArr) {
        b(str);
        e(str, bArr);
    }

    @Override // com.pushwoosh.e.a.a.a.i.b
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        if (this.a.isEmpty()) {
            c();
        }
        this.a.add(onSharedPreferenceChangeListener);
    }

    @Override // com.pushwoosh.e.a.a.a.i.b
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.a.remove(onSharedPreferenceChangeListener);
        if (this.a.isEmpty()) {
            d();
        }
    }
}
