package com.pushwoosh.e.a.a.a.k.a;

import com.pushwoosh.e.a.a.a.j.a;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public final class b implements a {
    private static final String[] c = new String[0];
    private final File a;
    private final File b;

    public b(com.pushwoosh.e.a.a.a.k.b.b bVar) {
        this.a = bVar.c();
        this.b = bVar.b();
    }

    private void a(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    private void a(File file, File file2) {
        if (file.exists()) {
            if (file2.exists()) {
                file2.delete();
            }
            file.renameTo(file2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x004b A[SYNTHETIC, Splitter:B:28:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0050 A[Catch:{ Exception -> 0x0053 }] */
    private void a(File file, byte[] bArr) {
        FileChannel fileChannel;
        Throwable th;
        Exception e;
        RandomAccessFile randomAccessFile;
        FileChannel channel;
        RandomAccessFile randomAccessFile2 = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                randomAccessFile.setLength(0);
                channel = randomAccessFile.getChannel();
            } catch (Exception e2) {
                e = e2;
                try {
                    throw new a(e);
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel = randomAccessFile2;
                    randomAccessFile2 = randomAccessFile;
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (Exception unused) {
                            throw th;
                        }
                    }
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                    throw th;
                }
            }
            try {
                MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, (long) bArr.length);
                map.put(bArr);
                channel.write(map);
                map.force();
                try {
                    randomAccessFile.close();
                    if (channel != 0) {
                        channel.close();
                    }
                } catch (Exception unused2) {
                }
            } catch (Exception e3) {
                e = e3;
                randomAccessFile2 = channel;
                throw new a(e);
            } catch (Throwable th3) {
                randomAccessFile2 = randomAccessFile;
                fileChannel = channel;
                th = th3;
                if (randomAccessFile2 != null) {
                }
                if (fileChannel != null) {
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            randomAccessFile = null;
            throw new a(e);
        } catch (Throwable th4) {
            th = th4;
            fileChannel = null;
            if (randomAccessFile2 != null) {
            }
            if (fileChannel != null) {
            }
            throw th;
        }
    }

    private void b(String str, byte[] bArr) {
        if (bArr.length != 0) {
            File file = new File(this.a, str);
            File file2 = this.b;
            File file3 = new File(file2, str + ".bak");
            a(file, file3);
            a(file, bArr);
            a(file3);
            return;
        }
        throw new a(String.format("%s key's value is zero bytes for saving", str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0049 A[SYNTHETIC, Splitter:B:30:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x004e A[Catch:{ Exception -> 0x0051 }] */
    private byte[] b(File file) {
        Throwable th;
        FileChannel fileChannel;
        RandomAccessFile randomAccessFile;
        Exception e;
        RandomAccessFile randomAccessFile2;
        FileChannel fileChannel2;
        Exception e2;
        RandomAccessFile randomAccessFile3 = null;
        try {
            randomAccessFile2 = new RandomAccessFile(file, "r");
            try {
                fileChannel2 = randomAccessFile2.getChannel();
                try {
                    int length = (int) randomAccessFile2.length();
                    byte[] bArr = new byte[length];
                    fileChannel2.map(FileChannel.MapMode.READ_ONLY, 0, (long) length).get(bArr);
                    try {
                        randomAccessFile2.close();
                        if (fileChannel2 != null) {
                            fileChannel2.close();
                        }
                    } catch (Exception unused) {
                    }
                    return bArr;
                } catch (Exception e3) {
                    e2 = e3;
                    randomAccessFile3 = fileChannel2;
                    e = e2;
                    try {
                        throw new a(e);
                    } catch (Throwable th2) {
                        th = th2;
                        randomAccessFile = randomAccessFile2;
                        fileChannel = randomAccessFile3;
                        randomAccessFile3 = randomAccessFile;
                        if (randomAccessFile3 != null) {
                            try {
                                randomAccessFile3.close();
                            } catch (Exception unused2) {
                                throw th;
                            }
                        }
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    randomAccessFile = randomAccessFile2;
                    fileChannel = fileChannel2;
                    th = th3;
                    randomAccessFile3 = randomAccessFile;
                    if (randomAccessFile3 != null) {
                    }
                    if (fileChannel != null) {
                    }
                    throw th;
                }
            } catch (Exception e4) {
                e2 = e4;
                fileChannel2 = null;
                randomAccessFile3 = fileChannel2;
                e = e2;
                throw new a(e);
            }
        } catch (Exception e5) {
            e = e5;
            randomAccessFile2 = null;
            throw new a(e);
        } catch (Throwable th4) {
            th = th4;
            fileChannel = null;
            if (randomAccessFile3 != null) {
            }
            if (fileChannel != null) {
            }
            throw th;
        }
    }

    private byte[] b(String str) {
        File file = this.b;
        File file2 = new File(file, str + ".bak");
        File file3 = new File(this.a, str);
        if (file2.exists()) {
            a(file3);
            a(file2, file3);
        }
        return b(file3);
    }

    private String[] b() {
        String[] list = this.a.list();
        return list == null ? c : list;
    }

    private void c(String str) {
        try {
            File file = new File(this.a, str);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            throw new a(e);
        }
    }

    @Override // com.pushwoosh.e.a.a.a.k.a.a
    public void a(String str, byte[] bArr) {
        b(str, bArr);
    }

    @Override // com.pushwoosh.e.a.a.a.k.a.a
    public byte[] a(String str) {
        return b(str);
    }

    @Override // com.pushwoosh.e.a.a.a.k.a.a
    public String[] a() {
        return b();
    }

    @Override // com.pushwoosh.e.a.a.a.k.a.a
    public void remove(String str) {
        c(str);
    }
}
