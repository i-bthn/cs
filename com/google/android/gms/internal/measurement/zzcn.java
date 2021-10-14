package com.google.android.gms.internal.measurement;

import android.net.Uri;

public final class zzcn {
    public static Uri zzdh(String str) {
        String valueOf = String.valueOf(Uri.encode(str));
        return Uri.parse(valueOf.length() != 0 ? "content://com.google.android.gms.phenotype/".concat(valueOf) : new String("content://com.google.android.gms.phenotype/"));
    }
}
