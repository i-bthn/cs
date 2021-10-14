package com.pushwoosh.internal.checker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a {
    private static final a b = new a();
    private List<Checker> a = new ArrayList();

    public static a b() {
        return b;
    }

    public boolean a() {
        Iterator<Checker> it = this.a.iterator();
        while (true) {
            boolean z = true;
            while (true) {
                if (!it.hasNext()) {
                    return z;
                }
                Checker next = it.next();
                if (!z || !next.check()) {
                    z = false;
                }
            }
        }
    }
}
