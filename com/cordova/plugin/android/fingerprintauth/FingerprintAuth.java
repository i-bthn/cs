package com.cordova.plugin.android.fingerprintauth;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Locale;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(23)
public class FingerprintAuth extends CordovaPlugin {
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final String DIALOG_FRAGMENT_TAG = "FpAuthDialog";
    public static final String TAG = "FingerprintAuth";
    public static CallbackContext mCallbackContext = null;
    public static Cipher mCipher = null;
    private static final String mClientId = "CordovaTouchPlugin";
    public static KeyGenerator mKeyGenerator;
    private static String mKeyID;
    public static KeyStore mKeyStore;
    public static PluginResult mPluginResult;
    public static String packageName;
    private int mCurrentMode;
    private FingerprintManager mFingerPrintManager;
    FingerprintAuthenticationDialogFragment mFragment;
    KeyguardManager mKeyguardManager;
    private String mLangCode = "en_US";
    private String mToEncrypt;

    @Override // org.apache.cordova.CordovaPlugin
    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        Log.v(TAG, "Init FingerprintAuth");
        packageName = cordovaInterface.getActivity().getApplicationContext().getPackageName();
        mPluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
        if (Build.VERSION.SDK_INT >= 23) {
            this.mKeyguardManager = (KeyguardManager) cordovaInterface.getActivity().getSystemService(KeyguardManager.class);
            this.mFingerPrintManager = (FingerprintManager) cordovaInterface.getActivity().getApplicationContext().getSystemService(FingerprintManager.class);
            try {
                mKeyGenerator = KeyGenerator.getInstance("AES", ANDROID_KEY_STORE);
                mKeyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
                try {
                    mCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException("Failed to get an instance of Cipher", e);
                } catch (NoSuchPaddingException e2) {
                    throw new RuntimeException("Failed to get an instance of Cipher", e2);
                }
            } catch (NoSuchAlgorithmException e3) {
                throw new RuntimeException("Failed to get an instance of KeyGenerator", e3);
            } catch (NoSuchProviderException e4) {
                throw new RuntimeException("Failed to get an instance of KeyGenerator", e4);
            } catch (KeyStoreException e5) {
                throw new RuntimeException("Failed to get an instance of KeyStore", e5);
            }
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        mCallbackContext = callbackContext;
        Log.v(TAG, "FingerprintAuth action: " + str);
        if (Build.VERSION.SDK_INT < 23) {
            Log.e(TAG, "minimum SDK version 23 required");
            mPluginResult = new PluginResult(PluginResult.Status.ERROR);
            mCallbackContext.error("minimum SDK version 23 required");
            mCallbackContext.sendPluginResult(mPluginResult);
            return true;
        } else if (str.equals("save")) {
            String string = jSONArray.getString(0);
            String string2 = jSONArray.getString(1);
            if (isFingerprintAuthAvailable()) {
                if (getSecretKey() == null && createKey()) {
                    getSecretKey();
                }
                mKeyID = string;
                this.mToEncrypt = string2;
                showFingerprintDialog(1, null);
                return true;
            }
            mPluginResult = new PluginResult(PluginResult.Status.ERROR);
            mCallbackContext.error("Fingerprint authentication not available");
            mCallbackContext.sendPluginResult(mPluginResult);
            return true;
        } else if (str.equals("verify")) {
            String string3 = jSONArray.getString(0);
            String string4 = jSONArray.getString(1);
            if (!isFingerprintAuthAvailable()) {
                mPluginResult = new PluginResult(PluginResult.Status.ERROR);
                mCallbackContext.error("Fingerprint authentication not available");
                mCallbackContext.sendPluginResult(mPluginResult);
            } else if (getSecretKey() != null) {
                mKeyID = string3;
                showFingerprintDialog(2, string4);
                mPluginResult.setKeepCallback(true);
            } else {
                mPluginResult = new PluginResult(PluginResult.Status.ERROR);
                mCallbackContext.error("Secret Key non available");
                mCallbackContext.sendPluginResult(mPluginResult);
            }
            return true;
        } else if (str.equals("isAvailable")) {
            new JSONObject();
            if (isFingerprintAuthAvailable()) {
                mPluginResult = new PluginResult(PluginResult.Status.OK);
                mCallbackContext.success("YES");
                mCallbackContext.sendPluginResult(mPluginResult);
            } else {
                mPluginResult = new PluginResult(PluginResult.Status.ERROR);
                mCallbackContext.error("No FP availabile");
                mCallbackContext.sendPluginResult(mPluginResult);
            }
            return true;
        } else if (str.equals("setLocale")) {
            this.mLangCode = jSONArray.getString(0);
            Resources resources = this.cordova.getActivity().getResources();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            Configuration configuration = resources.getConfiguration();
            configuration.locale = new Locale(this.mLangCode.toLowerCase());
            resources.updateConfiguration(configuration, displayMetrics);
            return true;
        } else if (str.equals("has")) {
            String string5 = jSONArray.getString(0);
            SharedPreferences preferences = this.cordova.getActivity().getPreferences(0);
            if (!preferences.getString("fing" + string5, "").equals("")) {
                mPluginResult = new PluginResult(PluginResult.Status.OK);
                mCallbackContext.success();
                mCallbackContext.sendPluginResult(mPluginResult);
            } else {
                mPluginResult = new PluginResult(PluginResult.Status.ERROR);
                mCallbackContext.error("No pw available");
                mCallbackContext.sendPluginResult(mPluginResult);
            }
            return true;
        } else if (!str.equals("delete")) {
            return false;
        } else {
            String string6 = jSONArray.getString(0);
            SharedPreferences.Editor edit = this.cordova.getActivity().getPreferences(0).edit();
            edit.remove("fing" + string6);
            edit.remove("fing_iv" + string6);
            if (edit.commit()) {
                mPluginResult = new PluginResult(PluginResult.Status.OK);
                mCallbackContext.success();
            } else {
                mPluginResult = new PluginResult(PluginResult.Status.ERROR);
                mCallbackContext.error("Could not delete password");
            }
            mCallbackContext.sendPluginResult(mPluginResult);
            return true;
        }
    }

    private boolean isFingerprintAuthAvailable() {
        return this.mFingerPrintManager.isHardwareDetected() && this.mFingerPrintManager.hasEnrolledFingerprints();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean initCipher(int i) {
        String str = "";
        boolean z = false;
        try {
            SecretKey secretKey = getSecretKey();
            if (i == 1) {
                new SecureRandom().nextBytes(new byte[16]);
                mCipher.init(i, secretKey);
            } else {
                mCipher.init(i, secretKey, new IvParameterSpec(Base64.decode(this.cordova.getActivity().getPreferences(0).getString("fing_iv" + mKeyID, ""), 0)));
            }
            z = true;
        } catch (KeyPermanentlyInvalidatedException unused) {
            removePermanentlyInvalidatedKey();
            str = "KeyPermanentlyInvalidatedException";
            setPluginResultError(str);
        } catch (InvalidKeyException unused2) {
            str = "Failed to init Cipher: " + "InvalidKeyException";
        } catch (InvalidAlgorithmParameterException e) {
            str = "Failed to init Cipher: " + "InvalidAlgorithmParameterException";
            e.printStackTrace();
        }
        if (!z) {
            Log.e(TAG, str);
        }
        return z;
    }

    private SecretKey getSecretKey() {
        String str = "";
        SecretKey secretKey = null;
        try {
            mKeyStore.load(null);
            secretKey = (SecretKey) mKeyStore.getKey(mClientId, null);
        } catch (KeyStoreException unused) {
            str = "Failed to get SecretKey from KeyStore: " + "KeyStoreException";
        } catch (CertificateException unused2) {
            str = "Failed to get SecretKey from KeyStore: " + "CertificateException";
        } catch (UnrecoverableKeyException unused3) {
            str = "Failed to get SecretKey from KeyStore: " + "UnrecoverableKeyException";
        } catch (IOException unused4) {
            str = "Failed to get SecretKey from KeyStore: " + "IOException";
        } catch (NoSuchAlgorithmException unused5) {
            str = "Failed to get SecretKey from KeyStore: " + "NoSuchAlgorithmException";
        } catch (UnrecoverableEntryException unused6) {
            str = "Failed to get SecretKey from KeyStore: " + "UnrecoverableEntryException";
        }
        if (secretKey == null) {
            Log.e(TAG, str);
        }
        return secretKey;
    }

    public static boolean createKey() {
        String str = "";
        boolean z = false;
        try {
            mKeyStore.load(null);
            mKeyGenerator.init(new KeyGenParameterSpec.Builder(mClientId, 3).setBlockModes("CBC").setUserAuthenticationRequired(true).setEncryptionPaddings("PKCS7Padding").build());
            mKeyGenerator.generateKey();
            z = true;
        } catch (NoSuchAlgorithmException unused) {
            str = "Failed to create key: " + "NoSuchAlgorithmException";
        } catch (InvalidAlgorithmParameterException unused2) {
            str = "Failed to create key: " + "InvalidAlgorithmParameterException";
        } catch (CertificateException unused3) {
            str = "Failed to create key: " + "CertificateException";
        } catch (IOException unused4) {
            str = "Failed to create key: " + "IOException";
        }
        if (!z) {
            Log.e(TAG, str);
            setPluginResultError(str);
        }
        return z;
    }

    public void showFingerprintDialog(final int i, final String str) {
        this.mCurrentMode = i;
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            /* class com.cordova.plugin.android.fingerprintauth.FingerprintAuth.AnonymousClass1 */

            public void run() {
                FingerprintAuth.this.mFragment = new FingerprintAuthenticationDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("dialogMode", i);
                bundle.putString("dialogMessage", str);
                FingerprintAuth.this.mFragment.setArguments(bundle);
                FingerprintAuth.this.mFragment.setmFingerPrintAuth(this);
                if (FingerprintAuth.this.initCipher(i)) {
                    FingerprintAuth.this.mFragment.setCancelable(false);
                    FingerprintAuth.this.mFragment.setCryptoObject(new FingerprintManager.CryptoObject(FingerprintAuth.mCipher));
                    FingerprintAuth.this.mFragment.show(FingerprintAuth.this.cordova.getActivity().getFragmentManager(), FingerprintAuth.DIALOG_FRAGMENT_TAG);
                    return;
                }
                FingerprintAuth.mCallbackContext.error("Failed to init Cipher");
                FingerprintAuth.mPluginResult = new PluginResult(PluginResult.Status.ERROR);
                FingerprintAuth.mCallbackContext.sendPluginResult(FingerprintAuth.mPluginResult);
            }
        });
    }

    public void onAuthenticated(boolean z) {
        Throwable cause;
        String str = "";
        String str2 = "";
        if (z) {
            try {
                SharedPreferences preferences = this.cordova.getActivity().getPreferences(0);
                if (this.mCurrentMode == 2) {
                    str = new String(mCipher.doFinal(Base64.decode(preferences.getString("fing" + mKeyID, ""), 0)));
                } else if (this.mCurrentMode == 1) {
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putString("fing" + mKeyID, Base64.encodeToString(mCipher.doFinal(this.mToEncrypt.getBytes()), 0));
                    edit.putString("fing_iv" + mKeyID, Base64.encodeToString(mCipher.getIV(), 0));
                    edit.commit();
                    this.mToEncrypt = "";
                    str = FirebaseAnalytics.Param.SUCCESS;
                }
            } catch (BadPaddingException e) {
                str2 = "Failed to encrypt the data with the generated key: BadPaddingException:  " + e.getMessage();
                Log.e(TAG, str2);
            } catch (IllegalBlockSizeException e2) {
                String message = e2.getMessage();
                String simpleName = e2.getClass().getSimpleName();
                if (message == null && (cause = e2.getCause()) != null) {
                    message = cause.getMessage();
                    simpleName = cause.getClass().getSimpleName();
                }
                String str3 = "Failed to encrypt the data with the generated key: " + simpleName + ": " + message;
                if (message == "Key user not authenticated") {
                    removePermanentlyInvalidatedKey();
                    str2 = "KeyPermanentlyInvalidatedException";
                } else {
                    str2 = str3;
                }
                Log.e(TAG, str2);
            }
        }
        if (str != "") {
            mCallbackContext.success(str);
            mPluginResult = new PluginResult(PluginResult.Status.OK);
        } else {
            mCallbackContext.error(str2);
            mPluginResult = new PluginResult(PluginResult.Status.ERROR);
        }
        mCallbackContext.sendPluginResult(mPluginResult);
    }

    public static void onCancelled() {
        mCallbackContext.error("Cancelled");
    }

    public static boolean setPluginResultError(String str) {
        mCallbackContext.error(str);
        mPluginResult = new PluginResult(PluginResult.Status.ERROR);
        return false;
    }

    private void removePermanentlyInvalidatedKey() {
        try {
            mKeyStore.deleteEntry(mClientId);
            Log.i(TAG, "Permanently invalidated key was removed.");
        } catch (KeyStoreException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
