package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzbk;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzbv;
import com.google.android.gms.internal.measurement.zzel;
import com.google.android.gms.internal.measurement.zzey;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzx extends zzjh {
    private static final String[] zzek = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    private static final String[] zzel = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzem = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;"};
    private static final String[] zzen = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zzeo = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzep = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzeq = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzer = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzy zzes = new zzy(this, getContext(), "google_app_measurement.db");
    private final zzjd zzet = new zzjd(zzx());

    zzx(zzjg zzjg) {
        super(zzjg);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.measurement.internal.zzjh
    public final boolean zzbk() {
        return false;
    }

    @WorkerThread
    public final void beginTransaction() {
        zzbi();
        getWritableDatabase().beginTransaction();
    }

    @WorkerThread
    public final void setTransactionSuccessful() {
        zzbi();
        getWritableDatabase().setTransactionSuccessful();
    }

    @WorkerThread
    public final void endTransaction() {
        zzbi();
        getWritableDatabase().endTransaction();
    }

    @WorkerThread
    private final long zza(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                long j = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    private final long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                long j2 = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return j2;
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    @WorkerThread
    public final SQLiteDatabase getWritableDatabase() {
        zzo();
        try {
            return this.zzes.getWritableDatabase();
        } catch (SQLiteException e) {
            zzab().zzgn().zza("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x015b  */
    @WorkerThread
    public final zzae zzc(String str, String str2) {
        Cursor cursor;
        Throwable th;
        SQLiteException e;
        Boolean bool;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzo();
        zzbi();
        boolean zze = zzad().zze(str, zzak.zziz);
        ArrayList arrayList = new ArrayList(Arrays.asList("lifetime_count", "current_bundle_count", "last_fire_timestamp", "last_bundled_timestamp", "last_bundled_day", "last_sampled_complex_event_id", "last_sampling_rate", "last_exempt_from_sampling"));
        if (zze) {
            arrayList.add("current_session_count");
        }
        try {
            boolean z = false;
            Cursor query = getWritableDatabase().query("events", (String[]) arrayList.toArray(new String[0]), "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (!query.moveToFirst()) {
                    if (query != null) {
                        query.close();
                    }
                    return null;
                }
                long j = query.getLong(0);
                long j2 = query.getLong(1);
                long j3 = query.getLong(2);
                long j4 = 0;
                long j5 = query.isNull(3) ? 0 : query.getLong(3);
                Long valueOf = query.isNull(4) ? null : Long.valueOf(query.getLong(4));
                Long valueOf2 = query.isNull(5) ? null : Long.valueOf(query.getLong(5));
                Long valueOf3 = query.isNull(6) ? null : Long.valueOf(query.getLong(6));
                if (!query.isNull(7)) {
                    if (query.getLong(7) == 1) {
                        z = true;
                    }
                    bool = Boolean.valueOf(z);
                } else {
                    bool = null;
                }
                if (zze && !query.isNull(8)) {
                    j4 = query.getLong(8);
                }
                cursor = query;
                try {
                    zzae zzae = new zzae(str, str2, j, j2, j4, j3, j5, valueOf, valueOf2, valueOf3, bool);
                    if (cursor.moveToNext()) {
                        zzab().zzgk().zza("Got multiple records for event aggregates, expected one. appId", zzef.zzam(str));
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return zzae;
                } catch (SQLiteException e2) {
                    e = e2;
                    try {
                        zzab().zzgk().zza("Error querying events. appId", zzef.zzam(str), zzy().zzaj(str2), e);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null) {
                        }
                        throw th;
                    }
                }
            } catch (SQLiteException e3) {
                e = e3;
                cursor = query;
                zzab().zzgk().zza("Error querying events. appId", zzef.zzam(str), zzy().zzaj(str2), e);
                if (cursor != null) {
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                cursor = query;
                if (cursor != null) {
                }
                throw th;
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
            zzab().zzgk().zza("Error querying events. appId", zzef.zzam(str), zzy().zzaj(str2), e);
            if (cursor != null) {
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final void zza(zzae zzae) {
        Preconditions.checkNotNull(zzae);
        zzo();
        zzbi();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzae.zzce);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzae.name);
        contentValues.put("lifetime_count", Long.valueOf(zzae.zzfg));
        contentValues.put("current_bundle_count", Long.valueOf(zzae.zzfh));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzae.zzfj));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzae.zzfk));
        contentValues.put("last_bundled_day", zzae.zzfl);
        contentValues.put("last_sampled_complex_event_id", zzae.zzfm);
        contentValues.put("last_sampling_rate", zzae.zzfn);
        if (zzad().zze(zzae.zzce, zzak.zziz)) {
            contentValues.put("current_session_count", Long.valueOf(zzae.zzfi));
        }
        contentValues.put("last_exempt_from_sampling", (zzae.zzfo == null || !zzae.zzfo.booleanValue()) ? null : 1L);
        try {
            if (getWritableDatabase().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzab().zzgk().zza("Failed to insert/update event aggregates (got -1). appId", zzef.zzam(zzae.zzce));
            }
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing event aggregates. appId", zzef.zzam(zzae.zzce), e);
        }
    }

    @WorkerThread
    public final void zzd(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzo();
        zzbi();
        try {
            zzab().zzgs().zza("Deleted user attribute rows", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error deleting user attribute. appId", zzef.zzam(str), zzy().zzal(str2), e);
        }
    }

    @WorkerThread
    public final boolean zza(zzjp zzjp) {
        Preconditions.checkNotNull(zzjp);
        zzo();
        zzbi();
        if (zze(zzjp.zzce, zzjp.name) == null) {
            if (zzjs.zzbk(zzjp.name)) {
                if (zza("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzjp.zzce}) >= 25) {
                    return false;
                }
            } else if (!zzad().zze(zzjp.zzce, zzak.zzij)) {
                if (zza("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzjp.zzce, zzjp.origin}) >= 25) {
                    return false;
                }
            } else if (!"_npa".equals(zzjp.name)) {
                if (zza("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzjp.zzce, zzjp.origin}) >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzjp.zzce);
        contentValues.put("origin", zzjp.origin);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzjp.name);
        contentValues.put("set_timestamp", Long.valueOf(zzjp.zztr));
        zza(contentValues, "value", zzjp.value);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzab().zzgk().zza("Failed to insert/update user property (got -1). appId", zzef.zzam(zzjp.zzce));
            }
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing user property. appId", zzef.zzam(zzjp.zzce), e);
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a9  */
    @WorkerThread
    public final zzjp zze(String str, String str2) {
        Cursor cursor;
        Throwable th;
        SQLiteException e;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzo();
        zzbi();
        try {
            cursor = getWritableDatabase().query("user_attributes", new String[]{"set_timestamp", "value", "origin"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
                try {
                    zzjp zzjp = new zzjp(str, cursor.getString(2), str2, cursor.getLong(0), zza(cursor, 1));
                    if (cursor.moveToNext()) {
                        zzab().zzgk().zza("Got multiple records for user property, expected one. appId", zzef.zzam(str));
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return zzjp;
                } catch (SQLiteException e2) {
                    e = e2;
                    try {
                        zzab().zzgk().zza("Error querying user property. appId", zzef.zzam(str), zzy().zzal(str2), e);
                        if (cursor != null) {
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null) {
                        }
                        throw th;
                    }
                }
            } catch (SQLiteException e3) {
                e = e3;
                zzab().zzgk().zza("Error querying user property. appId", zzef.zzam(str), zzy().zzal(str2), e);
                if (cursor != null) {
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                }
                throw th;
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
            zzab().zzgk().zza("Error querying user property. appId", zzef.zzam(str), zzy().zzal(str2), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a3  */
    @WorkerThread
    public final List<zzjp> zzaa(String str) {
        Throwable th;
        Cursor cursor;
        SQLiteException e;
        Preconditions.checkNotEmpty(str);
        zzo();
        zzbi();
        ArrayList arrayList = new ArrayList();
        try {
            cursor = getWritableDatabase().query("user_attributes", new String[]{AppMeasurementSdk.ConditionalUserProperty.NAME, "origin", "set_timestamp", "value"}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
            try {
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                }
                do {
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    String str2 = string2 == null ? "" : string2;
                    long j = cursor.getLong(2);
                    Object zza = zza(cursor, 3);
                    if (zza == null) {
                        zzab().zzgk().zza("Read invalid user property value, ignoring it. appId", zzef.zzam(str));
                    } else {
                        arrayList.add(new zzjp(str, str2, string, j, zza));
                    }
                } while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzab().zzgk().zza("Error querying user properties. appId", zzef.zzam(str), e);
                    if (cursor != null) {
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            zzab().zzgk().zza("Error querying user properties. appId", zzef.zzam(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00f8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0100, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0104, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0100 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0127  */
    @WorkerThread
    public final List<zzjp> zza(String str, String str2, String str3) {
        Throwable th;
        Cursor cursor;
        String str4;
        SQLiteException e;
        Preconditions.checkNotEmpty(str);
        zzo();
        zzbi();
        ArrayList arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            ArrayList arrayList2 = new ArrayList(3);
            arrayList2.add(str);
            StringBuilder sb = new StringBuilder("app_id=?");
            if (!TextUtils.isEmpty(str2)) {
                str4 = str2;
                arrayList2.add(str4);
                sb.append(" and origin=?");
            } else {
                str4 = str2;
            }
            if (!TextUtils.isEmpty(str3)) {
                arrayList2.add(String.valueOf(str3).concat("*"));
                sb.append(" and name glob ?");
            }
            cursor = getWritableDatabase().query("user_attributes", new String[]{AppMeasurementSdk.ConditionalUserProperty.NAME, "set_timestamp", "value", "origin"}, sb.toString(), (String[]) arrayList2.toArray(new String[arrayList2.size()]), null, null, "rowid", "1001");
            try {
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                }
                while (true) {
                    if (arrayList.size() >= 1000) {
                        zzab().zzgk().zza("Read more than the max allowed user properties, ignoring excess", 1000);
                        break;
                    }
                    String string = cursor.getString(0);
                    long j = cursor.getLong(1);
                    try {
                        Object zza = zza(cursor, 2);
                        String string2 = cursor.getString(3);
                        if (zza == null) {
                            try {
                                zzab().zzgk().zza("(2)Read invalid user property value, ignoring it", zzef.zzam(str), string2, str3);
                            } catch (SQLiteException e2) {
                                e = e2;
                                str4 = string2;
                                try {
                                    zzab().zzgk().zza("(2)Error querying user properties", zzef.zzam(str), str4, e);
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    return null;
                                } catch (Throwable th2) {
                                    th = th2;
                                    cursor2 = cursor;
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    throw th;
                                }
                            }
                        } else {
                            arrayList.add(new zzjp(str, string2, string, j, zza));
                        }
                        if (!cursor.moveToNext()) {
                            break;
                        }
                        str4 = string2;
                    } catch (SQLiteException e3) {
                        e = e3;
                        zzab().zzgk().zza("(2)Error querying user properties", zzef.zzam(str), str4, e);
                        if (cursor != null) {
                        }
                        return null;
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (SQLiteException e4) {
                e = e4;
                zzab().zzgk().zza("(2)Error querying user properties", zzef.zzam(str), str4, e);
                if (cursor != null) {
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                cursor2 = cursor;
                if (cursor2 != null) {
                }
                throw th;
            }
        } catch (SQLiteException e5) {
            e = e5;
            str4 = str2;
            cursor = null;
            zzab().zzgk().zza("(2)Error querying user properties", zzef.zzam(str), str4, e);
            if (cursor != null) {
            }
            return null;
        } catch (Throwable th4) {
        }
    }

    @WorkerThread
    public final boolean zza(zzq zzq) {
        Preconditions.checkNotNull(zzq);
        zzo();
        zzbi();
        if (zze(zzq.packageName, zzq.zzdw.name) == null) {
            if (zza("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzq.packageName}) >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzq.packageName);
        contentValues.put("origin", zzq.origin);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzq.zzdw.name);
        zza(contentValues, "value", zzq.zzdw.getValue());
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzq.active));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzq.triggerEventName);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzq.triggerTimeout));
        zzz();
        contentValues.put("timed_out_event", zzjs.zza(zzq.zzdx));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzq.creationTimestamp));
        zzz();
        contentValues.put("triggered_event", zzjs.zza(zzq.zzdy));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzq.zzdw.zztr));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzq.timeToLive));
        zzz();
        contentValues.put("expired_event", zzjs.zza(zzq.zzdz));
        try {
            if (getWritableDatabase().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzab().zzgk().zza("Failed to insert/update conditional user property (got -1)", zzef.zzam(zzq.packageName));
            }
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing conditional user property", zzef.zzam(zzq.packageName), e);
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0125  */
    @WorkerThread
    public final zzq zzf(String str, String str2) {
        Cursor cursor;
        Throwable th;
        SQLiteException e;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzo();
        zzbi();
        try {
            cursor = getWritableDatabase().query("conditional_properties", new String[]{"origin", "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
                String string = cursor.getString(0);
                try {
                    Object zza = zza(cursor, 1);
                    boolean z = cursor.getInt(2) != 0;
                    zzq zzq = new zzq(str, string, new zzjn(str2, cursor.getLong(8), zza, string), cursor.getLong(6), z, cursor.getString(3), (zzai) zzgw().zza(cursor.getBlob(5), zzai.CREATOR), cursor.getLong(4), (zzai) zzgw().zza(cursor.getBlob(7), zzai.CREATOR), cursor.getLong(9), (zzai) zzgw().zza(cursor.getBlob(10), zzai.CREATOR));
                    if (cursor.moveToNext()) {
                        zzab().zzgk().zza("Got multiple records for conditional property, expected one", zzef.zzam(str), zzy().zzal(str2));
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return zzq;
                } catch (SQLiteException e2) {
                    e = e2;
                    try {
                        zzab().zzgk().zza("Error querying conditional property", zzef.zzam(str), zzy().zzal(str2), e);
                        if (cursor != null) {
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            } catch (SQLiteException e3) {
                e = e3;
                zzab().zzgk().zza("Error querying conditional property", zzef.zzam(str), zzy().zzal(str2), e);
                if (cursor != null) {
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                }
                throw th;
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
            zzab().zzgk().zza("Error querying conditional property", zzef.zzam(str), zzy().zzal(str2), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
    }

    @WorkerThread
    public final int zzg(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzo();
        zzbi();
        try {
            return getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error deleting conditional property", zzef.zzam(str), zzy().zzal(str2), e);
            return 0;
        }
    }

    @WorkerThread
    public final List<zzq> zzb(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzo();
        zzbi();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzb(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public final List<zzq> zzb(String str, String[] strArr) {
        zzo();
        zzbi();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().query("conditional_properties", new String[]{"app_id", "origin", AppMeasurementSdk.ConditionalUserProperty.NAME, "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"}, str, strArr, null, null, "rowid", "1001");
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            }
            while (true) {
                if (arrayList.size() < 1000) {
                    boolean z = false;
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    String string3 = cursor.getString(2);
                    Object zza = zza(cursor, 3);
                    if (cursor.getInt(4) != 0) {
                        z = true;
                    }
                    String string4 = cursor.getString(5);
                    long j = cursor.getLong(6);
                    long j2 = cursor.getLong(8);
                    arrayList.add(new zzq(string, string2, new zzjn(string3, cursor.getLong(10), zza, string2), j2, z, string4, (zzai) zzgw().zza(cursor.getBlob(7), zzai.CREATOR), j, (zzai) zzgw().zza(cursor.getBlob(9), zzai.CREATOR), cursor.getLong(11), (zzai) zzgw().zza(cursor.getBlob(12), zzai.CREATOR)));
                    if (!cursor.moveToNext()) {
                        break;
                    }
                } else {
                    zzab().zzgk().zza("Read more than the max allowed conditional properties, ignoring extra", 1000);
                    break;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error querying conditional user property value", e);
            List<zzq> emptyList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0119 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x011d A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0153 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0155 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0164 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0179 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0195 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0196 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x01a5 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01c0 A[Catch:{ SQLiteException -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01d3  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01fd  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0204  */
    @WorkerThread
    public final zzf zzab(String str) {
        Cursor cursor;
        Throwable th;
        SQLiteException e;
        boolean z;
        boolean z2;
        Preconditions.checkNotEmpty(str);
        zzo();
        zzbi();
        try {
            boolean z3 = true;
            cursor = getWritableDatabase().query("apps", new String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", "app_version", "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample", "android_id", "adid_reporting_enabled", "ssaid_reporting_enabled", "admob_app_id", "dynamite_version", "safelisted_events"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
                try {
                    zzf zzf = new zzf(this.zzkz.zzjt(), str);
                    zzf.zza(cursor.getString(0));
                    zzf.zzb(cursor.getString(1));
                    zzf.zzd(cursor.getString(2));
                    zzf.zzk(cursor.getLong(3));
                    zzf.zze(cursor.getLong(4));
                    zzf.zzf(cursor.getLong(5));
                    zzf.zzf(cursor.getString(6));
                    zzf.zzg(cursor.getString(7));
                    zzf.zzh(cursor.getLong(8));
                    zzf.zzi(cursor.getLong(9));
                    if (!cursor.isNull(10)) {
                        if (cursor.getInt(10) == 0) {
                            z = false;
                            zzf.setMeasurementEnabled(z);
                            zzf.zzn(cursor.getLong(11));
                            zzf.zzo(cursor.getLong(12));
                            zzf.zzp(cursor.getLong(13));
                            zzf.zzq(cursor.getLong(14));
                            zzf.zzl(cursor.getLong(15));
                            zzf.zzm(cursor.getLong(16));
                            zzf.zzg(!cursor.isNull(17) ? -2147483648L : (long) cursor.getInt(17));
                            zzf.zze(cursor.getString(18));
                            zzf.zzs(cursor.getLong(19));
                            zzf.zzr(cursor.getLong(20));
                            zzf.zzh(cursor.getString(21));
                            long j = 0;
                            zzf.zzt(!cursor.isNull(22) ? 0 : cursor.getLong(22));
                            if (!cursor.isNull(23)) {
                                if (cursor.getInt(23) == 0) {
                                    z2 = false;
                                    zzf.zzb(z2);
                                    if (!cursor.isNull(24)) {
                                        if (cursor.getInt(24) == 0) {
                                            z3 = false;
                                        }
                                    }
                                    zzf.zzc(z3);
                                    zzf.zzc(cursor.getString(25));
                                    if (!cursor.isNull(26)) {
                                        j = cursor.getLong(26);
                                    }
                                    zzf.zzj(j);
                                    if (!cursor.isNull(27)) {
                                        zzf.zza(Arrays.asList(cursor.getString(27).split(",", -1)));
                                    }
                                    zzf.zzaf();
                                    if (cursor.moveToNext()) {
                                        zzab().zzgk().zza("Got multiple records for app, expected one. appId", zzef.zzam(str));
                                    }
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    return zzf;
                                }
                            }
                            z2 = true;
                            zzf.zzb(z2);
                            if (!cursor.isNull(24)) {
                            }
                            zzf.zzc(z3);
                            zzf.zzc(cursor.getString(25));
                            if (!cursor.isNull(26)) {
                            }
                            zzf.zzj(j);
                            if (!cursor.isNull(27)) {
                            }
                            zzf.zzaf();
                            if (cursor.moveToNext()) {
                            }
                            if (cursor != null) {
                            }
                            return zzf;
                        }
                    }
                    z = true;
                    zzf.setMeasurementEnabled(z);
                    zzf.zzn(cursor.getLong(11));
                    zzf.zzo(cursor.getLong(12));
                    zzf.zzp(cursor.getLong(13));
                    zzf.zzq(cursor.getLong(14));
                    zzf.zzl(cursor.getLong(15));
                    zzf.zzm(cursor.getLong(16));
                    zzf.zzg(!cursor.isNull(17) ? -2147483648L : (long) cursor.getInt(17));
                    zzf.zze(cursor.getString(18));
                    zzf.zzs(cursor.getLong(19));
                    zzf.zzr(cursor.getLong(20));
                    zzf.zzh(cursor.getString(21));
                    long j2 = 0;
                    zzf.zzt(!cursor.isNull(22) ? 0 : cursor.getLong(22));
                    if (!cursor.isNull(23)) {
                    }
                    z2 = true;
                    zzf.zzb(z2);
                    if (!cursor.isNull(24)) {
                    }
                    zzf.zzc(z3);
                    zzf.zzc(cursor.getString(25));
                    if (!cursor.isNull(26)) {
                    }
                    zzf.zzj(j2);
                    if (!cursor.isNull(27)) {
                    }
                    zzf.zzaf();
                    if (cursor.moveToNext()) {
                    }
                    if (cursor != null) {
                    }
                    return zzf;
                } catch (SQLiteException e2) {
                    e = e2;
                    try {
                        zzab().zzgk().zza("Error querying app. appId", zzef.zzam(str), e);
                        if (cursor != null) {
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            } catch (SQLiteException e3) {
                e = e3;
                zzab().zzgk().zza("Error querying app. appId", zzef.zzam(str), e);
                if (cursor != null) {
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                }
                throw th;
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
            zzab().zzgk().zza("Error querying app. appId", zzef.zzam(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
    }

    @WorkerThread
    public final void zza(zzf zzf) {
        Preconditions.checkNotNull(zzf);
        zzo();
        zzbi();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzf.zzag());
        contentValues.put("app_instance_id", zzf.getAppInstanceId());
        contentValues.put("gmp_app_id", zzf.getGmpAppId());
        contentValues.put("resettable_device_id_hash", zzf.zzai());
        contentValues.put("last_bundle_index", Long.valueOf(zzf.zzar()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzf.zzaj()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzf.zzak()));
        contentValues.put("app_version", zzf.zzal());
        contentValues.put("app_store", zzf.zzan());
        contentValues.put("gmp_version", Long.valueOf(zzf.zzao()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzf.zzap()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzf.isMeasurementEnabled()));
        contentValues.put("day", Long.valueOf(zzf.zzav()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzf.zzaw()));
        contentValues.put("daily_events_count", Long.valueOf(zzf.zzax()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzf.zzay()));
        contentValues.put("config_fetched_time", Long.valueOf(zzf.zzas()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzf.zzat()));
        contentValues.put("app_version_int", Long.valueOf(zzf.zzam()));
        contentValues.put("firebase_instance_id", zzf.getFirebaseInstanceId());
        contentValues.put("daily_error_events_count", Long.valueOf(zzf.zzba()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzf.zzaz()));
        contentValues.put("health_monitor_sample", zzf.zzbb());
        contentValues.put("android_id", Long.valueOf(zzf.zzbd()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzf.zzbe()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(zzf.zzbf()));
        contentValues.put("admob_app_id", zzf.zzah());
        contentValues.put("dynamite_version", Long.valueOf(zzf.zzaq()));
        if (zzf.zzbh() != null) {
            if (zzf.zzbh().size() == 0) {
                zzab().zzgn().zza("Safelisted events should not be an empty list. appId", zzf.zzag());
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", zzf.zzbh()));
            }
        }
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (((long) writableDatabase.update("apps", contentValues, "app_id = ?", new String[]{zzf.zzag()})) == 0 && writableDatabase.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzab().zzgk().zza("Failed to insert/update app (got -1). appId", zzef.zzam(zzf.zzag()));
            }
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing app. appId", zzef.zzam(zzf.zzag()), e);
        }
    }

    public final long zzac(String str) {
        Preconditions.checkNotEmpty(str);
        zzo();
        zzbi();
        try {
            return (long) getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzad().zzb(str, zzak.zzgu))))});
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error deleting over the limit events. appId", zzef.zzam(str), e);
            return 0;
        }
    }

    @WorkerThread
    public final zzw zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Preconditions.checkNotEmpty(str);
        zzo();
        zzbi();
        String[] strArr = {str};
        zzw zzw = new zzw();
        Cursor cursor = null;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            Cursor query = writableDatabase.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
            if (!query.moveToFirst()) {
                zzab().zzgn().zza("Not updating daily counts, app is not known. appId", zzef.zzam(str));
                if (query != null) {
                    query.close();
                }
                return zzw;
            }
            if (query.getLong(0) == j) {
                zzw.zzeg = query.getLong(1);
                zzw.zzef = query.getLong(2);
                zzw.zzeh = query.getLong(3);
                zzw.zzei = query.getLong(4);
                zzw.zzej = query.getLong(5);
            }
            if (z) {
                zzw.zzeg++;
            }
            if (z2) {
                zzw.zzef++;
            }
            if (z3) {
                zzw.zzeh++;
            }
            if (z4) {
                zzw.zzei++;
            }
            if (z5) {
                zzw.zzej++;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("day", Long.valueOf(j));
            contentValues.put("daily_public_events_count", Long.valueOf(zzw.zzef));
            contentValues.put("daily_events_count", Long.valueOf(zzw.zzeg));
            contentValues.put("daily_conversions_count", Long.valueOf(zzw.zzeh));
            contentValues.put("daily_error_events_count", Long.valueOf(zzw.zzei));
            contentValues.put("daily_realtime_events_count", Long.valueOf(zzw.zzej));
            writableDatabase.update("apps", contentValues, "app_id=?", strArr);
            if (query != null) {
                query.close();
            }
            return zzw;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error updating daily counts. appId", zzef.zzam(str), e);
            if (0 != 0) {
                cursor.close();
            }
            return zzw;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0073  */
    @WorkerThread
    public final byte[] zzad(String str) {
        Throwable th;
        Cursor cursor;
        SQLiteException e;
        Preconditions.checkNotEmpty(str);
        zzo();
        zzbi();
        try {
            cursor = getWritableDatabase().query("apps", new String[]{"remote_config"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
                byte[] blob = cursor.getBlob(0);
                if (cursor.moveToNext()) {
                    zzab().zzgk().zza("Got multiple records for app config, expected one. appId", zzef.zzam(str));
                }
                if (cursor != null) {
                    cursor.close();
                }
                return blob;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzab().zzgk().zza("Error querying remote config. appId", zzef.zzam(str), e);
                    if (cursor != null) {
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            zzab().zzgk().zza("Error querying remote config. appId", zzef.zzam(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
    }

    @WorkerThread
    public final boolean zza(zzbs.zzg zzg, boolean z) {
        zzo();
        zzbi();
        Preconditions.checkNotNull(zzg);
        Preconditions.checkNotEmpty(zzg.zzag());
        Preconditions.checkState(zzg.zzof());
        zzca();
        long currentTimeMillis = zzx().currentTimeMillis();
        if (zzg.zznr() < currentTimeMillis - zzs.zzbs() || zzg.zznr() > zzs.zzbs() + currentTimeMillis) {
            zzab().zzgn().zza("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzef.zzam(zzg.zzag()), Long.valueOf(currentTimeMillis), Long.valueOf(zzg.zznr()));
        }
        try {
            byte[] zzc = zzgw().zzc(zzg.toByteArray());
            zzab().zzgs().zza("Saving bundle, size", Integer.valueOf(zzc.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzg.zzag());
            contentValues.put("bundle_end_timestamp", Long.valueOf(zzg.zznr()));
            contentValues.put("data", zzc);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzg.zzpn()) {
                contentValues.put("retry_count", Integer.valueOf(zzg.zzpo()));
            }
            try {
                if (getWritableDatabase().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzab().zzgk().zza("Failed to insert bundle (got -1). appId", zzef.zzam(zzg.zzag()));
                return false;
            } catch (SQLiteException e) {
                zzab().zzgk().zza("Error storing bundle. appId", zzef.zzam(zzg.zzag()), e);
                return false;
            }
        } catch (IOException e2) {
            zzab().zzgk().zza("Data loss. Failed to serialize bundle. appId", zzef.zzam(zzg.zzag()), e2);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0041  */
    @WorkerThread
    public final String zzby() {
        Throwable th;
        Cursor cursor;
        SQLiteException e;
        try {
            cursor = getWritableDatabase().rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
            try {
                if (cursor.moveToFirst()) {
                    String string = cursor.getString(0);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return string;
                }
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzab().zzgk().zza("Database error getting next bundle app id", e);
                    if (cursor != null) {
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            zzab().zzgk().zza("Database error getting next bundle app id", e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final boolean zzbz() {
        return zza("select count(1) > 0 from queue where has_realtime = 1", null) != 0;
    }

    @WorkerThread
    public final List<Pair<zzbs.zzg, Long>> zza(String str, int i, int i2) {
        zzo();
        zzbi();
        Preconditions.checkArgument(i > 0);
        Preconditions.checkArgument(i2 > 0);
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            Cursor query = getWritableDatabase().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i));
            if (!query.moveToFirst()) {
                List<Pair<zzbs.zzg, Long>> emptyList = Collections.emptyList();
                if (query != null) {
                    query.close();
                }
                return emptyList;
            }
            ArrayList arrayList = new ArrayList();
            int i3 = 0;
            do {
                long j = query.getLong(0);
                try {
                    byte[] zzb = zzgw().zzb(query.getBlob(1));
                    if (!arrayList.isEmpty() && zzb.length + i3 > i2) {
                        break;
                    }
                    try {
                        zzbs.zzg.zza zza = (zzbs.zzg.zza) zzbs.zzg.zzpr().zzf(zzb, zzel.zztq());
                        if (!query.isNull(2)) {
                            zza.zzw(query.getInt(2));
                        }
                        i3 += zzb.length;
                        arrayList.add(Pair.create((zzbs.zzg) ((zzey) zza.zzug()), Long.valueOf(j)));
                    } catch (IOException e) {
                        zzab().zzgk().zza("Failed to merge queued bundle. appId", zzef.zzam(str), e);
                    }
                    if (!query.moveToNext()) {
                        break;
                    }
                } catch (IOException e2) {
                    zzab().zzgk().zza("Failed to unzip queued bundle. appId", zzef.zzam(str), e2);
                }
            } while (i3 <= i2);
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (SQLiteException e3) {
            zzab().zzgk().zza("Error querying bundles. appId", zzef.zzam(str), e3);
            List<Pair<zzbs.zzg, Long>> emptyList2 = Collections.emptyList();
            if (0 != 0) {
                cursor.close();
            }
            return emptyList2;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzca() {
        int delete;
        zzo();
        zzbi();
        if (zzcg()) {
            long j = zzac().zzlm.get();
            long elapsedRealtime = zzx().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > zzak.zzhd.get(null).longValue()) {
                zzac().zzlm.set(elapsedRealtime);
                zzo();
                zzbi();
                if (zzcg() && (delete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzx().currentTimeMillis()), String.valueOf(zzs.zzbs())})) > 0) {
                    zzab().zzgs().zza("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    @WorkerThread
    public final void zzb(List<Long> list) {
        zzo();
        zzbi();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzcg()) {
            String join = TextUtils.join(",", list);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 80);
            sb3.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb3.append(sb2);
            sb3.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zza(sb3.toString(), (String[]) null) > 0) {
                zzab().zzgn().zzao("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                StringBuilder sb4 = new StringBuilder(String.valueOf(sb2).length() + 127);
                sb4.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb4.append(sb2);
                sb4.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
                writableDatabase.execSQL(sb4.toString());
            } catch (SQLiteException e) {
                zzab().zzgk().zza("Error incrementing retry count. error", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(String str, zzbv[] zzbvArr) {
        boolean z;
        zzbi();
        zzo();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzbvArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            zzbi();
            zzo();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase writableDatabase2 = getWritableDatabase();
            writableDatabase2.delete("property_filters", "app_id=?", new String[]{str});
            writableDatabase2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzbv zzbv : zzbvArr) {
                zzbi();
                zzo();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzbv);
                Preconditions.checkNotNull(zzbv.zzzh);
                Preconditions.checkNotNull(zzbv.zzzg);
                if (zzbv.zzzf == null) {
                    zzab().zzgn().zza("Audience with no ID. appId", zzef.zzam(str));
                } else {
                    int intValue = zzbv.zzzf.intValue();
                    zzbk.zza[] zzaArr = zzbv.zzzh;
                    int length = zzaArr.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            zzbk.zzd[] zzdArr = zzbv.zzzg;
                            int length2 = zzdArr.length;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= length2) {
                                    zzbk.zza[] zzaArr2 = zzbv.zzzh;
                                    int length3 = zzaArr2.length;
                                    int i3 = 0;
                                    while (true) {
                                        if (i3 >= length3) {
                                            z = true;
                                            break;
                                        } else if (!zza(str, intValue, zzaArr2[i3])) {
                                            z = false;
                                            break;
                                        } else {
                                            i3++;
                                        }
                                    }
                                    if (z) {
                                        zzbk.zzd[] zzdArr2 = zzbv.zzzg;
                                        int length4 = zzdArr2.length;
                                        int i4 = 0;
                                        while (true) {
                                            if (i4 >= length4) {
                                                break;
                                            } else if (!zza(str, intValue, zzdArr2[i4])) {
                                                z = false;
                                                break;
                                            } else {
                                                i4++;
                                            }
                                        }
                                    }
                                    if (!z) {
                                        zzbi();
                                        zzo();
                                        Preconditions.checkNotEmpty(str);
                                        SQLiteDatabase writableDatabase3 = getWritableDatabase();
                                        writableDatabase3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                                        writableDatabase3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                                    }
                                } else if (!zzdArr[i2].zzkb()) {
                                    zzab().zzgn().zza("Property filter with no ID. Audience definition ignored. appId, audienceId", zzef.zzam(str), zzbv.zzzf);
                                    break;
                                } else {
                                    i2++;
                                }
                            }
                        } else if (!zzaArr[i].zzkb()) {
                            zzab().zzgn().zza("Event filter with no ID. Audience definition ignored. appId, audienceId", zzef.zzam(str), zzbv.zzzf);
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzbv zzbv2 : zzbvArr) {
                arrayList.add(zzbv2.zzzf);
            }
            zza(str, arrayList);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzbk.zza zza) {
        zzbi();
        zzo();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zza);
        Integer num = null;
        if (TextUtils.isEmpty(zza.zzjz())) {
            zzeh zzgn = zzab().zzgn();
            Object zzam = zzef.zzam(str);
            Integer valueOf = Integer.valueOf(i);
            if (zza.zzkb()) {
                num = Integer.valueOf(zza.getId());
            }
            zzgn.zza("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzam, valueOf, String.valueOf(num));
            return false;
        }
        byte[] byteArray = zza.toByteArray();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zza.zzkb() ? Integer.valueOf(zza.getId()) : null);
        contentValues.put("event_name", zza.zzjz());
        if (zzad().zze(str, zzak.zziy)) {
            contentValues.put("session_scoped", zza.zzkh() ? Boolean.valueOf(zza.zzki()) : null);
        }
        contentValues.put("data", byteArray);
        try {
            if (getWritableDatabase().insertWithOnConflict("event_filters", null, contentValues, 5) != -1) {
                return true;
            }
            zzab().zzgk().zza("Failed to insert event filter (got -1). appId", zzef.zzam(str));
            return true;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing event filter. appId", zzef.zzam(str), e);
            return false;
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzbk.zzd zzd) {
        zzbi();
        zzo();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzd);
        Integer num = null;
        if (TextUtils.isEmpty(zzd.getPropertyName())) {
            zzeh zzgn = zzab().zzgn();
            Object zzam = zzef.zzam(str);
            Integer valueOf = Integer.valueOf(i);
            if (zzd.zzkb()) {
                num = Integer.valueOf(zzd.getId());
            }
            zzgn.zza("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzam, valueOf, String.valueOf(num));
            return false;
        }
        byte[] byteArray = zzd.toByteArray();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zzd.zzkb() ? Integer.valueOf(zzd.getId()) : null);
        contentValues.put("property_name", zzd.getPropertyName());
        if (zzad().zze(str, zzak.zziy)) {
            contentValues.put("session_scoped", zzd.zzkh() ? Boolean.valueOf(zzd.zzki()) : null);
        }
        contentValues.put("data", byteArray);
        try {
            if (getWritableDatabase().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                return true;
            }
            zzab().zzgk().zza("Failed to insert property filter (got -1). appId", zzef.zzam(str));
            return false;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing property filter. appId", zzef.zzam(str), e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ad  */
    public final Map<Integer, List<zzbk.zza>> zzh(String str, String str2) {
        Cursor cursor;
        Throwable th;
        SQLiteException e;
        zzbi();
        zzo();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        ArrayMap arrayMap = new ArrayMap();
        try {
            cursor = getWritableDatabase().query("event_filters", new String[]{"audience_id", "data"}, "app_id=? AND event_name=?", new String[]{str, str2}, null, null, null);
            try {
                if (!cursor.moveToFirst()) {
                    Map<Integer, List<zzbk.zza>> emptyMap = Collections.emptyMap();
                    if (cursor != null) {
                        cursor.close();
                    }
                    return emptyMap;
                }
                do {
                    try {
                        zzbk.zza zza = zzbk.zza.zza(cursor.getBlob(1), zzel.zztq());
                        int i = cursor.getInt(0);
                        List list = (List) arrayMap.get(Integer.valueOf(i));
                        if (list == null) {
                            list = new ArrayList();
                            arrayMap.put(Integer.valueOf(i), list);
                        }
                        list.add(zza);
                    } catch (IOException e2) {
                        zzab().zzgk().zza("Failed to merge filter. appId", zzef.zzam(str), e2);
                    }
                } while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
                return arrayMap;
            } catch (SQLiteException e3) {
                e = e3;
                try {
                    zzab().zzgk().zza("Database error querying filters. appId", zzef.zzam(str), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
            zzab().zzgk().zza("Database error querying filters. appId", zzef.zzam(str), e);
            if (cursor != null) {
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ad  */
    public final Map<Integer, List<zzbk.zzd>> zzi(String str, String str2) {
        Cursor cursor;
        Throwable th;
        SQLiteException e;
        zzbi();
        zzo();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        ArrayMap arrayMap = new ArrayMap();
        try {
            cursor = getWritableDatabase().query("property_filters", new String[]{"audience_id", "data"}, "app_id=? AND property_name=?", new String[]{str, str2}, null, null, null);
            try {
                if (!cursor.moveToFirst()) {
                    Map<Integer, List<zzbk.zzd>> emptyMap = Collections.emptyMap();
                    if (cursor != null) {
                        cursor.close();
                    }
                    return emptyMap;
                }
                do {
                    try {
                        zzbk.zzd zzb = zzbk.zzd.zzb(cursor.getBlob(1), zzel.zztq());
                        int i = cursor.getInt(0);
                        List list = (List) arrayMap.get(Integer.valueOf(i));
                        if (list == null) {
                            list = new ArrayList();
                            arrayMap.put(Integer.valueOf(i), list);
                        }
                        list.add(zzb);
                    } catch (IOException e2) {
                        zzab().zzgk().zza("Failed to merge filter", zzef.zzam(str), e2);
                    }
                } while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
                return arrayMap;
            } catch (SQLiteException e3) {
                e = e3;
                try {
                    zzab().zzgk().zza("Database error querying filters. appId", zzef.zzam(str), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
            zzab().zzgk().zza("Database error querying filters. appId", zzef.zzam(str), e);
            if (cursor != null) {
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0086  */
    public final Map<Integer, List<Integer>> zzae(String str) {
        Throwable th;
        Cursor cursor;
        SQLiteException e;
        zzbi();
        zzo();
        Preconditions.checkNotEmpty(str);
        ArrayMap arrayMap = new ArrayMap();
        try {
            cursor = getWritableDatabase().rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str, str});
            try {
                if (!cursor.moveToFirst()) {
                    Map<Integer, List<Integer>> emptyMap = Collections.emptyMap();
                    if (cursor != null) {
                        cursor.close();
                    }
                    return emptyMap;
                }
                do {
                    int i = cursor.getInt(0);
                    List list = (List) arrayMap.get(Integer.valueOf(i));
                    if (list == null) {
                        list = new ArrayList();
                        arrayMap.put(Integer.valueOf(i), list);
                    }
                    list.add(Integer.valueOf(cursor.getInt(1)));
                } while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
                return arrayMap;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzab().zzgk().zza("Database error querying scoped filters. appId", zzef.zzam(str), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            zzab().zzgk().zza("Database error querying scoped filters. appId", zzef.zzam(str), e);
            if (cursor != null) {
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
    }

    private final boolean zza(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzbi();
        zzo();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            long zza = zza("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min(2000, zzad().zzb(str, zzak.zzhk)));
            if (zza <= ((long) max)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 140);
            sb3.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb3.append(sb2);
            sb3.append(" order by rowid desc limit -1 offset ?)");
            return writableDatabase.delete("audience_filter_values", sb3.toString(), new String[]{str, Integer.toString(max)}) > 0;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Database error querying filters. appId", zzef.zzam(str), e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0093  */
    public final Map<Integer, zzbs.zzi> zzaf(String str) {
        Throwable th;
        Cursor cursor;
        SQLiteException e;
        zzbi();
        zzo();
        Preconditions.checkNotEmpty(str);
        try {
            cursor = getWritableDatabase().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
                ArrayMap arrayMap = new ArrayMap();
                do {
                    int i = cursor.getInt(0);
                    try {
                        arrayMap.put(Integer.valueOf(i), zzbs.zzi.zze(cursor.getBlob(1), zzel.zztq()));
                    } catch (IOException e2) {
                        zzab().zzgk().zza("Failed to merge filter results. appId, audienceId, error", zzef.zzam(str), Integer.valueOf(i), e2);
                    }
                } while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
                return arrayMap;
            } catch (SQLiteException e3) {
                e = e3;
                try {
                    zzab().zzgk().zza("Database error querying filter results. appId", zzef.zzam(str), e);
                    if (cursor != null) {
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
            zzab().zzgk().zza("Database error querying filter results. appId", zzef.zzam(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
    }

    @WorkerThread
    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    @VisibleForTesting
    @WorkerThread
    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                zzab().zzgk().zzao("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzab().zzgk().zzao("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzab().zzgk().zza("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
        }
    }

    @WorkerThread
    public final long zzcb() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    @WorkerThread
    public final long zzj(String str, String str2) {
        long j;
        SQLiteException e;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzo();
        zzbi();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 32);
            sb.append("select ");
            sb.append(str2);
            sb.append(" from app2 where app_id=?");
            j = zza(sb.toString(), new String[]{str}, -1);
            if (j == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", (Integer) 0);
                contentValues.put("previous_install_count", (Integer) 0);
                if (writableDatabase.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                    zzab().zzgk().zza("Failed to insert column (got -1). appId", zzef.zzam(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                j = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put(str2, Long.valueOf(1 + j));
                if (((long) writableDatabase.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    zzab().zzgk().zza("Failed to update column (got 0). appId", zzef.zzam(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                return j;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzab().zzgk().zza("Error inserting column. appId", zzef.zzam(str), str2, e);
                    return j;
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            j = 0;
            zzab().zzgk().zza("Error inserting column. appId", zzef.zzam(str), str2, e);
            return j;
        }
    }

    @WorkerThread
    public final long zzcc() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public final long zza(zzbs.zzg zzg) throws IOException {
        zzo();
        zzbi();
        Preconditions.checkNotNull(zzg);
        Preconditions.checkNotEmpty(zzg.zzag());
        byte[] byteArray = zzg.toByteArray();
        long zza = zzgw().zza(byteArray);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzg.zzag());
        contentValues.put("metadata_fingerprint", Long.valueOf(zza));
        contentValues.put("metadata", byteArray);
        try {
            getWritableDatabase().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
            return zza;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing raw event metadata. appId", zzef.zzam(zzg.zzag()), e);
            throw e;
        }
    }

    public final boolean zzcd() {
        return zza("select count(1) > 0 from raw_events", null) != 0;
    }

    public final boolean zzce() {
        return zza("select count(1) > 0 from raw_events where realtime = 1", null) != 0;
    }

    public final long zzag(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005b  */
    public final String zzu(long j) {
        Throwable th;
        Cursor cursor;
        SQLiteException e;
        zzo();
        zzbi();
        try {
            cursor = getWritableDatabase().rawQuery("select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;", new String[]{String.valueOf(j)});
            try {
                if (!cursor.moveToFirst()) {
                    zzab().zzgs().zzao("No expired configs for apps with pending events");
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
                String string = cursor.getString(0);
                if (cursor != null) {
                    cursor.close();
                }
                return string;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzab().zzgk().zza("Error selecting expired configs", e);
                    if (cursor != null) {
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            zzab().zzgk().zza("Error selecting expired configs", e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final long zzcf() {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return -1;
            }
            long j = cursor.getLong(0);
            if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
            return -1;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008a  */
    public final Pair<zzbs.zzc, Long> zza(String str, Long l) {
        Throwable th;
        Cursor cursor;
        SQLiteException e;
        zzo();
        zzbi();
        try {
            cursor = getWritableDatabase().rawQuery("select main_event, children_to_process from main_event_params where app_id=? and event_id=?", new String[]{str, String.valueOf(l)});
            try {
                if (!cursor.moveToFirst()) {
                    zzab().zzgs().zzao("Main event not found");
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
                try {
                    Pair<zzbs.zzc, Long> create = Pair.create(zzbs.zzc.zzc(cursor.getBlob(0), zzel.zztq()), Long.valueOf(cursor.getLong(1)));
                    if (cursor != null) {
                        cursor.close();
                    }
                    return create;
                } catch (IOException e2) {
                    zzab().zzgk().zza("Failed to merge main event. appId, eventId", zzef.zzam(str), l, e2);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
            } catch (SQLiteException e3) {
                e = e3;
                try {
                    zzab().zzgk().zza("Error selecting main event", e);
                    if (cursor != null) {
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
            zzab().zzgk().zza("Error selecting main event", e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final boolean zza(String str, Long l, long j, zzbs.zzc zzc) {
        zzo();
        zzbi();
        Preconditions.checkNotNull(zzc);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] byteArray = zzc.toByteArray();
        zzab().zzgs().zza("Saving complex main event, appId, data size", zzy().zzaj(str), Integer.valueOf(byteArray.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", byteArray);
        try {
            if (getWritableDatabase().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            zzab().zzgk().zza("Failed to insert complex main event (got -1). appId", zzef.zzam(str));
            return false;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing complex main event. appId", zzef.zzam(str), e);
            return false;
        }
    }

    public final boolean zza(zzaf zzaf, long j, boolean z) {
        zzo();
        zzbi();
        Preconditions.checkNotNull(zzaf);
        Preconditions.checkNotEmpty(zzaf.zzce);
        zzbs.zzc.zza zzah = zzbs.zzc.zzmq().zzah(zzaf.zzfp);
        Iterator<String> it = zzaf.zzfq.iterator();
        while (it.hasNext()) {
            String next = it.next();
            zzbs.zze.zza zzbz = zzbs.zze.zzng().zzbz(next);
            zzgw().zza(zzbz, zzaf.zzfq.get(next));
            zzah.zza(zzbz);
        }
        byte[] byteArray = ((zzbs.zzc) ((zzey) zzah.zzug())).toByteArray();
        zzab().zzgs().zza("Saving event, name, data size", zzy().zzaj(zzaf.name), Integer.valueOf(byteArray.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzaf.zzce);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzaf.name);
        contentValues.put("timestamp", Long.valueOf(zzaf.timestamp));
        contentValues.put("metadata_fingerprint", Long.valueOf(j));
        contentValues.put("data", byteArray);
        contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
        try {
            if (getWritableDatabase().insert("raw_events", null, contentValues) != -1) {
                return true;
            }
            zzab().zzgk().zza("Failed to insert raw event (got -1). appId", zzef.zzam(zzaf.zzce));
            return false;
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error storing raw event. appId", zzef.zzam(zzaf.zzce), e);
            return false;
        }
    }

    private final boolean zzcg() {
        return getContext().getDatabasePath("google_app_measurement.db").exists();
    }
}
