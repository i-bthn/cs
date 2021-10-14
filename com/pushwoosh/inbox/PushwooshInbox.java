package com.pushwoosh.inbox;

import androidx.annotation.Nullable;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inbox.b.b;
import com.pushwoosh.inbox.b.c.c;
import com.pushwoosh.inbox.b.d.a;
import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.exception.InboxMessagesException;
import com.pushwoosh.inbox.internal.data.InboxMessageStatus;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PushwooshInbox {
    /* access modifiers changed from: private */
    public static /* synthetic */ void a(a aVar, Result result) {
        if (result.isSuccess()) {
            c.a(aVar.a((InboxMessage) result.getData()));
        }
    }

    private static void changeStatus(Collection<String> collection, InboxMessageStatus inboxMessageStatus) {
        HashMap hashMap = new HashMap(collection.size());
        for (String str : collection) {
            hashMap.put(str, inboxMessageStatus);
        }
        b.a().a((Map<String, InboxMessageStatus>) hashMap, false, (Callback<InboxMessage, InboxMessagesException>) null);
    }

    public static void deleteMessage(String str) {
        deleteMessages(Collections.singleton(str));
    }

    public static void deleteMessages(Collection<String> collection) {
        changeStatus(collection, InboxMessageStatus.DELETED_BY_USER);
    }

    public static Collection<InboxMessage> loadCachedMessages(@Nullable InboxMessage inboxMessage, int i) throws InboxMessagesException {
        return b.a().a(inboxMessage, i);
    }

    public static void loadCachedMessages(Callback<Collection<InboxMessage>, InboxMessagesException> callback, @Nullable InboxMessage inboxMessage, int i) throws InboxMessagesException {
        b.a().a(callback, inboxMessage, i);
    }

    public static void loadMessages(Callback<Collection<InboxMessage>, InboxMessagesException> callback) {
        loadMessages(callback, null, -1);
    }

    public static void loadMessages(Callback<Collection<InboxMessage>, InboxMessagesException> callback, @Nullable InboxMessage inboxMessage, int i) {
        b.a().b(callback, inboxMessage, i);
    }

    public static void messagesCount(Callback<Integer, InboxMessagesException> callback) {
        b.a().e(callback);
    }

    public static void messagesWithNoActionPerformedCount(Callback<Integer, InboxMessagesException> callback) {
        b.a().d(callback);
    }

    public static void performAction(String str) {
        b.a().a(Collections.singletonMap(str, InboxMessageStatus.OPEN), true, (Callback<InboxMessage, InboxMessagesException>) new Callback() {
            /* class com.pushwoosh.inbox.$$Lambda$PushwooshInbox$NoOf1Pc1oynH02no6pLyjsEeYI */

            @Override // com.pushwoosh.function.Callback
            public final void process(Result result) {
                PushwooshInbox.m14lambda$NoOf1Pc1oynH02no6pLyjsEeYI(a.this, result);
            }
        });
    }

    public static void readMessage(String str) {
        readMessages(Collections.singleton(str));
    }

    public static void readMessages(Collection<String> collection) {
        changeStatus(collection, InboxMessageStatus.READ);
    }

    public static void registerMessagesCountObserver(Callback<Integer, InboxMessagesException> callback) {
        b.a().a(callback);
    }

    public static void registerMessagesWithNoActionPerformedCountObserver(Callback<Integer, InboxMessagesException> callback) {
        b.a().b(callback);
    }

    public static void registerUnreadMessagesCountObserver(Callback<Integer, InboxMessagesException> callback) {
        b.a().c(callback);
    }

    public static void unreadMessagesCount(Callback<Integer, InboxMessagesException> callback) {
        b.a().f(callback);
    }

    public static void unregisterMessagesCountObserver(Callback<Integer, InboxMessagesException> callback) {
        b.a().g(callback);
    }

    public static void unregisterMessagesWithNoActionPerformedCountObserver(Callback<Integer, InboxMessagesException> callback) {
        b.a().h(callback);
    }

    public static void unregisterUnreadMessagesCountObserver(Callback<Integer, InboxMessagesException> callback) {
        b.a().i(callback);
    }
}
