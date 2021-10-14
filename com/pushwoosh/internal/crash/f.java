package com.pushwoosh.internal.crash;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

/* access modifiers changed from: package-private */
public class f implements Thread.UncaughtExceptionHandler {
    private boolean a = false;
    private e b;
    private Thread.UncaughtExceptionHandler c;

    f(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, e eVar, boolean z) {
        this.c = uncaughtExceptionHandler;
        this.a = z;
        this.b = eVar;
    }

    private static String a(Context context) {
        String packageName = context.getPackageName();
        return packageName == null ? "com.pushwoosh" : packageName;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0058  */
    private static void a(Context context, String str, String str2) throws IOException {
        Throwable th;
        BufferedWriter bufferedWriter;
        IOException e;
        if (!TextUtils.isEmpty(str)) {
            BufferedWriter bufferedWriter2 = null;
            try {
                File file = new File(g.a(context), str2);
                if (TextUtils.isEmpty(str) || TextUtils.getTrimmedLength(str) <= 0) {
                    bufferedWriter = null;
                } else {
                    bufferedWriter = new BufferedWriter(new FileWriter(file));
                    try {
                        bufferedWriter.write(str);
                        bufferedWriter.flush();
                    } catch (IOException e2) {
                        e = e2;
                    }
                }
                if (bufferedWriter == null) {
                    return;
                }
            } catch (IOException e3) {
                e = e3;
                bufferedWriter = null;
                try {
                    a.a("Failed to write value to " + str2, e);
                    if (bufferedWriter == null) {
                        return;
                    }
                    bufferedWriter.close();
                } catch (Throwable th2) {
                    bufferedWriter2 = bufferedWriter;
                    th = th2;
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (bufferedWriter2 != null) {
                }
                throw th;
            }
            bufferedWriter.close();
        }
    }

    public static void a(Throwable th, Thread thread, e eVar) {
        Date date = new Date();
        th.printStackTrace(new PrintWriter(new StringWriter()));
        WeakReference<Context> weakReference = d.c;
        Context context = weakReference != null ? weakReference.get() : null;
        if (context == null) {
            a.b("Failed to save exception: context in CrashManager is null");
            return;
        }
        String uuid = UUID.randomUUID().toString();
        c cVar = new c(uuid, th);
        cVar.a(a(context));
        cVar.b("6.2.8");
        cVar.c("6.2.8");
        cVar.i(b.b());
        cVar.a(date);
        cVar.h(b.a());
        if (eVar == null || eVar.d()) {
            cVar.g(Build.VERSION.RELEASE);
            cVar.f(Build.DISPLAY);
            cVar.d(Build.MANUFACTURER);
            cVar.e(Build.DEVICE);
        }
        if (thread != null && (eVar == null || eVar.e())) {
            cVar.j(thread.getName() + "-" + thread.getId());
        }
        cVar.a(context);
        if (eVar != null) {
            try {
                String a2 = eVar.a();
                a(context, a2, uuid + ".description");
            } catch (IOException e) {
                a.a("Error saving crash meta data!", e);
            }
        }
    }

    private boolean a(Context context, Throwable th) {
        String packageName = context.getPackageName();
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            String className = stackTraceElement.getClassName();
            for (String str : b.a) {
                if (className.contains(str)) {
                    return true;
                }
            }
            if (className.contains(packageName)) {
                return false;
            }
        }
        return false;
    }

    public void a(e eVar) {
        this.b = eVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        WeakReference<Context> weakReference = d.c;
        Context context = weakReference != null ? weakReference.get() : null;
        if (!(context == null || g.a(context) == null)) {
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                sb.append(stackTraceElement.getClassName());
            }
            String sb2 = sb.toString();
            if (a(context, th)) {
                Iterator<String> it = b.a.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (sb2.contains(it.next())) {
                            a(th, thread, this.b);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (this.a) {
                Process.killProcess(Process.myPid());
                System.exit(10);
                return;
            }
        }
        this.c.uncaughtException(thread, th);
    }
}
