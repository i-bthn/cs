package com.pushwoosh.e.a.a.a;

import android.content.SharedPreferences;
import java.util.Set;

public interface f extends SharedPreferences.Editor {
    f putBoolean(String str, boolean z);

    f putFloat(String str, float f);

    f putInt(String str, int i);

    f putLong(String str, long j);

    f putString(String str, String str2);

    @Override // android.content.SharedPreferences.Editor
    f putStringSet(String str, Set<String> set);
}
