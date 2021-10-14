package com.pushwoosh.internal.platform.e;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;

public class a implements b {
    private final WeakReference<Context> a;

    public a(@Nullable Context context) {
        this.a = new WeakReference<>(context);
    }

    @Nullable
    private Context c() {
        return this.a.get();
    }

    @Override // com.pushwoosh.internal.platform.e.b
    public float a(int i) {
        if (c() == null) {
            return -1.0f;
        }
        return c().getResources().getDimension(i);
    }

    @Override // com.pushwoosh.internal.platform.e.b
    public int a(String str, String str2) {
        if (c() == null) {
            return -1;
        }
        return c().getResources().getIdentifier(str, str2, c().getPackageName());
    }

    @Override // com.pushwoosh.internal.platform.e.b
    public Configuration a() {
        if (c() == null) {
            return null;
        }
        return c().getResources().getConfiguration();
    }

    @Override // com.pushwoosh.internal.platform.e.b
    public void a(int i, TypedValue typedValue, boolean z) {
        if (c() != null) {
            c().getResources().getValue(i, typedValue, z);
        }
    }

    @Override // com.pushwoosh.internal.platform.e.b
    public DisplayMetrics b() {
        if (c() == null) {
            return null;
        }
        return c().getResources().getDisplayMetrics();
    }
}
