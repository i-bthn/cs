package com.pushwoosh;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import com.pushwoosh.internal.platform.utils.a;

public class PushwooshSharedDataProvider extends ContentProvider {
    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    @Nullable
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        if (getContext() == null) {
            return null;
        }
        UriMatcher uriMatcher = new UriMatcher(-1);
        uriMatcher.addURI(getContext().getApplicationContext().getPackageName() + "." + PushwooshSharedDataProvider.class.getSimpleName(), "hwid", 1);
        if (uriMatcher.match(uri) != 1) {
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"hwid"});
        if (str2 != null) {
            matrixCursor.addRow(str2.compareTo(GeneralUtils.md5(getContext().getApplicationContext().getPackageName())) < 0 ? new String[]{a.g()} : new String[]{a.i()});
        }
        return matrixCursor;
    }

    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }
}
