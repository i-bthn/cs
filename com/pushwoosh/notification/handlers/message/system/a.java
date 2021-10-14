package com.pushwoosh.notification.handlers.message.system;

import android.os.Bundle;
import android.text.TextUtils;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.notification.b;
import com.pushwoosh.repository.s;
import java.util.ArrayList;
import org.json.JSONArray;

/* access modifiers changed from: package-private */
public class a extends c {
    a() {
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.notification.handlers.message.system.c
    public boolean a(Bundle bundle, String str) {
        if (!TextUtils.equals("getAttributes", str)) {
            return false;
        }
        String c = b.c(bundle);
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(c);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
        } catch (Exception unused) {
        }
        s sVar = new s(arrayList);
        RequestManager requestManager = NetworkModule.getRequestManager();
        if (requestManager == null) {
            return false;
        }
        requestManager.sendRequest(sVar);
        return true;
    }
}
