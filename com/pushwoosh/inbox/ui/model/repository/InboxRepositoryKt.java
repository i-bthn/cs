package com.pushwoosh.inbox.ui.model.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u001a0\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"remove", "", "T", "", "filter", "Lkotlin/Function1;", "", "pushwoosh-inbox-ui_release"}, k = 2, mv = {1, 1, 15})
/* compiled from: InboxRepository.kt */
public final class InboxRepositoryKt {
    @NotNull
    public static final <T> Collection<T> remove(@NotNull Collection<T> collection, @NotNull Function1<? super T, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(collection, "$this$remove");
        Intrinsics.checkParameterIsNotNull(function1, "filter");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            T next = it.next();
            if (function1.invoke(next).booleanValue()) {
                it.remove();
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
