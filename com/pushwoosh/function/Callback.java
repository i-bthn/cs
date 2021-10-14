package com.pushwoosh.function;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.pushwoosh.exception.PushwooshException;

public interface Callback<T, E extends PushwooshException> {
    @MainThread
    void process(@NonNull Result<T, E> result);
}
