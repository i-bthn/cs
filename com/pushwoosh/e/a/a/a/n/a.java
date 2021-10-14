package com.pushwoosh.e.a.a.a.n;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.pushwoosh.e.a.a.a.e;
import com.pushwoosh.e.a.a.a.f;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class a {
    private final List<SharedPreferences> a = new ArrayList();

    @SuppressLint({"ApplySharedPref"})
    private void a(SharedPreferences sharedPreferences, e eVar) {
        Map<String, ?> all = sharedPreferences.getAll();
        if (!all.isEmpty()) {
            f edit = eVar.edit();
            for (String str : all.keySet()) {
                a(all, edit, str);
            }
            if (edit.commit()) {
                sharedPreferences.edit().clear().commit();
            }
        }
    }

    private void a(Map<String, ?> map, f fVar, String str) {
        Object obj = map.get(str);
        if (obj instanceof String) {
            fVar.putString(str, (String) obj);
        }
        if (obj instanceof Set) {
            fVar.putStringSet(str, (Set) obj);
        }
        if (obj instanceof Integer) {
            fVar.putInt(str, ((Integer) obj).intValue());
        }
        if (obj instanceof Long) {
            fVar.putLong(str, ((Long) obj).longValue());
        }
        if (obj instanceof Float) {
            fVar.putFloat(str, ((Float) obj).floatValue());
        }
        if (obj instanceof Boolean) {
            fVar.putBoolean(str, ((Boolean) obj).booleanValue());
        }
    }

    public void a(e eVar) {
        for (SharedPreferences sharedPreferences : this.a) {
            a(sharedPreferences, eVar);
        }
    }
}
