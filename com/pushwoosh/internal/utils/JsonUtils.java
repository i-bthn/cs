package com.pushwoosh.internal.utils;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class JsonUtils {
    private JsonUtils() {
    }

    private static Bundle a(Map<String, Object> map) {
        Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                bundle.putString(key, (String) value);
            } else if (value instanceof Integer) {
                bundle.putInt(key, ((Integer) value).intValue());
            } else if (value instanceof Float) {
                bundle.putFloat(key, ((Float) value).floatValue());
            } else if (value instanceof Double) {
                bundle.putDouble(key, ((Double) value).doubleValue());
            } else if (value instanceof Boolean) {
                bundle.putBoolean(key, ((Boolean) value).booleanValue());
            } else if (value instanceof Byte) {
                bundle.putByte(key, ((Byte) value).byteValue());
            } else if (value instanceof Long) {
                bundle.putLong(key, ((Long) value).longValue());
            } else if (value instanceof Character) {
                bundle.putChar(key, ((Character) value).charValue());
            } else if (value instanceof char[]) {
                bundle.putCharArray(key, (char[]) value);
            } else if (value instanceof float[]) {
                bundle.putFloatArray(key, (float[]) value);
            } else if (value instanceof double[]) {
                bundle.putDoubleArray(key, (double[]) value);
            } else if (value instanceof int[]) {
                bundle.putIntArray(key, (int[]) value);
            } else if (value instanceof long[]) {
                bundle.putLongArray(key, (long[]) value);
            } else if (value instanceof byte[]) {
                bundle.putByteArray(key, (byte[]) value);
            } else if (value instanceof Map) {
                bundle.putBundle(key, a((Map<String, Object>) ((Map) value)));
            } else {
                throw new IllegalArgumentException("cant parse value by key:" + key + " value:" + value);
            }
        }
        return bundle;
    }

    private static Object a(Object obj) {
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject)) {
            return obj;
        }
        try {
            if (obj instanceof Collection) {
                return collectionToJson((Collection) obj);
            }
            if (obj.getClass().isArray()) {
                return arrayToJson((Object[]) obj);
            }
            if (obj instanceof Map) {
                return mapToJson((Map) obj);
            }
            if ((obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Short)) {
                return obj;
            }
            if (obj instanceof String) {
                return obj;
            }
            if (obj.getClass().getPackage().getName().startsWith("java.")) {
                return obj.toString();
            }
            return JSONObject.NULL;
        } catch (Exception e) {
            PWLog.exception(e);
        }
    }

    private static boolean a(@NonNull String str) {
        return str.trim().startsWith("[");
    }

    public static <T> JSONArray arrayToJson(T[] tArr) throws JSONException {
        int length = Array.getLength(tArr);
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < length; i++) {
            jSONArray.put(a(Array.get(tArr, i)));
        }
        return jSONArray;
    }

    private static boolean b(@NonNull String str) {
        return str.trim().startsWith("{");
    }

    @NonNull
    public static JSONObject bundleToJson(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null) {
            return jSONObject;
        }
        for (String str : bundle.keySet()) {
            try {
                Object obj = bundle.get(str);
                if (obj instanceof char[]) {
                    obj = Arrays.toString((char[]) obj);
                } else if (obj instanceof float[]) {
                    obj = Arrays.toString((float[]) obj);
                } else if (obj instanceof double[]) {
                    obj = Arrays.toString((double[]) obj);
                } else if (obj instanceof int[]) {
                    obj = Arrays.toString((int[]) obj);
                } else if (obj instanceof long[]) {
                    obj = Arrays.toString((long[]) obj);
                } else if (obj instanceof byte[]) {
                    obj = Arrays.toString((byte[]) obj);
                } else if (obj instanceof Object[]) {
                    obj = Arrays.deepToString((Object[]) obj);
                }
                jSONObject.put(str, obj);
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:6|(4:8|9|10|(3:12|26|24)(3:13|(1:15)(2:17|(1:19))|16))|20|21|27|24|4) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x004c */
    @NonNull
    public static JSONObject bundleToJsonWithUserData(Bundle bundle) {
        String str;
        Object jSONArray;
        JSONObject jSONObject = new JSONObject();
        if (bundle == null) {
            return jSONObject;
        }
        for (String str2 : bundle.keySet()) {
            if (str2.equals("u")) {
                String string = bundle.getString("u");
                if (string != null) {
                    if (b(string)) {
                        str = "userdata";
                        jSONArray = new JSONObject(string);
                    } else if (a(string)) {
                        str = "userdata";
                        jSONArray = new JSONArray(string);
                    }
                    jSONObject.put(str, jSONArray);
                }
            }
            jSONObject.put(str2, bundle.get(str2));
        }
        return jSONObject;
    }

    public static void clearJsonObject(@NonNull JSONObject jSONObject) {
        Iterator<String> keys = jSONObject.keys();
        ArrayList<String> arrayList = new ArrayList();
        while (keys.hasNext()) {
            arrayList.add(keys.next());
        }
        for (String str : arrayList) {
            jSONObject.remove(str);
        }
    }

    public static JSONArray collectionToJson(Collection collection) {
        JSONArray jSONArray = new JSONArray();
        if (collection != null) {
            for (Object obj : collection) {
                jSONArray.put(a(obj));
            }
        }
        return jSONArray;
    }

    public static Bundle jsonStringToBundle(String str) {
        return jsonStringToBundle(str, false);
    }

    public static Bundle jsonStringToBundle(String str, boolean z) {
        try {
            return a(jsonToMap(new JSONObject(str), z));
        } catch (JSONException e) {
            e.printStackTrace();
            return new Bundle();
        }
    }

    @NonNull
    public static List<Object> jsonToList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = jsonToList((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = jsonToMap((JSONObject) obj);
            } else if (JSONObject.NULL.equals(obj)) {
                obj = null;
            }
            arrayList.add(obj);
        }
        return arrayList;
    }

    @NonNull
    public static Map<String, Object> jsonToMap(@Nullable JSONObject jSONObject) throws JSONException {
        return jsonToMap(jSONObject, false);
    }

    @NonNull
    public static Map<String, Object> jsonToMap(@Nullable JSONObject jSONObject, boolean z) throws JSONException {
        HashMap hashMap = new HashMap();
        if (jSONObject == null) {
            return hashMap;
        }
        try {
            JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject2.get(next);
                if (!(obj instanceof JSONArray)) {
                    if (obj instanceof JSONObject) {
                        if (!z) {
                            obj = jsonToMap((JSONObject) obj, false);
                        }
                    } else if (JSONObject.NULL.equals(obj)) {
                        obj = null;
                    }
                    hashMap.put(next, obj);
                } else if (!z) {
                    obj = jsonToList((JSONArray) obj);
                    hashMap.put(next, obj);
                }
                obj = obj.toString();
                hashMap.put(next, obj);
            }
        } catch (Throwable unused) {
        }
        return hashMap;
    }

    @NonNull
    public static JSONObject mapToJson(@Nullable Map<?, ?> map) {
        JSONObject jSONObject = new JSONObject();
        if (map == null) {
            return new JSONObject();
        }
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if (str != null) {
                try {
                    jSONObject.put(str, a(entry.getValue()));
                } catch (JSONException e) {
                    PWLog.exception(e);
                }
            } else {
                throw new IllegalArgumentException("key == null");
            }
        }
        return jSONObject;
    }

    public static void mergeJson(@NonNull JSONObject jSONObject, @NonNull JSONObject jSONObject2) {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                jSONObject2.put(next, jSONObject.opt(next));
            } catch (JSONException e) {
                PWLog.exception(e);
            }
        }
    }
}
