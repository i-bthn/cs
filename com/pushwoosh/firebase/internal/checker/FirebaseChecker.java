package com.pushwoosh.firebase.internal.checker;

import com.pushwoosh.internal.checker.Checker;
import com.pushwoosh.internal.utils.PWLog;

public class FirebaseChecker implements Checker {
    @Override // com.pushwoosh.internal.checker.Checker
    public boolean check() {
        try {
            Class.forName("com.google.firebase.iid.FirebaseInstanceId");
            return false;
        } catch (ClassNotFoundException e) {
            PWLog.error("You must add \"implementation 'com.google.firebase:firebase-messaging:+'\" line to your app build.gradle.\nCheck documentation for more info https://goo.gl/UVJKfp");
            throw new IllegalStateException("You must add \"implementation 'com.google.firebase:firebase-messaging:+'\" line to your app build.gradle.\nCheck documentation for more info https://goo.gl/UVJKfp", e);
        }
    }
}
