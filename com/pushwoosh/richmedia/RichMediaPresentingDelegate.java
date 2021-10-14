package com.pushwoosh.richmedia;

import com.pushwoosh.exception.PushwooshException;

public interface RichMediaPresentingDelegate {
    void onClose(RichMedia richMedia);

    void onError(RichMedia richMedia, PushwooshException pushwooshException);

    void onPresent(RichMedia richMedia);

    boolean shouldPresent(RichMedia richMedia);
}
