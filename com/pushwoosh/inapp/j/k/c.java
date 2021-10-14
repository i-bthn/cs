package com.pushwoosh.inapp.j.k;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.util.Pair;
import com.pushwoosh.inapp.event.a;
import com.pushwoosh.inapp.j.l.b;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.d;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class c {
    private final Object a = new Object();
    private final com.pushwoosh.inapp.k.c b;
    private final b c = new b();
    private final Set<b> d = new ConcurrentSkipListSet();

    public c(com.pushwoosh.inapp.k.c cVar) {
        this.b = cVar;
    }

    private boolean a(b bVar, File file) {
        if (this.c.a(new Pair<>(file, bVar))) {
            return true;
        }
        file.delete();
        return false;
    }

    @Nullable
    private File b(b bVar, File file) {
        file.deleteOnExit();
        PWLog.noise("[InApp]InAppDownloader", "Start deploy:" + bVar.c());
        return d.a(file, this.b.d(bVar.c()));
    }

    private void b(String str) {
        File d2 = this.b.d(str);
        if (d2 != null && d2.exists()) {
            d.a(d2);
        }
    }

    private boolean b(b bVar) {
        StringBuilder sb;
        String str;
        b(bVar.c());
        File c2 = c(bVar);
        if (c2 == null) {
            sb = new StringBuilder();
            str = "Failed to download ";
        } else if (!a(bVar, c2)) {
            sb = new StringBuilder();
            str = "File is not valid ";
        } else if (b(bVar, c2) != null) {
            return true;
        } else {
            sb = new StringBuilder();
            str = "Failed to deploy ";
        }
        sb.append(str);
        sb.append(bVar.j());
        PWLog.error("[InApp]InAppDownloader", sb.toString());
        return false;
    }

    @Nullable
    private File c(b bVar) {
        PWLog.noise("[InApp]InAppDownloader", "Start download: " + bVar.c());
        EventBus.sendEvent(new a(a.EnumC0009a.DOWNLOADING_ZIP, bVar));
        File a2 = this.b.a();
        if (a2 == null) {
            return null;
        }
        File a3 = d.a(bVar.j(), new File(a2, bVar.c() + ".zip"));
        if (a3 == null) {
            return null;
        }
        EventBus.sendEvent(new a(a.EnumC0009a.DOWNLOADED_ZIP, bVar));
        return a3;
    }

    @WorkerThread
    public a a(List<b> list) {
        a aVar;
        a aVar2;
        if (list == null || list.isEmpty()) {
            return a.b();
        }
        ArrayList<b> arrayList = new ArrayList(list);
        Collections.sort(arrayList);
        this.d.addAll(arrayList);
        synchronized (this.a) {
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            ArrayList arrayList3 = new ArrayList();
            for (b bVar : arrayList) {
                if (b(bVar)) {
                    arrayList2.add(bVar);
                    PWLog.info("[InApp]InAppDownloader", bVar.c() + " deployed");
                    aVar2 = new a(a.EnumC0009a.DEPLOYED, bVar);
                } else {
                    arrayList3.add(bVar);
                    aVar2 = new a(a.EnumC0009a.DEPLOY_FAILED, bVar);
                }
                EventBus.sendEvent(aVar2);
                this.d.remove(bVar);
            }
            aVar = new a(arrayList2, arrayList3);
        }
        return aVar;
    }

    public void a(String str) {
        synchronized (this.a) {
            b(str);
        }
    }

    public boolean a(b bVar) {
        return this.d.contains(bVar);
    }
}
