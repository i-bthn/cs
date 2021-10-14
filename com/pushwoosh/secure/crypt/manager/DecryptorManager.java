package com.pushwoosh.secure.crypt.manager;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;

public interface DecryptorManager {
    @Nullable
    @WorkerThread
    String decrypt(String str);

    @Nullable
    @WorkerThread
    byte[] decryptBytes(String str);

    void removePublicKey(@Nullable Callback<Void, PushwooshException> callback);
}
