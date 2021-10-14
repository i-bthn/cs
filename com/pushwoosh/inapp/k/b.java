package com.pushwoosh.inapp.k;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ImagesContract;
import com.pushwoosh.inapp.j.l.a;
import com.pushwoosh.internal.utils.DbUtils;
import com.pushwoosh.internal.utils.PWLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class b extends SQLiteOpenHelper implements d {
    private final Object a = new Object();

    public b(Context context) {
        super(context, "inAppDb.db", (SQLiteDatabase.CursorFactory) null, 4);
    }

    @NonNull
    private com.pushwoosh.inapp.j.l.b a(Cursor cursor) {
        return new com.pushwoosh.inapp.j.l.b(cursor.getString(cursor.getColumnIndex("code")), cursor.getString(cursor.getColumnIndex(ImagesContract.URL)), "", cursor.getLong(cursor.getColumnIndex("updated")), a.a(cursor.getString(cursor.getColumnIndex("layout"))), null, cursor.getInt(cursor.getColumnIndex("required")) == 1, cursor.getInt(cursor.getColumnIndex("priority")), cursor.getString(cursor.getColumnIndex("businessCase")), cursor.getString(cursor.getColumnIndex("gdpr")));
    }

    private com.pushwoosh.inapp.j.l.b a(SQLiteDatabase sQLiteDatabase, com.pushwoosh.inapp.j.l.b bVar) {
        com.pushwoosh.inapp.j.l.b a2 = a(bVar.c(), sQLiteDatabase);
        if (a2 != null && a2.equals(bVar)) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ImagesContract.URL, bVar.j());
        contentValues.put("updated", Long.valueOf(bVar.i()));
        contentValues.put("layout", bVar.f().b());
        contentValues.put("priority", Integer.valueOf(bVar.g()));
        contentValues.put("required", Integer.valueOf(bVar.m() ? 1 : 0));
        contentValues.put("businessCase", bVar.b());
        contentValues.put("gdpr", bVar.d());
        if (sQLiteDatabase.updateWithOnConflict("inApps", contentValues, "code= ?", new String[]{bVar.c()}, 4) == 0) {
            contentValues.put("code", bVar.c());
            if (sQLiteDatabase.insert("inApps", null, contentValues) == 0) {
                PWLog.warn("InAppRetrieverWorker", "Not stored " + bVar.c());
                return null;
            }
        }
        return a2;
    }

    private com.pushwoosh.inapp.j.l.b a(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("inApps", null, "code = ?", DbUtils.getSelectionArgs(str), null, null, null);
        try {
            if (query.moveToFirst()) {
                return a(query);
            }
            query.close();
            return null;
        } finally {
            query.close();
        }
    }

    @NonNull
    private com.pushwoosh.inapp.j.l.b b(String str) {
        com.pushwoosh.inapp.j.l.b b;
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                try {
                    b = b(str, writableDatabase);
                } finally {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't download resource from db : " + str, e);
                return null;
            }
        }
        return b;
    }

    private com.pushwoosh.inapp.j.l.b b(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query("inApps", null, "gdpr= ?", DbUtils.getSelectionArgs(str), null, null, null);
        try {
            if (query.moveToFirst()) {
                return a(query);
            }
            query.close();
            return null;
        } finally {
            query.close();
        }
    }

    @Override // com.pushwoosh.inapp.k.d
    public com.pushwoosh.inapp.j.l.b a() {
        return b("Delete");
    }

    @Override // com.pushwoosh.inapp.k.d
    @Nullable
    public com.pushwoosh.inapp.j.l.b a(String str) {
        com.pushwoosh.inapp.j.l.b a2;
        if (str == null || TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                try {
                    a2 = a(str, writableDatabase);
                } finally {
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't download resource from db with code: " + str, e);
                return null;
            }
        }
        return a2;
    }

    @Override // com.pushwoosh.inapp.k.d
    public List<String> a(@Nullable List<com.pushwoosh.inapp.j.l.b> list) {
        if (list == null || list.size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                writableDatabase.beginTransaction();
                try {
                    for (com.pushwoosh.inapp.j.l.b bVar : list) {
                        com.pushwoosh.inapp.j.l.b a2 = a(writableDatabase, bVar);
                        if (a2 != null) {
                            arrayList.add(a2.c());
                        }
                    }
                    writableDatabase.setTransactionSuccessful();
                } finally {
                    writableDatabase.endTransaction();
                    writableDatabase.close();
                }
            } catch (Exception e) {
                PWLog.error("Can't update inApp database", e);
            }
        }
        return arrayList;
    }

    @Override // com.pushwoosh.inapp.k.d
    public com.pushwoosh.inapp.j.l.b b() {
        return b("Consent");
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format("create table %s (", "inApps") + String.format("%s text primary key, ", "code") + String.format("%s text, ", ImagesContract.URL) + String.format("%s text, ", "folder") + String.format("%s text, ", "layout") + String.format("%s integer, ", "updated") + String.format("%s integer default 0, ", "priority") + String.format("%s integer default 0, ", "required") + String.format("%s text, ", "businessCase") + String.format("%s text", "gdpr") + ");");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i != i2) {
            String format = String.format("ALTER TABLE %s ADD COLUMN ", "inApps");
            if (i < 2 && i2 >= 2) {
                sQLiteDatabase.execSQL(String.format(format + "%s INTEGER DEFAULT 0;", "priority"));
                sQLiteDatabase.execSQL(String.format(format + "%s INTEGER default 0;", "required"));
            }
            if (i < 3 && i2 >= 3) {
                sQLiteDatabase.execSQL(String.format(format + "%s TEXT;", "gdpr"));
            }
            if (i < 4 && i2 >= 4) {
                sQLiteDatabase.execSQL(String.format(format + "%s TEXT;", "businessCase"));
            }
        }
    }
}
