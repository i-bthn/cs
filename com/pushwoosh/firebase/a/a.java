package com.pushwoosh.firebase.a;

import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

public class a {
    public static boolean a(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        return data != null && data.containsKey("pw_msg");
    }
}
