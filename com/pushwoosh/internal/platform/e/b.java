package com.pushwoosh.internal.platform.e;

import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import androidx.annotation.Nullable;

public interface b {
    float a(int i);

    int a(String str, String str2);

    @Nullable
    Configuration a();

    void a(int i, TypedValue typedValue, boolean z);

    @Nullable
    DisplayMetrics b();
}
