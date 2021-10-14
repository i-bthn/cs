package com.pushwoosh.internal.crash;

import android.content.Context;
import java.io.File;

/* access modifiers changed from: package-private */
public class g {
    static File a(Context context) {
        return context.getFilesDir();
    }

    static String a() {
        return ".stacktrace.pushwoosh";
    }
}
