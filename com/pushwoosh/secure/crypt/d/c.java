package com.pushwoosh.secure.crypt.d;

import android.content.Context;
import com.pushwoosh.Pushwoosh;

public final class c {
    public static b a(Context context, String str) {
        return new a(context, str, Pushwoosh.getInstance().getHwid());
    }
}
