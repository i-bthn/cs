package com.pushwoosh.function;

import androidx.annotation.Nullable;
import com.pushwoosh.exception.PushwooshException;

public class Result<T, E extends PushwooshException> {
    private final T a;
    private final E b;

    private Result(T t, E e) {
        this.a = t;
        this.b = e;
    }

    public static <T, E extends PushwooshException> Result<T, E> from(T t, E e) {
        return new Result<>(t, e);
    }

    public static <T, E extends PushwooshException> Result<T, E> fromData(T t) {
        return new Result<>(t, null);
    }

    public static <T, E extends PushwooshException> Result<T, E> fromException(E e) {
        return new Result<>(null, e);
    }

    @Nullable
    public T getData() {
        return this.a;
    }

    @Nullable
    public E getException() {
        return this.b;
    }

    public boolean isSuccess() {
        return this.b == null;
    }
}
