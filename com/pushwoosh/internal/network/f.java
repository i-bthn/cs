package com.pushwoosh.internal.network;

import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.RegistrationPrefs;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class f implements RequestManager {
    private final RegistrationPrefs a;
    private String b;
    private boolean c = false;
    private com.pushwoosh.repository.config.b d;

    private static class a extends AsyncTask<Void, Void, Void> {
        private final WeakReference<f> a;
        private final String b;

        public a(f fVar, String str) {
            this.a = new WeakReference<>(fVar);
            this.b = str;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            if (this.a.get() == null) {
                return null;
            }
            this.a.get().a(0, 0, null, null, this.b, 0, null);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public static class b {
        private int a;
        private int b;
        private JSONObject c;
        private JSONObject d;

        b(int i, int i2, JSONObject jSONObject, JSONObject jSONObject2) {
            this.b = i;
            this.a = i2;
            this.c = jSONObject;
            this.d = jSONObject2;
        }

        /* access modifiers changed from: package-private */
        public int a() {
            return this.a;
        }

        /* access modifiers changed from: package-private */
        public JSONObject b() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public int c() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public boolean d() {
            int i;
            int i2 = this.b;
            return i2 >= 200 && i2 < 300 && (i = this.a) >= 200 && i < 300;
        }
    }

    /* access modifiers changed from: private */
    public static class c<Response> extends AsyncTask<Void, Void, Result<Response, NetworkException>> {
        private final WeakReference<f> a;
        private final PushRequest<Response> b;
        private final String c;
        private final Callback<Response, NetworkException> d;

        c(f fVar, PushRequest<Response> pushRequest, String str, Callback<Response, NetworkException> callback) {
            this.a = new WeakReference<>(fVar);
            this.b = pushRequest;
            this.c = str;
            this.d = callback;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Result<Response, NetworkException> doInBackground(Void... voidArr) {
            if (this.a.get() != null) {
                return this.a.get().a(this.b, this.c);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Result<Response, NetworkException> result) {
            Callback<Response, NetworkException> callback;
            super.onPostExecute(result);
            if (result != null && (callback = this.d) != null) {
                callback.process(result);
            }
        }
    }

    f(RegistrationPrefs registrationPrefs, @Nullable com.pushwoosh.repository.config.b bVar) {
        this.a = registrationPrefs;
        this.d = bVar;
        this.b = registrationPrefs.baseUrl().get();
        if (Build.VERSION.SDK_INT <= 19) {
            try {
                HttpsURLConnection.setDefaultSSLSocketFactory(new h());
            } catch (Exception e) {
                PWLog.error("RequestManager", "ERROR: " + e.getMessage(), e);
            }
        }
    }

    private <Response> Result<Response, NetworkException> a(PushRequest<Response> pushRequest, String str, int i) {
        PushwooshException pushwooshException;
        if (str == null) {
            str = this.b;
        }
        if (!a(pushRequest)) {
            PWLog.debug("RequestManager", "Try To send: " + pushRequest.getMethod() + "; baseUrl: " + str);
        }
        long time = new Date().getTime();
        try {
            b a2 = a(str, pushRequest.a(), pushRequest.getMethod(), a(pushRequest));
            if (pushRequest instanceof e) {
                return Result.fromData(null);
            }
            a(time, i, str, pushRequest, a2);
            if (200 == a2.c() && 200 == a2.a()) {
                if (!a(pushRequest)) {
                    PWLog.debug("RequestManager", pushRequest.getMethod() + " response success");
                }
                JSONObject b2 = a2.b();
                if (b2.has("base_url") && str.equals(this.b) && !this.c) {
                    a(b2.optString("base_url"));
                }
                JSONObject optJSONObject = b2.optJSONObject("response");
                if (optJSONObject == null) {
                    optJSONObject = new JSONObject();
                }
                return Result.fromData(pushRequest.parseResponse(optJSONObject));
            }
            pushwooshException = new NetworkException(a2.b().toString());
            if (!a(pushRequest)) {
                PWLog.error("RequestManager", "ERROR: " + pushwooshException.getMessage(), pushwooshException);
            }
            return Result.fromException(pushwooshException);
        } catch (Exception e) {
            if (pushRequest instanceof e) {
                return Result.fromData(null);
            }
            a(time, i, str, pushRequest, e.getClass().getCanonicalName() + ": " + e.getLocalizedMessage());
            pushwooshException = new c(e.getMessage());
        }
    }

    /* JADX INFO: finally extract failed */
    private b a(String str, JSONObject jSONObject, String str2, boolean z) throws Exception {
        try {
            URL url = new URL(str + str2);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            httpURLConnection.setDoOutput(true);
            if (TextUtils.equals(str, "https://post-log.pushwoosh.com/")) {
                httpURLConnection.setConnectTimeout(60000);
            } else {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("request", jSONObject);
                jSONObject = jSONObject2;
            }
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(jSONObject.toString().getBytes().length));
            httpURLConnection.setUseCaches(false);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            try {
                outputStream.write(jSONObject.toString().getBytes());
                outputStream.flush();
                outputStream.close();
                String b2 = b(httpURLConnection);
                if (!z) {
                    PWLog.info("RequestManager", "\nx\n|     Pushwoosh request:\n| Url: " + url.toString() + "\n| Payload: " + jSONObject.toString() + "\n| Response: " + b2 + "\nx");
                }
                JSONObject jSONObject3 = new JSONObject(b2);
                return new b(httpURLConnection.getResponseCode(), jSONObject3.getInt("status_code"), jSONObject3, a(httpURLConnection));
            } catch (Throwable th) {
                outputStream.close();
                throw th;
            }
        } catch (Exception e) {
            if (str.equals(this.b)) {
                this.b = this.a.getDefaultBaseUrl();
            }
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0023 A[Catch:{ all -> 0x0029 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0026 A[SYNTHETIC] */
    private JSONObject a(HttpURLConnection httpURLConnection) {
        boolean z;
        JSONObject jSONObject = new JSONObject();
        int i = 0;
        do {
            try {
                String headerFieldKey = httpURLConnection.getHeaderFieldKey(i);
                String headerField = httpURLConnection.getHeaderField(i);
                i++;
                if (TextUtils.isEmpty(headerFieldKey)) {
                    if (TextUtils.isEmpty(headerField)) {
                        z = false;
                        if (!z) {
                            jSONObject.put(headerFieldKey, headerField);
                            continue;
                        }
                    }
                }
                z = true;
                if (!z) {
                }
            } catch (Throwable unused) {
                PWLog.noise("RequestManager", "Failed to get http headers");
            }
        } while (z);
        return jSONObject;
    }

    private void a(long j, int i, String str, PushRequest pushRequest, String str2) {
        if (pushRequest instanceof LoggableRequest) {
            a(j, i, str, pushRequest, str2, 0, null);
        }
    }

    private void a(String str) {
        this.b = str;
        this.a.baseUrl().set(str);
    }

    private boolean a() {
        boolean z = this.a.removeAllDeviceData().get();
        if (z) {
            PWLog.noise("RequestManager", "remove all data device is true, it is block request to server");
        }
        return z;
    }

    private boolean a(int i) {
        return i >= 400 && i < 600;
    }

    private <Response> boolean a(PushRequest<Response> pushRequest) {
        return pushRequest instanceof a;
    }

    /* JADX INFO: finally extract failed */
    private String b(HttpURLConnection httpURLConnection) throws IOException {
        BufferedInputStream bufferedInputStream = a(httpURLConnection.getResponseCode()) ? new BufferedInputStream(httpURLConnection.getErrorStream()) : new BufferedInputStream(httpURLConnection.getInputStream());
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read >= 0) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        String trim = new String(byteArrayOutputStream.toByteArray()).trim();
                        byteArrayOutputStream.close();
                        return trim;
                    }
                }
            } catch (Throwable th) {
                byteArrayOutputStream.close();
                throw th;
            }
        } finally {
            bufferedInputStream.close();
        }
    }

    @NonNull
    public <Response> Result<Response, NetworkException> a(PushRequest<Response> pushRequest, String str) {
        return a(pushRequest, str, 0);
    }

    /* access modifiers changed from: package-private */
    public void a(long j, int i, String str, PushRequest pushRequest, @NonNull b bVar) {
        String str2;
        if (!bVar.d() && (pushRequest instanceof LoggableRequest)) {
            try {
                str2 = bVar.b().getString("status_message");
            } catch (Exception unused) {
                str2 = "Failed to parse \"status_message\" parameter from server response";
            }
            a(j, i, str, pushRequest, str2, bVar.a, bVar.d);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(long j, int i, String str, PushRequest pushRequest, String str2, int i2, JSONObject jSONObject) {
        com.pushwoosh.repository.config.b bVar = this.d;
        if (bVar == null || bVar.a().get() != 0) {
            e eVar = new e(j, new Date().getTime(), i, str, pushRequest, str2, i2, jSONObject);
            a(eVar, eVar.b());
        }
    }

    @Override // com.pushwoosh.internal.network.RequestManager
    public void disableReverseProxy() {
        this.c = false;
    }

    @Override // com.pushwoosh.internal.network.RequestManager
    public void logError(String str) {
        new a(this, str).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @Override // com.pushwoosh.internal.network.RequestManager
    public <Response> void sendRequest(PushRequest<Response> pushRequest) {
        if (!a()) {
            sendRequest(pushRequest, null);
        }
    }

    @Override // com.pushwoosh.internal.network.RequestManager
    public <Response> void sendRequest(PushRequest<Response> pushRequest, @Nullable Callback<Response, NetworkException> callback) {
        sendRequest(pushRequest, this.b, callback);
    }

    @Override // com.pushwoosh.internal.network.RequestManager
    public <Response> void sendRequest(PushRequest<Response> pushRequest, String str, Callback<Response, NetworkException> callback) {
        if (!a()) {
            new c(this, pushRequest, str, callback).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        } else if (callback != null) {
            callback.process(Result.fromException(new NetworkException("Device data was removed from Pushwoosh and all interactions were stopped")));
        }
    }

    @Override // com.pushwoosh.internal.network.RequestManager
    @NonNull
    public <Response> Result<Response, NetworkException> sendRequestSync(PushRequest<Response> pushRequest) {
        return sendRequestSync(pushRequest, 0);
    }

    @Override // com.pushwoosh.internal.network.RequestManager
    @NonNull
    public <Response> Result<Response, NetworkException> sendRequestSync(PushRequest<Response> pushRequest, int i) {
        return a() ? Result.fromData(null) : a(pushRequest, this.b, i);
    }

    @Override // com.pushwoosh.internal.network.RequestManager
    public void setReverseProxyUrl(String str) {
        this.c = true;
        a(str);
    }

    @Override // com.pushwoosh.internal.network.RequestManager
    public void updateBaseUrl(String str) {
        a(str);
    }
}
