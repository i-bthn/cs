package com.pushwoosh.secure.crypt.a;

import androidx.annotation.NonNull;
import java.security.Key;

public interface a {
    @NonNull
    String a(Key key, String str) throws com.pushwoosh.secure.crypt.b.a;

    @NonNull
    byte[] b(Key key, String str) throws com.pushwoosh.secure.crypt.b.a;
}
