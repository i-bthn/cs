package com.pushwoosh.inbox.f.c;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.inbox.internal.data.InboxMessageStatus;
import com.pushwoosh.inbox.internal.data.b;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class a implements com.pushwoosh.inbox.f.a {
    private final b a;

    public a(b bVar) {
        this.a = bVar;
    }

    @Override // com.pushwoosh.inbox.f.a
    public int a() {
        Integer a2 = this.a.a(InboxMessageStatus.OPEN.getLowerStatus());
        if (a2 == null) {
            return 0;
        }
        return a2.intValue();
    }

    @Override // com.pushwoosh.inbox.f.a
    @NonNull
    public com.pushwoosh.inbox.f.b.a a(@NonNull Collection<b> collection, boolean z) {
        return (!collection.isEmpty() || z) ? this.a.a(collection, z) : com.pushwoosh.inbox.f.b.a.f();
    }

    @Override // com.pushwoosh.inbox.f.a
    @Nullable
    public b a(String str) {
        return this.a.a(str);
    }

    @Override // com.pushwoosh.inbox.f.a
    @NonNull
    public Collection<b> a(long j, int i) {
        Collection<b> a2 = this.a.a(InboxMessageStatus.getActualCodes(), j, i);
        return a2 == null ? Collections.emptyList() : a2;
    }

    @Override // com.pushwoosh.inbox.f.a
    public Collection<String> a(@NonNull String str, @NonNull InboxMessageStatus inboxMessageStatus) {
        return this.a.a(Collections.singleton(str), inboxMessageStatus);
    }

    @Override // com.pushwoosh.inbox.f.a
    @NonNull
    public Collection<b> a(Collection<String> collection) {
        List<b> b = this.a.b(collection);
        return b == null ? Collections.emptyList() : b;
    }

    @Override // com.pushwoosh.inbox.f.a
    @NonNull
    public Collection<b> b() {
        Collection<b> b = this.a.b();
        return b == null ? Collections.emptyList() : b;
    }

    @Override // com.pushwoosh.inbox.f.a
    public void b(@NonNull Collection<String> collection) {
        if (!collection.isEmpty()) {
            this.a.c(collection);
        }
    }

    @Override // com.pushwoosh.inbox.f.a
    public int c() {
        Integer a2 = this.a.a(InboxMessageStatus.READ.getLowerStatus());
        if (a2 == null) {
            return 0;
        }
        return a2.intValue();
    }

    @Override // com.pushwoosh.inbox.f.a
    public int d() {
        Integer a2 = this.a.a(InboxMessageStatus.DELETED_BY_USER.getLowerStatus());
        if (a2 == null) {
            return 0;
        }
        return a2.intValue();
    }
}
