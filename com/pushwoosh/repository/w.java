package com.pushwoosh.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.pushwoosh.inapp.view.i.h.b;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.PushMessage;

public class w extends SQLiteOpenHelper implements v {
    private static final String b = "w";
    private final Object a = new Object();

    public w(Context context) {
        super(context, "silentRichMediaStorage.db", (SQLiteDatabase.CursorFactory) null, 2);
    }

    private ContentValues a(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("richMedia", str);
        contentValues.put("sound", str2);
        return contentValues;
    }

    private b a(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("richMedia"));
        return new b.C0014b().b(string).c(cursor.getString(cursor.getColumnIndex("sound"))).a(false).a();
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format("create table %s (", "resources") + String.format("%s TEXT , ", "richMedia") + String.format("%s TEXT ", "sound") + ");");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003b, code lost:
        if (r2 != null) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0041, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0042, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0045, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0048, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0049, code lost:
        if (r10 != null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x004f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0050, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0053, code lost:
        throw r3;
     */
    @Override // com.pushwoosh.repository.v
    @Nullable
    public b a() {
        b bVar;
        synchronized (this.a) {
            bVar = null;
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                Cursor query = writableDatabase.query("resources", null, null, null, null, null, null);
                if (query.moveToLast()) {
                    bVar = a(query);
                }
                if (!query.isClosed()) {
                    query.close();
                }
                if (query != null) {
                    query.close();
                }
                writableDatabase.execSQL("delete from resources");
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't get cached resources: ", e);
            }
        }
        return bVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0048, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0049, code lost:
        if (r2 != null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004f, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0050, code lost:
        r8.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0053, code lost:
        throw r3;
     */
    @Override // com.pushwoosh.repository.v
    public void a(PushMessage pushMessage) {
        synchronized (this.a) {
            String w = com.pushwoosh.notification.b.w(pushMessage.toBundle());
            String sound = pushMessage.getSound();
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase.insertWithOnConflict("resources", null, a(w, sound), 5) == -1) {
                    String str = b;
                    PWLog.warn(str, "Rich media " + w + " was not stored.");
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't cache richMedia resource: " + w, e);
            }
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS resources");
        a(sQLiteDatabase);
    }
}
