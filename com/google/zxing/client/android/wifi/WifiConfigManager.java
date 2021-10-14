package com.google.zxing.client.android.wifi;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;
import com.google.zxing.client.result.WifiParsedResult;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.text.Typography;

public final class WifiConfigManager extends AsyncTask<WifiParsedResult, Object, Object> {
    private static final Pattern HEX_DIGITS = Pattern.compile("[0-9A-Fa-f]+");
    private static final String TAG = "WifiConfigManager";
    private final WifiManager wifiManager;

    public WifiConfigManager(WifiManager wifiManager2) {
        this.wifiManager = wifiManager2;
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(WifiParsedResult... wifiParsedResultArr) {
        int i = 0;
        WifiParsedResult wifiParsedResult = wifiParsedResultArr[0];
        if (!this.wifiManager.isWifiEnabled()) {
            Log.i(TAG, "Enabling wi-fi...");
            if (this.wifiManager.setWifiEnabled(true)) {
                Log.i(TAG, "Wi-fi enabled");
                while (!this.wifiManager.isWifiEnabled()) {
                    if (i >= 10) {
                        Log.i(TAG, "Took too long to enable wi-fi, quitting");
                        return null;
                    }
                    Log.i(TAG, "Still waiting for wi-fi to enable...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException unused) {
                    }
                    i++;
                }
            } else {
                Log.w(TAG, "Wi-fi could not be enabled!");
                return null;
            }
        }
        String networkEncryption = wifiParsedResult.getNetworkEncryption();
        try {
            NetworkType forIntentValue = NetworkType.forIntentValue(networkEncryption);
            if (forIntentValue == NetworkType.NO_PASSWORD) {
                changeNetworkUnEncrypted(this.wifiManager, wifiParsedResult);
            } else {
                String password = wifiParsedResult.getPassword();
                if (password != null && !password.isEmpty()) {
                    if (forIntentValue == NetworkType.WEP) {
                        changeNetworkWEP(this.wifiManager, wifiParsedResult);
                    } else if (forIntentValue == NetworkType.WPA) {
                        changeNetworkWPA(this.wifiManager, wifiParsedResult);
                    }
                }
            }
            return null;
        } catch (IllegalArgumentException unused2) {
            String str = TAG;
            Log.w(str, "Bad network type; see NetworkType values: " + networkEncryption);
            return null;
        }
    }

    private static void updateNetwork(WifiManager wifiManager2, WifiConfiguration wifiConfiguration) {
        Integer findNetworkInExistingConfig = findNetworkInExistingConfig(wifiManager2, wifiConfiguration.SSID);
        if (findNetworkInExistingConfig != null) {
            String str = TAG;
            Log.i(str, "Removing old configuration for network " + wifiConfiguration.SSID);
            wifiManager2.removeNetwork(findNetworkInExistingConfig.intValue());
            wifiManager2.saveConfiguration();
        }
        int addNetwork = wifiManager2.addNetwork(wifiConfiguration);
        if (addNetwork < 0) {
            String str2 = TAG;
            Log.w(str2, "Unable to add network " + wifiConfiguration.SSID);
        } else if (wifiManager2.enableNetwork(addNetwork, true)) {
            String str3 = TAG;
            Log.i(str3, "Associating to network " + wifiConfiguration.SSID);
            wifiManager2.saveConfiguration();
        } else {
            String str4 = TAG;
            Log.w(str4, "Failed to enable network " + wifiConfiguration.SSID);
        }
    }

    private static WifiConfiguration changeNetworkCommon(WifiParsedResult wifiParsedResult) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = quoteNonHex(wifiParsedResult.getSsid(), new int[0]);
        wifiConfiguration.hiddenSSID = wifiParsedResult.isHidden();
        return wifiConfiguration;
    }

    private static void changeNetworkWEP(WifiManager wifiManager2, WifiParsedResult wifiParsedResult) {
        WifiConfiguration changeNetworkCommon = changeNetworkCommon(wifiParsedResult);
        changeNetworkCommon.wepKeys[0] = quoteNonHex(wifiParsedResult.getPassword(), 10, 26, 58);
        changeNetworkCommon.wepTxKeyIndex = 0;
        changeNetworkCommon.allowedAuthAlgorithms.set(1);
        changeNetworkCommon.allowedKeyManagement.set(0);
        changeNetworkCommon.allowedGroupCiphers.set(2);
        changeNetworkCommon.allowedGroupCiphers.set(3);
        changeNetworkCommon.allowedGroupCiphers.set(0);
        changeNetworkCommon.allowedGroupCiphers.set(1);
        updateNetwork(wifiManager2, changeNetworkCommon);
    }

    private static void changeNetworkWPA(WifiManager wifiManager2, WifiParsedResult wifiParsedResult) {
        WifiConfiguration changeNetworkCommon = changeNetworkCommon(wifiParsedResult);
        changeNetworkCommon.preSharedKey = quoteNonHex(wifiParsedResult.getPassword(), 64);
        changeNetworkCommon.allowedAuthAlgorithms.set(0);
        changeNetworkCommon.allowedProtocols.set(0);
        changeNetworkCommon.allowedProtocols.set(1);
        changeNetworkCommon.allowedKeyManagement.set(1);
        changeNetworkCommon.allowedKeyManagement.set(2);
        changeNetworkCommon.allowedPairwiseCiphers.set(1);
        changeNetworkCommon.allowedPairwiseCiphers.set(2);
        changeNetworkCommon.allowedGroupCiphers.set(2);
        changeNetworkCommon.allowedGroupCiphers.set(3);
        updateNetwork(wifiManager2, changeNetworkCommon);
    }

    private static void changeNetworkUnEncrypted(WifiManager wifiManager2, WifiParsedResult wifiParsedResult) {
        WifiConfiguration changeNetworkCommon = changeNetworkCommon(wifiParsedResult);
        changeNetworkCommon.allowedKeyManagement.set(0);
        updateNetwork(wifiManager2, changeNetworkCommon);
    }

    private static Integer findNetworkInExistingConfig(WifiManager wifiManager2, String str) {
        List<WifiConfiguration> configuredNetworks = wifiManager2.getConfiguredNetworks();
        if (configuredNetworks == null) {
            return null;
        }
        for (T t : configuredNetworks) {
            String str2 = ((WifiConfiguration) t).SSID;
            if (str2 != null && str2.equals(str)) {
                return Integer.valueOf(((WifiConfiguration) t).networkId);
            }
        }
        return null;
    }

    private static String quoteNonHex(String str, int... iArr) {
        return isHexOfLength(str, iArr) ? str : convertToQuotedString(str);
    }

    private static String convertToQuotedString(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"') {
            return str;
        }
        return Typography.quote + str + Typography.quote;
    }

    private static boolean isHexOfLength(CharSequence charSequence, int... iArr) {
        if (charSequence == null || !HEX_DIGITS.matcher(charSequence).matches()) {
            return false;
        }
        if (iArr.length == 0) {
            return true;
        }
        for (int i : iArr) {
            if (charSequence.length() == i) {
                return true;
            }
        }
        return false;
    }
}
