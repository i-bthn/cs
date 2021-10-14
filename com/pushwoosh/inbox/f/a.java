package com.pushwoosh.inbox.f;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.pushwoosh.inbox.internal.data.InboxMessageStatus;
import com.pushwoosh.inbox.internal.data.b;
import java.util.Collection;

public interface a {
    @WorkerThread
    int a();

    @NonNull
    @WorkerThread
    com.pushwoosh.inbox.f.b.a a(@NonNull Collection<b> collection, boolean z);

    @Nullable
    @WorkerThread
    b a(String str);

    @NonNull
    @WorkerThread
    Collection<b> a(long j, int i);

    @WorkerThread
    Collection<String> a(@NonNull String str, @NonNull InboxMessageStatus inboxMessageStatus);

    @NonNull
    @WorkerThread
    Collection<b> a(Collection<String> collection);

    @NonNull
    @WorkerThread
    Collection<b> b();

    @WorkerThread
    void b(@NonNull Collection<String> collection);

    @WorkerThread
    int c();

    @WorkerThread
    int d();
}
