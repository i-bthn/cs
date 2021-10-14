package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzca implements zzce {
    @GuardedBy("ConfigurationContentLoader.class")
    static final Map<Uri, zzca> zzaah = new ArrayMap();
    private static final String[] zzaam = {"key", "value"};
    private final Uri uri;
    private final ContentResolver zzaai;
    private final Object zzaaj = new Object();
    private volatile Map<String, String> zzaak;
    @GuardedBy("this")
    private final List<zzcf> zzaal = new ArrayList();

    private zzca(ContentResolver contentResolver, Uri uri2) {
        this.zzaai = contentResolver;
        this.uri = uri2;
        this.zzaai.registerContentObserver(uri2, false, new zzcc(this, null));
    }

    public static zzca zza(ContentResolver contentResolver, Uri uri2) {
        zzca zzca;
        synchronized (zzca.class) {
            zzca = zzaah.get(uri2);
            if (zzca == null) {
                try {
                    zzca zzca2 = new zzca(contentResolver, uri2);
                    try {
                        zzaah.put(uri2, zzca2);
                        zzca = zzca2;
                    } catch (SecurityException unused) {
                        zzca = zzca2;
                    }
                } catch (SecurityException unused2) {
                }
            }
        }
        return zzca;
    }

    public final Map<String, String> zzre() {
        Map<String, String> map = this.zzaak;
        if (map == null) {
            synchronized (this.zzaaj) {
                map = this.zzaak;
                if (map == null) {
                    map = zzrg();
                    this.zzaak = map;
                }
            }
        }
        if (map != null) {
            return map;
        }
        return Collections.emptyMap();
    }

    public final void zzrf() {
        synchronized (this.zzaaj) {
            this.zzaak = null;
            zzcm.zzrl();
        }
        synchronized (this) {
            for (zzcf zzcf : this.zzaal) {
                zzcf.zzrk();
            }
        }
    }

    private final Map<String, String> zzrg() {
        try {
            return (Map) zzch.zza(new zzcd(this));
        } catch (SQLiteException | IllegalStateException | SecurityException unused) {
            Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
            return null;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzce
    public final /* synthetic */ Object zzdd(String str) {
        return zzre().get(str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Map zzrh() {
        Map map;
        Cursor query = this.zzaai.query(this.uri, zzaam, null, null, null);
        if (query == null) {
            return Collections.emptyMap();
        }
        try {
            int count = query.getCount();
            if (count == 0) {
                return Collections.emptyMap();
            }
            if (count <= 256) {
                map = new ArrayMap(count);
            } else {
                map = new HashMap(count, 1.0f);
            }
            while (query.moveToNext()) {
                map.put(query.getString(0), query.getString(1));
            }
            query.close();
            return map;
        } finally {
            query.close();
        }
    }
}
