package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.WorkerThread;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.zzac;
import com.google.firebase.iid.zzaw;
import com.google.firebase.iid.zzc;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FirebaseMessagingService extends zzc {
    private static final Queue<String> zzec = new ArrayDeque(10);

    @WorkerThread
    public void onDeletedMessages() {
    }

    @WorkerThread
    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    @WorkerThread
    public void onMessageSent(String str) {
    }

    @WorkerThread
    public void onNewToken(String str) {
    }

    @WorkerThread
    public void onSendError(String str, Exception exc) {
    }

    /* access modifiers changed from: protected */
    @Override // com.google.firebase.iid.zzc
    public final Intent zzb(Intent intent) {
        return zzaw.zzak().zzal();
    }

    @Override // com.google.firebase.iid.zzc
    public final boolean zzc(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException unused) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (!MessagingAnalytics.shouldUploadMetrics(intent)) {
            return true;
        }
        MessagingAnalytics.logNotificationOpen(intent);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f7, code lost:
        if (r0.equals("send_event") == false) goto L_0x0118;
     */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x015b  */
    @Override // com.google.firebase.iid.zzc
    public final void zzd(Intent intent) {
        Task<Void> task;
        boolean z;
        String str;
        String action = intent.getAction();
        if ("com.google.android.c2dm.intent.RECEIVE".equals(action) || "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT".equals(action)) {
            String stringExtra = intent.getStringExtra("google.message_id");
            char c = 2;
            if (TextUtils.isEmpty(stringExtra)) {
                task = Tasks.forResult(null);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("google.message_id", stringExtra);
                task = zzac.zzc(this).zza(2, bundle);
            }
            if (TextUtils.isEmpty(stringExtra)) {
                z = false;
            } else if (zzec.contains(stringExtra)) {
                if (Log.isLoggable("FirebaseMessaging", 3)) {
                    String valueOf = String.valueOf(stringExtra);
                    Log.d("FirebaseMessaging", valueOf.length() != 0 ? "Received duplicate message: ".concat(valueOf) : new String("Received duplicate message: "));
                }
                z = true;
            } else {
                if (zzec.size() >= 10) {
                    zzec.remove();
                }
                zzec.add(stringExtra);
                z = false;
            }
            if (!z) {
                String stringExtra2 = intent.getStringExtra("message_type");
                if (stringExtra2 == null) {
                    stringExtra2 = "gcm";
                }
                int hashCode = stringExtra2.hashCode();
                if (hashCode != -2062414158) {
                    if (hashCode != 102161) {
                        if (hashCode == 814694033) {
                            if (stringExtra2.equals("send_error")) {
                                c = 3;
                                switch (c) {
                                    case 0:
                                        if (MessagingAnalytics.shouldUploadMetrics(intent)) {
                                            MessagingAnalytics.logNotificationReceived(intent);
                                        }
                                        Bundle extras = intent.getExtras();
                                        if (extras == null) {
                                            extras = new Bundle();
                                        }
                                        extras.remove("androidx.contentpager.content.wakelockid");
                                        if (zzb.zzh(extras)) {
                                            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
                                            try {
                                                if (new zzc(this, extras, newSingleThreadExecutor).zzas()) {
                                                    break;
                                                } else {
                                                    newSingleThreadExecutor.shutdown();
                                                    if (MessagingAnalytics.shouldUploadMetrics(intent)) {
                                                        MessagingAnalytics.logNotificationForeground(intent);
                                                    }
                                                }
                                            } finally {
                                                newSingleThreadExecutor.shutdown();
                                            }
                                        }
                                        onMessageReceived(new RemoteMessage(extras));
                                        break;
                                    case 1:
                                        onDeletedMessages();
                                        break;
                                    case 2:
                                        onMessageSent(intent.getStringExtra("google.message_id"));
                                        break;
                                    case 3:
                                        String stringExtra3 = intent.getStringExtra("google.message_id");
                                        if (stringExtra3 == null) {
                                            stringExtra3 = intent.getStringExtra("message_id");
                                        }
                                        onSendError(stringExtra3, new SendException(intent.getStringExtra("error")));
                                        break;
                                    default:
                                        String valueOf2 = String.valueOf(stringExtra2);
                                        if (valueOf2.length() != 0) {
                                            str = "Received message with unknown type: ".concat(valueOf2);
                                        } else {
                                            str = new String("Received message with unknown type: ");
                                        }
                                        Log.w("FirebaseMessaging", str);
                                        break;
                                }
                            }
                        } else if (hashCode == 814800675) {
                        }
                    } else if (stringExtra2.equals("gcm")) {
                        c = 0;
                        switch (c) {
                        }
                    }
                } else if (stringExtra2.equals("deleted_messages")) {
                    c = 1;
                    switch (c) {
                    }
                }
                c = 65535;
                switch (c) {
                }
            }
            try {
                Tasks.await(task, 1, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                String valueOf3 = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf3).length() + 20);
                sb.append("Message ack failed: ");
                sb.append(valueOf3);
                Log.w("FirebaseMessaging", sb.toString());
            }
        } else if ("com.google.firebase.messaging.NOTIFICATION_DISMISS".equals(action)) {
            if (MessagingAnalytics.shouldUploadMetrics(intent)) {
                MessagingAnalytics.logNotificationDismiss(intent);
            }
        } else if ("com.google.firebase.messaging.NEW_TOKEN".equals(action)) {
            onNewToken(intent.getStringExtra("token"));
        } else {
            String valueOf4 = String.valueOf(intent.getAction());
            Log.d("FirebaseMessaging", valueOf4.length() != 0 ? "Unknown intent action: ".concat(valueOf4) : new String("Unknown intent action: "));
        }
    }
}
