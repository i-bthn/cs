package org.apache.cordova;

import android.net.Uri;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Whitelist {
    public static final String TAG = "Whitelist";
    private ArrayList<URLPattern> whiteList = new ArrayList<>();

    private static class URLPattern {
        public Pattern host;
        public Pattern path;
        public Integer port;
        public Pattern scheme;

        private String regexFromPattern(String str, boolean z) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt == '*' && z) {
                    sb.append(".");
                } else if ("\\.[]{}()^$?+|".indexOf(charAt) > -1) {
                    sb.append('\\');
                }
                sb.append(charAt);
            }
            return sb.toString();
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x0026 A[Catch:{ NumberFormatException -> 0x008f }] */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0029 A[Catch:{ NumberFormatException -> 0x008f }] */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x005d A[Catch:{ NumberFormatException -> 0x008f }] */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0077 A[Catch:{ NumberFormatException -> 0x008f }] */
        public URLPattern(String str, String str2, String str3, String str4) throws MalformedURLException {
            if (str != null) {
                try {
                    if (!"*".equals(str)) {
                        this.scheme = Pattern.compile(regexFromPattern(str, false), 2);
                        if (!"*".equals(str2)) {
                            this.host = null;
                        } else if (str2.startsWith("*.")) {
                            this.host = Pattern.compile("([a-z0-9.-]*\\.)?" + regexFromPattern(str2.substring(2), false), 2);
                        } else {
                            this.host = Pattern.compile(regexFromPattern(str2, false), 2);
                        }
                        if (str3 != null) {
                            if (!"*".equals(str3)) {
                                this.port = Integer.valueOf(Integer.parseInt(str3, 10));
                                if (str4 != null) {
                                    if (!"/*".equals(str4)) {
                                        this.path = Pattern.compile(regexFromPattern(str4, true));
                                        return;
                                    }
                                }
                                this.path = null;
                            }
                        }
                        this.port = null;
                        if (str4 != null) {
                        }
                        this.path = null;
                    }
                } catch (NumberFormatException unused) {
                    throw new MalformedURLException("Port must be a number");
                }
            }
            this.scheme = null;
            if (!"*".equals(str2)) {
            }
            if (str3 != null) {
            }
            this.port = null;
            if (str4 != null) {
            }
            this.path = null;
        }

        public boolean matches(Uri uri) {
            try {
                if (this.scheme != null && !this.scheme.matcher(uri.getScheme()).matches()) {
                    return false;
                }
                if (this.host != null && !this.host.matcher(uri.getHost()).matches()) {
                    return false;
                }
                if (this.port != null && !this.port.equals(Integer.valueOf(uri.getPort()))) {
                    return false;
                }
                if (this.path == null || this.path.matcher(uri.getPath()).matches()) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                LOG.d(Whitelist.TAG, e.toString());
                return false;
            }
        }
    }

    public void addWhiteListEntry(String str, boolean z) {
        if (this.whiteList != null) {
            try {
                if (str.compareTo("*") == 0) {
                    LOG.d(TAG, "Unlimited access to network resources");
                    this.whiteList = null;
                    return;
                }
                Matcher matcher = Pattern.compile("^((\\*|[A-Za-z-]+):(//)?)?(\\*|((\\*\\.)?[^*/:]+))?(:(\\d+))?(/.*)?").matcher(str);
                if (matcher.matches()) {
                    String group = matcher.group(2);
                    String group2 = matcher.group(4);
                    if (("file".equals(group) || FirebaseAnalytics.Param.CONTENT.equals(group)) && group2 == null) {
                        group2 = "*";
                    }
                    String group3 = matcher.group(8);
                    String group4 = matcher.group(9);
                    if (group == null) {
                        this.whiteList.add(new URLPattern("http", group2, group3, group4));
                        this.whiteList.add(new URLPattern("https", group2, group3, group4));
                        return;
                    }
                    this.whiteList.add(new URLPattern(group, group2, group3, group4));
                }
            } catch (Exception unused) {
                LOG.d(TAG, "Failed to add origin %s", str);
            }
        }
    }

    public boolean isUrlWhiteListed(String str) {
        if (this.whiteList == null) {
            return true;
        }
        Uri parse = Uri.parse(str);
        Iterator<URLPattern> it = this.whiteList.iterator();
        while (it.hasNext()) {
            if (it.next().matches(parse)) {
                return true;
            }
        }
        return false;
    }
}
