package com.pushwoosh.badge.e.a.e;

import android.database.Cursor;
import java.io.Closeable;
import java.io.IOException;

public class b {
    public static void a(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
