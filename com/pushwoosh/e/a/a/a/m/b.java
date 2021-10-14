package com.pushwoosh.e.a.a.a.m;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

final class b implements Lock {
    private final File a;
    private RandomAccessFile b;
    private FileChannel c;
    private FileLock d;

    b(File file) {
        this.a = file;
    }

    public void lock() {
        try {
            this.b = new RandomAccessFile(this.a, "rwd");
            this.c = this.b.getChannel();
            this.d = this.c.lock();
        } catch (Exception e) {
            try {
                if (this.b != null) {
                    this.b.close();
                }
                if (this.c != null) {
                    this.c.close();
                }
            } catch (Exception unused) {
            }
            throw new com.pushwoosh.e.a.a.a.j.b(e);
        }
    }

    @Override // java.util.concurrent.locks.Lock
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException("Not implemented!");
    }

    public Condition newCondition() {
        throw new UnsupportedOperationException("Not implemented!");
    }

    public boolean tryLock() {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override // java.util.concurrent.locks.Lock
    public boolean tryLock(long j, TimeUnit timeUnit) throws InterruptedException {
        throw new UnsupportedOperationException("Not implemented!");
    }

    public void unlock() {
        try {
            if (this.d != null && this.d.isValid()) {
                this.d.release();
            }
            try {
                if (this.b != null) {
                    this.b.close();
                }
                if (this.c != null) {
                    this.c.close();
                }
            } catch (Exception unused) {
            }
        } catch (Exception e) {
            throw new com.pushwoosh.e.a.a.a.j.b(e);
        } catch (Throwable th) {
            try {
                if (this.b != null) {
                    this.b.close();
                }
                if (this.c != null) {
                    this.c.close();
                }
            } catch (Exception unused2) {
            }
            throw th;
        }
    }
}
