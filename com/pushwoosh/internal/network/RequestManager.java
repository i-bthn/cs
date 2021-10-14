package com.pushwoosh.internal.network;

import androidx.annotation.NonNull;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;

public interface RequestManager {
    void disableReverseProxy();

    void logError(String str);

    <Response> void sendRequest(PushRequest<Response> pushRequest);

    <Response> void sendRequest(PushRequest<Response> pushRequest, Callback<Response, NetworkException> callback);

    <Response> void sendRequest(PushRequest<Response> pushRequest, String str, Callback<Response, NetworkException> callback);

    @NonNull
    <Response> Result<Response, NetworkException> sendRequestSync(PushRequest<Response> pushRequest);

    @NonNull
    <Response> Result<Response, NetworkException> sendRequestSync(PushRequest<Response> pushRequest, int i);

    void setReverseProxyUrl(String str);

    void updateBaseUrl(String str);
}
