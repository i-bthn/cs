package com.pushwoosh.inbox.ui.utils;

import android.widget.ImageView;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GlideUtils {
    public static void applyInto(RequestBuilder requestBuilder, ImageView imageView) {
        Method method;
        try {
            method = RequestBuilder.class.getMethod("apply", Class.forName("com.bumptech.glide.request.BaseRequestOptions"));
        } catch (Throwable unused) {
            method = RequestBuilder.class.getMethod("apply", Class.forName("com.bumptech.glide.request.RequestOptions"));
        }
        ((RequestBuilder) method.invoke(requestBuilder, getCircleCropRequestOptions())).into(imageView);
    }

    public static Object getCircleCropRequestOptions() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        try {
            Object newInstance = RequestOptions.class.newInstance();
            Object newInstance2 = CircleCrop.class.newInstance();
            return RequestOptions.class.getMethod("transform", Transformation.class).invoke(newInstance, newInstance2);
        } catch (Throwable unused) {
            return getCircleCropBaseRequestOptions();
        }
    }

    private static Object getCircleCropBaseRequestOptions() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return RequestOptions.class.getMethod("circleCropTransform", new Class[0]).invoke(null, new Object[0]);
    }
}
