package com.google.zxing.client.android;

import android.util.Log;
import com.bumptech.glide.load.Key;
import com.pushwoosh.richmedia.animation.a;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public final class HttpHelper {
    private static final Collection<String> REDIRECTOR_DOMAINS = new HashSet(Arrays.asList("amzn.to", "bit.ly", "bitly.com", "fb.me", "goo.gl", "is.gd", "j.mp", "lnkd.in", "ow.ly", "R.BEETAGG.COM", "r.beetagg.com", "SCN.BY", "su.pr", "t.co", "tinyurl.com", "tr.im"));
    private static final String TAG = "HttpHelper";

    public enum ContentType {
        HTML,
        JSON,
        XML,
        TEXT
    }

    private HttpHelper() {
    }

    public static CharSequence downloadViaHttp(String str, ContentType contentType) throws IOException {
        return downloadViaHttp(str, contentType, Integer.MAX_VALUE);
    }

    public static CharSequence downloadViaHttp(String str, ContentType contentType, int i) throws IOException {
        String str2;
        switch (contentType) {
            case HTML:
                str2 = "application/xhtml+xml,text/html,text/*,*/*";
                break;
            case JSON:
                str2 = "application/json,text/*,*/*";
                break;
            case XML:
                str2 = "application/xml,text/*,*/*";
                break;
            default:
                str2 = "text/*,*/*";
                break;
        }
        return downloadViaHttp(str, str2, i);
    }

    /* JADX INFO: finally extract failed */
    private static CharSequence downloadViaHttp(String str, String str2, int i) throws IOException {
        int i2 = 0;
        while (i2 < 5) {
            HttpURLConnection safelyOpenConnection = safelyOpenConnection(new URL(str));
            safelyOpenConnection.setInstanceFollowRedirects(true);
            safelyOpenConnection.setRequestProperty("Accept", str2);
            safelyOpenConnection.setRequestProperty("Accept-Charset", "utf-8,*");
            safelyOpenConnection.setRequestProperty("User-Agent", "ZXing (Android)");
            try {
                int safelyConnect = safelyConnect(safelyOpenConnection);
                if (safelyConnect == 200) {
                    CharSequence consume = consume(safelyOpenConnection, i);
                    safelyOpenConnection.disconnect();
                    return consume;
                } else if (safelyConnect == 302) {
                    String headerField = safelyOpenConnection.getHeaderField("Location");
                    if (headerField != null) {
                        i2++;
                        safelyOpenConnection.disconnect();
                        str = headerField;
                    } else {
                        throw new IOException("No Location");
                    }
                } else {
                    throw new IOException("Bad HTTP response: " + safelyConnect);
                }
            } catch (Throwable th) {
                safelyOpenConnection.disconnect();
                throw th;
            }
        }
        throw new IOException("Too many redirects");
    }

    private static String getEncoding(URLConnection uRLConnection) {
        int indexOf;
        String headerField = uRLConnection.getHeaderField("Content-Type");
        return (headerField == null || (indexOf = headerField.indexOf("charset=")) < 0) ? Key.STRING_CHARSET_NAME : headerField.substring(indexOf + 8);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0032 A[SYNTHETIC, Splitter:B:19:0x0032] */
    private static CharSequence consume(URLConnection uRLConnection, int i) throws IOException {
        Throwable th;
        InputStreamReader inputStreamReader;
        int read;
        String encoding = getEncoding(uRLConnection);
        StringBuilder sb = new StringBuilder();
        try {
            inputStreamReader = new InputStreamReader(uRLConnection.getInputStream(), encoding);
            try {
                char[] cArr = new char[1024];
                while (sb.length() < i && (read = inputStreamReader.read(cArr)) > 0) {
                    sb.append(cArr, 0, read);
                }
                try {
                    inputStreamReader.close();
                } catch (IOException | NullPointerException unused) {
                }
                return sb;
            } catch (Throwable th2) {
                th = th2;
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException | NullPointerException unused2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStreamReader = null;
            if (inputStreamReader != null) {
            }
            throw th;
        }
    }

    public static URI unredirect(URI uri) throws IOException {
        if (!REDIRECTOR_DOMAINS.contains(uri.getHost())) {
            return uri;
        }
        HttpURLConnection safelyOpenConnection = safelyOpenConnection(uri.toURL());
        safelyOpenConnection.setInstanceFollowRedirects(false);
        safelyOpenConnection.setDoInput(false);
        safelyOpenConnection.setRequestMethod("HEAD");
        safelyOpenConnection.setRequestProperty("User-Agent", "ZXing (Android)");
        try {
            int safelyConnect = safelyConnect(safelyOpenConnection);
            if (safelyConnect != 307) {
                switch (safelyConnect) {
                    case a.DURATION_MILLIS /*{ENCODED_INT: 300}*/:
                    case 301:
                    case 302:
                    case 303:
                        break;
                    default:
                        return uri;
                }
            }
            String headerField = safelyOpenConnection.getHeaderField("Location");
            if (headerField != null) {
                try {
                    URI uri2 = new URI(headerField);
                    safelyOpenConnection.disconnect();
                    return uri2;
                } catch (URISyntaxException unused) {
                }
            }
            return uri;
        } finally {
            safelyOpenConnection.disconnect();
        }
    }

    private static HttpURLConnection safelyOpenConnection(URL url) throws IOException {
        try {
            URLConnection openConnection = url.openConnection();
            if (openConnection instanceof HttpURLConnection) {
                return (HttpURLConnection) openConnection;
            }
            throw new IOException();
        } catch (NullPointerException e) {
            String str = TAG;
            Log.w(str, "Bad URI? " + url);
            throw new IOException(e);
        }
    }

    private static int safelyConnect(HttpURLConnection httpURLConnection) throws IOException {
        try {
            httpURLConnection.connect();
            try {
                return httpURLConnection.getResponseCode();
            } catch (IllegalArgumentException | NullPointerException | StringIndexOutOfBoundsException e) {
                throw new IOException(e);
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException | NullPointerException | SecurityException e2) {
            throw new IOException(e2);
        }
    }
}
