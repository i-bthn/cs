package com.pushwoosh.secure.c.a;

import android.os.Bundle;
import androidx.annotation.Nullable;

public final class b {
    @Nullable
    public static a a(Bundle bundle) {
        if (b(bundle)) {
            return new c();
        }
        return null;
    }

    private static boolean b(Bundle bundle) {
        return bundle.containsKey("pw_encrypted");
    }
}
