package com.pushwoosh.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import com.pushwoosh.inapp.view.i.h.b;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.notification.PushMessage;
import java.util.ArrayList;
import java.util.List;

public class LockScreenMediaStorageImpl extends SQLiteOpenHelper implements j {
    private static final String b = b.class.getSimpleName();
    private final Object a = new Object();

    private static class Column {
        static final String REMOTE_URL = "remoteUrl";
        static final String RICH_MEDIA = "richMedia";
        static final String SOUND = "sound";

        private Column() {
        }
    }

    public LockScreenMediaStorageImpl(Context context) {
        super(context, "lockScreenRichMediaResources.db", (SQLiteDatabase.CursorFactory) null, 2);
    }

    private ContentValues a(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("remoteUrl", str);
        return contentValues;
    }

    private ContentValues a(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("richMedia", str);
        contentValues.put("sound", str2);
        return contentValues;
    }

    private Uri a(Cursor cursor) {
        return Uri.parse(cursor.getString(cursor.getColumnIndex("remoteUrl")));
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format("create table %s (", "lockScreenRemoteUrls") + String.format("%s TEXT ", "remoteUrl") + ");");
    }

    private b b(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("richMedia"));
        return new b.C0014b().b(string).c(cursor.getString(cursor.getColumnIndex("sound"))).a(true).a();
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format("create table %s (", "lockScreenResources") + String.format("%s TEXT , ", "richMedia") + String.format("%s TEXT ", "sound") + ");");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        r3 = move-exception;
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
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001f, code lost:
        throw r3;
     */
    @Override // com.pushwoosh.repository.j
    public void a() {
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                writableDatabase.execSQL("delete from lockScreenRemoteUrls");
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't clear remote urls", e);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0050, code lost:
        if (r1 != null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0057, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005a, code lost:
        throw r3;
     */
    @Override // com.pushwoosh.repository.j
    public void a(Uri uri) {
        synchronized (this.a) {
            String uri2 = uri.toString();
            if (TextUtils.isEmpty(uri2)) {
                PWLog.warn(b, "Remote url is empty.");
                return;
            }
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase.insertWithOnConflict("lockScreenRemoteUrls", null, a(uri2), 5) == -1) {
                    String str = b;
                    PWLog.warn(str, "Remote url " + uri2 + " was not stored.");
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't cache remote url: " + uri2, e);
            }
        }
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
    @Override // com.pushwoosh.repository.j
    public void a(PushMessage pushMessage) {
        synchronized (this.a) {
            String w = com.pushwoosh.notification.b.w(pushMessage.toBundle());
            String sound = pushMessage.getSound();
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase.insertWithOnConflict("lockScreenResources", null, a(w, sound), 5) == -1) {
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

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        r3 = move-exception;
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
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001f, code lost:
        throw r3;
     */
    @Override // com.pushwoosh.repository.j
    public void b() {
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                writableDatabase.execSQL("delete from lockScreenResources");
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't clear resources", e);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003d, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003e, code lost:
        if (r2 != null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0044, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0045, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0048, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004b, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004c, code lost:
        if (r10 != null) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0052, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0053, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0056, code lost:
        throw r3;
     */
    @Override // com.pushwoosh.repository.j
    public List<b> c() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                Cursor query = writableDatabase.query("lockScreenResources", null, null, null, null, null, null);
                while (query.moveToNext()) {
                    arrayList.add(b(query));
                }
                if (!query.isClosed()) {
                    query.close();
                }
                if (query != null) {
                    query.close();
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't get cached resources: ", e);
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003d, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003e, code lost:
        if (r2 != null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0044, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0045, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0048, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004b, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004c, code lost:
        if (r10 != null) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0052, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0053, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0056, code lost:
        throw r3;
     */
    @Override // com.pushwoosh.repository.j
    public List<Uri> d() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                Cursor query = writableDatabase.query("lockScreenRemoteUrls", null, null, null, null, null, null);
                while (query.moveToNext()) {
                    arrayList.add(a(query));
                }
                if (!query.isClosed()) {
                    query.close();
                }
                if (query != null) {
                    query.close();
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't get cached resources: ", e);
            }
        }
        return arrayList;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        b(sQLiteDatabase);
        a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS lockScreenResources");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS lockScreenRemoteUrls");
        b(sQLiteDatabase);
        a(sQLiteDatabase);
    }
}
