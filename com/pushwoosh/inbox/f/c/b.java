package com.pushwoosh.inbox.f.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pushwoosh.inbox.data.InboxMessageType;
import com.pushwoosh.inbox.internal.data.InboxMessageSource;
import com.pushwoosh.inbox.internal.data.InboxMessageStatus;
import com.pushwoosh.inbox.internal.data.b;
import com.pushwoosh.internal.utils.DbUtils;
import com.pushwoosh.internal.utils.PWLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class b extends SQLiteOpenHelper {
    private final Object a = new Object();

    class a implements com.pushwoosh.inbox.a.a<Cursor, Collection<com.pushwoosh.inbox.internal.data.b>> {
        a() {
        }

        public Collection<com.pushwoosh.inbox.internal.data.b> a(Cursor cursor) {
            ArrayList arrayList = new ArrayList();
            if (cursor.moveToFirst()) {
                do {
                    arrayList.add(b.this.a((b) cursor));
                } while (cursor.moveToNext());
            }
            return arrayList;
        }
    }

    /* renamed from: com.pushwoosh.inbox.f.c.b$b  reason: collision with other inner class name */
    class C0018b implements com.pushwoosh.inbox.a.a<Cursor, Collection<com.pushwoosh.inbox.internal.data.b>> {
        C0018b() {
        }

        public Collection<com.pushwoosh.inbox.internal.data.b> a(Cursor cursor) {
            ArrayList arrayList = new ArrayList();
            if (cursor.moveToFirst()) {
                do {
                    arrayList.add(b.this.a((b) cursor));
                } while (cursor.moveToNext());
            }
            return arrayList;
        }
    }

    class c implements com.pushwoosh.inbox.a.a<Cursor, Integer> {
        c(b bVar) {
        }

        public Integer a(Cursor cursor) {
            return Integer.valueOf(cursor.moveToFirst() ? cursor.getInt(cursor.getColumnIndex("COUNT")) : -1);
        }
    }

    class d implements com.pushwoosh.inbox.a.a<Cursor, com.pushwoosh.inbox.internal.data.b> {
        d() {
        }

        public com.pushwoosh.inbox.internal.data.b a(Cursor cursor) {
            if (cursor.moveToFirst()) {
                return b.this.a((b) cursor);
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public class e implements com.pushwoosh.inbox.a.a<Cursor, List<com.pushwoosh.inbox.internal.data.b>> {
        e() {
        }

        public List<com.pushwoosh.inbox.internal.data.b> a(Cursor cursor) {
            ArrayList arrayList = new ArrayList();
            if (cursor.moveToFirst()) {
                do {
                    arrayList.add(b.this.a((b) cursor));
                } while (cursor.moveToNext());
            }
            return arrayList;
        }
    }

    /* access modifiers changed from: private */
    public static class f {
        static String a = "inboxMessage";
    }

    public b(Context context) {
        super(context, "PwInbox.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    @NonNull
    private ContentValues a(com.pushwoosh.inbox.internal.data.b bVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("inbox_id", bVar.d());
        contentValues.put("inbox_order", Long.valueOf(bVar.h()));
        contentValues.put("expired_date", Long.valueOf(bVar.b()));
        contentValues.put("send_date", Long.valueOf(bVar.getSendDate()));
        contentValues.put("title", bVar.getTitle());
        contentValues.put("hash", bVar.c());
        contentValues.put("message", bVar.getMessage());
        contentValues.put("image", bVar.e());
        contentValues.put("type", Integer.valueOf(bVar.g().getCode()));
        contentValues.put("action_params", bVar.a());
        contentValues.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(bVar.f().getCode()));
        contentValues.put(FirebaseAnalytics.Param.SOURCE, Integer.valueOf(bVar.j().getCode()));
        return contentValues;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private com.pushwoosh.inbox.internal.data.b a(Cursor cursor) {
        return new b.C0019b().d(cursor.getString(cursor.getColumnIndex("inbox_id"))).b(cursor.getLong(cursor.getColumnIndex("inbox_order"))).a(cursor.getLong(cursor.getColumnIndex("expired_date"))).c(cursor.getLong(cursor.getColumnIndex("send_date"))).g(cursor.getString(cursor.getColumnIndex("title"))).c(cursor.getString(cursor.getColumnIndex("hash"))).f(cursor.getString(cursor.getColumnIndex("message"))).e(cursor.getString(cursor.getColumnIndex("image"))).a(InboxMessageType.getByCode(cursor.getInt(cursor.getColumnIndex("type")))).a(cursor.getString(cursor.getColumnIndex("action_params"))).a(InboxMessageStatus.getByCode(cursor.getInt(cursor.getColumnIndex(NotificationCompat.CATEGORY_STATUS)))).a(InboxMessageSource.getByCode(cursor.getInt(cursor.getColumnIndex(FirebaseAnalytics.Param.SOURCE)))).a();
    }

    /* JADX INFO: finally extract failed */
    private <T> T a(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, com.pushwoosh.inbox.a.a<Cursor, T> aVar) {
        T a2;
        synchronized (this.a) {
            try {
                SQLiteDatabase readableDatabase = getReadableDatabase();
                try {
                    readableDatabase.beginTransaction();
                    Cursor query = readableDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
                    try {
                        a2 = aVar.a(query);
                        readableDatabase.setTransactionSuccessful();
                        query.close();
                        readableDatabase.endTransaction();
                    } catch (Throwable th) {
                        query.close();
                        readableDatabase.endTransaction();
                        throw th;
                    }
                } catch (Exception e2) {
                    PWLog.error("Failed work with db", e2);
                    return null;
                } finally {
                    readableDatabase.close();
                }
            } catch (Exception e3) {
                PWLog.error("Problem with db executing", e3);
                return null;
            }
        }
        return a2;
    }

    private List<String> a(List<String> list, SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        String str = f.a;
        Cursor query = sQLiteDatabase.query(str, new String[]{"inbox_id"}, "inbox_id NOT IN ( " + d(list) + " )", a(list, new String[0]), null, null, null);
        try {
            if (query.moveToFirst()) {
                do {
                    arrayList.add(query.getString(query.getColumnIndex("inbox_id")));
                } while (query.moveToNext());
            }
            return arrayList;
        } finally {
            query.close();
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete(f.a, "expired_date < ?", new String[]{String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()))});
    }

    private void a(SQLiteDatabase sQLiteDatabase, com.pushwoosh.inbox.internal.data.b bVar) {
        if (sQLiteDatabase.insert(f.a, null, a(bVar)) == 0) {
            PWLog.warn("Not stored " + bVar.d());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x007e A[Catch:{ all -> 0x00a5 }] */
    private void a(SQLiteDatabase sQLiteDatabase, com.pushwoosh.inbox.internal.data.b bVar, com.pushwoosh.inbox.f.b.a aVar) {
        boolean z;
        String[] strArr = {NotificationCompat.CATEGORY_STATUS, FirebaseAnalytics.Param.SOURCE};
        Cursor query = sQLiteDatabase.query(f.a, strArr, "inbox_id = ?", DbUtils.getSelectionArgs(bVar.d()), null, null, null);
        try {
            if (query.moveToFirst()) {
                InboxMessageStatus byCode = InboxMessageStatus.getByCode(query.getInt(query.getColumnIndex(NotificationCompat.CATEGORY_STATUS)));
                if (!(InboxMessageSource.getByCode(query.getInt(query.getColumnIndex(FirebaseAnalytics.Param.SOURCE))) == InboxMessageSource.PUSH || byCode == null)) {
                    if (!byCode.isLowerStatus(bVar.f())) {
                        if (bVar.f().isLowerStatus(byCode)) {
                            aVar.b().put(bVar.d(), byCode);
                            z = false;
                            ContentValues a2 = a(bVar);
                            if (!z) {
                                a2.remove(NotificationCompat.CATEGORY_STATUS);
                            }
                            String str = f.a;
                            sQLiteDatabase.update(str, a2, "inbox_id = ?", new String[]{bVar.d()});
                        }
                        z = true;
                        ContentValues a22 = a(bVar);
                        if (!z) {
                        }
                        String str2 = f.a;
                        sQLiteDatabase.update(str2, a22, "inbox_id = ?", new String[]{bVar.d()});
                    }
                }
                aVar.d().add(bVar.d());
                z = true;
                ContentValues a222 = a(bVar);
                if (!z) {
                }
                String str22 = f.a;
                sQLiteDatabase.update(str22, a222, "inbox_id = ?", new String[]{bVar.d()});
            } else {
                aVar.c().add(bVar.d());
                a(sQLiteDatabase, bVar);
            }
        } finally {
            query.close();
        }
    }

    private String[] a(List<String> list, String... strArr) {
        if (list == null && strArr == null) {
            return new String[0];
        }
        String[] strArr2 = new String[((list == null || strArr == null) ? list == null ? strArr.length : list.size() : list.size() + strArr.length)];
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                strArr2[i] = list.get(i);
            }
        }
        if (strArr != null) {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                strArr2[list != null ? list.size() + i2 : i2] = strArr[i2];
            }
        }
        return strArr2;
    }

    /* JADX INFO: finally extract failed */
    private void b(String str) {
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                try {
                    writableDatabase.beginTransaction();
                    try {
                        writableDatabase.execSQL(str);
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                    } catch (Throwable th) {
                        writableDatabase.endTransaction();
                        throw th;
                    }
                } finally {
                    writableDatabase.close();
                }
            } catch (Exception e2) {
                PWLog.error("Problem with db executing", e2);
            }
        }
    }

    private String d(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < collection.size(); i++) {
            sb.append("?,");
        }
        String sb2 = sb.toString();
        return sb2.substring(0, sb2.length() - 1);
    }

    @NonNull
    private static String e(Collection<String> collection) {
        return "DELETE FROM " + f.a + " WHERE " + "inbox_id" + " IN ('" + TextUtils.join("', '", collection) + "')";
    }

    private List<String> f(Collection<InboxMessageStatus> collection) {
        ArrayList arrayList = new ArrayList();
        for (InboxMessageStatus inboxMessageStatus : collection) {
            arrayList.add(String.valueOf(inboxMessageStatus.getCode()));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public com.pushwoosh.inbox.f.b.a a(Collection<com.pushwoosh.inbox.internal.data.b> collection, boolean z) {
        com.pushwoosh.inbox.f.b.a e2 = com.pushwoosh.inbox.f.b.a.e();
        ArrayList arrayList = new ArrayList();
        synchronized (this.a) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                try {
                    a(writableDatabase);
                    for (com.pushwoosh.inbox.internal.data.b bVar : collection) {
                        arrayList.add(bVar.d());
                        a(writableDatabase, bVar, e2);
                    }
                    if (z) {
                        e2.a().addAll(a(arrayList, writableDatabase));
                        writableDatabase.execSQL(e(e2.a()));
                    }
                    writableDatabase.setTransactionSuccessful();
                } finally {
                    writableDatabase.endTransaction();
                }
            } catch (Exception e3) {
                PWLog.error("Failed work with db", e3);
            } catch (Throwable th) {
                writableDatabase.close();
                throw th;
            }
            writableDatabase.close();
        }
        return e2;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public com.pushwoosh.inbox.internal.data.b a(String str) {
        return (com.pushwoosh.inbox.internal.data.b) a(f.a, null, "inbox_id = ? AND expired_date > ?", new String[]{str, String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()))}, null, null, null, null, new d());
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Integer a(Collection<InboxMessageStatus> collection) {
        return (Integer) a(f.a, new String[]{"count(*) AS COUNT"}, "status IN ( " + d(collection) + " ) AND " + "expired_date" + " > ?", a(f(collection), String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()))), null, null, null, null, new c(this));
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Collection<com.pushwoosh.inbox.internal.data.b> a(Collection<InboxMessageStatus> collection, long j, int i) {
        String str = f.a;
        return (Collection) a(str, null, "status IN ( " + d(collection) + " ) AND " + "expired_date" + " > ? AND " + "inbox_order" + " < ?", a(f(collection), String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())), String.valueOf(j)), null, null, "inbox_order DESC ", i == -1 ? null : String.valueOf(i), new C0018b());
    }

    /* access modifiers changed from: package-private */
    public Collection<String> a(Collection<String> collection, InboxMessageStatus inboxMessageStatus) {
        ArrayList arrayList = new ArrayList(collection);
        List<com.pushwoosh.inbox.internal.data.b> b = b(collection);
        if (b == null) {
            return Collections.emptyList();
        }
        for (com.pushwoosh.inbox.internal.data.b bVar : b) {
            if (bVar.f() == inboxMessageStatus) {
                arrayList.remove(bVar.d());
            }
        }
        if (arrayList.isEmpty()) {
            return Collections.emptyList();
        }
        b("UPDATE " + f.a + " SET " + NotificationCompat.CATEGORY_STATUS + " = " + inboxMessageStatus.getCode() + " WHERE " + "inbox_id" + " IN ('" + TextUtils.join("', '", arrayList) + "')");
        return arrayList;
    }

    /* JADX INFO: finally extract failed */
    public void a() {
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                try {
                    writableDatabase.beginTransaction();
                    try {
                        writableDatabase.delete(f.a, "", new String[0]);
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                    } catch (Throwable th) {
                        writableDatabase.endTransaction();
                        throw th;
                    }
                } finally {
                    writableDatabase.close();
                }
            } catch (Exception e2) {
                PWLog.error("Problem with db executing", e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Collection<com.pushwoosh.inbox.internal.data.b> b() {
        return (Collection) a(f.a, null, "source = ?", new String[]{String.valueOf(InboxMessageSource.PUSH.getCode())}, null, null, null, null, new a());
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public List<com.pushwoosh.inbox.internal.data.b> b(Collection<String> collection) {
        String str = f.a;
        return (List) a(str, null, "inbox_id IN ( " + d(collection) + " ) AND " + "expired_date" + " > ?", a(new ArrayList(collection), String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()))), null, null, "inbox_order DESC", null, new e());
    }

    /* access modifiers changed from: package-private */
    public void c(Collection<String> collection) {
        b(e(collection));
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE " + f.a + " (" + "inbox_id" + " text primary key, " + "inbox_order" + " integer default 0, " + "expired_date" + " integer default 0, " + "send_date" + " integer default 0, " + "title" + " text, " + "hash" + " text, " + "message" + " text, " + "image" + " text, " + "type" + " integer default -1, " + "action_params" + " text, " + NotificationCompat.CATEGORY_STATUS + " integer default -1, " + FirebaseAnalytics.Param.SOURCE + " integer default -1);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
