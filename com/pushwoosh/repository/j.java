package com.pushwoosh.repository;

import android.net.Uri;
import com.pushwoosh.inapp.view.i.h.b;
import com.pushwoosh.notification.PushMessage;
import java.util.List;

public interface j {
    void a();

    void a(Uri uri);

    void a(PushMessage pushMessage);

    void b();

    List<b> c();

    List<Uri> d();
}
