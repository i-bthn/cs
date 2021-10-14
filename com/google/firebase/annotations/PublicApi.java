package com.google.firebase.annotations;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@17.0.0 */
public @interface PublicApi {
}
