package com.google.zxing.client.android.history;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.PreferencesActivity;
import com.google.zxing.client.android.result.ResultHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kotlin.text.Typography;

public final class HistoryManager {
    private static final String[] COLUMNS = {"text", "display", "format", "timestamp", "details"};
    private static final String[] COUNT_COLUMN = {"COUNT(1)"};
    private static final String[] ID_COL_PROJECTION = {"id"};
    private static final String[] ID_DETAIL_COL_PROJECTION = {"id", "details"};
    private static final int MAX_ITEMS = 2000;
    private static final String TAG = "HistoryManager";
    private final Activity activity;
    private final boolean enableHistory;

    public HistoryManager(Activity activity2) {
        this.activity = activity2;
        this.enableHistory = PreferenceManager.getDefaultSharedPreferences(activity2).getBoolean(PreferencesActivity.KEY_ENABLE_HISTORY, true);
    }

    public boolean hasHistoryItems() {
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor = null;
        try {
            sQLiteDatabase = new DBHelper(this.activity).getReadableDatabase();
            try {
                cursor = sQLiteDatabase.query("history", COUNT_COLUMN, null, null, null, null, null);
                cursor.moveToFirst();
                boolean z = false;
                if (cursor.getInt(0) > 0) {
                    z = true;
                }
                close(cursor, sQLiteDatabase);
                return z;
            } catch (Throwable th2) {
                th = th2;
                close(cursor, sQLiteDatabase);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            sQLiteDatabase = null;
            close(cursor, sQLiteDatabase);
            throw th;
        }
    }

    public List<HistoryItem> buildHistoryItems() {
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        DBHelper dBHelper = new DBHelper(this.activity);
        ArrayList arrayList = new ArrayList();
        try {
            sQLiteDatabase = dBHelper.getReadableDatabase();
            try {
                Cursor query = sQLiteDatabase.query("history", COLUMNS, null, null, null, null, "timestamp DESC");
                while (query.moveToNext()) {
                    String string = query.getString(0);
                    String string2 = query.getString(1);
                    String string3 = query.getString(2);
                    arrayList.add(new HistoryItem(new Result(string, null, null, BarcodeFormat.valueOf(string3), query.getLong(3)), string2, query.getString(4)));
                }
                close(query, sQLiteDatabase);
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                close(null, sQLiteDatabase);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            sQLiteDatabase = null;
            close(null, sQLiteDatabase);
            throw th;
        }
    }

    public HistoryItem buildHistoryItem(int i) {
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor = null;
        try {
            sQLiteDatabase = new DBHelper(this.activity).getReadableDatabase();
            try {
                cursor = sQLiteDatabase.query("history", COLUMNS, null, null, null, null, "timestamp DESC");
                cursor.move(i + 1);
                String string = cursor.getString(0);
                String string2 = cursor.getString(1);
                String string3 = cursor.getString(2);
                HistoryItem historyItem = new HistoryItem(new Result(string, null, null, BarcodeFormat.valueOf(string3), cursor.getLong(3)), string2, cursor.getString(4));
                close(cursor, sQLiteDatabase);
                return historyItem;
            } catch (Throwable th2) {
                th = th2;
                close(cursor, sQLiteDatabase);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            sQLiteDatabase = null;
            close(cursor, sQLiteDatabase);
            throw th;
        }
    }

    public void deleteHistoryItem(int i) {
        Throwable th;
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = new DBHelper(this.activity).getWritableDatabase();
            try {
                cursor = sQLiteDatabase.query("history", ID_COL_PROJECTION, null, null, null, null, "timestamp DESC");
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                close(cursor, sQLiteDatabase);
                throw th;
            }
            try {
                cursor.move(i + 1);
                sQLiteDatabase.delete("history", "id=" + cursor.getString(0), null);
                close(cursor, sQLiteDatabase);
            } catch (Throwable th3) {
                th = th3;
                close(cursor, sQLiteDatabase);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            sQLiteDatabase = null;
            cursor = null;
            close(cursor, sQLiteDatabase);
            throw th;
        }
    }

    public void addHistoryItem(Result result, ResultHandler resultHandler) {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        if (this.activity.getIntent().getBooleanExtra(Intents.Scan.SAVE_HISTORY, true) && !resultHandler.areContentsSecure() && this.enableHistory) {
            if (!PreferenceManager.getDefaultSharedPreferences(this.activity).getBoolean(PreferencesActivity.KEY_REMEMBER_DUPLICATES, false)) {
                deletePrevious(result.getText());
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("text", result.getText());
            contentValues.put("format", result.getBarcodeFormat().toString());
            contentValues.put("display", resultHandler.getDisplayContents().toString());
            contentValues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
            try {
                sQLiteDatabase = new DBHelper(this.activity).getWritableDatabase();
                try {
                    sQLiteDatabase.insert("history", "timestamp", contentValues);
                    close(null, sQLiteDatabase);
                } catch (Throwable th2) {
                    th = th2;
                    close(null, sQLiteDatabase);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                sQLiteDatabase = null;
                close(null, sQLiteDatabase);
                throw th;
            }
        }
    }

    public void addHistoryItemDetails(String str, String str2) {
        Throwable th;
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        String str3;
        String str4;
        try {
            sQLiteDatabase = new DBHelper(this.activity).getWritableDatabase();
            try {
                cursor = sQLiteDatabase.query("history", ID_DETAIL_COL_PROJECTION, "text=?", new String[]{str}, null, null, "timestamp DESC", "1");
                try {
                    if (cursor.moveToNext()) {
                        str4 = cursor.getString(0);
                        str3 = cursor.getString(1);
                    } else {
                        str4 = null;
                        str3 = null;
                    }
                    if (str4 != null) {
                        if (str3 != null) {
                            if (str3.contains(str2)) {
                                str2 = null;
                            } else {
                                str2 = str3 + " : " + str2;
                            }
                        }
                        if (str2 != null) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("details", str2);
                            sQLiteDatabase.update("history", contentValues, "id=?", new String[]{str4});
                        }
                    }
                    close(cursor, sQLiteDatabase);
                } catch (Throwable th2) {
                    th = th2;
                    close(cursor, sQLiteDatabase);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
                close(cursor, sQLiteDatabase);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
            sQLiteDatabase = null;
            close(cursor, sQLiteDatabase);
            throw th;
        }
    }

    private void deletePrevious(String str) {
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = new DBHelper(this.activity).getWritableDatabase();
            try {
                sQLiteDatabase.delete("history", "text=?", new String[]{str});
                close(null, sQLiteDatabase);
            } catch (Throwable th2) {
                th = th2;
                close(null, sQLiteDatabase);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            sQLiteDatabase = null;
            close(null, sQLiteDatabase);
            throw th;
        }
    }

    public void trimHistory() {
        Cursor cursor;
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        SQLiteException e;
        try {
            sQLiteDatabase = new DBHelper(this.activity).getWritableDatabase();
            try {
                cursor = sQLiteDatabase.query("history", ID_COL_PROJECTION, null, null, null, null, "timestamp DESC");
            } catch (SQLiteException e2) {
                cursor = null;
                e = e2;
                try {
                    Log.w(TAG, e);
                    close(cursor, sQLiteDatabase);
                } catch (Throwable th2) {
                    th = th2;
                    close(cursor, sQLiteDatabase);
                    throw th;
                }
            } catch (Throwable th3) {
                cursor = null;
                th = th3;
                close(cursor, sQLiteDatabase);
                throw th;
            }
            try {
                cursor.move(MAX_ITEMS);
                while (cursor.moveToNext()) {
                    String string = cursor.getString(0);
                    Log.i(TAG, "Deleting scan history ID " + string);
                    sQLiteDatabase.delete("history", "id=" + string, null);
                }
            } catch (SQLiteException e3) {
                e = e3;
                Log.w(TAG, e);
                close(cursor, sQLiteDatabase);
            }
        } catch (SQLiteException e4) {
            cursor = null;
            e = e4;
            sQLiteDatabase = null;
            Log.w(TAG, e);
            close(cursor, sQLiteDatabase);
        } catch (Throwable th4) {
            cursor = null;
            th = th4;
            sQLiteDatabase = null;
            close(cursor, sQLiteDatabase);
            throw th;
        }
        close(cursor, sQLiteDatabase);
    }

    /* access modifiers changed from: package-private */
    public CharSequence buildHistory() {
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = new DBHelper(this.activity).getWritableDatabase();
            try {
                Cursor query = sQLiteDatabase.query("history", COLUMNS, null, null, null, null, "timestamp DESC");
                DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(2, 2);
                StringBuilder sb = new StringBuilder(1000);
                while (query.moveToNext()) {
                    sb.append(Typography.quote);
                    sb.append(massageHistoryField(query.getString(0)));
                    sb.append("\",");
                    sb.append(Typography.quote);
                    sb.append(massageHistoryField(query.getString(1)));
                    sb.append("\",");
                    sb.append(Typography.quote);
                    sb.append(massageHistoryField(query.getString(2)));
                    sb.append("\",");
                    sb.append(Typography.quote);
                    sb.append(massageHistoryField(query.getString(3)));
                    sb.append("\",");
                    long j = query.getLong(3);
                    sb.append(Typography.quote);
                    sb.append(massageHistoryField(dateTimeInstance.format(new Date(j))));
                    sb.append("\",");
                    sb.append(Typography.quote);
                    sb.append(massageHistoryField(query.getString(4)));
                    sb.append("\"\r\n");
                }
                close(query, sQLiteDatabase);
                return sb;
            } catch (Throwable th2) {
                th = th2;
                close(null, sQLiteDatabase);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            sQLiteDatabase = null;
            close(null, sQLiteDatabase);
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void clearHistory() {
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = new DBHelper(this.activity).getWritableDatabase();
            try {
                sQLiteDatabase.delete("history", null, null);
                close(null, sQLiteDatabase);
            } catch (Throwable th2) {
                th = th2;
                close(null, sQLiteDatabase);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            sQLiteDatabase = null;
            close(null, sQLiteDatabase);
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ac A[SYNTHETIC, Splitter:B:23:0x00ac] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b3 A[SYNTHETIC, Splitter:B:29:0x00b3] */
    static Uri saveHistory(String str) {
        Throwable th;
        OutputStreamWriter outputStreamWriter;
        IOException e;
        File file = new File(new File(Environment.getExternalStorageDirectory(), "BarcodeScanner"), "History");
        if (file.exists() || file.mkdirs()) {
            File file2 = new File(file, "history-" + System.currentTimeMillis() + ".csv");
            try {
                outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file2), Charset.forName(Key.STRING_CHARSET_NAME));
                try {
                    outputStreamWriter.write(str);
                    Uri parse = Uri.parse("file://" + file2.getAbsolutePath());
                    try {
                        outputStreamWriter.close();
                    } catch (IOException unused) {
                    }
                    return parse;
                } catch (IOException e2) {
                    e = e2;
                    try {
                        Log.w(TAG, "Couldn't access file " + file2 + " due to " + e);
                        if (outputStreamWriter != null) {
                            try {
                                outputStreamWriter.close();
                            } catch (IOException unused2) {
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (outputStreamWriter != null) {
                            try {
                                outputStreamWriter.close();
                            } catch (IOException unused3) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e3) {
                e = e3;
                outputStreamWriter = null;
                Log.w(TAG, "Couldn't access file " + file2 + " due to " + e);
                if (outputStreamWriter != null) {
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                outputStreamWriter = null;
                if (outputStreamWriter != null) {
                }
                throw th;
            }
        } else {
            Log.w(TAG, "Couldn't make dir " + file);
            return null;
        }
    }

    private static String massageHistoryField(String str) {
        return str == null ? "" : str.replace("\"", "\"\"");
    }

    private static void close(Cursor cursor, SQLiteDatabase sQLiteDatabase) {
        if (cursor != null) {
            cursor.close();
        }
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }
}
