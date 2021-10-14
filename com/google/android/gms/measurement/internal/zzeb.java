package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.SystemClock;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

public final class zzeb extends zzg {
    private final zzea zzjv = new zzea(this, getContext(), "google_app_measurement_local.db");
    private boolean zzjw;

    zzeb(zzfj zzfj) {
        super(zzfj);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zzbk() {
        return false;
    }

    @WorkerThread
    public final void resetAnalyticsData() {
        zzm();
        zzo();
        try {
            int delete = getWritableDatabase().delete("messages", null, null) + 0;
            if (delete > 0) {
                zzab().zzgs().zza("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error resetting local analytics data. error", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c6 A[SYNTHETIC, Splitter:B:48:0x00c6] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0119 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0119 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0119 A[SYNTHETIC] */
    @WorkerThread
    private final boolean zza(int i, byte[] bArr) {
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        SQLiteFullException e;
        SQLiteException e2;
        zzm();
        zzo();
        boolean z = false;
        if (this.zzjw) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put("entry", bArr);
        int i2 = 0;
        int i3 = 5;
        for (int i4 = 5; i2 < i4; i4 = 5) {
            Cursor cursor2 = null;
            cursor2 = null;
            r7 = null;
            SQLiteDatabase sQLiteDatabase2 = null;
            Cursor cursor3 = null;
            try {
                sQLiteDatabase = getWritableDatabase();
                if (sQLiteDatabase == null) {
                    try {
                        this.zzjw = true;
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                        return z;
                    } catch (SQLiteFullException e3) {
                        e = e3;
                    } catch (SQLiteDatabaseLockedException unused) {
                        try {
                            SystemClock.sleep((long) i3);
                            i3 += 20;
                            if (cursor3 != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            i2++;
                            z = false;
                        } catch (Throwable th2) {
                            th = th2;
                            cursor = cursor3;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            throw th;
                        }
                    } catch (SQLiteException e4) {
                        e2 = e4;
                        cursor = null;
                        sQLiteDatabase2 = sQLiteDatabase;
                        if (sQLiteDatabase2 != null) {
                        }
                        zzab().zzgk().zza("Error writing entry to local database", e2);
                        this.zzjw = true;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase2 != null) {
                        }
                        i2++;
                        z = false;
                    }
                } else {
                    sQLiteDatabase.beginTransaction();
                    long j = 0;
                    cursor = sQLiteDatabase.rawQuery("select count(1) from messages", null);
                    if (cursor != null) {
                        try {
                            if (cursor.moveToFirst()) {
                                int i5 = z ? 1 : 0;
                                int i6 = z ? 1 : 0;
                                int i7 = z ? 1 : 0;
                                j = cursor.getLong(i5);
                            }
                        } catch (SQLiteFullException e5) {
                            e = e5;
                            cursor2 = cursor;
                            zzab().zzgk().zza("Error writing entry to local database", e);
                            this.zzjw = true;
                            if (cursor2 != null) {
                            }
                            if (sQLiteDatabase == null) {
                            }
                            i2++;
                            z = false;
                        } catch (SQLiteDatabaseLockedException unused2) {
                            cursor3 = cursor;
                            SystemClock.sleep((long) i3);
                            i3 += 20;
                            if (cursor3 != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            i2++;
                            z = false;
                        } catch (SQLiteException e6) {
                            e2 = e6;
                            sQLiteDatabase2 = sQLiteDatabase;
                            if (sQLiteDatabase2 != null) {
                            }
                            zzab().zzgk().zza("Error writing entry to local database", e2);
                            this.zzjw = true;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase2 != null) {
                            }
                            i2++;
                            z = false;
                        } catch (Throwable th3) {
                            th = th3;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            throw th;
                        }
                    }
                    if (j >= 100000) {
                        zzab().zzgk().zzao("Data loss, local db full");
                        long j2 = (100000 - j) + 1;
                        String[] strArr = new String[1];
                        String l = Long.toString(j2);
                        char c = z ? 1 : 0;
                        char c2 = z ? 1 : 0;
                        char c3 = z ? 1 : 0;
                        strArr[c] = l;
                        long delete = (long) sQLiteDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", strArr);
                        if (delete != j2) {
                            zzab().zzgk().zza("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j2), Long.valueOf(delete), Long.valueOf(j2 - delete));
                        }
                    }
                    sQLiteDatabase.insertOrThrow("messages", null, contentValues);
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return true;
                }
            } catch (SQLiteFullException e7) {
                e = e7;
                sQLiteDatabase = null;
                zzab().zzgk().zza("Error writing entry to local database", e);
                this.zzjw = true;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (sQLiteDatabase == null) {
                    sQLiteDatabase.close();
                }
                i2++;
                z = false;
            } catch (SQLiteDatabaseLockedException unused3) {
                sQLiteDatabase = null;
                SystemClock.sleep((long) i3);
                i3 += 20;
                if (cursor3 != null) {
                    cursor3.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                i2++;
                z = false;
            } catch (SQLiteException e8) {
                e2 = e8;
                cursor = null;
                if (sQLiteDatabase2 != null) {
                    try {
                        if (sQLiteDatabase2.inTransaction()) {
                            sQLiteDatabase2.endTransaction();
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        sQLiteDatabase = sQLiteDatabase2;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase != null) {
                        }
                        throw th;
                    }
                }
                zzab().zzgk().zza("Error writing entry to local database", e2);
                this.zzjw = true;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase2 != null) {
                    sQLiteDatabase2.close();
                }
                i2++;
                z = false;
            } catch (Throwable th5) {
                th = th5;
                sQLiteDatabase = null;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                throw th;
            }
        }
        zzab().zzgn().zzao("Failed to write entry to local database");
        return false;
    }

    public final boolean zza(zzai zzai) {
        Parcel obtain = Parcel.obtain();
        zzai.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzab().zzgn().zzao("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzjn zzjn) {
        Parcel obtain = Parcel.obtain();
        zzjn.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzab().zzgn().zzao("User property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean zzc(zzq zzq) {
        zzz();
        byte[] zza = zzjs.zza(zzq);
        if (zza.length <= 131072) {
            return zza(2, zza);
        }
        zzab().zzgn().zzao("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:27:0x0051 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:149:? */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:177:0x0251 */
    /* JADX DEBUG: Multi-variable search result rejected for r21v0, resolved type: int */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x01e6, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x01e7, code lost:
        r12 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x01eb, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x01ec, code lost:
        r12 = r15;
        r21 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x01ef, code lost:
        r12 = r15;
        r21 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x01f2, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x01f3, code lost:
        r12 = r15;
        r21 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        r9 = null;
        r12 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
        r9 = null;
        r12 = r15;
        r21 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
        r9 = null;
        r12 = r15;
        r21 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        zzab().zzgk().zzao("Failed to load event from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00f8, code lost:
        r10.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00fb, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0115, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        zzab().zzgk().zzao("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0124, code lost:
        r10.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x012e, code lost:
        r10.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0131, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x014b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        zzab().zzgk().zzao("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x015a, code lost:
        r10.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0165, code lost:
        r10.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0168, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:56:0x00e7 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x0117 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:80:0x014d */
    /* JADX WARNING: Removed duplicated region for block: B:127:? A[ExcHandler: SQLiteDatabaseLockedException (unused android.database.sqlite.SQLiteDatabaseLockedException), SYNTHETIC, Splitter:B:12:0x0027] */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01ff A[SYNTHETIC, Splitter:B:137:0x01ff] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0219  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x021e  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x022c  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0231  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0249  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x024e  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0258  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0251 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0251 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0251 A[SYNTHETIC] */
    public final List<AbstractSafeParcelable> zzc(int i) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Throwable th;
        SQLiteFullException e;
        SQLiteException e2;
        SQLiteDatabase sQLiteDatabase2;
        SQLiteDatabase sQLiteDatabase3;
        SQLiteDatabase sQLiteDatabase4;
        String[] strArr;
        String str;
        zzo();
        zzm();
        if (this.zzjw) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!zzcg()) {
            return arrayList;
        }
        int i2 = 0;
        int i3 = 5;
        SQLiteDatabase sQLiteDatabase5 = i;
        while (i2 < 5) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase == null) {
                    try {
                        this.zzjw = true;
                        if (writableDatabase != null) {
                            writableDatabase.close();
                        }
                        return null;
                    } catch (SQLiteFullException e3) {
                        e = e3;
                        sQLiteDatabase2 = writableDatabase;
                        sQLiteDatabase = sQLiteDatabase2;
                        SQLiteDatabase sQLiteDatabase6 = sQLiteDatabase2;
                        cursor = null;
                        sQLiteDatabase5 = sQLiteDatabase6;
                        zzab().zzgk().zza("Error reading entries from local database", e);
                        this.zzjw = true;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase == null) {
                        }
                        i2++;
                        sQLiteDatabase5 = sQLiteDatabase5;
                    } catch (SQLiteDatabaseLockedException unused) {
                    } catch (SQLiteException e4) {
                        e2 = e4;
                        sQLiteDatabase3 = writableDatabase;
                        sQLiteDatabase = sQLiteDatabase3;
                        SQLiteDatabase sQLiteDatabase7 = sQLiteDatabase3;
                        cursor = null;
                        sQLiteDatabase5 = sQLiteDatabase7;
                        if (sQLiteDatabase != null) {
                        }
                        zzab().zzgk().zza("Error reading entries from local database", e2);
                        this.zzjw = true;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase != null) {
                        }
                        i2++;
                        sQLiteDatabase5 = sQLiteDatabase5;
                    } catch (Throwable th2) {
                        th = th2;
                        sQLiteDatabase4 = writableDatabase;
                        sQLiteDatabase = sQLiteDatabase4;
                        cursor = null;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase != null) {
                        }
                        throw th;
                    }
                } else {
                    writableDatabase.beginTransaction();
                    long j = -1;
                    if (zzad().zza(zzak.zzjd)) {
                        long zza = zza(writableDatabase);
                        if (zza != -1) {
                            strArr = new String[]{String.valueOf(zza)};
                            str = "rowid<?";
                        } else {
                            str = null;
                            strArr = null;
                        }
                        sQLiteDatabase5 = writableDatabase;
                        try {
                            cursor = writableDatabase.query("messages", new String[]{"rowid", "type", "entry"}, str, strArr, null, null, "rowid asc", Integer.toString(100));
                            sQLiteDatabase5 = sQLiteDatabase5;
                        } catch (SQLiteFullException e5) {
                            e = e5;
                            sQLiteDatabase2 = sQLiteDatabase5;
                            sQLiteDatabase = sQLiteDatabase2;
                            SQLiteDatabase sQLiteDatabase62 = sQLiteDatabase2;
                            cursor = null;
                            sQLiteDatabase5 = sQLiteDatabase62;
                            zzab().zzgk().zza("Error reading entries from local database", e);
                            this.zzjw = true;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase == null) {
                            }
                            i2++;
                            sQLiteDatabase5 = sQLiteDatabase5;
                        } catch (SQLiteDatabaseLockedException unused2) {
                            sQLiteDatabase = sQLiteDatabase5;
                            sQLiteDatabase5 = sQLiteDatabase5;
                            cursor = null;
                            SystemClock.sleep((long) i3);
                            i3 += 20;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            i2++;
                            sQLiteDatabase5 = sQLiteDatabase5;
                        } catch (SQLiteException e6) {
                            e2 = e6;
                            sQLiteDatabase3 = sQLiteDatabase5;
                            sQLiteDatabase = sQLiteDatabase3;
                            SQLiteDatabase sQLiteDatabase72 = sQLiteDatabase3;
                            cursor = null;
                            sQLiteDatabase5 = sQLiteDatabase72;
                            if (sQLiteDatabase != null) {
                            }
                            zzab().zzgk().zza("Error reading entries from local database", e2);
                            this.zzjw = true;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            i2++;
                            sQLiteDatabase5 = sQLiteDatabase5;
                        } catch (Throwable th3) {
                            th = th3;
                            sQLiteDatabase4 = sQLiteDatabase5;
                            sQLiteDatabase = sQLiteDatabase4;
                            cursor = null;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            throw th;
                        }
                    } else {
                        SQLiteDatabase sQLiteDatabase8 = writableDatabase;
                        cursor = sQLiteDatabase8.query("messages", new String[]{"rowid", "type", "entry"}, null, null, null, null, "rowid asc", Integer.toString(100));
                        sQLiteDatabase5 = sQLiteDatabase8;
                    }
                    while (cursor.moveToNext()) {
                        try {
                            j = cursor.getLong(0);
                            int i4 = cursor.getInt(1);
                            byte[] blob = cursor.getBlob(2);
                            if (i4 == 0) {
                                Parcel obtain = Parcel.obtain();
                                obtain.unmarshall(blob, 0, blob.length);
                                obtain.setDataPosition(0);
                                zzai createFromParcel = zzai.CREATOR.createFromParcel(obtain);
                                obtain.recycle();
                                if (createFromParcel != null) {
                                    arrayList.add(createFromParcel);
                                }
                            } else if (i4 == 1) {
                                Parcel obtain2 = Parcel.obtain();
                                obtain2.unmarshall(blob, 0, blob.length);
                                obtain2.setDataPosition(0);
                                zzjn zzjn = zzjn.CREATOR.createFromParcel(obtain2);
                                obtain2.recycle();
                                if (zzjn != null) {
                                    arrayList.add(zzjn);
                                }
                            } else if (i4 == 2) {
                                Parcel obtain3 = Parcel.obtain();
                                obtain3.unmarshall(blob, 0, blob.length);
                                obtain3.setDataPosition(0);
                                zzq zzq = zzq.CREATOR.createFromParcel(obtain3);
                                obtain3.recycle();
                                if (zzq != null) {
                                    arrayList.add(zzq);
                                }
                            } else if (i4 == 3) {
                                zzab().zzgn().zzao("Skipping app launch break");
                            } else {
                                zzab().zzgk().zzao("Unknown record type in local database");
                            }
                        } catch (SQLiteFullException e7) {
                            e = e7;
                            sQLiteDatabase = sQLiteDatabase5;
                            sQLiteDatabase5 = sQLiteDatabase5;
                            zzab().zzgk().zza("Error reading entries from local database", e);
                            this.zzjw = true;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase == null) {
                            }
                            i2++;
                            sQLiteDatabase5 = sQLiteDatabase5;
                        } catch (SQLiteDatabaseLockedException unused3) {
                            sQLiteDatabase = sQLiteDatabase5;
                            SystemClock.sleep((long) i3);
                            i3 += 20;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            i2++;
                            sQLiteDatabase5 = sQLiteDatabase5;
                        } catch (SQLiteException e8) {
                            e2 = e8;
                            sQLiteDatabase = sQLiteDatabase5;
                            sQLiteDatabase5 = sQLiteDatabase5;
                            if (sQLiteDatabase != null) {
                            }
                            zzab().zzgk().zza("Error reading entries from local database", e2);
                            this.zzjw = true;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            i2++;
                            sQLiteDatabase5 = sQLiteDatabase5;
                        } catch (Throwable th4) {
                            th = th4;
                            sQLiteDatabase = sQLiteDatabase5;
                            if (cursor != null) {
                            }
                            if (sQLiteDatabase != null) {
                            }
                            throw th;
                        }
                    }
                    sQLiteDatabase = sQLiteDatabase5;
                    try {
                        if (sQLiteDatabase.delete("messages", "rowid <= ?", new String[]{Long.toString(j)}) < arrayList.size()) {
                            zzab().zzgk().zzao("Fewer entries removed from local database than expected");
                        }
                        sQLiteDatabase.setTransactionSuccessful();
                        sQLiteDatabase.endTransaction();
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                        return arrayList;
                    } catch (SQLiteFullException e9) {
                        e = e9;
                        sQLiteDatabase5 = sQLiteDatabase5;
                        zzab().zzgk().zza("Error reading entries from local database", e);
                        this.zzjw = true;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase == null) {
                        }
                        i2++;
                        sQLiteDatabase5 = sQLiteDatabase5;
                    } catch (SQLiteDatabaseLockedException unused4) {
                        SystemClock.sleep((long) i3);
                        i3 += 20;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase != null) {
                        }
                        i2++;
                        sQLiteDatabase5 = sQLiteDatabase5;
                    } catch (SQLiteException e10) {
                        e2 = e10;
                        sQLiteDatabase5 = sQLiteDatabase5;
                        if (sQLiteDatabase != null) {
                        }
                        zzab().zzgk().zza("Error reading entries from local database", e2);
                        this.zzjw = true;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase != null) {
                        }
                        i2++;
                        sQLiteDatabase5 = sQLiteDatabase5;
                    }
                }
            } catch (SQLiteFullException e11) {
                e = e11;
                cursor = null;
                sQLiteDatabase = null;
                sQLiteDatabase5 = sQLiteDatabase5;
                zzab().zzgk().zza("Error reading entries from local database", e);
                this.zzjw = true;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase == null) {
                    sQLiteDatabase.close();
                }
                i2++;
                sQLiteDatabase5 = sQLiteDatabase5;
            } catch (SQLiteDatabaseLockedException unused5) {
                cursor = null;
                sQLiteDatabase = null;
                SystemClock.sleep((long) i3);
                i3 += 20;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                i2++;
                sQLiteDatabase5 = sQLiteDatabase5;
            } catch (SQLiteException e12) {
                e2 = e12;
                cursor = null;
                sQLiteDatabase = null;
                sQLiteDatabase5 = sQLiteDatabase5;
                if (sQLiteDatabase != null) {
                    try {
                        if (sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        if (cursor != null) {
                        }
                        if (sQLiteDatabase != null) {
                        }
                        throw th;
                    }
                }
                zzab().zzgk().zza("Error reading entries from local database", e2);
                this.zzjw = true;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                i2++;
                sQLiteDatabase5 = sQLiteDatabase5;
            } catch (Throwable th6) {
                th = th6;
                cursor = null;
                sQLiteDatabase = null;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                throw th;
            }
        }
        zzab().zzgn().zzao("Failed to read events from database in reasonable time");
        return null;
    }

    @WorkerThread
    public final boolean zzgh() {
        return zza(3, new byte[0]);
    }

    @WorkerThread
    public final boolean zzgi() {
        zzo();
        zzm();
        if (this.zzjw || !zzcg()) {
            return false;
        }
        int i = 5;
        for (int i2 = 0; i2 < 5; i2++) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase == null) {
                    this.zzjw = true;
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                    return false;
                }
                writableDatabase.beginTransaction();
                writableDatabase.delete("messages", "type == ?", new String[]{Integer.toString(3)});
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
                return true;
            } catch (SQLiteFullException e) {
                zzab().zzgk().zza("Error deleting app launch break from local database", e);
                this.zzjw = true;
                if (0 != 0) {
                    sQLiteDatabase.close();
                }
            } catch (SQLiteDatabaseLockedException unused) {
                SystemClock.sleep((long) i);
                i += 20;
                if (0 != 0) {
                    sQLiteDatabase.close();
                }
            } catch (SQLiteException e2) {
                if (0 != 0) {
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                }
                zzab().zzgk().zza("Error deleting app launch break from local database", e2);
                this.zzjw = true;
                if (0 != 0) {
                    sQLiteDatabase.close();
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    sQLiteDatabase.close();
                }
                throw th;
            }
        }
        zzab().zzgn().zzao("Error deleting app launch break from local database in reasonable time");
        return false;
    }

    private static long zza(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query("messages", new String[]{"rowid"}, "type=?", new String[]{"3"}, null, null, "rowid desc", "1");
            if (cursor.moveToFirst()) {
                return cursor.getLong(0);
            }
            if (cursor == null) {
                return -1;
            }
            cursor.close();
            return -1;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @VisibleForTesting
    @WorkerThread
    private final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        if (this.zzjw) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzjv.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzjw = true;
        return null;
    }

    @VisibleForTesting
    private final boolean zzcg() {
        return getContext().getDatabasePath("google_app_measurement_local.db").exists();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzl() {
        super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzm() {
        super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzn() {
        super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzo() {
        super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zza zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzgp zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzdy zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhv zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhq zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzeb zzu() {
        return super.zzu();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zziw zzv() {
        return super.zzv();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzac zzw() {
        return super.zzw();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ Clock zzx() {
        return super.zzx();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzed zzy() {
        return super.zzy();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzjs zzz() {
        return super.zzz();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzfc zzaa() {
        return super.zzaa();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzef zzab() {
        return super.zzab();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzeo zzac() {
        return super.zzac();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzs zzad() {
        return super.zzad();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzr zzae() {
        return super.zzae();
    }
}
