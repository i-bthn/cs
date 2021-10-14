package com.google.zxing.client.android.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

public final class ClipboardInterface {
    private static final String TAG = "ClipboardInterface";

    private ClipboardInterface() {
    }

    public static CharSequence getText(Context context) {
        ClipData primaryClip = getManager(context).getPrimaryClip();
        if (hasText(context)) {
            return primaryClip.getItemAt(0).coerceToText(context);
        }
        return null;
    }

    public static void setText(CharSequence charSequence, Context context) {
        if (charSequence != null) {
            try {
                getManager(context).setPrimaryClip(ClipData.newPlainText(null, charSequence));
            } catch (IllegalStateException | NullPointerException e) {
                Log.w(TAG, "Clipboard bug", e);
            }
        }
    }

    public static boolean hasText(Context context) {
        ClipData primaryClip = getManager(context).getPrimaryClip();
        return primaryClip != null && primaryClip.getItemCount() > 0;
    }

    private static ClipboardManager getManager(Context context) {
        return (ClipboardManager) context.getSystemService("clipboard");
    }
}
