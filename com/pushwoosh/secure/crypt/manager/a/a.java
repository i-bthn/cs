package com.pushwoosh.secure.crypt.manager.a;

import android.os.AsyncTask;
import android.util.Base64;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.secure.crypt.manager.a.a.c;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.json.JSONException;
import org.json.JSONObject;

public class a<ResultType> extends AsyncTask<Void, Void, Result<ResultType, PushwooshException>> {
    private c<ResultType> a;
    private Callback<ResultType, PushwooshException> b;
    private String c;
    private List<String> d;
    private String e;

    public a(c<ResultType> cVar, String str, List<String> list, String str2, Callback<ResultType, PushwooshException> callback) {
        this.a = cVar;
        this.b = callback;
        this.c = str;
        this.d = list;
        this.e = str2;
    }

    private String a(HttpURLConnection httpURLConnection, c cVar) throws IOException, JSONException {
        String jSONObject = cVar.a().toString();
        byte[] bytes = jSONObject.getBytes(Key.STRING_CHARSET_NAME);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        try {
            outputStream.write(bytes);
            return jSONObject;
        } finally {
            outputStream.close();
        }
    }

    @NonNull
    private HttpURLConnection a(c cVar) throws IOException {
        if (!this.c.endsWith("/") && !cVar.b().startsWith("/")) {
            this.c += "/";
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.c + cVar.b()).openConnection();
        httpURLConnection.setReadTimeout(120000);
        httpURLConnection.setConnectTimeout(120000);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        return httpURLConnection;
    }

    private void a(HttpURLConnection httpURLConnection) {
        if (this.e != null && (httpURLConnection instanceof HttpsURLConnection)) {
            ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new HostnameVerifier() {
                /* class com.pushwoosh.secure.crypt.manager.a.a.AnonymousClass1 */

                public boolean verify(String str, SSLSession sSLSession) {
                    return HttpsURLConnection.getDefaultHostnameVerifier().verify(a.this.e, sSLSession);
                }
            });
        }
    }

    private boolean b(HttpURLConnection httpURLConnection) throws Exception {
        List<String> list;
        if (!(httpURLConnection instanceof HttpsURLConnection) || (list = this.d) == null || list.size() == 0) {
            return true;
        }
        String encodeToString = Base64.encodeToString(MessageDigest.getInstance("SHA-256").digest(((HttpsURLConnection) httpURLConnection).getServerCertificates()[0].getPublicKey().getEncoded()), 2);
        for (String str : this.d) {
            if (str.equals(encodeToString)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: finally extract failed */
    private String c(HttpURLConnection httpURLConnection) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    bufferedReader.close();
                    inputStream.close();
                    return sb.toString();
                }
            } catch (Throwable th) {
                bufferedReader.close();
                inputStream.close();
                throw th;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00fd, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ff, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r0 = com.pushwoosh.function.Result.fromException(new com.pushwoosh.exception.PushwooshException("Bad response body", r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x010b, code lost:
        if (r10 != null) goto L_0x010d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x010d, code lost:
        r10.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0110, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0111, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0112, code lost:
        r0 = com.pushwoosh.function.Result.fromException(new com.pushwoosh.exception.PushwooshException("Connection error", r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x011d, code lost:
        if (r10 != null) goto L_0x011f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x011f, code lost:
        r10.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0122, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0123, code lost:
        if (r10 != null) goto L_0x0125;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0125, code lost:
        r10.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0128, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0111 A[ExcHandler: IOException (r0v0 'e' java.io.IOException A[CUSTOM_DECLARE]), PHI: r10 
      PHI: (r10v2 java.net.HttpURLConnection) = (r10v1 java.net.HttpURLConnection), (r10v4 java.net.HttpURLConnection), (r10v4 java.net.HttpURLConnection), (r10v4 java.net.HttpURLConnection) binds: [B:1:0x0001, B:2:?, B:10:0x0024, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:1:0x0001] */
    /* renamed from: a */
    public Result<ResultType, PushwooshException> doInBackground(Void... voidArr) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = a(this.a);
            a(httpURLConnection);
            httpURLConnection.connect();
            try {
                if (!b(httpURLConnection)) {
                    Result<ResultType, PushwooshException> fromException = Result.fromException(new PushwooshException("SSL Certificate validation failed."));
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return fromException;
                }
                String a2 = a(httpURLConnection, this.a);
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != 200) {
                    Result<ResultType, PushwooshException> fromException2 = Result.fromException(new PushwooshException(String.format(Locale.US, "Bad response HTTP status code: %d request url: %s", Integer.valueOf(responseCode), httpURLConnection.getURL().toString())));
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return fromException2;
                }
                String c2 = c(httpURLConnection);
                PWLog.info("PostNetworkTask", "\nx\n|     PushwooshSecure request:\n| Url: " + httpURLConnection.getURL().toString() + "\n| Payload: " + a2 + "\n| Response: " + c2 + "\nx");
                JSONObject jSONObject = new JSONObject(c2);
                int i = jSONObject.getInt("status_code");
                String string = jSONObject.getString("status_message");
                if (i != 200) {
                    Result<ResultType, PushwooshException> fromException3 = Result.fromException(new PushwooshException(String.format(Locale.US, "Bad response status code: %d message: %s", Integer.valueOf(i), string)));
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return fromException3;
                }
                Result<ResultType, PushwooshException> fromData = Result.fromData(this.a.b(jSONObject));
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return fromData;
            } catch (Exception e2) {
                Result<ResultType, PushwooshException> fromException4 = Result.fromException(new PushwooshException("SSL Certificate validation failed.", e2));
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return fromException4;
            }
        } catch (JSONException e3) {
            Result<ResultType, PushwooshException> fromException5 = Result.fromException(new PushwooshException("Bad request body", e3));
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return fromException5;
        } catch (IOException e4) {
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Result<ResultType, PushwooshException> result) {
        super.onPostExecute(result);
        Callback<ResultType, PushwooshException> callback = this.b;
        if (callback != null) {
            callback.process(result);
        }
    }
}
