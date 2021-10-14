package com.pushwoosh.secure;

import android.content.Context;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.handlers.message.system.MessageSystemHandleChainProvider;
import com.pushwoosh.notification.handlers.message.system.MessageSystemHandler;
import com.pushwoosh.secure.c.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PushwooshSecure {
    private static void a() {
        Iterator<MessageSystemHandler> iterator = MessageSystemHandleChainProvider.getMessageSystemChain().getIterator();
        ArrayList arrayList = new ArrayList();
        while (iterator.hasNext()) {
            MessageSystemHandler next = iterator.next();
            if (next instanceof a) {
                arrayList.add(next);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            MessageSystemHandleChainProvider.getMessageSystemChain().removeItem((MessageSystemHandler) it.next());
        }
    }

    static void a(a aVar) {
        a();
        MessageSystemHandleChainProvider.getMessageSystemChain().addItem(aVar);
    }

    public static void setBaseURL(String str, List<String> list, String str2) {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext == null) {
            PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
        } else {
            com.pushwoosh.secure.crypt.manager.a.a(applicationContext, Pushwoosh.getInstance().getHwid(), str, list, str2);
        }
    }

    public static void setupDecryption(Callback<Void, PushwooshException> callback) {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext == null) {
            PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
        } else if (com.pushwoosh.secure.crypt.manager.a.a()) {
            a(new a(new com.pushwoosh.secure.crypt.manager.a(applicationContext, 3072, callback)));
        }
    }

    public static void teardownDecryption(Callback<Void, PushwooshException> callback) {
        if (AndroidPlatformModule.getApplicationContext() == null) {
            PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            return;
        }
        Iterator<MessageSystemHandler> iterator = MessageSystemHandleChainProvider.getMessageSystemChain().getIterator();
        boolean z = false;
        while (true) {
            Callback<Void, PushwooshException> callback2 = null;
            if (!iterator.hasNext()) {
                break;
            }
            MessageSystemHandler next = iterator.next();
            if (next instanceof a) {
                a aVar = (a) next;
                if (!z) {
                    callback2 = callback;
                }
                aVar.a(callback2);
                z = true;
            }
        }
        a();
        if (callback != null && !z) {
            callback.process(Result.fromData(null));
        }
    }
}
