package com.pushwoosh.inapp.view;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.view.a;
import java.lang.ref.WeakReference;

public class b extends Fragment implements a.AbstractC0011a {
    private AsyncTask<Void, Void, Result<com.pushwoosh.inapp.i.a, com.pushwoosh.inapp.g.a>> a;
    private WeakReference<AbstractC0012b> b = new WeakReference<>(null);
    private c c = c.NONE;
    private com.pushwoosh.inapp.i.a d;
    private com.pushwoosh.inapp.g.a e;

    /* access modifiers changed from: package-private */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a = new int[c.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            a[c.SUCCESS.ordinal()] = 1;
            a[c.ERROR.ordinal()] = 2;
            try {
                a[c.LOADING.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.pushwoosh.inapp.view.b$b  reason: collision with other inner class name */
    public interface AbstractC0012b {
        void a();

        void a(com.pushwoosh.inapp.g.a aVar);

        boolean a(com.pushwoosh.inapp.i.a aVar);

        void d();
    }

    public enum c {
        LOADING,
        SUCCESS,
        ERROR,
        NONE
    }

    public static b b(com.pushwoosh.inapp.j.l.b bVar) {
        b bVar2 = new b();
        Bundle bundle = new Bundle(1);
        bundle.putSerializable("keyInapp", bVar);
        bVar2.setArguments(bundle);
        return bVar2;
    }

    private void b(Result<com.pushwoosh.inapp.i.a, com.pushwoosh.inapp.g.a> result) {
        AbstractC0012b bVar = this.b.get();
        if (bVar != null) {
            if (!result.isSuccess()) {
                bVar.d();
                bVar.a(result.getException());
            } else if (!bVar.a(result.getData())) {
                bVar.d();
            }
        }
    }

    private void c(Result<com.pushwoosh.inapp.i.a, com.pushwoosh.inapp.g.a> result) {
        if (result.isSuccess()) {
            this.c = c.SUCCESS;
            this.d = result.getData();
            return;
        }
        this.c = c.ERROR;
        this.e = result.getException();
    }

    @Override // com.pushwoosh.inapp.view.a.AbstractC0011a
    public void a() {
        this.c = c.LOADING;
        AbstractC0012b bVar = this.b.get();
        if (bVar != null) {
            bVar.a();
        }
    }

    @Override // com.pushwoosh.inapp.view.a.AbstractC0011a
    public void a(Result<com.pushwoosh.inapp.i.a, com.pushwoosh.inapp.g.a> result) {
        c(result);
        b(result);
    }

    public void a(com.pushwoosh.inapp.j.l.b bVar) {
        this.a = new a(bVar, this);
        this.a.execute(new Void[0]);
    }

    public void b() {
        if (getActivity() instanceof AbstractC0012b) {
            this.b = new WeakReference<>((AbstractC0012b) getActivity());
        }
        AbstractC0012b bVar = this.b.get();
        if (bVar != null) {
            int i = a.a[this.c.ordinal()];
            if (i == 1) {
                bVar.d();
                bVar.a(this.d);
            } else if (i == 2) {
                bVar.a(this.e);
                bVar.d();
            } else if (i == 3) {
                bVar.a();
            } else if (getArguments() != null) {
                a((com.pushwoosh.inapp.j.l.b) getArguments().getSerializable("keyInapp"));
            }
        }
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        b();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        if (getArguments() != null) {
            com.pushwoosh.inapp.j.l.b bVar = (com.pushwoosh.inapp.j.l.b) getArguments().getSerializable("keyInapp");
            if (bundle == null) {
                a(bVar);
                return;
            }
            this.c = c.values()[bundle.getInt("[InApp]InAppFragment.key_STATE")];
            this.d = (com.pushwoosh.inapp.i.a) bundle.getSerializable("[InApp]InAppFragment.key_HTML_DATA");
            this.e = (com.pushwoosh.inapp.g.a) bundle.getSerializable("[InApp]InAppFragment.key_ERROR");
            c cVar = this.c;
            if (cVar != c.SUCCESS && cVar != c.ERROR) {
                a(bVar);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        AsyncTask<Void, Void, Result<com.pushwoosh.inapp.i.a, com.pushwoosh.inapp.g.a>> asyncTask = this.a;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.a = null;
        }
    }

    public void onDetach() {
        super.onDetach();
        this.b = null;
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("[InApp]InAppFragment.key_ERROR", this.e);
        bundle.putSerializable("[InApp]InAppFragment.key_HTML_DATA", this.d);
        bundle.putInt("[InApp]InAppFragment.key_STATE", this.c.ordinal());
    }
}
