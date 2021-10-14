package nl.xservices.plugins;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import javax.net.ssl.HttpsURLConnection;
import javax.security.cert.CertificateException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class SSLCertificateChecker extends CordovaPlugin {
    private static final String ACTION_CHECK_EVENT = "check";
    private static char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, final JSONArray jSONArray, final CallbackContext callbackContext) throws JSONException {
        if (ACTION_CHECK_EVENT.equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                /* class nl.xservices.plugins.SSLCertificateChecker.AnonymousClass1 */

                public void run() {
                    try {
                        String string = jSONArray.getString(0);
                        JSONArray jSONArray = jSONArray.getJSONArray(2);
                        String fingerprint = SSLCertificateChecker.getFingerprint(string);
                        for (int i = 0; i < jSONArray.length(); i++) {
                            if (jSONArray.get(i).toString().equalsIgnoreCase(fingerprint)) {
                                callbackContext.success("CONNECTION_SECURE");
                                return;
                            }
                        }
                        callbackContext.error("CONNECTION_NOT_SECURE");
                    } catch (Exception unused) {
                        callbackContext.error("CONNECTION_NOT_SECURE");
                    }
                }
            });
            return true;
        }
        callbackContext.error("sslCertificateChecker." + str + " is not a supported function. Did you mean '" + ACTION_CHECK_EVENT + "'?");
        return false;
    }

    /* access modifiers changed from: private */
    public static String getFingerprint(String str) throws IOException, NoSuchAlgorithmException, CertificateException, CertificateEncodingException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
        httpsURLConnection.setConnectTimeout(5000);
        httpsURLConnection.connect();
        Certificate certificate = httpsURLConnection.getServerCertificates()[0];
        MessageDigest instance = MessageDigest.getInstance("SHA256");
        instance.update(certificate.getEncoded());
        return dumpHex(instance.digest());
    }

    private static String dumpHex(byte[] bArr) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder((length * 3) - 1);
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(HEX_CHARS[(bArr[i] >> 4) & 15]);
            sb.append(HEX_CHARS[bArr[i] & 15]);
        }
        return sb.toString();
    }
}
