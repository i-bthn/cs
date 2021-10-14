package com.google.android.gms.measurement.internal;

import android.os.Process;
import androidx.work.WorkRequest;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;

/* access modifiers changed from: package-private */
public final class zzfg extends Thread {
    private final /* synthetic */ zzfc zznt;
    private final Object zznu = new Object();
    private final BlockingQueue<zzfh<?>> zznv;

    public zzfg(zzfc zzfc, String str, BlockingQueue<zzfh<?>> blockingQueue) {
        this.zznt = zzfc;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zznv = blockingQueue;
        setName(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0065, code lost:
        r1 = r6.zznt.zzng;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x006b, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r6.zznt.zznh.release();
        r6.zznt.zzng.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0084, code lost:
        if (r6 != r6.zznt.zzna) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0086, code lost:
        r6.zznt.zzna = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0092, code lost:
        if (r6 != r6.zznt.zznb) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0094, code lost:
        r6.zznt.zznb = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x009a, code lost:
        r6.zznt.zzab().zzgk().zzao("Current scheduler thread is neither worker nor network");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a9, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00aa, code lost:
        return;
     */
    public final void run() {
        boolean z = false;
        while (!z) {
            try {
                this.zznt.zznh.acquire();
                z = true;
            } catch (InterruptedException e) {
                zza(e);
            }
        }
        try {
            int threadPriority = Process.getThreadPriority(Process.myTid());
            while (true) {
                zzfh<?> poll = this.zznv.poll();
                if (poll != null) {
                    Process.setThreadPriority(poll.zznx ? threadPriority : 10);
                    poll.run();
                } else {
                    synchronized (this.zznu) {
                        if (this.zznv.peek() == null && !(this.zznt.zzni)) {
                            try {
                                this.zznu.wait(WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
                            } catch (InterruptedException e2) {
                                zza(e2);
                            }
                        }
                    }
                    synchronized (this.zznt.zzng) {
                        if (this.zznv.peek() == null) {
                        }
                    }
                }
            }
        } catch (Throwable th) {
            synchronized (this.zznt.zzng) {
                this.zznt.zznh.release();
                this.zznt.zzng.notifyAll();
                if (this == this.zznt.zzna) {
                    this.zznt.zzna = null;
                } else if (this == this.zznt.zznb) {
                    this.zznt.zznb = null;
                } else {
                    this.zznt.zzab().zzgk().zzao("Current scheduler thread is neither worker nor network");
                }
                throw th;
            }
        }
    }

    public final void zzhr() {
        synchronized (this.zznu) {
            this.zznu.notifyAll();
        }
    }

    private final void zza(InterruptedException interruptedException) {
        this.zznt.zzab().zzgn().zza(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }
}
