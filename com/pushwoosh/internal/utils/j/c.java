package com.pushwoosh.internal.utils.j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class c extends ObjectInputStream {
    private final List<a> a = new ArrayList();
    private final List<a> b = new ArrayList();

    public c(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    private void b(String str) throws InvalidClassException {
        for (a aVar : this.b) {
            if (aVar.a(str)) {
                a(str);
                throw null;
            }
        }
        boolean z = false;
        Iterator<a> it = this.a.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().a(str)) {
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (!z) {
            a(str);
            throw null;
        }
    }

    public c a(Class<?>... clsArr) {
        for (Class<?> cls : clsArr) {
            this.a.add(new b(cls.getName()));
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void a(String str) throws InvalidClassException {
        throw new InvalidClassException("Class name not accepted: " + str);
    }

    /* access modifiers changed from: protected */
    @Override // java.io.ObjectInputStream
    public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        b(objectStreamClass.getName());
        return super.resolveClass(objectStreamClass);
    }
}
