package com.pushwoosh.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import com.pushwoosh.internal.utils.JsonUtils;
import com.pushwoosh.internal.utils.PWLog;
import java.util.ArrayList;
import java.util.List;

public class PushBundleStorageImpl extends SQLiteOpenHelper implements m {
    private static final String b = "PushBundleStorageImpl";
    private final Object a = new Object();

    private static class Column {
        static final String PUSH_BUNDLE_JSON = "push_bundle_json";
        static final String ROW_ID = "rowid";

        private Column() {
        }
    }

    public PushBundleStorageImpl(Context context) {
        super(context, "pushBundleDb.db", (SQLiteDatabase.CursorFactory) null, 3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002e, code lost:
        if (r1 != null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0034, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0035, code lost:
        r6.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0038, code lost:
        throw r7;
     */
    private long a(Bundle bundle, String str) throws Exception {
        long insertWithOnConflict;
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                insertWithOnConflict = writableDatabase.insertWithOnConflict(str, null, c(bundle), 5);
                if (insertWithOnConflict == -1) {
                    PWLog.warn(b, "Push bundle with message was not stored.");
                    throw new Exception();
                } else if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Error occurred while storing push bundle", e);
                throw e;
            }
        }
        return insertWithOnConflict;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004f, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0050, code lost:
        if (r13 != null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0056, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0057, code lost:
        r1.addSuppressed(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005a, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x005e, code lost:
        if (r9 != null) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0064, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0065, code lost:
        r13.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0068, code lost:
        throw r1;
     */
    private Bundle a(long j, String str) throws Exception {
        Bundle a2;
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                Cursor query = writableDatabase.query(str, null, "rowid = ?", new String[]{Long.toString(j)}, null, null, null);
                if (query.moveToFirst()) {
                    a2 = a(query);
                    if (query != null) {
                        query.close();
                    }
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                } else {
                    PWLog.error("Can't get push bundle with id: " + j);
                    throw new Exception();
                }
            } catch (Exception e) {
                PWLog.error("Can't get push bundle with id: " + j, e);
                throw e;
            }
        }
        return a2;
    }

    private Bundle a(Cursor cursor) {
        return JsonUtils.jsonStringToBundle(cursor.getString(cursor.getColumnIndex("push_bundle_json")));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0034, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0035, code lost:
        if (r11 != null) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003b, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003c, code lost:
        r1.addSuppressed(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003f, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0042, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0043, code lost:
        if (r9 != null) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0049, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x004a, code lost:
        r11.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x004d, code lost:
        throw r1;
     */
    private List<Bundle> a(String str) {
        ArrayList arrayList;
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                Cursor query = writableDatabase.query(str, null, null, null, null, null, null);
                arrayList = new ArrayList();
                while (query.moveToNext()) {
                    arrayList.add(a(query));
                }
                if (query != null) {
                    query.close();
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't get group push bundles", e);
                throw e;
            }
        }
        return arrayList;
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL(String.format("create table %s (", str) + c() + ");");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        if (r1 != null) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0045, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        r5.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        throw r6;
     */
    private void b(long j, String str) {
        synchronized (this.a) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase.delete(str, "rowid=" + j, null) <= 0) {
                String str2 = b;
                PWLog.noise(str2, "failed to remove push bundle with id: " + j);
            }
            if (writableDatabase != null) {
                writableDatabase.close();
            }
        }
    }

    private void b(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
        a(sQLiteDatabase, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001e, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001f, code lost:
        if (r1 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0025, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0026, code lost:
        r4.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0029, code lost:
        throw r2;
     */
    private void b(String str) {
        synchronized (this.a) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase.delete(str, null, null) <= 0) {
                PWLog.noise(b, "failed to remove group push bundles");
            }
            if (writableDatabase != null) {
                writableDatabase.close();
            }
        }
    }

    private ContentValues c(Bundle bundle) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("push_bundle_json", JsonUtils.bundleToJson(bundle).toString());
        return contentValues;
    }

    private String c() {
        return String.format("%s TEXT ", "push_bundle_json");
    }

    @Override // com.pushwoosh.repository.m
    public long a(Bundle bundle) throws Exception {
        return a(bundle, "groupPushBundles");
    }

    @Override // com.pushwoosh.repository.m
    public void a() {
        b("groupPushBundles");
    }

    @Override // com.pushwoosh.repository.m
    public void a(long j) {
        b(j, "pushBundles");
    }

    @Override // com.pushwoosh.repository.m
    public long b(Bundle bundle) throws Exception {
        return a(bundle, "pushBundles");
    }

    @Override // com.pushwoosh.repository.m
    public List<Bundle> b() {
        return a("groupPushBundles");
    }

    @Override // com.pushwoosh.repository.m
    public void b(long j) {
        b(j, "groupPushBundles");
    }

    @Override // com.pushwoosh.repository.m
    public Bundle c(long j) throws Exception {
        return a(j, "pushBundles");
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase, "pushBundles");
        a(sQLiteDatabase, "groupPushBundles");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        b(sQLiteDatabase, "pushBundles");
        b(sQLiteDatabase, "groupPushBundles");
    }
}
