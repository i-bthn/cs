package com.pushwoosh.richmedia.animation;

import android.view.View;
import android.view.animation.Animation;

public interface RichMediaAnimation {
    void closeAnimation(View view, View view2, Animation.AnimationListener animationListener);

    void openAnimation(View view, View view2);
}
