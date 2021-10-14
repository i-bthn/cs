package com.pushwoosh.inapp.h;

import android.net.Uri;
import androidx.annotation.WorkerThread;
import com.pushwoosh.inapp.a;
import com.pushwoosh.inapp.k.c;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.d;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class b {
    private final c a;
    private final a b;

    public b(c cVar) {
        this.a = cVar;
        this.b = new a(cVar);
    }

    private String a(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        PWLog.noise("[InApp]ResourceMapper", "Key: \"" + str3 + "\", Type: \"" + str4 + "\", Default Value: \"" + str5 + "\"");
        if (map.containsKey(str3)) {
            str5 = a.a(map.get(str3), str4);
        }
        String replace = str.replace(str2, str5);
        PWLog.debug("[InApp]ResourceMapper", "Replacing \"" + str2 + "\" with \"" + str5 + "\"");
        return replace;
    }

    private String a(String str, Map<String, String> map) throws IOException {
        String d = d.d(this.a.c(str));
        try {
            d = a(a(a(d, Pattern.compile("\\{\\{(.[^\\}]+?)\\|(.[^\\}]+?)\\|(.[^\\}]*?)\\}\\}", 32), this.b.a(str)), Pattern.compile("\\{\\{(.[^\\}]+?)\\|(.[^\\}]+?)\\|(.[^\\}]*?)\\}\\}"), map), Pattern.compile("\\{\\{(.[^\\}]+?)\\|(.[^\\}]+?)\\}\\}"), this.b.a(str));
            return a(d, Pattern.compile("\\{(.[^\\}]+?)\\|(.[^\\}]+?)\\|(.[^\\}]*?)\\}"), map);
        } catch (Exception e) {
            PWLog.warn("[InApp]ResourceMapper", "Failed to process html: " + e.getMessage());
            return d;
        }
    }

    private String a(String str, Pattern pattern, Map<String, String> map) {
        String group;
        String group2;
        String group3;
        b bVar;
        String str2;
        Matcher matcher = pattern.matcher(str);
        String str3 = str;
        while (matcher.find()) {
            if (matcher.groupCount() == 3) {
                String group4 = matcher.group(0);
                str2 = matcher.group(1);
                group2 = matcher.group(2);
                group3 = matcher.group(3);
                bVar = this;
                group = group4;
            } else if (matcher.groupCount() == 2) {
                group = matcher.group(0);
                String group5 = matcher.group(1);
                group2 = matcher.group(2);
                group3 = matcher.group(1);
                bVar = this;
                str2 = group5;
            } else {
                PWLog.warn("[InApp]ResourceMapper", "Incorrect matching count");
            }
            str3 = bVar.a(str3, group, str2, group2, group3, map);
        }
        return str3;
    }

    @WorkerThread
    public com.pushwoosh.inapp.i.a a(com.pushwoosh.inapp.j.l.b bVar) throws IOException {
        return new com.pushwoosh.inapp.i.a(bVar.c(), Uri.fromFile(this.a.d(bVar.c())).toString(), a(bVar.c(), bVar.h()));
    }
}
