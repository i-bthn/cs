package com.pushwoosh.tags;

import com.pushwoosh.tags.TagsBundle;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;

public final class Tags {

    /* access modifiers changed from: private */
    public static class a {
        private static final TagsBundle a = new TagsBundle.Builder().build();
    }

    private Tags() {
    }

    public static TagsBundle appendList(String str, List<String> list) {
        return new TagsBundle.Builder().appendList(str, list).build();
    }

    public static TagsBundle booleanTag(String str, boolean z) {
        return new TagsBundle.Builder().putBoolean(str, z).build();
    }

    public static TagsBundle dateTag(String str, Date date) {
        return new TagsBundle.Builder().putDate(str, date).build();
    }

    public static TagsBundle empty() {
        return a.a;
    }

    public static TagsBundle fromJson(JSONObject jSONObject) {
        return new TagsBundle.Builder().putAll(jSONObject).build();
    }

    public static TagsBundle incrementInt(String str, int i) {
        return new TagsBundle.Builder().incrementInt(str, i).build();
    }

    public static TagsBundle intTag(String str, int i) {
        return new TagsBundle.Builder().putInt(str, i).build();
    }

    public static TagsBundle listTag(String str, List<String> list) {
        return new TagsBundle.Builder().putList(str, list).build();
    }

    public static TagsBundle longTag(String str, long j) {
        return new TagsBundle.Builder().putLong(str, j).build();
    }

    public static TagsBundle removeTag(String str) {
        return new TagsBundle.Builder().remove(str).build();
    }

    public static TagsBundle stringTag(String str, String str2) {
        return new TagsBundle.Builder().putString(str, str2).build();
    }
}
