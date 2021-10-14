package com.pushwoosh.tags;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.JsonUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TagsBundle {
    private final Map<String, Object> a;

    public static class Builder {
        private final Map<String, Object> a = new HashMap();

        public Builder appendList(String str, List<String> list) {
            HashMap hashMap = new HashMap();
            hashMap.put("operation", "append");
            hashMap.put("value", list);
            this.a.put(str, hashMap);
            return this;
        }

        public TagsBundle build() {
            return new TagsBundle(this);
        }

        public Builder incrementInt(String str, int i) {
            HashMap hashMap = new HashMap();
            hashMap.put("operation", "increment");
            hashMap.put("value", Integer.valueOf(i));
            this.a.put(str, hashMap);
            return this;
        }

        public Builder putAll(JSONObject jSONObject) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                this.a.put(next, jSONObject.opt(next));
            }
            return this;
        }

        public Builder putBoolean(String str, boolean z) {
            this.a.put(str, Boolean.valueOf(z));
            return this;
        }

        public Builder putDate(String str, Date date) {
            this.a.put(str, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
            return this;
        }

        public Builder putInt(String str, int i) {
            this.a.put(str, Integer.valueOf(i));
            return this;
        }

        public Builder putList(String str, List<String> list) {
            this.a.put(str, list);
            return this;
        }

        public Builder putLong(String str, long j) {
            this.a.put(str, Long.valueOf(j));
            return this;
        }

        public Builder putString(String str, String str2) {
            this.a.put(str, str2);
            return this;
        }

        public Builder remove(String str) {
            this.a.put(str, null);
            return this;
        }
    }

    private TagsBundle(Builder builder) {
        this.a = builder.a;
    }

    public boolean getBoolean(String str, boolean z) {
        Object obj = this.a.get(str);
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : z;
    }

    public int getInt(String str, int i) {
        Object obj = this.a.get(str);
        return obj instanceof Number ? ((Number) obj).intValue() : i;
    }

    @Nullable
    public List<String> getList(String str) {
        Object obj = this.a.get(str);
        if (obj instanceof List) {
            return (List) obj;
        }
        ArrayList arrayList = new ArrayList();
        if (!(obj instanceof JSONArray)) {
            return null;
        }
        for (int i = 0; i < ((JSONArray) obj).length(); i++) {
            try {
                arrayList.add(((JSONArray) obj).getString(i));
            } catch (JSONException unused) {
            }
        }
        return arrayList;
    }

    public long getLong(String str, long j) {
        Object obj = this.a.get(str);
        return obj instanceof Number ? ((Number) obj).longValue() : j;
    }

    public Map<String, Object> getMap() {
        return this.a;
    }

    @Nullable
    public String getString(String str) {
        Object obj = this.a.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    @NonNull
    public JSONObject toJson() {
        return JsonUtils.mapToJson(this.a);
    }
}
