package com.pushwoosh.internal.preference;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.f;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class PreferenceArrayListValue<T extends Serializable> implements PreferenceValue {
    @Nullable
    private final SharedPreferences a;
    private final String b;
    private ArrayList<T> c;
    private final int d;

    @Deprecated
    public PreferenceArrayListValue(@Nullable SharedPreferences sharedPreferences, String str, int i) {
        this(sharedPreferences, str, i, null);
    }

    public PreferenceArrayListValue(@Nullable SharedPreferences sharedPreferences, String str, int i, Class<T> cls) {
        Object a2;
        ArrayList<T> arrayList;
        this.b = str;
        this.d = i;
        String str2 = null;
        if (sharedPreferences != null) {
            try {
                str2 = sharedPreferences.getString(str, null);
            } catch (Exception e) {
                PWLog.exception(e);
                this.c = new ArrayList<>();
            }
        }
        if (str2 == null) {
            arrayList = new ArrayList<>();
        } else {
            if (cls == null) {
                a2 = f.a(str2, ArrayList.class);
            } else {
                a2 = f.a(str2, ArrayList.class, cls);
            }
            arrayList = (ArrayList) a2;
        }
        this.c = arrayList;
        this.a = sharedPreferences;
    }

    private void a() throws IOException {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            PWLog.error("Incorrect state of the app preferences is null");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(this.b, f.a(this.c));
        edit.apply();
    }

    public void add(T t) {
        try {
            this.c.add(t);
            if (this.c.size() > this.d) {
                this.c.remove(0);
            }
            a();
        } catch (Exception e) {
            PWLog.exception(e);
        }
    }

    public void clear() {
        try {
            this.c.clear();
            a();
        } catch (Exception e) {
            PWLog.exception(e);
        }
    }

    public ArrayList<T> get() {
        return new ArrayList<>(this.c);
    }

    public void remove(T t) {
        try {
            this.c.remove(t);
            a();
        } catch (Exception e) {
            PWLog.exception(e);
        }
    }

    public void replaceAll(Collection<T> collection) {
        try {
            this.c.clear();
            this.c.addAll(collection);
            a();
        } catch (Exception e) {
            PWLog.exception(e);
        }
    }
}
