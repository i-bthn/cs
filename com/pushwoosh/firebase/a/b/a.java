package com.pushwoosh.firebase.a.b;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

public class a {
    @NonNull
    public static Bundle a(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        Bundle bundle = new Bundle();
        if (data == null) {
            return bundle;
        }
        for (Map.Entry<String, String> entry : data.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        return bundle;
    }
}
