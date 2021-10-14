package com.pushwoosh.internal.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.FileProvider;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.notification.SoundType;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.l;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class e {

    /* access modifiers changed from: package-private */
    public enum a {
        sound,
        image
    }

    private static float a(int i, int i2, int i3) {
        DisplayMetrics b;
        int max = Math.max(i2, i3);
        if (-1 == i || (b = AndroidPlatformModule.getResourceProvider().b()) == null) {
            return 1.0f;
        }
        return ((float) max) / (((float) i) * b.density);
    }

    public static Bitmap a(String str, int i) {
        return c(str) ? c(str, i) : b(a(str), i);
    }

    private static Uri a(File file) {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext == null) {
            return Uri.fromFile(file);
        }
        Uri uriForFile = FileProvider.getUriForFile(applicationContext, applicationContext.getPackageName() + ".provider", file);
        applicationContext.grantUriPermission("com.android.systemui", uriForFile, 1);
        return uriForFile;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0089, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x008a, code lost:
        if (r0 != null) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0090, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0091, code lost:
        r4.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0094, code lost:
        throw r1;
     */
    private static Uri a(String str, a aVar) {
        String str2;
        String str3;
        String substring = str.substring(str.lastIndexOf(47) + 1);
        File c = AndroidPlatformModule.getAppInfoProvider().c();
        if (c == null) {
            str2 = "Asset";
            str3 = "Missing external cache dir";
        } else {
            String str4 = c.toString() + "/com.pushwoosh.noitfysnd";
            File file = new File(str4, substring);
            File file2 = new File(str4);
            if (file2.exists() || file2.mkdir()) {
                AssetManager assets = AndroidPlatformModule.getManagerProvider().getAssets();
                if (assets == null) {
                    return Uri.EMPTY;
                }
                try {
                    InputStream open = assets.open(str);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    a(open, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    if (aVar == a.sound) {
                        Uri a2 = a(file);
                        if (open != null) {
                            open.close();
                        }
                        return a2;
                    }
                    Uri fromFile = Uri.fromFile(file);
                    if (open != null) {
                        open.close();
                    }
                    return fromFile;
                } catch (Throwable th) {
                    PWLog.error("Asset", "File not found: assets/" + str);
                    PWLog.exception(th);
                }
            } else {
                str2 = "Asset";
                str3 = "Storage file not created";
            }
        }
        PWLog.error(str2, str3);
        return Uri.EMPTY;
    }

    public static String a(String str) {
        return a(str, a.image).getEncodedPath();
    }

    private static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static boolean a() {
        if (Build.VERSION.SDK_INT < 19) {
            return true;
        }
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext != null) {
            return NotificationManagerCompat.from(applicationContext).areNotificationsEnabled();
        }
        PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
        return false;
    }

    public static Bitmap b(String str, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = Math.round(a(i, options.outWidth, options.outHeight));
        return BitmapFactory.decodeFile(str, options);
    }

    @Nullable
    public static Uri b(String str) {
        l notificationPreferences = RepositoryModule.getNotificationPreferences();
        Uri defaultUri = RingtoneManager.getDefaultUri(2);
        AudioManager audioManager = AndroidPlatformModule.getManagerProvider().getAudioManager();
        if (audioManager != null) {
            if (notificationPreferences.n().get() != SoundType.ALWAYS && (audioManager.getRingerMode() != 2 || notificationPreferences.n().get() != SoundType.DEFAULT_MODE)) {
                return null;
            }
            if (str == null) {
                return defaultUri;
            }
            if (str.length() == 0) {
                return null;
            }
        }
        int a2 = AndroidPlatformModule.getResourceProvider().a(str, "raw");
        if (a2 != 0) {
            return Uri.parse("android.resource://" + AndroidPlatformModule.getAppInfoProvider().a() + "/" + a2);
        }
        Uri a3 = a("www/res/" + str, a.sound);
        return a3 != Uri.EMPTY ? a3 : defaultUri;
    }

    public static boolean b() {
        PackageManager packageManager = AndroidPlatformModule.getManagerProvider().getPackageManager();
        if (packageManager == null) {
            return false;
        }
        try {
            return packageManager.checkPermission("android.permission.VIBRATE", AndroidPlatformModule.getAppInfoProvider().a()) == 0;
        } catch (Exception e) {
            PWLog.error("error in checking vibrate permission", e);
        }
    }

    /* JADX INFO: finally extract failed */
    public static Bitmap c(String str, int i) {
        if (str == null) {
            return null;
        }
        try {
            URLConnection openConnection = new URL(str).openConnection();
            openConnection.connect();
            byte[] bArr = new byte[1024];
            InputStream inputStream = openConnection.getInputStream();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int read = inputStream.read(bArr);
                        if (read != -1) {
                            byteArrayOutputStream.write(bArr, 0, read);
                        } else {
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                            inputStream.close();
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inJustDecodeBounds = true;
                            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
                            float a2 = a(i, options.outWidth, options.outHeight);
                            options.inJustDecodeBounds = false;
                            options.inSampleSize = Math.round(a2);
                            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
                        }
                    } catch (Throwable th) {
                        byteArrayOutputStream.close();
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                inputStream.close();
                throw th2;
            }
        } catch (Throwable th3) {
            PWLog.error("Can't load image: " + str, th3);
            return null;
        }
    }

    public static void c() {
        PowerManager powerManager;
        try {
            if (RepositoryModule.getNotificationPreferences().g().get() && (powerManager = AndroidPlatformModule.getManagerProvider().getPowerManager()) != null) {
                powerManager.newWakeLock(268435466, "Push").acquire(1000);
            }
        } catch (Exception e) {
            PWLog.exception(e);
        }
    }

    public static boolean c(String str) {
        try {
            new URL(str).toURI();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static int d(String str) {
        int a2;
        int a3 = AndroidPlatformModule.getResourceProvider().a("pw_notification", "drawable");
        if (a3 == 0) {
            a3 = -1;
        }
        int k = PushwooshPlatform.getInstance().c().k();
        if (k != 0) {
            a3 = k;
        }
        return (str == null || (a2 = AndroidPlatformModule.getResourceProvider().a(str, "drawable")) == 0) ? a3 : a2;
    }
}
