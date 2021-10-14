package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;

/* access modifiers changed from: package-private */
@VisibleForTesting
public final class zzea extends SQLiteOpenHelper {
    private final /* synthetic */ zzeb zzju;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzea(zzeb zzeb, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.zzju = zzeb;
    }

    @WorkerThread
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @WorkerThread
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @WorkerThread
    public final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        try {
            return super.getWritableDatabase();
        } catch (SQLiteDatabaseLockedException e) {
            throw e;
        } catch (SQLiteException unused) {
            this.zzju.zzab().zzgk().zzao("Opening the local database failed, dropping and recreating it");
            if (!this.zzju.getContext().getDatabasePath("google_app_measurement_local.db").delete()) {
                this.zzju.zzab().zzgk().zza("Failed to delete corrupted local db file", "google_app_measurement_local.db");
            }
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e2) {
                this.zzju.zzab().zzgk().zza("Failed to open local database. Events will bypass local storage", e2);
                return null;
            }
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:3:0x0009 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.database.Cursor] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @WorkerThread
    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        if (Build.VERSION.SDK_INT < 15) {
            ?? r0 = 0;
            try {
                r0 = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", r0);
                r0.moveToFirst();
            } finally {
                if (r0 != 0) {
                    r0.close();
                }
            }
        }
        zzab.zza(this.zzju.zzab(), sQLiteDatabase, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", null);
    }

    @WorkerThread
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzab.zza(this.zzju.zzab(), sQLiteDatabase);
    }
}
