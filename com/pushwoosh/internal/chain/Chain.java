package com.pushwoosh.internal.chain;

import java.util.Iterator;

public interface Chain<T> {
    void addItem(T t);

    Iterator<T> getIterator();

    void removeItem(T t);
}
