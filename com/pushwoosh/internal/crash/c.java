package com.pushwoosh.internal.crash;

import android.content.Context;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* access modifiers changed from: package-private */
public class c {
    public static final SimpleDateFormat n = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
    private final String a;
    private Date b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;

    public c(String str) {
        this.a = str;
        this.l = "";
    }

    public c(String str, Throwable th) {
        this(str);
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        this.l = stringWriter.toString();
    }

    private void a(Writer writer, String str, String str2) throws IOException {
        writer.write(str + ": " + str2 + "\n");
    }

    public void a(Context context) {
        File a2 = g.a(context);
        a(new File(a2, this.a + g.a()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00a4 A[SYNTHETIC, Splitter:B:23:0x00a4] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    public void a(File file) {
        Throwable th;
        IOException e2;
        a.a("Writing unhandled exception to: " + file.getAbsolutePath());
        BufferedWriter bufferedWriter = null;
        try {
            BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file));
            try {
                a(bufferedWriter2, "Package", this.g);
                a(bufferedWriter2, "Version Code", this.i);
                a(bufferedWriter2, "Version Name", this.h);
                a(bufferedWriter2, "Android", this.c);
                a(bufferedWriter2, "Android Build", this.d);
                a(bufferedWriter2, "Manufacturer", this.e);
                a(bufferedWriter2, "Model", this.f);
                a(bufferedWriter2, "Thread", this.k);
                a(bufferedWriter2, "Date", n.format(this.b));
                a(bufferedWriter2, "Pushwoosh Application Code", this.j);
                a(bufferedWriter2, "Pushwoosh Plugin", this.m);
                bufferedWriter2.write("\n");
                bufferedWriter2.write(this.l);
                bufferedWriter2.flush();
                try {
                    bufferedWriter2.close();
                } catch (IOException e3) {
                    a.a("Error saving crash report!", e3);
                }
            } catch (IOException e4) {
                e2 = e4;
                bufferedWriter = bufferedWriter2;
                try {
                    a.a("Error saving crash report!", e2);
                    if (bufferedWriter == null) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e5) {
                            a.a("Error saving crash report!", e5);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedWriter = bufferedWriter2;
                if (bufferedWriter != null) {
                }
                throw th;
            }
        } catch (IOException e6) {
            e2 = e6;
            a.a("Error saving crash report!", e2);
            if (bufferedWriter == null) {
                bufferedWriter.close();
            }
        }
    }

    public void a(String str) {
        this.g = str;
    }

    public void a(Date date) {
        this.b = date;
    }

    public void b(String str) {
        this.i = str;
    }

    public void c(String str) {
        this.h = str;
    }

    public void d(String str) {
        this.e = str;
    }

    public void e(String str) {
        this.f = str;
    }

    public void f(String str) {
        this.d = str;
    }

    public void g(String str) {
        this.c = str;
    }

    public void h(String str) {
        this.j = str;
    }

    public void i(String str) {
        this.m = str;
    }

    public void j(String str) {
        this.k = str;
    }
}
