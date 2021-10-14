package com.pushwoosh.inapp.j.l;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.ImagesContract;
import com.pushwoosh.inapp.g.a;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements Serializable, Comparable<b> {
    private final String a;
    private final String b;
    private final String c;
    private final long d;
    private final a e;
    private final boolean f;
    private final int g;
    private final String h;
    private final String i;
    private Map<String, String> j;

    public b(String str) {
        this("", str, null, 0, a.FULLSCREEN, null, false, -1, null, null);
    }

    public b(String str, String str2, String str3, long j2, a aVar, Map<String, Object> map, boolean z, int i2, String str4, String str5) {
        if (str4 != null && !str4.isEmpty()) {
            z = true;
        }
        this.f = z;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = j2;
        this.e = aVar;
        this.j = c.a(map);
        this.g = i2;
        this.h = str4;
        this.i = str5;
    }

    public b(String str, boolean z) {
        this(str, null, null, 0, a.FULLSCREEN, null, z, -1, null, null);
    }

    public b(JSONObject jSONObject) throws JSONException {
        this(jSONObject.getString("code"), jSONObject.getString(ImagesContract.URL), jSONObject.optString("hash", ""), jSONObject.getLong("updated"), a.a(jSONObject.getString("layout")), Collections.emptyMap(), jSONObject.optBoolean("required", false), jSONObject.optInt("priority", 0), jSONObject.optString("businessCase", null), jSONObject.optString("gdpr"));
    }

    @NonNull
    public static b a(String str) throws a {
        return c.a(str);
    }

    /* renamed from: a */
    public int compareTo(@NonNull b bVar) {
        if ((!this.f || !bVar.f) && (this.f || bVar.f)) {
            return this.f ? -1 : 1;
        }
        int i2 = bVar.g - this.g;
        if (i2 != 0) {
            return i2;
        }
        String str = this.a;
        if (str == null) {
            return 1;
        }
        return str.compareTo(bVar.c());
    }

    public void a(Map<String, Object> map) {
        this.j = c.a(map);
    }

    public String b() {
        return this.h;
    }

    public String c() {
        return this.a;
    }

    public String d() {
        return this.i;
    }

    public String e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.d != bVar.d || this.f != bVar.f || this.g != bVar.g) {
            return false;
        }
        String str = this.a;
        if (str == null ? bVar.a != null : !str.equals(bVar.a)) {
            return false;
        }
        String str2 = this.b;
        if (str2 == null ? bVar.b != null : !str2.equals(bVar.b)) {
            return false;
        }
        String str3 = this.h;
        if (str3 == null ? bVar.h != null : !str3.equals(bVar.h)) {
            return false;
        }
        String str4 = this.i;
        if (str4 == null ? bVar.i == null : str4.equals(bVar.i)) {
            return this.e == bVar.e;
        }
        return false;
    }

    public a f() {
        return this.e;
    }

    public int g() {
        return this.g;
    }

    public Map<String, String> h() {
        return new HashMap(this.j);
    }

    public int hashCode() {
        String str = this.a;
        int i2 = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.i;
        int hashCode3 = str3 != null ? str3.hashCode() : 0;
        long j2 = this.d;
        int i3 = (((hashCode2 + hashCode3) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        a aVar = this.e;
        if (aVar != null) {
            i2 = aVar.hashCode();
        }
        return ((((i3 + i2) * 31) + (this.f ? 1 : 0)) * 31) + this.g;
    }

    public long i() {
        return this.d;
    }

    public String j() {
        return this.b;
    }

    public boolean k() {
        return this.a.length() > 0 && !this.a.startsWith("r-");
    }

    public boolean l() {
        return this.b == null;
    }

    public boolean m() {
        return this.f;
    }
}
