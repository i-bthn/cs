package com.pushwoosh.repository;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.network.LoggableRequest;
import com.pushwoosh.internal.network.PushRequest;
import com.pushwoosh.tags.Tags;
import com.pushwoosh.tags.TagsBundle;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class e extends PushRequest<TagsBundle> implements LoggableRequest {
    e() {
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "getTags";
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public TagsBundle parseResponse(@NonNull JSONObject jSONObject) throws JSONException {
        return Tags.fromJson(jSONObject.getJSONObject("result"));
    }
}
