package com.darktalker.cordova.screenshot;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;

public class PermissionHelper {
    private static final String LOG_TAG = "CordovaPermissionHelper";

    public static void requestPermission(CordovaPlugin cordovaPlugin, int i, String str) {
        requestPermissions(cordovaPlugin, i, new String[]{str});
    }

    public static void requestPermissions(CordovaPlugin cordovaPlugin, int i, String[] strArr) {
        try {
            CordovaInterface.class.getDeclaredMethod("requestPermissions", CordovaPlugin.class, Integer.TYPE, String[].class).invoke(cordovaPlugin.cordova, cordovaPlugin, Integer.valueOf(i), strArr);
        } catch (NoSuchMethodException unused) {
            LOG.d(LOG_TAG, "No need to request permissions " + Arrays.toString(strArr));
            deliverPermissionResult(cordovaPlugin, i, strArr);
        } catch (IllegalAccessException e) {
            LOG.e(LOG_TAG, "IllegalAccessException when requesting permissions " + Arrays.toString(strArr), e);
        } catch (InvocationTargetException e2) {
            LOG.e(LOG_TAG, "invocationTargetException when requesting permissions " + Arrays.toString(strArr), e2);
        }
    }

    public static boolean hasPermission(CordovaPlugin cordovaPlugin, String str) {
        try {
            return ((Boolean) CordovaInterface.class.getDeclaredMethod("hasPermission", String.class).invoke(cordovaPlugin.cordova, str)).booleanValue();
        } catch (NoSuchMethodException unused) {
            LOG.d(LOG_TAG, "No need to check for permission " + str);
            return true;
        } catch (IllegalAccessException e) {
            LOG.e(LOG_TAG, "IllegalAccessException when checking permission " + str, e);
            return false;
        } catch (InvocationTargetException e2) {
            LOG.e(LOG_TAG, "invocationTargetException when checking permission " + str, e2);
            return false;
        }
    }

    private static void deliverPermissionResult(CordovaPlugin cordovaPlugin, int i, String[] strArr) {
        int[] iArr = new int[strArr.length];
        Arrays.fill(iArr, 0);
        try {
            CordovaPlugin.class.getDeclaredMethod("onRequestPermissionResult", Integer.TYPE, String[].class, int[].class).invoke(cordovaPlugin, Integer.valueOf(i), strArr, iArr);
        } catch (NoSuchMethodException e) {
            LOG.e(LOG_TAG, "NoSuchMethodException when delivering permissions results", e);
        } catch (IllegalAccessException e2) {
            LOG.e(LOG_TAG, "IllegalAccessException when delivering permissions results", e2);
        } catch (InvocationTargetException e3) {
            LOG.e(LOG_TAG, "InvocationTargetException when delivering permissions results", e3);
        }
    }
}
