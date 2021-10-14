package com.pushwoosh.inapp.j;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.pushwoosh.inapp.j.l.b;
import com.pushwoosh.inapp.k.d;
import org.json.JSONException;
import org.json.JSONObject;

public class f {
    private final String a;
    private final b b;
    private final boolean c;

    @WorkerThread
    f(JSONObject jSONObject) throws JSONException {
        d d;
        this.a = jSONObject.optString("code");
        this.c = jSONObject.optBoolean("required", false);
        this.b = (this.a == null || (d = com.pushwoosh.inapp.b.d()) == null) ? null : d.a(this.a);
    }

    public String a() {
        return this.a;
    }

    @Nullable
    public b b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }
}
