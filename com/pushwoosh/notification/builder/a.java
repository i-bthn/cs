package com.pushwoosh.notification.builder;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.pushwoosh.internal.utils.PWLog;

@RequiresApi(23)
class a {
    static int a(Context context) {
        return context.getApplicationInfo().icon;
    }

    @Nullable
    static Icon a(@Nullable Context context, String str) {
        ApplicationInfo applicationInfo;
        if (context == null || (applicationInfo = context.getApplicationInfo()) == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return a(context.getPackageManager(), str);
        }
        try {
            return Icon.createWithResource(context, applicationInfo.icon);
        } catch (Exception e) {
            PWLog.error("Failed creation of icon", e);
            return null;
        }
    }

    @RequiresApi(api = 26)
    private static Icon a(@NonNull PackageManager packageManager, String str) {
        try {
            Drawable applicationIcon = packageManager.getApplicationIcon(str);
            if (applicationIcon instanceof BitmapDrawable) {
                return Icon.createWithBitmap(((BitmapDrawable) applicationIcon).getBitmap());
            }
            if (!(applicationIcon instanceof AdaptiveIconDrawable)) {
                return null;
            }
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{((AdaptiveIconDrawable) applicationIcon).getForeground()});
            Bitmap createBitmap = Bitmap.createBitmap(layerDrawable.getIntrinsicWidth(), layerDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            layerDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            layerDrawable.draw(canvas);
            return Icon.createWithAdaptiveBitmap(createBitmap);
        } catch (PackageManager.NameNotFoundException e) {
            PWLog.error("Failed to create icon", e);
            return null;
        }
    }
}
