package com.pushwoosh.inapp.j.k;

import androidx.core.util.Pair;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.d;
import java.io.File;

/* access modifiers changed from: package-private */
public class b implements com.pushwoosh.internal.checker.b<Pair<File, com.pushwoosh.inapp.j.l.b>> {
    b() {
    }

    public boolean a(Pair<File, com.pushwoosh.inapp.j.l.b> pair) {
        F f = pair.first;
        S s = pair.second;
        if (f == null || s == null) {
            PWLog.noise("[InApp]FileHashChecker", "incorrect state of arguments");
            return false;
        }
        String e = s.e();
        if (e == null || e.isEmpty()) {
            PWLog.noise("[InApp]FileHashChecker", "Hash is empty for " + s.j());
            return true;
        }
        String b = d.b((File) f);
        PWLog.noise("[InApp]FileHashChecker", "Resource hash " + e + ", file hash " + b);
        return e.equals(b);
    }
}
