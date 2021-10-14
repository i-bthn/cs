package com.pushwoosh.internal.utils.j;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* access modifiers changed from: package-private */
public final class b implements a {
    private final Set<String> a;

    public b(String... strArr) {
        this.a = Collections.unmodifiableSet(new HashSet(Arrays.asList(strArr)));
    }

    @Override // com.pushwoosh.internal.utils.j.a
    public boolean a(String str) {
        return this.a.contains(str);
    }
}
