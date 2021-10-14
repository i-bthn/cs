package com.pddstudio.preferences.encrypted;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.scottyab.aescrypt.AESCrypt;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class EncryptedPreferences {
    private static final String TAG = "EncryptedPreferences";
    private static EncryptedPreferences encryptedPreferences;
    private static EncryptedPreferences singletonInstance;
    private final String cryptoKey;
    private final EncryptedEditor encryptedEditor;
    private final List<OnSharedPreferenceChangeListenerImpl> listeners;
    private final boolean printDebugMessages;
    private final SharedPreferences sharedPreferences;
    private final Utils utils;

    public interface OnSharedPreferenceChangeListener {
        void onSharedPreferenceChanged(EncryptedPreferences encryptedPreferences, String str);
    }

    @Deprecated
    public static EncryptedPreferences getInstance(Context context) {
        if (encryptedPreferences == null) {
            encryptedPreferences = new Builder(context).build();
        }
        return encryptedPreferences;
    }

    public static EncryptedPreferences getSingletonInstance() {
        EncryptedPreferences encryptedPreferences2 = singletonInstance;
        if (encryptedPreferences2 != null) {
            return encryptedPreferences2;
        }
        throw new RuntimeException("Singleton instance doesn't exist. Did you forget to set Builder.withSaveAsSingleton(true) ?");
    }

    private EncryptedPreferences(Builder builder) {
        SharedPreferences sharedPreferences2;
        if (TextUtils.isEmpty(builder.prefsName)) {
            sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(builder.context);
        } else {
            sharedPreferences2 = builder.context.getSharedPreferences(builder.prefsName, 0);
        }
        this.sharedPreferences = sharedPreferences2;
        if (!TextUtils.isEmpty(builder.encryptionPassword)) {
            this.cryptoKey = builder.encryptionPassword;
            EncryptedPreferences encryptedPreferences2 = null;
            this.encryptedEditor = new EncryptedEditor(this);
            this.utils = new Utils(this);
            this.printDebugMessages = builder.context.getResources().getBoolean(R.bool.enable_debug_messages);
            this.listeners = new ArrayList();
            if (!builder.listeners.isEmpty()) {
                for (OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : builder.listeners) {
                    registerListener(onSharedPreferenceChangeListener);
                }
            }
            singletonInstance = builder.singleton ? this : encryptedPreferences2;
            return;
        }
        throw new RuntimeException("Unable to initialize EncryptedPreferences! Did you forget to set a password using Builder.withEncryptionPassword(encryptionKey) ?");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void log(String str) {
        if (this.printDebugMessages) {
            Log.d(TAG, str);
        }
    }

    private void registerListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        if (checkIfListenerExist(onSharedPreferenceChangeListener)) {
            log("registerListener() : " + onSharedPreferenceChangeListener + " is already registered - skip adding.");
            return;
        }
        OnSharedPreferenceChangeListenerImpl onSharedPreferenceChangeListenerImpl = new OnSharedPreferenceChangeListenerImpl(this, onSharedPreferenceChangeListener);
        this.sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListenerImpl);
        this.listeners.add(onSharedPreferenceChangeListenerImpl);
        log("registerListener() : interface registered: " + onSharedPreferenceChangeListener + " ");
    }

    private void unregisterListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        if (checkIfListenerExist(onSharedPreferenceChangeListener)) {
            OnSharedPreferenceChangeListenerImpl listenerImpl = getListenerImpl(onSharedPreferenceChangeListener);
            this.sharedPreferences.unregisterOnSharedPreferenceChangeListener(listenerImpl);
            removeListenerImpl(onSharedPreferenceChangeListener);
            log("unregisterListener() : " + listenerImpl + " ( interface: " + onSharedPreferenceChangeListener + " )");
            return;
        }
        log("unregisterListener() : unable to find registered listener ( " + onSharedPreferenceChangeListener + ")");
    }

    private OnSharedPreferenceChangeListenerImpl getListenerImpl(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        for (OnSharedPreferenceChangeListenerImpl onSharedPreferenceChangeListenerImpl : this.listeners) {
            if (onSharedPreferenceChangeListener.equals(onSharedPreferenceChangeListenerImpl.getListenerInterface())) {
                return onSharedPreferenceChangeListenerImpl;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean checkIfListenerExist(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        for (OnSharedPreferenceChangeListenerImpl onSharedPreferenceChangeListenerImpl : this.listeners) {
            if (onSharedPreferenceChangeListener.equals(onSharedPreferenceChangeListenerImpl.getListenerInterface())) {
                log("checkListener() : " + onSharedPreferenceChangeListener + " found implementation: " + onSharedPreferenceChangeListenerImpl);
                return true;
            }
        }
        return false;
    }

    private void removeListenerImpl(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        log("removeListenerImpl() : requested for " + onSharedPreferenceChangeListener);
        for (int i = 0; i < this.listeners.size(); i++) {
            if (onSharedPreferenceChangeListener.equals(this.listeners.get(i).getListenerInterface())) {
                this.listeners.remove(i);
                log("removeListenerImpl() : removed listener at position: " + i);
            }
        }
    }

    private void printListeners() {
        if (this.listeners.isEmpty()) {
            log("printListeners() => no listeners found");
            return;
        }
        Iterator<OnSharedPreferenceChangeListenerImpl> it = this.listeners.iterator();
        while (it.hasNext()) {
            log("printListeners() => " + it.next());
        }
    }

    private void removeExistingPreferenceKey(String str) {
        removeExistingPreferenceKeys(str);
    }

    private void removeExistingPreferenceKeys(String... strArr) {
        HashSet<String> hashSet = new HashSet();
        for (String str : strArr) {
            if (this.sharedPreferences.contains(str)) {
                hashSet.add(str);
            } else {
                log("removeExistingPreferenceKey() : Couldn't find key '" + str + "' ! Skipping...");
            }
        }
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        for (String str2 : hashSet) {
            edit.remove(str2);
        }
        edit.apply();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String encryptString(String str) {
        try {
            return encodeCharset(AESCrypt.encrypt(this.cryptoKey, str));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String decryptString(String str) {
        try {
            return AESCrypt.decrypt(this.cryptoKey, removeEncoding(str));
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    private String removeEncoding(String str) {
        String replaceAll = str.replaceAll("x0P1Xx", "\\+").replaceAll("x0P2Xx", "/").replaceAll("x0P3Xx", "=");
        log("removeEncoding() : " + str + " => " + replaceAll);
        return replaceAll;
    }

    private String encodeCharset(String str) {
        String replaceAll = str.replaceAll("\\+", "x0P1Xx").replaceAll("/", "x0P2Xx").replaceAll("=", "x0P3Xx");
        log("encodeCharset() : " + str + " => " + replaceAll);
        return replaceAll;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean containsEncryptedKey(String str) {
        return this.sharedPreferences.contains(str);
    }

    private <T> Object decryptType(String str, Object obj, T t) {
        String encryptString = encryptString(str);
        log("decryptType() => encryptedKey => " + encryptString);
        if (TextUtils.isEmpty(encryptString) || !containsEncryptedKey(encryptString)) {
            log("unable to encrypt or find key => " + encryptString);
            return t;
        }
        String string = this.sharedPreferences.getString(encryptString, null);
        log("decryptType() => encryptedValue => " + string);
        if (TextUtils.isEmpty(string)) {
            return t;
        }
        String decryptString = decryptString(string);
        log("decryptType() => orgValue => " + decryptString);
        if (TextUtils.isEmpty(decryptString)) {
            return t;
        }
        if (obj instanceof String) {
            return decryptString;
        }
        if (obj instanceof Integer) {
            try {
                return Integer.valueOf(Integer.parseInt(decryptString));
            } catch (NumberFormatException unused) {
                return t;
            }
        } else if (obj instanceof Long) {
            try {
                return Long.valueOf(Long.parseLong(decryptString));
            } catch (NumberFormatException unused2) {
                return t;
            }
        } else if (!(obj instanceof Float)) {
            return obj instanceof Boolean ? Boolean.valueOf(Boolean.parseBoolean(decryptString)) : t;
        } else {
            try {
                return Float.valueOf(Float.parseFloat(decryptString));
            } catch (NumberFormatException unused3) {
                return t;
            }
        }
    }

    public int getInt(String str, int i) {
        return ((Integer) decryptType(str, 0, Integer.valueOf(i))).intValue();
    }

    public long getLong(String str, long j) {
        return ((Long) decryptType(str, 0L, Long.valueOf(j))).longValue();
    }

    public boolean getBoolean(String str, boolean z) {
        return ((Boolean) decryptType(str, Boolean.valueOf(z), Boolean.valueOf(z))).booleanValue();
    }

    public float getFloat(String str, float f) {
        return ((Float) decryptType(str, Float.valueOf(0.0f), Float.valueOf(f))).floatValue();
    }

    public String getString(String str, String str2) {
        return (String) decryptType(str, "", str2);
    }

    public Set<String> getAllKeys(boolean z) {
        if (!z) {
            return this.sharedPreferences.getAll().keySet();
        }
        HashSet hashSet = new HashSet();
        for (String str : this.sharedPreferences.getAll().keySet()) {
            hashSet.add(decryptString(str));
        }
        return hashSet;
    }

    public Set<String> getAllKeys() {
        return getAllKeys(true);
    }

    public boolean contains(String str) {
        return this.sharedPreferences.contains(encryptString(str));
    }

    public EncryptedEditor edit() {
        return this.encryptedEditor;
    }

    public Utils getUtils() {
        return this.utils;
    }

    public void importSharedPreferences(SharedPreferences sharedPreferences2, boolean z) {
        importSharedPreferences(sharedPreferences2, z, false);
    }

    public void importSharedPreferences(SharedPreferences sharedPreferences2, boolean z, boolean z2) {
        if (sharedPreferences2 != null) {
            Map<String, ?> all = sharedPreferences2.getAll();
            int i = 0;
            for (String str : all.keySet()) {
                if (!contains(str) || (contains(str) && z)) {
                    log("-> Importing key: " + str);
                    this.encryptedEditor.putValue(str, String.valueOf(all.get(str)));
                    this.encryptedEditor.apply();
                    i++;
                    if (z2 && contains(str)) {
                        sharedPreferences2.edit().remove(str).apply();
                        log("-> Deleted entry for key : " + str);
                    }
                } else {
                    log("-> Skip import for " + str + " : key already exist");
                }
            }
            log("Import finished! (" + i + "/" + all.size() + " entries imported)");
        }
    }

    public void forceDeleteExistingPreferences() {
        Set<String> keySet = this.sharedPreferences.getAll().keySet();
        removeExistingPreferenceKeys((String[]) keySet.toArray(new String[keySet.size()]));
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        if (onSharedPreferenceChangeListener != null) {
            registerListener(onSharedPreferenceChangeListener);
        }
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        if (onSharedPreferenceChangeListener != null) {
            unregisterListener(onSharedPreferenceChangeListener);
        }
    }

    public final class Utils {
        private final EncryptedPreferences encryptedPreferences;

        private Utils(EncryptedPreferences encryptedPreferences2) {
            this.encryptedPreferences = encryptedPreferences2;
        }

        public String encryptStringValue(String str) {
            return this.encryptedPreferences.encryptString(str);
        }

        public String decryptStringValue(String str) {
            return this.encryptedPreferences.decryptString(str);
        }
    }

    /* access modifiers changed from: private */
    public class OnSharedPreferenceChangeListenerImpl implements SharedPreferences.OnSharedPreferenceChangeListener {
        private final EncryptedPreferences encryptedPreferences;
        private final OnSharedPreferenceChangeListener listener;

        private OnSharedPreferenceChangeListenerImpl(EncryptedPreferences encryptedPreferences2, OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
            this.listener = onSharedPreferenceChangeListener;
            this.encryptedPreferences = encryptedPreferences2;
        }

        /* access modifiers changed from: protected */
        public OnSharedPreferenceChangeListener getListenerInterface() {
            return this.listener;
        }

        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            if (EncryptedPreferences.this.checkIfListenerExist(this.listener)) {
                EncryptedPreferences encryptedPreferences2 = EncryptedPreferences.this;
                encryptedPreferences2.log("onSharedPreferenceChanged() : found listener " + this.listener);
                OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = this.listener;
                EncryptedPreferences encryptedPreferences3 = this.encryptedPreferences;
                onSharedPreferenceChangeListener.onSharedPreferenceChanged(encryptedPreferences3, encryptedPreferences3.getUtils().decryptStringValue(str));
                return;
            }
            EncryptedPreferences encryptedPreferences4 = EncryptedPreferences.this;
            encryptedPreferences4.log("onSharedPreferenceChanged() : couldn't find listener (" + this.listener + ")");
        }
    }

    public final class EncryptedEditor {
        private final String TAG;
        private final SharedPreferences.Editor editor;
        private final EncryptedPreferences encryptedPreferences;

        private EncryptedEditor(EncryptedPreferences encryptedPreferences2) {
            this.TAG = EncryptedEditor.class.getSimpleName();
            this.encryptedPreferences = encryptedPreferences2;
            this.editor = encryptedPreferences2.sharedPreferences.edit();
        }

        private synchronized void log(String str) {
            if (this.encryptedPreferences.printDebugMessages) {
                Log.d(this.TAG, str);
            }
        }

        private SharedPreferences.Editor editor() {
            return this.editor;
        }

        private String encryptValue(String str) {
            String encryptString = this.encryptedPreferences.encryptString(str);
            log("encryptValue() => " + encryptString);
            return encryptString;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void putValue(String str, String str2) {
            log("putValue() => " + str + " [" + encryptValue(str) + "] || " + str2 + " [" + encryptValue(str2) + "]");
            editor().putString(encryptValue(str), encryptValue(str2));
        }

        public EncryptedEditor putString(String str, String str2) {
            putValue(str, str2);
            return this;
        }

        public EncryptedEditor putInt(String str, int i) {
            putValue(str, String.valueOf(i));
            return this;
        }

        public EncryptedEditor putLong(String str, long j) {
            putValue(str, String.valueOf(j));
            return this;
        }

        public EncryptedEditor putFloat(String str, float f) {
            putValue(str, String.valueOf(f));
            return this;
        }

        public EncryptedEditor putBoolean(String str, boolean z) {
            putValue(str, String.valueOf(z));
            return this;
        }

        public EncryptedEditor remove(String str) {
            String encryptValue = encryptValue(str);
            if (EncryptedPreferences.this.containsEncryptedKey(encryptValue)) {
                log("remove() => " + str + " [ " + encryptValue + " ]");
                editor().remove(encryptValue);
            }
            return this;
        }

        public EncryptedEditor clear() {
            log("clear() => clearing preferences.");
            editor().clear();
            return this;
        }

        public void apply() {
            editor().apply();
        }

        public boolean commit() {
            return editor().commit();
        }
    }

    public static final class Builder {
        private final Context context;
        private String encryptionPassword;
        private final List<OnSharedPreferenceChangeListener> listeners;
        private String prefsName;
        private boolean singleton = false;

        public Builder(Context context2) {
            this.context = context2.getApplicationContext();
            this.listeners = new ArrayList();
        }

        public Builder withEncryptionPassword(String str) {
            this.encryptionPassword = str;
            return this;
        }

        public Builder withPreferenceName(String str) {
            this.prefsName = str;
            return this;
        }

        public Builder withSaveAsSingleton(boolean z) {
            this.singleton = z;
            return this;
        }

        public Builder withOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
            if (onSharedPreferenceChangeListener != null) {
                this.listeners.add(onSharedPreferenceChangeListener);
            }
            return this;
        }

        public EncryptedPreferences build() {
            return new EncryptedPreferences(this);
        }
    }
}
