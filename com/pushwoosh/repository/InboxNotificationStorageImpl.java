package com.pushwoosh.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.DbUtils;
import com.pushwoosh.internal.utils.PWLog;

public class InboxNotificationStorageImpl extends SQLiteOpenHelper implements h {
    private static final String b = "InboxNotificationStorageImpl";
    private final Object a = new Object();

    private static class Column {
        static final String INBOX_MESSAGE_ID = "inbox_message_id";
        static final String NOTIFICATION_ID = "notification_id";
        static final String NOTIFICATION_TAG = "notification_tag";

        private Column() {
        }
    }

    public InboxNotificationStorageImpl(Context context) {
        super(context, "inboxNotificationDb.db", (SQLiteDatabase.CursorFactory) null, 2);
    }

    private int a(Cursor cursor) {
        return cursor.getInt(cursor.getColumnIndex("notification_id"));
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL(String.format("create table %s (", str) + String.format("%s TEXT, ", "inbox_message_id") + String.format("%s TEXT, ", "notification_tag") + String.format("%s INTEGER ", "notification_id") + ");");
    }

    private ContentValues b(String str, int i, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("inbox_message_id", str);
        contentValues.put("notification_id", Integer.valueOf(i));
        contentValues.put("notification_tag", str2);
        return contentValues;
    }

    private String b(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex("notification_tag"));
    }

    private void b(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
        a(sQLiteDatabase, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0052, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0053, code lost:
        if (r1 != null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0059, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005a, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005d, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0060, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0061, code lost:
        if (r9 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0067, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0068, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006b, code lost:
        throw r2;
     */
    @Override // com.pushwoosh.repository.h
    @Nullable
    public String a(String str) {
        String b2;
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                Cursor query = writableDatabase.query("inboxNotifications", null, "inbox_message_id = ?", DbUtils.getSelectionArgs(str), null, null, null);
                if (query.moveToFirst()) {
                    b2 = b(query);
                    if (query != null) {
                        query.close();
                    }
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                } else {
                    String str2 = b;
                    PWLog.error(str2, "Can't find InboxMessage with id: " + str);
                    throw new Exception();
                }
            } catch (Exception e) {
                String str3 = b;
                PWLog.error(str3, "Can't get NotificationTag for InboxMessage with id: " + str, e);
                return null;
            }
        }
        return b2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
        if (r1 != null) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        r6.addSuppressed(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0047, code lost:
        throw r7;
     */
    @Override // com.pushwoosh.repository.h
    public void a(String str, int i, String str2) {
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase.insertWithOnConflict("inboxNotifications", null, b(str, i, str2), 5) == -1) {
                    String str3 = b;
                    PWLog.warn(str3, "Notification with inboxMessageId: " + str + " was not stored.");
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error(b, "Error occurred while storing notification id and notification tag", e);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0057, code lost:
        if (r1 != null) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005e, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0061, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0064, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0065, code lost:
        if (r9 != null) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x006b, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006c, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006f, code lost:
        throw r2;
     */
    @Override // com.pushwoosh.repository.h
    @Nullable
    public Integer b(String str) {
        Integer valueOf;
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                Cursor query = writableDatabase.query("inboxNotifications", null, "inbox_message_id = ?", DbUtils.getSelectionArgs(str), null, null, null);
                if (query.moveToFirst()) {
                    valueOf = Integer.valueOf(a(query));
                    if (query != null) {
                        query.close();
                    }
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                } else {
                    String str2 = b;
                    PWLog.error(str2, "Can't find InboxMessage with id: " + str);
                    throw new Exception();
                }
            } catch (Exception e) {
                String str3 = b;
                PWLog.error(str3, "Can't get NotificationId for InboxMessage with id: " + str, e);
                return null;
            }
        }
        return valueOf;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase, "inboxNotifications");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        b(sQLiteDatabase, "inboxNotifications");
    }
}
