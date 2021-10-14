package com.pushwoosh.inapp.k;

import android.content.Context;
import androidx.annotation.Nullable;
import java.io.File;

public class a implements c {
    @Nullable
    private final Context a;

    public a(@Nullable Context context) {
        this.a = context;
    }

    @Override // com.pushwoosh.inapp.k.c
    public File a() {
        Context context = this.a;
        if (context == null) {
            return null;
        }
        return context.getCacheDir();
    }

    @Override // com.pushwoosh.inapp.k.c
    public File a(String str) {
        File d = d(str);
        if (d == null) {
            return null;
        }
        return new File(d, "pushwoosh.json");
    }

    @Override // com.pushwoosh.inapp.k.c
    public boolean b(String str) {
        File d = d(str);
        return d != null && d.exists();
    }

    @Override // com.pushwoosh.inapp.k.c
    public File c(String str) {
        File d = d(str);
        if (d == null) {
            return null;
        }
        return new File(d, "index.html");
    }

    @Override // com.pushwoosh.inapp.k.c
    public File d(String str) {
        Context context = this.a;
        if (context == null) {
            return null;
        }
        return new File(context.getDir("htmls", 0), str);
    }
}
