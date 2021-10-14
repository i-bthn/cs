package com.pushwoosh.internal.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.crash.LogSender;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class d {
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00b6, code lost:
        if (r1 != null) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00c4, code lost:
        if (r1 != null) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0095 A[SYNTHETIC, Splitter:B:43:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00ce A[SYNTHETIC, Splitter:B:73:0x00ce] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0035 A[SYNTHETIC] */
    @Nullable
    public static File a(@Nullable File file, @Nullable File file2) {
        Throwable th;
        ZipInputStream zipInputStream;
        FileNotFoundException e;
        IOException e2;
        Throwable th2;
        BufferedOutputStream bufferedOutputStream;
        IOException e3;
        ZipInputStream zipInputStream2 = null;
        if (file == null || file2 == null || !c(file)) {
            return null;
        }
        a(file2);
        if (!file2.isDirectory() && !file2.mkdirs()) {
            return null;
        }
        try {
            zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file), 8192));
            try {
                byte[] bArr = new byte[8192];
                String canonicalPath = file2.getCanonicalPath();
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry != null) {
                        File file3 = new File(canonicalPath, nextEntry.getName());
                        if (!file3.getCanonicalPath().startsWith(canonicalPath)) {
                            throw new SecurityException("Provided zip file path has Path Traversal Vulnerability");
                        } else if (nextEntry.isDirectory()) {
                            file3.mkdir();
                        } else {
                            new File(file3.getParent()).mkdirs();
                            try {
                                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file3), 8192);
                                while (true) {
                                    try {
                                        int read = zipInputStream.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        bufferedOutputStream.write(bArr, 0, read);
                                    } catch (IOException e4) {
                                        e3 = e4;
                                        try {
                                            PWLog.exception(e3);
                                            file3.delete();
                                            if (bufferedOutputStream == null) {
                                            }
                                            bufferedOutputStream.close();
                                        } catch (Throwable th3) {
                                            th2 = th3;
                                            if (bufferedOutputStream != null) {
                                                try {
                                                    bufferedOutputStream.close();
                                                } catch (IOException unused) {
                                                }
                                            }
                                            throw th2;
                                        }
                                    }
                                }
                                bufferedOutputStream.flush();
                            } catch (IOException e5) {
                                e3 = e5;
                                bufferedOutputStream = null;
                                PWLog.exception(e3);
                                file3.delete();
                                if (bufferedOutputStream == null) {
                                }
                                bufferedOutputStream.close();
                            } catch (Throwable th4) {
                                th2 = th4;
                                bufferedOutputStream = null;
                                if (bufferedOutputStream != null) {
                                }
                                throw th2;
                            }
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException unused2) {
                            }
                        }
                    } else {
                        try {
                            zipInputStream.close();
                        } catch (IOException unused3) {
                        }
                        return file2;
                    }
                }
            } catch (FileNotFoundException e6) {
                e = e6;
                PWLog.error("FileUtils", e.getMessage(), e);
            } catch (IOException e7) {
                e2 = e7;
                try {
                    PWLog.error("FileUtils", e2.getMessage(), e2);
                } catch (Throwable th5) {
                    th = th5;
                    zipInputStream2 = zipInputStream;
                    if (zipInputStream2 != null) {
                    }
                    throw th;
                }
            }
        } catch (FileNotFoundException e8) {
            e = e8;
            zipInputStream = null;
            PWLog.error("FileUtils", e.getMessage(), e);
        } catch (IOException e9) {
            e2 = e9;
            zipInputStream = null;
            PWLog.error("FileUtils", e2.getMessage(), e2);
        } catch (Throwable th6) {
            th = th6;
            if (zipInputStream2 != null) {
                try {
                    zipInputStream2.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
        return null;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:26|27|(2:28|(1:30)(1:105))|31|32|33|34|35|(1:38)|39) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0042, code lost:
        if (r3 == null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        com.pushwoosh.internal.utils.PWLog.error("FileUtils", "fail download: " + r10 + "  responseCode: " + r2.getResponseCode());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x007e */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x00b8 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0052 A[SYNTHETIC, Splitter:B:19:0x0052] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00a7 A[SYNTHETIC, Splitter:B:60:0x00a7] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ae A[SYNTHETIC, Splitter:B:64:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00c0 A[SYNTHETIC, Splitter:B:72:0x00c0] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00c7 A[SYNTHETIC, Splitter:B:76:0x00c7] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x00d6 A[SYNTHETIC, Splitter:B:87:0x00d6] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x00df A[SYNTHETIC, Splitter:B:92:0x00df] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x00e6  */
    @Nullable
    public static File a(String str, File file) {
        BufferedInputStream bufferedInputStream;
        HttpURLConnection httpURLConnection;
        Throwable th;
        IOException e;
        IOException iOException;
        IOException e2;
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream2 = null;
        BufferedOutputStream bufferedOutputStream2 = null;
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i >= 3) {
                break;
            }
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() != 200) {
                        break;
                    }
                    bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    try {
                        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    } catch (MalformedURLException unused) {
                        if (bufferedInputStream != null) {
                        }
                        if (bufferedOutputStream2 != null) {
                        }
                        if (httpURLConnection != null) {
                        }
                        return null;
                    } catch (IOException e3) {
                        e = e3;
                        try {
                            PWLog.exception(e);
                            if (bufferedInputStream != null) {
                            }
                            if (bufferedOutputStream2 != null) {
                            }
                            if (httpURLConnection == null) {
                            }
                            i = i2;
                            bufferedInputStream2 = bufferedInputStream;
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedInputStream2 = bufferedInputStream;
                            if (bufferedInputStream2 != null) {
                            }
                            if (bufferedOutputStream2 != null) {
                            }
                            if (httpURLConnection != null) {
                            }
                            throw th;
                        }
                    }
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = bufferedInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr, 0, read);
                        }
                        bufferedOutputStream.flush();
                        bufferedInputStream.close();
                        bufferedOutputStream.close();
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return file;
                    } catch (MalformedURLException unused2) {
                        bufferedOutputStream2 = bufferedOutputStream;
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException unused3) {
                            }
                        }
                        if (bufferedOutputStream2 != null) {
                            try {
                                bufferedOutputStream2.close();
                            } catch (IOException unused4) {
                            }
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return null;
                    } catch (IOException e4) {
                        iOException = e4;
                        bufferedOutputStream2 = bufferedOutputStream;
                        e = iOException;
                        PWLog.exception(e);
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException unused5) {
                            }
                        }
                        if (bufferedOutputStream2 != null) {
                            try {
                                bufferedOutputStream2.close();
                            } catch (IOException unused6) {
                            }
                        }
                        if (httpURLConnection == null) {
                            httpURLConnection.disconnect();
                        }
                        i = i2;
                        bufferedInputStream2 = bufferedInputStream;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedOutputStream2 = bufferedOutputStream;
                        bufferedInputStream2 = bufferedInputStream;
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException unused7) {
                            }
                        }
                        if (bufferedOutputStream2 != null) {
                            try {
                                bufferedOutputStream2.close();
                            } catch (IOException unused8) {
                            }
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (MalformedURLException unused9) {
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                    }
                    if (bufferedOutputStream2 != null) {
                    }
                    if (httpURLConnection != null) {
                    }
                    return null;
                } catch (IOException e5) {
                    e2 = e5;
                    iOException = e2;
                    bufferedInputStream = bufferedInputStream2;
                    e = iOException;
                    PWLog.exception(e);
                    if (bufferedInputStream != null) {
                    }
                    if (bufferedOutputStream2 != null) {
                    }
                    if (httpURLConnection == null) {
                    }
                    i = i2;
                    bufferedInputStream2 = bufferedInputStream;
                } catch (Throwable th4) {
                    th = th4;
                    if (bufferedInputStream2 != null) {
                    }
                    if (bufferedOutputStream2 != null) {
                    }
                    if (httpURLConnection != null) {
                    }
                    throw th;
                }
            } catch (MalformedURLException unused10) {
                httpURLConnection = null;
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                }
                if (bufferedOutputStream2 != null) {
                }
                if (httpURLConnection != null) {
                }
                return null;
            } catch (IOException e6) {
                e2 = e6;
                httpURLConnection = null;
                iOException = e2;
                bufferedInputStream = bufferedInputStream2;
                e = iOException;
                PWLog.exception(e);
                if (bufferedInputStream != null) {
                }
                if (bufferedOutputStream2 != null) {
                }
                if (httpURLConnection == null) {
                }
                i = i2;
                bufferedInputStream2 = bufferedInputStream;
            } catch (Throwable th5) {
                th = th5;
                httpURLConnection = null;
                if (bufferedInputStream2 != null) {
                }
                if (bufferedOutputStream2 != null) {
                }
                if (httpURLConnection != null) {
                }
                throw th;
            }
            i = i2;
            bufferedInputStream2 = bufferedInputStream;
        }
        return null;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e7) {
                if (!a(e7)) {
                    LogSender.submitCustomError(e7);
                }
            }
        }
        return null;
        if (bufferedOutputStream2 != null) {
            try {
                bufferedOutputStream2.close();
            } catch (IOException unused11) {
            }
        }
        if (httpURLConnection != null) {
        }
        return null;
    }

    public static String a(String str) {
        String[] split = str.split("/");
        return split[split.length - 1];
    }

    public static boolean a(File file) {
        File[] listFiles;
        if (!file.exists()) {
            return true;
        }
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                a(file2);
            }
        }
        return file.delete();
    }

    private static boolean a(Exception exc) {
        return (exc instanceof NullPointerException) && exc.getMessage().equals("ssl_session == null");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0073 A[SYNTHETIC, Splitter:B:27:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0095 A[SYNTHETIC, Splitter:B:35:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b7 A[SYNTHETIC, Splitter:B:43:0x00b7] */
    @NonNull
    public static String b(@NonNull File file) {
        Throwable th;
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = bufferedInputStream2.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                }
                StringBuilder sb = new StringBuilder();
                byte[] digest = instance.digest();
                int length = digest.length;
                for (int i = 0; i < length; i++) {
                    sb.append(String.format("%02x", Byte.valueOf(digest[i])));
                }
                String sb2 = sb.toString();
                try {
                    bufferedInputStream2.close();
                } catch (IOException e) {
                    PWLog.error("Failed to read file " + file.getName(), e);
                }
                return sb2;
            } catch (IOException unused) {
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                }
                return "";
            } catch (NoSuchAlgorithmException unused2) {
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                }
                return "";
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                }
                throw th;
            }
        } catch (IOException unused3) {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e2) {
                    PWLog.error("Failed to read file " + file.getName(), e2);
                }
            }
            return "";
        } catch (NoSuchAlgorithmException unused4) {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e3) {
                    PWLog.error("Failed to read file " + file.getName(), e3);
                }
            }
            return "";
        } catch (Throwable th3) {
            th = th3;
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e4) {
                    PWLog.error("Failed to read file " + file.getName(), e4);
                }
            }
            throw th;
        }
    }

    public static String b(String str) {
        return str.substring(0, str.lastIndexOf("."));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000c, code lost:
        return false;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    private static boolean c(File file) {
        try {
            new ZipFile(file).close();
        } catch (IOException unused) {
        }
        return true;
    }

    /* JADX INFO: finally extract failed */
    public static String d(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append("\n");
                } else {
                    bufferedReader.close();
                    fileInputStream.close();
                    return sb.toString();
                }
            } catch (Throwable th) {
                bufferedReader.close();
                fileInputStream.close();
                throw th;
            }
        }
    }
}
