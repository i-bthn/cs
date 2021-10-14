package com.pushwoosh.notification.handlers.message.user;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.chain.Chain;
import com.pushwoosh.notification.handlers.message.user.a;

public class MessageHandleChainProvider {
    private static final MessageHandleChainProvider b = new MessageHandleChainProvider();
    private Chain<MessageHandler> a;

    private MessageHandleChainProvider() {
    }

    @NonNull
    private static Chain<MessageHandler> a() {
        return new a.b().a(new b()).a(new c()).a();
    }

    @NonNull
    public static Chain<MessageHandler> getHandleProcessor() {
        return b.a;
    }

    public static void init() {
        b.a = a();
    }
}
