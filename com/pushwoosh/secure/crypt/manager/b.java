package com.pushwoosh.secure.crypt.manager;

import android.content.Context;
import android.text.TextUtils;
import com.pddstudio.preferences.encrypted.EncryptedPreferences;
import java.util.Arrays;
import java.util.List;

public class b {
    private EncryptedPreferences a;

    public interface a {
        void a(String str, List<String> list, String str2);
    }

    b(Context context, String str) {
        this.a = new EncryptedPreferences.Builder(context).withPreferenceName("PWEndpointStorage").withEncryptionPassword(str).build();
    }

    /* access modifiers changed from: package-private */
    public void a(a aVar) {
        String string = this.a.getString("BASE_URL_KEY", null);
        List<String> asList = Arrays.asList(TextUtils.split(this.a.getString("PINS_KEY", ""), ", "));
        String string2 = this.a.getString("HOST_KEY", null);
        if (aVar != null) {
            aVar.a(string, asList, string2);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, List<String> list, String str2) {
        EncryptedPreferences.EncryptedEditor edit = this.a.edit();
        if (str != null) {
            edit.putString("BASE_URL_KEY", str);
        } else {
            edit.remove("BASE_URL_KEY");
        }
        if (list == null || list.size() <= 0) {
            edit.remove("PINS_KEY");
        } else {
            edit.putString("PINS_KEY", TextUtils.join(", ", list));
        }
        if (str2 != null) {
            edit.putString("HOST_KEY", str2);
        }
        edit.commit();
    }
}
