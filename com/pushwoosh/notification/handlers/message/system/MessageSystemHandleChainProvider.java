package com.pushwoosh.notification.handlers.message.system;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.chain.Chain;
import com.pushwoosh.notification.handlers.message.system.e;

public class MessageSystemHandleChainProvider {
    private static final MessageSystemHandleChainProvider b = new MessageSystemHandleChainProvider();
    private Chain<MessageSystemHandler> a;

    private MessageSystemHandleChainProvider() {
    }

    @NonNull
    private static Chain<MessageSystemHandler> a() {
        return new e.b().a(new f()).a(new a()).a(new d()).a(new b()).a();
    }

    @NonNull
    public static Chain<MessageSystemHandler> getMessageSystemChain() {
        return b.a;
    }

    public static void init() {
        b.a = a();
    }
}
