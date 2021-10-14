package com.pushwoosh.notification.builder;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.Nullable;

public interface b {
    Notification a();

    b a(int i);

    b a(int i, int i2, int i3);

    b a(int i, CharSequence charSequence, PendingIntent pendingIntent);

    b a(long j);

    b a(Bitmap bitmap);

    b a(@Nullable Bitmap bitmap, CharSequence charSequence);

    b a(Bundle bundle);

    b a(CharSequence charSequence);

    b a(Integer num);

    b b(int i);

    b b(CharSequence charSequence);

    b c(int i);

    b c(CharSequence charSequence);
}
