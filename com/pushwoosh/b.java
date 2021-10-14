package com.pushwoosh;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.preference.PreferenceArrayListValue;
import com.pushwoosh.internal.preference.PreferenceIntValue;
import com.pushwoosh.internal.preference.PreferenceValueFactory;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.g;
import com.pushwoosh.repository.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends AsyncTask<Void, Void, Void> {
    private final SharedPreferences a = AndroidPlatformModule.getPrefsProvider().provideDefault();
    private final c b;
    private final PreferenceValueFactory c;
    private final PreferenceArrayListValue<String> d;
    private final PreferenceIntValue e;

    public b(@NonNull Context context) {
        this.b = new c(context);
        this.c = new PreferenceValueFactory();
        this.d = this.c.buildPreferenceArrayListValue(this.a, "pushwoosh_showed_local_notificaions", 10);
        this.e = this.c.buildPreferenceIntValue(this.a, "pushwoosh_local_push_request_id", 0);
    }

    private void a() {
        if (!e()) {
            f();
            g();
        }
    }

    private List<com.pushwoosh.repository.b> b() {
        Set<String> d2 = d();
        ArrayList arrayList = new ArrayList();
        for (String str : d2) {
            SharedPreferences sharedPreferences = this.a;
            long j = 0;
            if (sharedPreferences != null) {
                j = sharedPreferences.getLong("pushwoosh_local_push_trigger_at_millis_" + str, 0);
            }
            SharedPreferences sharedPreferences2 = this.a;
            arrayList.add(new com.pushwoosh.repository.b(Integer.parseInt(str), j, g.a(sharedPreferences2, "pushwoosh_local_push_bundle_" + str)));
        }
        this.a.edit().putStringSet("pushwoosh_local_push_ids", new HashSet()).apply();
        return arrayList;
    }

    private List<com.pushwoosh.repository.b> c() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.d.get().iterator();
        while (it.hasNext()) {
            try {
                JSONObject jSONObject = new JSONObject(it.next());
                arrayList.add(new com.pushwoosh.repository.b(jSONObject.getInt("requestId"), jSONObject.getInt("notificationId"), jSONObject.getString("notificationTag")));
            } catch (JSONException e2) {
                PWLog.exception(e2);
            }
        }
        this.d.clear();
        return arrayList;
    }

    private Set<String> d() {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            return Collections.emptySet();
        }
        try {
            return new HashSet(sharedPreferences.getStringSet("pushwoosh_local_push_ids", new HashSet()));
        } catch (ClassCastException unused) {
            return new HashSet();
        }
    }

    private boolean e() {
        return d().isEmpty() && this.d.get().isEmpty();
    }

    private void f() {
        for (com.pushwoosh.repository.b bVar : b()) {
            this.b.b(bVar);
        }
    }

    private void g() {
        for (com.pushwoosh.repository.b bVar : c()) {
            this.b.a(bVar);
        }
    }

    private void h() {
        if (this.e.get() != 0) {
            this.b.c(this.e.get());
            this.e.set(0);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Void doInBackground(Void... voidArr) {
        h();
        a();
        return null;
    }
}
