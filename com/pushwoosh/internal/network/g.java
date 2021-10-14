package com.pushwoosh.internal.network;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pushwoosh.internal.utils.DbUtils;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.i;
import org.json.JSONException;
import org.json.JSONObject;

public class g extends SQLiteOpenHelper {
    private static final String c = "g";
    private final Object a = new Object();
    private i b;

    public g(Context context, i iVar) {
        super(context, "request.db", (SQLiteDatabase.CursorFactory) null, 1);
        this.b = iVar;
    }

    private b a(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("requestId"));
        String string2 = cursor.getString(cursor.getColumnIndex(FirebaseAnalytics.Param.METHOD));
        String string3 = cursor.getString(cursor.getColumnIndex("body"));
        JSONObject jSONObject = new JSONObject();
        if (string3 != null) {
            try {
                jSONObject = new JSONObject(string3);
            } catch (JSONException e) {
                PWLog.error("Can't parse body of request: ", e);
            }
        }
        return new b(string, string2, jSONObject);
    }

    @NonNull
    private ContentValues b(PushRequest<?> pushRequest) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("requestId", this.b.a());
        contentValues.put(FirebaseAnalytics.Param.METHOD, pushRequest.getMethod());
        try {
            contentValues.put("body", pushRequest.a().toString());
        } catch (InterruptedException | JSONException e) {
            PWLog.error(c, "not valid body request:", e);
        }
        return contentValues;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001c, code lost:
        if (r1 != null) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0022, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0023, code lost:
        r5.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0026, code lost:
        throw r2;
     */
    public long a(PushRequest<?> pushRequest) {
        long insert;
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                insert = writableDatabase.insert("REQUEST", null, b(pushRequest));
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error(c, "error add request", e);
                return -1;
            }
        }
        return insert;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0045, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0046, code lost:
        if (r11 != null) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004c, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004d, code lost:
        r12.addSuppressed(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0050, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0053, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0054, code lost:
        if (r9 != null) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x005a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x005b, code lost:
        r11.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x005e, code lost:
        throw r12;
     */
    public b a(long j) {
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                Cursor query = writableDatabase.query("REQUEST", null, "rowid = ?", DbUtils.getSelectionArgs(String.valueOf(j)), null, null, null);
                if (query.moveToFirst()) {
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
                PWLog.error(c, "Can't get cached request: ", e);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0015, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0016, code lost:
        if (r1 != null) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x001d, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0020, code lost:
        throw r3;
     */
    public void a() {
        synchronized (this.a) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            writableDatabase.execSQL("delete from REQUEST");
            if (writableDatabase != null) {
                writableDatabase.close();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        if (r3 != null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0023, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0024, code lost:
        r4.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0027, code lost:
        throw r5;
     */
    public void a(String str) {
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                writableDatabase.delete("REQUEST", "requestId=?", new String[]{str});
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error(c, String.format("Can't remove cached request by key %s: ", str), e);
            }
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format("create table %s (%s TEXT primary key, %s TEXT, %s TEXT)", "REQUEST", "requestId", FirebaseAnalytics.Param.METHOD, "body"));
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
