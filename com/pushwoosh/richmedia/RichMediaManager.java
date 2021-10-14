package com.pushwoosh.richmedia;

import com.pushwoosh.PushwooshPlatform;

public class RichMediaManager {
    public static RichMediaStyle getRichMediaStyle() {
        a h = PushwooshPlatform.getInstance().h();
        if (h != null) {
            return h.a();
        }
        return null;
    }

    public static void present(RichMedia richMedia) {
        a h = PushwooshPlatform.getInstance().h();
        if (h != null) {
            h.a(richMedia);
        }
    }

    public static void setDelegate(RichMediaPresentingDelegate richMediaPresentingDelegate) {
        a h = PushwooshPlatform.getInstance().h();
        if (h != null) {
            h.a(richMediaPresentingDelegate);
        }
    }
}
