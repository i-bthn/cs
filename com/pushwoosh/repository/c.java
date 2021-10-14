package com.pushwoosh.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import com.pushwoosh.internal.utils.DbUtils;
import com.pushwoosh.internal.utils.JsonUtils;
import com.pushwoosh.internal.utils.PWLog;
import java.util.HashSet;
import java.util.Set;

public class c extends SQLiteOpenHelper {
    private static final String b = b.class.getSimpleName();
    private final Object a = new Object();

    public interface a {
        void a(b bVar);
    }

    public c(Context context) {
        super(context, "localNotification.db", (SQLiteDatabase.CursorFactory) null, 2);
    }

    @NonNull
    private b a(Cursor cursor) {
        return new b(cursor.getInt(cursor.getColumnIndex("requestId")), cursor.getInt(cursor.getColumnIndex("notificationId")), cursor.getString(cursor.getColumnIndex("notificationTag")), cursor.getLong(cursor.getColumnIndex("triggerAtMilles")), JsonUtils.jsonStringToBundle(cursor.getString(cursor.getColumnIndex("bundle"))));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0014, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0015, code lost:
        if (r1 != null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x001c, code lost:
        r5.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x001f, code lost:
        throw r2;
     */
    private b a(String str, String str2) {
        b a2;
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                a2 = a(str, str2, writableDatabase);
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't get notification from db with requestId: " + str, e);
                return new b();
            }
        }
        return a2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004e, code lost:
        if (r13 != null) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0054, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0055, code lost:
        r11.addSuppressed(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0058, code lost:
        throw r12;
     */
    private b a(String str, String str2, SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query(str2, null, "requestId = ?", DbUtils.getSelectionArgs(str), null, null, null);
        if (query.moveToFirst()) {
            b a2 = a(query);
            if (query != null) {
                query.close();
            }
            return a2;
        }
        if (query != null) {
            query.close();
        }
        String str3 = b;
        PWLog.noise(str3, "cant find local notification in table " + str2 + " by id " + str);
        return null;
    }

    private void a(int i, ContentValues contentValues, SQLiteDatabase sQLiteDatabase, String str) {
        String num = Integer.toString(i);
        if (sQLiteDatabase.updateWithOnConflict(str, contentValues, "requestId= ?", new String[]{num}, 4) == 0 && sQLiteDatabase.insert(str, null, contentValues) == 0) {
            PWLog.warn("notification", "Not stored " + num);
        }
    }

    private void a(int i, SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", Integer.valueOf(i));
        if (sQLiteDatabase.updateWithOnConflict("nextRequestId", contentValues, null, null, 4) == 0 && sQLiteDatabase.insert("nextRequestId", null, contentValues) == 0) {
            PWLog.warn("saveNextId", "Not stored ");
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format("create table %s (", "nextRequestId") + String.format("%s INTEGER primary key ", "value") + ");");
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL(String.format("create table %s (", str) + String.format("%s INTEGER primary key, ", "requestId") + String.format("%s INTEGER, ", "notificationId") + String.format("%s INTEGER, ", "triggerAtMilles") + String.format("%s TEXT, ", "notificationTag") + String.format("%s TEXT ", "bundle") + ");");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        if (r11 != null) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0035, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0036, code lost:
        r12.addSuppressed(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0039, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003c, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x003d, code lost:
        if (r9 != null) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0043, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0044, code lost:
        r11.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0047, code lost:
        throw r12;
     */
    private void a(String str, a aVar) {
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                Cursor query = writableDatabase.query(str, null, null, null, null, null, null);
                while (query.moveToNext()) {
                    aVar.a(a(query));
                }
                if (query != null) {
                    query.close();
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't get NotificationList: ", e);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0038, code lost:
        if (r11 != null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        r0.addSuppressed(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
        throw r1;
     */
    @NonNull
    private Set<Integer> b(SQLiteDatabase sQLiteDatabase) {
        HashSet hashSet = new HashSet();
        Cursor query = sQLiteDatabase.query("localNotification", new String[]{"requestId"}, null, null, null, null, null);
        while (query.moveToNext()) {
            hashSet.add(Integer.valueOf(query.getInt(query.getColumnIndex("requestId"))));
        }
        if (query != null) {
            query.close();
        }
        return hashSet;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006b, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006c, code lost:
        if (r1 != null) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0072, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0073, code lost:
        r5.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0076, code lost:
        throw r6;
     */
    private void b(int i, String str) {
        String str2;
        StringBuilder sb;
        synchronized (this.a) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            int delete = writableDatabase.delete(str, "requestId=" + i, null);
            PWLog.debug("delete notification " + delete + " by requestID:" + i);
            if (delete > 0) {
                str2 = b;
                sb = new StringBuilder();
                sb.append("success remove local notification by ");
                sb.append(i);
            } else {
                str2 = b;
                sb = new StringBuilder();
                sb.append("fail remove local notification by ");
                sb.append(i);
            }
            PWLog.noise(str2, sb.toString());
            if (writableDatabase != null) {
                writableDatabase.close();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        if (r9 != null) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003b, code lost:
        r0.addSuppressed(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003e, code lost:
        throw r1;
     */
    private int c(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("nextRequestId", null, null, null, null, null, null);
        if (query.moveToFirst()) {
            int i = query.getInt(query.getColumnIndex("value"));
            if (query != null) {
                query.close();
            }
            return i;
        }
        if (query != null) {
            query.close();
        }
        PWLog.noise(b, "nextId is empty, return 0");
        return 0;
    }

    @NonNull
    private ContentValues c(b bVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("requestId", Integer.valueOf(bVar.d()));
        contentValues.put("notificationId", Integer.valueOf(bVar.b()));
        contentValues.put("notificationTag", bVar.c());
        contentValues.put("triggerAtMilles", Long.valueOf(bVar.e()));
        contentValues.put("bundle", JsonUtils.bundleToJson(bVar.a()).toString());
        return contentValues;
    }

    private long d(SQLiteDatabase sQLiteDatabase) {
        return DatabaseUtils.queryNumEntries(sQLiteDatabase, "localNotificationShown");
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("localNotificationShown", null, null, null, null, null, "requestId");
        if (query.moveToFirst()) {
            sQLiteDatabase.delete("localNotificationShown", "requestId=?", new String[]{query.getString(query.getColumnIndex("requestId"))});
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0048, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r11 != null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004f, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0050, code lost:
        r12.addSuppressed(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0053, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0056, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0057, code lost:
        if (r9 != null) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x005d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x005e, code lost:
        r11.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0061, code lost:
        throw r12;
     */
    public b a(int i, String str) {
        String num = Integer.toString(i);
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                Cursor query = writableDatabase.query("localNotificationShown", null, "notificationId = ? AND notificationTag = ?", DbUtils.getSelectionArgs(num, str), null, null, null);
                if (query.moveToNext()) {
                    b a2 = a(query);
                    if (query != null) {
                        query.close();
                    }
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                    return a2;
                }
                if (query != null) {
                    query.close();
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
                return null;
            } catch (Exception e) {
                PWLog.error("Can't get Notification: ", e);
            }
        }
    }

    public b a(String str) {
        return a(str, "localNotificationShown");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0014, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0015, code lost:
        if (r1 != null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x001c, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x001f, code lost:
        throw r3;
     */
    public Set<Integer> a() {
        Set<Integer> b2;
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                b2 = b(writableDatabase);
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't get all request ids", e);
                return new HashSet();
            }
        }
        return b2;
    }

    public void a(int i) {
        b(i, "localNotification");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
        if (r2 != null) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0030, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0031, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0034, code lost:
        throw r3;
     */
    public void a(b bVar) {
        ContentValues c = c(bVar);
        synchronized (this.a) {
            int d = bVar.d();
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                a(d, c, writableDatabase, "localNotificationShown");
                if (d(writableDatabase) > 10) {
                    e(writableDatabase);
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't update preference value:" + d, e);
            }
        }
    }

    public void a(a aVar) {
        a("localNotification", aVar);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0019, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001a, code lost:
        if (r1 != null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0020, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0021, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0024, code lost:
        throw r3;
     */
    public int b() {
        int c;
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                c = c(writableDatabase);
                a(c + 1, writableDatabase);
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't set next RequestId", e);
                return 0;
            }
        }
        return c;
    }

    public void b(int i) {
        b(i, "localNotificationShown");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        if (r2 != null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0023, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
        r5.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0027, code lost:
        throw r3;
     */
    public void b(b bVar) {
        int d = bVar.d();
        ContentValues c = c(bVar);
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                a(d, c, writableDatabase, "localNotification");
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't update preference value:" + d, e);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0015, code lost:
        if (r1 != null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001c, code lost:
        r4.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001f, code lost:
        throw r2;
     */
    public void c(int i) {
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                a(i + 1, writableDatabase);
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't set next RequestId", e);
            }
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
        a(sQLiteDatabase, "localNotification");
        a(sQLiteDatabase, "localNotificationShown");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
